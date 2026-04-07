package com.produtos.api.controller;

import com.produtos.api.model.Produto;
import com.produtos.api.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "*") // Permite chamadas do Vue.js em desenvolvimento
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    // GET /api/produtos → Lista todos os produtos
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        List<Produto> produtos = service.listarTodos();
        return ResponseEntity.ok(produtos);
    }

    // GET /api/produtos/{id} → Busca produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/produtos/buscar?termo=xxx → Busca por nome ou categoria
    @GetMapping("/buscar")
    public ResponseEntity<List<Produto>> buscar(@RequestParam String termo) {
        List<Produto> produtos = service.buscarPorTermo(termo);
        return ResponseEntity.ok(produtos);
    }

    // POST /api/produtos → Cria novo produto
    @PostMapping
    public ResponseEntity<Produto> criar(@Valid @RequestBody Produto produto) {
        Produto salvo = service.salvar(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // PUT /api/produtos/{id} → Atualiza produto existente
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id,
                                              @Valid @RequestBody Produto produto) {
        try {
            Produto atualizado = service.atualizar(id, produto);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/produtos/{id} → Remove produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
