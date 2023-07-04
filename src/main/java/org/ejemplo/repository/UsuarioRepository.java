package org.ejemplo.repository;

import java.util.List;

import javax.sql.DataSource;

import org.ejemplo.mappers.UsuarioMapper;
import org.ejemplo.modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Repository
public class UsuarioRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UsuarioRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);// inicializamos el template con el objeto que contiene la
                                                    // configuración necesaria para conectarnos a nuestra base de datos
    }

    // metodo para obtener todos los usuarios
    public List<Usuario> getAll() {
        String sql = "SELECT * FROM usuarios;";
        return jdbcTemplate.query(sql, new UsuarioMapper());
    }

    public Usuario createUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (user, pass) VALUES (?,?);";

        jdbcTemplate.update(sql, usuario.getUser(), usuario.getPassword());
        return usuario;
    }

    public Usuario updateUsuario(Usuario usuario) {
        String sql = "UPDATE usuarios SET user = ?, pass = ? WHERE id = ?";

        jdbcTemplate.update(sql, usuario.getId(), usuario.getUser(), usuario.getPassword());
        return usuario;
    }

    public void removeUsuario(int id) throws Exception {
        log.info("Eliminiando el Usuario {}", id);
        String sql = "DELETE FROM usuarios WHERE id = ?";

        int rf = jdbcTemplate.update(sql, id);
        if (rf > 0) {
            log.info("Usuario borrado correctamente");
        } else {
            throw new Exception("Error al intentar borrar el Usuario");
        }
    }
}
