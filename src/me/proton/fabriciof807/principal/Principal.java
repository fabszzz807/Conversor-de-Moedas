package me.proton.fabriciof807.principal;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.proton.fabriciof807.classes.Menu;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        String key = "b3bf0dd77015232a5cfc761d";
        String pair = "";
        System.out.println(menu);

        int opcao = Integer.valueOf(scanner.nextLine());
        switch (opcao) {
            case 1:
                pair = "USD/ARS";
                break;
            case 2:
                pair = "USD/BRL";
                break;
            case 3:
                pair = "USD/COP";
                break;
            case 4:
                pair = "USD/EUR";
                break;
            case 5:
                pair = "ARS/USD";
                break;
            case 6:
                pair = "BRL/USD";
                break;
            case 7:
                pair = "COP/USD";
                break;
            case 8:
                pair = "EUR/USD";
                break;
            case 9:
                break;
        }

        // URL da API
        String endereco = "https://v6.exchangerate-api.com/v6/" + key + "/pair/" + pair;

        // Client, request e response Http
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        // Conversão para JSON
        JsonElement elemento = JsonParser.parseString(response.body());
        JsonObject objectRoot = elemento.getAsJsonObject();

        // Accessando o JsonObject
        double taxa = objectRoot.get("conversion_rate").getAsDouble();

        System.out.println(taxa);
    }
}
