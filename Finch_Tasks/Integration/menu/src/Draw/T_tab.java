package Draw;

import org.eclipse.swt.widgets.Display;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.util.logging.Logger;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.SimpleFormatter;


import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;


public class T_tab {
	
	private final static Logger Loggf = Logger.getLogger("LogFile"); //  name of log 



	protected static final Level INFO = null;



	protected static final Level SEVERE = null;
	
	

	protected Shell Triangle_Draw;
	private Button ST;
	private Text LT11;
	private Text LT22;
	private Text LT33;
	private Label backgroundT;
	private Button button;

	/**
	 * Launch the application.
	 * @param args
	 * @wbp.parser.entryPoint
	 */
	public static void Tmeasurments() {
		try {
			T_tab window = new T_tab();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		Triangle_Draw.open();
		Triangle_Draw.layout();
		while (!Triangle_Draw.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		Triangle_Draw = new Shell();
		Triangle_Draw.setImage(SWTResourceManager.getImage(T_tab.class, "/finchpic.png"));
		Triangle_Draw.setSize(605, 374);
		Triangle_Draw.setText("Triangle Draw");
		Triangle_Draw.setLayout(null);
		
		LT11 = new Text(Triangle_Draw, SWT.BORDER);
		LT11.setBounds(270, 137, 76, 21);
		
		LT22 = new Text(Triangle_Draw, SWT.BORDER);
		LT22.setBounds(270, 195, 76, 21);
		
		LT33 = new Text(Triangle_Draw, SWT.BORDER);
		LT33.setBounds(270, 253, 76, 21);
		
		ST = new Button(Triangle_Draw, SWT.NONE);
		ST . setImage(SWTResourceManager.getImage(T_tab.class, "/Sbutton.png"));
		ST.setBounds(280, 280, 48, 42);
		
		button = new Button(Triangle_Draw, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new gui();
				gui.main(null);
			}
		});
		button.setText("Go Back");
		button.setBounds(10, 297, 75, 25);
		
		backgroundT = new Label(Triangle_Draw, SWT.NONE);
		backgroundT.setImage(SWTResourceManager.getImage(T_tab.class, "/gui_TR.png"));
		backgroundT.setBounds(0, 0, 614, 336);
		ST.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				// FileHandler 
				
				
				try {
				
				LogManager.getLogManager().reset(); // get rid on any handlers so it does not interfere with other stuff
				FileHandler fh = new FileHandler ("LogFile" , true); // so i can log to a FILE
				fh.setLevel(Level.INFO); // level of log how important it is
				fh.setFormatter(new SimpleFormatter()); // Change format of log to simple which include date time ex...
				Loggf.addHandler(fh); // add handler to log
				
				
				Loggf.log(INFO, "FIRST LOG");
				
				} catch (Exception  f) { // if log fails the program will continue noramlly
					f.printStackTrace();
				}
			
				
				

				
				
				int A = Integer.parseInt(LT11.getText());
				int B = Integer.parseInt(LT22.getText());
				int C = Integer.parseInt(LT33.getText());

			
				
				if ( A < 20 || B < 20 || C < 20 ) {
					MessageDialog.openError(Triangle_Draw, "Error", "Enter a valid value from 20-80 cm");
				}
				
				else if ( A > 80 || B  > 80 || C > 80 ) {
					MessageDialog.openError(Triangle_Draw, "Error", "Enter a valid value from 20-80 cm");
					
					
				 }
				
				
					else  {
						
						
						Finch myF = new Finch();
						
						myF.setLED(255, 0, 0);
						
						if (myF.isFinchLevel()==true); {   //				check if finch on flat surface or not	
						
						myF.setWheelVelocities(255,255,26 * A); // A
						myF.setWheelVelocities(200,0,1000); // angleA
						myF.buzz(1000, 500); // first turn buzz
						myF.setWheelVelocities(0,0,500);
						
						myF.setWheelVelocities(255,255,26 * B);// B
						myF.setWheelVelocities(200,0,1000);// angleB
						myF.buzz(1000, 1000); // second turn buzz
						myF.setWheelVelocities(0,0,1000);
						
						myF.setWheelVelocities(255,255,26 * C);// C
						myF.setWheelVelocities(200,0,1000);// angleC
						myF.buzz(3000, 2000); // third turn and ending sound
					
						myF.setWheelVelocities(0,0,2000); // needed for buzz to work
						myF.quit(); // quit finch
						
						// Angles
						
						double angleA = Math.acos((Math.pow(C, 2) + Math.pow(B, 2) - Math.pow(A, 2)) / ( 2 * B * C));
						double DegA = Math.toDegrees(angleA);
						double angleB = Math.acos((Math.pow(C, 2) + Math.pow(A, 2) - Math.pow(B, 2)) / ( 2 * C * A));
						double DegB = Math.toDegrees(angleB);
						double angleC = Math.acos((Math.pow(B, 2) + Math.pow(A, 2) - Math.pow(C, 2)) / ( 2 * B * A));
						double DegC = Math.toDegrees(angleC);
						
						
						Loggf.info("Triangle" + " A=" + A + " , B=" + B + " , C=" + C +" Angles A=" + DegA + " , B=" + DegB + " , C=" + DegC ); // what i want to be recorded in log
						
					
			}

			}
			}});

	}
}

