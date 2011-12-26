package com.foo.domain;

import com.foo.shared.domain.Provincia;

/**
 * <p>Basic test to verify hibernate mappings.</p>
 * Este test b&aacute;sico se usa solamente para verificar que levante
 * correctamente el entity manager para chequear los mappings.
 * 
 * @author Gardella Juan Pablo -
 *         jpgardel@senasa.gov.ar/gardellajuanpablo@gmail.com
 * @since 0.1.0
 * 
 */
public class LecturaEntityManagerTest extends BaseTest {

	/**
	 * El test solamente levanta el entity manager y se ejecuta el validor de
	 * hibernate.
	 * 
	 * @throws Exception
	 *             en caso de error
	 */
	public void testLeerEntityManager() throws Exception {
		// No hacemos nada. Se ejecuta el validador de hibernate

		getEntityManager().find(Provincia.class, 1);
	}

}
