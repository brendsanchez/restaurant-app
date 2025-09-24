package com.dusk.restaurant.client;

import com.dusk.restaurant.client.dto.ErrorDto;
import com.dusk.restaurant.exception.GenericException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestClient {

    private static RestClient instance;
    private final HttpClient client;
    private final ObjectMapper objectMapper;
    private final String baseUrl;

    private RestClient() {
        this.client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
        this.baseUrl = this.getBaseUrl();
    }

    public static RestClient getInstance() {
        if (instance == null) {
            instance = new RestClient();
        }
        return instance;
    }

    public <T, R> Result<R> post(String endpoint, T dto, Class<R> classResponse) {
        String json = this.getJson(dto);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(this.baseUrl + endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        return this.getResult(classResponse, httpRequest);
    }

    private <R> Result<R> getResult(Class<R> classResponse, HttpRequest httpRequest) {
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            int status = response.statusCode();
            if (status >= 200 && status < 300) {
                R body = this.objectMapper.readValue(response.body(), classResponse);
                return Result.ok(body);
            } else {
                var errorDto = this.objectMapper.readValue(response.body(), ErrorDto.class);
                return Result.err(errorDto);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
            var errorDto = ErrorDto.builder()
                    .message("Error llamado externo")
                    .build();
            return Result.err(errorDto);
        }
    }

    private <T> String getJson(T dto) {
        try {
            return this.objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.fillInStackTrace();
            throw new GenericException("error dto parse to json");
        }
    }

    private String getBaseUrl() {
        String url = System.getenv("API_BASE_URL");
        if (url == null || url.isBlank()) {
            throw new GenericException("No se encontro el env para la base url");
        }
        return url;
    }
}
