import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ManipuladorArquivo {
	public void leescreve() {
		new Animacao();

		try {
			RandomAccessFile arquivo = new RandomAccessFile(
					"H:\\GAME_DON'T_TOUCH_ME\\src\\Recordes\\Recorde.txt", "rw");

			System.out.println("Recorde:  " + Animacao.getRecorde());
			arquivo.writeInt((int) Animacao.getRecorde());

			arquivo.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println(fnfe.getMessage());
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}

		try {
			RandomAccessFile arquivo = new RandomAccessFile(
					"H:\\GAME_DON'T_TOUCH_ME\\src\\Recordes\\Recorde.txt", "r");

			int tam = (int) (arquivo.length() / 4);
			for (int i = 0; i < tam; i++) {
				// Animacao.setRecorde(arquivo.readInt());
				System.out.println(arquivo.readInt());
			}

			arquivo.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println(fnfe.getMessage());
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

}
