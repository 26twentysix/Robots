import com.lilangel.model.Field;
import com.lilangel.model.RobotsModel;
import com.lilangel.model.ObjectOnTile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BattlegroundTest {
    @Test
    public void rightAmountOfRobotsCreated(){

    }

    @Test
    public void fieldCreationAndTactSpeed(){

    }

    @Test
    public void isSomethingHappening(){
        Field field = new Field();
        field.doTact();
        Assertions.assertTrue(field.hasChanged());
    }
}
