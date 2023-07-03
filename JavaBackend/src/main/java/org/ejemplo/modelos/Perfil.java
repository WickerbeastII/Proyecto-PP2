package org.ejemplo.modelos;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Perfil {
    private int user_id;
    private String nombre;
    private String apellido;
    private String DNI;
    private Date fechaNacimiento;
}
