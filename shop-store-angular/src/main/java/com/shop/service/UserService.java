package com.shop.service;

import java.util.Set;

import com.shop.domain.User;
import com.shop.domain.security.UserRole;

public interface UserService {
	
	User createUser(User user, Set<UserRole> userRoles);
	
}
