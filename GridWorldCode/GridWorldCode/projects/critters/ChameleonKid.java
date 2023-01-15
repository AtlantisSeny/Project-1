import info.gridworld.actor.Actor;

import java.util.ArrayList;

public class ChameleonKid extends ChameleonCritter {

    public ArrayList<Actor> getActors() {
        final int behind = 180;

        ArrayList<Actor> neighbors = new ArrayList<Actor>();
        if (getGrid()
                .isValid(getLocation().getAdjacentLocation(getDirection()))) {
            Actor target = getGrid().get(
                    getLocation().getAdjacentLocation(getDirection()));
            if (target != null) {
                neighbors.add(target);
            }
        }
        if (getGrid().isValid(
                getLocation().getAdjacentLocation(getDirection() + behind))) {
            Actor target = getGrid().get(
                    getLocation().getAdjacentLocation(getDirection() + behind));
            if (target != null) {
                neighbors.add(target);
            }
        }
        return neighbors;
    }

}