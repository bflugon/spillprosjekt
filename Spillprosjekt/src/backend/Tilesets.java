package backend;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

import javax.swing.ImageIcon;

public class Tilesets extends Component{
	public static Image[] 	block_tileset,
							base_tileset,
							barrel_tileset,
							button_tileset,
							ammoMenu_tileset,
							barrelMenu_tileset,
							baseMenu_tileset;
	
	public static Image componentsTexture;
	
	public Tilesets(){
		block_tileset = new Image[5];
		for(int i = 0; i < block_tileset.length; i++){
			block_tileset[i] = new ImageIcon("resources/block_tileset.png").getImage();
			block_tileset[i] = createImage(new FilteredImageSource(block_tileset[i].getSource(), new CropImageFilter(0, 60*i, 60, 60)));
		}
		
		base_tileset = new Image[5];
		for(int i = 0; i < block_tileset.length; i++){
			base_tileset[i] = new ImageIcon("resources/base_tileset.png").getImage();
			base_tileset[i] = createImage(new FilteredImageSource(base_tileset[i].getSource(), new CropImageFilter(0, 60*i, 60, 60)));
		}
		
		barrel_tileset = new Image[5];
		for(int i = 0; i < block_tileset.length; i++){
			barrel_tileset[i] = new ImageIcon("resources/barrel_tileset.png").getImage();
			barrel_tileset[i] = createImage(new FilteredImageSource(barrel_tileset[i].getSource(), new CropImageFilter(0, 60*i, 60, 60)));
		}
		
		button_tileset = new Image[5];
		for(int i = 0; i < button_tileset.length; i++){
			button_tileset[i] = new ImageIcon("resources/button_tileset.png").getImage();
			button_tileset[i] = createImage(new FilteredImageSource(button_tileset[i].getSource(), new CropImageFilter(0, 80*i, 80, 80)));
		}
		
		
		//Menu tilesets
		ammoMenu_tileset = new Image[5];
		for(int i = 0; i < ammoMenu_tileset.length; i++){
			ammoMenu_tileset[i] = new ImageIcon("resources/ammoMenu_tileset.png").getImage();
			ammoMenu_tileset[i] = createImage(new FilteredImageSource(ammoMenu_tileset[i].getSource(), new CropImageFilter(0, 200*i, 400, 200)));
		}
		
		barrelMenu_tileset = new Image[5];
		for(int i = 0; i < barrelMenu_tileset.length; i++){
			barrelMenu_tileset[i] = new ImageIcon("resources/barrelMenu_tileset.png").getImage();
			barrelMenu_tileset[i] = createImage(new FilteredImageSource(barrelMenu_tileset[i].getSource(), new CropImageFilter(0, 200*i, 400, 200)));
		}
		
		baseMenu_tileset = new Image[5];
		for(int i = 0; i < baseMenu_tileset.length; i++){
			baseMenu_tileset[i] = new ImageIcon("resources/baseMenu_tileset.png").getImage();
			baseMenu_tileset[i] = createImage(new FilteredImageSource(baseMenu_tileset[i].getSource(), new CropImageFilter(0, 200*i, 400, 200)));
		}
	}
}
