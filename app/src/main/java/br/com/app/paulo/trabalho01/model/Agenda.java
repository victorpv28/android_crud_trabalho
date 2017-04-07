package br.com.app.paulo.trabalho01.model;

/**
 * Created by pvnluz on 06/04/2017.
 */

public class Agenda {

    private Long id;
    private String nome;
    private String telefone;
    private Double nota;

    public Agenda(String nome, String telefone, Double nota){

        this.nome = nome;
        this.telefone = telefone;
        this.nota = nota;

    }

    public Agenda(Long id, String nome, String telefone, Double nota){

        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.nota = nota;

    }

    public Agenda() {

    }

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}
