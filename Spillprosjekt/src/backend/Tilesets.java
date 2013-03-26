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
							aim_tileset;
	
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
			barrel_tileset[i] = createImage(new FilteredImageSource(barrel_tileset[i].getSource(), new CropImageFilter(0, 3*i, 60, 3)));
		}
		
		componentsTexture = new ImageIcon("resources/componentsTexture.png").getImage();
	}
}
