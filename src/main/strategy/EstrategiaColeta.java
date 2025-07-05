package strategy;

import model.SensorLixo;
import model.Rota;
import java.util.List;

/**
 * Interface que representa uma estratégia de geração de rotas para coleta de lixo.
 *
 * Este é o ponto central do padrão Strategy. Ele define o contrato que todas
 * as estratégias concretas (como `ColetaPorNivelMaisAlto` ou `ColetaSequencial`)
 * devem seguir para serem utilizadas pelo sistema de coleta.
 *
 * O uso dessa interface permite que o algoritmo de roteamento seja alterado
 * dinamicamente durante a execução, promovendo flexibilidade.
 */
public interface EstrategiaColeta {

    /**
     * Gera uma rota baseada na lista de sensores fornecida.
     *
     * @param sensores Lista de sensores de lixo a serem avaliados.
     * @return Uma instância de `Rota` definindo a sequência de coleta.
     *
     * Implementações podem definir critérios diferentes para determinar a ordem:
     * nível de lixo, proximidade geográfica, prioridade do bairro, etc.
     */
    Rota gerarRota(List<SensorLixo> sensores);
}
