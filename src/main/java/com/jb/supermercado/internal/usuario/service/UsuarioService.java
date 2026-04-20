package com.jb.supermercado.internal.usuario.service;

import com.jb.supermercado.config.exception.BusinessException;
import com.jb.supermercado.config.exception.RecursoNaoEncontradoException;
import com.jb.supermercado.internal.usuario.dto.UsuarioRequestRecord;
import com.jb.supermercado.internal.usuario.dto.UsuarioResponseRecord;
import com.jb.supermercado.internal.usuario.entity.UsuarioEntity;
import com.jb.supermercado.internal.usuario.mapper.UsuarioMapperRecord;
import com.jb.supermercado.internal.usuario.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioResponseRecord> listaUsuarios() {
        List<UsuarioEntity> usuarios = this.usuarioRepository.findAll();
        return UsuarioMapperRecord.entidadeParaResponseRecordList(usuarios);
    }

    public void cadastrarUsuario(UsuarioRequestRecord usuarioRequest) {
        boolean emailJaExiste = this.usuarioRepository.existsByEmail(usuarioRequest.email());
        if (emailJaExiste) {
            throw new BusinessException("Ja existe usuario cadastrado com este email");
        }
        UsuarioEntity usuarioEntity = UsuarioMapperRecord.requestParaEntidade(usuarioRequest);
        this.usuarioRepository.save(usuarioEntity);
    }

    public UsuarioResponseRecord buscarUsuarioPorId(Long id) {
        UsuarioEntity usuarioEntity = this.usuarioRepository.findById(id).orElseThrow(() ->
                new RecursoNaoEncontradoException("Usuario nao Encontrado"));
        return UsuarioMapperRecord.entidadeParaResponse(usuarioEntity);
    }

    public void atualizarUsuario(Long id, UsuarioRequestRecord usuarioRequest) {
        UsuarioEntity usuarioEntity = this.usuarioRepository.findById(id).orElseThrow(() ->
                new RecursoNaoEncontradoException("Usuario nao Encontrado"));

        if (!usuarioEntity.getEmail().equals(usuarioRequest.email()) && this.usuarioRepository.existsByEmail(usuarioRequest.email())) {
            throw new BusinessException("Ja existe usuario cadastrado com este email");
        }

        usuarioEntity.setNome(usuarioRequest.nome());
        usuarioEntity.setEmail(usuarioRequest.email());
        usuarioEntity.setSenha(usuarioRequest.senha());
        usuarioEntity.setStatus(usuarioRequest.status());
        this.usuarioRepository.save(usuarioEntity);
    }

    public void removerUsuario(Long id) {
        UsuarioEntity usuarioEntity = this.usuarioRepository.findById(id).orElseThrow(() ->
                new RecursoNaoEncontradoException("Usuario nao Encontrado"));
        this.usuarioRepository.delete(usuarioEntity);
    }
}

