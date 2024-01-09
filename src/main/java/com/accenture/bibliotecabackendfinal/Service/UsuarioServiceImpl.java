package com.accenture.bibliotecabackendfinal.Service;

import com.accenture.bibliotecabackendfinal.Repository.UsuarioRepository;
import com.accenture.bibliotecabackendfinal.models.Usuario;
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
        if(usuarioEncontrado.isPresent()){
            Usuario usuarioExistente = usuarioEncontrado.get();
            usuarioExistente.setNombre(usuario.getNombre());
            usuarioExistente.setDireccion(usuario.getDireccion());
            usuarioExistente.setTelefono(usuario.getTelefono());
            usuarioExistente.setEmail(usuario.getEmail());
            return this.nuevoUsuario(usuarioExistente);
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
