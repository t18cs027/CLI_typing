
public class ConsoleView {
	private Model model;
	private char [][] screen;
	private int width;
	private int height;
	private final static int TITLEH=2;
	private final static int STARTW=33;
	private final static int STARTH=10;
	private final static int RULEW=33;
	private final static int RULEH=13;
	private boolean status;
	private int perspective=49;
	private String sight="";
	private double schoolPoint=100;


	private String InputAlpString []= {"koronanoseidedennkidaigakakaru",
			"yamanashidaigakukonnpyu-tarikougakka",
			"so-syarudhisutannsu",
			"sofutoweasekkeikaihatsuennsyuu",
			"nidoarukotohasanndoaru",
			"ZOOMniteonnrainnzyugyou",
			"tsuyuhasenntakumonogatamaru",
			"hitori10mannennnokyuufukinn",
			"asahapannyorigohann",
			"takedashinngenn",
			"2021nenntoukyouorinnpikku",
			"ashitano12zihatikoumae",
			"mutikokumukesseki",
			"toukyoudhizuni-ranndo",
			"toshimaennheienn",
			"hottarakashionnsenn",
			"kiyosatonomori"
	};
	private String InputJpString []= {"コロナのせいで電気代がかかる",
			"山梨大学コンピュータ理工学科",
			"ソーシャルディスタンス",
			"ソフトウェア設計開発演習",
			"二度あることは三度ある",
			"ZOOMにてオンライン授業",
			"梅雨は洗濯物が溜まる",
			"一人10万円の給付金",
			"朝はパンよりご飯",
			"武田信玄",
			"2021年東京オリンピック",
			"明日の12時ハチ公前",
			"無遅刻無欠席",
			"東京ディズニーランド",
			"としまえん閉園",
			"ほったらかし温泉",
			"清里の森"
	};

	private String ruleString[]= {"<<授業中、先生にばれないようにスマホでチャットをしよう！>>",
			"~ルール説明~",
			"・周期的に教室でキョロキョロする先生がこちらを向いていない間にタイピングを行うゲームである",
			"・先生が左を向いている間にタイピングをすると「内申点」が減る。",
			"・「内申点」が 0 になるとゲームオーバーとなる",
			"・ゲームを開始してから一定時間たつと、チャイムが鳴ってゲーム終了となる",
			"・得点=（正打数ー誤打数）*内申点獲得率となる",};


	//private char [][] gameScreen;


	public ConsoleView(Model model,int width,int height) {
		this.model=model;
		this.width=width;
		this.height=height;
		screen=new char[width][height];
		clear();
	}


	public ConsoleView(int width,int height) {
		this.width=width;
		this.height=height;
		screen=new char[width][height];
	}

	public void clear() {
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				screen[j][i]=' ';
			}
		}
	}

	public void paint() {
		for(int i=0;i<height;i++) { 
			for(int j=0;j<width;j++) 
				System.out.print(screen[j][i]);
			System.out.println(); 
		}
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


	public void changeDown() {
		clear();
		drawString("<授業中、先生にばれないようにスマホでチャットをしよう！>",width/2-ruleString[0].length()/2-7,TITLEH);
		drawString("ゲーム開始",STARTW,STARTH);
		drawString("--> 遊び方",RULEW,RULEH);
		status=false;
		paint();
	}


	public void changeUp() {
		clear();
		drawString("<授業中、先生にばれないようにスマホでチャットをしよう！>",width/2-ruleString[0].length()/2-7,TITLEH);
		drawString("--> ゲーム開始",STARTW,STARTH);
		drawString("遊び方",RULEW,RULEH);
		status=true;
		paint();
	}


	public void settitle() {
		drawString("<授業中、先生にばれないようにスマホでチャットをしよう！>",width/2-ruleString[0].length()/2-7,TITLEH);
		drawString("--> ゲーム開始",STARTW,STARTH);
		drawString("遊び方",RULEW,RULEH);
		status=true;
	}



	public  boolean ArrowKeyStatus() {
		return status;
	}


	public void setGame() {
		screen[width/2][0]='○';
		for(int i=5;i<width-5;i++) {
			for(int j=4;j<height-10;j++) {
				if(i%10==0&&j%4==0) {
					screen[i][j]='□'	;
					screen[i][j+1]='○'	;
					if(j==4&&i==10) {
						screen[i][j+1]='●'	;
						drawString("you-> ",i-6,j+1);
					}
				}		
			}
		}

	}


	public void ShowString(int number) {
		drawString(InputAlpString[number],width/2-InputAlpString[number].length()/2,17);
		drawString(InputJpString[number],width/2-InputAlpString[number].length()/2,19);
	}

	public String StringNumber(int number) {
		return InputAlpString [number];
	}

	public void drawRule() {
		drawString(ruleString[0],14,8);
		for(int i=1;i<7;i++)
			drawString(ruleString[i],1,i+9);
	}


	public boolean searchTeacherPerspective() {
		perspective++;
		if(perspective==151)
			perspective=0;
		if(perspective==50 ||perspective==100||perspective==150)
			return true;
		return false;

	}


	public void setTeacherPerspective() {
		if(perspective==50) {
			sight="-->";
			drawString(sight,width/2+2,0);
		}	
		else if(perspective==100) {
			sight="|V";
			put(sight.charAt(0),width/2,1);
			put(sight.charAt(1),width/2,2);
		}
		else if(perspective==150) {
			sight="<--";
			drawString(sight,width/2-3,0);
		}
	}


	public void clearPerspective() {
		drawString("   ",width/2+2,0);
		drawString(" ",width/2,1);
		drawString(" ",width/2,2);
		drawString("   ",width/2-3,0);
	}


	public boolean CheakDecleaseSchoolPoint() {
		return sight=="<--";				
	}

	public void DecleaseSchoolPoint() {
		schoolPoint--;
	}

	public double returnschoolPoint() {
		return schoolPoint;
	}

	public void finish() {
		clear();
		drawString("GAME OVER",width/2-5,1);
		drawString("正打数",17,height/2);
		drawString("誤打数",23,height/2);
		drawString("内申点補正",30,height/2);
		drawString("得点",41,height/2);

		String trueTyped=model.gettrueTyped()+"";
		String falseTyped=model.getfalseTyped()+"";
		String schoolPointString=schoolPoint+""+"/100.0";

		int score=0;
		schoolPoint= schoolPoint*0.01;
		score=(int) ((model.gettrueTyped()-model.getfalseTyped())*schoolPoint);
		if(score<0)
			score=0;
		String scoreString=score+"";
		String result="(  "+trueTyped+" -  "+falseTyped+"   )  *  "+schoolPointString+"   ==   "+scoreString;
		drawString(result,17,height/2+3);
		paint();

	}


	public void resetPerspective() {
		perspective=49;		
	}


	public void resetSchoolPoint() {
		schoolPoint=100;		
	}


	public void clearString(int number,String typedString) {
		int stringNumberALP=InputAlpString[number].length();
		int stringNumberJP=InputJpString[number].length();
		String nullALP="";
		String nullJP="";
		String nulltyped="";

		for(int i=0;i<stringNumberALP;i++)
			nullALP+=" ";
		for(int i=0;i<stringNumberJP;i++)
			nullJP+=" ";
		for(int i=0;i<typedString.length();i++)
			nulltyped+=" ";


		drawString(nullALP,width/2-InputAlpString[number].length()/2,17);
		drawString(nullJP,width/2-InputAlpString[number].length()/2,19);
		drawString(nulltyped,width/2-InputAlpString[number].length()/2,21);

	}


	public void startView() {
		clear();
		setGame();
		searchTeacherPerspective();
		setTeacherPerspective();
	}


}

