package com.orange.teleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orange.teleservice.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,Long>{
	Users findByUserNameAndPassword(String userName,String password);
}
