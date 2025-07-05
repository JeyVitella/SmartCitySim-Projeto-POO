package strategy;

import model.SensorLixo;
import model.Rota;
import java.util.*;

/**
 * Implementação da estratégia de coleta que prioriza sensores com maior nível de lixo.
 *
 * Esta classe segue o padrão Strategy, permitindo que diferentes algoritmos de roteamento
 * possam ser utilizados pelo sistema de coleta sem alterar sua estrutura principal.
 *
 * A estratégia "ColetaPorNivelMaisAlto" classifica os sensores em ordem decrescente
 * de nível de lixo e define essa como a ordem da rota.
 *
 * Útil para cenários em que a prioridade é esvaziar as lixeiras mais cheias primeiro,
 * evitando transbordamentos.
 */
public class ColetaPorNivelMaisAlto implements EstrategiaColeta {

    /**
     * Gera uma rota ordenada de sensores, com base no nível de lixo (maior primeiro).
     *
     * @param sensores Lista dos sensores atualmente em operação.
     * @return Uma rota (`Rota`) contendo os sensores ordenados do mais cheio ao menos cheio.
     *
     * O método primeiro cria uma cópia da lista original para preservar a integridade dos dados,
     * depois usa um comparador que classifica os sensores de forma decrescente com base no valor
     * retornado por `getNivel()`.
     */
    @Override
    public Rota gerarRota(List<SensorLixo> sensores) {
        List<SensorLixo> ordenados = new ArrayList<>(sensores);
        ordenados.sort(Comparator.comparingInt(SensorLixo::getNivel).reversed());
        return new Rota(ordenados);
    }
}
