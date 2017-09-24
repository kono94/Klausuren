import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private void calcRect(){		
		x = (int) (m_PointA.getX() < m_PointB.getX() ? m_PointA.getX() : m_PointB.getX());
		y = (int) (m_PointA.getY() < m_PointB.getY() ? m_PointA.getY() : m_PointB.getY());
		w = (int) Math.abs(m_PointA.getX() - m_PointB.getX());
		h = (int) Math.abs(m_PointA.getY() - m_PointB.getY());	
		System.out.println(x + " " + y + " " + w + " " + h );
		m_PointA = null;
		m_PointB = null;
	}
	
	@Override	public void paint(Graphics g){		
		g.drawRect(x,y,w,h);
	}	
	
	public static void main(String[] args){
		new SquareCircle();
	}
}
