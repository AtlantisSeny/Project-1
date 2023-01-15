

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public class MazeBugRunner {
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(0,0), new info.gridworld.Maze.MazeBugImprovement());
        world.add(new Location(1,1),new Rock());
        world.show();
    }
}
