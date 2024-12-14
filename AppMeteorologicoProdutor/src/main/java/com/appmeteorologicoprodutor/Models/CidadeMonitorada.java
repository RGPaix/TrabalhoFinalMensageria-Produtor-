package com.appmeteorologicoprodutor.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "cidades_monitoradas")
public class CidadeMonitorada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;

    public CidadeMonitorada() {
    }

    public CidadeMonitorada(String nome, Estado estado) {
        this.nome = nome;
        this.estado = estado;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
