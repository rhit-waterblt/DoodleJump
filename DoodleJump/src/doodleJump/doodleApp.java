package doodleJump;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class doodleApp extends JPanel{

		//variables
		int score;
		int level;
		int life;
		Font sbFont;
		private boolean intro = true;
		private Timer timer;
		private Rectangle doodleGuy, p1, p2, p3, p4, p5, p6, p7, p8, p9;
		private Random rnd;
		private Clip hit;
		private boolean screen, erase;
		
		
		
		public doodleApp() {
			setBackground(Color.black);
			sbFont = new Font("Comic Sans MS", Font.CENTER_BASELINE, 48 );
			score = 0; level = 1; life = 1;
			rnd = new Random();
			screen = true; erase = true;
			
			timer = new Timer(10, new DoodleListener());
			
			doodleGuy = new Rectangle(400, 550, 75, 75, Color.BLUE);
			
			doodleGuy.setImage(new ImageIcon(ClassLoader.getSystemResource("doodleGuy2.png")));
			
			p1 = new Rectangle(400, 799, 100, 25, Color.RED);
			p2 = new Rectangle(500, 700, 100, 25, Color.RED);
			p3 = new Rectangle(300, 500, 100, 25, Color.RED);
			p4 = new Rectangle(200, 400, 100, 25, Color.RED);
			p5 = new Rectangle(600, 200, 100, 25, Color.RED);
			p6 = new Rectangle(100, 300, 100, 25, Color.RED);
			p7 = new Rectangle(-300, 400, 100, 25, Color.BLUE);
			p8 = new Rectangle(-300, 200, 100, 25, Color.BLUE);
			p9 = new Rectangle(-300, 300, 100, 25, Color.BLUE);
			
			
			
			timer.start();
			
			//listeners
			addKeyListener(new pressKey());
			setFocusable(true);
		
		
		}//end constructor
		
		public void drawBorders(Graphics g) {
			g.setColor(Color.gray);
			g.fillRect(0,  0,  getWidth(),  getHeight());
			g.setColor(Color.black);
			g.fillRect(50, 25, 825, 800);
			
			g.fillRect(925, 25, 300, 800);
		
		}//end draw border
		
		public void scoreboard(Graphics g) {
			g.setFont(sbFont);
			g.setColor(Color.white);
			g.drawString("Score:", 1000, 150);
			g.setColor(Color.orange);
			g.drawString("" +score, 1050, 200);
			
			g.setColor(Color.white);
			g.drawString("Level:", 1000, 350);
			g.setColor(Color.orange);
			g.drawString("" +level, 1050, 400);
			
			g.setColor(Color.white);
			g.drawString("Lives:", 1000, 550);
			g.setColor(Color.orange);
			g.drawString("" +life, 1050, 600);
			
		}//end scoreboard
		
		
		public void introScreen (Graphics g) {
			g.setFont(sbFont);
			g.setColor(Color.white);
			g.drawString("Welcome to Doodle Jump", 300, 200);
			g.drawString("Get as high as you can!", 300, 300);
			g.drawString("He will jump automatically, Only one bounce on blue!", 50, 400);
			g.drawString("Arrow keys to move left & right",  250,  500);
			g.drawString("You lose points if you bounce on the same platform", 50, 600);
			g.drawString("Press any key to start", 300, 700);
			
		}//end introScreen
		
		public void drawPlayAgain (Graphics g) {
			g.setFont(sbFont);
			g.setColor(Color.white);
			g.drawString("Game Over!", 350, 400);
			g.drawString("Play Again?(y/n)", 300, 500);
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			if(!intro) {
				drawBorders(g);
				scoreboard(g);
				doodleGuy.drawImage(g);
				p1.fill(g);p2.fill(g);p3.fill(g);p4.fill(g);p5.fill(g);p6.fill(g);
				p7.fill(g);p8.fill(g);p9.fill(g);
			}else {
				introScreen(g);
			}
			
			if(life <= 0) {
				drawPlayAgain(g);
			}
			
		}//end paintC
		
		
		private class DoodleListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(!intro) {
					
					if(doodleGuy.getY() <= 400) {
						if(screen) {
							p1.setDy(doodleGuy.getDy() * -1); 
							p2.setDy(doodleGuy.getDy() * -1); 
							p3.setDy(doodleGuy.getDy() * -1); 
							p4.setDy(doodleGuy.getDy() * -1); 
							p5.setDy(doodleGuy.getDy() * -1);
							p6.setDy(doodleGuy.getDy() * -1);
							
							screen = false;
						}if(p1.getDy() >= 0) {	
							p7.setDy(p1.getDy());
							p8.setDy(p1.getDy());
							p9.setDy(p1.getDy());
							p1.fallPlatform();
							score += p1.getDy();
							p2.fallPlatform();
							p3.fallPlatform();
							p4.fallPlatform();
							p5.fallPlatform();
							p6.fallPlatform();
							p7.fallPlatform();
							p8.fallPlatform();
							p9.fallPlatform();
						}if(p1.getDy()<0) {
							screen = true;
							doodleGuy.setY(401);
							doodleGuy.setDy(0);
							
						}
					}else {
						doodleGuy.fall();
						if(doodleGuy.lands(p1) && doodleGuy.getDy() >= 0) {
							doodleGuy.setDy(-9.25); playHit();
						}
						if(doodleGuy.lands(p2) && doodleGuy.getDy() >= 0) {
							doodleGuy.setDy(-9.25);playHit();
						}
						if(doodleGuy.lands(p3) && doodleGuy.getDy() >= 0) {
							doodleGuy.setDy(-9.25);playHit();
						}
						if(doodleGuy.lands(p4) && doodleGuy.getDy() >= 0) {
							doodleGuy.setDy(-9.25);playHit();
						}
						if(doodleGuy.lands(p5) && doodleGuy.getDy() >= 0) {
							doodleGuy.setDy(-9.25);playHit();
						}
						if(doodleGuy.lands(p6) && doodleGuy.getDy() >= 0) {
							doodleGuy.setDy(-9.25);playHit();
						}
						if(doodleGuy.lands(p7) && doodleGuy.getDy() >= 0) {
							doodleGuy.setDy(-9.25);playHit();
							p7.setFall(true);
						}
						if(doodleGuy.lands(p8) && doodleGuy.getDy() >= 0) {
							doodleGuy.setDy(-9.25);playHit();
							p8.setFall(true);
						}
						if(doodleGuy.lands(p9) && doodleGuy.getDy() >= 0) {
							doodleGuy.setDy(-9.25);playHit();
							p9.setFall(true);
						}
					//doodleGuy.move(0, 2);
					}//end iff doodle guy
					
					
					
					
					//redraw the platforms
					if(p1.getY() >= 800) {
						p1.setY(50);p1.setX(rnd.nextInt(600)+50);
					}
						if(p2.getY() >= 800) {
						p2.setY(50);p2.setX(rnd.nextInt(600)+50);
					}
						if(p3.getY() >= 800) {
						p3.setY(50);p3.setX(rnd.nextInt(600)+50);
					}
						if(p4.getY() >= 800 && level == 1) {
						p4.setY(50);p4.setX(rnd.nextInt(600)+50);
					}
						if(p4.getY() >= 800 && level == 2 && p7.isErase()) {
							p4.setX(-100); p4.setY(50);
							p7.setDy(0); p7.setY(50); p7.setX(rnd.nextInt(600)+50); p7.setErase(false);
						}
						if(p5.getY() >= 800 && level == 1) {
						p5.setY(50);p5.setX(rnd.nextInt(600)+50);
					}
						if(p5.getY() >= 800 && level == 2 && p8.isErase()) {
							p5.setX(-100); p5.setY(50);
							p8.setDy(0); p8.setY(50); p8.setX(rnd.nextInt(600)+50); p8.setErase(false);
						}
						if(p6.getY() >= 800 && level == 1) {
						p6.setY(50);p6.setX(rnd.nextInt(600)+50);
					}
						if(p6.getY() >= 800 && level == 2 && p9.isErase()) {
							p6.setX(-100); p6.setY(50);
							p9.setDy(0); p9.setY(50); p9.setX(rnd.nextInt(600)+50); p9.setErase(false);
						}
						
						//reset the blues
						
						if(p4.getY() >= 800 && level == 2 && p7.isErase() == false) {
							p7.setFall(false);p7.setY(50); p7.setX(rnd.nextInt(600)+50); p7.setDy(0); 
							p4.setX(-100); p4.setY(50);
						}
						if(p5.getY() >= 800 && level == 2 && p8.isErase() == false) {
							p8.setFall(false); p8.setY(50); p8.setX(rnd.nextInt(600)+50); p8.setDy(0);
							p5.setX(-100); p5.setY(50);
						}
						if(p6.getY() >= 800 && level == 2 && p9.isErase() == false) {
							p9.setFall(false); p9.setY(50); p9.setX(rnd.nextInt(600)+50); p9.setDy(0);
							p6.setX(-100); p6.setY(50);
						}
						if(p7.getY() >= 800) {
							p7.setY(-500);p7.setX(-600); p7.setFall(false); p7.setDy(0);
							
						}
						if(p8.getY() >= 800) {
							p8.setY(-500);p8.setX(-600); p8.setFall(false); p8.setDy(0);
							
						}
						if(p9.getY() >= 800) {
							p9.setY(-500);p9.setX(-600); p9.setFall(false); p9.setDy(0);
							
						}
				}
				
				if(p7.isFall()) {
					p7.fall();
				}
				if(p8.isFall()) {
					p8.fall();
				}
				if(p9.isFall()) {
					p9.fall();
				}
				
					//movement loop
					if (doodleGuy.getX() <= 25) doodleGuy.setX(749);
					if (doodleGuy.getX() >= 750) doodleGuy.setX(26);
					if(doodleGuy.getY() >= 900) life = 0;
				
				
				if(score >= 1000) level = 2;
				
				if(life <= 0) {
					timer.stop();
				}//end no lives
				repaint();
				
			}//end action performed
		}//end meteor listener
		
		
		
		private class pressKey extends KeyAdapter {
			public void keyPressed(KeyEvent e) {
				if(intro) {
					timer.start();
					intro = false;
				}
				
				
			
				if(life <= 0) {
					if(e.getExtendedKeyCode() == KeyEvent.VK_Y) {
						score = 0; life = 1; level = 1;intro = true; 
						
						p1.setX(400); p1.setY(799);
						
						p2.setX(500); p2.setY(700);
					
						p3.setX(300); p3.setY(500);
					
						p4.setX(200); p4.setY(400);
					
						p5.setX(600); p5.setY(200);
					
						p6.setX(100); p6.setY(300);

						p7.setX(-100); p7.setY(300);

						p8.setX(-100); p8.setY(300);
						
						p9.setX(-100); p9.setY(300);
						
						p7.setErase(true); p8.setErase(true); p9.setErase(true);
						
						doodleGuy.setX(400); doodleGuy.setY(550); doodleGuy.setDy(0);
						p7.setFall(false); p8.setFall(false); p9.setFall(false);
					}if(e.getExtendedKeyCode() == KeyEvent.VK_N) {
						System.exit(0);}
				}else {
					if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						doodleGuy.move(20, 0);
					}
					
					if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						doodleGuy.move(-20, 0);
					}
				}//end else
				repaint();
				}//end keyPress
			}//end keyPressed
		
		private void playHit() {
			
			try {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(ClassLoader.getSystemResource("Liam Waterbury - smb_jump-small.wav"));
				hit = AudioSystem.getClip();
				hit.open(audioInputStream);
				hit.start();
				
			} catch (Exception e) {
				System.out.println("Error no file found");
				e.printStackTrace();
			}
			
			
			
		}//end playHit
		
		
		
		public static void main(String[] args) {
		JFrame f = new JFrame();	
		f.setSize(1300, 900);
		f.setTitle("Doodle Jump");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		doodleApp p = new doodleApp();
		
		Container c = f.getContentPane();
		c.add(p);
		
		f.setVisible(true);
		
		
		}//end main
	}//end class
