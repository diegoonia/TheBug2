package npc;

import output.Sprite;
import personaje.Atacable;

public abstract class Npc implements Atacable{
	protected String nombre;
	protected int salud;
	protected int saludpornivel=this.nivel*100;
	protected int nivel = 1;
	
	public boolean estaVivo() {
		return this.salud > 0;
	}
	
	public void serAtacado(int daño) {
		this.salud -= daño;
	}
	
	public void serCurado() {
		this.salud = this.saludpornivel;
	}
	
	public int obtenerPuntosDeSalud() {
		return this.salud;
	}
	
	@Override
	public int darExperiencia() {
		return this.nivel*10; // Cuando lo matas te da de experiencia su nivel*10
	}

	public int getSalud() {
		return salud;
	}

	public void setSalud(int salud) {
		this.salud = salud;  
	}
	
	public void revivir() {
		this.serCurado(); //solo le da salud, no lo energiza, es para los Test nada mas.
	}
	
	public Sprite getSprite()
	{return new Sprite();} //Si alguna ves usamos esto, que alguien ponga un sprite de verdad.
	
}
