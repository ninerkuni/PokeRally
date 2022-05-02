package MVC;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class CardImages {
    //static method to match the card signatures to the .png images
    static public ImageIcon card(String image) throws IOException{
        //exception is handled in the user interface
        String path = "src/main/resources/Cards/";
        BufferedImage Picture = null;
        if (Objects.equals(image, "move1Forward")) {
                Picture = ImageIO.read(new File(path + "FORWARD.png"));
            }
        else if (Objects.equals(image, "move2Forward")) {
               Picture = ImageIO.read(new File(path + "FORWARD2.png"));
            }
        else if (Objects.equals(image, "turnR")) {
                Picture = ImageIO.read(new File(path + "RIGHT.png"));
            }
        else if (Objects.equals(image, "turnL")) {
                Picture = ImageIO.read(new File(path + "LEFT.png"));
            }
        else if (Objects.equals(image, "turn180")) {
            Picture = ImageIO.read(new File(path + "turn180.png"));
        }
        else if (Objects.equals(image, "Card1")) {
            Picture = ImageIO.read(new File(path + "CARD1.png"));
        }
        else if (Objects.equals(image, "Card2")) {
            Picture = ImageIO.read(new File(path + "CARD2.png"));
        }
        else if (Objects.equals(image, "Card3")) {
            Picture = ImageIO.read(new File(path + "CARD3.png"));
        }
        else if (Objects.equals(image, "Card4")) {
            Picture = ImageIO.read(new File(path + "CARD4.png"));
        }
        else if (Objects.equals(image, "Card5")) {
            Picture = ImageIO.read(new File(path + "CARD5.png"));
        }
        else{
            //if none of the signatures match, return null
            return null;
        }
        return new ImageIcon(Picture);
    }
}
