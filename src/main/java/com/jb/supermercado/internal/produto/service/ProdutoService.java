package com.jb.supermercado.internal.produto.service;

import com.jb.supermercado.config.exception.BusinessException;
import com.jb.supermercado.config.exception.RecursoNaoEncontradoException;
import com.jb.supermercado.internal.produto.dto.ProdutoRequestRecord;
import com.jb.supermercado.internal.produto.dto.ProdutoResponseRecord;
import com.jb.supermercado.internal.produto.entity.ProdutoEntity;
import com.jb.supermercado.internal.produto.mapper.ProdutoMapperRecord;
import com.jb.supermercado.internal.produto.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoResponseRecord> listaProdutos() {
        List<ProdutoEntity> produtos = this.produtoRepository.findAll();
        return ProdutoMapperRecord.entidadeParaResponseRecordList(produtos);
    }

    public void cadastrarProduto(ProdutoRequestRecord produtoRequest) {
        boolean nomeJaExiste = this.produtoRepository.existsByNome(produtoRequest.nome());
        if (nomeJaExiste) {
            throw new BusinessException("Ja existe produto cadastrado com este nome");
        }
        ProdutoEntity produtoEntity = ProdutoMapperRecord.requestParaEntidade(produtoRequest);
        this.produtoRepository.save(produtoEntity);
    }

    public ProdutoResponseRecord buscarProdutoPorId(Long id) {
        ProdutoEntity produtoEntity = this.produtoRepository.findById(id).orElseThrow(() ->
                new RecursoNaoEncontradoException("Produto nao Encontrado"));
        return ProdutoMapperRecord.entidadeParaResponse(produtoEntity);
    }

    public void atualizarProduto(Long id, ProdutoRequestRecord produtoRequest) {
        ProdutoEntity produtoEntity = this.produtoRepository.findById(id).orElseThrow(() ->
                new RecursoNaoEncontradoException("Produto nao Encontrado"));

        if (!produtoEntity.getNome().equals(produtoRequest.nome()) && this.produtoRepository.existsByNome(produtoRequest.nome())) {
            throw new BusinessException("Ja existe produto cadastrado com este nome");
        }

        produtoEntity.setNome(produtoRequest.nome());
        produtoEntity.setDescricao(produtoRequest.descricao());
        produtoEntity.setPreco(produtoRequest.preco());
        produtoEntity.setQuantidadeEstoque(produtoRequest.quantidadeEstoque());
        produtoEntity.setStatus(produtoRequest.status());
        this.produtoRepository.save(produtoEntity);
    }

    public void removerProduto(Long id) {
        ProdutoEntity produtoEntity = this.produtoRepository.findById(id).orElseThrow(() ->
                new RecursoNaoEncontradoException("Produto nao Encontrado"));
        this.produtoRepository.delete(produtoEntity);
    }
}

