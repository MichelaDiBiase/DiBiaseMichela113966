package it.unicam.cs.pa2024.dibiasemichela113966.MotoreDiGioco;

import java.util.ArrayList;

public interface IMacchina {

    /**
     * Sposta la macchina nel punto selezionato
     */
    boolean controlloMovimento(int spostamentoX, int spostamentoY);

    Locazione sinistra(int altezza, int lunghezza);

    Locazione altoSinistra(int altezza, int lunghezza);

    Locazione bassoSinistra(int altezza, int lunghezza);

    Locazione destra(int altezza, int lunghezza);

    Locazione altoDestra(int altezza, int lunghezza);

    Locazione bassoDestra(int altezza, int lunghezza);

    Locazione basso(int altezza, int lunghezza);

    Locazione alto(int altezza, int lunghezza);

    Locazione previsioneProssimaMossa();

    Locazione calcoloSpostamento(int altezza, int lunghezza, int x, int y);

    ArrayList<Locazione> calcoloProssimeMosse(int altezza, int lunghezza);

    void spostamento(Locazione nuovaPosizione) throws Exception;

    Locazione getPosizioneAttuale();

    Locazione getPosizionePrecedente();

    Colore getColore();

    StatoMacchina getStato();

    void setStato(StatoMacchina statoAttuale);

    /**
     * Informa se la macchina è ancora in gara
     * @return true se lo stato della macchina è in_gioco, false altrimenti
     */
    boolean inGara();

    /**
     * Informa se il giocatore ha vinto.
     * @return true se il giocatore ha vinto questa gara, false altrimenti.
     */
    boolean getVittoria();

    /**
     * Imposta true se il giocatore ha vinto.
     * @param vittoria esito della gara.
     */
    void setVittoria(boolean vittoria);

}
