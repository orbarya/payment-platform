package com.example.payment_platform.common.repository;

import com.example.payment_platform.common.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT u FROM User u WHERE u.fullName LIKE %:searchTerm%")
    List<User> findByFullNameContaining(@Param("searchTerm") String searchTerm);
}
