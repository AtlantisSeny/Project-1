import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SparseBoundedGrid<E> extends AbstractGrid<E> {
    private int numRows, numCols;
    private Map<Location, E> gridMap;

    public SparseBoundedGrid(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        gridMap = new HashMap<Location, E>();
    }

    @Override
    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    @Override
    public int getNumCols() {
        return numCols;
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }

    @Override
    public E put(Location loc, E obj) {
        if(isValid(loc) && obj != null)
            return gridMap.put(loc,obj);
        else {throw new IllegalArgumentException("Wrong");}
    }

    ;

    @Override
    public E remove(Location loc) {
        return gridMap.remove(loc);
    }

    ;

    @Override
    public boolean isValid(Location loc) {
        if (loc.getCol() >= 0 && loc.getRow() >= 0 && getNumRows() > loc.getRow() && loc.getCol() < getNumCols()) {
            return true;
        } else
            return false;
    }

    ;

    @Override
    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> tempList = new ArrayList<Location>();
        tempList.addAll(gridMap.keySet());
        return tempList;
    }

    @Override
    public E get(Location loc){
        return gridMap.get(loc);
    }

}
