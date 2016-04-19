package org;

import javax.ws.rs.Path;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.framework.Home;
import org.jboss.seam.resteasy.ResourceHome;
import org.model.Customer;

/**
 * @author omidp
 *  <p> URL : /omidbiz/seam/resource/restservice/customers
 *  </p>
 */
@Name("categoryResourceHome")
@Path("/customers")
public class CustomerResourceHome extends ResourceHome<Customer, Long> {

	@In(create = true)
	private CustomerHome customerHome;

	@Override
	public Home<?, Customer> getEntityHome() {
		return customerHome;
	}

	public CustomerResourceHome() {
		setMediaTypes(new String[] { "application/json" });
	}

}
