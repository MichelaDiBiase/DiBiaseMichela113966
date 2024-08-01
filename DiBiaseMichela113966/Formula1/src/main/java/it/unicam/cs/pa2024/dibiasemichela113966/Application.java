package it.unicam.cs.pa2024.dibiasemichela113966;

import it.unicam.cs.pa2024.dibiasemichela113966.Giocatori.Bot;
import it.unicam.cs.pa2024.dibiasemichela113966.Giocatori.Utente;
import it.unicam.cs.pa2024.dibiasemichela113966.MotoreDiGioco.*;
import it.unicam.cs.pa2024.dibiasemichela113966.TracciatoDiGara.ReaderCircuito;
import it.unicam.cs.pa2024.dibiasemichela113966.TracciatoDiGara.TracciatoDiGara;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws Exception {
		ReaderCircuito r = new ReaderCircuito();
		TracciatoDiGara tracciato = new TracciatoDiGara(r.creazioneCircuito("circuitoUno.txt"));

		GestioneGara gara = new GestioneGara(tracciato);

		Macchina rosso = new Macchina(tracciato.getPosizioniIniziali().get(0), Colore.rosso);
		Macchina giallo = new Macchina(tracciato.getPosizioniIniziali().get(1), Colore.giallo);
		Macchina blu = new Macchina(tracciato.getPosizioniIniziali().get(2), Colore.blu);

		Scanner s = new Scanner(System.in);

		System.out.println("Giocherai con la macchina rossa(R), inserisci il nome e inizia a giocare!");
		String nome = s.nextLine();

		Utente g1 = new Utente(nome, tracciato, rosso);
		Bot b1 = new Bot("Bot1", tracciato, giallo);
		Bot b2 = new Bot("Bot2", tracciato, blu);

		tracciato.inserisciMacchina(rosso);
		tracciato.inserisciMacchina(blu);
		tracciato.inserisciMacchina(giallo);

		boolean finita = false;

		while(!finita) {
			if(gara.getRound() == 1) {
				tracciato.stampaCircuito();

				System.out.println("Scegli la coordinata in cui spostare la macchina (es:X-Y) :");
				String input = s.nextLine();
				String[] coordinate = input.split("-");

				Locazione nuovaPosizione = new Locazione(
						Integer.parseInt(coordinate[0]), Integer.parseInt(coordinate[1])
				);

				g1.spostaMacchinaIn(nuovaPosizione);
				gara.roundSuccessivo();
			} else if(gara.getRound() == 2) {
				b1.spostamentoMacchina();
				gara.roundSuccessivo();
			} else {
				b2.spostamentoMacchina();
				gara.roundSuccessivo();
			}

			if(gara.controlloVittoria(rosso) || gara.controlloVittoria(giallo) || gara.controlloVittoria(blu)) {
				finita = true;
			}

			if(g1.getMacchina().getStato().equals(StatoMacchina.fuori_gara)) {
				System.out.println("HAI PERSO");
				finita = true;
			}
		}
	}

}
