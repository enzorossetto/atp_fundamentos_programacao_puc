public class Televisao extends Eletronico {

    private int polegadas;
    private boolean smart;

    public Televisao(String nome, double massa, boolean bivolt, int polegadas, boolean smart) {
        super(nome, massa, bivolt);
        this.polegadas = polegadas;
        this.smart = smart;
    }

    public int getPolegadas() {
        return polegadas;
    }

    public void setPolegadas(int polegadas) {
        this.polegadas = polegadas;
    }

    public boolean isSmart() {
        return smart;
    }

    public void setSmart(boolean smart) {
        this.smart = smart;
    }

    @Override
    public String toString() {
        String descricao = super.toString();

        descricao += "Tamanho da tela: " + this.polegadas + "polegadas\n";

        return descricao;
    }

    @Override
    public String ligarDispositivo() {
        return "Ligando televisão no canal de esportes\n";
    }
}
