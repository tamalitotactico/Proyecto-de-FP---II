import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Enemigo extends Guerrero{
	
	protected double vida = 1000;
	protected int armadura = 20;
	protected int daño = 10;
	protected String fase="Normal";
	private int desarmado = 0;
	public Enemigo() {
		
	}
	
	public void turnosEnemigo(int turno) {
		
		armadura = 20;
		if (turno>=15) {
			faseFinal();
		}	else {
			if (turno== 1 || turno == 2)
				faseNormal();
			if (turno== 3 || turno== 4)
				faseDefensa();
			if (turno== 5 || turno == 6)
				faseAtaque();
			if (turno==7 || turno == 8)
				faseNormal();
			if (turno>=9 && turno<=13)
				faseAtaque();
			if (turno>=14 && turno<15)
				faseDefensa();
		}
	}
	
	public void desarmado() {
		desarmado = 3;
	}
	
	public void desarmadoColdown() {
		if (desarmado > 0) {
			desarmado--;
		}
	}
		
	public void faseAtaque() {
		fase = "Ataque";
		daño = 10;
		armadura = 20;
	}
	
	public void faseDefensa() {
		fase = "Defensa";
		armadura = 50;
		daño = 5;
	}
	
	public void faseNormal() {
		fase = "Normal";
		armadura = 5;
		daño = 5;
	}
	
	public void faseFinal() {
		fase = "Final";
		armadura = 50;
		daño = 10;
	}
		
	public void serAtacado(double dañoRecibido) {
		if (armadura == 0) {
			vida = vida-dañoRecibido;
		}	else {
			double block = (dañoRecibido*armadura)/100;
			vida = vida-(dañoRecibido-block);
		}
	}
	
	public void cambiarArmadura() {
		armadura = 20;
	}
		
	public void ataqueEnemigo(JTextArea info, JLabel title, Enemigo enemy, Mago mago) {
		Timer timer = new Timer();
		
		TimerTask tarea = new TimerTask() {
			public void run() {
				mago.personajeVivo();
				if (desarmado==0) {
					if (mago.vivo == true) {
						title.setText("¡¡EL ENEMIGO ATACÓ!!");
						info.setText("\n\n\t¡¡EL ENEMIGO REALIZÓ UN ATAQUE E \n\tINFLINGIÓ "+enemy.daño+
								" DE DAÑO AL MAGO!!");
						mago.recibirAtaque(daño);
					}	else {
						title.setText("¡¡EL ENEMIGO YA DERROTÓ AL MAGO!!");
						info.setText("\n\n\t¡¡EL ENEMIGO REALIZÓ UN ATAQUE PERO\n\tEL MAGO ESTABA DERROTADO!!");
					}
				}	else {
					title.setText("¡¡EL ENEMIGO ATACÓ!!");
					info.setText("\n\n\t¡¡PERO ESTE SE ECONTRABA DESARMADO!!\n\n\tTiempo restante: "+desarmado);
				}
			}
		};
		timer.schedule(tarea, 1000);
	}
	
	public void ataqueEnemigo(JTextArea info, JLabel title, Enemigo enemy, Espadachin esp) {
		Timer timer = new Timer();
		
		TimerTask tarea = new TimerTask() {
			public void run() {
				esp.personajeVivo();
				if (desarmado == 0) {
					if (esp.vivo == true) {
						title.setText("¡¡EL ENEMIGO ATACÓ!!");
						info.setText("\n\n\t¡¡EL ENEMIGO REALIZÓ UN ATAQUE E \n\tINFLINGIÓ "+enemy.daño+
								" DE DAÑO AL ESPADACHÍN!!");
						esp.recibirAtaque(daño);
					}	else {
						title.setText("¡¡EL ENEMIGO YA DERROTÓ AL ESPADACHÍN!!");
						info.setText("\n\n\t¡¡EL ENEMIGO REALIZÓ UN ATAQUE PERO\n\tEL MAGO ESTABA DERROTADO!!");
					}
				}	else {
					title.setText("¡¡EL ENEMIGO ATACÓ!!");
					info.setText("\n\n\t¡¡PERO ESTE SE ECONTRABA DESARMADO!!\n\n\tTiempo restante: "+desarmado);
				}
			}
		};
		timer.schedule(tarea, 1000);
	}
	
	public void ataqueEnemigo(JTextArea info, JLabel title, Enemigo enemy, LuchadorFuerte luch) {
		Timer timer = new Timer();
		
		TimerTask tarea = new TimerTask() {
			public void run() {
				luch.personajeVivo();
				if (desarmado == 0) {	
					if (luch.vivo == true) {
						title.setText("¡¡EL ENEMIGO ATACÓ!!");
						info.setText("\n\n\t¡¡EL ENEMIGO REALIZÓ UN ATAQUE E \n\tINFLINGIÓ "+enemy.daño+
								" DE DAÑO AL LUCHADOR!!");
						luch.recibirAtaque(daño);
					}	else {
						title.setText("¡¡EL ENEMIGO YA DERROTÓ AL ESPADACHÍN!!");
						info.setText("\n\n\t¡¡EL ENEMIGO REALIZÓ UN ATAQUE PERO\n\tEL LUCHADOR ESTABA DERROTADO!!");
					}
				}	else {
					title.setText("¡¡EL ENEMIGO ATACÓ!!");
					info.setText("\n\n\t¡¡PERO ESTE SE ECONTRABA DESARMADO!!\n\n\tTiempo restante: "+desarmado);
				}
			}
		};
		timer.schedule(tarea, 1000);
	}
}
