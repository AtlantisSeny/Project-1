public class OccupantInCol {
    private Object occupant;
    private int col;

    public OccupantInCol(Object occupant, int col) {
        this.occupant = occupant;
        this.col = col;
    }

    public OccupantInCol(Object occupant) {
        this.occupant = occupant;
    }

    public OccupantInCol(int col) {
        this.col = col;
    }

    public Object getOccupant() {
        return occupant;
    }

    public void setOccupant(Object occupant) {
        this.occupant = occupant;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
