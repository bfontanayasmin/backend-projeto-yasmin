package com.example.projetolivros.controller;

import com.example.projetolivros.model.Book;
import com.example.projetolivros.model.PreferenciasRequest;
import com.example.projetolivros.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// A classe é um controlador REST que gerencia as requisições da API relacionadas aos livros.
@RestController
@RequestMapping("/api")  // Define a base URL "/api" para todas as rotas da classe.
public class BookController {

    // A dependência do serviço que irá lidar com as operações dos livros.
    private final BookService bookService;

    // O construtor injeta o serviço que será utilizado para processar as operações.
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Método para buscar livros por título.
    // Recebe o título como variável de caminho na URL (por exemplo, "/livros/{titulo}").
    @GetMapping("/livros/{titulo}")
    public List<Book> buscarLivros(@PathVariable String titulo) {
        return bookService.buscarLivros(titulo);  // Chama o serviço para buscar livros.
    }

    // Método para buscar livros por categoria.
    // A categoria é passada na URL como variável (por exemplo, "/genero/{categoria}").
    @GetMapping("/genero/{categoria}")
    public List<Book> buscarPorCategoria(@PathVariable String categoria) {
        return bookService.buscarPorCategoria(categoria);  // Chama o serviço para buscar por categoria.
    }

    // Método para recomendar livros com base nas preferências.
    // Recebe um JSON com as preferências no corpo da requisição.
    @PostMapping("/recomendar")
    public List<Book> recomendarLivros(@RequestBody PreferenciasRequest request) {
        return bookService.recomendarLivros(request);  // Chama o serviço para recomendar livros com base nas preferências.
    }

    // Método para retornar informações sobre o sistema (exemplo: quem está no projeto).
    @GetMapping("/sobre")
    public Map<String, Object> sobre() {
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("integrantes", List.of("Yasmin Bez Fontana"));
        resposta.put("nome_projeto", "BookLet");
        return resposta;
    }
}
