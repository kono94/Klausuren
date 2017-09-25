import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class SquareCircle extends Frame {
	private Point m_PointA = null;
	private Point m_PointB = null;
	private int x;
	private int y;
	private int w;
	private int h;
	public SquareCircle(){
	
	
		setSize(500,500);
		setVisible(true);
	
		
		addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){			
				if(m_PointA == null){
					m_PointA = e.getPoint();									
				}else if(m_PointB == null){
					m_PointB = e.getPoint();
					calcRect();
					repaint();
				}
			}
		});
		
	}
	public void printTestPage(){
		PrintJob pJob = getToolkit().getPrintJob(this, "Testpage", new JobAttributes(), new PageAttributes());
		if(pJob != null){
			Dimension dim = pJob.getPageDimension();
			Graphics g = pJob.getGraphics();
			if(g!=null){
				paint(g);
				g.dispose();
			}
			pJob.end();
		}
	}
	private void calcRect(){		
		x = (int) (m_PointA.getX() < m_PointB.getX() ? m_PointA.getX() : m_PointB.getX());
		y = (int) (m_PointA.getY() < m_PointB.getY() ? m_PointA.getY() : m_PointB.getY());
		w = (int) Math.abs(m_PointA.getX() - m_PointB.getX());
		h = (int) Math.abs(m_PointA.getY() - m_PointB.getY());	
		System.out.println(x + " " + y + " " + w + " " + h );
	
		m_PointA = null;
		m_PointB = null;
		//  printTestPage(); Test fürs drucken
	}
	
	@Override	public void paint(Graphics g){		
		g.drawRect(x,y,w,h);
	}	
	
	public static void main(String[] args){
		new SquareCircle();
	}
}
