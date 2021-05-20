package com.udayanga.timetableio.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.udayanga.timetableio.model.Role;
import com.udayanga.timetableio.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class AuthenticatedUser extends org.springframework.security.core.userdetails.User
{

	private static final long serialVersionUID = 1L;
	private User user;

//	public AuthenticatedUser(User user)
//	{
//		super(user.getEmail(), user.getPassword(), getAuthorities(user));
//		this.user = user;
//	}

	public AuthenticatedUser(User user)
	{
		super(user.getEmail(), user.getPassword(), getAuthorities(user));
		this.user = user;
	}


	public User getUser()
	{
		return user;
	}

	public static AuthenticatedUser build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new AuthenticatedUser(user);
	}


	private static Collection<? extends GrantedAuthority> getAuthorities(User user)
	{
		Set<String> roleAndPermissions = new HashSet<>();
		Set<Role> roles = user.getRoles();

		for (Role role : roles)
		{
			roleAndPermissions.add(role.getName().name());
		}
		String[] roleNames = new String[roleAndPermissions.size()];
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roleAndPermissions.toArray(roleNames));
		return authorities;
	}
}
