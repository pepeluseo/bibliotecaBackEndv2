package com.accenture.bibliotecabackendfinal.Controller;

import com.accenture.bibliotecabackendfinal.Repository.UsuarioRepository;
import com.accenture.bibliotecabackendfinal.models.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class HomeController {
    private final UsuarioRepository usuarioRepository;

    public HomeController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @RequestMapping("/")
    public String index(Model model){
        List<Usuario> lista = usuarioRepository.findAll();
        model.addAttribute("lista", lista);
        return "index";
    }


}
