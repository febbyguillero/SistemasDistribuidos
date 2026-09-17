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

    public static final int NUMFILAS = 8, NUMCOLUMNAS = 8, NUMBARCOS = 6;

    private GuiTablero guiTablero = null;
    private Partida partida = null;
    private int quedan = NUMBARCOS, disparos = 0;
    private boolean partidaFinalizada = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        partida = new Partida(NUMFILAS, NUMCOLUMNAS, NUMBARCOS);
        quedan = NUMBARCOS;
        disparos = 0;
        partidaFinalizada = false;
        guiTablero = new GuiTablero(NUMFILAS, NUMCOLUMNAS, primaryStage);
        guiTablero.dibujaTablero();
    }

    private class GuiTablero {

        private static final String COLOR_AGUA = "cyan";
        private static final String COLOR_SOLUCION = "magenta";
        private static final String COLOR_HUNDIDO = "red";
        private static final String COLOR_TOCADO = "orange";

        private int numFilas, numColumnas;
        private Stage stage = null;
        private BorderPane panelPrincipal = null;
        private GridPane panelGrid = null;
        private Label estado = null;
        private Button buttons[][] = null;

        GuiTablero(int numFilas, int numColumnas, Stage stage) {
            this.numFilas = numFilas;
            this.numColumnas = numColumnas;
            this.stage = stage;
            this.panelPrincipal = new BorderPane();
        }

        public void dibujaTablero() {
            anyadeMenu();
            anyadeGrid(numFilas, numColumnas);
            anyadePanelEstado("Intentos: " + disparos + "    Barcos restantes: " + quedan);
            Scene scene = new Scene(panelPrincipal, 360, 330);
            stage.setTitle("Hundir la flota");
            stage.setScene(scene);
            stage.show();
        }

        private void anyadeMenu() {
            Menu menu = new Menu("Opciones");
            MenuItem nuevaPartida = new MenuItem("Nueva partida");
            MenuItem mostrarSolucion = new MenuItem("Mostrar solución");
            MenuItem salir = new MenuItem("Salir");
            MenuListener listener = new MenuListener();

            nuevaPartida.setOnAction(listener);
            mostrarSolucion.setOnAction(listener);
            salir.setOnAction(listener);
            menu.getItems().addAll(nuevaPartida, mostrarSolucion, salir);
            panelPrincipal.setTop(new MenuBar(menu));
        }

        private void anyadeGrid(int nf, int nc) {
            panelGrid = new GridPane();
            panelGrid.setHgap(2);
            panelGrid.setVgap(2);
            panelGrid.setAlignment(Pos.CENTER);
            buttons = new Button[nf][nc];

            panelGrid.add(new Label(""), 0, 0);
            for (int j = 0; j < nc; j++) {
                panelGrid.add(new Label(String.valueOf(j + 1)), j + 1, 0);
            }

            for (int i = 0; i < nf; i++) {
                panelGrid.add(new Label(String.valueOf((char) ('A' + i))), 0, i + 1);
                for (int j = 0; j < nc; j++) {
                    Button boton = new Button();
                    boton.setPrefSize(32, 32);
                    boton.getProperties().put("fila", i);
                    boton.getProperties().put("columna", j);
                    boton.setOnAction(new ButtonListener());
                    buttons[i][j] = boton;
                    panelGrid.add(boton, j + 1, i + 1);
                }
            }
            panelPrincipal.setCenter(panelGrid);
        }

        private void anyadePanelEstado(String cadena) {
            estado = new Label(cadena);
            estado.setAlignment(Pos.CENTER);
            estado.setMaxWidth(Double.MAX_VALUE);
            panelPrincipal.setBottom(estado);
        }

        public void cambiaEstado(String cadenaEstado) {
            estado.setText(cadenaEstado);
        }

        public void muestraSolucion() {
            limpiaTablero();
            for (int i = 0; i < numFilas; i++) {
                for (int j = 0; j < numColumnas; j++) {
                    buttons[i][j].setDisable(true);
                    pintaBoton(buttons[i][j], COLOR_AGUA);
                }
            }

            JSONArray solucion = partida.getSolucion();
            for (Object elemento : solucion) {
                pintaBarcoSolucion((JSONObject) elemento);
            }
            partidaFinalizada = true;
        }

        private void pintaBarcoSolucion(JSONObject objBarco) {
            int fila = getInt(objBarco, "filaInicial");
            int columna = getInt(objBarco, "columnaInicial");
            int tamanyo = getInt(objBarco, "tamanyo");
            char orientacion = getChar(objBarco, "orientacion");

            for (int i = 0; i < tamanyo; i++) {
                int f = fila + (orientacion == 'V' ? i : 0);
                int c = columna + (orientacion == 'H' ? i : 0);
                pintaBoton(buttons[f][c], COLOR_SOLUCION);
            }
        }

        public void pintaBarcoHundido(JSONObject objBarco) {
            int fila = getInt(objBarco, "filaInicial");
            int columna = getInt(objBarco, "columnaInicial");
            int tamanyo = getInt(objBarco, "tamanyo");
            char orientacion = getChar(objBarco, "orientacion");

            for (int i = 0; i < tamanyo; i++) {
                int f = fila + (orientacion == 'V' ? i : 0);
                int c = columna + (orientacion == 'H' ? i : 0);
                pintaBoton(buttons[f][c], COLOR_HUNDIDO);
            }
        }

        public void pintaBoton(Button b, String color) {
            b.setStyle("-fx-background-color: " + color + ";");
        }

        public void limpiaTablero() {
            for (int i = 0; i < numFilas; i++) {
                for (int j = 0; j < numColumnas; j++) {
                    buttons[i][j].setStyle("");
                }
            }
        }

        public void liberaRecursos() {
            stage.close();
        }
    }

    private class MenuListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            MenuItem item = (MenuItem) e.getSource();
            String opcion = item.getText();

            if (opcion.equals("Salir")) {
                guiTablero.liberaRecursos();
            } else if (opcion.equals("Nueva partida")) {
                partida = new Partida(NUMFILAS, NUMCOLUMNAS, NUMBARCOS);
                quedan = NUMBARCOS;
                disparos = 0;
                partidaFinalizada = false;
                guiTablero.limpiaTablero();

                for (int i = 0; i < NUMFILAS; i++) {
                    for (int j = 0; j < NUMCOLUMNAS; j++) {
                        guiTablero.buttons[i][j].setDisable(false);
                    }
                }
                guiTablero.cambiaEstado("Intentos: " + disparos + "    Barcos restantes: " + quedan);
            } else if (opcion.equals("Mostrar solución")) {
                guiTablero.muestraSolucion();
            }
        }
    }

    private class ButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            if (partidaFinalizada) {
                return;
            }

            Button boton = (Button) e.getSource();
            int fila = (Integer) boton.getProperties().get("fila");
            int columna = (Integer) boton.getProperties().get("columna");
            JSONObject resultado = partida.pruebaCasilla(fila, columna);
            int valor = getInt(resultado, "resultado");
            disparos++;

            if (valor == AGUA) {
                guiTablero.pintaBoton(boton, GuiTablero.COLOR_AGUA);
            } else if (valor == TOCADO) {
                guiTablero.pintaBoton(boton, GuiTablero.COLOR_TOCADO);
            } else if (valor >= 0) {
                guiTablero.pintaBarcoHundido(partida.getBarco(valor));
                quedan--;
            }

            guiTablero.cambiaEstado("Intentos: " + disparos + "    Barcos restantes: " + quedan);

            if (quedan == 0) {
                partidaFinalizada = true;
                for (int i = 0; i < NUMFILAS; i++) {
                    for (int j = 0; j < NUMCOLUMNAS; j++) {
                        guiTablero.buttons[i][j].setDisable(true);
                    }
                }
            }
        }
    }
}
