package output;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

import logica.MapaLogico;


public class Output  extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 765987516366043796L;
	ArrayList<Layer> Layers;
	Component padre;
	Sprite fondo;

	
	public Output()
	{
		super();
		Layers= new ArrayList<Layer>(); //TODO: setear el mapa por defecto.
		
	}
	public Output(String path)
	{
		super();
		Layers= new ArrayList<Layer>(); //TODO: setear el mapa por defecto.
		fondo = new Sprite(path);
		this.setBounds(fondo.getRec());
		fondo.setBounds(0, 0,fondo.getWidth(),this.getHeight());
		
		
		
	}
	
	public void addLayer(MapaLogico ml)
	{
		Layer l = new Layer();
		l.update(ml, null);
		ml.addObserver(l);
		Layers.add(l);
	}
	
    public void paintComponent(Graphics g) {
        
        g.setColor(Color.BLACK);
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
       
			drawSelf((Graphics2D) g, width, height);
		
    }
   
    private void drawSelf(Graphics2D g2, int width, int height) {
    	
    	g2.drawString(System.getProperty("user.dir"),50,50);
    	g2.drawString(fondo.toString(), 50, 100);
    	g2.drawString(this.getClass().toString(), 1000, 150);
    	if (fondo!=null) fondo.printSprite(this, g2);
    	
		for (Layer l : Layers)
			l.printLayer(this, g2);

    }
    
    public void setMapa(String path){
    	fondo=new Sprite(path);
    }
    
    

}
