package object;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class Door_Object extends SuperObject {
    public Door_Object() {
        name = "Door";
        try {
            FileInputStream fileStream = new FileInputStream("src/res/objects/door.png");
            image = ImageIO.read(fileStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
