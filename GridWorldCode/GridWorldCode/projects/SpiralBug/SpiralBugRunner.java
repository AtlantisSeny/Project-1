import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

public class SpiralBugRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        SpiralBug alice = new SpiralBug(6);
        alice.setColor(Color.RED);
        SpiralBug bob = new SpiralBug(3);
        world.add(new Location(7, 8), alice);
        world.show();
    }
}
