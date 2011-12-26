package com.foo.client.places;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.WithTokenizers;

/**
 * <p>The mapper where define the {@link PlaceTokenizer} of the app.</p>
 * Mapper donde se definen los {@link PlaceTokenizer} de la aplicaci&oacute;n.
 * 
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @since 0.1.0
 * 
 */
@WithTokenizers({ ProvinciaEditPlace.Tokenizer.class,
		ProvinciaListaPlace.Tokenizer.class, MainPlace.Tokenizer.class })
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}
