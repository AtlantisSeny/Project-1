import info.gridworld.actor.Bug;
public class CircleBug extends Bug
{
    private int t;
    private int edgelen;

    public CircleBug(int l)
    {
        edgelen = l;
        t = 0;
    }

    public void act()
    {
        if (t < edgelen && canMove())
        {
            move();
            t++;
            return;
        }
        turn();
        t = 0;
    }
}
