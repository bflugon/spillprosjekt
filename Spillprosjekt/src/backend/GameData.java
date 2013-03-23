package backend;

import graphics.Tower;

import java.util.ArrayList;

public class GameData {
	
	public static int 	money = 0,
						score = 0,
						totEnemiesKilled = 0,
						lives = 50;
	
//	index til "base"-ene i tilesetet
	public static int	basicBase = 0;
	
//	index til barrel i tilesettet
	public static int	basicBarrel = 0,
						littlefinger = 1,
						bigMama = 2;
	
//	index til siktet i tilesettet
	public static int noe;
	
	
	public static final int grass = 0,
							foundation = 1,
							water = 2,
							start = 3,
							goal = 4;
	
//	Denne listen inneholder taarnene man har komponert selv. Nye taarn vil kopiere det 
//	onskede taarnet i copy-metoden
	public static ArrayList<Tower> modelTower = modelTower = new ArrayList<Tower>();;
}
