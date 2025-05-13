package com.teixeira.currencyconverter;

public class Currency {
    private String code; // Ex: "USD", "BRL", "EUR"
    private String name; // Ex: "Dólar Americano", "Real Brasileiro", "Euro"

    // Construtor para inicializar os atributos
    public Currency(String code, String name) {
        this.code = code;
        this.name = name;
    }

    // Métodos "getters" para acessar os atributos
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    // Método toString para representação textual do objeto
    // Isso é útil para exibir o objeto Currency de forma legível
    @Override
    public String toString() {
        return name + " (" + code + ")";
    }
}

