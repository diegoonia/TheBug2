package personaje;

import java.awt.Point;

public class Holograma extends Personaje{
	
	Holograma()
	{
		
			super();
			super.setSpritePath("<Hardcodee el sprite aqui>");
		
	}

	@Override
	public boolean puedeAtacar() {
		return energia >= 10;
	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPosition(Point p) {
		// TODO Auto-generated method stub
		
	}



	
}
