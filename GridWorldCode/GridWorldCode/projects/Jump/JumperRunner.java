import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Jumper;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

import java.awt.Color;


public class JumperRunner
{
    public JumperRunner(){

    }
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        Jumper bug = new Jumper(Color.BLUE);
        world.add(new Location(4, 4), bug);
        world.add(new Location(1, 2), new Rock());
        world.add(new Location(3, 1), new Flower());
        world.show();
    }
}