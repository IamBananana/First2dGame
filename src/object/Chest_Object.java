package object;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class Chest_Object extends SuperObject {
    public Chest_Object() {
        name = "Chest";
        try {
            FileInputStream fileStream = new FileInputStream("src/res/objects/chest.png");
            image = ImageIO.read(fileStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
