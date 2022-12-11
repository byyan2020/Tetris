package tetris;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoadImage {
    public static BufferedImage pieceI;
    public static BufferedImage pieceJ;
    public static BufferedImage pieceL;
    public static BufferedImage pieceO;
    public static BufferedImage pieceS;
    public static BufferedImage pieceT;
    public static BufferedImage pieceZ;
    public static BufferedImage backImage;

    static {
        try{
            pieceI = ImageIO.read(new File("images/I.png"));
            pieceJ = ImageIO.read(new File("images/J.png"));
            pieceL = ImageIO.read(new File("images/L.png"));
            pieceO = ImageIO.read(new File("images/O.png"));
            pieceS = ImageIO.read(new File("images/S.png"));
            pieceT = ImageIO.read(new File("images/T.png"));
            pieceZ = ImageIO.read(new File("images/Z.png"));
            backImage = ImageIO.read(new File("images/background.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

