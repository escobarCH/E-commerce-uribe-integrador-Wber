package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.UsuarioGenericoDTO;
import com.example.EcomerceUribe.modelos.Usuario;
import com.example.EcomerceUribe.modelos.mapas.IUsuarioMapa;
import com.example.EcomerceUribe.repositorios.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioServicio {

    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;

    @Autowired
    private IUsuarioMapa usuarioMapa;

    /**
     * Guarda un usuario genérico en el sistema.
     * Valida correo duplicado, nombre no vacío y longitud mínima de la contraseña.
     *
     * @param datosUsuario Datos del usuario a guardar.
     * @return UsuarioGenericoDTO con la información del usuario registrado.
     */
    public UsuarioGenericoDTO guardarUsuarioGenerico(Usuario datosUsuario) {

        // Validar correo duplicado
        if (this.usuarioRepositorio.findByCorreo(datosUsuario.getCorreo()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ya existe un usuario registrado con el correo ingresado"
            );
        }

        // Validar que el nombre no esté vacío
        if (datosUsuario.getNombres() == null || datosUsuario.getNombres().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El nombre del usuario es obligatorio"
            );
        }

        // Validar longitud mínima de la contraseña
        if (datosUsuario.getContraseña() == null || datosUsuario.getContraseña().length() < 6) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La contraseña debe tener al menos 6 caracteres"
            );
        }

        // Guardar usuario en la base de datos
        Usuario usuarioGuardado = this.usuarioRepositorio.save(datosUsuario);

        if (usuarioGuardado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al guardar el usuario en la base de datos"
            );
        }

        // Convertir a DTO y devolver
        return this.usuarioMapa.convertir_usuario_a_usuariogenericodto(usuarioGuardado);
    }
}
