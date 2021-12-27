import java.awt.Color;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class LuchadorFuerte extends Guerrero {
	private int proteccionEscarlata = 1;
	private int desarmar = 1;
	protected boolean vivo =true;
	
	public LuchadorFuerte() {
		da�o = 10;
		vida = 40;
		armadura = 50;
	}

	public void personajeVivo() {
		if (vida > 0) {
			vivo = true;
		}	else {
			vivo = false;
		}
	}
	
	public void proteccionEscarlata(Espadachin esp, Mago mag,Turnos turno, JTextArea info,JPanel panel) {
		if (vivo == false) {
			info.setText("\n\n\tEL LUCHADOR FUE DERROTADO");
		}	else {
			if (proteccionEscarlata <= turno.turno) {
				proteccionEscarlata = turno.turno+3;
				info.setText("\n\n\tLA ARMADURA DE TUS ALIADOS SE\n\n\tAUMENT� PERMANENTEMENTE"
						+ "\n\n\tArmadura de Luchador : "+armadura+"+5"
						+ "\n\tArmadura de Espadachin : "+esp.armadura+"+5"
						+ "\n\tArmadura de Mago: "+mag.armadura+"+5");
				armadura = armadura + 5;
				esp.armadura = esp.armadura + 5;
				mag.armadura = mag.armadura + 5;
				
				// -------------------------------------
				JLabel prot1 = new JLabel();
				JLabel prot2 = new JLabel();
				JLabel prot3 = new JLabel();

				panel.add(prot1);
				prot1.setText(" ARMADURA +5 ");
				prot1.setForeground(Color.ORANGE);
				prot1.setBounds(500,530,200,20);
				prot1.setVisible(true);
				prot1.setFont(new Font("Bahnschrift SemiBold SemiConden", 3, 15));
				panel.add(prot2);
				prot2.setText(" ARMADURA +5 ");
				prot2.setForeground(Color.ORANGE);
				prot2.setBounds(670,450,200,20);
				prot2.setVisible(true);
				prot2.setFont(new Font("Bahnschrift SemiBold SemiConden", 3, 15));
				panel.add(prot3);
				prot3.setText(" ARMADURA +5 ");
				prot3.setForeground(Color.ORANGE);
				prot3.setBounds(840,530,200,20);
				prot3.setVisible(true);
				prot3.setFont(new Font("Bahnschrift SemiBold SemiConden", 3, 15));

				Timer timer = new Timer();
				TimerTask tarea = new TimerTask() {
					public void run() {
						prot1.setVisible(false);
						prot2.setVisible(false);
						prot3.setVisible(false);
					}
				};
				timer.schedule(tarea, 2000);
				// -------------------------------------

			}	else {
				info.setText("\n\n\tLA PROTECCION ESCARLATA A�N EST� \n\n\tCARGANDO, FALTA: "+
				(proteccionEscarlata-turno.turno));
			}
		}
	}
	
	public void desarmar(Enemigo enemy, Turnos turno, JTextArea info, JPanel panel) {
		if (vivo == false) {
			info.setText("\n\n\tEL LUCHADOR FUE DERROTADO");
		}	else {
			if (desarmar <= turno.turno) {
				desarmar = turno.turno+7;
				info.setText("\n\n\t�EL ENEMIGO FUE DESARMADO POR \n\t3 TURNOS!!");
				enemy.desarmado();
				
				// -------------------------------------
				JLabel desarmed = new JLabel();
				panel.add(desarmed);
				desarmed.setText(" - DESARMADO -");
				desarmed.setForeground(Color.GREEN);
				desarmed.setBounds(659,150,200,20);
				desarmed.setVisible(true);
				desarmed.setFont(new Font("Bahnschrift SemiBold SemiConden", 3, 18));

				Timer timer = new Timer();
				TimerTask tarea = new TimerTask() {
					public void run() {
						desarmed.setVisible(false);
					}
				};
				timer.schedule(tarea, 1000);
				// -------------------------------------
				
			}	else {
				info.setText("\n\n\tLA HABILIDAD DESARMAR ESTA A�N\n\tRECARGANDOSE!!\n\n\tFALTA: "+
				(desarmar-turno.turno));
			}
		}
	}

	public void ataque(Enemigo enemy,Turnos turnos,JTextArea info, JPanel panel) {
		if (vivo == false) {
			info.setText("\n\n\tEL LUCHADOR FUE DERROTADO");
		}	else {
			enemy.serAtacado(da�o);
			// -------------------------------------
			JLabel puntosDa�o = new JLabel();
			panel.add(puntosDa�o);
			puntosDa�o.setText("- "+da�o);
			puntosDa�o.setForeground(Color.RED);
			puntosDa�o.setBounds(659,150,100,20);
			puntosDa�o.setFont(new Font("Bahnschrift SemiBold SemiConden", 3, 25));
			puntosDa�o.setVisible(true);
			Timer timer = new Timer();
			TimerTask tarea = new TimerTask() {
				public void run() {
					puntosDa�o.setVisible(false);
				}
			};
			timer.schedule(tarea, 1000);
			// -------------------------------------
			info.setText("\n\n\t�EL LUCHADOR LANZ� UN ATAQUE\n\n\tE INFLINGI� "+da�o+" DE DA�O!");
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

