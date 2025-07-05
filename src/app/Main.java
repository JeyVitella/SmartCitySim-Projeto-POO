package app;

import factory.*;
import model.*;
import strategy.*;

import java.util.Arrays;
import java.util.List;

/**
 * Classe principal que executa a simulação do sistema de coleta de lixo.
 * Aqui são criados sensores e caminhões, escolhida uma estratégia de coleta,
 * e o sistema simula aumento de lixo e a geração de uma rota.
 */
public class Main {
    public static void main(String[] args) {
        // Cria uma fábrica de sensores com limite de 10
        FabricaSensorCapacidade fabricaSensor = new FabricaSensorCapacidade(10);

        // Instancia três sensores
        SensorLixo s1 = fabricaSensor.criar();
        SensorLixo s2 = fabricaSensor.criar();
        SensorLixo s3 = fabricaSensor.criar();

        List<SensorLixo> sensores = Arrays.asList(s1, s2, s3);

        // Cria caminhões de coleta
        FabricaCaminhaoSimples fabricaCaminhao = new FabricaCaminhaoSimples();
        CaminhaoColeta caminhao = fabricaCaminhao.criar();

        List<CaminhaoColeta> caminhoes = Arrays.asList(caminhao);

        // Define a estratégia de coleta como "nível mais alto"
        EstrategiaColeta estrategia = new ColetaPorNivelMaisAlto();

        // Cria o sistema principal e associa sensores, caminhões e estratégia
        SistemaColetaLixo sistema = new SistemaColetaLixo(sensores, caminhoes, estrategia);

        // Simula aumento de lixo
        System.out.println("Simulando sensores...");
        ((SensorLixoCapacidade) s1).simularAumento(3);
        ((SensorLixoCapacidade) s2).simularAumento(6);
        ((SensorLixoCapacidade) s3).simularAumento(12); // Este ultrapassa o limite

        // Salva o estado atual da simulação
        sistema.salvarEstado();

        // Gera e exibe a rota
        System.out.println("\nRota gerada:");
        Rota rota = estrategia.gerarRota(sensores);
        rota.exibirRota();
    }
}
