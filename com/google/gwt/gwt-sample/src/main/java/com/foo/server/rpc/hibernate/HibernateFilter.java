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
package com.foo.server.rpc.hibernate;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;	

import org.hibernate.Hibernate;
import org.hibernate.collection.PersistentBag;
import org.hibernate.collection.PersistentList;
import org.hibernate.collection.PersistentMap;
import org.hibernate.collection.PersistentSet;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * <p>This class is based on the book <i>Pro Web 2.0 Application Development with GWT escrito por Jeff Dwyer.</i>
 * </p>
 * This class put null values to proxy and convert persistents collections of hibernate in default implementations of collections framework.
 * 
 *  @see http://code.google.com/p/tocollege-net/
 *  @see http://groups.google.com/group/google-web-toolkit/browse_thread/thread/6bd717b92ae4a708
 *  @since 0.2.0
 */
/*
 * Esta clase es una solucion tomada del libro:
 * 
 * 				Pro Web 2.0 Application Development with GWT escrito por Jeff Dwyer.
 * 
 * 				ISBN-13 (pbk) : 978-1-59059-985-3
 * 				ISBN-10 (pbk) : 1-59059-985-3
 * 
 * 	Algunos links de referencia: http://code.google.com/p/tocollege-net/
 *                               http://groups.google.com/group/google-web-toolkit/browse_thread/thread/6bd717b92ae4a708
 *                               
 *                               
 *  Basicamente esta clase es la encargada de establecer a nulos -en los proxies de hibernate- los valores de 
 *  los objetos que fueron referenciados como de carga perezosa.
 * */
public class HibernateFilter
{
	private static final Logger logger = LoggerFactory.getLogger( HibernateFilter.class );


    public static Object filter( Object instance )
    {
        if( instance == null ) { return instance; }
        if( instance instanceof Date ) { return new java.util.Date( ( ( java.util.Date )instance ).getTime() ); }

        if( instance instanceof PersistentSet )
        {
            HashSet<Object> hashSet = new HashSet<Object>();
            PersistentSet persSet = ( PersistentSet )instance;
            if( persSet.wasInitialized() )
            {
                hashSet.addAll( persSet );
            }
            return hashSet;
        }
        if( instance instanceof PersistentList )
        {
            ArrayList<Object> arrayList = new ArrayList<Object>();
            PersistentList persList = ( PersistentList )instance;
            if( persList.wasInitialized() )
            {
                arrayList.addAll( persList );
            }
            return arrayList;
        }
        if( instance instanceof PersistentBag )
        {
            ArrayList<Object> arrayList = new ArrayList<Object>();
            PersistentBag persBag = ( PersistentBag )instance;
            if( persBag.wasInitialized() )
            {
                arrayList.addAll( persBag );
            }
            return arrayList;
        }
        if( instance instanceof PersistentMap )
        {
            HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
            PersistentMap persMap = ( PersistentMap )instance;
            if( persMap.wasInitialized() )
            {
                hashMap.putAll( persMap );
            }
            return hashMap;
        }
        //If use older Hibernate versions.
        //if( instance.getClass().getName().contains( "CGLIB" ) )
        if( instance.getClass().getName().contains( "javassist" ) )
        {

            if( Hibernate.isInitialized( instance ) )
            {

                try
                {
                    HibernateProxy hp = ( HibernateProxy )instance;
                    LazyInitializer li = hp.getHibernateLazyInitializer();
                    logger.warn( "On The Fly initialization: " + li.getEntityName() );
                    return li.getImplementation();

                }
                catch( ClassCastException c )
                {
                    logger.error( "error casting to HibernateProxy " + instance );
                    return null;
                }
            }
            else
            {
                logger.debug( "Uninitialized javassist" );
                return null;
            }
        }

        return instance;
    }

}
