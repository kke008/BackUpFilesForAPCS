/* Make a new	directory	Activity3	inside	the	GridWorldCode	directory	(GridWorldCode/Activity3).	Do	all	your	work	in	
this	directory.	(Note:	This	activity	is	different	than	what	is	mentioned	in	the	GridWorld	Student	Manual.)

Create	a	new	class	called	Jumper	that	extends	the	Bug	class.	Jumper	is	the	color	blue.	This	actor	can	move	forward	two	
cells	in	each	move,	so	it	can	“jump”	over	obstacles,	like	rocks	and	flowers	(and	blossoms).	If	there	are	no	empty	cells	two	
away	in	its	current	direction,	it	tries	new	directions	until	it	finds	and	moves	to	an	empty	cell	two	away.	If	it	moves	in	a	
different	direction,	it	faces	in	the	direction	it	just	moved.	Instead	of	leaving	a	Flower	like	a	Bug	does,	it	leaves	a	Blossom
when	it	moves.
Here	are	some	other	things	a	Jumper	should	do.
a)	 If	all	of	the	cells	two	away	are	occupied,	the	Jumper	moves	one	cell	and	tries	to	jump	again.	(You	don’t	want	an	
infinite	loop.)

c)	 The	Jumper	has	a	parameter	that	determines	how	far	it	will	move	in	one	direction	without	obstacles	before	it	
changes	direction.
d)	 A	Jumper	does	not	land	on	Blossoms.
F)	Download	the	JumperRunner	from	the	web	site	to	test	Jumper.	The	code	is	shown	below.
*/
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;

import java.awt.Color;

public class Jumper extends Bug {
	private int maxInOneDirection;	//////////////////////////////////////////////////////////////////
	private int turned;		// number of times Jumper has turned trying to move
	private boolean jumpTwo;	// true if Jumper is trying to jump two spots away
	
	public Jumper() {
		setColor(Color.BLUE);
		maxInOneDirection = 10;
		turned = 0;
		jumpTwo = true;
	}
	
	 /**
     * Moves if it can move, turns otherwise.
     */
    public void act()
    {
        if (canMove()) {
            move();
            turned = 0;
		}
        else {
            turn();
            turned++;
            if (turned == 8) {
				turned = 0;
				jumpTwo = false;
			}
		}
    }

    /**
     * Turns the bug 45 degrees to the right without changing its location.
     */
    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

    /**
     * Moves the bug forward, putting a blossom into the location it previously
     * occupied.
     */
    public void move()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (jumpTwo)
			next = next.getAdjacentLocation(getDirection());
        if (gr.isValid(next))
            moveTo(next);
        else
            removeSelfFromGrid();
        Blossom blossom = new Blossom();
        blossom.putSelfInGrid(gr, loc);
    }

    /**
     * Tests whether this bug can move forward into a location that is empty or
     * contains a flower.
     * @return true if this bug can move.
     */
    public boolean canMove()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (jumpTwo)
			next = next.getAdjacentLocation(getDirection());
        if (!gr.isValid(next))
            return false;
        Actor neighbor = gr.get(next);
        return (neighbor == null) || (neighbor instanceof Blossom);
        // ok to move into empty location or onto blossom
        // not ok to move onto any other actor
    }
}
