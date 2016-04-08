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
	 * Variabile di tipo Point che contiene al suo interno i valori di quanto traslare di x e y il disegno
	 */
	private Point translate;
	/**
	 * Costruttore di un tris
	 */
	public Tris(){
		setPlayer1(new Player(I,J));
		setPlayer2(new Player(I,J));
		setRect(new Rectangle(0,0,0,0));
		setMRect(new Rectangle[I][J]);
		setTranslate(0,0);
	}
	/**
	 * Getter del player 1
	 * @return player1
	 */
	public Player getPlayer1(){
		return this.player1;
	}
	/**
	 * Getter del player 2
	 * @return player2
	 */
	public Player getPlayer2(){
		return this.player2;
	}
	/**
	 * Getter per l'array di rettangoli
	 * @return mRect
	 */
	public Rectangle[][] getRectangles(){
		return this.mRect;
	}
	/**
	 * Getter del rettangolo "padre"
	 * @return rect
	 */
	public Rectangle getRect(){
		return this.rect;
	}
	/**
	 * Getter per il translate
	 * @return translate
	 */
	public Point getTranslate(){
		return this.translate;
	}
	/**
	 * Setter del player 1
	 * @param player1 Il player da impostare
	 */
	public void setPlayer1(Player player1){
		this.player1 = player1;
	}
	/**
	 * Getter del player 2
	 * @param player1 Il player da impostare
	 */
	public void setPlayer2(Player player1){
		this.player2 = player2;
	}
	/**
	 * Setter per il point translate
	 * @param x di quanto spostarlo sull'asse x
	 * @param y di quanto spostarlo sull'asse y
	 */
	public void setTranslate(double x, double y){
		if(this.translate != null)
			this.translate.setLocation(x,y);
		else
			this.translate = new Point((int)x,(int)y);
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
	/**
	 * Metodo per vedere se il punto in cui si ha cliccato corrisponde alla matrice
	 * @param i riga
	 * @param j colonna
	 * @return flag
	 */
	public boolean isClicked(int i, int j){
		if(player1.getMatrix(i,j) != NONE)
			return true;
		else
			return false;
	}
	/**
	 * Metodo per controllare se il mouse si trova in un quadrato
	 * @param point Il punto in cui si trova il mouse
	 * @return coordinate nella matrice di dove si ha il mouse
	 */
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
	 * @param mRect rettangolo da impostare
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
	 * @param rect Il rettangolo da impostare
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
		if(getRateo(width,height) > getRateo(RETTANGOLO.getWidth(),RETTANGOLO.getHeight())){
			rect.setSize((int)(getRateo(height,RETTANGOLO.getHeight())*RETTANGOLO.getWidth()),(int)height);
			setTranslate((width-rect.getWidth())/2,0);
		}
		else{
			rect.setSize((int)width,(int)(getRateo(width,RETTANGOLO.getWidth())*RETTANGOLO.getHeight()));
			setTranslate(0,(height-rect.getHeight())/2);
		}
	}
	/**
	 * Metodo per ridimensionare l'array di rettangoli in base alla grandezza del rettangolo "padre"
	 */
	public void resizeRectangles(){
		for(int i = 0; i < this.I; i++){
			for(int j = 0; j < this.J; j++){
				mRect[i][j].setSize((int)(rect.getWidth()/J),(int)(rect.getWidth()/I));
				mRect[i][j].setLocation((int)((rect.getWidth()*j)/J),(int)((rect.getHeight()*i)/I));
				mRect[i][j].translate((int)translate.getX(),(int)translate.getY());
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