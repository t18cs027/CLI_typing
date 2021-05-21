
public class Bullet {
	private int x;
	private int y;
	private int dx;
	private int dy;
	private String s;
	
	public boolean isOutOfScreen(int width, int height) {
		return x<0||x>=width||y<0||y>=height;
		
	}
	
	public Bullet(String event, int x, int y, int dx, int dy) {
		this.s=event;
	this.x=x;	
	this.y=y;	
	this.dx=1;	
	this.dy=0;	
	}
	
	public void update() {
		x+=dx;
		y+=dy;
	}

	public void paint(ConsoleView view) {
		view.drawString(s, x, y);
	}


/*public static void main(String[] args) {
	Bullet b= new Bullet(2,3,1,0);	
	
	}
*/
}
