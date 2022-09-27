import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class OreLavorate {
	public LocalDate data;	
	public String matricola;	
	public int ore;
	public int minuti;
	public int oreExtra;
	public int minutiExtra;
	public int oreMancanti;
	public int minutiMancanti;
	
	public int getOreMancanti() {
		return oreMancanti;
	}
	public void setOreMancanti(int oreMancanti) {
		this.oreMancanti = oreMancanti;
	}
	public int getMinutiMancanti() {
		return minutiMancanti;
	}
	public void setMinutiMancanti(int minutiMancanti) {
		this.minutiMancanti = minutiMancanti;
	}
	public int getOreExtra() {
		return oreExtra;
	}
	public void setOreExtra(int oreExtra) {
		this.oreExtra = oreExtra;
	}
	public int getMinutiExtra() {
		return minutiExtra;
	}
	public void setMinutiExtra(int minutiExtra) {
		this.minutiExtra = minutiExtra;
	}
	public OreLavorate() {
		
	}
	public OreLavorate(LocalDate giorno, String matricola) {		
		this.data = giorno;
		this.matricola = matricola;
	}
	public OreLavorate(LocalDate data, String matricola, int ore, int minuti) {		
		this.data = data;
		this.matricola = matricola;
		this.ore = ore;
		this.minuti = minuti;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public String getMatricola() {
		return matricola;
	}
	public void setMatricola(String matricola) {
		this.matricola = matricola;
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
	public void transmute() {
		while (this.minutiExtra>=60) {
			this.oreExtra++;
			this.minutiExtra-=60;
		}
		while (this.minutiMancanti>=60) {
			this.oreMancanti++;
			this.minutiMancanti-=60;
		}
	}
	
}
