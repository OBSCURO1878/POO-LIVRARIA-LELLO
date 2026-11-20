package com.Livraria_Lello.Biblioteca.repository;

import com.Livraria_Lello.Biblioteca.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
