package CRUD3.CRUD3.dto;

import lombok.Data;

@Data
public class Oauth2 {
    String provider;
    String id;
    String email;
    String name;
    String photoUrl;
    String firstName;
    String lastName;
    String authToken;
    String idToken;
    String authorizationCode;
}
