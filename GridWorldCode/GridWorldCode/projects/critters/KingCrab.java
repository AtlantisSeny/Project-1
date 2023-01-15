import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class KingCrab extends CrabCritter {

    public void forceToMove(Actor a, int ac, int ar, int kr, int kc) {
        int targetdir;
        Location target;
        Grid<?> gr = getGrid();

        if (ar < kr)
        {
            if (ac < kc)
                targetdir = Location.NORTHWEST;
            else
                targetdir = Location.NORTHEAST;
        }
        else
        {
            if (ac < kc)
                targetdir = Location.SOUTHWEST;
            else
                targetdir = Location.SOUTHEAST;
        }
        target = a.getLocation().getAdjacentLocation(targetdir);
        if (gr.isValid(target)) {
            a.moveTo(target);
        } else {
            a.removeSelfFromGrid();
        }
    }

    public void processActors(ArrayList<Actor> actors) {
        int kr = getLocation().getRow();
        int kc = getLocation().getCol();
        Grid<?> gr = getGrid();

        for (Actor a : actors) {
            int ar = a.getLocation().getRow();
            int ac = a.getLocation().getCol();
            int rd = ar > kr ? (ar - kr) : (kr - ar);
            int cd = ac > kc ? (ac - kc) : (kc - ac);
            if (rd == 1 && cd == 1) {
                forceToMove(a, ac, ar, kr, kc);
            } else {
                if (gr.isValid(a.getLocation().getAdjacentLocation(
                        getDirection()))) {
                    a.moveTo(a.getLocation()
                            .getAdjacentLocation(getDirection()));
                } else {
                    a.removeSelfFromGrid();
                }
            }
        }
    }
}
