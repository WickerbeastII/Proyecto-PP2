package org.ejemplo.controladores;

import org.ejemplo.modelos.LoginDTO;
import org.ejemplo.servicios.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
public class UsuarioController {
    public UsersService service = new UsersService();

    @RequestMapping(value = "/login", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> login(@RequestBody LoginDTO person) {
        // try {
        //     Perfil respuesta = service.login(usuario, pass);
            
        //     ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        //     String json = ow.writeValueAsString(respuesta);

        //     return ResponseEntity.ok(json);
        // } catch (Exception e) {
        //     return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
        // }

        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("ok");
    }
}
