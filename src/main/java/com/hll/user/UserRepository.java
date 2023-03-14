package com.hll.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findBySnsAndEmail(Integer sns, String email);
}
