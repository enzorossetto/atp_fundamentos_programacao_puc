public class Teclado extends Eletronico {

    private String layout;
    private boolean mecanico;

    public Teclado(String nome, double massa, boolean bivolt, String layout, boolean mecanico) {
        super(nome, massa, bivolt);
        this.layout = layout;
        this.mecanico = mecanico;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public boolean isMecanico() {
        return mecanico;
    }

    public void setMecanico(boolean mecanico) {
        this.mecanico = mecanico;
    }

    @Override
    public String toString() {
        String descricao = super.toString();

        descricao += "Layout das teclas: " + this.layout + "\n";
        descricao += "Teclado mecânico: " + (this.mecanico ? "Sim" : "Não") + "\n";

        return descricao;
    }

    @Override
    public String ligarDispositivo() {
        return "Teclado com RGB ligado para dar mais FPS\n";
    }
}
