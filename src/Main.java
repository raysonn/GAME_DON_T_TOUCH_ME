
public class Main {

    private static Painel painel;
    private static Temporizador temporizador;

    public static void main(String args[]) {
        painel = new Painel();
        temporizador = new Temporizador();
        painel.quadro();
        temporizador.run();
    }
}