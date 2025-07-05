package model;

import observer.Observador;

/**
 * Interface que representa um sensor de lixo.
 * Fornece métodos para consultar o nível, registrar observadores e notificar eventos.
 */
public interface SensorLixo {
    int getNivel();
    void registrarObservador(Observador o);
    void notificar();
}
