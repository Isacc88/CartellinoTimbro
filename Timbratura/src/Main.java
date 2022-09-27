import java.io.IOException;
import java.util.ArrayList;
import java.text.ParseException;
import java.time.LocalTime;

public class Main {

	public static void main(String[] args) {

		// leggo il file e creo in arraylist di stringhe pari alle righe

		ArrayList<String> leggo = new ArrayList<String>();
		try {
			leggo = OperazioniFile.leggiFile(
					"C:\\Users\\Proprietario\\OneDrive\\Desktop\\github\\timbratura\\Timbratura\\src\\orari.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// creo un array di timbri e lo popolo tramite l'arraylist di stringhe
		ArrayList<Timbro> listaTimbrature = new ArrayList<Timbro>();
		for (int i = 0; i < leggo.size(); i++) {
			try {
				Timbro x = new Timbro(leggo.get(i));
				listaTimbrature.add(x);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// test
//		for (int i = 0; i < listaTimbrature.size(); i++) {
//			System.out.println("il giorno:" + listaTimbrature.get(i).getData() + " la matricola:"
//					+ listaTimbrature.get(i).getMatricola() + " ha timbrato l'entrata? "
//					+ listaTimbrature.get(i).getEntrata() + " alle ore:" + listaTimbrature.get(i).getOrario());
//		}

		// metto entrate e uscite

		for (int i = 0; i < listaTimbrature.size(); i++) {
			for (int j = i + 1; j < listaTimbrature.size(); j++)
				if (listaTimbrature.get(i).getMatricola().equals(listaTimbrature.get(j).getMatricola())
						&& listaTimbrature.get(i).getEntrata() == true) {
					listaTimbrature.get(j).setEntrata(false);
					break;
				}
		}
		// test per verificare di aver creato l'array bene
//		for (int i = 0; i < listaTimbrature.size(); i++) {
//			System.out.println("il giorno:" + listaTimbrature.get(i).getData() + " la matricola:"
//					+ listaTimbrature.get(i).getMatricola() + " ha timbrato l'entrata? "
//					+ listaTimbrature.get(i).getEntrata() + " alle ore:" + listaTimbrature.get(i).getOrario());
//		}	

		// calcolo i tempi di lavoro lo straordinario e le ore mancanti per ogni turno

		ArrayList<OreLavorate> listaOre = new ArrayList<OreLavorate>();
		for (int i = 0; i < listaTimbrature.size(); i++) {
			OreLavorate y = new OreLavorate();

			for (int j = i; j < listaTimbrature.size(); j++)

				if (listaTimbrature.get(i).getMatricola().equals(listaTimbrature.get(j).getMatricola())
						&& listaTimbrature.get(i).getData().equals(listaTimbrature.get(j).getData())) {

					if (listaTimbrature.get(i).getEntrata() == true && listaTimbrature.get(j).getEntrata() == false) {
						if (listaTimbrature.get(i).getOrario().getHour() < 8) {
							y.setOreExtra(7 - listaTimbrature.get(i).getOrario().getHour());
							y.setMinutiExtra(60 - (listaTimbrature.get(i).getOrario().getMinute()
									+ (15 - listaTimbrature.get(i).getOrario().getMinute() % 15)));
							listaTimbrature.get(i).setOrario(LocalTime.of(8, 0, 0));
						} else if (listaTimbrature.get(i).getOrario().getHour() >= 8
								&& listaTimbrature.get(i).getOrario().getHour() < 12) {
							y.setOreMancanti(listaTimbrature.get(i).getOrario().getHour() - 8);
							y.setMinutiMancanti((listaTimbrature.get(i).getOrario().getMinute()
									+ (30 - listaTimbrature.get(i).getOrario().getMinute() % 30)));
							listaTimbrature.get(i).setOrario(LocalTime.of(8, 0, 0));
						} else if (listaTimbrature.get(i).getOrario().getHour() < 14
								&& listaTimbrature.get(i).getOrario().getHour() >= 12) {
							y.setOreExtra(13 - listaTimbrature.get(i).getOrario().getHour());
							y.setMinutiExtra(60 - (listaTimbrature.get(i).getOrario().getMinute()
									+ (15 - listaTimbrature.get(i).getOrario().getMinute() % 15)));
							listaTimbrature.get(i).setOrario(LocalTime.of(14, 0, 0));
						} else if (listaTimbrature.get(i).getOrario().getHour() >= 14) {
							y.setOreMancanti(listaTimbrature.get(i).getOrario().getHour() - 14);
							y.setMinutiMancanti((listaTimbrature.get(i).getOrario().getMinute()
									+ (30 - listaTimbrature.get(i).getOrario().getMinute() % 30)));
							listaTimbrature.get(i).setOrario(LocalTime.of(14, 0, 0));
						}
						if (listaTimbrature.get(j).getOrario().getHour() >= 12
								&& listaTimbrature.get(j).getOrario().getHour() < 14) {
							y.setOreExtra(y.getOreExtra() + listaTimbrature.get(j).getOrario().getHour() - 12);
							y.setMinutiExtra((y.getMinutiExtra() + listaTimbrature.get(j).getOrario().getMinute()
									- (listaTimbrature.get(j).getOrario().getMinute() % 15)));
							listaTimbrature.get(j).setOrario(LocalTime.of(12, 0, 0));
						} else if (listaTimbrature.get(j).getOrario().getHour() < 12) {
							y.setOreMancanti(y.getOreMancanti() + 11 - listaTimbrature.get(j).getOrario().getHour());
							y.setMinutiMancanti(
									y.getMinutiMancanti() + 60 - (listaTimbrature.get(j).getOrario().getMinute()
											- (listaTimbrature.get(j).getOrario().getMinute() % 30)));
							listaTimbrature.get(j).setOrario(LocalTime.of(12, 0, 0));
						} else if (listaTimbrature.get(j).getOrario().getHour() >= 18) {
							y.setOreExtra(y.getOreExtra() + listaTimbrature.get(j).getOrario().getHour() - 18);
							y.setMinutiExtra((y.getMinutiExtra() + listaTimbrature.get(j).getOrario().getMinute()
									- (listaTimbrature.get(j).getOrario().getMinute() % 15)));
							listaTimbrature.get(j).setOrario(LocalTime.of(18, 0, 0));
						} else if (listaTimbrature.get(j).getOrario().getHour() < 18
								&& listaTimbrature.get(j).getOrario().getHour() >= 14) {
							y.setOreMancanti(y.getOreMancanti() + 17 - listaTimbrature.get(j).getOrario().getHour());
							y.setMinutiMancanti(
									y.getMinutiMancanti() + 60 - (listaTimbrature.get(j).getOrario().getMinute()
											- (listaTimbrature.get(j).getOrario().getMinute() % 30)));
							listaTimbrature.get(j).setOrario(LocalTime.of(18, 0, 0));
						}
						y.setData(listaTimbrature.get(i).getData());
						y.setMatricola(listaTimbrature.get(i).getMatricola());
						y.setOre((listaTimbrature.get(j).getOrario().getHour()
								- listaTimbrature.get(i).getOrario().getHour()));
						y.setMinuti((listaTimbrature.get(j).getOrario().getMinute()
								- listaTimbrature.get(i).getOrario().getMinute()));
						y.transmute();
						listaOre.add(y);
						break;
					}
				}
		}
		// test
//		for (int i = 0; i < listaOre.size(); i++) {
//			System.out.println("il giorno:" + listaOre.get(i).data + " la matricola:" + listaOre.get(i).matricola
//					+ " ha lavorato:" + listaOre.get(i).getOre() + " ore " + listaOre.get(i).getMinuti() + " minuti e "
//					+ listaOre.get(i).getOreExtra() + " ore e " + listaOre.get(i).getMinutiExtra()
//					+ " minuti di straordinario e ha fatto " + listaOre.get(i).getOreMancanti() + " ore e "
//					+ listaOre.get(i).getMinutiMancanti() + " minuti di assenza");
//		}

		// unisco i tempi di lavoro delle varie matricole

		ArrayList<OreLavorate> tempoTotxGiorno = new ArrayList<OreLavorate>();
		for (int i = 0; i < listaOre.size(); i++) {
			OreLavorate totali = new OreLavorate();
			totali = listaOre.get(i);
			for (int j = i + 1; j < listaOre.size(); j++) {
				if (listaOre.get(i) != null && listaOre.get(j) != null) {
					if (listaOre.get(i).getMatricola().equals(listaOre.get(j).getMatricola())) {
						totali.setOre((int) (totali.getOre() + listaOre.get(j).getOre()));
						totali.setOreExtra((int) (totali.getOreExtra() + listaOre.get(j).getOreExtra()));
						totali.setOreMancanti((int) (totali.getOreMancanti() + listaOre.get(j).getOreMancanti()));
						totali.setMinuti((int) (totali.getMinuti() + listaOre.get(j).getMinuti()));
						totali.setMinutiExtra((int) (totali.getMinutiExtra() + listaOre.get(j).getMinutiExtra()));
						totali.setMinutiMancanti(
								(int) (totali.getMinutiMancanti() + listaOre.get(j).getMinutiMancanti()));
						totali.transmute();
						listaOre.set(j, null);// cancello via via
					}
				}
			}
			if (totali != null) {
				tempoTotxGiorno.add(totali);
			}
		}
		// test
		for (int i = 0; i < tempoTotxGiorno.size(); i++) {
			System.out.println(" la matricola:" + tempoTotxGiorno.get(i).matricola + " ha lavorato:"
					+ tempoTotxGiorno.get(i).getOre() + " ore " + tempoTotxGiorno.get(i).getMinuti() + " minuti e "
					+ tempoTotxGiorno.get(i).getOreExtra() + " ore e " + tempoTotxGiorno.get(i).getMinutiExtra()
					+ " minuti di straordinario e ha fatto " + tempoTotxGiorno.get(i).getOreMancanti() + " ore e "
					+ tempoTotxGiorno.get(i).getMinutiMancanti() + " minuti di assenza questo mese");
		}
		try {
			OperazioniFile.scriviFile("orelavorate", tempoTotxGiorno);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
