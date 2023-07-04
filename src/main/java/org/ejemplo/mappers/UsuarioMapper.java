package org.ejemplo.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.ejemplo.modelos.Usuario;
import org.springframework.jdbc.core.RowMapper;

public class UsuarioMapper implements RowMapper<Usuario> {
   @Override
    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {//Sobrescribimos el método MapRow para crear nuestro objeto a partir del ResultSet
        Usuario usuario = new Usuario();

        usuario.setId(rs.getInt("id"));
        usuario.setUser(rs.getString("User"));
        usuario.setPassword(rs.getString("Password"));
        

        return usuario;
    } 
}
