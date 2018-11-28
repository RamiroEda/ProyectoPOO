
import Viborita.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
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

    private Dificultad dificultad = Dificultad.DIFICIL; //Dificultad del juego; en Facil por defecto

    private int FRAME_RATE = this.dificultad.getVelocidad(); //Frame-rate

    public static final int KEY_ARRIBA    = 38, //KeyCodes de las teclas de direcciones
            KEY_IZQUIERDA = 37,
            KEY_ABAJO     = 40,
            KEY_DERECHA   = 39;

    private long lastFrameRateUpdate = 60;
    private int frameRateCalls = 0;
    private Double promedioFrameRate;
    private DecimalFormat format;

    private Window window; //La ventana que abre el juego

    private Timer timer; //El timer actualiza el juego cada SEGUNDO/FRAMERATE

    private int lastDirection; //Aqui se guarda la ultima tecla presionada

    private Viborita1 viborita;

    private Manzanas manzana;

    Juego(){
        this.format = new DecimalFormat("#.00");
        this.promedioFrameRate = FRAME_RATE/1.0;
        resetManzana();
    }


    public void stop(){ //Lo que se hace cuando el juego se PAUSA/DETIENE
        timer.stop();
    }

    public void start(){ //Se prepara el juego
        int delay = (1000/dificultad.getVelocidad());
        timer = new Timer(delay , this);
        window = new Window(this);
        viborita = new Viborita1();

        timer.start();
    }

    public void resume(){
        timer.start();
    }

    public void restart(){
        stop();

        int delay = (1000/dificultad.getVelocidad());
        timer = new Timer(delay , this);
        viborita = new Viborita1();

        timer.start();
    }

    @Override
    public void paint(Graphics g){ //Aqui se reflejan los cambios del juego ya que aqui se dibujan los elementos
        super.paint(g);

        this.setSize(window.getGameSize());

        int width = getWidth();
        int height = getHeight();

        int gridWidth = width/dificultad.getColumnas();
        int gridHeight = height/dificultad.getFilas();

        g.setFont(new Font("Sans", Font.PLAIN, 20));
        g.drawString("FPS:"+format.format(this.promedioFrameRate),0,20);

        // TODO: Despues de aqui se dibujaran los elementos del juego
        drawGrid(g, gridWidth, gridHeight);

        this.manzana.Imagen(g, gridWidth, gridHeight);
    }

    private void drawGrid(Graphics g, int gridWidth, int gridHeight){
        g.setColor(Color.LIGHT_GRAY);
        for(int i = 0; i <= dificultad.getFilas() ; ++i){
            g.drawLine(0, i*gridHeight, gridWidth*dificultad.getColumnas(), i*gridHeight);
            for(int e = 0 ; e <= dificultad.getColumnas() ; ++e){
                g.drawLine(e*gridWidth, 0,
                        e*gridWidth, gridHeight*dificultad.getFilas());
            }
        }
        g.setColor(Color.black);
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
        resetManzana();
        restart();
        this.repaint();
    }

    private void resetManzana(){
        this.manzana = new Manzanas(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) { // Aqui se incluiran las acciones; Una llamada a este metodo equivale a un frame
        try{
            frameUpdate();
        }catch (Exception error){
            Log.e("ERROR_UPDATE", error.toString());
        }
    }

    private void frameUpdate(){
        this.frameRateCalls++;

        if(System.currentTimeMillis() - this.lastFrameRateUpdate > 1000){
            this.lastFrameRateUpdate = System.currentTimeMillis();
            this.promedioFrameRate = (this.promedioFrameRate + this.frameRateCalls)/2.0;
            this.frameRateCalls = 0;
        }

        this.repaint();
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
        resetManzana();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
