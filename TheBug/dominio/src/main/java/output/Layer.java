package output;

import java.awt.Component;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import logica.MapaLogico;

//Esta clase se usa como conveniencia, para poder siempre dibujar sprites de forma agrupada.

public class Layer implements Observer{ //TODO: repensar la clase para hacerla mas generica
	
	ArrayList <Sprite> sprites;
	
	Layer(){
		sprites = new ArrayList<Sprite>();
	}
	
	public void addSprite(Sprite s)
	{
		sprites.add(s); // Las propiedades del sprite se inicializan con los metodos del sprite, preferiblemente afuera.
	}
	
	public void printLayer(Component c, Graphics2D g)
	{
		for(Sprite s : sprites)
		{
			if(s!=null)
			s.printSprite(c, g);
		}
	}
	
	public void update(Observable o, Object arg)
	{
		MapaLogico ml=(MapaLogico)o;
		
		for(int i=0;i<ml.getW();i++)
			for (int j=0;j<ml.getH();j++)
				//if(ml.ocupada(i, j)) 
				{Sprite s= ml.getSprite(i,j);
				if (s!=null)
				s.setpos(i * ml.getH(), j * ml.getW()); 
				this.addSprite(s);}
	}
	
}
