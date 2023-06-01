/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rest;

/**
 *
 * @author HugoFernandes
 */
public class Utilizador {
    String nome;
    boolean estado;
    boolean login;
    boolean estadoJogo;

    public Utilizador(String nome) {
        this.nome = nome;
        this.estado = false;
        this.estadoJogo = false;
        this.login = false;
    }

    public String getNome() {
        return nome;
    }

    public boolean isEstado() {
        return estado;
    }

    public boolean isLogin() {
        return login;
    }

    public boolean isEstadoJogo() {
        return estadoJogo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public void setEstadoJogo(boolean estadoJogo) {
        this.estadoJogo = estadoJogo;
    }

}
