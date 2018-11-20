
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * Copyright 2018 Ramiro Estrada García.
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

public class Juego extends JPanel
        implements ActionListener, KeyListener{

    private static final int FRAME_RATE = 60; //Frame-rate

    public static final int KEY_ARRIBA    = 38, //KeyCodes de las teclas de direcciones
            KEY_IZQUIERDA = 37,
            KEY_ABAJO     = 40,
            KEY_DERECHA   = 39;

    private long lastFrameRateUpdate = 0;
    private int frameRateCalls = 0;
    private Double promedioFrameRate;
    private DecimalFormat format;


    private Dificultad dificultad = Dificultad.FACIL; //Dificultad del juego; en Facil por defecto

    private Window window; //La ventana que abre el juego

    private Timer timer; //El timer actualiza el juego cada SEGUNDO/FRAMERATE

    private int lastDirection; //Aqui se guarda la ultima tecla presionada

    private Manzanas manzana;

    Juego(){
        this.format = new DecimalFormat("#.00");
        this.promedioFrameRate = FRAME_RATE/1.0;
        this.dificultad = Dificultad.DIFICIL;
    }


    public void stop(){ //Lo que se hace cuando el juego se PAUSA/DETIENE
        timer.stop();
    }

    public void start(){ //Se prepara el juego
        int delay = (1000/FRAME_RATE)-1;
        timer = new Timer(delay , this);
        window = new Window(this);

        timer.start();
    }

    @Override
    public void paint(Graphics g){ //Aqui se reflejan los cambios del juego ya que aqui se dibujan los elementos
        super.paint(g);
        int width = getWidth();
        int height = getHeight();

        int gridWidth = width/dificultad.getColumnas();
        int gridHeight = height/dificultad.getFilas();

        g.setFont(new Font("Sans", Font.PLAIN, 20));
        g.drawString("FPS:"+format.format(this.promedioFrameRate),0,20);

        // TODO: Despues de aqui se dibujaran los elementos del juego
        for(int i = 0; i < dificultad.getFilas() ; ++i){
            g.drawLine(0, i*gridHeight, width, i*gridHeight);
            for(int e = 0 ; e < dificultad.getColumnas() ; ++e){
                g.drawLine(e*gridWidth, 0,
                        e*gridWidth, height);
            }
        }

        this.manzana.Imagen(g);
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    @Override
    public void actionPerformed(ActionEvent e) { // Aqui se incluiran las acciones; Una llamada a este metodo equivale a un frame
        frameRate();
        if(frameRateCalls >= 60){
            if(manzana == null){
                manzana = new Manzanas();
            }
            this.repaint();
        }
    }

    private void frameRate(){
        this.frameRateCalls++;
        if(System.currentTimeMillis() - this.lastFrameRateUpdate > 1000){
            this.lastFrameRateUpdate = System.currentTimeMillis();
            this.promedioFrameRate = (this.promedioFrameRate + this.frameRateCalls)/2.0;
            this.frameRateCalls = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { /* NO FUNCIONA PARA ESTE PROYECTO AAAUUUUN*/ }

    @Override
    public void keyPressed(KeyEvent e) { // Se obtienen las teclas presionadas
        Log.i("KEY_PRESS", e.getExtendedKeyCode());
        switch(e.getExtendedKeyCode()){
            case KEY_ARRIBA:
                this.lastDirection = KEY_ARRIBA;
                Log.d("DIRECCION", "Arriba");
                break;
            case KEY_ABAJO:
                this.lastDirection = KEY_ABAJO;
                Log.d("DIRECCION", "Abajo");
                break;

            case KEY_IZQUIERDA:
                this.lastDirection = KEY_IZQUIERDA;
                Log.d("DIRECCION", "Izquierda");
                break;
            case KEY_DERECHA:
                this.lastDirection = KEY_DERECHA;
                Log.d("DIRECCION", "Derecha");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
