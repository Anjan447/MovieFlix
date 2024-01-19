package com.anjan.MovieFlix.Controller;

import com.anjan.MovieFlix.Model.User;
import com.anjan.MovieFlix.Repo.UserRepo;
import com.anjan.MovieFlix.Service.UserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepo userRepo;

    //get all users
    @GetMapping("/allUsers")
    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.ok(this.userService.getUsers());
    }

    // get a user by id
    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable("id") Long tempId){
        return this.userService.findById(tempId);
    }


    // delete a user
    @DeleteMapping("/delete/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        this.userService.deleteUserById(userId);
    }

    // update a user
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User tempUser){
        return ResponseEntity.ok(this.userService.updateUser(tempUser));
    }

    //get the current user
    @GetMapping("/current-user")
    public  User getLoggedInUser(Principal principal){
        return ((User)this.userDetailsService.loadUserByUsername(principal.getName()));
    }

    //change are made for OTP generation through email
    @PostMapping(path = "/forgotPassword")
    public ResponseEntity<String> forgetPassword(@RequestBody Map<String, String> requestMap){
        try {
//            return userService.forgetPassword(requestMap);
            User user = userRepo.findByEmail(requestMap.get("email"));
            if (user != null) {
                ResponseEntity<String> response = userService.forgetPassword(requestMap);
                return response;
            } else {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("SOMETHING_WENT_WRONG", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @PostMapping(path = "/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody Map<String, String> requestMap){
        try {
            return userService.updatePassword(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("SOMETHING_WENT_WRONG", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(path = "/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody Map<String, String> requestMap){
        try {
            return userService.changePassword(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("SOMETHING_WENT_WRONG", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @PostMapping("/google")
    public ResponseEntity<String> authenticateGoogle(@RequestBody String idToken) throws  Exception{
        try {
            // Initialize the Google API client
            HttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();


            // Build the Google ID token verifier
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                    .setAudience(Collections.singletonList("70674515851-as87gs0v1dsuf9fsslu8on3lnd1d960j.apps.googleusercontent.com")) // Replace with your actual client ID
                    .build();


            // Verify the ID token
            GoogleIdToken googleIdToken = verifier.verify(idToken);
            if (googleIdToken != null) {
                // Extract user information from the payload
                GoogleIdToken.Payload payload = googleIdToken.getPayload();
                String userId = payload.getSubject();
                System.out.println("User ID: " + userId);
                System.out.println(payload.getEmail());

                // Add your logic to process the verified ID token
                // ...
//                AuthenticationResponse response = userService.authenticateViaGoogle(payload.getEmail());
                return userService.authenticateViaGoogle(payload.getEmail());
            } else {
                System.out.println("Invalid ID token.");
                return ResponseEntity.badRequest().body("{\"error\": \"Invalid ID token\"}");
            }
        } catch (GeneralSecurityException | IOException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
            return ResponseEntity.status(500).body("{\"error\": \"Internal Server Error\"}");
        }
    }

}