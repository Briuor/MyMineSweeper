
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author briuo
 */
public class Sprite {

    private static BufferedImage spriteSheet;

    public static BufferedImage loadSprite(String file) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(new File(file+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sprite;
    }
    
    public static BufferedImage getSprite(int xGrid, int yGrid, int w, int h) {
        if(spriteSheet == null){
           spriteSheet = loadSprite("spritesheet2");
        }
        
        return spriteSheet.getSubimage(xGrid, yGrid, w, h);
    }
}
