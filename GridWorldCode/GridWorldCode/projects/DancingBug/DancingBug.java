import info.gridworld.actor.Bug;

public class DancingBug extends Bug
{
    private int steps;
    private int[] dance;
    private int num;
    private int sideLength;

    public DancingBug(int[] danceArr)
    {
        steps = 0;
        num = 0;
        int length = danceArr.length;
        sideLength = 6;
        dance = new int[length];
        System.arraycopy(danceArr, 0, dance, 0, danceArr.length);
    }
    
    public void act()
    {
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else
        {
            for (int i = 0; i < dance[num]; i++){
                turn();
            }
            if (num < dance.length - 1){
                num += 1;
            } else {
                num = 0;
            }
            steps = 0;
        }
    }
}
