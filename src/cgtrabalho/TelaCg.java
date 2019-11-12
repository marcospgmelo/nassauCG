package cgtrabalho;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Arrays;
import javax.swing.*;
 
public class TelaCg extends JPanel {
  int scale = 50;
    int[] poligonoX = {1, 1, 2, 3, 4, 4, 3 , 2 ,1};
    int[] poligonoY = {2, 3, 4, 4, 3, 2, 1, 1, 2};
    int pontos = 9;
    
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
       
        return g;
    }
    
    public int[] mover(int[] pontos, int mult){
        int[] nvPont = Arrays.copyOf(pontos, pontos.length);
        for (int i = 0; i < pontos.length; i++) {
            nvPont[i] = nvPont[i] + mult;
        }
        return nvPont;
    }
    public Polygon criarPolig(int[] pontosX, int[] pontosY){
        Polygon poli = new Polygon();
        for (int i = 0; i < pontos; i++) {
            poli.addPoint(obterPxL(pontosX[i]), obterPxA(pontosY[i])); 
            poli.addPoint(obterPxL(pontosX[i]), obterPxA(pontosY[i])); 
        }
        return poli;
    }
    
    public int[] escalar(int[] pontos, int mult){
        int[] nvPont = Arrays.copyOf(pontos, pontos.length);
        for (int i = 0; i < pontos.length; i++) {
            nvPont[i] = nvPont[i] * mult;
        }
        return nvPont;
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
        //int x[] = {1, 1, 2, 3, 4, 4, 3 , 2 ,1}; 
        // A 
        //int y[] = {2,3,4,4,3,2,1,1,2}; 
        // pontos 
        //ORIGEM PRONTA
        
        /*
        ROTA플O A : MAGENTA
        TRANSLA플O A: RED
        ESCALA A: BLUE
        
        
       // A
        g.setColor(Color.MAGENTA);
        g = gira(g,60);
        g.drawPolygon(polyA);
        
        g.setColor(Color.RED);
        g = move(g,3,2);
        g.drawPolygon(polyA);
       */
        
        Polygon poly = criarPolig(poligonoX, poligonoY);
        g.setColor(Color.GREEN); 
        g.drawPolygon(poly);
        
        
        g.setColor(Color.RED);
        poly = criarPolig(mover(poligonoX, 2), mover(poligonoY, 2));
        g.drawPolygon(poly);
        
        
        g.setColor(Color.BLUE);
        poly = criarPolig(escalar(mover(poligonoX, 3),2), escalar(mover(poligonoY, 2),2));
        g.drawPolygon(poly);
        
        /*
        ESCALA B: ORANGE
        ROTA플O B : DARK GRAY
        TRANSLA플O B: CYAN
            
       
        g.setColor(Color.ORANGE);
        g = aumenta(polyB, 2, 2);
        g.drawPolygon(polyB);
        
        g.setColor(Color.DARK_GRAY);
        g = gira(g, 60);
        g.drawPolygon(polyB);
        
        g.setColor(Color.CYAN);
        g = move(g, 3,2);
        g.drawPolygon(polyB);
        */
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
 