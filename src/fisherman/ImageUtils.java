package fisherman;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ImageUtils {
    public static BufferedImage getImage(String path){
        BufferedImage image;
        try {
            image = ImageIO.read(Objects.requireNonNull(ImageUtils.class.getResource(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }
}
