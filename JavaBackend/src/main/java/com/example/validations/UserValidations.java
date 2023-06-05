package com.example.validations;

import com.example.modelos.Usuario;

import java.util.List;

public class UserValidations {
    public static Boolean validateExistUser(List<Usuario> usuarios, String username){
        for(Usuario user: usuarios){
            if (user.getUser().equals(username)){
                return true;
            }
        }
        return false;
    }
}
