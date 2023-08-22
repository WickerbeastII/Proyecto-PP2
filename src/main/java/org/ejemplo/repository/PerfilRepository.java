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

    public Perfil createPerfil(Perfil perfil) {
        String sql = "INSERT INTO perfiles (Nombre, Apellidos, DNI, FechaNacimiento) VALUES (?,?,?,?);";

        jdbcTemplate.update(sql, perfil.getNombre(), perfil.getApellido(), perfil.getDNI(), perfil.getFechaNacimiento());
        return perfil;
    }

    public Perfil updatePerfil(Perfil perfil) {
        String sql = "UPDATE perfiles SET Nombre = ?, Apellidos = ?, DNI = ?, FechaNacimiento = ? WHERE id = ?";

        jdbcTemplate.update(sql, perfil.getNombre(), perfil.getApellido(), perfil.getDNI(), perfil.getFechaNacimiento(), perfil.getUser_id());
        return perfil;
    }

    public void removePerfil(int id) throws Exception {
        log.info("Eliminiando el producto {}", id);
        String sql = "DELETE FROM perfiles WHERE id = ?";

        int rf = jdbcTemplate.update(sql, id);
        if (rf > 0) {
            log.info("producto borrado correctamente");
        } else {
            throw new Exception("Error al intentar borrar el producto");
        }
    }
}
