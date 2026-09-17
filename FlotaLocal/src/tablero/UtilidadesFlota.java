package tablero;

import org.json.simple.JSONObject;

/**
 * Clase con constantes y metodos auxiliares compartidos por el cliente
 * y el servidor del juego Hundir la flota.
 */
public final class UtilidadesFlota {

	/** Estados posibles de una casilla */
	public static final int AGUA = -1;
	public static final int TOCADO = -2;
	public static final int HUNDIDO = -3;

	/**
	 * Constructor privado para evitar que se creen objetos de esta clase.
	 */
	private UtilidadesFlota() {
	}

	/**
	 * Devuelve como entero el valor numerico asociado a una clave de un objeto JSON.
	 * @param obj    objeto JSON del que se quiere obtener el valor
	 * @param clave  clave asociada al valor numerico
	 * @return       valor entero asociado a la clave
	 */
	public static int getInt(JSONObject obj, String clave) {
		return ((Number) obj.get(clave)).intValue();
	}

	/**
	 * Devuelve como caracter el valor asociado a una clave de un objeto JSON.
	 * @param obj    objeto JSON del que se quiere obtener el valor
	 * @param clave  clave asociada al valor de tipo caracter
	 * @return       primer caracter del valor asociado a la clave
	 */
	public static char getChar(JSONObject obj, String clave) {
		return obj.get(clave).toString().charAt(0);
	}
}