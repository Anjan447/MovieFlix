package com.anjan.MovieFlix.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Response {

    private  String email;
    private String jwtToken;

}
