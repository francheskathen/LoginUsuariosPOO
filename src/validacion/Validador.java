package validacion;

public interface Validador {

    /**
     * @return null si la validacion es exitosa, o un mensaje de error.
     */
    String validar();
}
