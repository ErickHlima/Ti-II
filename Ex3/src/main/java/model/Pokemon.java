package model;

public class Pokemon {
    private int id;
    private String nome;
    private String tipo;
    private int nivel;

    public Pokemon(int id, String nome, String tipo, int nivel) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.nivel = nivel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
    	if (nome.length() >= 3)
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
    	if(nivel > 0)
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "Pokemon [id=" + id + ", nome=" + nome + ", tipo=" + tipo + ", nivel=" + nivel + "]";
    }
}
