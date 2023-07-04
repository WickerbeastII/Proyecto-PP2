package org.ejemplo.servicios;

import lombok.extern.slf4j.Slf4j;

import org.ejemplo.Exceptions.LoginException;
import org.ejemplo.modelos.Perfil;
import org.ejemplo.modelos.Usuario;
import org.ejemplo.modelos.DTO.Login;
import org.ejemplo.repository.PerfilRepository;
import org.ejemplo.repository.UsuarioRepository;
import org.ejemplo.validations.UserValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UsersService {
    @Autowired //Utilizamos Auytowired para hacer inyeccion de dependencias por medio de springboot
    UsuarioRepository usuarios;

    @Autowired
    PerfilRepository perfiles;

    public List<Usuario> retornarUsuarios(){
        return usuarios.getAll();
    }

    public Usuario login(String user, String pass) throws LoginException {
        Usuario usuario = getUser(user);

        if (usuario == null) {
            throw new LoginException(HttpStatus.UNAUTHORIZED, "Fallo al iniciar sesión", "No existe el usuario especificado");
        }

        if (!usuario.getPassword().equals(pass)) {
            throw new LoginException(HttpStatus.UNAUTHORIZED, "Fallo al iniciar sesión", "Contraseña incorrecta");
        }

        return usuario;
    }

    public Usuario registrar(String nombre, String pass) throws LoginException {
        
        if (nombre == "") {
            throw new LoginException(HttpStatus.UNAUTHORIZED, "Fallo al registrar", "El campo usuario esta vacio");
        }

        if (pass == "") {
            throw new LoginException(HttpStatus.UNAUTHORIZED, "Fallo al registrar", "El campo contraseña esta vacio");
        }
        
        Usuario usuario = getUser(nombre);

        if (usuario != null) {
            throw new LoginException(HttpStatus.UNAUTHORIZED, "Fallo al registrar", "El usuario ya existe");
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUser(nombre);
        nuevoUsuario.setPassword(pass);

        return usuarios.createUsuario(nuevoUsuario);        
    }

    private Usuario getUser(String userName){
        for (Usuario usuario: usuarios.getAll()){
            if (usuario.getUser().equals(userName)){
                return usuario;
            }
        }
        return null;
    }

    private Perfil getPerfil(int userID){
        for (Perfil perfil: perfiles.getAll()){
            if (perfil.getUser_id() == userID) {
                return perfil;
            }
        }
        return null;
    }
}
