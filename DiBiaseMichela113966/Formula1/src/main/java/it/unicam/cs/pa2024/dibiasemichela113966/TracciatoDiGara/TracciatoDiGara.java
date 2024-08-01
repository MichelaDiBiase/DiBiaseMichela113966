package it.unicam.cs.pa2024.dibiasemichela113966.TracciatoDiGara;

import it.unicam.cs.pa2024.dibiasemichela113966.MotoreDiGioco.Colore;
import it.unicam.cs.pa2024.dibiasemichela113966.MotoreDiGioco.Locazione;
import it.unicam.cs.pa2024.dibiasemichela113966.MotoreDiGioco.Macchina;

import java.util.ArrayList;

public class TracciatoDiGara implements ITracciatoDiGara {

    private final int[][] tracciato;

    public TracciatoDiGara(int[][] tracciato) {
        this.tracciato = tracciato;
    }
    @Override
    public int getAltezza() {
        return tracciato.length;
    }

    @Override
    public int getLunghezza() {
        return tracciato[0].length;
    }

    @Override
    public ArrayList<Locazione> getPosizioniIniziali() {
        ArrayList<Locazione> puntiDiInizio = new ArrayList<>();
        for(int y = 0; y < getAltezza(); y++) {
            for(int x = 0; x < getLunghezza(); x++) {
                if(tracciato[y][x] == 3) {
                    puntiDiInizio.add(new Locazione(x, y));
                }
            }
        }
        return puntiDiInizio;
    }

    @Override
    public ArrayList<Locazione> getTraguardo() {
        ArrayList<Locazione> traguardo = new ArrayList<>();
        for(int y = 0; y < getAltezza(); y++) {
            for(int x = 0; x < getLunghezza(); x++) {
                if(tracciato[y][x] == 2) {
                    traguardo.add(new Locazione(y, x));
                }
            }
        }
        return traguardo;
    }

    @Override
    public int getTipologiaLocazione(Locazione posizione) {
        return tracciato[posizione.getY()][posizione.getX()];
    }

    @Override
    public void inserisciMacchina(Macchina macchina) {
        tracciato[macchina.getPosizioneAttuale().getY()][macchina.getPosizioneAttuale().getX()] = getNumeroMacchina(macchina);
    }

    @Override
    public void spostaMacchina(Locazione prossimaPosizione, Macchina macchina) {
        tracciato[macchina.getPosizionePrecedente().getY()][macchina.getPosizionePrecedente().getX()] = 1;
        tracciato[prossimaPosizione.getY()][prossimaPosizione.getX()] = getNumeroMacchina(macchina);
    }

    @Override
    public int getNumeroMacchina(Macchina macchina) {
        if(macchina.getColore().equals(Colore.rosso)) {
            return 7;
        }
        if(macchina.getColore().equals(Colore.giallo)) {
            return 8;
        }
        if(macchina.getColore().equals(Colore.blu)) {
            return 9;
        }

        return 0;
    }

    @Override
    public boolean controlloPosizione(Locazione locazione) {
        if(tracciato[locazione.getY()][locazione.getX()] == 0 ||
                tracciato[locazione.getY()][locazione.getX()] == 7 ||
                tracciato[locazione.getY()][locazione.getX()] == 8 ||
                tracciato[locazione.getY()][locazione.getX()] == 9) {
            return true;
        }
        return false;
    }

    public void stampaCircuito() {
        for(int y = 0; y < getAltezza(); y++) {
            for(int x = 0; x < getLunghezza(); x++) {

                if(tracciato[y][x] == 0) {
                    System.out.print("X ");
                } else if(tracciato[y][x] == 1) {
                    System.out.print(". ");
                } else if(tracciato[y][x] == 2) {
                    System.out.print("= ");
                } else if(tracciato[y][x] == 3) {
                    System.out.print("- ");
                } else if(tracciato[y][x] == 7) {
                    System.out.print("R ");
                } else if(tracciato[y][x] == 8) {
                    System.out.print("G ");
                } else if(tracciato[y][x] == 9) {
                    System.out.print("B ");
                }

                if(x == getLunghezza()-1) {
                    System.out.println(" |" + y + "");
                }
            }
        }
    }
}
