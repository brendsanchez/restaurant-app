package com.dusk.restaurant.service;

import com.dusk.restaurant.client.RestClient;
import com.dusk.restaurant.client.dto.UserResponse;
import com.dusk.restaurant.exception.GenericException;

public class UserSessionService {

    public UserResponse findByUsername(String username) {
        var userResponseResult = RestClient.getInstance().get(
                "/api/user/" + username,
                UserResponse.class
        );

        if (userResponseResult.isErr()) {
            throw new GenericException(userResponseResult.getErrorDto().getMessage());
        }

        return userResponseResult.unwrap();
    }

}
