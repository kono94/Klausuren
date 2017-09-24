import java.awt.*;
import java.awt.event.*;


import javax.swing.JMenuBar;

public class Circle extends Frame {
	private Point m_PointA = null;
	private Point m_PointB = null;
	private int x;
	private int y;
	private int w;
	private int h;
	private Color m_Color = Color.BLACK;
	private boolean m_ChangeColor;
	
	public Circle(){
		setSize(500,500);
		addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				if(m_PointA == null){
					m_PointA = e.getPoint();
				}else if(m_PointB == null){
					m_PointB = e.getPoint();
					calcCircle();
					repaint();
				}
			}
		});	
		
		MenuBar menuBar = new MenuBar();
		Menu colorMenu = new Menu("Color");
		MenuItem black = new MenuItem("black");
		MenuItem blue = new MenuItem("blue");
		MenuItem red = new MenuItem("red");
		MenuItem start = new MenuItem("start");
		start.addActionListener(e->{
			if(!m_ChangeColor){
				m_ChangeColor = true;
				new Animation();
				start.setLabel("stop");
			}else{
				m_ChangeColor = false;
				start.setLabel("start");
			}
		});
		black.addActionListener(e->{
			m_Color = Color.BLACK;
			repaint();
		});
		blue.addActionListener(e->{
			m_Color = Color.RED;
			repaint();
		});
		red.addActionListener(e->{
			m_Color = Color.GREEN;
			repaint();
		});
		colorMenu.add(black);
		colorMenu.add(blue);
		colorMenu.add(red);
		colorMenu.add(start);
		menuBar.add(colorMenu);
		setMenuBar(menuBar);
		setVisible(true);
	}
	
	private void calcCircle(){
		
		int anC = (int)(Math.abs(m_PointA.getX() - m_PointB.getX()));
		int geC = (int)(Math.abs(m_PointA.getY() - m_PointB.getY()));
		int rad = (int)(Math.sqrt(anC  * anC + geC * geC));
		x = (int)m_PointA.getX() - rad;
		y = (int) m_PointA.getY() - rad;
		w = rad * 2;
		h = rad * 2;	
		m_PointA = null;
		m_PointB = null;	
	}
	
	
	@Override
	public void paint(Graphics g){
		g.setColor(m_Color);
		g.drawOval(x, y, w, h);		
	}
	public static void main(String[] args){
		new Circle();
	}
	
	class Animation implements Runnable{

		public Animation(){
			Thread t = new Thread(this);
			t.start();
		}
		
		@Override
		public void run() {		
			System.out.println("mee");
			while(m_ChangeColor){		
				System.out.println("fff");
				if(m_Color == Color.GREEN)
					m_Color = Color.RED;
				else
					m_Color = Color.GREEN;
				repaint();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			
		}
		
	}
}


