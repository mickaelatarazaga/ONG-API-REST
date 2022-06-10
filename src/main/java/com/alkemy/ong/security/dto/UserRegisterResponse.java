package com.alkemy.ong.security.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterResponse {

    @Schema(name = "id", type = "Long", example = "1")
    private Long id;
    @Schema(name = "firstName", example = "Mariana", type = "String")
    private String firstName;
    @Schema(name = "lastName", example = "López", type = "String")
    private String lastName;
    @Schema(name = "email", example = "marianalopez@mail.com", type = "String")
    private String email;
    @Schema(name = "jwt", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0YXJhemFnYS5taWNrYWVsYUBnbWFpbC5jb20iLCJleHAiOjE2NDg3NzMyNTgsImlhdCI6MTY0ODczNzI1OH0.yA07ci-rUbAvfoIAB4jeqQwl8lg1NOabLLcCZFLZuis", type = "String")
    private String jwt;

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegisterResponse that = (UserRegisterResponse) o;
        return id.equals(that.id) &&
                email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
