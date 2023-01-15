import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.awt.*;
import java.util.ArrayList;


public class BlusterCritter extends Critter {
    private int energe;

    public BlusterCritter(int energe_) {
        super();
        energe = energe_;
    }

    public int getenerge() {
        return energe;
    }

    public ArrayList<Actor> getActors() {
        ArrayList<Actor> posR = new ArrayList<Actor>();
        Location posLoc = getLocation();
        for (int i = posLoc.getRow() - 2; i <= posLoc.getRow() + 2; i++)
        {
            for (int j = posLoc.getCol() - 2; j <= posLoc.getCol() + 2; j++)
            {
                Location posTT = new Location(i, j);
                if (getGrid().isValid(posTT))
                {
                    Actor a = getGrid().get(posTT);
                    if (a != null && a != this)
                        posR.add(a);
                }
            }
        }
        return posR;
    }

    public void processActors(ArrayList<Actor> actors) {
        int num = 0;
        final int rgbmax = 255;
        final int ele1 = 3;
        final double ele2 = 0.2;
        
        for (Actor a : actors) {
            if ((a instanceof Critter)
                    && !(a.getLocation().equals(this.getLocation()))) {
                num++;
            }
        }
        if (num > energe) {
            Color c = getColor();
            int red = (int) (c.getRed() * (1 - ele2));
            int green = (int) (c.getGreen() * (1 - ele2));
            int blue = (int) (c.getBlue() * (1 - ele2));
            setColor(new Color(red, green, blue));
        } else if (num < energe) {
            Color c = getColor();
            int red = (int) (c.getRed() * (ele1 + ele2));
            int green = (int) (c.getGreen() * (ele1 + ele2));
            int blue = (int) (c.getBlue() * (ele1 + ele2));
            if (red > rgbmax) {
                red = rgbmax;
            }
            if (green > rgbmax) {
                green = rgbmax;
            }
            if (blue > rgbmax) {
                blue = rgbmax;
            }
            setColor(new Color(red, green, blue));
        }
    }
}