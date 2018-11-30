import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Scoreboard {
    Integer puntaje=0;
    private static String ruta = "puntaje.txt";
    List<Pair<String, Integer>> highScore;
    Juego juego;
    public Scoreboard(Juego juego){
        this.highScore = new ArrayList<>();
        this.juego = juego;
        LeerArchivo();
    }
    public void SumarPuntos(){
       puntaje=puntaje+1;
    }
    public void ResetPuntos(String nombre){
        HacerArchivo(nombre);
        LeerArchivo();
        puntaje=0;
        this.juego.getWindow().updateScore(0);
    }
    private void LeerArchivo(){
        this.highScore.clear();

        String cadena;
        String[] tmp;

        try{
            FileReader f = new FileReader(ruta);
            BufferedReader b = new BufferedReader(f);
            while((cadena = b.readLine())!=null) {
                tmp = cadena.split("\\|");

                this.highScore.add(new Pair(tmp[0], Integer.valueOf(tmp[1])));
            }
            b.close();
        }catch (Exception e){
            Log.e("ERROR_ARCHIVO", e.toString());
        }

        this.juego.getWindow().setScoreList(this.highScore);
    }

    private void Ordenar(){
        List<Pair<String, Integer>> ordered = new ArrayList<>();
        int i = 0;

        for(Pair<String, Integer> h : highScore){
            for(i = 0 ; i < ordered.size() ; i++){
                if(ordered.get(i).getValue() < h.getValue()){
                    break;
                }
            }
            ordered.add(i, h);
        }

        Log.d("ORDENADO", ordered);

        this.highScore = ordered;
    }

    private void HacerArchivo(String nombre){
        try {
            String contenido = "";

            highScore.add(new Pair(nombre,puntaje));

            Ordenar();

            for(int i = 0 ; i <  Math.min(this.highScore.size(), 10) ; i++){
                contenido += this.highScore.get(i).getKey()+"|"+this.highScore.get(i).getValue()+"\n";
            }

            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();
        } catch (Exception e) {
            Log.e("ERROR_ESCRIBIR", e.toString());
        }
    }

}
