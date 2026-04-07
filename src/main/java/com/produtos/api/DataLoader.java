package com.produtos.api;

import com.produtos.api.model.Produto;
import com.produtos.api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProdutoRepository repository;

    @Override
    public void run(String... args) {
        // Dados iniciais para facilitar testes
        repository.save(new Produto(null, "Notebook Dell Inspiron", "Notebook 15\" Intel i5, 8GB RAM, 256GB SSD",
                new BigDecimal("3499.99"), 10, "Eletrônicos", null, null));
        repository.save(new Produto(null, "Mouse Logitech MX Master", "Mouse sem fio ergonômico",
                new BigDecimal("349.90"), 25, "Periféricos", null, null));
        repository.save(new Produto(null, "Teclado Mecânico Keychron K2", "Teclado compacto RGB switches Brown",
                new BigDecimal("599.00"), 15, "Periféricos", null, null));
        repository.save(new Produto(null, "Monitor LG 24\" Full HD", "Monitor IPS 75Hz HDMI",
                new BigDecimal("999.00"), 8, "Eletrônicos", null, null));
        repository.save(new Produto(null, "Cadeira Gamer DXRacer", "Cadeira ergonômica com apoio lombar",
                new BigDecimal("1299.00"), 5, "Móveis", null, null));

        System.out.println("✅ Dados iniciais carregados com sucesso!");
    }
}
