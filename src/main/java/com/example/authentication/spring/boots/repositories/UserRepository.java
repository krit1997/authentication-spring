package com.example.authentication.spring.boots.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.authentication.spring.boots.entities.User;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);

    Optional<User> findByUsernameOrEmail(String username, String email);

    @Query(value = "SELECT * FROM app_users u WHERE u.username = :username", nativeQuery = true)
    Optional<User> findByIdentity(@Param("username") String username);

    @Query(value = "SELECT * FROM app_users u WHERE u.is_active = true", nativeQuery = true)
    Optional<List<User>> findAllActive();

    Page<User> findAll(Pageable pageable);

    @Query(value = """
                SELECT * FROM app_users
                WHERE is_active = true
                AND (
                    CASE WHEN :column = 'email' THEN email
                         WHEN :column = 'first_name' THEN first_name
                         WHEN :column = 'role' THEN role
                    END
                ) ILIKE :keyword
            """, nativeQuery = true)
    Page<User> findByColumnPage(@Param("column") String column, @Param("keyword") String keyword, Pageable pageable);

    Optional<User> findById(String id);
}
