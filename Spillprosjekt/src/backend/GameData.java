package backend;

import graphics.Tower;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;

import components.BasicAmmo;
import components.BasicBarrel;
import components.BasicBase;
import components.Doomsday;
import components.Littlefinger;
import components.MissileLauncher;
import components.TowerComponent;

public class GameData {
	
	public static int 	money = 0,
						score = 0,
						totEnemiesKilled = 0,
						lives = 50;
	
//	index til "base"-ene i tilesetet
	public static final int	basicBase = 0,
							doomsday = 1;
	
//	index til barrel i tilesettet
	public static final int	basicBarrel = 0,
							littlefinger = 1,
							bigMama = 2;
	
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
	public static ArrayList<Tower> modelTowers = new ArrayList<Tower>(Arrays.asList(new Tower(), new Tower()));
	
	public static TowerComponent[] components = {new BasicBarrel(),
												new Littlefinger(),
												new MissileLauncher(),
//												Base
												new BasicBase(),
												new Doomsday(),
//												Ammo
												new BasicAmmo()};
}
