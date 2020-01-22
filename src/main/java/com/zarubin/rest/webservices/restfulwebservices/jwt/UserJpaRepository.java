package com.zarubin.rest.webservices.restfulwebservices.jwt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
}
