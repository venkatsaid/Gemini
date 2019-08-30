package com.example.demo.repository;
//import javax.management.relation.Role;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.auth.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}