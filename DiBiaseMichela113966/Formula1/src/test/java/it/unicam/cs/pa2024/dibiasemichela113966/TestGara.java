package it.unicam.cs.pa2024.dibiasemichela113966;
import it.unicam.cs.pa2024.dibiasemichela113966.Giocatori.Bot;
import it.unicam.cs.pa2024.dibiasemichela113966.Giocatori.Giocatore;
import it.unicam.cs.pa2024.dibiasemichela113966.Giocatori.Utente;
import it.unicam.cs.pa2024.dibiasemichela113966.MotoreDiGioco.*;
import it.unicam.cs.pa2024.dibiasemichela113966.TracciatoDiGara.ReaderCircuito;
import it.unicam.cs.pa2024.dibiasemichela113966.TracciatoDiGara.TracciatoDiGara;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Test per controllare l'andamento di una gara base
public class TestGara {
  private GestioneGara gestioneGara;
    private TracciatoDiGara tracciato;
    private Macchina rosso;
    private Macchina giallo;
    private Macchina blu;
    private Giocatore giocatore;
    private Bot bot1;

    @BeforeEach
    public void setUp() {
        ReaderCircuito reader = new ReaderCircuito();
        int[][] circuito = reader.creazioneCircuito("circuitoUno.txt");
        tracciato = new TracciatoDiGara(circuito);
        gestioneGara = new GestioneGara(tracciato);

        rosso = new Macchina(tracciato.getPosizioniIniziali().get(0), Colore.rosso);
        giallo = new Macchina(tracciato.getPosizioniIniziali().get(1), Colore.giallo);
        blu = new Macchina(tracciato.getPosizioniIniziali().get(2), Colore.blu);

        giocatore = new Utente("Giocatore", tracciato, rosso);
        bot1 = new Bot("Bot1", tracciato, giallo);

        tracciato.inserisciMacchina(rosso);
        tracciato.inserisciMacchina(giallo);
        tracciato.inserisciMacchina(blu);
    }

    @Test
    public void testRoundSuccessivo() {
        assertEquals(1, gestioneGara.getRound());
        gestioneGara.roundSuccessivo();
        assertEquals(2, gestioneGara.getRound());
        gestioneGara.roundSuccessivo();
        assertEquals(3, gestioneGara.getRound());
        gestioneGara.roundSuccessivo();
        assertEquals(1, gestioneGara.getRound());
    }

    @Test
    public void testControlloStato() {
        Locazione posizioneDentro = new Locazione(1, 5);
        Locazione posizioneFuori = new Locazione(0, 5);
        Locazione posizioneTraguardo = new Locazione(1, 7);

        assertTrue(gestioneGara.controlloInGara(rosso, posizioneDentro));
        assertNotEquals(StatoMacchina.fuori_gara, rosso.getStato());
        assertFalse(gestioneGara.controlloInGara(rosso, posizioneFuori));
        assertEquals(StatoMacchina.fuori_gara, rosso.getStato());
        assertFalse(gestioneGara.controlloInGara(rosso, posizioneTraguardo));
        assertNotEquals(StatoMacchina.fuori_gara, rosso.getStato());
        assertEquals(StatoMacchina.arrivata, rosso.getStato());

        //riporto la macchina allo stato iniziale
        rosso.setStato(StatoMacchina.partenza);
    }

    @Test
    public void testControlloMossaNonValida() {
        ArrayList<Locazione> possibiliMosse = rosso.calcoloProssimeMosse(tracciato.getAltezza(), tracciato.getLunghezza());
        Locazione mossaValida = possibiliMosse.get(0);
        Locazione mossaNonValida = new Locazione(8, 10);

        assertTrue(gestioneGara.controlloMossa(possibiliMosse, mossaValida));
        assertFalse(gestioneGara.controlloMossa(possibiliMosse, mossaNonValida));
    }

    @Test
    public void testControlloMossePossibili() {
        ArrayList<Locazione> possibiliMosse = new ArrayList<>();
        Locazione spostamento1 = new Locazione(1, 5);
        Locazione spostamento2 = new Locazione(2, 5);
        Locazione spostamento3 = new Locazione(3, 5);

        possibiliMosse.add(spostamento1);
        possibiliMosse.add(spostamento2);
        assertTrue(confrontoArrayList(possibiliMosse, giocatore.getProssimeMossePossibili()));
        assertFalse(confrontoArrayList(possibiliMosse, bot1.getProssimeMossePossibili()));

        possibiliMosse.add(spostamento3);

        assertTrue(confrontoArrayList(possibiliMosse, bot1.getProssimeMossePossibili()));
        assertFalse(confrontoArrayList(possibiliMosse, giocatore.getProssimeMossePossibili()));
    }

    @Test
    public void testControlloVittoria() {
        Locazione posizioneTraguardo = tracciato.getTraguardo().get(0);
        blu.spostamento(posizioneTraguardo);
        assertTrue(gestioneGara.controlloVittoria(blu));
        assertTrue(blu.getVittoria());
    }

    @Test
    public void testSpostaMacchina() throws Exception {
        Locazione mossa1 = new Locazione(1, 5);
        giocatore.spostaMacchinaIn(mossa1);
        assertEquals(mossa1, rosso.getPosizioneAttuale());

        Locazione mossa2 = new Locazione(10, 10);

        assertThrows(Exception.class, () -> giocatore.spostaMacchinaIn(mossa2));

        Locazione mossa3 = new Locazione(0, 4);
        assertThrows(Exception.class, () -> giocatore.spostaMacchinaIn(mossa3));
    }

    @Test
    public void testStatoMacchinaFuoriGara() {
        Locazione posizioneFuori = new Locazione(0, 0);
        gestioneGara.controlloInGara(giallo, posizioneFuori);
        assertEquals(StatoMacchina.fuori_gara, giallo.getStato());
    }

    public boolean confrontoArrayList(ArrayList<Locazione> previsione, ArrayList<Locazione> lista) {
        int count = 0;

        for(Locazione locazione : previsione) {
            for(Locazione l : lista) {
                if(locazione.getX() == l.getX() && locazione.getY() == l.getY()) {
                    count++;
                    break;
                }
            }
        }

        return previsione.size() == count && previsione.size() == lista.size();
    }
}