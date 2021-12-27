import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Enemigo extends Guerrero{
	
	protected double vida = 1000;
	protected int armadura = 20;
	protected int da�o = 10;
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
		da�o = 10;
		armadura = 20;
	}
	
	public void faseDefensa() {
		fase = "Defensa";
		armadura = 50;
		da�o = 5;
	}
	
	public void faseNormal() {
		fase = "Normal";
		armadura = 5;
		da�o = 5;
	}
	
	public void faseFinal() {
		fase = "Final";
		armadura = 50;
		da�o = 10;
	}
		
	public void serAtacado(double da�oRecibido) {
		if (armadura == 0) {
			vida = vida-da�oRecibido;
		}	else {
			double block = (da�oRecibido*armadura)/100;
			vida = vida-(da�oRecibido-block);
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
						title.setText("��EL ENEMIGO ATAC�!!");
						info.setText("\n\n\t��EL ENEMIGO REALIZ� UN ATAQUE E \n\tINFLINGI� "+enemy.da�o+
								" DE DA�O AL MAGO!!");
						mago.recibirAtaque(da�o);
					}	else {
						title.setText("��EL ENEMIGO YA DERROT� AL MAGO!!");
						info.setText("\n\n\t��EL ENEMIGO REALIZ� UN ATAQUE PERO\n\tEL MAGO ESTABA DERROTADO!!");
					}
				}	else {
					title.setText("��EL ENEMIGO ATAC�!!");
					info.setText("\n\n\t��PERO ESTE SE ECONTRABA DESARMADO!!\n\n\tTiempo restante: "+desarmado);
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
						title.setText("��EL ENEMIGO ATAC�!!");
						info.setText("\n\n\t��EL ENEMIGO REALIZ� UN ATAQUE E \n\tINFLINGI� "+enemy.da�o+
								" DE DA�O AL ESPADACH�N!!");
						esp.recibirAtaque(da�o);
					}	else {
						title.setText("��EL ENEMIGO YA DERROT� AL ESPADACH�N!!");
						info.setText("\n\n\t��EL ENEMIGO REALIZ� UN ATAQUE PERO\n\tEL MAGO ESTABA DERROTADO!!");
					}
				}	else {
					title.setText("��EL ENEMIGO ATAC�!!");
					info.setText("\n\n\t��PERO ESTE SE ECONTRABA DESARMADO!!\n\n\tTiempo restante: "+desarmado);
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
						title.setText("��EL ENEMIGO ATAC�!!");
						info.setText("\n\n\t��EL ENEMIGO REALIZ� UN ATAQUE E \n\tINFLINGI� "+enemy.da�o+
								" DE DA�O AL LUCHADOR!!");
						luch.recibirAtaque(da�o);
					}	else {
						title.setText("��EL ENEMIGO YA DERROT� AL ESPADACH�N!!");
						info.setText("\n\n\t��EL ENEMIGO REALIZ� UN ATAQUE PERO\n\tEL LUCHADOR ESTABA DERROTADO!!");
					}
				}	else {
					title.setText("��EL ENEMIGO ATAC�!!");
					info.setText("\n\n\t��PERO ESTE SE ECONTRABA DESARMADO!!\n\n\tTiempo restante: "+desarmado);
				}
			}
		};
		timer.schedule(tarea, 1000);
	}
}
