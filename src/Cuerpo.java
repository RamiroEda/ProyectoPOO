
import java.awt.Graphics;
import javax.swing.ImageIcon;

/*
 * Copyright 2018 psdh.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 *
 * @author psdh
 */
public class Cuerpo {
    public Cuerpo(Juego juego){
        this.juego=juego;
    }
    public void moverse(int direccion){
        switch(direccion){
            case Juego.KEY_ARRIBA:
                Y--;
                Log.d("DIRECCION", "Arriba");
                break;
            case Juego.KEY_ABAJO:
                Y++;
                Log.d("DIRECCION", "Abajo");
                break;
            case Juego.KEY_IZQUIERDA:
                X--;
                Log.d("DIRECCION", "Izquierda");
                break;
            case Juego.KEY_DERECHA:
                X++;
                Log.d("DIRECCION", "Derecha");
                break;
        }
        if (sigcuerpo!=null){
        sigcuerpo.moverse(Oldir);
        Oldir=direccion;
        }
    }
    private int Oldir;
    private int X,Y;
    private Cuerpo sigcuerpo;
    private Juego juego;
    public void Imagen(Graphics g, int gridWidth, int gridHeight){

        ImageIcon Img = new ImageIcon(getClass().getResource("cabeza.png"));
        g.drawImage(Img.getImage(),
                (((juego.getWidth())/(juego.getDificultad().getColumnas()))*X),
                (((juego.getHeight())/(juego.getDificultad().getFilas()))*Y) ,
                gridWidth,
                gridHeight,
                null);
        //Log.d("x", (((juego.getWidth())/(juego.getDificultad().getColumnas()))*X));
        //Log.d("y", (((juego.getHeight())/(juego.getDificultad().getFilas()))*Y));
        Log.d("X", X);
        Log.d("Y", Y);
    }
}
