package it.unicam.cs.pa2024.dibiasemichela113966.MotoreDiGioco;

import java.util.ArrayList;

public interface IGestioneGara {

    void roundSuccessivo();
    int getRound();
    boolean controlloInGara(Macchina macchina, Locazione prossimaPosizione);
    boolean controlloMossa(ArrayList<Locazione> possibiliMosse, Locazione prossimaPosizione);
    boolean controlloVittoria(Macchina macchina);
}
