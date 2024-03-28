/* Extends Critter. Chicken starts out white. Every step, Chicken randomly
 * chooses to move forward or makes a random turn in any of the 8 compass
 * directions. While the Chicken is young, it has a 1 in 20 percent chance
 * of laying an egg. After 200 steps, the Chicken is middled aged and moves
 * or turns only every other step. After 280 steps, the Chicken is elderly
 * so it starts to gray and only moves every fourth steps. After 300 steps,
 * the Chicken dies and is replaced by a tombstone.
 *
 * @author Karen Ke
 * @since March 27, 2024
 */

imports and comments ///////////////////////////
public class Chicken extends Critter {
  private int steps;
  private int age;  // 1 = young, 2 = middle age, 4 = elderly
  private int nthStep;
  
  public Chicken() {
    steps = 0;
    age = 1;
    nthStep = 0;
    setColor(Color.WHITE);
  }

  public ArrayList<Location> getMoveLocations() {
    ArrayList<Location> moveLocations = new ArrayList<Location>();
    Location forward = getLocation().getAdjacentLocation(getDirection());
    if (getGrid().isValid(forward))
      moveLocations.add(forward);
  }

  public void makeMove(Location loc) {
    if (nthStep == age) {
      int randNum = (int)(Math.random()*10);
      if (randNum == 0 && loc != null) {
        Location currentLoc = getLocation();
        moveTo(loc);
        if (age == 1) {  // if young, has 1 in 20 change of laying egg
          int randNum2 = (int)(Math.random()*20);
          if (randNum2 == 2)
            getGrid().put(currentLoc, new Egg());
        }
      }
      else
          moveTo(getLocation().getAdjacentLocation(randNum * 45));
    }
    else
      nthStep++;

    if (age == 4) {    // if elderly, gets grayer
      Color c = getColor();
      int avg = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
      Color newC = new Color(avg, avg, avg);
      setColor(newC);
    }

    steps++;
    if (steps == 200)
        age = 2;
    else if (steps == 280)
        age = 4;
    else if (steps == 300)
        getGrid().put(getLocation(), new Tombstone());
  }
}
