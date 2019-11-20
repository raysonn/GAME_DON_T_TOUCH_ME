import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Painel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static Animacao animacao;
	private static JFrame janela;
	private static Painel painel;
	private static Temporizador temporizador;
	private int x;
	private int y;
	private int initialX;
	private int initialY;
	private int finalX;
	private int finalY;
	private static int acertos = 0;
	private static int erros = 0;

	public void paint(Graphics g) {
		animacao = new Animacao();
		super.paint(g);
		Graphics2D gr = (Graphics2D) g;
		animacao.paint(gr, g);
		repaint();
	}

	public void quadro(Graphics2D gr, Graphics g){
		janela = new JFrame();
		painel = new Painel();
		painel.setFocusable(true);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		janela.setSize(800, 600);
		janela.add(painel);
		janela.setTitle("DON'T TOUCH ME 'GAME'");
		janela.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent click) {
				detectClick(click);
			}
		});
	}

	private void detectClick(MouseEvent click) {
		temporizador = new Temporizador();
		janela.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		x = click.getX() - 300;
		y = click.getY() - 300;
		initialX = animacao.getPosicaoX();
		initialY = animacao.getPosicaoY();
		finalX = 7 + initialX + animacao.getWidth();
		finalY = 25 + initialY + animacao.getHeight();
		janela.setTitle("D'ONT TOUCH ME 'GAME'" + " X: " + x + " Y: " + y);
		if ((x > initialX && x < finalX) && (y > initialY && y < finalY)) {
			acertos++;
			temporizador.setSegundo(temporizador.getSegundo()+1);
		}
		if ((x < initialX || x > finalX) || (y < initialY || y > finalY)) {
			erros++;
			temporizador.setSegundo(temporizador.getSegundo()-5);
		}
	}
	
	public int getAcertos() {
		return acertos;
	}

	public void setAcertos(int acertos) {
		Painel.acertos = acertos;
	}

	public int getErros() {
		return erros;
	}

	public void setErros(int erros) {
		Painel.erros = erros;
	}

}