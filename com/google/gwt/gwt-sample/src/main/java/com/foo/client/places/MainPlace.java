package com.foo.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * <p>Application's main place.</p>
 * Place principal de la aplicaci&oacute;n. Place que se visualizar&aacute; por
 * defecto.
 * 
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @since 0.1.0
 */
public class MainPlace extends Place {

	/**
	 * Tokenizer de {@link MainPlace}
	 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com 
	 * @since 0.1.0
	 */
	public static class Tokenizer implements PlaceTokenizer<MainPlace> {

		@Override
		public String getToken(MainPlace place) {
			return "";
		}

		@Override
		public MainPlace getPlace(String token) {
			return new MainPlace();
		}
	}

}
