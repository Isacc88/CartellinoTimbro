import java.io.IOException;
import java.util.ArrayList;
import java.text.ParseException;
public class Main {

	public static void main(String[] args) {
		
		// leggo il file e creo in arraylist di stringhe pari alle righe
	
		ArrayList<String> leggo = new ArrayList<String>();
		try {
			leggo=LetturaFile.leggiFile("C:\\Users\\Proprietario\\eclipse-workspace\\Timbratura\\src\\orari.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// creo un array di timbri e lo popolo tramite l'arraylist di stringhe
		ArrayList<Timbro> listaTimbrature = new  ArrayList<Timbro>();		
		for (int i=0; i<leggo.size();i++) {
			try {
				Timbro x = new Timbro(leggo.get(i));
				listaTimbrature.add(x);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}	
		//test
//		for (int i=0; i<listaTimbrature.size();i++) {
//			System.out.println("il giorno:"+listaTimbrature.get(i).giorno+" la matricola:"+listaTimbrature.get(i).matricola+" ha timbrato l'entrata? "+listaTimbrature.get(i).getEntrata() +" alle ore:"+listaTimbrature.get(i).getOre()+" e "+listaTimbrature.get(i).getMinuti()+"minuti");
//		}
		
		//metto entrate e uscite
		
		for (int i=0; i<listaTimbrature.size();i++) {						
			for (int j=i+1; j<listaTimbrature.size();j++)				
				if(listaTimbrature.get(i).getMatricola().equals(listaTimbrature.get(j).getMatricola())&& listaTimbrature.get(i).getEntrata()==true) {
					listaTimbrature.get(j).setEntrata(false);
					break;
					}
				}
		//test per verificare di aver creato l'array bene
//		for (int i=0; i<listaTimbrature.size();i++) {
//			System.out.println("il giorno:"+listaTimbrature.get(i).giorno+" la matricola:"+listaTimbrature.get(i).matricola+" ha timbrato l'entrata? "+listaTimbrature.get(i).getEntrata() +" alle ore:"+listaTimbrature.get(i).getOre()+" e "+listaTimbrature.get(i).getMinuti()+"minuti");
//		}	
		
		// calcolo i tempi di lavoro 
		ArrayList<OreLavorate> listaOre = new  ArrayList<OreLavorate>();
		for (int i=0; i<listaTimbrature.size();i++) {			
			OreLavorate y= new OreLavorate();
			
			for (int j=i; j<listaTimbrature.size();j++)
				
				if(listaTimbrature.get(i).getMatricola().equals(listaTimbrature.get(j).getMatricola())&&listaTimbrature.get(i).getGiorno()==listaTimbrature.get(j).getGiorno()) {
					
					if (listaTimbrature.get(i).getEntrata()==true && listaTimbrature.get(j).getEntrata()==false ) {						
						y= new OreLavorate(listaTimbrature.get(i).getGiorno(),listaTimbrature.get(i).getMatricola(),((listaTimbrature.get(j).getOre()*60+listaTimbrature.get(j).getMinuti())-((listaTimbrature.get(i).getOre()*60+listaTimbrature.get(i).getMinuti()))));
						listaOre.add(y);
						break;
					}
				}			
		}
		//test
//		for (int i=0; i<listaOre.size();i++) {
//			System.out.println("il giorno:"+listaOre.get(i).giorno+" la matricola:"+listaOre.get(i).matricola+" ha lavorato:"+listaOre.get(i).getTempoLav()+"  minuti");
//		}
		
		//unisco i tempi di lavoro delle varie matricole giorno per giorno
		ArrayList<OreLavorate> tempoTotxGiorno=new  ArrayList<OreLavorate>();
		for (int i=0; i<listaOre.size();i++) {
			OreLavorate totali = new OreLavorate();
			totali=listaOre.get(i);
			for (int j=i+1; j<listaOre.size();j++) {
				if(listaOre.get(i)!=null&&listaOre.get(j)!=null) {
					if(listaOre.get(i).getGiorno()==listaOre.get(j).getGiorno()&&listaOre.get(i).getMatricola().equals(listaOre.get(j).getMatricola())) {						
						totali.setTempoLav((int) (totali.getTempoLav()+listaOre.get(j).getTempoLav()));		
						listaOre.set(j,null);//cancello via via 
					}
				}
			}
			if (totali!=null) {
			tempoTotxGiorno.add(totali);
			}
		}
		//test
		for (int i=0; i<tempoTotxGiorno.size();i++) {
			System.out.println("il giorno:"+tempoTotxGiorno.get(i).giorno+" la matricola:"+tempoTotxGiorno.get(i).matricola+" ha lavorato:"+tempoTotxGiorno.get(i).getTempoLav()+"  minuti");
			}		

		
	}

}
