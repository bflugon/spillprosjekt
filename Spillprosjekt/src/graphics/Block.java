package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Block extends Rectangle{
	
//	indeksen til blokken i gridet og hva slags blokk det er
	private int indX,
				indY,
				blockID;
	
	private Image texture;
	
//	Staar det er taarn paa blokken?
	private boolean open;
	
	public Block(int x, int y, int indX, int indY, int blockSize){
		this.indX = indX;
		this.indY = indY;
		
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
	public void setID(int blockID){
		this.blockID = blockID;
//		texture = Tilesets.block_tileset[blockID];
	}
	
//	Tegner blokken
	public void draw(Graphics g){
		g.setColor(Color.GREEN);
		
		if (texture != null)g.drawImage(texture, x, y, width, height, null);
		else g.fillRect(x,y,width,height);
	}
}