package view;

import model.Cotacao;
import java.util.Scanner;

public class CotacaoView {
    private Scanner scanner;
    
    public CotacaoView() {
        this.scanner = new Scanner(System.in);
    }
    
    public String solicitarMoeda() {
        System.out.println("=== CONSULTA DE COTAÇÃO DE MOEDAS ===");
        System.out.println("Moedas disponíveis:");
        System.out.println("USD - Dólar Americano");
        System.out.println("EUR - Euro");
        System.out.println("GBP - Libra Esterlina");
        System.out.println("JPY - Iene Japonês");
        System.out.println("CAD - Dólar Canadense");
        System.out.println("ARS - Peso Argentino");
        System.out.println("BTC - Bitcoin");
        System.out.println("ETH - Ethereum");
        System.out.println("\nDigite o código da moeda (ex: USD, EUR):");
        
        return scanner.nextLine().toUpperCase().trim();
    }
    
    public void exibirCotacao(Cotacao cotacao) {
        if (cotacao != null) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println(cotacao);
            System.out.println("=".repeat(50));
        } else {
            System.out.println("\n Não foi possível obter a cotação. Verifique o código da moeda.");
        }
    }
    
    public void exibirErro(String mensagem) {
        System.out.println(" Erro: " + mensagem);
    }
    
    public boolean perguntarContinuar() {
        System.out.println("\nDeseja consultar outra cotação? (S/N)");
        String resposta = scanner.nextLine().toUpperCase().trim();
        return resposta.equals("S") || resposta.equals("SIM");
    }
    
    public void exibirMensagemDespedida() {
        System.out.println("Obrigado por usar o sistema de cotações!");
    }
    
    public void close() {
        scanner.close();
    }
}