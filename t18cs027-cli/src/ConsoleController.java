import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.Timer;

public class ConsoleController implements ActionListener{

	private static final int DELAY = 50;//DELAY 最初50
	private Model model;
	private Timer timer;

	public ConsoleController(Model m) {
		model = m;
		timer = new Timer(DELAY, this);
	}
	
	public void start() {
		timer.start();
	}

	public void stop() {
		timer.stop();
	}

	public void startrun() throws IOException, InterruptedException { 
		timer.start();
		BufferedReader reader
		= new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		while ((line = reader.readLine()) != null) {
			model.StartProcess(line);
		}
	}

	public void actionPerformed(ActionEvent e) {
		try {
			model.StartProcess("TIME_ELAPSED");
		} catch (InterruptedException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
	}
}





