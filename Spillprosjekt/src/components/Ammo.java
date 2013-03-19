package components;

public class Ammo extends TowerComponent {
	
	
	//s: Jeg tenker vi tar slow, splashdamage osv. her i ammoklassen
	private boolean splashDamage,
						slow;
	

	public Ammo(String name, int damage, int range, int firerate, boolean splashDamage, boolean slow) {
		
		super(name,damage, range, firerate);
		
		this.splashDamage = splashDamage;
		this.slow = slow;
	}
	
}
