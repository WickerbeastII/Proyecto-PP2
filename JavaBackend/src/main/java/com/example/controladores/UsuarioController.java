package com.example.controladores;

import com.example.modelos.Login;
import com.example.modelos.Usuario;
import com.example.servicios.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController {
    public UsersService service = new UsersService();

    @PostMapping("/registry")
    public ResponseEntity<String> createUser(@RequestBody Usuario usuario){
        String respuesta = service.guardarUsuario(usuario);
        if (respuesta.contains("error")){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(respuesta);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Usuario>> getAll(){
        return ResponseEntity.ok(service.retornarUsuarios());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login) {
        String respuesta = service.login(login);
        if (respuesta.contains("Error")){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(respuesta);
        }
        return ResponseEntity.ok(respuesta);
    }
}
