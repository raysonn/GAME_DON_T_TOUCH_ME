import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Font;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class Animacao {

    private static int level = 0;
    private static int posicaoX = 1;
    private static int posicaoY = 1;
    private static int deslocamentoX = 25;
    private static int deslocamentoY = 10;
    private int width = 50;
    private int height = 50;
    private double porcentagem = 0;
    private static Painel painel;
    private static Temporizador temporizador;
    private static int X0 = -1, X1 = 1;
    private static int Y0 = -1, Y1 = 1;
    private static Image alien;
    private static Image terreno;
    private static Image menu;
    private static Image game_over;
    private static int recorde;
    private int deltaAE;
    private int A;

    public void move() {
        painel = new Painel();

        deltaAE = painel.getAcertos() - painel.getErros();

        if (painel.getAcertos() == 0) {
            level = 0;
            A = 10;
            gerador();
        }

        if (deltaAE >= 1 && deltaAE < 5) {
            level = 1;
            A = 10;
            gerador();
        }
        if (deltaAE >= 5 && deltaAE < 10) {
            level = 2;
            A = 20;
            gerador();
        }
        if (deltaAE >= 10 && deltaAE < 15) {
            level = 3;
            A = 30;
            gerador();
        }
        if (deltaAE >= 15 && deltaAE < 20) {
            level = 4;
            A = 40;
            gerador();
        }
        if (deltaAE >= 20 && deltaAE < 25) {
            level = 5;
            A = 50;
            gerador();
        }
        if (deltaAE >= 25 && deltaAE < 30) {
            level = 6;
            A = 60;
            gerador();
        }
        if (deltaAE >= 30 && deltaAE < 35) {
            level = 7;
            A = 70;
            gerador();
        }
        if (deltaAE >= 35 && deltaAE < 40) {
            level = 8;
            A = 80;
            gerador();
        }
        if (deltaAE >= 40 && deltaAE < 45) {
            level = 9;
            A = 90;
            gerador();
        }
        if (deltaAE >= 45 && deltaAE < 50) {
            level = 10;
            A = 100;
            gerador();
        }

        posicaoX = posicaoX + deslocamentoX;
        posicaoY = posicaoY + deslocamentoY;

        if ((posicaoX > 190) || (posicaoY > 190)) {
            deslocamentoX = X0;
            deslocamentoY = Y0;
        }
        if ((posicaoX < -280) || (posicaoY < -280)) {
            deslocamentoX = X1;
            deslocamentoY = Y1;
        }
        if (posicaoX > 190)
            posicaoX = 190;
        if (posicaoX < -280)
            posicaoX = -280;
        if (posicaoY > 190)
            posicaoY = 190;
        if (posicaoY < -280)
            posicaoY = -280;
    }

    public void gerador() {
        Random gerador = new Random();
        X0 = -gerador.nextInt(A) + A / 2;
        X1 = gerador.nextInt(A) + A / 2;
        Y0 = -gerador.nextInt(A) + A / 2;
        Y1 = gerador.nextInt(A) + A / 2;
    }

    public Animacao() {
        try {
            temporizador = new Temporizador();
            game_over = ImageIO.read(getClass().getResourceAsStream(
                    "/imagem/game_over.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // Dessa maneira as imagens serão importadas junto com o .JAR
            alien = ImageIO.read(getClass().getResourceAsStream(
                    "/imagem/alien.png"));
            // Desse jeito as imagens só aparecem pelo eclipse
            // alien = ImageIO.read(new
            // File("/GAME_DON'T_TOUCH_ME/src/imagem/alien.png"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            terreno = ImageIO.read(getClass().getResourceAsStream(
                    "/imagem/terreno.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            menu = ImageIO.read(getClass().getResourceAsStream(
                    "/imagem/menu.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics2D gr, Graphics g) {
        g.translate(300, 300);

        move();
        JButton botao = new JButton();
        painel.add(botao);
        porcentagem = painel.getAcertos() + painel.getErros();
        porcentagem = 100 * painel.getAcertos() / porcentagem;
        porcentagem = (int) (porcentagem);
        recorde = (int) (painel.getAcertos() * porcentagem);

        g.drawImage(terreno, -290, -290, null);
        g.drawImage(alien, posicaoX, posicaoY, null);

        // metodo para deixar as bordas dos desenhos mais arredondadas
        gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.BLACK);
        g.fill3DRect(250, -300, 240, 570, false);
        gr.setStroke(new BasicStroke(10.0f)); // LARGURA DOS PIXELS
        g.setFont(new Font("Monospaced", Font.BOLD, 20));
        g.drawRect(-290, -290, 540, 540);
        g.setColor(Color.WHITE);
        g.drawImage(menu, 250, -250, null);
        g.setColor(Color.BLACK);
        g.fillRect(270, -130, 200, 40);
        g.fillRect(270, -180, 200, 40);
        g.fillRect(270, -230, 200, 40);
        g.fillRect(270, -80, 200, 40);
        g.fillRect(270, 200, 200, 40);
        g.setColor(Color.WHITE);
        g.drawString("Maestria: " + porcentagem + "%", 280, -200);
        g.drawString("Erros: " + painel.getErros(), 280, -150);
        g.drawString("Acertos: " + painel.getAcertos(), 280, -100);
        g.drawString("Pontuação: " + painel.getAcertos() * porcentagem, 280,
                -50);
        g.setColor(Color.RED);
        g.setFont(new Font("Monospaced", Font.BOLD, 35));
        g.drawString("TEMPO: " + temporizador.getSegundo(), 280, 230);
        g.fillRect(250, -280, 230, 50);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Monospaced", Font.BOLD, 50));
        g.drawString("Level " + level, 260, -240);
        if (temporizador.getSegundo() <= 0) {
            g.drawImage(game_over, -330, -310, null);

        }

    }

    public static double getRecorde() {
        return recorde;
    }

    public static void setRecorde(int recorde) {
        Animacao.recorde = recorde;
    }

    public int getPosicaoX() {
        return posicaoX;
    }

    public void setPosicaoX(int d) {
        Animacao.posicaoX = d;
    }

    public int getPosicaoY() {
        return posicaoY;
    }

    public void setPosicaoY(int d) {
        Animacao.posicaoY = d;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
