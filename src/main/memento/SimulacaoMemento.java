package memento;

import model.SensorLixo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Armazena o estado dos sensores em um dado momento da simulação.
 * Representa o "memento" no padrão Memento.
 */
public class SimulacaoMemento {
    private Map<Integer, Integer> estadoSensores;
    private List<SensorLixo> ordemSensores;

    public SimulacaoMemento(Map<Integer, Integer> estadoSensores, List<SensorLixo> ordemSensores) {
        this.estadoSensores = new HashMap<>(estadoSensores);
        this.ordemSensores = List.copyOf(ordemSensores);
    }

    public Map<Integer, Integer> getEstadoSensores() {
        return new HashMap<>(estadoSensores);
    }

    public List<SensorLixo> getOrdemSensores() {
        return List.copyOf(ordemSensores);
    }
}
