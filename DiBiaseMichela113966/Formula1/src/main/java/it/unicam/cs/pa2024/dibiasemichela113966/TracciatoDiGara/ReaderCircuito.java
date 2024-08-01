package it.unicam.cs.pa2024.dibiasemichela113966.TracciatoDiGara;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReaderCircuito {

    ArrayList<String[]> lines;

    public ReaderCircuito() {
        this.lines = new ArrayList<>();
    }

    public int[][] creazioneCircuito(String file) {

        // Legge il file e salva ogni riga come un array di stringhe
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Supponendo che i valori siano separati da virgola
                lines.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Converti ArrayList in un array bidimensionale
        int rowCount = lines.size();
        int colCount = lines.get(0).length;
        String[][] circuito = new String[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            circuito[i] = lines.get(i);
        }

        int[][] circuitoFinale = new int[rowCount][colCount];

        for(int r = 0; r< rowCount; r++){
            for(int c = 0; c< colCount; c++){
                String current = circuito[r][c];
                if(current.equals("x")) {
                    circuitoFinale[r][c] = 0;
                } else if(current.equals("-")) {
                    circuitoFinale[r][c] = 1;
                } else if(current.equals("*")) {
                    circuitoFinale[r][c] = 2;
                } else if(current.equals("+")) {
                    circuitoFinale[r][c] = 3;
                }
            }
        }

        return circuitoFinale;
    }

    public void stampaCircuito(int row, int col, int[][] circuito) {
        // Stampa l'array bidimensionale per verificare il risultato
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(circuito[i][j] + " ");
            }
            System.out.println();
        }
    }
}
