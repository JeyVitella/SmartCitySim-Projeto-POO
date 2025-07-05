// =====================
// Arquivo: model/SistemaColetaLixo.java
// =====================
package model;

import observer.Observador;
import strategy.EstrategiaColeta;
import memento.*;
import java.util.*;

/**
 * Classe responsável por gerenciar o sistema de coleta de lixo.
 * Atua como ponto central de controle entre sensores, caminhões e a estratégia de coleta.
 * Também implementa o padrão Observer para reagir a sensores cheios e utiliza Memento para salvar e restaurar estados.
 */
public class SistemaColetaLixo implements Observador {
    private List<SensorLixo> sensores; // Lista dos sensores de lixo na cidade
    private List<CaminhaoColeta> caminhoes; // Lista de caminhões de coleta
    private EstrategiaColeta estrategia; // Estratégia atual utilizada para definir a rota
    private SimulacaoCaretaker caretaker = new SimulacaoCaretaker(); // Responsável por armazenar os estados anteriores
    private int proximoIdSensor = 0; // Usado para gerar IDs únicos para os sensores
    private Map<SensorLixo, Integer> idsSensores = new HashMap<>(); // Mapeia sensores para seus respectivos IDs

    /**
     * Construtor principal que inicializa o sistema com os sensores, caminhões e estratégia inicial.
     * Também registra o sistema como observador dos sensores.
     */
    public SistemaColetaLixo(List<SensorLixo> sensores, List<CaminhaoColeta> caminhoes, EstrategiaColeta estrategia) {
        this.sensores = sensores;
        this.caminhoes = caminhoes;
        this.estrategia = estrategia;

        for (SensorLixo sensor : sensores) {
            sensor.registrarObservador(this);
            idsSensores.put(sensor, proximoIdSensor++);
        }
    }

    /**
     * Método chamado automaticamente quando um sensor notifica uma alteração (ex: atingiu o limite).
     * Implementação do padrão Observer.
     */
    @Override
    public void atualizar(SensorLixo sensor) {
        System.out.println("Sensor atingiu o limite: " + sensor.getNivel());
    }

    /**
     * Salva o estado atual do sistema: nível de cada sensor e sua ordem.
     * Isso permite restaurar o sistema ao mesmo estado posteriormente (padrão Memento).
     */
    public void salvarEstado() {
        Map<Integer, Integer> estadoAtual = new HashMap<>();
        for (SensorLixo sensor : sensores) {
            int id = idsSensores.get(sensor);
            estadoAtual.put(id, sensor.getNivel());
        }
        SimulacaoMemento memento = new SimulacaoMemento(estadoAtual, sensores);
        caretaker.salvarEstado(memento);
        System.out.println("Estado salvo.");
    }

    /**
     * Restaura o estado anterior do sistema, desfazendo alterações no nível dos sensores
     * e restaurando a ordem original dos sensores.
     */
    public void restaurarEstado() {
        SimulacaoMemento memento = caretaker.desfazer();
        if (memento == null) {
            System.out.println("Nenhum estado anterior disponível.");
            return;
        }

        Map<Integer, Integer> estadoAnterior = memento.getEstadoSensores();
        List<SensorLixo> ordemAnterior = memento.getOrdemSensores();

        for (SensorLixo sensor : ordemAnterior) {
            int id = idsSensores.get(sensor);
            if (sensor instanceof SensorLixoCapacidade && estadoAnterior.containsKey(id)) {
                ((SensorLixoCapacidade) sensor).setNivel(estadoAnterior.get(id));
            }
        }
        this.sensores = new ArrayList<>(ordemAnterior);
        System.out.println("Estado restaurado.");
    }

    /**
     * Getter para obter os sensores do sistema. Utilizado na interface gráfica.
     */
    public List<SensorLixo> getSensores() {
        return sensores;
    }

    /**
     * Getter para a estratégia atual de coleta de lixo.
     */
    public EstrategiaColeta getEstrategia() {
        return estrategia;
    }

    /**
     * Setter para alterar a estratégia de coleta de lixo (por exemplo, mudar de coleta sequencial para por nível).
     */
    public void setEstrategia(EstrategiaColeta estrategia) {
        this.estrategia = estrategia;
    }

    /**
     * Executa a coleta de lixo conforme a rota gerada pela estratégia atual.
     * Os sensores são esvaziados na ordem definida pela rota.
     */
    public void coletarLixo() {
        Rota rota = estrategia.gerarRota(sensores);
        for (SensorLixo sensor : rota.getPontos()) {
            if (sensor instanceof SensorLixoCapacidade) {
                ((SensorLixoCapacidade) sensor).setNivel(0);
            }
        }
        System.out.println("Coleta realizada conforme a rota.");
    }
}
