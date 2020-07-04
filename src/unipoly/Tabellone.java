package unipoly;

import java.util.ArrayList;

public class Tabellone {

	public ArrayList<String> ArrayCasella = new ArrayList<String>();
	int posizioneStazione = 0;

	public void CreazioneTabellone() {

		// 1 casella = casella iniziale

		ArrayCasella.add("CasellaIniziale");
		ArrayCasella.add("Imprevisto");
		ArrayCasella.add("Stazione");
		ArrayCasella.add("Propieta");	
		ArrayCasella.add("Ticket");
		ArrayCasella.add("Imprevisto");
		ArrayCasella.add("Imprevisto");
		ArrayCasella.add("Probabilita");
		ArrayCasella.add("Propieta");	
		ArrayCasella.add("Stazione");
		ArrayCasella.add("Probabilita");
		ArrayCasella.add("Prigione");
		ArrayCasella.add("Probabilita");
		ArrayCasella.add("Imprevisto");

	}

	public int TrovaStazione(int k) {

		k++;
		posizioneStazione= 0;
		
		for (int i = k; i < ArrayCasella.size(); i++) {

			if (ArrayCasella.get(i).contains("Stazione")) {
				posizioneStazione = i;
				
			}
		}
		if (posizioneStazione == 0) {
			for (int i = 0; i < ArrayCasella.size(); i++) {

				if (ArrayCasella.get(i).contains("Stazione")) {
					posizioneStazione = i;
					break;
				}
			}
		}
		return posizioneStazione;
	}

	

}
