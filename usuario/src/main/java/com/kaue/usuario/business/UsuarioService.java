package com.kaue.usuario.business;

import com.kaue.usuario.api.converter.converter.UsuarioConverter;
import com.kaue.usuario.api.converter.converter.UsuarioMapper;
import com.kaue.usuario.api.converter.request.UsuarioRequestDTO;
import com.kaue.usuario.api.converter.response.UsuarioResponseDTO;
import com.kaue.usuario.infrastructure.entity.BusinessException;
import com.kaue.usuario.infrastructure.entity.EnderecoEntity;
import com.kaue.usuario.infrastructure.entity.UsuarioEntity;
import com.kaue.usuario.infrastructure.entity.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.util.Assert.notNull;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final UsuarioMapper usuarioMapper;
    private final EnderecoService enderecoService;


    public UsuarioEntity salvaUsuario(UsuarioEntity usuarioEntity) {
        return usuarioRepository.save(usuarioEntity);
    }

    public UsuarioResponseDTO gravarUsuarios(UsuarioRequestDTO usuarioRequestDTO) {
        try {
            notNull(usuarioRequestDTO, "Os dados do usuário são obrigatórios");

            // Convertendo DTO para entidade e salvando o usuário
            UsuarioEntity usuarioEntity = salvaUsuario(usuarioConverter.paraUsuarioEntity(usuarioRequestDTO));

            // Salvando o endereço associado ao usuário
            EnderecoEntity enderecoEntity = enderecoService.salvaEndereco(
                    usuarioConverter.paraEnderecoEntity(usuarioRequestDTO.getEndereco(), usuarioEntity.getId()));

            // Retornando o DTO do usuário salvo
            return usuarioMapper.paraUsuarioResponseDTO(usuarioEntity, enderecoEntity);
        } catch (Exception e) {
            throw new BusinessException("Erro ao gravar dados de usuário", e);
        }
    }


    public UsuarioResponseDTO buscaDadosUsuario(String email) {
    try {
        UsuarioEntity entity = usuarioRepository.findByEmail(email);
        notNull(entity, "Usuário não encontrado");
        EnderecoEntity enderecoEntity = enderecoService.findByUsuarioId(entity.getId());

        return usuarioMapper.paraUsuarioResponseDTO(entity, enderecoEntity);
    } catch (Exception e) {
        throw new BusinessException("Erro ao buscar dados de usuário", e);
    }
}

    @Transactional
    public void deletaDadosUsuario(String email) {
        UsuarioEntity entity = usuarioRepository.findByEmail(email);
        usuarioRepository.deleteByEmail(email);
        enderecoService.deletaUsuarioId(entity.getId());

    }


}