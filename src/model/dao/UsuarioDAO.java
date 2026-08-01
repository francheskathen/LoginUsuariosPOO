package dao;

import java.util.ArrayList;
import model.Usuario;

public class UsuarioDAO {

    private ArrayList<Usuario> listaUsuarios;

    public UsuarioDAO() {

        listaUsuarios = new ArrayList<>();

    }

    public void agregarUsuario(Usuario usuario){

        listaUsuarios.add(usuario);

    }

    public ArrayList<Usuario> obtenerUsuarios(){

        return listaUsuarios;

    }

}