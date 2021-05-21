import java.io.IOException;
import java.util.Random;


public class Model {
	private ConsoleView view;
	private ConsoleView startview;
	private ConsoleController controller;
	private final static int WIDTH=80;//80
	private final static int HEIGHT=25;//24
	private Random random=new Random();
	private int ViewStatus;
	private int number;
	private int index=0;
	private String typedstring="";
	private int time=0;
	private int trueTyped=0;
	private int  falseTyped=0;
	private int TIME=1200;
			


	public Model() {
		startview= new ConsoleView(this,WIDTH,HEIGHT);
		view= new ConsoleView(this,WIDTH,HEIGHT);
		controller=new ConsoleController(this);
	}

	public void runStart() throws IOException, InterruptedException{
		startview.settitle();
		view.setGame();
		startview.paint();
		controller.startrun();
		ViewStatus=0;
	}


	public synchronized void StartProcess(String event) throws InterruptedException  {
		if(ViewStatus==0) {
			if (event.equals("TIME_ELAPSED")) {
				;
			}
			if (event.equals("DOWN")) 
				startview.changeDown();
			
			else if(event.equals("UP")) 
				startview.changeUp();

			else if(event.equals(""+"")) {
				if(startview.ArrowKeyStatus()) {
					ViewStatus=1;
					view.startView();
					random = new Random(System.currentTimeMillis());
					number = random.nextInt(17);
					view.ShowString(number);
					view.paint();
				}
				else {
					ViewStatus=2;
					view.clear();
					view.drawRule();
					view.drawString("<ENTERで戻る>",1,18);
					view.paint();
				}
			}

		}
		else if(ViewStatus==1) {
			if (event.equals("TIME_ELAPSED")) {
				time++;
				if(time==TIME) {
					controller.stop();
					view.clearString(number,typedstring);
					view.drawString("<キーンコーンカーンコーン>",WIDTH/2-10,19);
					view.paint();
					Thread.sleep(5000);
					controller.start();
					ViewStatus=4;
				}
				if(view.searchTeacherPerspective() ) {
					view.clearPerspective();
					view.setTeacherPerspective();
					view.paint();
				}
			}
			else {
				if(view.CheakDecleaseSchoolPoint()) 
					view.DecleaseSchoolPoint();
				
				if(view.returnschoolPoint()==0)
					ViewStatus=4;
				
				if(event.equals(view.StringNumber(number).charAt(index)+"")) {
					typedstring+=event;
					index++;
					trueTyped++;
					view.drawString(typedstring,WIDTH/2-view.StringNumber(number).length()/2,21);
					view.paint();
				}	
				
				else if(!event.equals(view.StringNumber(number).charAt(index)+"")){
				falseTyped++;
				}
				
			}

			if(view.StringNumber(number).equals(typedstring)) {
				view.clearString(number,typedstring);
				number = random.nextInt(15);
				index=0;
				typedstring="";
				view.setGame();
				view.ShowString(number);
				view.paint();	
			}
		}



		else if(ViewStatus==2) {
			if (event.equals("TIME_ELAPSED")) 
				;
			else if(event.equals(""+"")) {
				ViewStatus=0;
				startview.paint();

			}
		}	
		else if(ViewStatus==4) {
			controller.stop();
			view.finish();
			Thread.sleep(12000);
			view.clear();
			reset();
			ViewStatus=0;
		}

	}
	
	private void reset() {
		number=0;
		index=0;
		typedstring="";
		time=0;
		trueTyped=0;
		falseTyped=0;
		startview.clear();
		view.clear();
		startview.settitle();
		view.setGame();
		view.resetPerspective();
		view.resetSchoolPoint();
		startview.paint();		
		controller.start();
	}

	public static void main(String[] args) throws IOException, InterruptedException  {
		Model model=new Model();
		model.runStart();
	}

	public int gettrueTyped() {
				return trueTyped;
	}

	public int getfalseTyped() {
				return falseTyped;
	}
	
	public  int getnumber() { 
	return number;
	}
	
}




