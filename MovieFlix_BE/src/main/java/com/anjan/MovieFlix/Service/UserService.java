package com.anjan.MovieFlix.Service;

import com.anjan.MovieFlix.Model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    public Optional<User> findById(Long id);

    public Set<User> getUsers();

    public User addUser(User user) throws Exception;

    public void deleteUserById(Long id);

    public User updateUser(User user);

    ResponseEntity<String> forgetPassword(Map<String, String> requestMap);

    ResponseEntity<String> updatePassword(Map<String, String> requestMap);

    ResponseEntity<String> changePassword(Map<String, String> requestMap);

    ResponseEntity<String> authenticateViaGoogle(String email);
}