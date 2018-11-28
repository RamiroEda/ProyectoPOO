import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Scoreboard {
    Integer puntaje=0;
    public void Scoreboard(){

    }
    public void SumarPuntos(){
       puntaje=puntaje+1;
    }
    public void ResetPuntos(){
        HacerArchivo();
        puntaje=0;
    }
    private void HacerArchivo(){
        try {
            String ruta = "puntaje.txt";
            String contenido = puntaje.toString();
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
            e.printStackTrace();
        }
    }

}
