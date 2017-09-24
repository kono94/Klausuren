
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class RandomCircle_2017_1 {
	public static void main(String[] args) {
		new Controller();
	}
}

class Controller{
	private Model m_Model;
	private View m_View;
	volatile private int sleepTime = 100;
	public Controller(){
		m_Model = new Model(150);
		m_View = new View(m_Model);
		applyListeners();
		go();
	}	
	
	private void go(){
		while(true){
			Insets ins = m_View.getDrawingPanel().getInsets();
			m_Model.generateNewCirclePoint(m_View.getPanelWidth(), m_View.getPanelHeight(), ins.right, ins.left, ins.top, ins.left);
			m_View.repaint();			
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void applyListeners(){
		m_View.getSlider().addChangeListener(e->{
			sleepTime = ((JSlider) e.getSource()).getValue();
		});
	}
}

class View extends JFrame{
	private Model m_Model;
	private DrawingPanel drawingPanel;
	private JSlider slider;
	public View(Model m){
		setLayout(new BorderLayout());
		setSize(500,500);
		drawingPanel = new DrawingPanel();		
		m_Model = m;	
		slider = new JSlider(20,200, 100);		
		add(BorderLayout.SOUTH, slider);
		add(BorderLayout.CENTER, drawingPanel);
		
		pack();
		setVisible(true);	
	}	 
	
	 public int getPanelWidth(){
		 return drawingPanel.getWidth();
	 }
	 public int getPanelHeight(){
		 return drawingPanel.getHeight();
	 }
	 public DrawingPanel getDrawingPanel(){
		 return drawingPanel;
	 }
	 
	 public JSlider getSlider(){
		 return slider;
	 }
	 
	 class DrawingPanel extends JPanel{
		 
		 public DrawingPanel(){
			 setPreferredSize(new Dimension(500,500));
		 }
		 
		 @Override
		 public void paintComponent(Graphics g){
			 //super.paintComponent(g);
			 g.drawOval(m_Model.getPoint().x, m_Model.getPoint().y, m_Model.getRadius(), m_Model.getRadius());
		 }
		 
	 }
}

class Model{
	private int m_Radius;
	private Point m_Point;
	
	public Model(int radius){
		m_Radius = radius;
		m_Point = new Point();
	}
	
	public void generateNewCirclePoint(int windowWidth, int windowHeight, int insetRight, int insetLeft, int insetTop, int insetBottom){
		m_Point.x = (int) (Math.random() * (windowWidth - m_Radius - insetRight) + insetLeft);
		m_Point.y = (int) (Math.random() * (windowHeight - m_Radius - insetBottom) + insetTop);
	}
	
	public Point getPoint(){
		return m_Point;
	}	
	public int getRadius(){
		return m_Radius;
	}
}

