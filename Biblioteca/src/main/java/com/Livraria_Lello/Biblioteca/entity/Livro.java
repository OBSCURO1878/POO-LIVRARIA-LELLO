package com.Livraria_Lello.Biblioteca.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "livros")
@Data
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;

    @Column(name = "codigo_isbn")
    private String codigoIsbn;

    private BigDecimal preco;

    @Column(name = "ano_publicacao")
    private LocalDate anoPublicacao;
}
