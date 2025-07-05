package factory;

import model.SensorLixo;
import model.SensorLixoCapacidade;

/**
 * Fábrica responsável por criar sensores de lixo com limite de capacidade.
 * Utiliza o padrão Factory Method para encapsular a criação.
 */
public class FabricaSensorCapacidade implements FabricaElementoCidade<SensorLixo> {
    private int limite;

    /**
     * Construtor que define o limite de capacidade dos sensores criados.
     * @param limite capacidade máxima antes de disparar notificação.
     */
    public FabricaSensorCapacidade(int limite) {
        this.limite = limite;
    }

    /**
     * Cria e retorna uma nova instância de SensorLixoCapacidade com o limite configurado.
     */
    @Override
    public SensorLixo criar() {
        return new SensorLixoCapacidade(limite);
    }
}
