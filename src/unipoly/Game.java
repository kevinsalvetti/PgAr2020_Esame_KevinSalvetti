package unipoly;

public class Game {

	private final static String MESS_DASOLDI_INIZIO = "  ASPETTA MA SEI SENZA SOLDI :-(\nDAI OGGI MI SENTO GENTILE E TI REGALO ";
	private final static String MESS_BUONAFORTUNA = " BUONA FORTUNA";

	private final static String MESS_BYE = "GRAZIE PER GIOCATO\nBYE BYE ";
	private final static String TIRA_DADO = "VUOI LANCIARE IL DADO (S PER CONTINUARE / N PER TERMINARE IL GIOCO)";
	private final static String BILANCIO = " IL TUO BILANCIO ATTUALE E' DI : \t";
	private final static String MESS_WIN = "!!!!!!!!!!!!!!!!!!HAI VINTO :-D!!!!!!!!!!!!!!!!!!";
	private final static String MESS_LOSE = ":_( HAI PERSO :_(\tRIPROVA LA POSSIMA VOLTA ;-D";
	private final static String MESS_CAS_INIZIALE = "SEI SULLA CASELLA INIZIALE";
	private final static String MESS_CAS_PROBABILITA = "SEI SU UNA CASELLA PROBABILITA :-)\nHAI GUADAGNATO = \t";
	private final static String MESS_CAS_IMPREVISTO = "SEI SU UNA CASELLA IMPREVISTO :-(\nHAI PERSO = \t";
	private final static String MESS_CAS_STAZIONE = "SEI SU UNA CASELLA STAZIONE, Vuoi spostarti in una nuova stazione? ";

	// numero di caselle nel tabellone
	private final static Integer NUM_CASELLE_TAB = 12;

	private final static String LUCKY_PRISON = "PER FORTUNA HAI RACCOLTO IL TICKET, ESCI DI PRIGIONE GRATIS\n";
	private final static String BAD_PRISON = "SFORTUNATAMENTO SEI FINITO IN PRIGIONE\n";
	private final static String PAY_TO_FREEPT1 = "VUOI PAGARE ";
	private final static String PAY_TO_FREEPT2 = " PER USCIRE DI PRIGIONE";
	private final static String PAY_TO_FREEPT3 = "HAI IL PORTAFOGLIO PIU LEGGERO, MA SEI LIBERO";
	private final static String NOT_PAY_TO_FREE = "PER ESSERE LIBERO DEVI AVERE FORTUNA:\n*SE ESCONO DUE NUMERI UGUALI SEI LIBERO ALTRIMENTI DEVI PAGARE 1/3 (DEL NUMERO DEL PRIMO DADO) + 1/4 (DEL NUMERO DEL SECONDO DADO)";
	private final static Integer CAUSALE = 20000;

	private final static Integer MIN_ESTRAZIONE = 1;
	private final static Integer MAX_ESTRAZIONE = 1000000;

	private final static String FREE_NOT_PAY = "DOPO 3 TURNI SEI CONDONATO\nSEI LIBERO";

	// private final static Integer NUM_TIRI_FOR_PRISON = 3; // corretto
	private final static Integer NUM_TIRI_FOR_PRISON = 0;
	private final static String CASELLA_PRIGIONE = "OHHH NOOO MA CHE SFORTUNA SEI ARRIVATO SULLA CASELLA PRIGIONE";
	private final static String CASELLA_TICKET = "MA CHE FORTUNA SEI ARRIVATO SULLA CASELLA TICKET\nECCO PER TE UN TICKET PER USCIRE DI PRIGIONE";
	private final static String CASELLA_CORRENTE = "SEI ARRIVATO SULLA CASELLA NUMERO ";
	private final static String CASELLA_PROPIETA = "SEI ARRIVATO SULLA CASELLA DELL PROPIETA, VUOI ACQUISTARE DELLE PROPIETA?";
	private final static String COMPRO_CASA = "PREMI 1 PER COMPRARE UNA CASA SU QUESTA PROPIETA? [COSTO]";
	private final static Integer COSTO_CASA = 20000;

	private final static String COMPRO_ALBERGO = "PREMI 2 PER COMPRARE UNA ALBERGO SU QUESTA PROPIETA? [COSTO]";
	private final static Integer COSTO_ALBERGO = 80000;
	private final static String RETRIBUZIONE_PROPIETA = "ESSENDE SU UNA TUA PROPIETA TI ASPETTA UNA RETRIBUZIONE DI: ";
	private final static Integer RETRIBUZIONE_CASA = 6000;
	private final static Integer RETRIBUZIONE_ALBERGO = 10000;

	int posizioneCasella = 0;
	int countPrigione = 0;
	int countNotPayToFree = 0;
	boolean exit = false;

	public void StartGame() {

	
		Giocatore giocatore = new Giocatore(null, null, false, false, false, false, null, null);
		giocatore.NomeGiocatore();
		giocatore.SoldiGiocatore();

		System.out.println(giocatore.getNome() + MESS_DASOLDI_INIZIO + giocatore.getSoldi() + "$$" + MESS_BUONAFORTUNA);

		// Creazione tabellone
		Tabellone tab = new Tabellone();
		tab.CreazioneTabellone();

		do {
			// se giocatore propietario di albergo / casa  sulla posizione corrente--> gli viene dato una retribuzione
			if (giocatore.isPropietarioAlbergo()) {
				System.out.println(giocatore.getCompratoAlbergo());
				System.out.println(posizioneCasella);
				
				if (giocatore.getCompratoAlbergo().equals(posizioneCasella)) {

					System.out.println(RETRIBUZIONE_PROPIETA + RETRIBUZIONE_ALBERGO);
					giocatore.AggiuntaSoldi(RETRIBUZIONE_ALBERGO);
				}
			} 
			if(giocatore.isPropietarioCasa()) {
				if (giocatore.getCompratoCasa().equals(posizioneCasella)) {
					
					System.out.println(RETRIBUZIONE_PROPIETA + RETRIBUZIONE_CASA);
					giocatore.AggiuntaSoldi(RETRIBUZIONE_CASA);

				}
			}
			
			if (mylib.InputDati.yesOrNo(TIRA_DADO)) {

				// tira il Dado
				
				  int estrazione1 = Dado.TiraDado(); int estrazione2 = Dado.TiraDado();
				 

				System.out.println("è uscito il numero " + estrazione1 + "\t" + estrazione2);

				// controllo se dopo 3 tiri consecutivi giocatore vai in prigione
				if (estrazione1 == estrazione2) {
					countPrigione++;
				}

				if (countPrigione >= NUM_TIRI_FOR_PRISON) {

					// posizione esatta della casella
					posizioneCasella = posizioneCasella + (estrazione1 + estrazione2);

					if (posizioneCasella >= NUM_CASELLE_TAB) {
						posizioneCasella = posizioneCasella - NUM_CASELLE_TAB;
					}

					System.out.println(CASELLA_CORRENTE + posizioneCasella);

					// Dare soldi se giocatore ha una propieta / albergo - casa

					// Effetto Caselle

					if (tab.ArrayCasella.get(posizioneCasella).equals("CasellaIniziale")) {
						System.out.println(MESS_CAS_INIZIALE);
					} else if (tab.ArrayCasella.get(posizioneCasella).equals("Probabilita")) {
						int guadagno = mylib.EstrazioniCasuali.estraiIntero(MIN_ESTRAZIONE, MAX_ESTRAZIONE);
						giocatore.AggiuntaSoldi(guadagno);
						System.out.println(MESS_CAS_PROBABILITA + guadagno);

					} else if (tab.ArrayCasella.get(posizioneCasella).equals("Stazione")) {
						System.out.println(MESS_CAS_STAZIONE);
						boolean sceltaStazione = mylib.InputDati.yesOrNo("");

						if (sceltaStazione) {
							posizioneCasella = tab.TrovaStazione(posizioneCasella);
						}
						System.out.println(CASELLA_CORRENTE + posizioneCasella);

					} else if (tab.ArrayCasella.get(posizioneCasella).equals("Imprevisto")) {

						int perdita = mylib.EstrazioniCasuali.estraiIntero(MIN_ESTRAZIONE, MAX_ESTRAZIONE);
						giocatore.TogliSoldi(perdita);
						System.out.println(MESS_CAS_IMPREVISTO + perdita);
					} else if (tab.ArrayCasella.get(posizioneCasella).equals("Prigione")) {
						System.out.println(CASELLA_PRIGIONE);
						giocatore.setImprigionato(true);
						prigione(giocatore);
						giocatore.setImprigionato(false);

					} else if (tab.ArrayCasella.get(posizioneCasella).equals("Ticket")) {
						System.out.println(CASELLA_TICKET);
						giocatore.setTicket(true);

					} else if (tab.ArrayCasella.get(posizioneCasella).equals("Propieta")) {
						// scelta se comprare o meno
						if (mylib.InputDati.yesOrNo(CASELLA_PROPIETA)) {

							System.out.println(COMPRO_CASA + COSTO_CASA + "\n" + COMPRO_ALBERGO + COSTO_ALBERGO);
							int scelta = mylib.InputDati.leggiIntero("", 0, 3);

							switch (scelta) {
							// compra casa
							case 1:
								giocatore.setPropietarioCasa(true);
								giocatore.setCompratoCasa(posizioneCasella);
								giocatore.TogliSoldi(COSTO_CASA);

								break;
							// compra albergp
							case 2:
								giocatore.setPropietarioAlbergo(true);
								giocatore.setCompratoAlbergo(posizioneCasella);
								giocatore.TogliSoldi(COSTO_ALBERGO);

								break;
							}
						}

					}

					System.out.println(giocatore.getNome() + BILANCIO + giocatore.getSoldi());

				} else {

					countPrigione = 0;

					if (giocatore.isTicket() == true) {

						giocatore.setImprigionato(false);
						giocatore.setTicket(false);

						System.out.println(LUCKY_PRISON);
					} else {
						giocatore.setImprigionato(true);

						System.out.println(BAD_PRISON);

						prigione(giocatore);

						giocatore.setImprigionato(false);
					}

				}
				// scelta no
			} else {
				exit = true;
			}

		} while (giocatore.Win() == false && giocatore.Lose() == false && exit == false);

		// controllo vincita e perdita
		if (giocatore.Win()) {
			System.out.println(MESS_WIN);
		} else if (giocatore.Lose()) {
			System.out.println(MESS_LOSE);
		} else {
			System.out.println(MESS_BYE);
		}
		
	}

	private void prigione(Giocatore giocatore) {

		do {
			System.out.println(BILANCIO + giocatore.getSoldi());
			if (mylib.InputDati.yesOrNo(PAY_TO_FREEPT1 + CAUSALE + PAY_TO_FREEPT2)) {

				giocatore.TogliSoldi(CAUSALE);

				giocatore.setImprigionato(false);

				System.out.println(PAY_TO_FREEPT3);
			} else {

				countNotPayToFree++;

				if (countNotPayToFree >= 3) {
					giocatore.setImprigionato(false);
					System.out.println(FREE_NOT_PAY);
				} else {
					System.out.println(NOT_PAY_TO_FREE);

					int estrazione3 = Dado.TiraDado();
					int estrazione4 = Dado.TiraDado();

					System.out.println("è uscito il numero " + estrazione3 + "\t" + estrazione4);

					System.out.println();
					if (estrazione3 == estrazione4) {
						giocatore.setImprigionato(false);
					} else {
						giocatore.TogliSoldi(((1 % 3) * estrazione3) + ((1 % 4) * estrazione4));
					}

				}

			}

		} while (giocatore.isImprigionato());
	}

}
