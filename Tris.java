import java.awt.*;

public class Tris{
	/**
	 * Righe
	 */
	public final int I = 3;
	/**
	 * Colonne
	 */
	public final int J = 3;
	/**
	 * Costante casella vuota
	 */
	private final int NONE = 0; 
	/**
	 * Costante player 1
	 */
	private final int P1 = 1;
	/**
	 * Costante player 2
	 */
	private final int P2 = 2;
	/**
	 * Player 1
	 */
	private Player player1;
	/**
	 * Player 2
	 */
	private Player player2;
	/**
	 * Rettangoli per l'interfaccia
	 */
	private Rectangle mRect[][];
	/**
	 * Rettangolo per l'interfaccia
	 */
	private Rectangle rect;
	/**
	 * Costante rettangolo fisso
	 */
	private Rectangle RETTANGOLO = new Rectangle(0,0,300,300);
	/**
	 * Costruttore di un tris
	 */
	public Tris(){
		setPlayer1(new Player(I,J));
		setPlayer2(new Player(I,J));
		setRect(new Rectangle(0,0,0,0));
		setMRect(new Rectangle[I][J]);
	}
	/**
	 * Getter del player 1
	 */
	public Player getPlayer1(){
		return this.player1;
	}
	/**
	 * Getter del player 2
	 */
	public Player getPlayer2(){
		return this.player2;
	}
	/**
	 * Getter per l'array di rettangoli
	 */
	public Rectangle[][] getRectangles(){
		return this.mRect;
	}
	/**
	 * Getter del rettangolo "padre"
	 */
	public Rectangle getRect(){
		return this.rect;
	}
	/**
	 * Setter del player 1
	 */
	public void setPlayer1(Player player1){
		this.player1 = player1;
	}
	/**
	 * Getter del player 2
	 */
	public void setPlayer2(Player player1){
		this.player2 = player2;
	}
	/**
	 * Metodo per impostare nella matrice del giocatore il punto in cui si ha cliccato nella GUI
	 * @param point Punto in cui si ha cliccato
	 */
	public void translateClick(Point point){
		for(int i = 0; i < mRect.length; i++){
			for(int j = 0; j < mRect.length; j++){
				if(mRect[i][j].contains(point) && player1.getMatrix(i,j) == NONE){
					if(player1.getIsMyTurn()){
						player1.disableTurn();
						player2.enableTurn();
						player1.setMatrix(i,j,P1);
					}
					else{
						player2.disableTurn();
						player1.enableTurn();
						player2.setMatrix(i,j,P2);
					}
				}
			}
		}
	}
	public Point translateHover(Point point){
		for(int i = 0; i < mRect.length; i++){
			for(int j = 0; j < mRect.length; j++){
				if(mRect[i][j].contains(point))
					return new Point(i,j);
			}
		}
		return null;
	}
	/**
	 * Setter dell'array di rettangoli
	 */
	public void setMRect(Rectangle[][] mRect){
		this.mRect = mRect;
		if(mRect[0][0] == null){
			for(int i = 0; i < mRect.length; i++){
				for(int j = 0; j < mRect[i].length; j++){
					this.mRect[i][j] = new Rectangle(0,0,0,0);
				}
			}
		}
	}
	/**
	 * Setter del rettangolo "padre"
	 */
	public void setRect(Rectangle rect){
		this.rect = rect;
	}
	/**
	 * Metodo per ridimensionare il rettangolo con un rapporto
	 * @param width larghezza del quadrato a cui si deve adattare
	 * @param height altezza del quadrato a cui si deve adattare
	 */
	public void resizeRectangle(double width, double height){
		if(getRateo(width,height) > getRateo(RETTANGOLO.getWidth(),RETTANGOLO.getHeight()))
			rect.setSize((int)(getRateo(height,RETTANGOLO.getHeight())*RETTANGOLO.getWidth()),(int)height);
		else
			rect.setSize((int)width,(int)(getRateo(width,RETTANGOLO.getWidth())*RETTANGOLO.getHeight()));
	}
	/**
	 * Metodo per ridimensionare l'array di rettangoli in base alla grandezza del rettangolo "padre"
	 */
	public void resizeRectangles(){
		for(int i = 0; i < this.I; i++){
			for(int j = 0; j < this.J; j++){
				mRect[i][j].setSize((int)(rect.getWidth()/J),(int)(rect.getWidth()/I));
				mRect[i][j].setLocation((int)((rect.getWidth()*j)/J),(int)((rect.getHeight()*i)/I));
			}
		}
	}
	/**
	 * Metodo che ritorna il rapporto tra due numeri
	 * @param f primo numero
	 * @param l secondo numero
	 * @return rapporto tra primo e secondo numero
	 */
	private double getRateo(double f, double l){
		return (double)f/l;
	}
}