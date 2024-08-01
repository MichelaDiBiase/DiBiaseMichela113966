package it.unicam.cs.pa2024.dibiasemichela113966.MotoreDiGioco;

import java.util.ArrayList;

public class Macchina implements IMacchina {

    private Locazione posizioneAttuale;
    private Locazione posizioneprecedente;
    private final Colore colore;
    private StatoMacchina stato;
    private boolean vittoria;
    public Macchina(Locazione locazione, Colore colore) {
        this.posizioneAttuale = new Locazione(locazione.getX(), locazione.getY());
        this.posizioneprecedente = new Locazione(locazione.getX(), locazione.getY());
        this.colore = colore;
        this.stato = StatoMacchina.partenza;
        this.vittoria = false;
    }

    public Macchina(int x, int y, Colore colore) {
        this.posizioneAttuale = new Locazione(x, y);
        this.posizioneprecedente = new Locazione(x, y);
        this.colore = colore;
        this.stato = StatoMacchina.partenza;
    }

    @Override
    public boolean controlloMovimento(int spostamentoX, int spostamentoY) {
        return false;
    }

    @Override
    public Locazione sinistra(int altezza, int lunghezza) {
        return calcoloSpostamento(altezza, lunghezza, -1, 0);
    }

    @Override
    public Locazione altoSinistra(int altezza, int lunghezza) {
        return calcoloSpostamento(altezza, lunghezza, -1, -1);
    }

    @Override
    public Locazione bassoSinistra(int altezza, int lunghezza) {
        return calcoloSpostamento(altezza, lunghezza, -1, 1);
    }

    @Override
    public Locazione destra(int altezza, int lunghezza) {
        return calcoloSpostamento(altezza, lunghezza, 1, 0);
    }

    @Override
    public Locazione altoDestra(int altezza, int lunghezza) {
        return calcoloSpostamento(altezza, lunghezza, 1, -1);
    }

    @Override
    public Locazione bassoDestra(int altezza, int lunghezza) {
        return calcoloSpostamento(altezza, lunghezza, 1, 1);
    }

    @Override
    public Locazione basso(int altezza, int lunghezza) {
        return calcoloSpostamento(altezza, lunghezza, 0, 1);
    }

    @Override
    public Locazione alto(int altezza, int lunghezza) {
        return calcoloSpostamento(altezza, lunghezza, 0, -1);
    }

    @Override
    public Locazione previsioneProssimaMossa() {
        int x = 2 * posizioneAttuale.getX() - posizioneprecedente.getX();
        int y = 2 * posizioneAttuale.getY() - posizioneprecedente.getY();

        return new Locazione(x, y);
    }

    @Override
    public Locazione calcoloSpostamento(int altezza, int lunghezza, int x, int y) {
        Locazione previsione = previsioneProssimaMossa();
        int spostamentoX = previsione.getX() + x;
        int spostamentoY = previsione.getY() + y;

        if ((0<= spostamentoX)&&(spostamentoX<lunghezza)&&(0<=spostamentoY)&&(spostamentoY<altezza)) {
            return new Locazione(spostamentoX,spostamentoY);
        }

        return null;
    }

    @Override
    public ArrayList<Locazione> calcoloProssimeMosse(int altezza, int lunghezza) {
        ArrayList<Locazione> possibiliMosse = new ArrayList<>();

        possibiliMosse.add(sinistra(altezza, lunghezza));
        possibiliMosse.add(altoSinistra(altezza, lunghezza));
        possibiliMosse.add(bassoSinistra(altezza, lunghezza));
        possibiliMosse.add(destra(altezza, lunghezza));
        possibiliMosse.add(altoDestra(altezza, lunghezza));
        possibiliMosse.add(bassoDestra(altezza, lunghezza));
        possibiliMosse.add(alto(altezza, lunghezza));
        possibiliMosse.add(basso(altezza, lunghezza));

        return possibiliMosse;
    }

    @Override
    public void spostamento(Locazione nuovaPosizione) {
        posizioneprecedente = posizioneAttuale;
        posizioneAttuale = nuovaPosizione;
        setStato(StatoMacchina.in_gara);
    }

    @Override
    public Locazione getPosizioneAttuale() {
        return posizioneAttuale;
    }

    @Override
    public Locazione getPosizionePrecedente() {
        return posizioneprecedente;
    }

    @Override
    public Colore getColore() {
        return colore;
    }

    @Override
    public StatoMacchina getStato() {
        return stato;
    }

    @Override
    public void setStato(StatoMacchina statoAttuale) {
        this.stato = statoAttuale;
    }

    @Override
    public boolean inGara() {
        return stato == StatoMacchina.in_gara;
    }

    @Override
    public boolean getVittoria() {
        return vittoria;
    }

    @Override
    public void setVittoria(boolean vittoria) {
        this.vittoria = vittoria;
    }

}
