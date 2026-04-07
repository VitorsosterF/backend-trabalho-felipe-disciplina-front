package com.produtos.api.service;

import com.produtos.api.model.Produto;
import com.produtos.api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Produto> buscarPorTermo(String termo) {
        return repository.buscarPorTermo(termo);
    }

    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    public Produto atualizar(Long id, Produto produtoAtualizado) {
        Produto existente = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));

        existente.setNome(produtoAtualizado.getNome());
        existente.setDescricao(produtoAtualizado.getDescricao());
        existente.setPreco(produtoAtualizado.getPreco());
        existente.setQuantidade(produtoAtualizado.getQuantidade());
        existente.setCategoria(produtoAtualizado.getCategoria());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
