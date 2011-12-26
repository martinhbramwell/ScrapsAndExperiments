/*
 * Copyright 2008 Jeff Dwyer
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.foo.server.rpc230;



/**
 * Updated by Jeff Dwyer to add HibernateFilter and allow explicit Serialization
 * 
 * Copyright 2006 George Georgovassilis <g.georgovassilis[at]gmail.com>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */





import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.foo.server.rpc.hibernate.HibernateFilter;
import com.foo.server.rpc.hibernate.InfrastructureException;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.user.server.rpc.SerializationPolicy;



/**
 * Simple spring controller that merges GWT's {@link RemoteServiceServlet} , the
 * {@link Controller} and also implements the {@link RemoteService} interface so
 * as to be able to directly delegate RPC calls to extending classes.
 * 
 * @author g.georgovassilis
 * 
 */

public class GWTController extends RemoteServiceServlet implements RemoteService
{
    private static final long serialVersionUID    = 5399966488983189122L;
    private boolean           serializeEverything = false;

	private static final Logger logger = LoggerFactory.getLogger( GWTController.class );


    public void setSerializeEverything( boolean serializeEverything )
    {
        this.serializeEverything = serializeEverything;
    }


    @Override
    public String processCall( String payload ) throws SerializationException
    {
        try
        {
            RPCRequest rpcRequest = RPC.decodeRequest( payload, this.getClass(), this );
            ServerSerializationStreamWriterSenasa writer = getWriter( rpcRequest );

            return CustomRPC.invokeAndEncodeResponse( this, rpcRequest.getMethod(), rpcRequest.getParameters(), writer );

        }
        catch( IncompatibleRemoteServiceException ex )
        { 	
             logger.error(
             "An IncompatibleRemoteServiceException was thrown while processing this call.",
             ex);
            return RPC.encodeResponseForFailure( null, ex );
        }
        catch( Exception e )
        {
             logger.error(
             "An Exception was thrown while processing this call.", e );
             
            return RPC.encodeResponseForFailure( null, e );
        }
    }


    private ServerSerializationStreamWriterSenasa getWriter( RPCRequest rpcRequest )
    {
        return getWriter( rpcRequest.getSerializationPolicy() );
    }


    /**
     * would prefer to call doGetSerializationPolicy() so that we could use the
     * new serializer policies, but not sure how to get the necessary parameters
     * 
     * @return
     */
    private ServerSerializationStreamWriterSenasa getWriter()
    {
        return getWriter( OneFourTenSerializationPolicy.getInstance() );
    }


    private ServerSerializationStreamWriterSenasa getWriter( SerializationPolicy serializationPolicy )
    {
        ServerSerializationStreamWriterSenasa writer = new ServerSerializationStreamWriterSenasa( serializationPolicy );

        writer.setValueWriter( Object.class, new ValueWriter()
        {
            public void write( ServerSerializationStreamWriterSenasa stream, Object instance ) throws SerializationException
            {
                stream.writeObject( HibernateFilter.filter( instance ) );
            }
        } );
        return writer;
    }


    /**
     * implement GWTSerializer. Used for GWT host pages that want to serialize
     * objects to bootstrap GWT and prevent needing a startup async call.
     */

    public String serializeObject( Object object, Class<?> clazz ) 
    {

        ServerSerializationStreamWriterSenasa serializer = getWriter();

        try
        {
            serializer.serializeValue( object, clazz );
        }
        catch( SerializationException e )
        {
            throw new InfrastructureException( e );
        }
        String bufferStr = "//OK" + serializer.toString();
        
        logger.debug( bufferStr );
        
        return bufferStr;
    }


    /**
     * Normal GWT Serialization requires that we do a GWT compile to create the
     * serialization whitelist. Unfortunately this means we can't just restart
     * jetty and have this Controller Serialize, unless we do a gwt compile,
     * which slows us down considerably. Solutions is to use our funky laissez
     * faire 1.4.10 (RC1) style serialization policy to serialize everything
     * which means we don't need to recompile all the gwt stuff just to restart
     * jetty.
     * 
     * Use the 'serializeEverything' variable which is set differently on test
     * and deployment machines to go to regular 1.5 serialization when deployed.
     */
    @Override
    protected SerializationPolicy doGetSerializationPolicy( HttpServletRequest request, String moduleBaseURL, String strongName )
    {
        if( serializeEverything )
        {
        	logger.debug( "Using 1.4.10 (RC1) style serializaion." );
            return OneFourTenSerializationPolicy.getInstance();
        }
        else
        {
        	logger.debug( "Using Standard Serialization." );
            return super.doGetSerializationPolicy( request, moduleBaseURL, strongName );
        }
    }


    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response ) throws Exception
    {
        try
        {
            doPost( request, response );
            return null;
        }
        catch( Exception ex )
        {
            return null;
        }
    }


    protected ServletContext servletContext;
}
