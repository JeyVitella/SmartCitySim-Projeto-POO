package model;

import java.util.List;

/**
 * Representa uma rota de coleta que contém a ordem de sensores a serem visitados.
 */
public class Rota {
    private List<SensorLixo> pontos;

    /**
     * Cria uma rota a partir de uma lista de sensores.
     */
    public Rota(List<SensorLixo> pontos) {
        this.pontos = pontos;
    }

    public List<SensorLixo> getPontos() {
        return pontos;
    }

    /**
     * Exibe no console os sensores incluídos na rota.
     */
    public void exibirRota() {
        System.out.println("Rota de coleta:");
        for (SensorLixo s : pontos) {
            System.out.println(" - Sensor com nível: " + s.getNivel());
        }
    }
}
