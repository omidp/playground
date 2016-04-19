package org;

import javax.ws.rs.Path;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.resteasy.ResourceQuery;
import org.model.Customer;

@Name("categoryResourceQuery")
@Path("/customers")
public class CustomerResourceQuery extends ResourceQuery<Customer> {

	public CustomerResourceQuery() {
		setMediaTypes(new String[] { "application/json" });
	}
	
}
