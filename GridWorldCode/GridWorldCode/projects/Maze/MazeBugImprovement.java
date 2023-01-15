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


public class MazeBugImprovement extends Bug {
    public Location nextgrid;
    public boolean ending = false;

    boolean flag = false;
    public Stack<ArrayList<Location>> locationStack = new Stack<>();
    public Integer countStep = 0;
    public Stack<Location> theRoad = new Stack<>();

    private final int[] turn = {1, 1, 1, 1};

    public MazeBugImprovement() {
        setColor(Color.GREEN);
    }

    public void act() {
        //add the initial location to the first array list
        if(countStep ==0){
            destPredict();
            Location location = this.getLocation();

            ArrayList<Location> firstList = new ArrayList<>();
            firstList.add(location);
            locationStack.add(firstList);
            theRoad.push(location);
        }

        boolean willMove = canMove();
        if (ending)
        {
            setRightWay(theRoad);
            if (!flag)
            {
                String msg = countStep.toString() + " steps";
                JOptionPane.showMessageDialog(null, msg);
                flag = true;
            }
        }
        else if (willMove)
        {
            countStep++;
            move();
        }
        else goBack();
    }


    public ArrayList<Location> getValid(Location loc) {
        Grid<Actor> grid = getGrid();
        if (grid == null) return null;
        ArrayList<Location> arrayList = new ArrayList<>();

        int[] dir = new int[4];
        dir[0] = Location.NORTH;
        dir[1] = Location.EAST;
        dir[2] = Location.SOUTH;
        dir[3] = Location.WEST;
        for(int i = 0; i < 4; i++){
            Location location = loc.getAdjacentLocation(dir[i]);
            if(grid.isValid(location)){
                Actor actor = grid.get(location);
                //if the goal is around, return the location of the goal
                if(( actor instanceof Rock) && actor.getColor().equals(Color.RED) ){
                    nextgrid = location;
                    ArrayList<Location> tar = new ArrayList<>();
                    tar.add(nextgrid);
                    return tar;
                }else if(actor == null){
                    arrayList.add(location);
                }
            }
        }
        return arrayList;
    }


    public void move() {
        Grid<Actor> grid = getGrid();
        if (grid == null)
            return;
        Location loc = this.getLocation();

        ArrayList<Location> chooseLocation = getValid(loc);
        int maximum = 0;
        int tmp = 0;
        int total = 0;
        int choose = 0;
        for( Location location : chooseLocation ){
            int direction = loc.getDirectionToward(location);
            if( maximum < turn[direction/90] ){
                tmp = direction /90;
                choose = total;
                maximum = turn[direction/90];
            }
            total++;
        }

        if(chooseLocation.size() == 1){
            nextgrid = chooseLocation.get(choose);
        }else {
            int temp = (int) (Math.random() * 10);
            if(temp >= 0 && temp < 7){
                nextgrid = chooseLocation.get(choose);
            }else {
                nextgrid = chooseLocation.get(temp % chooseLocation.size());
                int direction = loc.getDirectionToward(nextgrid);
                tmp = direction / 90;
            }
        }
        turn[tmp]++;

        for( Location location : chooseLocation ){
            if( this.getDirection() == this.getLocation().getDirectionToward(location) ){
                nextgrid = location;
                int direction = loc.getDirectionToward(nextgrid);
                tmp = direction / 90;
                turn[tmp]++;
                break;
            }
        }

        if (grid.isValid(nextgrid)) {
            Actor actor = grid.get(nextgrid);

            if( actor instanceof Rock && actor.getColor().equals(Color.RED) ){
                ending = true;
                setRightWay(theRoad);
            }
            moveTo(nextgrid);
            theRoad.push(nextgrid);
            int toward = loc.getDirectionToward(nextgrid);
            this.setDirection(toward);

            ArrayList<Location> temp = locationStack.pop();
            temp.add(nextgrid);
            locationStack.push(temp);

            ArrayList<Location> arrayList = new ArrayList<>();
            locationStack.push(arrayList);

            arrayList.add(nextgrid);

        } else {
            removeSelfFromGrid();
        }
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(grid, loc);
    }
    public boolean canMove() {
        ArrayList<Location> validLocation;
        Location current = this.getLocation();

        validLocation = getValid(current);

        return !validLocation.isEmpty();
    }
    public void setRightWay(Stack<Location> locations){
        for( Location location : locations ){
            Grid<Actor> grid = getGrid();
            Actor act = grid.get(location);
            act.setColor(Color.GREEN);
        }
    }

    public void destPredict(){
        Grid<Actor> grid = getGrid();
        ArrayList<Location> arrayList = grid.getOccupiedLocations();

        for( Location location : arrayList ){
            Actor act = grid.get(location);
            if ( act instanceof Rock && act.getColor().equals(Color.RED)){
                Location loc = this.getLocation();
                if( loc.getCol() >= location.getCol() ){
                    turn[1] = 5;
                    turn[3] = 2;
                }else {
                    turn[3] = 5;
                    turn[1] = 2;
                }
                if( loc.getRow() >= location.getRow() ){
                    turn[2] = 2;
                    turn[0] = 5;
                }else {
                    turn[0] = 5;
                    turn[2] = 2;
                }
                break;
            }
        }
    }
    public void goBack(){
        if (!locationStack.empty()) {
            theRoad.pop();
            locationStack.pop();

            if(!locationStack.empty()){
                Grid<Actor> grid = getGrid();
                if( grid == null )
                    return;
                ArrayList<Location> back = locationStack.peek();
                Location returnLocation = back.get(0);

                Location current = this.getLocation();

                int direction = current.getDirectionToward(returnLocation);
                if (grid.isValid(returnLocation)) {
                    this.setDirection(direction);
                    moveTo(returnLocation);
                    countStep++;
                }else {
                    removeSelfFromGrid();
                }
                if ( (direction/90) == 0 ) {
                    turn[2]--;
                }else if ( (direction/90) == 1 ) {
                    turn[3]--;
                }else if ( (direction/90) == 2 ) {
                    turn[0]--;
                }else if ( (direction/90) == 3 ) {
                    turn[1]--;
                }
                Flower flower = new Flower(getColor());
                flower.putSelfInGrid(grid, current);
            }
        }
    }

}
