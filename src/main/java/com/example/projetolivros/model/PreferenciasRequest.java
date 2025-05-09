package com.example.projetolivros.model;

import java.util.List;

// Classe que representa as preferências de um usuário.
public class PreferenciasRequest {
    private List<String> preferencias;  // Lista de preferências do usuário (termos de busca).

    // Getter para acessar a lista de preferências.
    public List<String> getPreferencias() {
        return preferencias;
    }

    // Setter para definir a lista de preferências.
    public void setPreferencias(List<String> preferencias) {
        this.preferencias = preferencias;
    }

    // Método toString para representar as preferências como uma string legível.
    @Override
    public String toString() {
        return "PreferenciasRequest{" +
                "preferencias=" + preferencias +  // Exibe a lista de preferências.
                '}';
    }
}
