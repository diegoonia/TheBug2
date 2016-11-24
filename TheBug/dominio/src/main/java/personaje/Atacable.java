package personaje;


import output.IhasSprite;

public interface Atacable extends IhasSprite{ //Todo objeto que se pueda atacar tiene que poder ser visualizado


	
		void serAtacado(int daño);
		
		int darExperiencia();

		public boolean estaVivo();

		void revivir();

		
		
}
