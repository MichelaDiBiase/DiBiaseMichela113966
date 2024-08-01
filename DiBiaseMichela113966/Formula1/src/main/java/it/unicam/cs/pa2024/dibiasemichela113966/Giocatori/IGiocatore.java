package it.unicam.cs.pa2024.dibiasemichela113966.Giocatori;

import it.unicam.cs.pa2024.dibiasemichela113966.MotoreDiGioco.Locazione;
import it.unicam.cs.pa2024.dibiasemichela113966.MotoreDiGioco.Macchina;
import it.unicam.cs.pa2024.dibiasemichela113966.TracciatoDiGara.TracciatoDiGara;

import java.util.ArrayList;

public interface IGiocatore {

    /**
     * Restituisce il nome del giocatore.
     * @return il nome del giocatore.
     */
    String getNome();

    /**
     * Restituisce il tracciato di gara in cui si trova il giocatore.
     * @return il tracciato di gara in cui si trova il giocatore.
     */
    TracciatoDiGara getTracciatoDiGara();

    /**
     * Scelta della macchina.
     * @param macchina macchina scelta dal giocatore.
     */
    void sceltaMacchina(Macchina macchina);

    /**
     * Restituisce la macchina associata al giocatore.
     * @return la macchina associata al giocatore.
     */
    Macchina getMacchina();

    /**
     * Restituisce l'insieme di posizioni in cui la macchina potrà spostarsi.
     * @return l'insieme di posizioni in cui la macchina potrà spostarsi.
     */
    ArrayList<Locazione> getProssimeMossePossibili();

    /**
     * Sposta la macchina in una delle posizioni passate come parametro.
     * @param prossimaPosizione prossima posizione scelta per la macchina.
     */
    void spostaMacchinaIn(Locazione prossimaPosizione) throws Exception;
}
