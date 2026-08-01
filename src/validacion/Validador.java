package validacion;

/**
 * ABSTRACCION: define el contrato para validar formularios.
 * POLIMORFISMO: cada implementacion valida de forma diferente.
 */
public interface Validador {

    /**
     * @return null si la validacion es exitosa, o un mensaje de error.
     */
    String validar();
}
