package com.Livraria_Lello.Biblioteca;

import com.Livraria_Lello.Biblioteca.controller.LivroController;
import com.Livraria_Lello.Biblioteca.entity.Livro;
import com.Livraria_Lello.Biblioteca.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LivroControllerTest {

    @Mock
    private LivroRepository repository;

    @InjectMocks
    private LivroController controller;

    private Livro livroExemplo;

    @BeforeEach
    void setUp() {
        livroExemplo = new Livro();
        livroExemplo.setId(1L);
        livroExemplo.setTitulo("O Hobbit");
        livroExemplo.setAutor("J.R.R. Tolkien");
        livroExemplo.setCodigoIsbn("9788595084742");
        livroExemplo.setPreco(new BigDecimal("49.90"));
        livroExemplo.setAnoPublicacao(LocalDate.of(1937, 9, 21));
    }

    @Test
    void deveSalvarNovoLivro() {
        when(repository.save(any(Livro.class))).thenReturn(livroExemplo);

        Livro salvo = controller.salvar(livroExemplo);

        assertNotNull(salvo);
        assertEquals("O Hobbit", salvo.getTitulo());
        verify(repository, times(1)).save(livroExemplo);
    }

    @Test
    void deveListarTodosOsLivros() {
        when(repository.findAll()).thenReturn(List.of(livroExemplo));

        List<Livro> lista = controller.listarTodos();

        assertEquals(1, lista.size());
        assertEquals("J.R.R. Tolkien", lista.get(0).getAutor());
    }

    @Test
    void deveBuscarLivroPorIdQuandoExistir() {
        when(repository.findById(1L)).thenReturn(Optional.of(livroExemplo));

        ResponseEntity<Livro> resposta = controller.buscarPorId(1L);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
        assertEquals(1L, resposta.getBody().getId());
    }

    @Test
    void deveRetornarNotFoundQuandoIdNaoExistir() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Livro> resposta = controller.buscarPorId(99L);

        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
    }

    @Test
    void deveAtualizarLivroExistente() {
        Livro novosDados = new Livro();
        novosDados.setTitulo("O Hobbit - Edição Especial");
        novosDados.setAutor("J.R.R. Tolkien");
        novosDados.setCodigoIsbn("9788595084742");
        novosDados.setPreco(new BigDecimal("79.90"));
        novosDados.setAnoPublicacao(LocalDate.of(2019, 7, 15));

        when(repository.findById(1L)).thenReturn(Optional.of(livroExemplo));
        when(repository.save(any(Livro.class))).thenReturn(livroExemplo);

        ResponseEntity<Livro> resposta = controller.atualizar(1L, novosDados);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals("O Hobbit - Edição Especial", resposta.getBody().getTitulo());
    }

    @Test
    void deveExcluirLivroQuandoExistir() {
        when(repository.existsById(1L)).thenReturn(true);

        ResponseEntity<Void> resposta = controller.excluir(1L);

        assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());
        verify(repository, times(1)).deleteById(1L);
    }
}
