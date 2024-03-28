package com.kaue.usuario.business;


import com.kaue.usuario.infrastructure.entity.EnderecoEntity;
import com.kaue.usuario.infrastructure.entity.repository.EnderecoRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static org.springframework.util.Assert.notNull;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoEntity salvaEndereco(EnderecoEntity enderecoEntity) {
        return enderecoRepository.save(enderecoEntity);
    }

    public EnderecoEntity findByUsuarioId(String usuarioId){
        return enderecoRepository.findByUsuarioId(usuarioId);
    }
    public void deletaUsuarioId(String usuarioId){
        enderecoRepository.deleteByUsuarioId(usuarioId);
    }

}
