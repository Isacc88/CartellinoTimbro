
public class OreLavorate {
	public int giorno;		
	public String matricola;
	public int tempoLav;
	public double ore;
	
	public OreLavorate() {
		
	}
	public OreLavorate(int giorno, String matricola) {		
		this.giorno = giorno;
		this.matricola = matricola;
	}
	public OreLavorate(int giorno, String matricola, int tempoLav) {		
		this.giorno = giorno;
		this.matricola = matricola;
		this.tempoLav = tempoLav;
	}
	public int getGiorno() {
		return giorno;
	}
	public void setGiorno(int giorno) {
		this.giorno = giorno;
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
	public void setTempoLav(int d) {
		this.tempoLav = d;
	}
	public void setOre() {
		if(this.tempoLav>=60) {
			this.ore= Math.floor((this.tempoLav/60));
			this.tempoLav= this.tempoLav-(int)this.ore*60;
			}
		else this.ore = 0;		
	}
	public double getOre() {
		return tempoLav;
	}
	
}
