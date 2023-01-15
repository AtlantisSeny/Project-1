import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.*;
public class SparseBoundedGrid2<E> extends AbstractGrid<E> {
    private int numRows, numCols;
    private ArrayList<LinkedList<OccupantInCol>> occupantList;

    @Override
    public boolean isValid(Location loc){
        if(loc.getCol() >= 0 && loc.getRow() >= 0 && getNumRows()>loc.getRow() && loc.getCol() < getNumCols())
            return true;
        else return false;
    };

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

    public ArrayList<LinkedList<OccupantInCol>> getOccupantList() {
        return occupantList;
    }

    public SparseBoundedGrid2(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        occupantList = new ArrayList<LinkedList<OccupantInCol>>();
        for(int i = 0;i < numRows;i++) occupantList.add(new LinkedList<OccupantInCol>());
    }

    @Override
    public ArrayList<Location> getOccupiedLocations(){
        ArrayList<Location> locationList = new ArrayList<Location>();
        for(int i = 0;i < getNumRows();i++){
            LinkedList<OccupantInCol> rowTmp = getOccupantList().get(i);
            if(rowTmp != null){
                for(int j = 0;j < rowTmp.size();j++){
                    OccupantInCol occTmp = rowTmp.get(j);
                    int colTmp = occTmp.getCol();
                    Location location = new Location(i,colTmp);
                    locationList.add(location);
                }
            }
        }
        return locationList;
    }


    @Override
    public E put(Location loc, E obj){
        if(!isValid(loc)){
            throw new IllegalArgumentException("That is wrong");
        }
        E oldTmp = remove(loc);
        OccupantInCol tmp = new OccupantInCol(obj, loc.getCol());
        occupantList.get(loc.getRow()).add(tmp);
        return oldTmp;
    };

    @Override
    public E get(Location loc){
        int rowNumber = loc.getRow();
        LinkedList<OccupantInCol> rowTemp = occupantList.get(rowNumber);
        if(rowTemp == null) return null;
        else{
            for(int i = 0;i < rowTemp.size();i++){
                OccupantInCol occTemp = rowTemp.get(i);
                if(loc.getCol() == occTemp.getCol()){
                    E temp = (E) occTemp.getOccupant();
                    return temp;
                }
            }
        }
        return null;
    }

    @Override
    public E remove(Location loc) {
        E temp = get(loc);
        if (temp == null) return null;
        else {
            LinkedList<OccupantInCol> listTemp = occupantList.get(loc.getRow());
            for (int i = 0; i < listTemp.size(); i++) {
                OccupantInCol colTmp = listTemp.get(i);
                if (colTmp.getCol() == loc.getCol()) {
                    listTemp.remove(i);
                    break;
                }
            }
            return temp;
        }
    }
}
