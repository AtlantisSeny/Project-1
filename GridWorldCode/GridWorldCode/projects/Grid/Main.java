import info.gridworld.actor.*;
import info.gridworld.grid.Location;


public class Main {
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        world.addGridClass("SparseBoundedGrid");
        world.addGridClass("SparseBoundedGrid2");

        world.add(new Location(2,2), new Critter());
        world.show();

        world.show();
    }
}
