import java.util.*;

public class Gol {
	
	String tipus = "";
	String hora = "";
	String resultat = "";
	String jugador = "";
	String observacions = "";
	int segons = 0;
	ArrayList<String> aspectes = new ArrayList<String>();
	Partit partit = null;
	
	public Gol (Partit partit, String tipus, String hora, String resultat, String jugador, String observacions) {
		// tipus (Aspecte o AspecteNo), hora, resultat, jugador, observacions (p., p.p., anul·lat...)
		this.partit = partit;
		this.tipus = tipus;
		this.hora = hora;
		String[] mom = hora.split(":");
		int h = Integer.parseInt(mom[0]);
		int m = Integer.parseInt(mom[1]);
		int s = Integer.parseInt(mom[2]);
		this.segons = (h * 3600 + m * 60 + s) - (partit.horaIni * 3600 + partit.minutIni * 60 + partit.segonIni);
		this.resultat = resultat;
		this.jugador = jugador;
		this.observacions = observacions;
	}
	
	protected String getTipus() {
		return tipus;
	}
	
	protected String getHora() {
		return hora;
	}
	
	protected int getSegons() {
		return segons;
	}
	
	protected String getResultat() {
		return resultat;
	}
	
	protected String getJugador() {
		return jugador;
	}
	
	protected String getObservacions() {
		return observacions;
	}
	
	protected ArrayList<String> getAspectes() {
		return aspectes;
	}
	
	protected void setAspecte(String aspecte) {
		aspectes.add(aspecte);
	}
}