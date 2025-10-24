package app;

import controller.CotacaoController;
import model.Cotacao;
import view.CotacaoView;

public class Main {
    public static void main(String[] args) {
        CotacaoView view = new CotacaoView();
        CotacaoController controller = new CotacaoController();
        
        try {
            boolean continuar = true;
            
            while (continuar) {
                String moeda = view.solicitarMoeda();
                
                if (moeda.isEmpty()) {
                    view.exibirErro("Código da moeda não pode estar vazio.");
                    continue;
                }
                
                System.out.println("\n⏳ Consultando cotação...");
                Cotacao cotacao = controller.consultarCotacao(moeda);
                
                view.exibirCotacao(cotacao);
                continuar = view.perguntarContinuar();
            }
            
            view.exibirMensagemDespedida();
            
        } finally {
            view.close();
        }
    }
}