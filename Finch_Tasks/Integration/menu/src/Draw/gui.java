package Draw;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;

import java.util.logging.Level;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;

public class gui {
	

	protected static final Level INFO = null;

	protected Shell shlTaskShapeDraw;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			gui window = new gui();
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
		shlTaskShapeDraw.open();
		shlTaskShapeDraw.layout();
		while (!shlTaskShapeDraw.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlTaskShapeDraw = new Shell();
		shlTaskShapeDraw.setImage(SWTResourceManager.getImage(gui.class, "/finchpic.png"));
		shlTaskShapeDraw.setSize(600, 340);
		shlTaskShapeDraw.setText("Task2 Shape Draw");
		
		Button btnTriangle = new Button(shlTaskShapeDraw, SWT.NONE);
		btnTriangle.setImage(SWTResourceManager.getImage(T_tab.class, "/Tchoose.png"));
		btnTriangle.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
				new T_tab();
				T_tab.Tmeasurments();
				
				
			}
		});
		btnTriangle.setBounds(216, 100, 152, 69);
		btnTriangle.setText("Triangle");
		
		Button btnRectangle = new Button(shlTaskShapeDraw, SWT.NONE);
		btnRectangle.setText("Rectangle");
		btnRectangle.setImage(SWTResourceManager.getImage(T_tab.class, "/Rchoose.png"));
		btnRectangle.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				new R_tab();
				R_tab.Rmeasurments();
			}
		});
		btnRectangle.setBounds(216, 175, 152, 69);
		
		Label Background = new Label(shlTaskShapeDraw, SWT.NONE);
		Background .setImage(SWTResourceManager.getImage(gui.class,"/guiv3_select.png"));
		Background.setBounds(0, 0, 584, 301);
		
		
	
	}
}

