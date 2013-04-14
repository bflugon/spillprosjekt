package editor;

import graphics.Block;
import graphics.Board;

public class Editor {
	int id = -1;
	Board board = new Board();
	//last board fra et default board
	
	public static void main() {
		//tegne brettet
		//tegne blokker under brettet for å velge id
		//tegne en currently-selected blokk med this.id = id
	}
	
	public void setBlock(Block block) {
		block.setBlockID(id);
	}
	
	public void setID(Block block) {
		id = block.getBlockID();
	}
}
