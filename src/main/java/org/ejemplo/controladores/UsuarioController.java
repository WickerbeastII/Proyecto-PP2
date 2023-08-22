package org.ejemplo.controladores;

import org.ejemplo.Exceptions.LoginException;
import org.ejemplo.modelos.Usuario;
import org.ejemplo.modelos.DTO.Login;
import org.ejemplo.modelos.DTO.Registro;
import org.ejemplo.servicios.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UsuarioController {
    @Autowired
    public UsersService service;

    @PostMapping(value = "/login", consumes = { "application/json" })
    public ResponseEntity<String> login(@RequestBody Login loginData) {
        try {
            Usuario logedUser = service.login(loginData.getUser(), loginData.getPassword());
            return ResponseEntity.status(HttpStatus.OK)
                    .body("El usuario " + logedUser.getUser() + " ha iniciado correctamente sesión");
        } catch (LoginException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(String.format("%s \n %s", e.getMessage(), e.getCausa()));
        } catch (Exception e) {
            log.error("Error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ups!!! Algo salio mal, nuestro desarrolladores estan trabajando para solucionarlo");
        }
    }

    @PostMapping(value = "/registrar", consumes = { "application/json" })
    public ResponseEntity<String> Registrar(@RequestBody Registro registerData) {
        try {
            Usuario logedUser = service.registrar(registerData.getTipo(), registerData.getEmail(), registerData.getPassword(),
                    registerData.getDni(), registerData.getApellido(), registerData.getFechaNacimiento());

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("El usuario " + logedUser.getUser() + " se ha creado correctamente");
        } catch (LoginException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(String.format("%s \n %s", e.getMessage(), e.getCausa()));
        } catch (Exception e) {
            log.error("Error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ups!!! Algo salio mal, nuestro desarrolladores estan trabajando para solucionarlo");
        }
    }
}
