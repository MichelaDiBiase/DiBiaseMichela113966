package it.unicam.cs.pa2024.dibiasemichela113966.MotoreDiGioco;

import it.unicam.cs.pa2024.dibiasemichela113966.TracciatoDiGara.TracciatoDiGara;

import java.util.ArrayList;

public class GestioneGara implements IGestioneGara {

    private TracciatoDiGara tracciato;
    private int round;

    public GestioneGara(TracciatoDiGara tracciato) {
        this.tracciato = tracciato;
        this.round = 1;
    }

    @Override
    public void roundSuccessivo() {
        if(round == 3) {
            round = 1;
        } else {
            round++;
        }
    }

    @Override
    public int getRound() {
        return round;
    }

    @Override
    public boolean controlloInGara(Macchina macchina, Locazione prossimaPosizione) {
        if(tracciato.getTipologiaLocazione(prossimaPosizione) == 0) {
            macchina.setStato(StatoMacchina.fuori_gara);
            return false;
        }
        if(tracciato.getTipologiaLocazione(prossimaPosizione) == 2) {
            macchina.setStato(StatoMacchina.arrivata);
            return false;
        }
        return true;
    }

    @Override
    public boolean controlloMossa(ArrayList<Locazione> possibiliMosse, Locazione prossimaPosizione) {
        for(Locazione l : possibiliMosse) {
            if(l.getX() == prossimaPosizione.getX() && l.getY() == prossimaPosizione.getY()) {
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean controlloVittoria(Macchina macchina) {
        for(Locazione l : tracciato.getTraguardo()) {
            if(l.getX() == macchina.getPosizioneAttuale().getX() && l.getY() == macchina.getPosizioneAttuale().getY()) {
                macchina.setVittoria(true);
                return true;
            }
        }
        return false;
    }
}
