package com.kaue.usuario.infrastructure.entity.repository;



import com.kaue.usuario.infrastructure.entity.EnderecoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;


public interface EnderecoRepository extends MongoRepository<EnderecoEntity, String> {

    EnderecoEntity findByUsuarioId(String usuarioId);

    @Transactional
    void deleteByUsuarioId(String usuarioId);
}
