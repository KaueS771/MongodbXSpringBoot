package com.kaue.usuario.api.converter.converter;


import com.kaue.usuario.infrastructure.entity.EnderecoEntity;
import com.kaue.usuario.infrastructure.entity.UsuarioEntity;
import com.kaue.usuario.api.converter.response.UsuarioResponseDTO;
import org.springframework.stereotype.Component;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
@Component
public interface UsuarioMapper {

    @Mapping(target = "id", source = "usuario.id")
    @Mapping(target = "nome", source = "usuario.nome")
    @Mapping(target = "documento", source = "usuario.documento")
    @Mapping(target = "endereco", source = "enderecoEntity")
    UsuarioResponseDTO paraUsuarioResponseDTO(UsuarioEntity usuario, EnderecoEntity enderecoEntity);

}
