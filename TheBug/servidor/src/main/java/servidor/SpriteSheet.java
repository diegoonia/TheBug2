package servidor;


import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

/*
 * Una clase que se encarga de cargar todo el mapa de sprites. El objetivo principal es que se le pueda pedir un sprite especifico con un entero
 * Alternativamente, se puede usar para animar sprites.
 * 
 * BTW: La clase la pongo en ingles, porque la alternativa es poner HojaSprites o MapaImagenes, y no me gusta ninguno de los dos :D
 */
public class SpriteSheet {
	
	BufferedImage sheet;
	int size=32, cantw, canth; //Tamanio del lado del sprite, utilizado para calcular las coordenadas en pixeles.
	
	SpriteSheet(String path)// TODO: Validar path para que no reviente si la imagen no existe.
	{

		sheet = (BufferedImage) new ImageIcon(path).getImage();

	}
	void setsize ( int s)
	{
		this.size=s;
		///cantw= Math.floorDiv(this.sheet.getWidth(), size);
		///canth= Math.floorDiv(this.sheet.getHeight(), size);
	}
	
	SpriteSheet(String path, int s)//TODO: Validar path para que no reviente si la imagen no existe.
	{
			
			sheet= (BufferedImage) new ImageIcon(path).getImage();
			setsize(s);
			
	}
	
	BufferedImage getFrame(int idx)
	{
		
		
		BufferedImage aux= this.sheet.getSubimage((idx%cantw)*size, (idx/cantw)*size, size, size);
		return aux;
	}
	

}
