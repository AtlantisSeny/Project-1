package info.gridworld.Maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;
public class MazeBug extends Bug{
    public Location nextGrid;
    public Location last;
    public Integer countStep = 0;
    boolean flag = false;
    public boolean ending = false;
    public Stack<Location> locationStack = new Stack<>();

    int[] turn = new int[4];

    public MazeBug() {
        setColor(Color.GREEN);
        last = null;
        nextGrid = null;
    }
    /**
     * Moves to the nextGrid location of the square.
     */
    public ArrayList<Location> getValid(Location loc) {
        Grid<Actor> grid = getGrid();
        if (grid == null)
            return null;
        ArrayList<Location> valid = new ArrayList<>();
        for (int i = 0; i < 4; i++)
        {
            Location tmpl = getLocation().getAdjacentLocation(i*90);
            if (grid.get(tmpl) == null)
                valid.add(tmpl);
            else if ((grid.get(tmpl) instanceof Rock) && (grid.get(tmpl).getColor().equals(Color.RED))) {
                ending = true;
                setDirection(getLocation().getDirectionToward(tmpl));
                return null;
            }
        }
        return valid;
    }
    public void act() {
        last = getLocation();
        boolean willMove = canMove();
        if (ending)
        {
            if (!flag)
            {
                flag = true;
                String msg = countStep.toString() + " steps";
                JOptionPane.showMessageDialog(null, msg);

            }
        }
        else if (willMove)
        {
            move();
            countStep++;
        }
        else
        {
            Grid<Actor> grid = getGrid();
            last = locationStack.peek();
            locationStack.pop();
            Location back = locationStack.peek();
            setDirection(getLocation().getDirectionToward(back));
            moveTo(back);
            turn[back.getDirectionToward(last)/90]--;
            nextGrid = null;
            countStep++;
            last = back;
            Flower flower = new Flower(getColor());
            flower.putSelfInGrid(grid, last);
        }
    }




    public void move() {
        Grid<Actor> grid = getGrid();
        if (grid == null)
            return;
        Location location = getLocation();
        if (locationStack.empty()) {
            locationStack.push(getLocation());
        }
        locationStack.push(nextGrid);
        setDirection(getLocation().getDirectionToward(nextGrid));


        moveTo(nextGrid);
        last = nextGrid;
        nextGrid = null;
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(grid, location);


    }

    public boolean canMove() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<Location> nextLoc = getValid(getLocation());

        if (nextLoc == null || nextLoc.isEmpty()) {
            return false;
        } else {
            for (int i = 0; i < nextLoc.size(); i++) {
                int tmp = (getLocation().getDirectionToward(nextLoc.get(i)))/90;
                for (int j = 0; j < turn[tmp]; j++)
                    arrayList.add(i);
            }
            int temp = (int) (Math.random() * arrayList.size());
            nextGrid = (arrayList.isEmpty()) ? nextLoc.get(0) : nextLoc.get(arrayList.get(temp));
            turn[getLocation().getDirectionToward(nextGrid)/90]++;
            return true;
        }
    }


}
