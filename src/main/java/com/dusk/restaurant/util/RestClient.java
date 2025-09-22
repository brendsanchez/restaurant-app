package com.dusk.restaurant.util;

import com.dusk.restaurant.exception.GenericException;
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

    public <T> HttpResponse<String> post(String endpoint, T dto) throws Exception {
        String json = this.objectMapper.writeValueAsString(dto);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.baseUrl + endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private String getBaseUrl() {
        String url = System.getenv("API_BASE_URL");
        if (url == null || url.isBlank()) {
            throw new GenericException("No se encontro el env para la base url");
        }
        return url;
    }
}
