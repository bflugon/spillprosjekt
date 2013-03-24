package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import backend.Colors;
import backend.GameData;
import backend.Tilesets;

public class Block extends Rectangle{
	
//	indeksen til blokken i gridet og hva slags blokk det er
	private int indX,
				indY,
				blockID,
				g = 1,
				f;
	
	private Block prev;
	
	private Image texture;
	
//	Staar det er taarn paa blokken?
	private boolean open;
	
	public Block(int x, int y, int indX, int indY, int blockSize){
		this.indX = indX;
		this.indY = indY;
		
		open = true;
		
		setBlockID(0);
		
//		Setter posisjonen til blokken og storelsen
		setBounds(x, y, blockSize, blockSize);
	}
	
//	Er det er taarn paa blokken?
	public boolean isOpen(){
		return open;
	}
	
//	Faa plassen til blokken i brettet
	public int getIndX(){
		return indX;
	}
	public int getIndY(){
		return indY;
	}
	
//	Endre id-en og oppdater teksturen
	public void setBlockID(int blockID){
		this.blockID = blockID;
		texture = Tilesets.block_tileset[blockID];
	}

	public void setF(int f) {
		this.f = f;
	}
	
	public int getG(){
		return g;
	}
	
	public int getF(){
		return f;
	}
	
	public void setG(int g){
		this.g = g;
	}

	public void setPrev(Block prev) {
		this.prev = prev;
	}
	
	public int getBlockID(){
		return blockID;
	}
	
//	Tegner blokken
	public void draw(Graphics g){
		g.drawImage(texture, x, y, width, height, null);
	}

	public Block getPrev() {
		return prev;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
}
