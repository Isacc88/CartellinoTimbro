
import java.text.ParseException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.text.DateFormatter;

public class Timbro {
	private LocalDate data;
	private LocalTime orario;
	private String matricola;
	private boolean entrata;
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public Timbro(LocalDate data, LocalTime orario, String matricola) {
		this.data = data;
		this.orario = orario;
		this.matricola = matricola;
		this.entrata = true;		
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public Timbro() {
	}

	public Timbro(String riga) throws ParseException {
		String[] timbro = riga.split(" ");
		this.data = LocalDate.parse(timbro[0], formatter);
		this.orario = LocalTime.parse(timbro[1]);
		this.matricola = timbro[2];
		this.entrata = true;
	}

	public boolean getEntrata() {
		return entrata;
	}

	public void setEntrata(boolean entrata) {
		this.entrata = entrata;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getOrario() {
		return orario;
	}

	public void setOrario(LocalTime orario) {
		this.orario = orario;
	}

}
