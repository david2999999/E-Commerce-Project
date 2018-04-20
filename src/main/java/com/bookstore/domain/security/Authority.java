package com.bookstore.domain.security;

import org.springframework.security.core.GrantedAuthority;


//Think of a GrantedAuthority as being a "permission" or a "right". 
//Those "permissions" are (normally) expressed as strings (with the getAuthority() method).
//Those strings let you identify the permissions and let your voters decide if they grant access to something.
public class Authority implements GrantedAuthority {
	
	private final String authority;

	public Authority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}
	
	
	
}
