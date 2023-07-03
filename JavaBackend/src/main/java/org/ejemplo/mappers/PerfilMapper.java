package org.ejemplo.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.ejemplo.modelos.Perfil;
import org.springframework.jdbc.core.RowMapper;

public class PerfilMapper implements RowMapper<Perfil> {
   @Override
    public Perfil mapRow(ResultSet rs, int rowNum) throws SQLException {//Sobrescribimos el método MapRow para crear nuestro objeto a partir del ResultSet
        Perfil perfil = new Perfil();

        perfil.setUser_id(rs.getInt("id"));
        perfil.setNombre(rs.getString("Nombre"));
        perfil.setApellido(rs.getString("Apellidos"));
        perfil.setDNI(rs.getString("DNI"));
        perfil.setFechaNacimiento(rs.getDate("FechaNacimiento"));

        return perfil;
    } 
}

//Objeto utilizado para Mappear una fila de nuestra base de datos a un objeto creado en java (en este caso el objeto es "Producto")
