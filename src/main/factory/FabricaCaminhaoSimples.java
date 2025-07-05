package factory;

import model.CaminhaoColeta;

/**
 * Fábrica concreta responsável por criar caminhões de coleta simples.
 * Implementa o padrão Factory Method para instanciar objetos do tipo CaminhaoColeta.
 */
public class FabricaCaminhaoSimples implements FabricaElementoCidade<CaminhaoColeta> {

    /**
     * Cria e retorna uma nova instância de CaminhaoColeta.
     * @return instância de CaminhaoColeta.
     */
    @Override
    public CaminhaoColeta criar() {
        return new CaminhaoColeta();
    }
}
