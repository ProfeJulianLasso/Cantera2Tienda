package com.sofka.controller;

import com.sofka.domain.Usuario;
import com.sofka.service.UsuarioService;
import com.sofka.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    private Response response = new Response();

    @GetMapping(path = "/")
    public Map<String, String> index() {
        var respuesta = new HashMap<String, String>();
        respuesta.put("message", "Hola Mundo");
        return respuesta;
    }

    @GetMapping(path = "/users")
    public Response listado() {
        // var usuarios = usuarioService.list();
        // return usuarios;
        try {
            response.data = usuarioService.list();
        } catch (Exception exc) {
            response.error = true;
            response.message = exc.getMessage();
            response.status = "ERROR";
        }
        return response;
    }

    @PostMapping(path = "/user")
    public ResponseEntity<Response> crear(Usuario usuario) {
        response.data = usuario;
        try {
            log.info("Usuario a crear: {}", usuario);
            usuarioService.save(usuario);
            return new ResponseEntity<Response>(response, HttpStatus.CREATED);
        } catch (Exception exc) {
            response.status = exc.getCause().toString();
            response.error = true;
            if (Pattern.compile("(usuario.tipo_documento_and_documento_UNIQUE)").matcher(exc.getMessage()).find()) {
                response.message = "documento duplicado";
                return new ResponseEntity<Response>(response, HttpStatus.CONFLICT);
            } else {
                response.message = exc.getMessage();
                return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @DeleteMapping(path = "/user/{id}")
    public ResponseEntity<Usuario> borrar(Usuario usuario) {
        log.info("Usuario a borrar: {}", usuario);
        usuarioService.delete(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PutMapping(path = "/user/{id}")
    public ResponseEntity<Usuario> actualizar(Usuario usuario, @PathVariable("id") Long id) {
        log.info("Usuario a modificar: {}", usuario);
        usuarioService.update(id, usuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PatchMapping(path = "/user/name/{id}")
    public ResponseEntity<Usuario> actualizarNombre(Usuario usuario, @PathVariable("id") Long id) {
        log.info("Usuario a modificar nombre: {}", usuario);
        usuarioService.updateNombre(id, usuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PatchMapping(path = "/user/typeId/{id}")
    public ResponseEntity<Usuario> actualizarTipoDocumento(Usuario usuario, @PathVariable("id") Long id) {
        log.info("Usuario a modificar tipoDocumento: {}", usuario);
        usuarioService.updateTipoDocumento(id, usuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PatchMapping(path = "/user/document/{id}")
    public ResponseEntity<Usuario> actualizarDocumento(Usuario usuario, @PathVariable("id") Long id) {
        log.info("Usuario a modificar documento: {}", usuario);
        usuarioService.updateDocumento(id, usuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

}
