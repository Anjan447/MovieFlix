package com.anjan.MovieFlix.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Request {

    private String email;
    private String password;

}