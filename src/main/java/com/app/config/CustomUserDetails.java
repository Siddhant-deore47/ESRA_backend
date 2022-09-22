package com.app.config;

import java.util.Collection;
import java.util.HashSet;

import com.app.model.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class CustomUserDetails implements UserDetails {
	
	private BaseUser baseUser;

	public CustomUserDetails(BaseUser baseUser) {
		this.baseUser = baseUser;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		HashSet<SimpleGrantedAuthority> set = new HashSet<>();
		if(baseUser instanceof Admin)
			set.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		else if(baseUser instanceof User)
			set.add(new SimpleGrantedAuthority("ROLE_USER"));
		else if(baseUser instanceof Hospital)
			set.add(new SimpleGrantedAuthority("ROLE_HOSPITAL"));
		else if(baseUser instanceof PoliceStation)
			set.add(new SimpleGrantedAuthority("ROLE_POLICESTATION"));
		return set;
	}

	@Override
	public String getPassword() {
		if(baseUser instanceof Admin)
			return ((Admin)baseUser).getPassword();
		else if(baseUser instanceof User)
			return ((User)baseUser).getPassword();
		else if(baseUser instanceof Hospital)
			return ((Hospital)baseUser).getPassword();
		else
			return ((PoliceStation)baseUser).getPassword();
	}

	@Override
	public String getUsername() {
		if(baseUser instanceof Admin)
			return ((Admin)baseUser).getEmail();
		else if(baseUser instanceof User)
			return ((User)baseUser).getEmail();
		else if(baseUser instanceof Hospital)
			return ((Hospital)baseUser).getEmail();
		else
			return ((PoliceStation)baseUser).getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
