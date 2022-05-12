package com.example.trabalhosqlite.model;

public class Livro {

    public static final String TABLE = "Livros";
    public static final String KEY_ID = "id";
    public static final String KEY_nome= "nome";
    public static final String KEY_isbn = "isbn";
    public static final String KEY_ano = "ano";
    public static final String KEY_autor = "autor";

    private int livroid;
    private String nome;
    private int isbn;
    private int ano;
    private String autor;

    public int getLivroid() {
        return livroid;
    }

    public void setLivroid(int livroid) {
        this.livroid = livroid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

}
