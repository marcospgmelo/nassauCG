package cgtrabalho;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Arrays;
import javax.swing.*;
 
public class TelaCg extends JPanel {
  int scale = 50;

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
    
    public double obterPxL(double px){
        return getMetadeLargura()+(scale*px);
    }
    public double obterPxA(double px){
        return getMetadeAltura()-(scale*px);
    }
    
    public Polygon gira(double[] pontosX,double[] pontosY, int graus) {
        int i;
        double x1, y1;
        double radiano = Math.toRadians(graus), cos = Math.cos(radiano), sin = Math.sin(radiano);
        for (i = 0; i < pontosY.length; i++) {
            x1 = pontosX[i];
            y1 = pontosY[i];
            pontosX[i] =  (x1 * cos - y1 * sin);
            pontosY[i] =  (y1 * cos + x1 * sin);
        }
        return criarPolig(pontosX, pontosY);
    }
    
    public double[] mover(double[] pontos, double mult){
        double[] nvPont = Arrays.copyOf(pontos, pontos.length);
        for (int i = 0; i < pontos.length; i++) {
            nvPont[i] = nvPont[i] + mult;
        }
        return nvPont;
    }
    public Polygon criarPolig(double[] pontosX, double[] pontosY){
        Polygon poli = new Polygon();
        for (int i = 0; i < pontos; i++) {
            poli.addPoint((int)obterPxL(pontosX[i]), (int)obterPxA(pontosY[i])); 
            poli.addPoint((int)obterPxL(pontosX[i]), (int)obterPxA(pontosY[i])); 
        }
        return poli;
    }
    
    public double[] escalar(double[] pontos, double mult){
        for (int i = 0; i < pontos.length; i++) {
            pontos[i] = pontos[i] * mult;
        }
        return pontos;
    }
    
  public void paintComponent(Graphics g2) {
      Graphics2D g = (Graphics2D) g2;
     //Horizontal     
       
     try{
         
     
     g.setColor(Color.BLACK);
     g.drawLine(0, getMetadeAltura(), this.getLargura(), getMetadeAltura());
 
     //vertical
     g.setColor(Color.BLACK);
     g.drawLine(getMetadeLargura(), 0, getMetadeLargura(),this.getAltura());
     
     int qtdEscalasL = (getLargura()/scale)-1; //Escalas que cabem na Largura
     int qtdEscalasA = (getAltura()/scale)-1; //Escalas que cabem na Altura
     
      for (int div = (qtdEscalasL/2)*-1; div <= ((qtdEscalasL/2)); div++) {
          g.setColor(Color.BLACK);
          g.drawLine((int)obterPxL(div), getMetadeAltura()-15 , (int)obterPxL(div), getMetadeAltura()+15); //desenhando a reta
          g.setColor(Color.BLUE);
          g.drawString(String.valueOf(div), (int)obterPxL(div)+5, getMetadeAltura()-10);
      }
      
      for (int div = (qtdEscalasA/2)*-1; div <= ((qtdEscalasA/2)); div++) {
          if(div == 0){
              continue;
          }
          g.setColor(Color.BLACK);
          g.drawLine(getMetadeLargura()-15 , (int)obterPxA(div) , getMetadeLargura()+15 ,(int)obterPxA(div));
          g.setColor(Color.BLUE);
          g.drawString(String.valueOf(div),getMetadeLargura()+15 ,(int)obterPxA(div)+10);
      }
      //DESENHANDO O POLIGONO
        
       // ROTA플O A : MAGENTA
       // TRANSLA플O A: RED
       // ESCALA A: BLUE
        double[] poligonoXA = {0.3, 0.5, 0.9, 1.3, 1.6, 2.8, 2.2 , 1.8,0.2};
        double[] poligonoYA = {0.2, 1.2, 2.7, 2.8, 1.3, 0.9, 0.5, 0.6, 1.1};
        
        Polygon poly = criarPolig(poligonoXA, poligonoYA);
        g.setColor(Color.GREEN); 
        g.drawPolygon(poly);
        
        /*
        
        g.setColor(Color.MAGENTA);
        poly = gira(poligonoXA,poligonoYA,60);
        g.drawPolygon(poly);
        
        g.setColor(Color.RED);
        poly = criarPolig(mover(poligonoXA, 2), mover(poligonoYA, 2));
        g.drawPolygon(poly);
        
        g.setColor(Color.BLUE);
        poly = criarPolig(escalar(poligonoXA,3), escalar(poligonoYA,2));
        g.drawPolygon(poly);
    
        */
        double[] poligonoXB = {0.3, 0.5, 0.9, 1.3, 1.6, 2.8, 2.2 , 1.8,0.2};
        double[] poligonoYB = {0.2, 1.2, 2.7, 2.8, 1.3, 0.9, 0.5, 0.6, 1.1};
        
       // ESCALA B: ORANGE
      //  ROTA플O B : DARK GRAY
      //  TRANSLA플O B: CYAN
      
        g.setColor(Color.ORANGE);
        poly = criarPolig(escalar(poligonoXB,3), escalar(poligonoYB,2));
        g.drawPolygon(poly);
        
        g.setColor(Color.PINK);
        poly = gira(poligonoXB,poligonoYB,60);
        g.drawPolygon(poly);
        
        g.setColor(Color.CYAN);
        poly = criarPolig(mover(poligonoXB, 2), mover(poligonoYB, 2));
        g.drawPolygon(poly);
        
       }finally{
      g = null;
  }
  }
 
  public static void main(String[] args) {
    JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame frame = new JFrame("Trabalho Marcos Paulo, para CG UNINASSAU ");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBackground(Color.white);
    frame.setSize(800, 600);
    TelaCg panel = new TelaCg();
    frame.add(panel);
    frame.setState(Frame.MAXIMIZED_BOTH);
    frame.setVisible(true);
  }
}
 