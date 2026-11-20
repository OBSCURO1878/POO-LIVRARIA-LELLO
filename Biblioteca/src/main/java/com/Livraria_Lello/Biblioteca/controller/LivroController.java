package com.Livraria_Lello.Biblioteca.controller;

import com.Livraria_Lello.Biblioteca.entity.Livro;
import com.Livraria_Lello.Biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository repository;

    @PostMapping
    public Livro salvar(@RequestBody Livro livro) {
        return repository.save(livro);
    }

    @GetMapping
    public List<Livro> listarTodos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @RequestBody Livro dadosAtualizados) {
        return repository.findById(id)
                .map(livroExistente -> {
                    livroExistente.setTitulo(dadosAtualizados.getTitulo());
                    livroExistente.setAutor(dadosAtualizados.getAutor());
                    livroExistente.setCodigoIsbn(dadosAtualizados.getCodigoIsbn());
                    livroExistente.setPreco(dadosAtualizados.getPreco());
                    livroExistente.setAnoPublicacao(dadosAtualizados.getAnoPublicacao());
                    return ResponseEntity.ok(repository.save(livroExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
