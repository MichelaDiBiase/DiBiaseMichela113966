package it.unicam.cs.pa2024.dibiasemichela113966.Giocatori;

import it.unicam.cs.pa2024.dibiasemichela113966.MotoreDiGioco.GestioneGara;
import it.unicam.cs.pa2024.dibiasemichela113966.MotoreDiGioco.Locazione;
import it.unicam.cs.pa2024.dibiasemichela113966.MotoreDiGioco.Macchina;
import it.unicam.cs.pa2024.dibiasemichela113966.MotoreDiGioco.StatoMacchina;
import it.unicam.cs.pa2024.dibiasemichela113966.TracciatoDiGara.TracciatoDiGara;

import java.util.ArrayList;

public abstract class Giocatore implements IGiocatore {
    private final String nome;
    private TracciatoDiGara tracciato;
    private Macchina macchina;
    private GestioneGara gara;

    public Giocatore(String nome, TracciatoDiGara tracciato, Macchina macchina) {
        this.nome = nome;
        this.tracciato = tracciato;
        this.macchina = macchina;
        this.gara = new GestioneGara(tracciato);
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public TracciatoDiGara getTracciatoDiGara() {
        return tracciato;
    }

    @Override
    public void sceltaMacchina(Macchina macchina) {
        this.macchina = macchina;
    }

    @Override
    public Macchina getMacchina() {
        return macchina;
    }

    @Override
    public ArrayList<Locazione> getProssimeMossePossibili() {
        ArrayList<Locazione> spostamentiPossibili = macchina.calcoloProssimeMosse(tracciato.getAltezza(), tracciato.getLunghezza());

        spostamentiPossibili.removeIf(l -> tracciato.controlloPosizione(l));
        spostamentiPossibili.removeIf(l -> macchina.getStato() == StatoMacchina.partenza && tracciato.getTipologiaLocazione(l) == 2);

        return spostamentiPossibili;
    }

    @Override
    public void spostaMacchinaIn(Locazione prossimaPosizione) throws Exception {
        if(macchina.getStato().equals(StatoMacchina.fuori_gara) || macchina.getStato().equals(StatoMacchina.arrivata)) {
            throw new Exception("La macchina non è più in gara");
        }
        if(!gara.controlloMossa(getProssimeMossePossibili(), prossimaPosizione)) {
            throw new Exception("Non è possibile fare questo spostamento");
        }

        gara.controlloInGara(macchina, prossimaPosizione);
        macchina.spostamento(prossimaPosizione);
        tracciato.spostaMacchina(prossimaPosizione, macchina);
    }
}