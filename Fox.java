/** Create a Fox class that extends the Critter class. Set its color to be null. The Fox
always moves to an open neighboring location at random and faces the direction it
has moved. If it cannot move, then it turns in a random direction.

Make the Fox run after the closest Chicken. Find the closest Chicken by using the
distance formula then move to the next empty location in that
direction. If there are no Chickens on the grid, then make a move like 1 above. If several
Chickens are equidistant, then pick one of those directions at random. If there is no open location
in the direction of the closest Chicken, then move to an open neighboring location at random.
*/
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import java.awt.Color;

COMMENTS

public class Fox extends Critter {
  private Chicken nearestChicken;
  private int directionsTried;
  private boolean move;
  private boolean areOtherChickens;
  
  public Fox() {
    setColor(null);
    nearestChicken = new Chicken();
    directionsTried = 0;
    move = true;
    areOtherChickens = true;
  }

  public void move(ArrayList<Location> locs) {
    Location loc1 = getLocation();
    ArrayList<Chicken> chickens = getGrid().getNeightbors(loc1);
    if (chickens.size() == 0)
      areOtherChickens = false;
    
    if (directionsTried == 7) {
      directionsTried = 0;
      move = false;
    }

    if (areOtherChickens) { /////////////////////////////////////////////////////////////////////
      int closestChickenIndex = getCCI(chickens);
    }
    
    else if (move) {
      Location locInDir = loc1.getAdjacentLocation(directionsTried * 45);
      if (getGrid().isValid(locInDir) && getGrid().get(locInDir) == null) {
        setDirection(loc1.getDirectionToward(locInDir));
        moveTo(locInDir);
      }
      else
        directionsTried++;
    }

    else {
      int randDir = (int)(Math.random()*7)
      setDirection(randDir * 45);
    }
  }

  public int getCCI(ArrayList<Chicken> chickens) { /////////////////////////////////////////////////////////////////////
    int chickenIndex = 0;
    for (int i = 0; i < chickens.size(); i++) {
      
    }
  }
}
