public class Laptop extends Eletronico{

    private String sistemaOperacional;
    private String marcaProcessador;

    public Laptop(String nome, double massa, boolean bivolt, String sistemaOperacional, String marcaProcessador) {
        super(nome, massa, bivolt);
        this.sistemaOperacional = sistemaOperacional;
        this.marcaProcessador = marcaProcessador;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    @Override
    public String toString() {
        String descricao = super.toString();

        descricao += "Sistema operacional: " + this.sistemaOperacional + "\n";
        descricao += "Marca do processador: " + this.marcaProcessador + "\n";

        return descricao;
    }

    @Override
    public String ligarDispositivo() {
        return "Iniciando laptop no sistema operacional " + this.sistemaOperacional + "\n";
    }
}
