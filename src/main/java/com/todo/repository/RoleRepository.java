package com.todo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.todo.domain.Role;

/**
 * @author vinodkumara
 *
 */
public interface RoleRepository extends MongoRepository<Role, Integer> {
	
	/**
	 * @param role
	 * @return Role
	 */
	Role findByRole(Integer role);
	
	/**
	 * @param desc
	 * @return Role
	 */
	Role findByDesc(String desc);

	/**
	 * @param desc
	 * @return Role
	 */
	Role findByDescLike(String desc);

}
