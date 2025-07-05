package factory;

/**
 * Interface genérica para fábricas de elementos da cidade.
 * Define o método `criar` para ser implementado por fábricas específicas.
 * @param <T> Tipo de elemento a ser criado.
 */
public interface FabricaElementoCidade<T> {
    T criar();
}
