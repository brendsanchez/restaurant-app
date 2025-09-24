package com.dusk.restaurant.service;

import com.dusk.restaurant.client.RestClient;
import com.dusk.restaurant.client.dto.UserDto;
import com.dusk.restaurant.client.dto.UserResponse;
import com.dusk.restaurant.dto.AlertDto;
import javafx.scene.control.Alert;

public class UserService {

    public AlertDto login(String username, String password) {
        var userDto = this.getUserDto(username, password);

        var loginResponse = RestClient.getInstance().post(
                "/api/login",
                userDto,
                UserResponse.class
        );

        if (loginResponse.isErr()) {
            return AlertDto.builder()
                    .type(Alert.AlertType.ERROR)
                    .title("Login fallido")
                    .message(loginResponse.getErrorDto().getMessage())
                    .build();
        }

        return AlertDto.builder()
                .type(Alert.AlertType.INFORMATION)
                .title("Login exitoso")
                .message("¡Bienvenido!")
                .build();
    }

    public AlertDto register(String username, String password) {
        var userDto = this.getUserDto(username, password);

        var registerResponse = RestClient.getInstance().post(
                "/api/register",
                userDto,
                UserResponse.class
        );

        if (registerResponse.isErr()) {
            return AlertDto.builder()
                    .type(Alert.AlertType.ERROR)
                    .title("Register fallido")
                    .message(registerResponse.getErrorDto().getMessage())
                    .build();
        }

        return AlertDto.builder()
                .type(Alert.AlertType.INFORMATION)
                .title("Register exitoso")
                .message("¡Creacion de usuario existosamente!")
                .build();
    }

    private UserDto getUserDto(String username, String password) {
        return UserDto.builder()
                .username(username)
                .password(password)
                .build();
    }
}
