import java.awt.Color;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Espadachin extends Guerrero {
	
	private int hojaPlateadaColdown = 1;
	private int estocadaMortalColdown = 1;
	protected boolean vivo =true;
	public Espadachin() {
		daño = 20;
		vida = 30;
		armadura = 20;
	}
	
	public void personajeVivo() {
		if (vida > 0) {
			vivo = true;
		}	else {
			vivo = false;
		}
	}
	
	public void hojaPlateada(Enemigo enemy,Turnos turno,JTextArea info, JPanel panel){

		if (vivo == false) {
			info.setText("\n\n\tEL ESPADACHÍN FUE DERROTADO");
		}	else {
			if (hojaPlateadaColdown <= turno.turno) {
				enemy.armadura = 0;
				hojaPlateadaColdown = turno.turno+3;
				info.setText("\n\n\tHOJA PLATEADA LANZADA, \n\n\tARMADURA ENEMIGA A 0!");
				
				// -------------------------------------
				JLabel hojaPlateada = new JLabel();
				panel.add(hojaPlateada);
				hojaPlateada.setText("¡ ARMADURA A 0 !");
				hojaPlateada.setForeground(new Color(162, 0, 255));
				hojaPlateada.setBounds(659,150,200,20);
				hojaPlateada.setVisible(true);
				hojaPlateada.setFont(new Font("Copperplate Gothic Bold", 2, 18));

				Timer timer = new Timer();
				TimerTask tarea = new TimerTask() {
					public void run() {
						hojaPlateada.setVisible(false);
					}
				};
				timer.schedule(tarea, 1000);
				// -------------------------------------
				
			}	else {
				info.setText("\n\n\tLA HOJA PLATEADA AÚN ESTÁ \n\n\tCARGANDO, FALTA: "+
				(hojaPlateadaColdown-turno.turno));
			}
		}
	}
	
	public void estocadaMortal(Enemigo enemy,Turnos turno, JTextArea info, JPanel panel){
		
		if (vivo == false) {
			info.setText("\n\n\tEL ESPADACHÍN FUE DERROTADO");
		}	else {
			if (estocadaMortalColdown <= turno.turno) {
				estocadaMortalColdown = turno.turno+5;
				double dañoInflingido = ((int)(20*((int)(Math.random()*4+1)))*5);
				enemy.serAtacado(dañoInflingido);
				
				// -------------------------------------
				JLabel puntosDaño = new JLabel();
				panel.add(puntosDaño);
				puntosDaño.setText("¡¡ - "+dañoInflingido+" !!");
				puntosDaño.setForeground(Color.RED);
				puntosDaño.setBounds(659,150,150,20);
				puntosDaño.setVisible(true);
				puntosDaño.setFont(new Font("Copperplate Gothic Bold", 3, 18));

				Timer timer = new Timer();
				TimerTask tarea = new TimerTask() {
					public void run() {
						puntosDaño.setVisible(false);
					}
				};
				timer.schedule(tarea, 1000);
				// -------------------------------------
				
				info.setText("\n\n\t¡¡LA ESTOCADA MORTAL INFLINGIÓ\n\n\t"+dañoInflingido+" DE DAÑO!!");
				turno.pasarTurno();
			}	else {
				info.setText("\n\n\tLA ESTOCADA MORTAL AÚN ESTÁ \n\n\tCARGANDO, FALTA: "+
				(estocadaMortalColdown-turno.turno));
			}
		}
	}
	
	public void ataque(Enemigo enemy,Turnos turnos,JTextArea info, JPanel panel) {
		if (vivo == false) {
			info.setText("\n\n\tEL ESPADACHÍN FUE DERROTADO");
		}	else {
			int crit = (int)(20*((int)(Math.random()*4+1)));	// Critico aleatorio x2 o x3 o x4
			enemy.serAtacado(crit);
			
			// -------------------------------------
			JLabel puntosDaño = new JLabel();
			panel.add(puntosDaño);
			puntosDaño.setText("- "+crit);
			puntosDaño.setForeground(Color.RED);
			puntosDaño.setFont(new Font("Copperplate Gothic Bold", 3, 18));
			puntosDaño.setBounds(659,150,60,20);
			puntosDaño.setVisible(true);
			Timer timer = new Timer();
			TimerTask tarea = new TimerTask() {
				public void run() {
					puntosDaño.setVisible(false);
				}
			};
			timer.schedule(tarea, 1000);
			// -------------------------------------
			
			info.setText("\n\n\t¡EL ESPADACHÍN LANZÓ UN ATAQUE\n\n\tE INFLINGIÓ "+crit+" DE DAÑO!");
			turnos.pasarTurno();
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
