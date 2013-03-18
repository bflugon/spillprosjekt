package components;

public class TowerComponent {
	private int damage,
				firerate,
				range;
	
	private boolean splashDamage,
					slow;
	
	public TowerComponent(int damage, int range, int firerate, boolean splashDamage, boolean slow){
		this.damage = damage;
		this.firerate = firerate;
		this.range = range;
		
		this.splashDamage = splashDamage;
		this.slow = slow;
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
