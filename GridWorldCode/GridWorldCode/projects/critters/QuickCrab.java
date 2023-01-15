import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class QuickCrab extends CrabCritter {
    public ArrayList<Location> getMoveLocations() {
        final int turn = 90;
        ArrayList<Location> locs = new ArrayList<Location>();
        canGoDirectionTwice(locs, getDirection() + turn);
        canGoDirectionTwice(locs, getDirection() - turn);
        if (locs.size() > 0) {
            return locs;
        } else {
            return super.getMoveLocations();
        }
    }

    public void canGoDirectionTwice(ArrayList<Location> locs, int direction) {
        Grid<?> gr = getGrid();
        Location loc = getLocation();
        Location target = loc.getAdjacentLocation(direction);
        if (gr.isValid(target) && (gr.get(target) == null)) {
            loc = target;
            target = loc.getAdjacentLocation(direction);
            if (gr.isValid(target) && (gr.get(target) == null)) {
                locs.add(target);
            }

        }
    }

    public void makeMove(Location loc) {
        final double factor = 0.5;
        if (loc.equals(getLocation()))
        {
            double r = Math.random();
            int angle;
            if (r < factor) {
                angle = Location.LEFT;
            } else {
                angle = Location.RIGHT;
            }
            setDirection(getDirection() + angle);
        }
        else
        {
            super.makeMove(loc);
        }
    }

}