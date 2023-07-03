package org.ejemplo.repository;

import java.util.List;

import javax.sql.DataSource;

import org.ejemplo.mappers.PerfilMapper;
import org.ejemplo.modelos.Perfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Repository
public class PerfilRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PerfilRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);// inicializamos el template con el objeto que contiene la
                                                    // configuración necesaria para conectarnos a nuestra base de datos
    }

    // metodo para obtener todos los usuarios
    public List<Perfil> getAll() {
        String sql = "SELECT * FROM perfiles;";
        return jdbcTemplate.query(sql, new PerfilMapper());
    }

    public Perfil createProducto(Perfil perfil) {
        String sql = "INSERT INTO perfiles (id, Nombre, Apelido, DNI, FechaNacimiento) VALUES (?,?,?,?,?);";

        jdbcTemplate.update(sql, perfil.getUser_id(), perfil.getNombre(), perfil.getApellido(), perfil.getDNI(), perfil.getFechaNacimiento());
        return perfil;
    }

    public Perfil updateProducto(Perfil perfil) {
        String sql = "UPDATE perfiles SET Nombre = ?, Descripcion = ?, Fecha = ?, Stock = ?, Precio = ? WHERE Codigo = ?";

        jdbcTemplate.update(sql, perfil.getNombre(), perfil.getApellido(), perfil.getDNI(), perfil.getFechaNacimiento(), perfil.getUser_id());
        return perfil;
    }

    public void removeProducto(int id) throws Exception {
        log.info("Eliminiando el producto {}", id);
        String sql = "DELETE FROM perfiles WHERE Codigo = ?";

        int rf = jdbcTemplate.update(sql, id);
        if (rf > 0) {
            log.info("producto borrado correctamente");
        } else {
            throw new Exception("Error al intentar borrar el producto");
        }
    }
}
