package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Gestiona la coleccion de usuarios registrados.
 * ENCAPSULAMIENTO: la lista interna no es accesible directamente.
 */
public class GestorUsuarios {

    private static GestorUsuarios instancia;
    private final List<Usuario> usuarios;
    private static final String ARCHIVO = "usuarios.dat";

    private GestorUsuarios() {
        usuarios = new ArrayList<Usuario>();
        cargarUsuarios();
    }

    public static GestorUsuarios getInstancia() {
        if (instancia == null) {
            instancia = new GestorUsuarios();
        }
        return instancia;
    }

    public boolean registrar(Usuario usuario) {
        if (buscarPorNombreUsuario(usuario.getNombreUsuario()).isPresent()) {
            return false;
        }
        usuarios.add(usuario);
        guardarUsuarios();
        return true;
    }

    public Optional<Usuario> autenticar(String nombreUsuario, String contrasena) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)
                    && usuario.getContrasena().equals(contrasena)) {
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }

    public Optional<Usuario> buscarPorNombreUsuario(String nombreUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }

    public List<Usuario> obtenerTodos() {
        return new ArrayList<Usuario>(usuarios);
    }

    public boolean actualizar(String nombreUsuarioOriginal, Usuario usuarioActualizado) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getNombreUsuario().equals(nombreUsuarioOriginal)) {
                if (!nombreUsuarioOriginal.equals(usuarioActualizado.getNombreUsuario())
                        && buscarPorNombreUsuario(usuarioActualizado.getNombreUsuario()).isPresent()) {
                    return false;
                }
                usuarios.set(i, usuarioActualizado);
                guardarUsuarios();
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(String nombreUsuario) {
        boolean eliminado = false;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getNombreUsuario().equals(nombreUsuario)) {
                usuarios.remove(i);
                eliminado = true;
                break;
            }
        }
        if (eliminado) {
            guardarUsuarios();
        }
        return eliminado;
    }

    @SuppressWarnings("unchecked")
    private void cargarUsuarios() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return;
        }
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(archivo));
            List<Usuario> cargados = (List<Usuario>) ois.readObject();
            usuarios.addAll(cargados);
        } catch (IOException e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar archivo: " + e.getMessage());
                }
            }
        }
    }

    private void guardarUsuarios() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO));
            oos.writeObject(usuarios);
        } catch (IOException e) {
            System.err.println("Error al guardar usuarios: " + e.getMessage());
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar archivo: " + e.getMessage());
                }
            }
        }
    }
}
