public class Usuario extends Persona {

    private String usuario;
    private String password;

    public Usuario(String usuario,
                   String password,
                   String nombre,
                   String apellido,
                   String telefono,
                   String correo) {

        super(nombre, apellido, telefono, correo);

        this.usuario = usuario;
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return usuario;
    }

}