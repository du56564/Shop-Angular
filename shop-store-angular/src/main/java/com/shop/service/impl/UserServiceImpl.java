package com.shop.service.impl;

import java.util.Set;








import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.domain.User;
import com.shop.domain.security.UserRole;
import com.shop.repository.RoleRepository;
import com.shop.repository.UserRepository;
import com.shop.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);  
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Transactional
	public User createUser(User user, Set<UserRole> userRoles) {
		User localUser = userRepository.findByUsername(user.getUsername());
		
		if(localUser != null){
			LOG.info("User with username {} already  exist. Nothing will be done. ",user.getUsername());
		}else{
			for(UserRole ur : userRoles){
				roleRepository.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			localUser = userRepository.save(user);	
		}	
		
		return localUser;	
	}

}