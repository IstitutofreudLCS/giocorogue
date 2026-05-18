package giocorogue.objects;

public class OBJ_Key extends SuperObjects {

    public OBJ_Key() {
        name = "Key";
        try {
            image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }

}
