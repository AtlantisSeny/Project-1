import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class UnboundedGrid<E> extends AbstractGrid<E> {
    private Object[][] occupantArray;
    public UnboundedGrid()
    {
        occupantArray = new Object[16][16];
    }
    @Override
    public int getNumRows() {
        return -1;
    }

    @Override
    public int getNumCols() {
        return -1;
    }

    @Override
    public boolean isValid(Location location) {
        return location.getCol() >= 0 && location.getRow() >= 0;
    }

    @Override
    public E put(Location location, E o) {
        if(location == null || o == null){
            throw new NullPointerException("Is null");
        }
        else if(!isValid(location)){
            throw new IllegalArgumentException("not valid");
        }
        else{
            E ocp = get(location);
            if (location.getRow() < occupantArray.length && location.getCol() < occupantArray[0].length){
                occupantArray[location.getRow()][location.getCol()] = o;
            }
            else{
                Object[][] tmpArray = occupantArray;
                int len = tmpArray.length;

                while(location.getCol()>=len || location.getRow()>=len){
                    len  = len*2;
                }
                occupantArray = new Object[len][len];
                for(int i = 0;i < len;i++)
                    for(int j = 0;j < len;j++){
                        occupantArray[i][j] = tmpArray[i][j];
                    }
                occupantArray[location.getRow()][location.getCol()] = o;
            }
            return ocp;
        }
    }

    @Override
    public E remove(Location location) {
        if (location == null)
            throw new NullPointerException("loc null");

        if (!isValid(location))
            throw new IllegalArgumentException("not valid");

        E rm = get(location);
        if (location.getRow() < occupantArray.length && location.getCol() < occupantArray.length)
            occupantArray[location.getRow()][location.getCol()] = null;
        return rm;
    }

    @Override
    public E get(Location location) {
        if (location == null)
            throw new NullPointerException("loc null");
        if (!isValid(location))
            throw new IllegalArgumentException("not valid");
        if (location.getRow() >= occupantArray.length || location.getCol() >= occupantArray[0].length)
            return null;
        return (E) occupantArray[location.getRow()][location.getCol()];
    }

    @Override
    public ArrayList<Location> getOccupiedLocations() {
        return null;
    }
}
