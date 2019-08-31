package spring;

import spring.models.ClientType;

public interface CouponClientFacade {
	
	public CouponClientFacade login (String name, String password, ClientType clientType);

}
