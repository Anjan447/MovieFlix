package com.anjan.MovieFlix.Repo;

import com.anjan.MovieFlix.Model.User;
import com.anjan.MovieFlix.Model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);

//    User findByName(String name);

    User findByRole(UserRole userRole);

}
