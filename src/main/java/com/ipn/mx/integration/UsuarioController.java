package com.ipn.mx.integration;

import com.ipn.mx.domain.entity.Usuario;
import com.ipn.mx.service.EmailService;
import com.ipn.mx.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/apiUsuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private EmailService emailService;

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> insertarUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.save(usuario);
        String to = nuevoUsuario.getEmail();
        String subject = "Bienvenido a API inventario mini";
        String text = "¡Gracias por registrarte en nuestra aplicación!";
        emailService.sendSimpleMessage(to, subject, text);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }
}
