package com.foo.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
/**
 * 
 * <p>The place where list states.</p>
 * Place de la pantalla de listados de provincias. 
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @since 0.1.0
 *
 */
public class ProvinciaListaPlace extends Place {

	/**
	 * Places definidos a modo ejemplo. 
	 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
	 * @since 0.1.0
	 */
	public static class Tokenizer implements
			PlaceTokenizer<ProvinciaListaPlace> {

		@Override
		public String getToken(ProvinciaListaPlace place) {
			return "";
		}

		@Override
		public ProvinciaListaPlace getPlace(String token) {
			return new ProvinciaListaPlace();
		}
	}

}
