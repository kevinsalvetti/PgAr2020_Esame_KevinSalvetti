package unipoly;

import mylib.*;

public class MainUnipoly {

	private final static String MESS_BENVENUTO = "BENVENUTO NEL ROGRAMMA UNIPOLY \n";
	private final static String MESS_RULES = "LO SCOPO DEL GIOCO E' DI DIVENTARE RICCHI :-)\nSE RIESCI AD ARRIVARE AD $$ 1.000.000 $$ HAI VINTO\nOCCHIO CHE NON è COSI FACILE ;-)";
	private final static String CHOOSE_OPTIONS = "Scegli cosa fare";
	private final static String[] MAIN_MENU_ITEMS = { "INIZIA IL GIOCO" };
	private final static String ERROR_MESSAGE = "ATTENZIONE INPUT NON VALIDO";
	private final static String MESS_BYE = "GRAZIE PER GIOCATO\nBYE BYE ";

	public static void main(String[] args) {

		System.out.println(MESS_BENVENUTO + "\n" + MESS_RULES + "\n");

		MyMenu principale = new MyMenu(CHOOSE_OPTIONS, MAIN_MENU_ITEMS);
		boolean fine = false;

		do {

			int voceSelezionata = principale.scegli();

			switch (voceSelezionata) {

			case 0:
				fine = true;
				System.out.println(MESS_BYE);
				break;

			case 1:

				Game gioco = new Game();

				gioco.StartGame();

				break;

			default:
				System.out.println(ERROR_MESSAGE);

				break;

			}

		} while (!fine);

	}

}
