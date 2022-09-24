import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList; 
public class LetturaFile{
	
	
	public static ArrayList<String> leggiFile (String orari) throws FileNotFoundException, IOException{
			
		BufferedReader reader = new BufferedReader(new FileReader(orari));
		String line = reader.readLine();
		
		ArrayList<String> righe = new ArrayList<String>();
		while(line!=null) {
			righe.add(line);
			line = reader.readLine();
	        }
		reader.close();
		return righe;	
		
	}
	
	public static ArrayList<String[]> separaString (ArrayList<String> rigaUnita) {
		ArrayList<String[]> separate = new ArrayList<String[]>();
		
		for (int i=0; i<rigaUnita.size();i++) {
			separate.add(rigaUnita.get(i).split(","));
		}
		return separate;	
	}
	public static ArrayList<String[]> creaTimbro(String file)throws FileNotFoundException, IOException {
	
		return separaString(leggiFile(file));
	}
	
}
	