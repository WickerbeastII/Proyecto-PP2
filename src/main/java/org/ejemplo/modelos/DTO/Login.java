package org.ejemplo.modelos.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// DTO, siguifica Data Transer Object, quiere decir que es lo que va a recibir el servidor

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Login {
    private String usuario;
    private String pass;
}
