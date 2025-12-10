package com.backend.rocker.repository;

import com.backend.rocker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);
    
    @Query("""
            select u from User u where 
                        u.name = :name or 
                        u.phoneNumber = :phoneNumber or 
                        u.email = :email
            """)
    Optional<User> findDuplicateRecord(String name, String phoneNumber, String email);
}
