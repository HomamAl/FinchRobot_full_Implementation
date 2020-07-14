package menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class GUI {
	private static JFrame frame;
	private static JPanel panel;
	private static JButton button1, button2, button3, button4, button5, button6, button7;
	
	public static void main(String[] args) {
		
		frame = new JFrame("Finch Robot tasks");
		frame.setVisible(true);
		frame.setSize(1000, 700);
		frame.setLocationRelativeTo(null);  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		
		button1 = new JButton("Task 1 : FollowLight");
        button1.setPreferredSize(new Dimension(300,200));
        button1.setFont(new Font("Arial", Font.BOLD, 24));

		button2 = new JButton("Task 2 : Draw Shape");
		button2.setPreferredSize(new Dimension(300,200));
		button2.setFont(new Font("Arial", Font.BOLD, 24));
		
		button3 = new JButton("Task 3 : Navigate    ");
		button3.setPreferredSize(new Dimension(300,200));
		button3.setFont(new Font("Arial", Font.BOLD, 24));
		
		button4 = new JButton("Task 4 : Zigzag     ");
		button4.setPreferredSize(new Dimension(300,200));
		button4.setFont(new Font("Arial", Font.BOLD, 24));
		
		button5 = new JButton("Task 5 : Follow Object ");
		button5.setPreferredSize(new Dimension(300,200));
		button5.setFont(new Font("Arial", Font.BOLD, 24));
		
		button6 = new JButton("Task 6 : Dance");
		button6.setPreferredSize(new Dimension(300,200));
		button6.setFont(new Font("Arial", Font.BOLD, 24));
		
		button7 = new JButton("Task 7 : Tilt control");
		button7.setPreferredSize(new Dimension(300,200));
		button7.setFont(new Font("Arial", Font.BOLD, 24));
		
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					light.myfollowthelight.main(args);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Draw.gui.main(args);
			}
		});
		button3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					Navigation.navigation.main(args);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Zigzag.User.main(args);
			}
		});
		button5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				DetectObject.Object.main(args);
			}
		});
		button6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dance.GameDriver.main(args);
			}
		});
		button7.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
	        	tilt.FinchTilt.main(args);
			}
		});
		
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		panel.add(button5);
		panel.add(button6);
		panel.add(button7);
		frame.add(panel);
	}
}