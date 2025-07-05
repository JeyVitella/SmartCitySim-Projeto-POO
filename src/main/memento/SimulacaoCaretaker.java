package memento;

import java.util.Stack;

/**
 * Classe responsável por armazenar os estados salvos da simulação.
 * Atua como "caretaker" no padrão de projeto Memento.
 */
public class SimulacaoCaretaker {
    private Stack<SimulacaoMemento> historico = new Stack<>();

    /**
     * Armazena um estado da simulação na pilha de histórico.
     */
    public void salvarEstado(SimulacaoMemento memento) {
        historico.push(memento);
    }

    /**
     * Restaura e retorna o último estado salvo da simulação.
     */
    public SimulacaoMemento desfazer() {
        if (!historico.isEmpty()) {
            return historico.pop();
        }
        return null;
    }
}
