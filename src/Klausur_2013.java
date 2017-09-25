import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Klausur_2013 extends JFrame {
	private Color m_Color;
	private boolean drawCircle;
	private JPanel drawingPanel;
	public Klausur_2013(){
		m_Color = Color.RED;
		setLayout(new BorderLayout());
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		drawingPanel = new JPanel(){
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				g.setColor(m_Color);
				if(drawCircle)
					g.drawOval(getWidth()/2 - 25, getHeight()/2 - 25 , 50, 50);
				else
					g.drawRect(getWidth()/2 - 25, getHeight()/2 - 25 , 50, 50);
			}
		};
		JButton red = new JButton("red");
		JButton green = new JButton("green");
		JButton circle = new JButton("circle");
		JButton rect = new JButton("rect");
		red.addActionListener(e->{
			m_Color = Color.RED;
			drawingPanel.repaint();
		});
		green.addActionListener(e->{
			m_Color = Color.GREEN;
			drawingPanel.repaint();
		});
		circle.addActionListener(e->{
			drawCircle = true;
			drawingPanel.repaint();
		});
		rect.addActionListener(e->{
			drawCircle = false;
			drawingPanel.repaint();
		});
		buttonPanel.add(red);
		buttonPanel.add(green);
		buttonPanel.add(circle);
		buttonPanel.add(rect);
		add(BorderLayout.CENTER, drawingPanel);
		add(BorderLayout.SOUTH, buttonPanel);
		drawingPanel.setPreferredSize(new Dimension(500,500));
		pack();
		setVisible(true);
		new ColorChange();
		new FormChange();
	}
	
	
	public static void main(String[] args) {
		new Klausur_2013();
	}
	
	class ColorChange implements Runnable{
		public ColorChange(){
			Thread t = new Thread(this);
			t.start();
		}
		@Override
		public void run(){
			while(true){
				if(m_Color == Color.GREEN){
					m_Color = Color.RED;
				}else{
					m_Color = Color.GREEN;
				}
				drawingPanel.repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		}
	}
	class FormChange implements Runnable{
		public FormChange(){
			Thread t = new Thread(this);
			t.start();
		}
		@Override
		public void run(){
			while(true){
				if(drawCircle){
					drawCircle = false;
				}else{
					drawCircle = true;
				}
				
				try {
					Thread.sleep(130);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		}
	}
}
