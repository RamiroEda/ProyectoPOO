
import javax.swing.*;
import java.awt.*;

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

public class Window extends JFrame{
    private static final int INIT_WIDTH = 1280,
            INIT_HEIGHT = 720;

    private Juego juego;
    private JPanel mainPane;
    private JPanel gamePane;
    private JPanel scorePane;
    private JLabel scoreLabel;
    private JButton facilButton;
    private JButton medioButton;
    private JButton dificilButton;

    public Window(Juego juego){
        this.juego = juego;
        initWindow();
        initLayout();
    }

    private void initWindow(){
        this.setSize(INIT_WIDTH, INIT_HEIGHT);
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void initLayout(){
        this.setContentPane(mainPane);
        gamePane.setLayout(new BoxLayout(gamePane, BoxLayout.PAGE_AXIS));
        gamePane.add(juego);
        this.addKeyListener(juego);

        facilButton.setFocusable(false);
        medioButton.setFocusable(false);
        dificilButton.setFocusable(false);

        facilButton.addActionListener(e -> {
            juego.setDificultad(Dificultad.FACIL);
            repaint();
        });

        medioButton.addActionListener(e -> {
            juego.setDificultad(Dificultad.MEDIO);
            repaint();
        });

        dificilButton.addActionListener(e -> {
            juego.setDificultad(Dificultad.DIFICIL);
            repaint();
        });
    }

    public void updateScore(Integer score){
        this.scoreLabel.setText(score.toString());
    }

    public Dimension getGameSize(){
        return this.gamePane.getSize();
    }

    @Override
    public void paint(Graphics g) {
        int h = this.gamePane.getHeight();
        int w = this.gamePane.getWidth();

        int gameSize = Math.min(h,w);
        double aspectRatio = juego.getDificultad().getAspectRatio();
        Log.d("ASPECT_RATIO",aspectRatio);

        this.gamePane.setSize((int)(gameSize*aspectRatio),gameSize);
        super.paint(g);
    }
}
