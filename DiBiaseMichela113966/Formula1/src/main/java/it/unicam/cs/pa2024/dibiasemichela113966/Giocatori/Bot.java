package it.unicam.cs.pa2024.dibiasemichela113966.Giocatori;

import it.unicam.cs.pa2024.dibiasemichela113966.MotoreDiGioco.Macchina;
import it.unicam.cs.pa2024.dibiasemichela113966.TracciatoDiGara.TracciatoDiGara;

import java.util.Random;

public class Bot extends Giocatore {

    public Bot(String nome, TracciatoDiGara tracciato, Macchina macchina) {
        super(nome, tracciato, macchina);
    }

    public void spostamentoMacchina() throws Exception {
        Random random = new Random();

        int indicePossibile = random.nextInt(getProssimeMossePossibili().size());

        spostaMacchinaIn(getProssimeMossePossibili().get(indicePossibile));
    }
}
