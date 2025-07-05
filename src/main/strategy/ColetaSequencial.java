package strategy;

import model.SensorLixo;
import model.Rota;
import java.util.List;

/**
 * Implementação da estratégia de coleta sequencial.
 *
 * Essa estratégia segue a ordem original da lista de sensores, ou seja,
 * coleta os resíduos na sequência em que os sensores estão dispostos.
 *
 * É útil para situações em que a prioridade não é definida pela quantidade de lixo,
 * mas sim pela ordem física (geográfica) dos sensores ou pela simplicidade do trajeto.
 */
public class ColetaSequencial implements EstrategiaColeta {

    /**
     * Gera uma rota com base na ordem em que os sensores aparecem.
     *
     * @param sensores Lista de sensores de lixo.
     * @return Uma rota (`Rota`) com os sensores na mesma ordem da lista original.
     *
     * Esta abordagem é direta e não envolve ordenação, sendo útil quando
     * a eficiência do percurso é determinada por outra lógica fora do sistema.
     */
    @Override
    public Rota gerarRota(List<SensorLixo> sensores) {
        return new Rota(sensores);
    }
}
