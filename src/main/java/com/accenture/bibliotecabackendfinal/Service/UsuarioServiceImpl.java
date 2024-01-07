package com.accenture.bibliotecabackendfinal.Service;

import com.accenture.bibliotecabackendfinal.Repository.UsuarioRepository;
import com.accenture.bibliotecabackendfinal.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }
    @Override
    public Usuario nuevoUsuario(Usuario nuevoUsuario) {
        return usuarioRepository.save(nuevoUsuario);
    }

    @Override
    public Iterable<Usuario> getAll() {
        return this.usuarioRepository.findAll();
    }

    @Override
    public Usuario usuarioModificado(Usuario usuario) {
        Optional<Usuario> usuarioEncontrado= this.usuarioRepository.findById(usuario.getId());
        if(usuarioEncontrado.get() != null){
            usuarioEncontrado.get().setNombre(usuario.getNombre());
            usuarioEncontrado.get().setDireccion(usuario.getDireccion());
            usuarioEncontrado.get().setTelefono(usuario.getTelefono());
            usuarioEncontrado.get().setEmail(usuario.getEmail());
            return this.nuevoUsuario(usuarioEncontrado.get());
        }
        return null;
    }

    @Override
    public Boolean usuarioBorrado(Long id) {
        this.usuarioRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Usuario> obtenerPorNombre(String nombre) {
        return usuarioRepository.findAllByNombre(nombre);
    }

    @Override
    public Usuario obtenerPorEmailYPassword(String email, String password) {
        return usuarioRepository.findAllByEmailAndPassword(email, password);
    }


}
