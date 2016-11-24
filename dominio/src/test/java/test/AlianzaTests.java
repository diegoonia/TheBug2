package test;

import org.junit.Assert;
import org.junit.Test;

import personaje.Humano;
import personaje.Personaje;

public class AlianzaTests {

	@Test
	public void probarQueSeCreaAlianzaConExito() //romperAlianzaAlaQuePertenezco()
	{
		Personaje bruno = new Humano("bruno");
		Personaje ivan = new Humano("ivan");
		Personaje pepe = new Humano("pepe");
		
		bruno.crearAlianza(ivan);
		ivan.crearAlianza(pepe);
		
		Assert.assertEquals(3, bruno.obtenerAlianzaAct().cantMiembrosQueHayAlianza());
	}
	
	@Test
	public void probarDeshacerAlianza(){
		
		Personaje nano = new Humano("nano");
		Personaje diego = new Humano("diego");
		Personaje matias = new Humano("matias");
		
		nano.crearAlianza(diego);
		diego.crearAlianza(matias);
		
		Assert.assertEquals(3, nano.obtenerAlianzaAct().cantMiembrosQueHayAlianza());
		
		nano.obtenerAlianzaAct().eliminarAlianza();
		
		Assert.assertEquals(null, nano.obtenerAlianzaAct());
		Assert.assertEquals (null,diego.obtenerAlianzaAct());
		Assert.assertEquals (null,matias.obtenerAlianzaAct());
	}
	
	
	
	@Test
	public void probarQuePuedoAbandonarLaAlianza(){
		

		Personaje bruno = new Humano("bruno");
		Personaje ivan = new Humano("ivan");
		Personaje pepe = new Humano("pepe");
		
		bruno.crearAlianza(ivan);
		ivan.crearAlianza(pepe);
		
		Assert.assertEquals(3, bruno.obtenerAlianzaAct().cantMiembrosQueHayAlianza());
		
		ivan.romperAlianzaAct();
		
		Assert.assertEquals(2, bruno.obtenerAlianzaAct().cantMiembrosQueHayAlianza());
	}
	
}
