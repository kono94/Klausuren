import java.awt.*;
import java.awt.event.*;

public class Memory_2014_2 extends Frame {
	private Card[] m_Cards;
	private int firstCard = -1;
	volatile private boolean switchConstantly;
	
	public Memory_2014_2(){
		m_Cards = new Card[9];
		m_Cards[0] = new Card(Color.RED, 0);
		m_Cards[1] = new Card(Color.GREEN, 1);
		m_Cards[2] = new Card(Color.BLUE, 2);
		m_Cards[3] = new Card(Color.YELLOW, 3);
		m_Cards[4] = new Card(Color.BLACK, 4);
		m_Cards[5] = new Card(Color.WHITE, 5);
		m_Cards[6] = new Card(Color.ORANGE, 6);
		m_Cards[7] = new Card(Color.DARK_GRAY, 7);
		m_Cards[8] = new Card(Color.CYAN, 8);
	
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Action");
		MenuItem startItem = new MenuItem("start");
		menu.add(startItem);
		menuBar.add(menu);
		setMenuBar(menuBar);
		startItem.addActionListener(e->{
			if(!switchConstantly){
				switchConstantly = true;
				new Animation();
				startItem.setLabel("stop");
			}else{
				switchConstantly = false;
				startItem.setLabel("start");
			}
		});
		
		setLayout(new GridLayout(3,3));
		fillGrid();
		pack();
		setVisible(true);		
	}
	
	@Override
	public void paint(Graphics g){
		revalidate();
		for (int i = 0; i < m_Cards.length; i++) {			
			m_Cards[i].update(m_Cards[i].getGraphics());
		}
	}
	
	private void fillGrid(){
		removeAll();
		revalidate();
		repaint();
		for (int i = 0; i < m_Cards.length; i++) {			
			add(m_Cards[i]);
		}	
		repaint();
	}
	
	private void switchCards(int firstIndex, int secondIndex){

//		Card tmp = new Card(m_Cards[firstIndex].m_MyColor, secondIndex);
		Card tmp = m_Cards[firstIndex];
		tmp.setIndex(secondIndex);
		
		m_Cards[firstIndex] = m_Cards[secondIndex];
		m_Cards[firstIndex].setIndex(firstIndex);
		
		
		m_Cards[secondIndex] = tmp;	
		
		fillGrid();

	}
	
	class Card extends Component{
		private Color m_MyColor;
		private int m_Index;
		public Card(Color c, int index){
			m_MyColor = c;
			m_Index = index;
			setPreferredSize(new Dimension(100,100));
			addMouseListener(new MouseAdapter(){
				@Override
				public void mousePressed(MouseEvent e){
					System.out.println(m_Index);
					if(firstCard == -1){
						firstCard = m_Index;
					}else if(firstCard != m_Index){
						switchCards(firstCard, m_Index);
						firstCard = -1;
					}
				}
			});
		}
		
		@Override
		public void paint(Graphics g){
			g.setColor(m_MyColor);
			g.fillRect(0, 0, getWidth(), getHeight());		
		}
		public void setIndex(int i){
			m_Index = i;
		}
		public void setColor(Color c){
			m_MyColor = c;
		}
		
	}
	
	class Animation implements Runnable{
		public Animation(){
			Thread t = new Thread(this);
			t.start();
		}
		 @Override
		 public void run() {
			 while(switchConstantly){
				int first = (int)( Math.random()* 8);
				int second  = (int)( Math.random()* 8);
				while(first == second){
					second = (int)( Math.random()* 8);
				}
				System.out.println(first + " " + second);
				switchCards(first, second);
				
				try{
					Thread.sleep(400);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			 }
		 }
	}
	
	public static void main(String[] args) {
		new Memory_2014_2();
	}
}
