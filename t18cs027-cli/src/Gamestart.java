
public class Gamestart {
		private Model model;
		private char [][] screen;
		private int width;
		private int height;
		//private final static int WIDTH=80;
		//private final static int HEIGHT=24;

		public Gamestart(Model model,int width,int height) {
			this.model=model;
			this.width=width;
			this.height=height;
		screen=new char[width][height];
		}
		
		public void put(char c, int x,int y) {
			if(x<0||x>=width||y<0||y>=height)
				return;
			else
			screen[x][y]=c;
		}
		
		public void drawString(String s, int x,int y) {
			for(int i=0;i<s.length();i++)
				put(s.charAt(i),x+i,y);
		}
		
		public void clear() {
							
		}
	
}
