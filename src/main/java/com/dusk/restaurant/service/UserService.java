package com.dusk.restaurant.service;

import com.dusk.restaurant.client.RestClient;
import com.dusk.restaurant.client.dto.UserDto;
import com.dusk.restaurant.client.dto.UserResponse;
import com.dusk.restaurant.dto.AlertDto;
import javafx.scene.control.Alert;

public class UserService {

    public AlertDto login(String username, String password) {
        var userDto = this.getUserDto(username, password);

        try {
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
        } catch (Exception e) {
            return this.getAlertDtoError(e, "Login");
        }
    }

    public AlertDto register(String username, String password) {
        var userDto = this.getUserDto(username, password);

        try {
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
        } catch (Exception e) {
            return this.getAlertDtoError(e, "Reister");
        }
    }

    private AlertDto getAlertDtoError(Exception e, String from) {
        System.out.println(e.getMessage());
        return AlertDto.builder()
                .type(Alert.AlertType.ERROR)
                .title(from + " fallido")
                .message("Error Internal Conexion " + from)
                .build();
    }

    private UserDto getUserDto(String username, String password) {
        return UserDto.builder()
                .username(username)
                .password(password)
                .build();
    }
}
