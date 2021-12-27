import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

public class Mago extends Guerrero{
	
	private int curarAliadosColdown = 0;
	private int explosionArcanaColdown = 0;
	protected boolean vivo =true;
	
	public Mago() {
		vida = 15; //15
		armadura = 10;
		daño = 5;
	}
	
	public void personajeVivo() {
		if (vida > 0) {
			vivo = true;
		}	else {
			vivo = false;
		}
	}
	
	public void explosionArcana(Enemigo enemy,Turnos turno, JTextArea info, JPanel panel) {
		if (vivo == false) {
			info.setText("\n\n\tEL MAGO FUE DERROTADO");
		}	else { 
			if (explosionArcanaColdown <= turno.turno) {
				explosionArcanaColdown = explosionArcanaColdown+5;
				info.setText("\n\n\tEL MAGO LANZÓ EXPLOSIÓN ARCANA"+"\n\n\t¡EL HECHIZO FUE TAN DEVASTADOR"
						+ "\n\tQUE INFLINGIÓ 100 DE DAÑO AL ENEMIGO!");
				enemy.vida = enemy.vida-100;
				
				// -------------------------------------
				JLabel explosion = new JLabel();
				panel.add(explosion);
				explosion.setText("¡¡ DAÑO PURO !!");
				explosion.setForeground(Color.YELLOW);
				explosion.setBounds(659,150,250,20);
				explosion.setVisible(true);
				explosion.setFont(new Font("Bahnschrift SemiBold SemiConden", 3, 18));

				Timer timer = new Timer();
				TimerTask tarea = new TimerTask() {
					public void run() {
						explosion.setText(" ¡¡ - 100 !!");
						explosion.setForeground(Color.RED);
					}
				};
				TimerTask tarea2 = new TimerTask() {
					public void run() {
						explosion.setVisible(false);
					}
				};
				timer.schedule(tarea, 1000);
				timer.schedule(tarea2, 2000);
				// -------------------------------------
				
			}	else {
				info.setText("\n\n\tLA EXPLOSIÓN ARCANA AÚN ESTÁ\n\tCARGANDO, FALTA: "+
				(explosionArcanaColdown-turno.turno));
			}
		}
	}
	
	public void curarAliados(Turnos turno, JTextArea info,Espadachin esp, LuchadorFuerte luch, JPanel panel) {
		if (vivo == false) {
			info.setText("\n\n\tEL MAGO FUE DERROTADO");
		}	else {
			if (curarAliadosColdown <= turno.turno) {
				curarAliadosColdown = curarAliadosColdown+3;
				info.setText("\n\n\t¡TUS ALIADOS FUERON CURADOS!"+"\n\n\tEspadachin: "+esp.vida+"+10"+
						"\n\tLuchador: "+luch.vida+"+10\n\tMago: "+vida+"+10");
				esp.vida = esp.vida+10;
				luch.vida = luch.vida+10;
				vida = vida+10;
				
				// -------------------------------------
				JLabel cura1 = new JLabel();
				JLabel cura2 = new JLabel();
				JLabel cura3 = new JLabel();

				panel.add(cura1);
				cura1.setText("VIDA +10");
				cura1.setForeground(Color.GREEN);
				cura1.setBounds(530,530,200,20);
				cura1.setVisible(true);
				cura1.setFont(new Font("Bahnschrift SemiBold SemiConden", 1, 18));
				panel.add(cura2);
				cura2.setText("VIDA +10");
				cura2.setForeground(Color.GREEN);
				cura2.setBounds(700,450,200,20);
				cura2.setVisible(true);
				cura2.setFont(new Font("Bahnschrift SemiBold SemiConden", 1, 18));
				panel.add(cura3);
				cura3.setText("VIDA +10");
				cura3.setForeground(Color.GREEN);
				cura3.setBounds(870,530,200,20);
				cura3.setVisible(true);
				cura3.setFont(new Font("Bahnschrift SemiBold SemiConden", 1, 18));

				Timer timer = new Timer();
				TimerTask tarea = new TimerTask() {
					public void run() {
						cura1.setVisible(false);
						cura2.setVisible(false);
						cura3.setVisible(false);
					}
				};
				timer.schedule(tarea, 2000);
				// -------------------------------------
				
			}	else {
				info.setText("\n\n\tLA CURACIÓN DE ALIADOS AÚN ESTÁ \n\n\tCARGANDO, FALTA: "+
				(curarAliadosColdown-turno.turno));
			}
		}
	}

	public void ataque(Enemigo enemy,Turnos turno, JTextArea info, JPanel panel) {
		if (vivo == false) {
			info.setText("\n\n\tEL MAGO FUE DERROTADO");
		}	else {
			enemy.serAtacado(daño);

			// -------------------------------------
			JLabel puntosDaño = new JLabel();
			panel.add(puntosDaño);
			puntosDaño.setText("- "+daño);
			puntosDaño.setForeground(Color.RED);
			puntosDaño.setBounds(659,150,100,20);
			puntosDaño.setVisible(true);
			Timer timer = new Timer();
			TimerTask tarea = new TimerTask() {
				public void run() {
					puntosDaño.setVisible(false);
				}
			};
			timer.schedule(tarea, 1000);
			// -------------------------------------
			
			info.setText("\n\n\t¡¡EL MAGO LANZÓ UN ATAQUE!!\n\n\tFUE TAN DÉBIL QUE EL ENEMIGO\n\tAPENAS LO SINTIÓ");
			turno.pasarTurno();
		}
	}
	
	public void recibirAtaque(double daño) {
		daño = funcionalidadArmadura(daño,armadura);
		if (vida-daño<0) {
			vida = 0;
		}	else {
			vida = vida-daño;
		}
	}
	
	public double funcionalidadArmadura(double daño, double armadura) {
		double block = armadura/100;
		daño = daño-block;
		return daño;
	}
}
