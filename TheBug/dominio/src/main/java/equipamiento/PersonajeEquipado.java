package equipamiento;
import personaje.*;
import output.Sprite;

public abstract class PersonajeEquipado extends Personaje{ //TODO: no me esta convenciendo extender personaje. Capaz una clase llamada equipo y que sea un atributo de personaje? Asi como esta, un equipamiento tiene que poder moverse, atacar, etc.

	Personaje personajeDecorado;
	int nivelItem;
	String nombre;
	
	public PersonajeEquipado(Personaje personajeDecorado) {
		this.personajeDecorado = personajeDecorado;
	}

	@Override
	public boolean puedeAtacar() {
		return this.personajeDecorado.puedeAtacar();
	}

	@Override
	public int obtenerPuntosDeFuerza() {
		return this.personajeDecorado.obtenerPuntosDeFuerza();
	}
	
	@Override
	public int obtenerPuntosDeDefensa() {
		return this.personajeDecorado.obtenerPuntosDeDefensa();
	}
	
	@Override
	public int obtenerPuntosDeSalud() {
		return this.personajeDecorado.obtenerPuntosDeSalud();
	}

	@Override
	public int obtenerPuntosDeEnergia() {
		return this.personajeDecorado.obtenerPuntosDeEnergia();
	}

	@Override
	public int obtenerPuntosDeIngenio() {
		return this.personajeDecorado.obtenerPuntosDeIngenio();
	}

	public String getSpritepath() {
		// TODO Auto-generated method stub
		return this.personajeDecorado.getSpritePath();
	}
	
	public Sprite getSprite() {
		// TODO Auto-generated method stub
		return this.personajeDecorado.getSprite();
	}
	
	public void setSpritePath(String path){
		this.personajeDecorado.setSpritePath(path);
	}
	
}
