package com.anjan.MovieFlix.ServiceImpl;

import com.anjan.MovieFlix.Model.User;
import com.anjan.MovieFlix.Model.UserRole;
import com.anjan.MovieFlix.Repo.UserRepo;
import com.anjan.MovieFlix.Security.JwtAuthenticationFilter;
import com.anjan.MovieFlix.Security.JwtHelper;
import com.anjan.MovieFlix.Service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    JwtAuthenticationFilter jwtFilter;
    @Autowired
    JwtHelper jwtHelper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public Set<User> getUsers() {
        return new LinkedHashSet<>(userRepo.findAll());
    }

    @Override
    public User addUser(User tempUser) throws Exception{
//        System.err.println(tempUser);
        User local = this.userRepo.findByEmail(tempUser.getEmail());
        if(local!=null)
        {
            System.out.println("User already Exists");
            throw new Exception("User already Exists");
        }else {
            // tempUser.setUserId(UUID.randomUUID());
//            System.err.println(tempUser.getPassword());
            tempUser.setPassword(passwordEncoder.encode(tempUser.getPassword()));
            tempUser.setRole(UserRole.USER);
            local = this.userRepo.save(tempUser);
        }

        return local;
    }

    @Override
    public void deleteUserById(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public User updateUser(User user) {
        return userRepo.save(user);
    }

    @Autowired
    public JavaMailSender emailSender;

    //implementing the OTP send module for forgot password

    @Override
    public ResponseEntity<String> forgetPassword(Map<String, String> requestMap) {
        System.out.println("inside the forgot password function");
        try {
            User user = userRepo.findByEmail(requestMap.get("email"));
            System.out.println("user email is : " + user.getEmail());
            if (!Objects.isNull(user)) {
                System.out.println("11");
                this.forgetMail(user.getEmail() , "OTP by Splitbills" , requestMap.get("otp"));
                return new ResponseEntity<>("Check Your mail for Credentials", HttpStatus.OK);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("SOMETHING_WENT_WRONG", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    public void  forgetMail(String to , String subject, String otp) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("vsampath@argusoft.com");
        helper.setTo(to);
        helper.setSubject(subject);
        String htmlMSG = "<p><b>Hello Thank you For receiving the Email </b></p><p><b>Your Login details for Split Bills</b></p><b>Email:</b>"+ to + "<br><b>OTP: </b>" + otp + "<br><a href=\"http://localhost:4200/\">Click here to login</a></p>";
        message.setContent(htmlMSG , "text/html");
        emailSender.send(message);
    }


    //update password
    @Override
    public ResponseEntity<String> updatePassword(Map<String, String> requestMap) {
        try {
            User user = userRepo.findByEmail(requestMap.get("email"));
            if (!user.equals(null)) {

                user.setPassword(passwordEncoder.encode(requestMap.get("password")));
                userRepo.save(user);
                return new ResponseEntity<>("Password Updated Successfully", HttpStatus.OK);


            }
            return new ResponseEntity<>("SOMETHING_WENT_WRONG", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("SOMETHING_WENT_WRONG",  HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try {
            User user = userRepo.findByEmail(jwtFilter.getCurrentUser());
            if (!user.equals(null)) {
                if (passwordEncoder.matches(requestMap.get("oldPassword"), user.getPassword())) {
                    user.setPassword(passwordEncoder.encode(requestMap.get("newPassword")));
                    userRepo.save(user);
                    return new ResponseEntity<>("Password Updated Successfully", HttpStatus.OK);
                }
                log.info(passwordEncoder.encode(requestMap.get("oldPassword")));
                return new ResponseEntity<>("Incorrect Old Password", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("SOMETHING_WENT_WRONG", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("SOMETHING_WENT_WRONG", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    public ResponseEntity<String> authenticateViaGoogle(String googleEmail) {
        try {
            // Check if the email exists in the database
            User user = userRepo.findByEmail(googleEmail);
            if (user != null) {
                // If the user with the Google email exists, generate a JWT token for them
//                var jwtToken = jwtUtil.generateToken(googleEmail, "CUSTOMER");

//                saveUserToken(user, jwtToken);
//                return AuthenticationResponse.builder().token(jwtToken).build();
                return new ResponseEntity<String>("{\"token\":\"" + jwtHelper.generateToken(user) + "\",\"role\":\"CUSTOMER" + "\"}", HttpStatus.OK);
            } else {
                // Handle the case when the email does not exist in the database
                // You can return an error response or perform other actions as needed
                // For example, you can throw an exception, log the event, or return a specific error response.
                throw new Exception("Email not found in the database");
            }
        } catch (Exception exception) {
            log.error("{}", exception);
        }
        return new ResponseEntity<String>("{\"message\":\"" + "Bad credentials" + "\"}", HttpStatus.BAD_REQUEST);
    }

    @PostConstruct
    public void createAdminAccount(){
        User adminAccount = userRepo.findByRole(UserRole.ADMIN);
        if(null == adminAccount){
            User user= new User();
            user.setFirstName("admin");
            user.setLastName("istrator");
            user.setUserName("admin");
            user.setEmail("admin@test.com");
            user.setPassword(passwordEncoder.encode("admin123"));
            user.setPhone("1234567890");

            user.setRole(UserRole.ADMIN);
            userRepo.save(user);
        }
    }


}