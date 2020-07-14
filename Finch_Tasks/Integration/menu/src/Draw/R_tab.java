package Draw;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;



public class R_tab {
	
	private final static Logger Loggf = Logger.getLogger("LogFile");






	protected static final Level INFO = null;






	protected static final Level SEVERE = null;
	
	


	

	protected Shell Rectangle_Draw;
	private Text LR11;
	private Text LR22;
	private Text LR33;
	private Text LR44;
	private Button button;

	/**
	 * Launch the application.
	 * @param args
	 * @wbp.parser.entryPoint
	 */
	public static void Rmeasurments() {
		try {
			R_tab window = new R_tab();
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
		Rectangle_Draw.open();
		Rectangle_Draw.layout();
		while (!Rectangle_Draw.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		Rectangle_Draw = new Shell();
		Rectangle_Draw.setImage(SWTResourceManager.getImage(T_tab.class, "/finchpic.png"));
		Rectangle_Draw.setSize(605, 375);
		Rectangle_Draw.setText("Rectangle Draw");
		
		LR11 = new Text(Rectangle_Draw, SWT.BORDER);
		LR11.setBounds(260, 109, 76, 21);
		
		LR22 = new Text(Rectangle_Draw, SWT.BORDER);
		LR22.setBounds(260, 157, 76, 21);
		
		LR33 = new Text(Rectangle_Draw, SWT.BORDER);
		LR33.setBounds(260, 205, 76, 21);
		
		LR44 = new Text(Rectangle_Draw, SWT.BORDER);
		LR44.setBounds(260, 247, 76, 21);
		
		button = new Button(Rectangle_Draw, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
                 // FileHandler 
				
				
				try {
				
				LogManager.getLogManager().reset(); // get rid on any handlers so it does not interfere with other stuff
				FileHandler fh = new FileHandler ("LogFile" , true); // so i can log to a FILE
				fh.setLevel(Level.INFO); // level of log how important it is
				fh.setFormatter(new SimpleFormatter()); // Change format of log to simple which include date time ex..
				Loggf.addHandler(fh); // add handler to log
				
				
				Loggf.log(INFO, "FIRST LOG");
				
				} catch (Exception  f) { // if log fails the program will continue noramlly
					f.printStackTrace();
				}
			
				
				
				
				
				
				
					
					
					
           

				int A = Integer.parseInt(LR11.getText());
				int B = Integer.parseInt(LR22.getText());
				int C = Integer.parseInt(LR33.getText());
				int D = Integer.parseInt(LR44.getText());

				
				
				if ( A < 20 || B < 20 || C < 20 || D < 20 ) {
					MessageDialog.openError(Rectangle_Draw, "Error", "Enter a valid value from 20-80 cm");
				}
				
				else if ( A > 80 || B  > 80 || C > 80 || D > 80 ) {
					MessageDialog.openError(Rectangle_Draw, "Error", "Enter a valid value from 20-80 cm");
					
					
				 }
				
				
					else  {
						
						
                        Finch myF = new Finch();
						
						if (myF.isFinchLevel()==true); {   //				check if finch on flat surface or not
						
					    myF.setLED(0, 255, 0);
						myF.setWheelVelocities(255,255,26 * A); // A
						myF.setWheelVelocities(150,0,800); // A-B
						myF.buzz(1000, 500);; // First turn buzz
						myF.setWheelVelocities(0,0,1000); // needed for buzz to work
						
						myF.setWheelVelocities(255,255,26 * B);// B
						myF.setWheelVelocities(150,0,800);// B-C
						myF.buzz(1000, 500); // second turn buzz
						myF.setWheelVelocities(0,0,500); // needed for buzz to work
						
						myF.setWheelVelocities(255,255,26 * C);// C 
						myF.setWheelVelocities(150,0,800);// C-D
						myF.buzz(1000, 500); // Third turn buzz
						myF.setWheelVelocities(0,0,500); // needed for buzz to work
						
						
						myF.setWheelVelocities(255,255,26 * D);// D
						myF.setWheelVelocities(150,0,800); // D-A
						myF.buzz(3000, 2000);  // Fourth and ending buzz
						myF.setWheelVelocities(0,0,2000);  // needed for buzz to work
						myF.quit(); // quit finch
						Loggf.info("Rectangle" + " A=" + A + " , B=" + B + " , C=" + C + " , D=" + D ); // what i want to be recorded in log
					   
						
						
				}
				
				
			}
			
			}});
		button.setImage(SWTResourceManager.getImage(T_tab.class, "/Sbutton.png"));
		button.setBounds(275, 294, 48, 42);
		
		Button btnGoBack = new Button(Rectangle_Draw, SWT.NONE);
		btnGoBack.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new gui();
				gui.main(null);
			}
		});
		
		btnGoBack.setBounds(10, 303, 75, 25);
		btnGoBack.setText("Go Back");
		
		Label backgroundR = new Label(Rectangle_Draw, SWT.NONE);
		backgroundR.setImage(SWTResourceManager.getImage(R_tab.class, "/gui_RE.png"));
		backgroundR.setBounds(0, 0, 589, 336);

	}
}
