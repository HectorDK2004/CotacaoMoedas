package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cotacao {
    private String codigoMoeda;
    private double valorCompra;
    private double valorVenda;
    private double variacao;
    private LocalDateTime dataHora;
    
    public Cotacao(String codigoMoeda, double valorCompra, double valorVenda, double variacao, LocalDateTime dataHora) {
        this.codigoMoeda = codigoMoeda;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.variacao = variacao;
        this.dataHora = dataHora;
    }
    
    // Getters e Setters
    public String getCodigoMoeda() {
        return codigoMoeda;
    }
    
    public void setCodigoMoeda(String codigoMoeda) {
        this.codigoMoeda = codigoMoeda;
    }
    
    public double getValorCompra() {
        return valorCompra;
    }
    
    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }
    
    public double getValorVenda() {
        return valorVenda;
    }
    
    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }
    
    public double getVariacao() {
        return variacao;
    }
    
    public void setVariacao(double variacao) {
        this.variacao = variacao;
    }
    
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format(
            "Cotação %s:\n" +
            "Compra: R$ %.4f\n" +
            "Venda: R$ %.4f\n" +
            "Variação: %.2f%%\n" +
            "Data/Hora: %s",
            codigoMoeda, valorCompra, valorVenda, variacao, 
            dataHora.format(formatter)
        );
    }
}