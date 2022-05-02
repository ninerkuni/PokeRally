package MVC;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Tile {
    static public JLabel tile(String element) throws IOException {
        String path = "src/main/";
        BufferedImage Picture;
        if (Objects.equals(element, "Robot1_0")) {
            Picture = ImageIO.read(new File(path+"resources/Cards/ASH_N.png"));
            return new JLabel(new ImageIcon(Picture));
        }
        if (Objects.equals(element, "Robot1_2")) {
            Picture = ImageIO.read(new File(path+"resources/Cards/ASH_S.png"));
            return new JLabel(new ImageIcon(Picture));

        }
        if (Objects.equals(element, "Robot1_1")) {
            Picture = ImageIO.read(new File(path+"resources/Cards/ASH_E.png"));
            return new JLabel(new ImageIcon(Picture));

        }
        if (Objects.equals(element, "Robot1_3")) {

            Picture = ImageIO.read(new File(path+"resources/Cards/ASH_W.png"));
            return new JLabel(new ImageIcon(Picture));
        }
        if (Objects.equals(element, "Robot2_0")) {

            Picture = ImageIO.read(new File(path+"resources/Cards/TEAM_ROCKET_N.png"));
            return new JLabel(new ImageIcon(Picture));

        }
        if (Objects.equals(element, "Robot2_1")) {

            Picture = ImageIO.read(new File(path+"resources/Cards/TEAM_ROCKET_E.png"));
            return new JLabel(new ImageIcon(Picture));

        }
        if (Objects.equals(element, "Robot2_2")) {

            Picture = ImageIO.read(new File(path+"resources/Cards/TEAM_ROCKET_S.png"));
            return new JLabel(new ImageIcon(Picture));

        }
        if (Objects.equals(element, "Robot2_3")) {

            Picture = ImageIO.read(new File(path+"resources/Cards/TEAM_ROCKET_W.png"));
            return new JLabel(new ImageIcon(Picture));

        }

        if (Objects.equals(element, "Trampoline")) {

            Picture = ImageIO.read(new File(path+"resources/Cards/SPOINK.png"));
            return new JLabel(new ImageIcon(Picture));

        }
        if (Objects.equals(element, "Conveyor")) {

            Picture = ImageIO.read(new File(path+"resources/Cards/SNORLAX.png"));
            return new JLabel(new ImageIcon(Picture));

        }
        if (Objects.equals(element, "Pit")) {

            Picture = ImageIO.read(new File(path+"resources/Cards/POND.png"));
            return new JLabel(new ImageIcon(Picture));

        }
        if (Objects.equals(element, "Gear")) {

            Picture = ImageIO.read(new File(path+"resources/Cards/PSYDUCK.png"));
            return new JLabel(new ImageIcon(Picture));

        }
        if (Objects.equals(element, "Checkpoint1")) {

            Picture = ImageIO.read(new File(path+"resources/Cards/CHECKPOINT.png"));
            JLabel Checkpoint1 = new JLabel(new ImageIcon(Picture));
            Checkpoint1.setText("1");
            Checkpoint1.setHorizontalTextPosition(SwingConstants.CENTER);
            Checkpoint1.setVerticalTextPosition(SwingConstants.CENTER);
            Checkpoint1.setForeground(Color.BLACK);
            Checkpoint1.setFont(new Font("Orange Kid", Font.PLAIN, 36));
            return Checkpoint1;

        }
        if (Objects.equals(element, "Checkpoint2")) {

            Picture = ImageIO.read(new File(path+"resources/Cards/CHECKPOINT.png"));
            JLabel Checkpoint2 = new JLabel(new ImageIcon(Picture));
            Checkpoint2.setText("2");
            Checkpoint2.setHorizontalTextPosition(SwingConstants.CENTER);
            Checkpoint2.setVerticalTextPosition(SwingConstants.CENTER);
            Checkpoint2.setForeground(Color.BLACK);
            Checkpoint2.setFont(new Font("Orange Kid", Font.PLAIN, 36));
            return Checkpoint2;
        }
        if (Objects.equals(element, "Checkpoint3")) {

            Picture = ImageIO.read(new File(path+"resources/Cards/CHECKPOINT.png"));
            JLabel Checkpoint3 = new JLabel(new ImageIcon(Picture));
            Checkpoint3.setText("3");
            Checkpoint3.setHorizontalTextPosition(SwingConstants.CENTER);
            Checkpoint3.setVerticalTextPosition(SwingConstants.CENTER);
            Checkpoint3.setForeground(Color.BLACK);
            Checkpoint3.setFont(new Font("Orange Kid", Font.PLAIN, 36));
            return Checkpoint3;
        }

        if (Objects.equals(element, "Checkpoint4")) {

            Picture = ImageIO.read(new File(path+"resources/Cards/CHECKPOINT.png"));
            JLabel Checkpoint4 = new JLabel(new ImageIcon(Picture));
            Checkpoint4.setText("4");
            Checkpoint4.setHorizontalTextPosition(SwingConstants.CENTER);
            Checkpoint4.setVerticalTextPosition(SwingConstants.CENTER);
            Checkpoint4.setForeground(Color.BLACK);
            Checkpoint4.setFont(new Font("Orange Kid", Font.PLAIN, 36));
            return Checkpoint4;
        }

        else if(element != null){
            if(element.contains("Robot1_0")){
                Picture = ImageIO.read(new File(path+"resources/Cards/CL_ASH_N.png"));
            }
            else if(element.contains("Robot1_1")){
                Picture = ImageIO.read(new File(path+"resources/Cards/CL_ASH_E.png"));
            }
            else if(element.contains("Robot1_2")){
                Picture = ImageIO.read(new File(path+"resources/Cards/CL_ASH_S.png"));
            }
            else if(element.contains("Robot1_3")){
                Picture = ImageIO.read(new File(path+"resources/Cards/CL_ASH_W.png"));
            }
            else if(element.contains("Robot2_0")){
                Picture = ImageIO.read(new File(path+"resources/Cards/CLASH_TEAM_ROCKET_N.png"));
            }
            else if(element.contains("Robot2_1")){
                Picture = ImageIO.read(new File(path+"resources/Cards/CLASH_TEAM_ROCKET_E.png"));
            }
            else if(element.contains("Robot2_2")){
                Picture = ImageIO.read(new File(path+"resources/Cards/CLASH_TEAM_ROCKET_S.png"));
            }
            else if(element.contains("Robot2_3")){
                Picture = ImageIO.read(new File(path+"resources/Cards/CLASH_TEAM_ROCKET_W.png"));
            }
            else Picture = ImageIO.read(new File(path+"resources/Cards/CLASH_TILE.png"));
        }


        else {

            Picture = ImageIO.read(new File(path+"resources/Cards/OPEN_TILE.png"));
        }
        return new JLabel(new ImageIcon(Picture));

    }

}
