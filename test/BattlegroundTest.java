import com.lilangel.models.Field;
import com.lilangel.models.enums.ModelConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class BattlegroundTest {
    @Test
    public void rightAmountOfRobotsCreated(){

    }

    @Test
    public void fieldCreationAndTactSpeed(){
        var start = Instant.now().toEpochMilli();
        for (int i = 0; i < 1000; i++) {
           Field field = new Field();
           field.doTact();
        }
        var end = Instant.now().toEpochMilli();
        System.out.println((end-start)/1000.0);
    }

    @Test
    public void isSomethingHappening(){
        Field field = new Field();
        field.doTact();
        Assertions.assertTrue(field.hasChanged());
    }

    @Test
    public void coordinatesNormalization(){
        int width = ModelConstants.FIELD_WIDTH.value;
        int height = ModelConstants.FIELD_HEIGT.value;
        int x = -154;
        int y = -79;
        while (x < 0)
            x += width;
        while (x >= width)
            x -= width;
        while (y < 0)
            y += height;
        while (y >= height)
            y -= height;
        System.out.println(x + " " + y);
    }
}
