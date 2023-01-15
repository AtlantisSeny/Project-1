import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Jumper;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JumperTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testCanJumpFir() {
        ActorWorld world = new ActorWorld();
        Jumper jumper = new Jumper();
        Rock rock = new Rock();
        world.add(new Location(2, 3), jumper);
        world.add(new Location(0, 3), rock);
        assertFalse(jumper.canJump());
    }

    @Test
    public void testCanJumpSec() {
        ActorWorld world = new ActorWorld();
        Flower flower = new Flower();
        Jumper jumper = new Jumper();
        world.add(new Location(2, 3), jumper);
        world.add(new Location(0, 3), flower);
        assertTrue(jumper.canJump());
    }

    @Test
    public void testCanJumpThr() {
        Jumper jumper = new Jumper();
        ActorWorld world = new ActorWorld();
        world.add(new Location(0, 9), jumper);
        assertFalse(jumper.canJump());
    }

    @Test
    public void testCanJumpFou() {
        ActorWorld world = new ActorWorld();
        Jumper jumper = new Jumper();
        Rock rock = new Rock();
        world.add(new Location(2, 3), jumper);
        world.add(new Location(1, 3), rock);
        assertTrue(jumper.canJump());
    }

    @Test
    public void testCanJumpFiv() {
        ActorWorld world = new ActorWorld();
        Jumper jumper = new Jumper();
        Flower flower = new Flower();
        world.add(new Location(2, 3), jumper);
        world.add(new Location(1, 3), flower);
        assertTrue(jumper.canJump());
    }
}