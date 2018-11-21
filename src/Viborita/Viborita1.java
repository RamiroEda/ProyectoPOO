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
package Viborita;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author psdh
 */
public class Viborita1 extends JPanel{
    private ImageIcon titleImage;
    public Viborita1(){
        
    }
    public void draw(Graphics g){
        g.setColor(Color.GRAY);
        g.drawRect(10, 15, 20, 25);
        titleImage=new titleImage("descarga.jpg");
        titleImage.paintIcon(this, g, 30, 20);
        
    }
    
}
