package object;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class Key_Object extends SuperObject {
    public Key_Object() {
        name = "Key";
        try {
            FileInputStream fileStream = new FileInputStream("src/res/objects/key.png");
            image = ImageIO.read(fileStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
