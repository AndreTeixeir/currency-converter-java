package com.teixeira.currencyconverter;

import java.util.Optional;
import java.util.Scanner;

public class CurrencyConverterApplication {

    private static final String MENU = """
            Bem-vindo ao Conversor de Moedas =]

            Escolha uma opção de conversão:
            1) Dólar Americano (USD) =>> Real Brasileiro (BRL)
            2) Real Brasileiro (BRL) =>> Dólar Americano (USD)
            3) Euro (EUR)           =>> Real Brasileiro (BRL)
            4) Real Brasileiro (BRL) =>> Euro (EUR)
            5) Libra Esterlina (GBP) =>> Real Brasileiro (BRL)
            6) Dólar Australiano (AUD) =>> Real Brasileiro (BRL)
            7) Sair
            """;

    // Adicionamos uma instância do nosso cliente da API
    private static final ExchangeRateApiClient apiClient = new ExchangeRateApiClient();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Aplicação Conversor de Moedas Iniciada ===");

        while (true) {
            displayMenu();
            int menuOption = readInteger(scanner, "Digite sua opção: ");

            if (menuOption == 7) {
                System.out.println("Obrigado por usar o Conversor de Moedas! Encerrando...");
                break;
            }

            if (menuOption < 1 || menuOption > 6) {
                System.out.println("Opção inválida! Por favor, escolha uma opção entre 1 e 7.");
                continue;
            }

            double amountToConvert = readDecimal(scanner, "Digite o valor que deseja converter: ");

            processConversion(menuOption, amountToConvert);
            System.out.println("---------------------------------------------------");
        }

        scanner.close();
        System.out.println("=== Aplicação encerrada ===");
    }

    private static void displayMenu() {
        System.out.println("***************************************************");
        System.out.println(MENU);
        System.out.println("***************************************************");
    }

    private static int readInteger(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine(); // Consome a nova linha
                return value;
            } else {
                System.out.println("Entrada inválida! Por favor, digite um número inteiro.");
                scanner.next();
                scanner.nextLine();
            }
        }
    }

    private static double readDecimal(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine(); // Consome a nova linha
                return value;
            } else {
                System.out.println("Entrada inválida! Por favor, digite um valor numérico.");
                scanner.next();
                scanner.nextLine();
            }
        }
    }

    private static void processConversion(int menuOption, double amount) {
        String baseCurrency = null;
        String targetCurrency = null;

        switch (menuOption) {
            case 1: // USD -> BRL
                baseCurrency = "USD";
                targetCurrency = "BRL";
                break;
            case 2: // BRL -> USD
                baseCurrency = "BRL";
                targetCurrency = "USD";
                break;
            case 3: // EUR -> BRL
                baseCurrency = "EUR";
                targetCurrency = "BRL";
                break;
            case 4: // BRL -> EUR
                baseCurrency = "BRL";
                targetCurrency = "EUR";
                break;
            case 5: // GBP -> BRL
                baseCurrency = "GBP";
                targetCurrency = "BRL";
                break;
            case 6: // AUD -> BRL
                baseCurrency = "AUD";
                targetCurrency = "BRL";
                break;
            default:
                System.out.println("Opção de conversão desconhecida.");
                return;
        }

        System.out.printf("Convertendo %.2f %s para %s...%n", amount, baseCurrency, targetCurrency);

        Optional<ExchangeRateApiResponse> apiResponseOptional = apiClient.getLatestRates(baseCurrency);

        if (apiResponseOptional.isPresent()) {
            ExchangeRateApiResponse apiResponse = apiResponseOptional.get();
            Double rate = apiResponse.getRateFor(targetCurrency);

            if (rate != null) {
                double convertedAmount = amount * rate;
                System.out.printf("%.2f %s equivalem a %.2f %s (Taxa: %.4f)%n",
                        amount, baseCurrency, convertedAmount, targetCurrency, rate);
            } else {
                System.out.printf("Não foi possível obter a taxa de conversão de %s para %s.%n",
                        baseCurrency, targetCurrency);
            }
        } else {
            System.out.println("Erro ao buscar as taxas de câmbio. Verifique sua conexão ou a chave da API.");
        }
    }
}
