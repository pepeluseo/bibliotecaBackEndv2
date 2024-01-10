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
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(nuevoUsuario.getId());

        if (usuarioExistente.isPresent()) {
            // La ID del nuevo usuario ya existe, no se puede crear un usuario con una ID existente.
            // Puedes lanzar una excepción, devolver null u otra lógica según tus necesidades.
            // En este caso, se devuelve null, pero considera una alternativa más robusta.
            return null;
        }

        // Si llegamos aquí, significa que la ID del nuevo usuario es única y podemos guardarlo.
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
            if (!usuarioExistente.getId().equals(usuario.getId())) {
                // El ID del usuario que estás tratando de modificar no coincide con el ID de otro usuario existente.
                // Puedes lanzar una excepción, devolver un usuario por defecto u otra lógica según tus necesidades.
                // En este caso, se devuelve null, pero considera una alternativa más robusta.
                return null;
            }

            usuarioExistente.setNombre(usuario.getNombre());
            usuarioExistente.setDireccion(usuario.getDireccion());
            usuarioExistente.setTelefono(usuario.getTelefono());
            usuarioExistente.setEmail(usuario.getEmail());
            return this.usuarioRepository.save(usuarioExistente);
        }
        return null;
    }

    @Override
    public Boolean usuarioBorrado(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            usuarioRepository.deleteById(id);
            return true;
        } else {
            return false; // Usuario no encontrado, no se realiza la eliminación.
        }
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
