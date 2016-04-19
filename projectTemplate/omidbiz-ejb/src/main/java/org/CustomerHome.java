package org;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityHome;
import org.model.Customer;

@Name("customerHome")
@Scope(ScopeType.CONVERSATION)
public class CustomerHome extends EntityHome<Customer>{

}
