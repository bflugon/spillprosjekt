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
import components.Missile;
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
	
	
	public static final int nextWave = 0,
							goToShop = 1,
							goToBoard = 2,
							newTower = 3;
	
	public static final int basicAmmo = 0,
							missile = 1;
	
	
	public static final int grass = 0,
							foundation = 1,
							water = 2,
							start = 3,
							goal = 4;
	
	public static ArrayList<TowerComponent> components_list = new ArrayList<TowerComponent>();
	
	public static final int basicEnemy = 0;
	
	public static Font 	header = new Font("Arial", Font.PLAIN, 30),
						large_header = new Font("Arial", Font.PLAIN, 60),
						normal = new Font("Arial", Font.PLAIN, 20),
						normal_large = new Font("Arial", Font.PLAIN, 40),
						list_stats = new Font("Arial", Font.PLAIN, 10);
	
//	Denne listen inneholder taarnene man har komponert selv. Nye taarn vil kopiere det 
//	onskede taarnet i copy-metoden
	public static ArrayList<Tower> modelTowers = new ArrayList<Tower>();
	
	public static TowerComponent[] components = {new BasicBarrel(),
												new Littlefinger(),
												new MissileLauncher(),
//												Base
												new BasicBase(),
												new Doomsday(),
//												Ammo
												new BasicAmmo(),
												new Missile()};

	public static boolean muted = false;
	public static String[] songs = {"backgroundMusic1.wav","backgroundMusic.wav"};
	public static int songNum = 0;
}
