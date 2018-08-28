package com.shop.repository;

import org.springframework.data.repository.CrudRepository;

import com.shop.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{

}
