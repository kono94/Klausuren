import java.awt.*;
import java.awt.event.*;

public class Viereck_2014_1 extends Frame {
	private Point m_A, m_B, m_C, m_D;
	private Point m_Ta, m_Tb, m_Tc, m_Td;
	private Color m_Color;
	volatile boolean m_ChangeColor;

	public Viereck_2014_1() {
		m_Color = Color.RED;
	
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (m_A == null) {
					m_A = e.getPoint();
				} else if (m_B == null) {
					m_B = e.getPoint();
				} else if (m_C == null) {
					m_C = e.getPoint();
				} else if (m_D == null) {
					m_D = e.getPoint();
					setViereck();
					repaint();
				}
			}
		});
		setLayout(new BorderLayout());
		Panel buttonPanel = new Panel();
		buttonPanel.setLayout(new FlowLayout());
		Button redButton = new Button("red");
		Button greenButton = new Button("green");
		Button startStopButton = new Button("start");
		startStopButton.addActionListener(e->{
			if(!m_ChangeColor){
				m_ChangeColor = true;
				startStopButton.setLabel("stop");
				new Animation();
			}else{
				m_ChangeColor = false;
				startStopButton.setLabel("start");
			}
		});
		redButton.addActionListener(e -> {
			m_Color = Color.RED;
			repaint();
		});
		greenButton.addActionListener(e -> {
			m_Color = Color.GREEN;
			repaint();
		});
		buttonPanel.add(redButton);
		buttonPanel.add(greenButton);
		buttonPanel.add(startStopButton);
		add(BorderLayout.SOUTH, buttonPanel);
		pack();
		setSize(500,500);
		setVisible(true);

	}

	@Override
	public void paint(Graphics g) {
		if (m_Ta != null) {
			g.setColor(m_Color);
			g.drawLine(m_Ta.x, m_Ta.y, m_Tb.x, m_Tb.y);
			g.drawLine(m_Tb.x, m_Tb.y, m_Tc.x, m_Tc.y);
			g.drawLine(m_Tc.x, m_Tc.y, m_Td.x, m_Td.y);
			g.drawLine(m_Td.x, m_Td.y, m_Ta.x, m_Ta.y);
		}
	}

	public void setViereck() {
		m_Ta = m_A;
		m_Tb = m_B;
		m_Tc = m_C;
		m_Td = m_D;
		m_A = null;
		m_B = null;
		m_C = null;
		m_D = null;
	}

	public static void main(String[] args) {
		new Viereck_2014_1();
	}
	
	
	class Animation implements Runnable{
		public Animation(){
			Thread t = new Thread(this);
			t.start();
		}
		@Override
		public void run(){
			while(m_ChangeColor){
				if(m_Color == Color.RED){
					m_Color = Color.GREEN;
					repaint();
				}else{
					m_Color = Color.RED;
					repaint();
				}
				try{
					Thread.sleep(200);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
	}
}
