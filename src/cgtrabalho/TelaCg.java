package cgtrabalho;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import javax.swing.*;
 
public class TelaCg extends JPanel {
  int scale = 50;
 
    public int getLargura(){ // largura da tela
        return this.getWidth();
    }
    
    public int getMetadeLargura(){ // metade ta largura
        return this.getWidth()/2;
    } 
    public int getAltura(){ // tamanho da tela
        return this.getHeight();
    }
    
    public int getMetadeAltura(){ // metade da tela
        return this.getHeight()/2;
    }
    
    public int obterPxL(int px){
        return getMetadeLargura()+(scale*px);
    }
    public int obterPxA(int px){
        return getMetadeAltura()-(scale*px);
    }
    
    public Graphics2D gira(Graphics2D g, int graus){
        AffineTransform rotA = new AffineTransform();
        rotA.translate(getMetadeLargura(), getMetadeAltura());
        rotA.rotate(Math.toRadians(graus));
        rotA.translate(getMetadeLargura()*-1, getMetadeAltura()*-1);
        g.transform(rotA);
        return g;
    }
    
    public Graphics2D move(Graphics2D g, int x, int y){
       AffineTransform tranA = new AffineTransform();
        tranA.translate(x,y);
        g.transform(tranA);
        return g;
    }
    
    public Graphics2D aumenta(Graphics2D g, int x, int y){
        AffineTransform escA = new AffineTransform();
        escA.translate(getMetadeLargura(), getMetadeAltura());
        escA.scale(2,2);
        escA.translate(-getMetadeLargura(), -getMetadeAltura());
        g.transform(escA);
        return g;
    }
    
    
    
    
  public void paintComponent(Graphics g2) {
      Graphics2D g = (Graphics2D) g2;
     //Horizontal
     g.setColor(Color.BLACK);
     g.drawLine(0, getMetadeAltura(), this.getLargura(), getMetadeAltura());
 
     //vertical
     g.setColor(Color.BLACK);
     g.drawLine(getMetadeLargura(), 0, getMetadeLargura(),this.getAltura());
     
     int qtdEscalasL = (getLargura()/scale)-1; //Escalas que cabem na Largura
     int qtdEscalasA = (getAltura()/scale)-1; //Escalas que cabem na Altura
     
      for (int div = (qtdEscalasL/2)*-1; div <= ((qtdEscalasL/2)); div++) {
          g.setColor(Color.BLACK);
          g.drawLine(obterPxL(div), getMetadeAltura()-15 , obterPxL(div), getMetadeAltura()+15);
          g.setColor(Color.BLUE);
          g.drawString(String.valueOf(div), obterPxL(div)+5, getMetadeAltura()-10);
      }
      
      for (int div = (qtdEscalasA/2)*-1; div <= ((qtdEscalasA/2)); div++) {
          if(div == 0){
              continue;
          }
          g.setColor(Color.BLACK);
          g.drawLine(getMetadeLargura()-15 , obterPxA(div) , getMetadeLargura()+15 ,obterPxA(div));
          g.setColor(Color.BLUE);
          g.drawString(String.valueOf(div),getMetadeLargura()+15 ,obterPxA(div)+10);
      }
      //DESENHANDO O POLIGONO
        // L 
        int x[] = {1, 1, 2, 3, 4, 4, 3 , 2 ,1}; 
        // A 
        int y[] = {2,3,4,4,3,2,1,1,2}; 
        // pontos 
        int pontos = 9; 
        Polygon poly = new Polygon();
        // set the color of line drawn to blue 
        g.setColor(Color.GREEN); 
        for (int i = 0; i < pontos; i++) {
            //g.drawLine(obterPxL(x[i]), obterPxL(y[i])); 
            poly.addPoint(obterPxL(x[i]), obterPxA(y[i])); 
      }
        g.drawPolygon(poly);
        //ORIGEM PRONTA
        
        /*
        ROTA플O A : MAGENTA
        TRANSLA플O A: RED
        ESCALA A: BLUE 
        
        
        
        ESCALA B: ORANGE
        ROTA플O B : DARK GRAY
        TRANSLA플O B: CYAN
        
        */
        AffineTransform backup = g.getTransform();
    
       // A
        g.setColor(Color.MAGENTA);
        g = gira(g,60);
        g.drawPolygon(poly);
        
        g.setColor(Color.RED);
        g = move(g,3,2);
        g.drawPolygon(poly);
       
        g.setColor(Color.BLUE);
        g = aumenta(g,2,2);
        g.drawPolygon(poly);
       
        g.setTransform(backup);
        g.setColor(Color.ORANGE);
        g = aumenta(g, 2, 2);
        g.drawPolygon(poly);
        
        g.setColor(Color.DARK_GRAY);
        g = gira(g, 60);
        g.drawPolygon(poly);
        
        g.setColor(Color.CYAN);
        g = move(g, 3,2);
        g.drawPolygon(poly);
        
  }
 
  public static void main(String[] args) {
    JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame frame = new JFrame("Trabalho Marcos Paulo, para CG UNINASSAU ");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBackground(Color.white);
    frame.setSize(800, 600);
    TelaCg panel = new TelaCg();
    frame.add(panel);
    frame.setVisible(true);
  }
}
 