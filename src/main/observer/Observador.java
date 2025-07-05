package observer;

import model.SensorLixo;

/**
 * Interface do padrão Observer (Observador).
 *
 * Esta interface define um contrato para que objetos possam "observar" sensores de lixo
 * e reagir a mudanças em seu estado — por exemplo, quando uma lixeira atingir seu limite.
 *
 * O objetivo principal é permitir que componentes do sistema (como o sistema de coleta)
 * sejam automaticamente notificados quando um sensor detectar que a lixeira está cheia,
 * promovendo uma arquitetura desacoplada e reativa.
 *
 * Este padrão favorece a extensibilidade do sistema, pois novos observadores
 * podem ser adicionados sem alterar os sensores.
 */
public interface Observador {

    /**
     * Método que será chamado sempre que o sensor notificar uma mudança importante.
     *
     * @param sensor O sensor de lixo que disparou a notificação.
     *               Geralmente é passado para que o observador possa acessar seus dados.
     *
     * Implementações desse método devem definir o que fazer quando o sensor
     * atingir o limite de capacidade, como logar, exibir alertas ou iniciar coleta.
     */
    void atualizar(SensorLixo sensor);
}
