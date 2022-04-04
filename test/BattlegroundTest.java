import com.lilangel.model.Battleground;
import com.lilangel.model.ObjectOnTile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BattlegroundTest {
    @Test
    public void rightAmountOfRobotsCreated(){
        for (int k = 0; k < 1000; k++){
            Battleground bg = new Battleground();
            var field = bg.getField();
            int counter = 0;
            for(int i = 0; i < field.length;i++)
                for(int j = 0; j < field[i].length; j++)
                    if(field[i][j]== ObjectOnTile.ROBOT)
                        counter++;
            if (100 != counter)
                Assertions.fail();
        }
    }

    @Test
    public void fieldCreationAndTactSpeed(){
        long start = System.currentTimeMillis();
        for (int i = 0; i< 1000; i++){
            Battleground battleground = new Battleground();
            battleground.startFight();
        }
        long end = System.currentTimeMillis();
        System.out.println(((double) end - start)/1000.0);
    }
}
