package com.example.projetolivros.service;

import com.example.projetolivros.model.Book;
import com.example.projetolivros.model.PreferenciasRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

// A classe é um serviço que contém a lógica de negócio relacionada aos livros e recomendações.
@Service
public class BookService {
    // URL base para a API de busca de livros do Google
    private static final String API_URL = "https://www.googleapis.com/books/v1/volumes?q=";

    // Armazena o histórico das preferências de busca dos usuários.
    private final List<PreferenciasRequest> historicoRequisicoes = new ArrayList<>();

    // Método para buscar livros com base em uma consulta (query).
    public List<Book> buscarLivros(String query) {
        registrarPreferencia(query);  // Armazena a consulta como uma preferência.
        String url = API_URL + query;  // Monta a URL para a consulta da API.
        RestTemplate restTemplate = new RestTemplate();  // Cria uma instância do RestTemplate para fazer a requisição HTTP.
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);  // Faz a requisição GET e recebe a resposta.
        return extrairLivros(response);  // Extrai os livros da resposta da API.
    }

    // Método para buscar livros por categoria (assunto).
    public List<Book> buscarPorCategoria(String categoria) {
        registrarPreferencia("subject:" + categoria);  // Armazena a categoria como uma preferência.
        return buscarLivros("subject:" + categoria);  // Realiza a busca pelos livros na categoria.
    }

    // Método para recomendar livros com base nas preferências do usuário.
    public List<Book> recomendarLivros(PreferenciasRequest request) {
        // Se o usuário forneceu preferências, armazena no histórico.
        if (request.getPreferencias() != null && !request.getPreferencias().isEmpty()) {
            historicoRequisicoes.add(request);  // Adiciona as preferências ao histórico.
        }

        // Caso o usuário não forneça novas preferências, usa as do histórico.
        List<String> preferencias = request.getPreferencias();
        if (preferencias == null || preferencias.isEmpty()) {
            // Coleta todas as preferências únicas do histórico.
            preferencias = historicoRequisicoes.stream()
                    .flatMap(p -> p.getPreferencias().stream())
                    .distinct()
                    .collect(Collectors.toList());
        }

        // Cria uma lista para armazenar os livros recomendados.
        List<Book> recomendados = new ArrayList<>();
        for (String pref : preferencias) {
            recomendados.addAll(buscarLivros(pref));  // Busca livros para cada preferência.
        }

        // Limita o número de livros recomendados a 10.
        return recomendados.stream().limit(10).collect(Collectors.toList());
    }

    // Método para listar o histórico de preferências das requisições.
    public List<PreferenciasRequest> listarHistorico() {
        return historicoRequisicoes;  // Retorna o histórico de preferências.
    }

    // Método privado para registrar uma preferência no histórico.
    private void registrarPreferencia(String termo) {
        PreferenciasRequest request = new PreferenciasRequest();  // Cria um novo objeto de preferências.
        request.setPreferencias(List.of(termo));  // Define o termo como uma preferência.
        historicoRequisicoes.add(request);  // Adiciona o objeto de preferências ao histórico.
    }

    // Método privado para extrair os livros da resposta da API do Google.
    private List<Book> extrairLivros(Map<String, Object> response) {
        // Extrai a lista de livros da resposta (se existirem).
        List<Map<String, Object>> items = (List<Map<String, Object>>) response.get("items");
        if (items == null) return Collections.emptyList();  // Se não houver livros, retorna uma lista vazia.

        // Cria uma lista para armazenar os livros extraídos.
        List<Book> livros = new ArrayList<>();
        for (Map<String, Object> item : items) {
            Map<String, Object> volumeInfo = (Map<String, Object>) item.get("volumeInfo");  // Extrai as informações do volume (livro).
            Book livro = new Book();  // Cria um novo objeto Book para armazenar as informações do livro.
            livro.setTitulo((String) volumeInfo.get("title"));  // Define o título do livro.
            livro.setAutores((List<String>) volumeInfo.get("authors"));  // Define os autores do livro.
            livro.setDescricao((String) volumeInfo.get("description"));  // Define a descrição do livro.
            livro.setCategoria(volumeInfo.containsKey("categories") ?
                    ((List<String>) volumeInfo.get("categories")).get(0) : "N/A");  // Define a categoria, se disponível.

            // Extrai a URL da capa do livro (se disponível).
            Map<String, Object> imageLinks = (Map<String, Object>) volumeInfo.get("imageLinks");
            livro.setCapaUrl(imageLinks != null ? (String) imageLinks.get("thumbnail") : null);
            livros.add(livro);  // Adiciona o livro à lista de livros.
        }
        return livros;  // Retorna a lista de livros.
    }
}
