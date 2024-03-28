package com.kaue.usuario.api.converter.response;

public record UsuarioResponseDTO(Long id,

                                 String nome,

                                 String email,

                                 String documento,

                                 EnderecoResponseDTO endereco) {


}
