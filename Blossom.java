/* Create	a	new	class	called	Blossom	that	extends	the	Flower	class.	The	Blossom	starts	out	green	and	gets	darker	just	like	a	
Flower.	A	Blossom	has	a	lifetime,	and	at	the	end	of	its	lifetime	it	is	removed	from	the	grid	(removeSelfFromGrid	method	
in	the	Actor	API).	Create	two	constructors	for	the	Blossom.	A	no-args	constructor	that	sets	the	lifetime	to	10	(steps),	and	
a	second	constructor	with	a	parameter	that	specifies	the	lifetime.

b)	 Each	Blossom	has	a	random	lifetime,	some	short	and	some	long.
*/
import java.awt.Color;

public class Blossom extends Flower {
	private int lifetime;	// the amount of "lifes" the blossom has left
	private static final double DARKENING_FACTOR = 0.05;
	
	public Blossom() {
		lifetime = 10;
		setColor(Color.GREEN);
	}
	
	public Blossom (int lifetimeIn) {
		lifetime = lifetimeIn;
		setColor(Color.GREEN);
	}
	
	 /**
     * Causes the color of this flower to darken.
     */
    public void act()
    {
		lifetime--;
		if (lifetime == 0)
			removeSelfFromGrid();
		
		else {
			Color c = getColor();
			int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
			int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
			int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));

			setColor(new Color(red, green, blue));			
		}
    }
}
