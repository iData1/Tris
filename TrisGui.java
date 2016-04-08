import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;


public class TrisGui extends JFrame implements MouseListener, MouseMotionListener{
	private Point mousePos;
	private Tris tris;
	public TrisGui(String name){
		super(name);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		mousePos = new Point(-1,-1);
		tris = new Tris();
	}
	public void paint(Graphics g){
		
		tris.resizeRectangle(getWidth(),getHeight());
		tris.resizeRectangles();
		
		Rectangle[][] r = tris.getRectangles();
		for(int i = 0; i < tris.I; i++){
			for(int j = 0; j < tris.J; j++){
				if(tris.translateHover(mousePos) != null && ((int)tris.translateHover(mousePos).getX() == i && (int)tris.translateHover(mousePos).getY() == j)){
					g.setColor(Color.RED);
					g.fillRect((int)r[i][j].getX(),(int)r[i][j].getY(),(int)r[i][j].getWidth(),(int)r[i][j].getHeight());
				}
				else{
					g.setColor(Color.WHITE);
					g.fillRect((int)r[i][j].getX(),(int)r[i][j].getY(),(int)r[i][j].getWidth(),(int)r[i][j].getHeight());
				}
				g.setColor(Color.BLACK);
				g.drawRect((int)r[i][j].getX(),(int)r[i][j].getY(),(int)r[i][j].getWidth(),(int)r[i][j].getHeight());

			}
		}
		System.out.println("I: "+tris.translateHover(mousePos).getX()+" J: "+ tris.translateHover(mousePos).getY());
		System.out.println("Width: "+getWidth()+" Height: "+ getHeight());
	}
	public static void main(String[] args) {
		TrisGui disegno = new TrisGui("tris");
		disegno.setSize(300, 300);
		disegno.setVisible(true);
	}
	public void mouseClicked(MouseEvent e) {
		if(tris.translateHover(e.getPoint()) != null){
			tris.translateClick(e.getPoint());
			repaint();
		}
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    	mousePos = e.getPoint();
		if(tris.translateHover(mousePos) != null)
			repaint();
    }

}
