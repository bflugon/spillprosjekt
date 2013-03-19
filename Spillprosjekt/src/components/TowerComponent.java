package components;

public class TowerComponent {
<<<<<<< HEAD
	private String name;
	
	private int damage,
				firerate,
				splashdamage,
				range;
=======
	protected String 	name,
						type;
	
	private double 	damage,
					firerate,
					range;
>>>>>>> Noen eksempler på hvor enkelt det ville være å lage komponenter
	
	private boolean splashDamage,
					slow, 
					radar;
	
<<<<<<< HEAD
	public TowerComponent(String name, int damage, int range, int firerate){
		
		
		this.name = name;
=======
	public TowerComponent(double damage, double range, double firerate, boolean splashDamage, boolean slow, boolean radar){
>>>>>>> Noen eksempler på hvor enkelt det ville være å lage komponenter
		this.damage = damage;
		this.firerate = firerate;
		this.range = range;
		
<<<<<<< HEAD

	}
	
	public String getName(){
		return name;
=======
		this.splashDamage = splashDamage;
		this.slow = slow;
		this.radar = radar;
>>>>>>> Noen eksempler på hvor enkelt det ville være å lage komponenter
	}
	
	public double getDamage(){
		return damage;
	}
	
	public double getFirerate(){
		return firerate;
	}
	
	public double getRange(){
		return range;
	}
	
	public boolean getSplashDamage(){
		return splashDamage;
	}

	public boolean getSlow(){
		return slow;
	}
	
	public boolean getRadar(){
		return radar;
	}
	
	public String getName(){
		return name;
	}
}
