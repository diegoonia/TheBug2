package test;

import org.junit.Assert;
import org.junit.Test;

import equipamiento.*;
import personaje.Humano;
import personaje.Personaje;

public class ItemTests {

/* Dado un Personaje, cuando el Personaje obtenga un item, 
entonces el mismo incrementará o decrementará los Puntos de Defensa 
o Puntos de Ataque del Personaje*/
	
	@Test
	public void equiparItemAfectaPuntos(){
		
		Personaje diego = new Humano("diego");
		//aca tiene 50 PA,  // PA = Puntos Ataq.
		
		diego = new ConMouseNoganet(diego);
		// Ahora debe tener 40 PA, porque el mouse le da -10
		
		Assert.assertEquals(40, diego.obtenerPuntosDeFuerza());
		
		diego = new ConMouseThermaltake(diego);
		// Ahora debe tener 80 PA, porque el mouse hace 40*2
		
		Assert.assertEquals(80, diego.obtenerPuntosDeFuerza());
	}
	
/* Dado un Personaje, cuando el Personaje recoja un item entonces
  el mismo se equipará sin importar la cantidad de items que ya tenga equipados.*/

	@Test
	public void equiparMuchosItems(){
		
		Personaje diego = new Humano("diego");
		//aca tiene 50 PA // PA = Puntos Ataq.
		
		diego = new ConMouseThermaltake(diego);
		// Ahora debe tener 100 PA, porque el mouse hace 50*2
		diego = new ConMouseThermaltake(diego);
		// Ahora debe tener 2000 PA, porque el mouse hace 100*2
		diego = new ConMouseThermaltake(diego);
		// Ahora debe tener 400 PA, porque el mouse hace 200*2
		diego = new ConMouseThermaltake(diego);
		// Ahora debe tener 800 PA, porque el mouse hace 400*2
		
		Assert.assertEquals(800, diego.obtenerPuntosDeFuerza());
	}
			
}
