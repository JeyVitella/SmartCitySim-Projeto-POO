package model;

import observer.Observador;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação concreta de SensorLixo com capacidade limitada.
 * Quando o nível atinge o limite, observadores são notificados.
 */
public class SensorLixoCapacidade implements SensorLixo {
    private int nivel;
    private int limite;
    private List<Observador> observadores;

    public SensorLixoCapacidade(int limite) {
        this.limite = limite;
        this.nivel = 0;
        this.observadores = new ArrayList<>();
    }

    @Override
    public int getNivel() {
        return nivel;
    }

    /**
     * Simula um aumento no nível de lixo.
     * Se o nível ultrapassar o limite, dispara notificação.
     */
    public void simularAumento(int quantidade) {
        if (nivel < limite) {
            this.nivel += quantidade;
            if (this.nivel >= limite) {
                this.nivel = limite;
                notificar();
            }
        }
    }

    @Override
    public void registrarObservador(Observador o) {
        observadores.add(o);
    }

    @Override
    public void notificar() {
        for (Observador o : observadores) {
            o.atualizar(this);
        }
    }

    /**
     * Permite redefinir o nível do sensor (usado na restauração de estado).
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
