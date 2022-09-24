
import java.text.ParseException;
public class Timbro {
	public int giorno;		
	public int ore;
	public int minuti;
	public String matricola;
	public int tempoLav;
	public boolean entrata;
	
	public Timbro(int giorno, int ore,int minuti, String matricola) {	
		this.giorno = giorno;		
		this.ore=ore;
		this.minuti=minuti;
		this.matricola = matricola;
		this.entrata=true;
	}
	
	
	
	public int getGiorno() {
		return giorno;
	}

	public void setGiorno(int giorno) {
		this.giorno = giorno;
	}

	

	public int getOre() {
		return ore;
	}



	public void setOre(int ore) {
		this.ore = ore;
	}



	public int getMinuti() {
		return minuti;
	}



	public void setMinuti(int minuti) {
		this.minuti = minuti;
	}



	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public double getTempoLav() {
		return tempoLav;
	}

	public void setTempoLav(int tempoLav) {
		this.tempoLav = tempoLav;
	}

	public Timbro() {		
	}
	
	public Timbro(String riga) throws ParseException {
		String []timbro= riga.split(",");		
		String[] orario= new String [2];
		orario= timbro[1].split("\\.");
		this.giorno = Integer.parseInt(timbro[0]);
		this.ore = Integer.parseInt(orario[0]);
		this.minuti= Integer.parseInt(orario[1]);
		this.matricola = timbro[2];
		this.entrata=true;
						
	}



	public boolean getEntrata() {
		return entrata;
	}



	public void setEntrata(boolean entrata) {
		this.entrata = entrata;
	}
	
	
	
}
