package rest;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "acao")
public class Acao {

    private String sigla;
    private String nome;
    private float valorAbertura;
    private float valorAtual;

    public Acao() {
    }

    public Acao(String sigla, String nome, float valorAbertura) {
        this.sigla = sigla;
        this.nome = nome;
        this.valorAbertura = valorAbertura;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public float getValorAbertura() {
        return valorAbertura;
    }

    public void setValorAbertura(float valorAbertura) {
        this.valorAbertura = valorAbertura;
    }

    public float getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(float valorAtual) {
        this.valorAtual = valorAtual;
    }

    public float getVariacao() {
        return ((valorAtual - valorAbertura) / valorAbertura) * 100;
    }

    @Override
    public String toString() {
        return "Acao{"
                + "sigla='" + sigla + '\''
                + ", nome='" + nome + '\''
                + ", valorAbertura=" + valorAbertura
                + ", valorAtual=" + valorAtual
                + '}';
    }
}
