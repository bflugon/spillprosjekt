package components;

public class BigMama extends TowerComponent {

	public BigMama() {
//		Paa denne maaten slipper vi aa sende parametere hver gang, vi bare skriver "new Canon()"
		super(8, 0, -100, false, false, false);
		name = "Big Mama";
		type = "barrel";
	}
}
