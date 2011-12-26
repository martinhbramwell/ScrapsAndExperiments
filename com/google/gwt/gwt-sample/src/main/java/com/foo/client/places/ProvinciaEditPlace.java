package com.foo.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;


/**
 * <p>An example place.</p>
 * Places definidos a modo ejemplo. Luego ser&aacute;n eliminados.
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @since 0.1.0
 */
public class ProvinciaEditPlace extends Place {

	private Integer id;
	
	public ProvinciaEditPlace(Integer id) {
		this.id=id;
	}
	
	public Integer getId() {
		return id;
	}
	public static class Tokenizer implements PlaceTokenizer<ProvinciaEditPlace> {
		
		@Override
		public String getToken(ProvinciaEditPlace place) {
			return place.id == null ? "" : place.id.toString();
		}

		@Override
		public ProvinciaEditPlace getPlace(String token) {	
			try{
				Integer id = Integer.parseInt(token);
				return new ProvinciaEditPlace(id);
			}catch(Exception e){
				return new ProvinciaEditPlace(null);
			}
		}
	}

	

}
