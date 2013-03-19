package components;

public class Canon extends TowerComponent {

	public Canon() {
//		Paa denne maaten slipper vi aa sende parametere hver gang, vi bare skriver "new Canon()"
		super(8, 0, -100, false, false, false);
		name = "Big Mama";
		type = "barrel";
	}
}
