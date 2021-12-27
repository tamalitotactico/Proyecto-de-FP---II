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
		da�o = 20;
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
			info.setText("\n\n\tEL ESPADACH�N FUE DERROTADO");
		}	else {
			if (hojaPlateadaColdown <= turno.turno) {
				enemy.armadura = 0;
				hojaPlateadaColdown = turno.turno+3;
				info.setText("\n\n\tHOJA PLATEADA LANZADA, \n\n\tARMADURA ENEMIGA A 0!");
				
				// -------------------------------------
				JLabel hojaPlateada = new JLabel();
				panel.add(hojaPlateada);
				hojaPlateada.setText("� ARMADURA A 0 !");
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
				info.setText("\n\n\tLA HOJA PLATEADA A�N EST� \n\n\tCARGANDO, FALTA: "+
				(hojaPlateadaColdown-turno.turno));
			}
		}
	}
	
	public void estocadaMortal(Enemigo enemy,Turnos turno, JTextArea info, JPanel panel){
		
		if (vivo == false) {
			info.setText("\n\n\tEL ESPADACH�N FUE DERROTADO");
		}	else {
			if (estocadaMortalColdown <= turno.turno) {
				estocadaMortalColdown = turno.turno+5;
				double da�oInflingido = ((int)(20*((int)(Math.random()*4+1)))*5);
				enemy.serAtacado(da�oInflingido);
				
				// -------------------------------------
				JLabel puntosDa�o = new JLabel();
				panel.add(puntosDa�o);
				puntosDa�o.setText("�� - "+da�oInflingido+" !!");
				puntosDa�o.setForeground(Color.RED);
				puntosDa�o.setBounds(659,150,150,20);
				puntosDa�o.setVisible(true);
				puntosDa�o.setFont(new Font("Copperplate Gothic Bold", 3, 18));

				Timer timer = new Timer();
				TimerTask tarea = new TimerTask() {
					public void run() {
						puntosDa�o.setVisible(false);
					}
				};
				timer.schedule(tarea, 1000);
				// -------------------------------------
				
				info.setText("\n\n\t��LA ESTOCADA MORTAL INFLINGI�\n\n\t"+da�oInflingido+" DE DA�O!!");
				turno.pasarTurno();
			}	else {
				info.setText("\n\n\tLA ESTOCADA MORTAL A�N EST� \n\n\tCARGANDO, FALTA: "+
				(estocadaMortalColdown-turno.turno));
			}
		}
	}
	
	public void ataque(Enemigo enemy,Turnos turnos,JTextArea info, JPanel panel) {
		if (vivo == false) {
			info.setText("\n\n\tEL ESPADACH�N FUE DERROTADO");
		}	else {
			int crit = (int)(20*((int)(Math.random()*4+1)));	// Critico aleatorio x2 o x3 o x4
			enemy.serAtacado(crit);
			
			// -------------------------------------
			JLabel puntosDa�o = new JLabel();
			panel.add(puntosDa�o);
			puntosDa�o.setText("- "+crit);
			puntosDa�o.setForeground(Color.RED);
			puntosDa�o.setFont(new Font("Copperplate Gothic Bold", 3, 18));
			puntosDa�o.setBounds(659,150,60,20);
			puntosDa�o.setVisible(true);
			Timer timer = new Timer();
			TimerTask tarea = new TimerTask() {
				public void run() {
					puntosDa�o.setVisible(false);
				}
			};
			timer.schedule(tarea, 1000);
			// -------------------------------------
			
			info.setText("\n\n\t�EL ESPADACH�N LANZ� UN ATAQUE\n\n\tE INFLINGI� "+crit+" DE DA�O!");
			turnos.pasarTurno();
		}
	}
	
	public void recibirAtaque(double da�o) {
		da�o = funcionalidadArmadura(da�o,armadura);
		if (vida-da�o<0) {
			vida = 0;
		}	else {
			vida = vida-da�o;
		}
	}
	
	public double funcionalidadArmadura(double da�o, double armadura) {
		double block = armadura/100;
		da�o = da�o-block;
		return da�o;
	}
}
