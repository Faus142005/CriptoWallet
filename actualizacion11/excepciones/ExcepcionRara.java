package excepciones;

public class ExcepcionRara extends Exception{

	public ExcepcionRara(String msg) {
		super(msg);
	}
	
	public ExcepcionRara() {
		super("Error inesperado");
	}
}
