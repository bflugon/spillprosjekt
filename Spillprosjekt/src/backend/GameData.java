package backend;

import graphics.Enemy;
import graphics.Tower;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;

import components.Ammo;
import components.Barrel;
import components.Base;
import components.Inventory;
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
	public static final int	basicBarrel = 3,
							littlefinger = 1,
							bigMama = 2;
	
	
	public static final int nextWave = 0,
							goToShop = 1,
							goToBoard = 2,
							newTower = 3,
							mute = 4;
	
	public static final int basicAmmo = 0,
							missile = 1;
	
	
	public static final int grass = 0,
							foundation = 1,
							water = 2,
							start = 3,
							goal = 4;
	
	public static ArrayList<Barrel> barrels = new ArrayList<Barrel>();
	public static ArrayList<Ammo> ammo = new ArrayList<Ammo>();
	public static ArrayList<Base> bases = new ArrayList<Base>();
	
	public static final int spaceInvader = 0,
							pacGhost = 1;
	
	public static Font 	header = new Font("Arial", Font.PLAIN, 30),
						largeHeader = new Font("Arial", Font.PLAIN, 60),
						normal = new Font("Arial", Font.PLAIN, 20),
						normalLarge = new Font("Arial", Font.PLAIN, 40),
						small = new Font("Arial", Font.PLAIN, 15);
	
//	Denne listen inneholder taarnene man har komponert selv. Nye taarn vil kopiere det 
//	onskede taarnet i copy-metoden
	public static ArrayList<Tower> modelTowers = new ArrayList<Tower>();
	
	public static Enemy[] enemies;
	
	public static Inventory invetar = new Inventory();

	
	public static boolean muted = false;
	
	
	
	public static String[] songs = {"backgroundMusic1.wav","backgroundMusic.wav"};
	public static int songNum = new Random().nextInt(2);
}
