package org.ejemplo.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Perfil {
    private String user_id;
    private String nombre;
    private String apellido;
    private String DNI;
    private String fechaNacimiento;
}
