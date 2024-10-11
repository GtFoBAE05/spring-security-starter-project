package org.gtfo.learn_spring_security.dto.mapper;

import org.gtfo.learn_spring_security.dto.response.auth.LoginResponse;
import org.gtfo.learn_spring_security.dto.response.auth.RegisterResponse;
import org.gtfo.learn_spring_security.entity.UserAccount;

public class UserAccountMapper {

    public static RegisterResponse userAccountToRegisterResponse(UserAccount userAccount){
        return RegisterResponse.builder()
                .id(userAccount.getId())
                .username(userAccount.getUsername())
                .role(userAccount.getRole().stream().map(
                        role -> role.getName().name()
                ).toList())
                .build();
    }

    public static LoginResponse userAccountToLoginResponse(String token){
        return LoginResponse.builder()
                .accessToken(token)
                .build();
    }
}
