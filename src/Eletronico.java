import java.io.Serializable;

public abstract class Eletronico implements Serializable {

    private String nome;
    private double massa;
    private boolean bivolt;

    public Eletronico(String nome, double massa, boolean bivolt) {
        this.nome = nome;
        this.massa = massa;
        this.bivolt = bivolt;
    }

    public String toString() {
        String descricao = "";

        descricao += "Nome: " + this.nome + "\n";
        descricao += "Massa: " + this.massa +  "kg\n";
        descricao += "Este equipamento " + (this.bivolt ? "é" : "não é") + " bivolt\n";

        return descricao;
    }

    public abstract String ligarDispositivo();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getMassa() {
        return massa;
    }

    public void setMassa(double massa) {
        this.massa = massa;
    }

    public boolean isBivolt() {
        return bivolt;
    }

    public void setBivolt(boolean bivolt) {
        this.bivolt = bivolt;
    }
}
