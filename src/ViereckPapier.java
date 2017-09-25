import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class ViereckPapier extends JFrame {
	private Point pA, pB;
	private int x,y,w,h;
	private Color myColor;
	private JPanel drawingPanel;
	volatile private boolean animate;
	public ViereckPapier(){
		myColor = Color.RED;
		setLayout(new BorderLayout());
		drawingPanel = new JPanel(){
			{
				addMouseListener(new MouseAdapter(){
					@Override
					public void mousePressed(MouseEvent e){
						if(pA == null)
							pA = e.getPoint();
						else if(pB == null){
							pB = e.getPoint();
							x = pA.x < pB.x ? pA.x : pB.x;
							y = pA.y < pB.y ? pA.y : pB.y;
							w = Math.abs(pA.x - pB.x);
							h = Math.abs(pA.y  - pB.y);
							//repaint();
							pA = null;
							pB = null;
							drawingPanel.update(drawingPanel.getGraphics());
						}
					}
				});
			}
			@Override
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				g.setColor(myColor);
				g.drawRect(x, y, w, h);
			}
		};
		add(BorderLayout.CENTER, drawingPanel);
		drawingPanel.setPreferredSize(new Dimension(500,500));

		JPanel buttonPanel = new JPanel(){
			{
				JButton red = new JButton("red"){
					{
						addActionListener(e->{
							myColor = Color.RED;
							drawingPanel.repaint();
						});
					}
				};
				JButton green = new JButton("green"){
					{
						addActionListener(e->{
							myColor = Color.GREEN;
							drawingPanel.repaint();
						});
					}
				};
				JButton ani = new JButton("animate"){
					{
						addActionListener(e->{
							if(!animate){
								animate = true;
								setLabel("stop animatin");
								new Animation();
							}else{
								animate = false;
								setLabel("animate");
							}							
						});
					}
				};
				setLayout(new FlowLayout());
				add(red);
				add(green);
				add(ani);
			}
		};
		add(BorderLayout.SOUTH, buttonPanel);
		pack();
		setVisible(true);
	}
	
	class Animation implements Runnable{
		public Animation(){
			Thread t = new Thread(this);
			t.start();
		}
		@Override
		public void run(){
			while(animate){
				if(myColor == Color.RED)
					myColor = Color.GREEN;
				else
					myColor = Color.RED;
				
				w =	(int) (Math.random() * (drawingPanel.getWidth()));
				h = (int) (Math.random() * (drawingPanel.getHeight()));
				x = (int) (Math.random() * (drawingPanel.getWidth() - getInsets().right - w) );
				y = (int) (Math.random() * (drawingPanel.getHeight() - getInsets().bottom - h));
				drawingPanel.repaint();
				try{
					Thread.sleep(50);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				
			}
		}
	}
	
	public static void main(String[] args) {
		new ViereckPapier();
	}
}
