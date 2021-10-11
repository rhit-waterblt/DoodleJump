package doodleJump;

import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;

public class Rectangle {
	private int x;
	private int y;
	private int h;
	private int b, gr;
	private int vs, ts;
	private Color c;
	private boolean fire, fall, erase;
	private double dy;
	private ImageIcon img;
	//Constructor
	public Rectangle(int x, int y, int b, int h, Color c) {
		this.x = x;
		this.y = y;
		this.b = b;
		this.h = h;
		this.c = c;
		this.vs = 0; this.ts = 100;
		this.gr = 10;
		this.fire = false;
		this.dy = 1;
		this.fall = false;
		erase = true;
	}
	
	public boolean isErase() {
		return erase;
	}

	public void setErase(boolean erase) {
		this.erase = erase;
	}

	//Draw
	public void draw(Graphics g) {
		Color old = g.getColor();
		g.setColor(c);
		g.drawRect(x, y, b, h);
		g.setColor(old);
	}
	
	public boolean isFall() {
		return fall;
	}

	public void setFall(boolean fall) {
		this.fall = fall;
	}

	//Fill
	public void fill(Graphics g) {
		Color old = g.getColor();
		g.setColor(c);
		g.fillRect(x, y, b, h);
		g.setColor(old);
	}
	
	//ContainsPoint
	public boolean containsPoint(int mx, int my) {
		if(mx > x && mx < x + b && my > y && my < y + h)
			return true;
		else 
			return false;
	}
	
	public void setImage(ImageIcon i) {img = i;}
	public int getBase(ImageIcon i) {return i.getIconWidth();}
	public void drawImage(Graphics g) {
		img.paintIcon(null,  g,  x,  y);
	}
	
	
	//Move
	public void move(int xAmount, int yAmount) {
		x += xAmount;
		y += yAmount;
	}
	
	public void fall() {		       
				dy = dy + .2;
				y = y + (int)dy;
	}
	public void fallPlatform() {		       
		//dy *= -1;
		dy = dy - .2;
		y = y + (int)dy;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public boolean isFire() {
		return fire;
	}

	public void setFire(boolean fire) {
		this.fire = fire;
	}

	public int getB() {return b;}
	public int getH() {return h;}
	

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

//	public boolean hitCircle(Circle c) {
//		if (c.getCenterX() > getX() && c.getCenterX() < getX() + getB() && c.getCenterY() + c.getR() > getY() - getH())
//			return true;
//		else
//			return false;
//	}
//	
//	public boolean collides(Circle c) {
//		if (x <= c.getCenterX()+c.getR() && x+b >= c.getCenterX() - c.getR() 
//				&& y <= c.getCenterY() + c.getR() && y-h>= c.getCenterY() - c.getR()) {
//			return true;
//		}
//		return false;
//	}
	
	
	public boolean lands(Rectangle p) {
		if ( x + 20 < p.getX() + p.getB() && x + b + 20 > p.getX() && y + 100 >= p.getY() - p.getH() * 1.5 
				&& y <= p.getY() - p.getH() * 3){
			return true;
		}
		return false;
	}
}//end class