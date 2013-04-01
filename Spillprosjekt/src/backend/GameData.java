package backend;

import graphics.Tower;

import java.awt.Font;
import java.util.ArrayList;

import components.BasicBarrel;
import components.BasicBase;
import components.Littlefinger;
import components.MissileLauncher;
import components.TowerComponent;

public class GameData {
	
	public static int 	money = 0,
						score = 0,
						totEnemiesKilled = 0,
						lives = 50;
	
//	index til "base"-ene i tilesetet
	public static final int	basicBase = 0;
	
//	index til barrel i tilesettet
	public static final int	basicBarrel = 0,
							littlefinger = 2,
							bigMama = 1;
	
	public static final int basicAmmo = 0;
	
	
	public static final int grass = 0,
							foundation = 1,
							water = 2,
							start = 3,
							goal = 4;
	
	public static Font 	header = new Font("Arial", Font.PLAIN, 30),
						normal = new Font("Arial", Font.PLAIN, 20);
	
//	Denne listen inneholder taarnene man har komponert selv. Nye taarn vil kopiere det 
//	onskede taarnet i copy-metoden
	public static ArrayList<Tower> modelTower = modelTower = new ArrayList<Tower>();
	
	public static TowerComponent[] components = {new BasicBarrel(),
												new Littlefinger(),
												new BasicBase(),
												new MissileLauncher()};

	public static Tower[] modelTowers = {new Tower(), new Tower(), new Tower(), new Tower(), new Tower()};

}
