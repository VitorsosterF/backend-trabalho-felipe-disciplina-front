package com.produtos.api.repository;

import com.produtos.api.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Busca por nome (case insensitive)
    List<Produto> findByNomeContainingIgnoreCase(String nome);

    // Busca por categoria
    List<Produto> findByCategoria(String categoria);

    // Busca combinada por nome ou categoria
    @Query("SELECT p FROM Produto p WHERE " +
           "LOWER(p.nome) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(p.categoria) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<Produto> buscarPorTermo(@Param("termo") String termo);
}
