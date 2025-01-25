package br.com.razila.simulado.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "materias")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_materia", nullable = false, length = 200)
    private String nomeMateria;

    public Long getId() {
        return id;
    }

    public String getNomeMateria() {
        return nomeMateria;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNomeMateria(String nomeMateria) {
        this.nomeMateria = nomeMateria;
    }
}
