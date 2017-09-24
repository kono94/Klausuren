import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JSlider;

public class Klausur_2013_2 extends JFrame {
	private Panel drawinPanel;
	private int percent = 1;
	volatile boolean fill;

	public Klausur_2013_2(){
		setLayout(new BorderLayout());
		drawinPanel = new Panel(){
			@Override
			public void paint(Graphics g){
				int width = (getWidth() * percent)/100;
				int height = getHeight() * percent /100;
				try {
					Image image = ImageIO.read(new File("lp.jpg"));					
					g.drawImage(image, getWidth()/2 - width/2, getHeight()/2 - height/2, width, height, this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("mee");
				System.out.println(Paths.get("").toAbsolutePath().toString());
				}
				
//				if(!fill)
//					g.drawRect(getWidth()/2 - width/2, getHeight()/2 - height/2, width, height);
//				else
//					g.fillRect(getWidth()/2 - width/2, getHeight()/2 - height/2, width, height);
			}
		};
		drawinPanel.setPreferredSize(new Dimension(500,500));
		Panel buttonPanel = new Panel();
		buttonPanel.setLayout(new FlowLayout());
		JSlider slider = new JSlider(0,100, 1);
		slider.addChangeListener(e->{
			percent = ((JSlider) e.getSource()).getValue();
			drawinPanel.repaint();
		});
		buttonPanel.add(slider);
		Button fillButton = new Button("fill");
		fillButton.addActionListener(e->{
			if(fill)
				fill = false;
			else
				fill = true;
			
			drawinPanel.repaint();
		});
		buttonPanel.add(fillButton);
		add(BorderLayout.CENTER, drawinPanel);
		add(BorderLayout.SOUTH, buttonPanel);
		pack();
		setVisible(true);
		
	}

	public static void main(String[] args) {
		new Klausur_2013_2();
	}
}
