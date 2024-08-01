package it.unicam.cs.pa2024.dibiasemichela113966.TracciatoDiGara;

import it.unicam.cs.pa2024.dibiasemichela113966.MotoreDiGioco.Locazione;
import it.unicam.cs.pa2024.dibiasemichela113966.MotoreDiGioco.Macchina;

import java.util.ArrayList;

public interface ITracciatoDiGara {
    int getAltezza();
    int getLunghezza();

    /**
     * Aggiunge ad un ArrayList le coordinate del punto di inizio della gara
     * @return un ArrayList di tipo Locazione con tutte le coordinate di inizio gara in cui posizionare la macchina
     */
    ArrayList<Locazione> getPosizioniIniziali();

    /**
     * Aggiunge ad un ArrayList le coordinate del traguardo della gara
     * @return un ArrayList di tipo Locazione con tutte le coordinate del traguardo
     */
    ArrayList<Locazione> getTraguardo();

    /**
     * Informa della posizione della macchina rispetto al tracciato
     * @param posizione la posizione da controllare
     * @return 0 se la macchina è andata fuori strada, 1 se è rimasta in gara, 2 se è arrivata al traguardo, 3 se è alla posizione iniziale
     */
    int getTipologiaLocazione(Locazione posizione);

    /**
     * Inserisce la macchina in gara
     */
    void inserisciMacchina(Macchina macchina);

    /**
     * Sposta la macchina nella prossima Locazione
     * @param prossimaPosizione in cui la macchina si sposta
     */
    void spostaMacchina(Locazione prossimaPosizione, Macchina macchina);

    /**
     * Restituisce il numero a cui corrisponde la macchina nel circuito
     * @return il numero della macchina
     */
    int getNumeroMacchina(Macchina macchina);

    /**
     * Controlla se la posizione è occupata da una macchina oppure se è fuori dal circuito
     * @return true se la posizione è occupata, false altrimenti
     */
    boolean controlloPosizione(Locazione locazione);
}
