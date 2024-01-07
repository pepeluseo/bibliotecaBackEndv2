package com.accenture.bibliotecabackendfinal.Service;

import com.accenture.bibliotecabackendfinal.models.Usuario;

import java.util.List;

public interface UsuarioService {
    public Usuario nuevoUsuario (Usuario nuevoUsuario);
    public Iterable<Usuario> getAll();
    public Usuario usuarioModificado(Usuario usuario);
    public Boolean usuarioBorrado(Long id);
    public List<Usuario> obtenerPorNombre(String nombre);
    public Usuario obtenerPorEmailYPassword(String email, String password);
}
