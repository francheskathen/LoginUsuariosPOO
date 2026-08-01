package validacion;

/**
 * Valida los campos del formulario de registro.
 * POLIMORFISMO: implementa Validador.
 */
public class ValidadorRegistro implements Validador {

    private final String nombreUsuario;
    private final String nombre;
    private final String apellido;
    private final String telefono;
    private final String correo;
    private final String contrasena;
    private final String confirmarContrasena;

    public ValidadorRegistro(String nombreUsuario, String nombre, String apellido,
                             String telefono, String correo, String contrasena,
                             String confirmarContrasena) {
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasena = contrasena;
        this.confirmarContrasena = confirmarContrasena;
    }

    @Override
    public String validar() {
        if (esVacio(nombreUsuario)) {
            return "Debe ingresar el campo: Nombre de usuario.";
        }
        if (esVacio(nombre)) {
            return "Debe ingresar el campo: Nombre.";
        }
        if (esVacio(apellido)) {
            return "Debe ingresar el campo: Apellido.";
        }
        if (esVacio(telefono)) {
            return "Debe ingresar el campo: Número de teléfono.";
        }
        if (esVacio(correo)) {
            return "Debe ingresar el campo: Correo electrónico.";
        }
        if (esVacio(contrasena)) {
            return "Debe ingresar el campo: Contraseña.";
        }
        if (esVacio(confirmarContrasena)) {
            return "Debe ingresar el campo: Confirmar contraseña.";
        }
        if (!contrasena.equals(confirmarContrasena)) {
            return "La contraseña y la confirmación de la contraseña no coinciden.";
        }
        return null;
    }

    private boolean esVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }
}
