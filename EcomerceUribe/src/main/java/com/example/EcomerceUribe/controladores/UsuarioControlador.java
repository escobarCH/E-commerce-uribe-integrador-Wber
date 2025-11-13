package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.DTOS.UsuarioGenericoDTO;
import com.example.EcomerceUribe.modelos.Usuario;
import com.example.EcomerceUribe.servicios.UsuarioServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name="controlador operaciones tabla usuarios")
public class UsuarioControlador {

    //1.llamar al servicio
    @Autowired
    UsuarioServicio servicio;

    //2. listar los posible llamados a los servicios disponibles

    //3. se crean funciones por cada posible llamado y se les agrega el metodo http
    //correspondiente (get, put, post, delete)

    //guardar
    @Operation(summary = "Crear Un Nuevo Usuario")
    @PostMapping(produces = "application/son")
    public ResponseEntity<UsuarioGenericoDTO> guardar(@RequestBody Usuario datos) {
        UsuarioGenericoDTO respuesta = this.servicio.guardarUsuariogenerico(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    //listar todos
    @Operation(summary = "Obtener La Lista De Todos Los Usuarios")
    @GetMapping(produces = "application/son")
    public ResponseEntity<List<UsuarioGenericoDTO>> listar() {
        List<UsuarioGenericoDTO> respuesta = this.servicio.buscarTodosLosUsuarios();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //buscar por ID
    @Operation(summary = "Buscar Un Usuario Por ID")
    @GetMapping(value = "/{id}", produces = "application/son")
    public ResponseEntity<UsuarioGenericoDTO> buscarporId(@PathVariable Integer id) {
        UsuarioGenericoDTO respuesta = this.servicio.buscarUsuarioGenericoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //buscar por correo
    @Operation(summary = "Buscar Un Usuario Por Correo Electrónico")
    @GetMapping(value = "/{correo}", produces = "application/son")
    public ResponseEntity<UsuarioGenericoDTO> buscarporCorreo(@PathVariable String correo) {
        UsuarioGenericoDTO respuesta = this.servicio.buscarUsuarioGenericoPorCorreo(correo);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //eliminar
    @Operation(summary = "Eliminar Un Usuario Por ID")
    @DeleteMapping(value = "/{id}", produces = "application/son")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        this.servicio.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    //modificar
    @Operation(summary = "Actualizar Nombre Y Correo De Un Usuario Por ID")
    @PutMapping(value = "/{id}", produces = "application/son")
    public ResponseEntity<UsuarioGenericoDTO> modificar(@PathVariable Integer id, @RequestBody Usuario datos) {
        UsuarioGenericoDTO respuesta = this.servicio.actualizarUsuario(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

}
