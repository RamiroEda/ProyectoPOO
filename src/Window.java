
import javax.swing.*;

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
    private static final int INIT_WIDTH = 680,
                        INIT_HEIGHT = 680;
    private Juego juego;
    
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
        this.setContentPane(juego);
        this.addKeyListener(juego);
    }
}
