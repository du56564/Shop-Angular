package com.shop;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.shop.config.SecurityUtility;
import com.shop.domain.User;
import com.shop.domain.security.Role;
import com.shop.domain.security.UserRole;
import com.shop.service.UserService;

@SpringBootApplication
public class ShopAngularApplication implements CommandLineRunner{

	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(ShopAngularApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		User user1 = new User();
		
		user1.setFirstName("Deepak");
		user1.setLastName("Upadhyay");
		user1.setUsername("d");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
		user1.setEmail("udeepak1204@gmail.com");
		Set<UserRole> userRoles = new HashSet<UserRole>();
		Role role1 = new Role();
		role1.setRoleId(1);
		role1.setName("ROLE_USER");
		userRoles.add(new UserRole(user1, role1));
		userService.createUser(user1,userRoles);
		userRoles.clear();

	
		User user2 = new User();
		user2.setFirstName("Admin");
		user2.setLastName("Admin");
		user2.setUsername("admin");
		user2.setPassword(SecurityUtility.passwordEncoder().encode("p"));
		user2.setEmail("admin@gmail.com");
		Role role2 = new Role();
		role2.setRoleId(0);
		role2.setName("ROLE_ADMIN");
		userRoles.add(new UserRole(user2, role2));
		userService.createUser(user2,userRoles);
		userRoles.clear();
	}
}
