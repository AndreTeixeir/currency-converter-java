package com.teixeira.currencyconverter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
// Certifique-se de que sua classe ExchangeRateApiResponse está no mesmo pacote
// ou importe-a corretamente se estiver em outro lugar.

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class ExchangeRateApiClient {

    private static final String API_KEY = "cd53f7ffa82865471e7840d5"; // Mantenha sua chave API real aqui
    private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6/";

    private final HttpClient httpClient;
    private final Gson gson; // Instância do GSON

    public ExchangeRateApiClient()  {
        this.httpClient = HttpClient.newHttpClient() ;
        this.gson = new Gson(); // Inicializa o GSON
    }

    public Optional<ExchangeRateApiResponse> getLatestRates(String baseCurrencyCode) { // <<< TIPO DE RETORNO CORRIGIDO
        URI uri = buildUriForLatestRates(baseCurrencyCode);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString() );
            int statusCode = response.statusCode();

            if (statusCode >= 200 && statusCode < 300) {
                String jsonBody = response.body();
                try {
                    // Usa GSON para converter o JSON para o objeto ExchangeRateApiResponse
                    ExchangeRateApiResponse apiResponse = gson.fromJson(jsonBody, ExchangeRateApiResponse.class);
                    return Optional.of(apiResponse);
                } catch (JsonSyntaxException e) {
                    System.err.printf("Erro ao desserializar JSON para %s: %s%n", baseCurrencyCode, e.getMessage());
                    System.err.println("JSON recebido: " + jsonBody);
                    return Optional.empty();
                }
            } else {
                System.err.printf("Erro ao buscar taxas de câmbio para %s. Código HTTP: %d%n", baseCurrencyCode, statusCode);
                System.err.println("Corpo da resposta: " + response.body());
                return Optional.empty();
            }

        } catch (IOException | InterruptedException e) {
            System.err.printf("Erro ao chamar a API para %s: %s%n", baseCurrencyCode, e.getMessage());
            Thread.currentThread().interrupt(); // Restaura o status de interrupção
            return Optional.empty();
        }
    }

    private URI buildUriForLatestRates(String baseCurrencyCode) {
        String fullUrl = API_BASE_URL + API_KEY + "/latest/" + baseCurrencyCode;
        return URI.create(fullUrl);
    }
}
