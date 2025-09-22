package com.dusk.restaurant.service;

import com.dusk.restaurant.dto.AlertDto;
import com.dusk.restaurant.dto.LoginDto;
import com.dusk.restaurant.util.RestClient;
import javafx.scene.control.Alert;

import java.net.http.HttpResponse;

public class LoginService {

    public AlertDto login(String username, String password) {
        try {
            var loginDto = LoginDto.builder()
                    .username(username)
                    .password(password)
                    .build();

            HttpResponse<String> response = RestClient.getInstance()
                    .post("/api/login", loginDto);

            if (response.statusCode() != 200) {
                return AlertDto.builder()
                        .type(Alert.AlertType.ERROR)
                        .title("Login fallido")
                        .message("Usuario o contraseña inválidos")
                        .build();
            }

            return AlertDto.builder()
                    .type(Alert.AlertType.INFORMATION)
                    .title("Login exitoso")
                    .message("¡Bienvenido!")
                    .build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return AlertDto.builder()
                    .type(Alert.AlertType.ERROR)
                    .title("Error")
                    .message("Error de conexión")
                    .build();
        }
    }
}
