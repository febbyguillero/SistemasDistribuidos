package tablero;
import org.json.simple.JSONObject;

public class Barco {
	/**
	 * Clase para guardar la informacion de un barco en una partida de
	 * 'Hundir la flota'
	 */

	private int filaInicial,	// coordenadas iniciales del barco
	columnaInicial;
	private char orientacion;	// 'H': horizontal; 'V':vertical
	private int	tamanyo,		// numero de casillas que ocupa
	tocadas;					// numero de casillas tocadas

	/**
	 * Constructor por defecto. No hace nada especifico
	 */
	public Barco() {
		super();
	}

	/**
	 * Constructor con argumentos
	 * @param f				fila inicial del barco
	 * @param c				columna inicial del barco
	 * @param orientacion	orientacion (vertical u horizontal)
	 * @param tamanyo		tamanyo del barco
	 */
	public Barco(int f, int c, char orientacion, int tamanyo) {
		super();
		this.filaInicial = f;
		this.columnaInicial = c;
		this.orientacion = orientacion;
		this.tamanyo = tamanyo;
		this.tocadas = 0;
	}

	/**
	 * Toca una nueva casilla de un barco
	 */
	public void tocaBarco() {
		this.tocadas++;
	}

	/**
	 * Indica si un barco esta hundido
	 * @return	valor logico indicando si se ha hundido
	 */
	public boolean estaHundido() {
		return (this.tocadas == this.tamanyo);
	}

	/**
	 * Devuelve un objeto JSON con la informacion del barco
	 */
	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {
		// POR IMPLEMENTAR
		return null; // POR MODIFICAR
	}

	/**
	 * Devuelve una cadena conteniendo los datos del barco en formato JSON
	 */
	@Override
	public String toString() {
		return this.toJSON().toJSONString();
	}

	/****************************************************************/
	/****************     GETTERS y SETTERS    **********************/
	/****************************************************************/

	/**
	 * @return the filaInicial
	 */
	public int getFilaInicial() {
		return filaInicial;
	}

	/**
	 * @return the columnaInicial
	 */
	public int getColumnaInicial() {
		return columnaInicial;
	}

	/**
	 * @return the orientacion
	 */
	public char getOrientacion() {
		return orientacion;
	}

	/**
	 * @return the tamanyo
	 */
	public int getTamanyo() {
		return tamanyo;
	}

	/**
	 * @return the tocadas
	 */
	public int getTocadas() {
		return tocadas;
	}

} // end class Barco
