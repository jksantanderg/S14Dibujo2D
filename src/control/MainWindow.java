package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MainWindow implements Initializable{
    @FXML
    private Canvas canvas;
    
    private ArrayList<Double>ejeX=new ArrayList<>();
    private ArrayList<Double>ejeY=new ArrayList<>();
    private GraphicsContext gc;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
      gc = canvas.getGraphicsContext2D();
      
	  paint();
	}
	public void paint() {
        gc.setFill(Color.rgb(240,240,240));
        gc.fillRect(0, 0, canvas.getWidth(),canvas.getHeight());
        for(int i=0;i<10;i++) {
        	ejeX.add(50.0+10*i);
        	ejeY.add(50*Math.random());
        }
       double[]resX = getMinMax(ejeX);
       double minX = resX[0];
       double maxX = resX[1];
       
       double[]resY = getMinMax(ejeY);
       double minY = resY[0];
       double maxY = resY[1];
       
       double deltaPx = canvas.getWidth();
       double deltaDias = maxX-minX;
       
       double pendienteX=deltaPx/deltaDias;
       
       double intercepto = pendienteX * minX *(-1);
       
       double deltaPy = -canvas.getHeight();
       
       double deltaAccidentes = maxY-minY;
       
       double pendienteY = deltaPy/deltaAccidentes;
       double interceptoY = pendienteY* maxY*(-1);
       //System.out.println(minX + " "+maxX+" "+minY+" "+maxY);
       gc.setFill(Color.BLUE);
       for(int i=0;i<ejeX.size();i++) {
    	   gc.fillOval(conversion(ejeX.get(i),pendienteX,intercepto), conversion(ejeY.get(i),pendienteY,interceptoY)-3, 6, 6);
       }
       gc.setStroke(Color.BLUE);
       gc.setLineWidth(2);
       for(int i=0;i<ejeX.size()-1;i++) {
      // gc.setLineWidth(2);
       gc.moveTo(conversion(ejeX.get(i),pendienteX,intercepto)+3, conversion(ejeY.get(i),pendienteY,interceptoY)+3);
       gc.lineTo(conversion(ejeX.get(i+1),pendienteX,intercepto)+3, conversion(ejeY.get(i+1),pendienteY,interceptoY)+3);
       }
       gc.stroke();
	}

	public double[] getMinMax(ArrayList<Double>eje) {
		ArrayList<Double>aux=new ArrayList<>();
		aux.addAll(eje);
		Collections.sort(aux);
		double min = aux.get(0);
		double max = aux.get(aux.size()-1);
		return new double [] {min,max};
	}
	private double conversion(double x,double m,double b) {
		
	   return m*x+b;	
	}
}
