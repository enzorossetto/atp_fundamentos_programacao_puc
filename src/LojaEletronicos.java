import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class LojaEletronicos {

    private ArrayList<Eletronico> eletronicos = new ArrayList<>();

    public String[] leValores (String [] dadosIn){
        String [] dadosOut = new String [dadosIn.length];

        for (int i = 0; i < dadosIn.length; i++)
            dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");

        return dadosOut;
    }

    public Laptop leLeptop() {
        String [] nomeValores = {"Nome", "Massa", "É bivolt", "Sistema operacional", "Marca do processador"};
        String [] valores = leValores (nomeValores);

        double massa = this.retornaDouble(valores[1]);
        boolean bivolt = retornaBoolean(valores[2], "O laptop é bivolt?");

        return new Laptop(valores[0], massa, bivolt, valores[3], valores[4]);
    }

    public Televisao leTelevisao() {
        String [] nomeValores = {"Nome", "Massa", "É bivolt", "Tamanho da tela", "É smart?"};
        String [] valores = leValores (nomeValores);

        double massa = this.retornaDouble(valores[1]);
        boolean bivolt = retornaBoolean(valores[2], "O laptop é bivolt?");
        int polegadas = retornaInteiro(valores[3]);
        boolean smart = retornaBoolean(valores[4], "É smart?");

        return new Televisao(valores[0], massa, bivolt, polegadas, smart);
    }

    public Teclado leTeclado() {
        String [] nomeValores = {"Nome", "Massa", "É bivolt", "Layout do teclado", "É mecânico?"};
        String [] valores = leValores (nomeValores);

        double massa = this.retornaDouble(valores[1]);
        boolean bivolt = retornaBoolean(valores[2], "O laptop é bivolt?");
        boolean mecanico = retornaBoolean(valores[4], "É mecânico?");

        return new Teclado(valores[0], massa, bivolt, valores[3], mecanico);
    }

    private boolean intValido(String s) {
        try {
            Integer.parseInt(s); // Tenta converter string em inteiro
            return true;
        } catch (NumberFormatException e) { // Captura o erro caso formato da string não esteja correto
            return false;
        }
    }

    public int retornaInteiro(String entrada) { // retorna um valor inteiro
        // Enquanto o usuário não digitar um valor inteiro não sai do loop
        while (!this.intValido(entrada)) {
            entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
        }
        return Integer.parseInt(entrada);
    }

    private boolean doubleValido(String s) {
        try {
            Double.parseDouble(s); // Tenta converter string em double
            return true;
        } catch (NumberFormatException e) { // Captura o erro caso formato da string não esteja correto
            return false;
        }
    }

    public double retornaDouble(String entrada) { // retorna um valor double
        // Enquanto o usuário não digitar um valor double não sai do loop
        while (!this.doubleValido(entrada)) {
            entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número.");
        }
        return Double.parseDouble(entrada);
    }

    public boolean retornaBoolean(String entrada, String mensagem) {
        if (entrada.equals("Sim") || entrada.equals("Não")) {
            return entrada.equals("Sim");
        } else {
            Object[] options = { "Sim", "Não" };
            int resposta = JOptionPane.showOptionDialog(null, mensagem, "Valor incorreto", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            return resposta == 0;
        }
    }

    public void salvaEletronicos (ArrayList<Eletronico> eletronicos){
        ObjectOutputStream outputStream = null;

        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("eletronicos.dados"));

            for (Eletronico eletronico : eletronicos) {
                outputStream.writeObject(eletronico);
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Impossível criar arquivo!");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {  //Encerra o ObjectOutputStream
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ArrayList<Eletronico> recuperaEletronicos (){
        ArrayList<Eletronico> eletronicosTemp = new ArrayList<>();

        ObjectInputStream inputStream = null;

        try {
            inputStream = new ObjectInputStream(new FileInputStream("corpos.dados"));
            Object obj;

            while ((obj = inputStream.readObject()) != null) {
                if (obj instanceof Eletronico) {
                    eletronicosTemp.add((Eletronico) obj);
                }
            }
        } catch (EOFException ex) { // ao chegar no caracter EOF
            System.out.println("Fim de arquivo.");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Arquivo com eletrônicos não existe!");
            ex.printStackTrace();
        }
         catch (ClassNotFoundException | IOException ex) {
             ex.printStackTrace();
         } finally {  //Encerra o ObjectInputStream
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
        return eletronicosTemp;
    }

    public void menuEletronicos (){

        String menu;
        String entrada;
        int    opc1, opc2;

        do {
            menu = """
                    Loja de Eletrônicos
                    Opções:
                    1. Entrar Eletrônico
                    2. Exibir Eletrônico
                    3. Limpar Eletrônico
                    4. Gravar Eletrônico
                    5. Recuperar Eletrônico
                    9. Sair""";

            entrada = JOptionPane.showInputDialog (menu + "\n\n");
            opc1 = this.retornaInteiro(entrada);

            switch (opc1) {
                case 1:// Entrar dados
                    menu = """
                            Entrada de Eletrônicos
                            Opções:
                            1. Laptop
                            2. Televisão
                            3. Teclado""";

                    entrada = JOptionPane.showInputDialog (menu + "\n\n");
                    opc2 = this.retornaInteiro(entrada);

                    switch (opc2){
                        case 1: eletronicos.add(leLeptop());
                            break;
                        case 2: eletronicos.add(leTelevisao());
                            break;
                        case 3: eletronicos.add(leTeclado());
                            break;
                        default:
                            JOptionPane.showMessageDialog(null,"Eletrônico para entrada não escolhido!");
                    }

                    break;

                case 2: // Exibir dados
                    if (eletronicos.size() == 0) {
                        JOptionPane.showMessageDialog(null,"Não há eletrônicos em memória. Entre com eletrônicos primeiramente");
                        break;
                    }
                    String dados = "";
                    for (Eletronico eletronico : eletronicos)	{
                        dados += eletronico.toString() + "---------------\n";
                    }
                    JOptionPane.showMessageDialog(null,dados);
                    break;

                case 3: // Limpar Dados
                    if (eletronicos.size() == 0) {
                        JOptionPane.showMessageDialog(null,"Não há eletrônicos em memória. Entre com eletrônicos primeiramente");
                        break;
                    }
                    eletronicos.clear();
                    JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
                    break;

                case 4: // Grava Dados
                    if (eletronicos.size() == 0) {
                        JOptionPane.showMessageDialog(null,"Não há eletrônicos em memória. Entre com eletrônicos primeiramente");
                        break;
                    }
                    salvaEletronicos(eletronicos);
                    JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
                    break;

                case 5: // Recupera Dados
                    eletronicos = recuperaEletronicos();
                    if (eletronicos.size() == 0) {
                        JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
                        break;
                    }
                    JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
                    break;
                case 9:
                    JOptionPane.showMessageDialog(null,"Fim do aplicativo CONTROLE ESPACIAL");
                    break;
            }
        } while (opc1 != 9);
    }

    public static void main(String[] args) {
        LojaEletronicos loja = new LojaEletronicos();

        loja.menuEletronicos();
    }
}
