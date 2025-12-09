package com.example.EcomerceUribe.modelos.mapas;

import com.example.EcomerceUribe.modelos.DTOS.UsuarioEspecialDTO;
import com.example.EcomerceUribe.modelos.DTOS.UsuarioGenericoDTO;
import com.example.EcomerceUribe.modelos.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUsuarioMapa {

    // 1️⃣ Modelo → DTO genérico
    UsuarioGenericoDTO convertirAUsuarioGenericoDTO(Usuario usuario);

    // 2️⃣ Lista de modelos → lista de DTO genéricos
    List<UsuarioGenericoDTO> convertirListaAUsuarioGenericoDTO(List<Usuario> lista);

    // 3️⃣ Modelo → DTO especial
    UsuarioEspecialDTO convertirAUsuarioEspecialDTO(Usuario usuario);

    // 4️⃣ Lista de modelos → lista de DTO especiales
    List<UsuarioEspecialDTO> convertirListaAUsuarioEspecialDTO(List<Usuario> lista);

}

