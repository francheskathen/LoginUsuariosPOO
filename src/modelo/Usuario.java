package modelo;

import java.io.Serializable;

/**
 * Representa un usuario registrado en el sistema.
 * ENCAPSULAMIENTO: atributos privados con acceso controlado.
 */
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombreUsuario;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String contrasena;

    public Usuario(String nombreUsuario, String nombre, String apellido,
                   String telefono, String correo, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    @Override
    public String toString() {
        return getNombreCompleto() + " (" + nombreUsuario + ")";
    }
}
