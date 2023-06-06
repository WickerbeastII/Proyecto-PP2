package org.ejemplo.servicios;

import lombok.extern.slf4j.Slf4j;
import org.ejemplo.modelos.LoginDTO;
import org.ejemplo.modelos.Perfil;
import org.ejemplo.modelos.Usuario;
import org.ejemplo.validations.UserValidations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UsersService {
    List<Usuario> usuarios = new ArrayList<>();
    List<Perfil> perfiles = new ArrayList<>();

    public List<Usuario> retornarUsuarios(){
        return usuarios;
    }

    public Perfil login(String user, String pass){
        Usuario usuario = getUser(user);

        if (usuario != null) {
            throw new Error("No se ha econtrado el usuario");
        }

        if (!usuario.getPassword().equals(pass)) {
            throw new Error("La contraseña no es correcta");
        }

        Perfil perfil = getPerfil(usuario.getId());

        if (perfil != null) {
            return perfil;
        }

        throw new Error("Perfil no encontrado");
    }


    private Usuario getUser(String userName){
        for (Usuario usuario: usuarios){
            if (usuario.getUser().equals(userName)){
                return usuario;
            }
        }
        return null;
    }

    private Perfil getPerfil(String userID){
        for (Perfil perfil: perfiles){
            if (perfil.getUser_id().equals(userID)) {
                return perfil;
            }
        }
        return null;
    }
}
