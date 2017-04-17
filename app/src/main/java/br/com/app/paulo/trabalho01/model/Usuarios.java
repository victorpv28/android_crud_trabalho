package br.com.app.paulo.trabalho01.model;

/**
 * Created by pvnluz on 17/04/2017.
 */

public class Usuarios {

    private long id;
    private String login;
    private String senha;


    public Usuarios(){}

    public Usuarios(long id, String login, String senha){
        this.id = id;
        this.login = login;
        this.senha = senha;
    }

    public Usuarios(String login, String senha){
        this.login = login;
        this.senha = senha;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
