package controller;

import model.Cotacao;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;

public class CotacaoController {
    private static final String API_URL = "https://economia.awesomeapi.com.br/json/last/";
    
    public Cotacao consultarCotacao(String moeda) {
        try {
            // Formatar a moeda para o padrão da API (ex: USD-BRL)
            String moedaFormatada = moeda + "-BRL";
            String urlString = API_URL + moedaFormatada;
            
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            
            int responseCode = connection.getResponseCode();
            
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
                );
                
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                
                return parseJsonResponse(response.toString(), moeda);
            } else {
                System.out.println("Erro na requisição: " + responseCode);
                return null;
            }
            
        } catch (Exception e) {
            System.out.println("Erro ao consultar cotação: " + e.getMessage());
            return null;
        }
    }
    
    private Cotacao parseJsonResponse(String jsonResponse, String moeda) {
        try {
            // Parse manual do JSON sem bibliotecas externas
            String chave = "\"" + moeda + "BRL\"";
            int startIndex = jsonResponse.indexOf(chave);
            
            if (startIndex == -1) {
                return null;
            }
            
            // Encontrar o objeto JSON da cotação
            int objectStart = jsonResponse.indexOf("{", startIndex);
            int objectEnd = findMatchingBracket(jsonResponse, objectStart);
            
            if (objectStart == -1 || objectEnd == -1) {
                return null;
            }
            
            String cotacaoJson = jsonResponse.substring(objectStart, objectEnd + 1);
            
            // Extrair campos manualmente
            String codigo = extractValue(cotacaoJson, "code");
            double valorCompra = Double.parseDouble(extractValue(cotacaoJson, "bid"));
            double valorVenda = Double.parseDouble(extractValue(cotacaoJson, "ask"));
            double variacao = Double.parseDouble(extractValue(cotacaoJson, "pctChange"));
            
            // Converter timestamp para LocalDateTime
            String timestamp = extractValue(cotacaoJson, "timestamp");
            LocalDateTime dataHora = LocalDateTime.ofEpochSecond(
                Long.parseLong(timestamp), 0, java.time.ZoneOffset.UTC
            );
            
            return new Cotacao(codigo, valorCompra, valorVenda, variacao, dataHora);
            
        } catch (Exception e) {
            System.out.println("Erro ao processar resposta JSON: " + e.getMessage());
            return null;
        }
    }
    
    private String extractValue(String json, String key) {
        String searchKey = "\"" + key + "\":";
        int keyIndex = json.indexOf(searchKey);
        if (keyIndex == -1) return "";
        
        int valueStart = keyIndex + searchKey.length();
        int valueEnd = json.indexOf(",", valueStart);
        int valueEnd2 = json.indexOf("}", valueStart);
        
        if (valueEnd == -1 || (valueEnd2 != -1 && valueEnd2 < valueEnd)) {
            valueEnd = valueEnd2;
        }
        
        if (valueEnd == -1) return "";
        
        String value = json.substring(valueStart, valueEnd).trim();
        
        // Remover aspas se for string
        if (value.startsWith("\"") && value.endsWith("\"")) {
            value = value.substring(1, value.length() - 1);
        }
        
        return value;
    }
    
    private int findMatchingBracket(String text, int startIndex) {
        int count = 1;
        for (int i = startIndex + 1; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '{') count++;
            else if (c == '}') count--;
            
            if (count == 0) return i;
        }
        return -1;
    }
}