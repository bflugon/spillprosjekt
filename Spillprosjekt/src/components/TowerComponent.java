package components;

public class TowerComponent {
	private String name;
	
	private int damage,
				firerate,
				splashdamage,
				range;
	
	private boolean splashDamage,
					slow;
	
	public TowerComponent(String name, int damage, int range, int firerate){
		
		
		this.name = name;
		this.damage = damage;
		this.firerate = firerate;
		this.range = range;
		

	}
	
	public String getName(){
		return name;
	}
	
	public int getDamage(){
		return damage;
	}
	
	public int getFirerate(){
		return firerate;
	}
	
	public int getRange(){
		return range;
	}
	
	public boolean getSplashDamage(){
		return splashDamage;
	}

	public boolean getSlow(){
		return slow;
	}
}
