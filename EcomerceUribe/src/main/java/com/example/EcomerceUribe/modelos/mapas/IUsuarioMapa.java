package com.example.EcomerceUribe.modelos.mapas;

import com.example.EcomerceUribe.modelos.DTOS.UsuarioEspecialDTO;
import com.example.EcomerceUribe.modelos.DTOS.UsuarioGenericoDTO;
import com.example.EcomerceUribe.modelos.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUsuarioMapa {

    // 1️⃣ Transformar un modelo en un DTO genérico
    @Mapping(source = "nombres", target = "nombres")
    @Mapping(source = "correo", target = "correo")
    @Mapping(source = "estado", target = "estado")
    @Mapping(source = "fechaNacimiento", target = "fechaNacimiento")
    @Mapping(source = "documento", target = "documento")
    UsuarioGenericoDTO convertir_usuario_a_usuariogenericodto(Usuario usuario);

    // 2️⃣ Transformar una lista de modelos en una lista de DTO genéricos
    List<UsuarioGenericoDTO> convetir_lista_a_listadtogenerico(List<Usuario> lista);

    // 3️⃣ Transformar un modelo en un DTO especial
    @Mapping(source = "nombres", target = "nombres")
    @Mapping(source = "correo", target = "correo")
    @Mapping(source = "estado", target = "estado")
    @Mapping(source = "fechaNacimiento", target = "fechaNacimiento")
    @Mapping(source = "documento", target = "documento")
    @Mapping(source = "contraseña", target = "contraseña")
    UsuarioEspecialDTO convertir_usuario_a_usuarioespecialdto(Usuario usuario);

    // 4️⃣ Transformar una lista de modelos en una lista de DTO especiales
    List<UsuarioEspecialDTO> convetir_lista_a_listadtoespecial(List<Usuario> lista);
}
