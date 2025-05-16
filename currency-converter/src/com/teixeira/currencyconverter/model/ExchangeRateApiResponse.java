package com.teixeira.currencyconverter;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class ExchangeRateApiResponse {

    @SerializedName("base_code")
    private final String baseCode;

    @SerializedName("conversion_rates")
    private final Map<String, Double> conversionRates;

    // Construtor padrão (pode ser usado por GSON, embora GSON também possa usar outros mecanismos)
    public ExchangeRateApiResponse() {
        // GSON pode precisar de um construtor no-args,
        // mas com campos final, ele geralmente usa reflection para setá-los.
        // Se houver problemas, podemos ajustar. Por ora, pode ser assim ou até mesmo removido se GSON não reclamar.
        this.baseCode = null; // Inicialização para campos final se o construtor for chamado diretamente
        this.conversionRates = null;
    }

    // Construtor para criar instâncias manualmente se necessário (não usado por GSON para desserialização)
    // public ExchangeRateApiResponse(String baseCode, Map<String, Double> conversionRates) {
    //     this.baseCode = baseCode;
    //     this.conversionRates = conversionRates;
    // }

    public String getBaseCode() {
        return baseCode;
    }

    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }

    public Double getRateFor(String currencyCode) {
        if (conversionRates == null) {
            return null;
        }
        return conversionRates.get(currencyCode);
    }

    @Override
    public String toString() {
        return "ExchangeRateApiResponse{" +
                "baseCode=\'" + baseCode + "\'," +
                " conversionRates=" + conversionRates +
                '}';
    }
}
