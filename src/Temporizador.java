import javax.swing.JOptionPane;

public class Temporizador {
    private static Animacao animacao;
    private static Painel painel;
    private int centsegundo;
    private static int segundo = 30;
    private int aux = 1;

    void run() {
        animacao = new Animacao();
        painel = new Painel();
        while (true) {
            animacao.move();
            painel.repaint();
            try {
                Thread.sleep(50);
                centsegundo++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (centsegundo == 20) {
                segundo = segundo - aux;
                centsegundo = 0;
            }
            if (segundo <= 0) {
                segundo = 0;
                if (JOptionPane.showConfirmDialog(null,
                        "Deseja jogar novamente?", "GAME_OVER",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    segundo = 30;
                    painel.setAcertos(0);
                    painel.setErros(0);

                    // try {
                    // restartApp(); // Dessa maneira reinicia o
                    // aplicativo para reiniciar o jogo vulgo GAMBI
                    // } catch (IOException e) {
                    // //
                    // e.printStackTrace();
                    // }

                } else {
                    System.exit(0);
                }
            }
        }
    }

    // public void restartApp() throws IOException {
    // // new File("").getAbsolutePath();
    // // new ProcessBuilder("cmd",
    // // "/c start /min H:\\jogo.jar ^& exit").start();
    // File file = new File("TOUCH.jar");
    // String dirPath = file.getAbsoluteFile().getParentFile()
    // .getAbsolutePath();
    // assert dirPath.equals("H:\\TOUCH.jar");
    // System.out.println(dirPath);
    // new ProcessBuilder("cmd", "/c start /min " + dirPath
    // + "\\TOUCH.jar ^& exit").start();
    // System.exit(0);
    // }

    public int getSegundo() {
        return segundo;
    }

    public void setSegundo(int segundo) {
        Temporizador.segundo = segundo;
    }
}
