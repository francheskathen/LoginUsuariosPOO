package dao;

import java.util.ArrayList;

public class UsuarioDAO {

    private ArrayList<Usuario> listaUsuarios;

    public UsuarioDAO() {

        listaUsuarios = new ArrayList<>();

    }

    public void agregarUsuario(Usuario subarid){

        dao.Usuario usuario = null;
        listaUsuarios.add(usuario);

    }

    public ArrayList<Usuario> obtenerUsuarios(){

        return listaUsuarios;

    }

}