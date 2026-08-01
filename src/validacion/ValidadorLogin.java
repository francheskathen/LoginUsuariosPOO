package validacion;

//Valida los campos del formulario de inicio de sesion de usuarios registrados.
public class ValidadorLogin implements Validador {

    private final String usuario;
    private final String contrasena;

    public ValidadorLogin(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    @Override
    public String validar() {
        if (usuario == null || usuario.trim().isEmpty()
                || contrasena == null || contrasena.trim().isEmpty()) {
            return "Debe ingresar su usuario y contraseña, si no está registrado debe registrarse.";
        }
        return null;
    }
}
