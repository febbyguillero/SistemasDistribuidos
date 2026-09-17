package tablero;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static tablero.UtilidadesFlota.*;


public class Juego extends Application {

	/**
	 * Implementa el juego 'Hundir la flota' mediante una interfaz grafica (GUI)
	 * desarrollada con JavaFX.
	 */

	/** Parametros por defecto de una partida */
	public static final int NUMFILAS=8, NUMCOLUMNAS=8, NUMBARCOS=6;

	// El juego se encarga de crear y modificar la interfaz grafica
	private GuiTablero guiTablero = null;
	// Objeto con los datos de la partida en juego
	private Partida partida = null;

	/** Atributos de la partida guardados en el juego para simplificar
	 *     su implementacion */
	private int quedan = NUMBARCOS, disparos = 0;

	/**
	 * Programa principal. Lanza la aplicacion JavaFX.
	 * @param args argumentos de entrada desde la linea de ordenes del sistema. No hay ninguno
	 */
	public static void main(String[] args) {
		launch(args);
	} // end main

	/**
	 * Metodo llamado automaticamente por JavaFX al arrancar la aplicacion.
	 * Crea la primera partida y dibuja la interfaz grafica.
	 * @param primaryStage ventana principal de la aplicacion JavaFX
	 */
	@Override
	public void start(Stage primaryStage) {
		partida = new Partida(NUMFILAS, NUMCOLUMNAS, NUMBARCOS);
		guiTablero = new GuiTablero(NUMFILAS, NUMCOLUMNAS, primaryStage);
		guiTablero.dibujaTablero();
	} // end start


	/*************************************************************/
	/*********  CLASE INTERNA GuiTablero   ***********************/
	/*************************************************************/
	/**
	 * Clase para gestionar la Vista del tablero del juego
	 */
	private class GuiTablero {

		private static final String COLOR_AGUA = "cyan";
		private static final String COLOR_SOLUCION = "magenta";
		private static final String COLOR_HUNDIDO = "red";
		private static final String COLOR_TOCADO = "orange";

		private int numFilas, numColumnas;

		private Stage stage = null;              	// Ventana principal
		private BorderPane panelPrincipal = null; 	// Panel principal de la ventana
		private GridPane panelGrid = null;       	// Panel con las casillas del tablero
		private Label estado = null;             	// Texto en el panel de estado
		private Button buttons[][] = null;        	// Botones asociados a las casillas

		/**
		 * Constructor de un tablero dadas sus dimensiones.
		 * @param numFilas     numero de filas del tablero
		 * @param numColumnas  numero de columnas del tablero
		 * @param stage        ventana principal de la aplicacion
		 */
		GuiTablero(int numFilas, int numColumnas, Stage stage) {
			this.numFilas = numFilas;
			this.numColumnas = numColumnas;
			this.stage = stage;
			this.panelPrincipal = new BorderPane();
		}

		/**
		 * Dibuja el tablero de juego y crea la escena inicial
		 */
		public void dibujaTablero() {
			anyadeMenu();
			anyadeGrid(numFilas, numColumnas);
			anyadePanelEstado("Intentos: " + disparos +  "    Barcos restantes: " + quedan);
			Scene scene = new Scene(panelPrincipal, 300, 300);
			stage.setTitle("Hundir la flota");
			stage.setScene(scene);
			stage.show();
		} // end dibujaTablero

		/**
		 * Anyade el menu de opciones del juego y le asocia un escuchador.
		 */
		private void anyadeMenu() {
            // POR IMPLEMENTAR
		} // end anyadeMenu

		/**
		 * Anyade el panel con las casillas del mar y sus etiquetas.
		 * Cada casilla sera un boton que tendra asociado un escuchador.
		 * @param nf	numero de filas
		 * @param nc	numero de columnas
		 */
		private void anyadeGrid(int nf, int nc) {
            // POR IMPLEMENTAR
		} // end anyadeGrid

		/**
		 * Anyade el panel de estado al tablero.
		 * @param cadena	cadena inicial del panel de estado
		 */
		private void anyadePanelEstado(String cadena) {
			estado = new Label(cadena);
			estado.setAlignment(Pos.CENTER);
			estado.setMaxWidth(Double.MAX_VALUE);
			panelPrincipal.setBottom(estado);
		} // end anyadePanelEstado

		/**
		 * Cambia la cadena mostrada en el panel de estado.
		 * @param cadenaEstado	nuevo estado
		 */
		public void cambiaEstado(String cadenaEstado) {
			estado.setText(cadenaEstado);
		} // end cambiaEstado

		/**
		 * Muestra la solucion de la partida y marca la partida como finalizada.
		 */
		public void muestraSolucion() {
            // POR IMPLEMENTAR
		} // end muestraSolucion

		/**
		 * Pinta un barco como hundido en el tablero.
		 * @param objBarco objeto JSON con los datos del barco:
		 *                 fila inicial, columna inicial, orientacion y tamanyo
		 */
		public void pintaBarcoHundido(JSONObject objBarco) {
            // POR IMPLEMENTAR
		} // end pintaBarcoHundido

		/**
		 * Pinta un boton de un color dado.
		 * @param b			boton a pintar
		 * @param color		color CSS a usar
		 */
		public void pintaBoton(Button b, String color) {
			b.setStyle("-fx-background-color: " + color + ";");
		} // end pintaBoton

		/**
		 * Limpia las casillas del tablero pintandolas del color por defecto.
		 */
		public void limpiaTablero() {
			for (int i = 0; i < numFilas; i++) {
				for (int j = 0; j < numColumnas; j++) {
					buttons[i][j].setStyle("");
				}
			}
		} // end limpiaTablero

		/**
		 * Destruye y libera los recursos de la ventana.
		 */
		public void liberaRecursos() {
			stage.close();
		} // end liberaRecursos

	} // end class GuiTablero

	/*********************************************************************/
	/********  CLASE INTERNA MenuListener ********************************/
	/*********************************************************************/

	/**
	 * Clase interna que escucha el menu de Opciones del tablero.
	 */
	private class MenuListener implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent e) {
            // POR IMPLEMENTAR
		} // end handle

	} // end class MenuListener

	/**************************************************************/
	/*********************  CLASE INTERNA ButtonListener **********/
	/**************************************************************/
	/**
	 * Clase interna que escucha cada uno de los botones del tablero.
	 * Para poder identificar el boton que ha generado el evento se pueden
	 * usar las propiedades de los componentes.
	 */
	private class ButtonListener implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent e) {
            // POR IMPLEMENTAR
        } // end handle

	} // end class ButtonListener

} // end class Juego
