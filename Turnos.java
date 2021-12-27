import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class Turnos {
	
	protected int turno=1;
	Enemigo enemy = new Enemigo();
	Espadachin espadachin = new Espadachin();
	LuchadorFuerte luchador = new LuchadorFuerte();
	Mago mago = new Mago();
	
	public Turnos() {

	}	
	
	public void pasarTurno() {
		enemy.turnosEnemigo(turno);
		turno++;
		enemy.desarmadoColdown();
		espadachin.personajeVivo();
		luchador.personajeVivo();
		mago.personajeVivo();
	}
	
	public boolean ganar() {
		if (enemy.vida <= 0) {
			return true;
		}	else {
			return false;
		}
	}
	
	public boolean perder() {
		if (espadachin.vivo == false && luchador.vivo == false && mago.vivo == false) {
			return true;
		}	else {
			return false;
		}
	}
}
