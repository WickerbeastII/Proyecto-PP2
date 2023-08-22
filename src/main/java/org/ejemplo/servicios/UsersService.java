package org.ejemplo.servicios;

import lombok.extern.slf4j.Slf4j;

import org.ejemplo.Exceptions.LoginException;
import org.ejemplo.modelos.Perfil;
import org.ejemplo.modelos.Usuario;
import org.ejemplo.repository.PerfilRepository;
import org.ejemplo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@Slf4j
public class UsersService {
    @Autowired // Utilizamos Auytowired para hacer inyeccion de dependencias por medio de
               // springboot
    UsuarioRepository usuarios;

    @Autowired
    PerfilRepository perfiles;

    public List<Usuario> retornarUsuarios() {
        return usuarios.getAll();
    }

    public Usuario login(String user, String pass) throws LoginException {
        Usuario usuario = getUser(user);

        if (usuario == null) {
            throw new LoginException(HttpStatus.UNAUTHORIZED, "Fallo al iniciar sesión",
                    "No existe el usuario especificado");
        }

        if (!usuario.getPassword().equals(pass)) {
            throw new LoginException(HttpStatus.UNAUTHORIZED, "Fallo al iniciar sesión", "Contraseña incorrecta");
        }

        return usuario;
    }

    public Usuario registrar(String tipo, String email, String password, String DNI, String Apellido,
            Date fechaNacimiento)
            throws LoginException {

        if (tipo == "" || tipo == null) {
            throw new LoginException(HttpStatus.UNAUTHORIZED, "Fallo al registrar", "El campo tipo esta vacio");
        }

        if (email == "" || email == null) {
            throw new LoginException(HttpStatus.UNAUTHORIZED, "Fallo al registrar", "El campo email esta vacio");
        }

        if (password == "" || password == null) {
            throw new LoginException(HttpStatus.UNAUTHORIZED, "Fallo al registrar", "El campo contraseña esta vacio");
        }

        if (DNI == "" || DNI == null) {
            throw new LoginException(HttpStatus.UNAUTHORIZED, "Fallo al registrar", "El campo DNI esta vacio");
        }

        if (Apellido == "" || Apellido == null) {
            throw new LoginException(HttpStatus.UNAUTHORIZED, "Fallo al registrar", "El campo Apellido esta vacio");
        }

        if (fechaNacimiento == null) {
            throw new LoginException(HttpStatus.UNAUTHORIZED, "Fallo al registrar",
                    "El campo fechaNacimiento esta vacio");
        }

        Usuario usuario = getUser(email);

        if (usuario != null) {
            throw new LoginException(HttpStatus.UNAUTHORIZED, "Fallo al registrar", "El usuario ya existe");
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setTipo(tipo);
        nuevoUsuario.setUser(email);
        nuevoUsuario.setPassword(password);

        nuevoUsuario = usuarios.createUsuario(nuevoUsuario);

        Perfil nuevoPerfil = new Perfil();
        nuevoPerfil.setUser_id(nuevoUsuario.getId());
        nuevoPerfil.setNombre(email);
        nuevoPerfil.setApellido(Apellido);
        nuevoPerfil.setDNI(DNI);
        nuevoPerfil.setFechaNacimiento(fechaNacimiento);
        perfiles.createPerfil(nuevoPerfil);

        return nuevoUsuario;
    }

    private Usuario getUser(String userName) {
        for (Usuario usuario : usuarios.getAll()) {
            if (usuario.getUser().equals(userName)) {
                return usuario;
            }
        }
        return null;
    }

    private Perfil getPerfil(int userID) {
        for (Perfil perfil : perfiles.getAll()) {
            if (perfil.getUser_id() == userID) {
                return perfil;
            }
        }
        return null;
    }
}
