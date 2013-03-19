package components;

public class Turret extends TowerComponent {

	public Turret() {
<<<<<<< HEAD
		super("Littlefinger",2, 100, 50);
=======
//		Paa denne maaten slipper vi aa sende parametere hver gang, vi bare skriver "new Turret()"
		super(0, 20, -300, false, false, false);
		name = "Turret";
		type = "barrel";
>>>>>>> Noen eksempler på hvor enkelt det ville være å lage komponenter
	}
}