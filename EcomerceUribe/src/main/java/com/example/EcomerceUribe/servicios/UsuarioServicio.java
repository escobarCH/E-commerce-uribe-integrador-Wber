package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.UsuarioGenericoDTO;
import com.example.EcomerceUribe.modelos.Usuario;
import com.example.EcomerceUribe.modelos.mapas.IUsuarioMapa;
import com.example.EcomerceUribe.repositorios.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    private IUsuarioRepositorio repositorio;

    @Autowired
    private IUsuarioMapa mapa;


    @Transactional
    public UsuarioGenericoDTO guardarUsuariogenerico(Usuario datosUsuario) {
        if (this.repositorio.findByCorreo(datosUsuario.getCorreo()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "***** El Correo Electrónico Ya Está Registrado *****");
        }

        if (datosUsuario.getNombres() == null || datosUsuario.getNombres().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "***** Nombre De Usuario Requerido *****");
        }

        if (datosUsuario.getContrasena() == null || datosUsuario.getContrasena().length() < 6) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "***** Contraseña Mínima De 6 Caracteres *****");
        }

        Usuario usuarioGuardado = this.repositorio.save(datosUsuario);
        return this.mapa.convertirAUsuarioGenericoDTO(usuarioGuardado);
    }

    @Transactional(readOnly = true)
    public List<UsuarioGenericoDTO> buscarTodosLosUsuarios() {
        List<Usuario> lista = this.repositorio.findAll();
        return this.mapa.convertirListaAUsuarioGenericoDTO(lista);
    }

    @Transactional(readOnly = true)
    public UsuarioGenericoDTO buscarUsuarioGenericoPorId(Integer id) {
        Usuario usuario = this.repositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "***** No Existe El Usuario Con ID: " + id + " *****"));
        return this.mapa.convertirAUsuarioGenericoDTO(usuario);
    }

    @Transactional(readOnly = true)
    public UsuarioGenericoDTO buscarUsuarioGenericoPorCorreo(String correo) {
        Usuario usuario = this.repositorio.findByCorreo(correo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "***** El Correo " + correo + " No Tiene Ningun Usuario *****"));
        return this.mapa.convertirAUsuarioGenericoDTO(usuario);
    }

    @Transactional
    public void eliminarUsuario(Integer id) {
        Usuario usuario = this.repositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "***** ERROR: No Existe El Usuario Con ID: " + id + " *****"));
        try {
            this.repositorio.delete(usuario);
        } catch (Exception error) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "***** Usuario No Se Pudo Eliminar Del Sistema *****" + error.getMessage());
        }
    }

    @Transactional
    public UsuarioGenericoDTO actualizarUsuario(Integer id, Usuario datosActualizados) {
        Usuario usuario = this.repositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "***** ID " + id + " No Encontrado *****"));

        usuario.setNombres(datosActualizados.getNombres());
        usuario.setCorreo(datosActualizados.getCorreo());

        Usuario actualizado = this.repositorio.save(usuario);
        return this.mapa.convertirAUsuarioGenericoDTO(actualizado);
    }
}

