package com.accenture.bibliotecabackendfinal.Repository;

import com.accenture.bibliotecabackendfinal.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public List<Usuario> findAllByNombre(String nombre);
    public Usuario findAllByEmailAndPassword(String email, String password);
}
