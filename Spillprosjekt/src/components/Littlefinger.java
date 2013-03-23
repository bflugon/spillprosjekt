package components;

public class Littlefinger extends TowerComponent {

	public Littlefinger() {
//		Paa denne maaten slipper vi aa sende parametere hver gang, vi bare skriver "new Turret()"
		super(0, 20, -300, false, false, false);
		name = "Littlefinger";
		type = "barrel";
	}
}