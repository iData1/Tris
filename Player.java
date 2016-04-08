public class Player{
	/**
	 * Matrice di gioco statica
	 */
	private static int[][] matrix;
	/**
	 * Valore booleano del turno del giocatore
	 */
	private boolean isMyTurn;
	
	public Player(int i, int j){
		setMatrix(i,j);
		isMyTurn = false;
	}
	/**
	 * Costruttore player
	 */
	public Player(){
		this(3,3);
	}
	/**
	 * Setter del turno del giocatore
	 * @param isMyTurn valore booleano corrispondente al turno
	 */
	public void setIsMyTurn(boolean isMyTurn){
		this.isMyTurn = isMyTurn;
	}
	/**
	 * Metodo per disabilitare il turno del giocatore
	 */
	public void disableTurn(){
		setIsMyTurn(false);
	}
	/**
	 * Metodo per abilitare il turno del giocatore
	 */
	public void enableTurn(){
		setIsMyTurn(true);
	}
	/**
	 * Getter del turno del giocatore
	 * @return il turno del giocatore in formato booleano
	 */
	public boolean getIsMyTurn(){
		return this.isMyTurn;
	}
	/**
	 * Costruttore personalizzato per la inizializzazione della tabella
	 * @param i Quante righe per la tabella
	 * @param j Quante colonne per la tabella
	 */
	public void setMatrix(int[][] matrix){
		this.matrix = matrix;
		if(this.matrix == null){
			for(int i = 0; i < this.matrix.length; i++){
				for(int j = 0; j < this.matrix.length; j++){
					setMatrix(i,j,0);
				}
			}
		}

	}
	/**
	 * Getter per la matrice
	 */
	public int[][] getMatrix(){
		return this.matrix;
	}
	/**
	 * Metodo per sapere il valore nella matrice in un determinato punto
	 * @param i Numero della riga
	 * @param j Numero della colonna
	 */
	public int getMatrix(int i, int j){
		return this.matrix[i][j];
	}
	/**
	 * Metodo per impostare un valore nella matrice in un determinato punto
	 * @param i Riga della matrice
	 * @param j Colonna della matrice 
	 * @param value Valore da inserire nella matrice
	 */
	public void setMatrix(int i, int j, int value){
		this.matrix[i][j] = value;
	}
	/**
	 * Metodo per impostare il valore nella matrice in un determinato punto
	 * @param i Numero della riga
	 * @param j Numero della colonna
	 */
	public void setMatrix(int i, int j){
		setMatrix(new int[i][j]);
	}
}