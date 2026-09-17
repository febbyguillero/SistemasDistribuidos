package tablero;
import org.json.simple.JSONObject;

public class Barco {
    private int filaInicial, columnaInicial;
    private char orientacion;
    private int tamanyo, tocadas;

    public Barco() {
        super();
    }

    public Barco(int f, int c, char orientacion, int tamanyo) {
        super();
        this.filaInicial = f;
        this.columnaInicial = c;
        this.orientacion = orientacion;
        this.tamanyo = tamanyo;
        this.tocadas = 0;
    }

    public void tocaBarco() {
        this.tocadas++;
    }

    public boolean estaHundido() {
        return this.tocadas == this.tamanyo;
    }

    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject resultado = new JSONObject();
        resultado.put("filaInicial", this.filaInicial);
        resultado.put("columnaInicial", this.columnaInicial);
        resultado.put("orientacion", String.valueOf(this.orientacion));
        resultado.put("tamanyo", this.tamanyo);
        resultado.put("tocadas", this.tocadas);
        return resultado;
    }

    @Override
    public String toString() {
        return this.toJSON().toJSONString();
    }

    public int getFilaInicial() {
        return filaInicial;
    }

    public int getColumnaInicial() {
        return columnaInicial;
    }

    public char getOrientacion() {
        return orientacion;
    }

    public int getTamanyo() {
        return tamanyo;
    }

    public int getTocadas() {
        return tocadas;
    }
}
