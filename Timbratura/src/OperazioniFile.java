import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class OperazioniFile {

	public static ArrayList<String> leggiFile(String orari) throws FileNotFoundException, IOException {

		BufferedReader reader = new BufferedReader(new FileReader(orari));
		String line = reader.readLine();

		ArrayList<String> righe = new ArrayList<String>();
		while (line != null) {
			righe.add(line);
			line = reader.readLine();
		}
		reader.close();
		return righe;

	}

	public static void scriviFile(String nomeFile, String testo) throws FileNotFoundException, IOException {
		PrintWriter out = new PrintWriter(
				"C:\\Users\\Proprietario\\OneDrive\\Desktop\\github\\timbratura\\Timbratura\\src\\" + nomeFile);
		out.println(testo);
		out.close();
	}

	public static void scriviFile(String nomeFile, ArrayList<OreLavorate> testo)
			throws FileNotFoundException, IOException {
		PrintWriter out = new PrintWriter(
				"C:\\Users\\Proprietario\\OneDrive\\Desktop\\github\\timbratura\\Timbratura\\src\\" + nomeFile);

		for (int i = 0; i < testo.size(); i++) {
			out.println(" la matricola:" + testo.get(i).matricola + " ha lavorato:" + testo.get(i).getOre() + " ore "
					+ testo.get(i).getMinuti() + " minuti e " + testo.get(i).getOreExtra() + " ore e "
					+ testo.get(i).getMinutiExtra() + " minuti di straordinario e ha fatto "
					+ testo.get(i).getOreMancanti() + " ore e " + testo.get(i).getMinutiMancanti()
					+ " minuti di assenza questo mese");
		}
		
		out.close();
	}

	public static ArrayList<String[]> separaString(ArrayList<String> rigaUnita) {
		ArrayList<String[]> separate = new ArrayList<String[]>();

		for (int i = 0; i < rigaUnita.size(); i++) {
			separate.add(rigaUnita.get(i).split(","));
		}
		return separate;
	}

	public static ArrayList<String[]> creaTimbro(String file) throws FileNotFoundException, IOException {

		return separaString(leggiFile(file));
	}

}
