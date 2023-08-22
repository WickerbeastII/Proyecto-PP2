package org.ejemplo.modelos.DTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// DTO, siguifica Data Transer Object, quiere decir que es lo que va a recibir el servidor

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Registro {
    private String tipo;
    private String email;
    private String password;
    private String dni;
    private String apellido;
    private Date fechaNacimiento;
}
