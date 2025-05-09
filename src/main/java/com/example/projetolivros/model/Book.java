package com.example.projetolivros.model;

import java.util.List;

// Classe que representa um livro.
public class Book {
    private String titulo;           // Título do livro.
    private List<String> autores;    // Lista de autores do livro.
    private String descricao;        // Descrição do livro.
    private String capaUrl;          // URL da imagem da capa do livro.
    private String categoria;        // Categoria ou gênero do livro.

    // Getters e Setters
    // Métodos para obter e definir os valores das variáveis de instância.

    // Retorna o título do livro
    public String getTitulo() {
        return titulo;
    }

    // Define o título do livro
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Retorna a lista de autores do livro
    public List<String> getAutores() {
        return autores;
    }

    // Define a lista de autores do livro
    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    // Retorna a descrição do livro
    public String getDescricao() {
        return descricao;
    }

    // Define a descrição do livro
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Retorna a URL da capa do livro
    public String getCapaUrl() {
        return capaUrl;
    }

    // Define a URL da capa do livro
    public void setCapaUrl(String capaUrl) {
        this.capaUrl = capaUrl;
    }

    // Retorna a categoria (gênero) do livro
    public String getCategoria() {
        return categoria;
    }

    // Define a categoria (gênero) do livro
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Método toString para exibir informações do livro
    @Override
    public String toString() {
        return "Book{" +
                "titulo='" + titulo + '\'' +        // Exibe o título do livro
                ", autores=" + autores +           // Exibe a lista de autores
                ", descricao='" + descricao + '\'' +// Exibe a descrição do livro
                ", capaUrl='" + capaUrl + '\'' +    // Exibe a URL da capa
                ", categoria='" + categoria + '\'' +// Exibe a categoria do livro
                '}';
    }
}
