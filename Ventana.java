import java.awt.Image;
import java.awt.Graphics;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class Ventana extends JFrame {
	private JPanel panel;
	private static JPanel panelInGame;
	private JLabel saludo;
	private JTextField cajaTexto;
	static Turnos playing = new Turnos();
	
	class FondoPanel extends JPanel{
		private Image imagen;
			
		public void paint(Graphics g) {
			imagen = new ImageIcon(getClass().getResource("introFondo.jpg")).getImage();
			g.drawImage(imagen,0,0,getWidth(),getHeight(),this);
			setOpaque(false);
			super.paint(g);
		}
	}
	
	class FondoBatalla extends JPanel{
		private Image imagen;
			
		public void paint(Graphics g) {
			imagen = new ImageIcon(getClass().getResource("spaceImage.jpg")).getImage();
			g.drawImage(imagen,0,0,getWidth(),getHeight(),this);
			setOpaque(false);
			super.paint(g);
		}
	}
	
	public Ventana() {
		setBounds(50,50,50,50);
		setSize(995, 720);
		setTitle("LA BATALLA DECISIVA");
		iniciarComponentes();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}
	
	public void iniciarComponentes(){
		colocarPanel();
		colocarEtiqueta();
		colocarCajaText();
		colocarBoton();
	}
	
	private void colocarPanel() {
		panel = new FondoPanel();
		panel.setLayout(null);
		this.add(panel);
	}
	
	private void colocarEtiqueta() {
		JLabel etiqueta = new JLabel("Ingrese su nombre");
		etiqueta.setBounds(30,10,200,30);
		etiqueta.setFont(new Font("cooper black",0,15));
		panel.add(etiqueta);
	}
	
	private void colocarCajaText() {
		cajaTexto = new JTextField();
		cajaTexto.setBounds(30,50,300,30);
		panel.add(cajaTexto);
	}
	
	private void colocarBoton() {
		
		JButton boton = new JButton("Pulsa aquí");
		JButton boton2 = new JButton("Jugar");

		boton.setBounds(150, 100, 150, 30);
		boton.setFont(new Font("arial", 0, 15));
		panel.add(boton);
		
		saludo = new JLabel();
		saludo.setBounds(20, 200, 300, 40);
		saludo.setFont(new Font("arial",0, 15));
		panel.add(saludo);
		
		ActionListener oyenteDeAccion = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				boton.setVisible(false);
				saludo.setText("Jugador "+ cajaTexto.getText()+" aceptado.");
				cajaTexto.setVisible(false);
					
				boton2.setBounds(150, 100, 150, 30);
				boton2.setFont(new Font("arial", 0, 15));
				boton2.setVisible(true);
				panel.add(boton2);
			}			

		};
		
		ActionListener oyenteDeAccion2 = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				panel.setVisible(false);
				iniciarJuego();
			}
		};
		boton.addActionListener(oyenteDeAccion);
		boton2.addActionListener(oyenteDeAccion2);
	}
	
	public void iniciarJuego() {
			
		// --------------------
		panelInGame = new FondoBatalla();
		panelInGame.setLayout(null);
		panelInGame.setVisible(true);
		this.remove(panel);
		this.add(panelInGame);
		// --------------------
		
		JTextArea info = new JTextArea("Hola");
		JLabel titleInfo = new JLabel("Title",SwingConstants.CENTER);

		inicioJuegoSettings(info,titleInfo);
		interfazEspadachin(info,titleInfo);
	}
	
	public static void interfazMago(JTextArea info, JLabel titleInfo) {
		
		System.out.println("ERES MAGO");
		JButton btn1Mago = new JButton("ATACAR - Mago");
		btn1Mago.setBounds(20,600, 300, 60);
		btn1Mago.setVisible(true);
		
		btn1Mago.setBackground(new Color(12, 12, 12));
		btn1Mago.setBorder(BorderFactory.createLineBorder(Color.green));
		btn1Mago.setForeground(Color.GREEN);
		panelInGame.add(btn1Mago);
		
		JButton btn2Mago = new JButton("ESPECIAL - Mago");
		btn2Mago.setBounds(340,600, 300, 60);
		btn2Mago.setVisible(true);
		
		btn2Mago.setBackground(new Color(12, 12, 12));
		btn2Mago.setBorder(BorderFactory.createLineBorder(Color.green));
		btn2Mago.setForeground(Color.GREEN);
		panelInGame.add(btn2Mago);
		
		JButton btn3Mago = new JButton("CAMBIAR - ERES MAGO");
		btn3Mago.setBounds(660,600, 300, 60);
		btn3Mago.setVisible(true);
		
		btn3Mago.setBackground(new Color(12, 12, 12));
		btn3Mago.setBorder(BorderFactory.createLineBorder(Color.green));
		btn3Mago.setForeground(Color.GREEN);
		panelInGame.add(btn3Mago);

		
		MouseListener botonListenerAttack = new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				info.setText("\n\tATACASTE!");
				playing.mago.ataque(playing.enemy,playing,info,panelInGame);
				playing.enemy.ataqueEnemigo(info, titleInfo, playing.enemy, playing.mago);
				if (playing.ganar()) {
					JButton victoria = new JButton("¡ GANASTE !");
					info.setVisible(false);
					titleInfo.setVisible(false);
					btn1Mago.setVisible(false);
					btn2Mago.setVisible(false);
					btn3Mago.setVisible(false);
					victoria.setBounds(340,300, 320, 60);
					victoria.setForeground(Color.GREEN);
					victoria.setBackground(new Color(12, 12, 12));
					victoria.setBorder(BorderFactory.createLineBorder(Color.green));
					panelInGame.add(victoria);
				}	else if (playing.perder()){
					JButton victoria = new JButton("¡ PERDISTE !");
					info.setVisible(false);
					titleInfo.setVisible(false);
					btn1Mago.setVisible(false);
					btn2Mago.setVisible(false);
					btn3Mago.setVisible(false);
					victoria.setBounds(340,300, 320, 60);
					victoria.setForeground(Color.red);
					victoria.setBackground(new Color(12, 12, 12));
					victoria.setBorder(BorderFactory.createLineBorder(Color.red));
					panelInGame.add(victoria);
				}
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
				titleInfo.setText("ATACAR?");
				info.setText("\n\n\tTu daño = "+playing.mago.daño+
						"\n\n\tMago: Tu personaje es debíl atacando"
						+ "\n\n\tTIP: Aprovecha tus especiales");
			}
			public void mouseExited(MouseEvent e) {
				titleInfo.setText("Turno: "+playing.turno);
				info.setText("\n\n\tTu vida = "+playing.mago.vida+
						"\n\n\tFase del enemigo ="+playing.enemy.fase+
						"\n\n\tVida Enemiga ="+playing.enemy.vida+
						"\n\n\tArmadura Enemiga ="+playing.enemy.armadura);
			}
		};
		MouseListener botonListenerEspecial = new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {
				
				btn2Mago.setVisible(false);
				JButton poder1 = new JButton("CURAR ALIADOS");
				JButton poder2 = new JButton("EXPLOSIÓN ARCANA");
				JButton cerrarPoderes = new JButton("Especial - Mago");
				poder1.setBounds(340,530, 300, 60);
				poder1.setBackground(new Color(12, 12, 12));
				poder1.setBorder(BorderFactory.createLineBorder(Color.green));
				poder1.setForeground(Color.GREEN);
				
				panelInGame.add(poder1);
				poder2.setBounds(340,460, 300, 60);
				poder2.setBackground(new Color(12, 12, 12));
				poder2.setBorder(BorderFactory.createLineBorder(Color.green));
				poder2.setForeground(Color.GREEN);
				
				panelInGame.add(poder2);
				cerrarPoderes.setBounds(340,600, 300, 60);
				cerrarPoderes.setBackground(new Color(12, 12, 12));
				cerrarPoderes.setBorder(BorderFactory.createLineBorder(Color.green));
				cerrarPoderes.setForeground(Color.GREEN);
				panelInGame.add(cerrarPoderes);

				info.setText("\n\tESPECIALES DE MAGO!");
				poder1.setVisible(true);
				poder2.setVisible(true);
				cerrarPoderes.setVisible(true);

				MouseListener poder1Listener = new MouseListener() {

					public void mouseClicked(MouseEvent e) {
						playing.mago.curarAliados(playing, info, playing.espadachin, playing.luchador, panelInGame);

					}
					public void mousePressed(MouseEvent e) {
					}
					public void mouseReleased(MouseEvent e) {
					}
					public void mouseEntered(MouseEvent e) {
						titleInfo.setText("CURAR ALIADOS");
						info.setText("\n\n\tTUS ALIADOS SON CURADOS POR\n\n\t"
								+ "10 PUNTOS!\n\n\tTiempo de recarga = 3 turnos\n\n\tVida espadachín = "+playing.espadachin.vida
								+"\n\n\tVida luchador = "+playing.luchador.vida
								+"\n\n\tVida mago = "+playing.mago.vida);
					}
					public void mouseExited(MouseEvent e) {
						titleInfo.setText("Turno: "+playing.turno);
						info.setText("\n\n\tTu vida = "+playing.mago.vida+
								"\n\n\tFase del enemigo ="+playing.enemy.fase+
								"\n\n\tVida Enemiga ="+playing.enemy.vida+
								"\n\n\tArmadura Enemiga ="+playing.enemy.armadura);
					}
				};
				MouseListener poder2Listener = new MouseListener() {

					public void mouseClicked(MouseEvent e) {
						info.setText("\n\tLANZASTE EXPLOSIÓN ARCANA!");
						playing.mago.explosionArcana(playing.enemy, playing, info,panelInGame);
					}
					public void mousePressed(MouseEvent e) {
					}
					public void mouseReleased(MouseEvent e) {
					}
					public void mouseEntered(MouseEvent e) {
						titleInfo.setText("EXPLOSIÓN ARCANA");
						info.setText("\n\n\tDaño de la explosión = 100"+
								"\n\n\tTiempo de recarga: 5 turnos");
					}
					public void mouseExited(MouseEvent e) {
						titleInfo.setText("Turno: "+playing.turno);
						info.setText("\n\n\tTu vida = "+playing.mago.vida+
								"\n\n\tFase del enemigo ="+playing.enemy.fase+
								"\n\n\tVida Enemiga ="+playing.enemy.vida+
								"\n\n\tArmadura Enemiga ="+playing.enemy.armadura);
					}
				};
				MouseListener cerrarListener = new MouseListener() {

					public void mouseClicked(MouseEvent e) {
						info.setText("\n\tESPECIALES DE MAGO");
						poder1.setVisible(false);
						poder2.setVisible(false);
						cerrarPoderes.setVisible(false);
						btn2Mago.setVisible(true);
					}
					public void mousePressed(MouseEvent e) {
					}
					public void mouseReleased(MouseEvent e) {
					}
					public void mouseEntered(MouseEvent e) {

					}
					public void mouseExited(MouseEvent e) {
					}
				};
				poder1.addMouseListener(poder1Listener);
				poder2.addMouseListener(poder2Listener);
				cerrarPoderes.addMouseListener(cerrarListener);

			}

			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
				titleInfo.setText("ESPECIAL DE MAGO");
				info.setText("\n\n\tPoderes:\n\n\tCURAR ALIADOS\n\n\tEXPLOSIÓN ARCANA");
			}
			public void mouseExited(MouseEvent e) {
				titleInfo.setText("Turno: "+playing.turno);
				info.setText("\n\n\tTu vida = "+playing.mago.vida+
						"\n\n\tFase del enemigo ="+playing.enemy.fase+
						"\n\n\tVida Enemiga ="+playing.enemy.vida+
						"\n\n\tArmadura Enemiga ="+playing.enemy.armadura);
			}
		};		
		MouseListener botonListenerCambiar = new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				
				JButton cambiarEspadachin = new JButton("CAMBIAR A ESPADACHIN");
				cambiarEspadachin.setBounds(660,530, 300, 60);
				cambiarEspadachin.setBackground(new Color(12, 12, 12));
				cambiarEspadachin.setBorder(BorderFactory.createLineBorder(Color.green));
				cambiarEspadachin.setForeground(Color.GREEN);
				
				panelInGame.add(cambiarEspadachin);
				JButton cambiarLuchador = new JButton("CAMBIAR A LUCHADOR");
				cambiarLuchador.setBounds(660,460, 300, 60);
				cambiarLuchador.setBackground(new Color(12, 12, 12));
				cambiarLuchador.setBorder(BorderFactory.createLineBorder(Color.green));
				cambiarLuchador.setForeground(Color.GREEN);
				
				panelInGame.add(cambiarLuchador);
				btn3Mago.setVisible(false);
				JButton cambiar = new JButton("CAMBIAR - ERES MAGO");
				cambiar.setBounds(660,600, 300, 60);
				cambiar.setBackground(new Color(12, 12, 12));
				cambiar.setBorder(BorderFactory.createLineBorder(Color.green));
				cambiar.setForeground(Color.GREEN);
				panelInGame.add(cambiar);

				MouseListener botonListenerCambiarLuchador = new MouseListener() {

					public void mouseClicked(MouseEvent e) {
						btn1Mago.setVisible(false);
						btn2Mago.setVisible(false);
						btn3Mago.setVisible(false);
						cambiarEspadachin.setVisible(false);
						cambiarLuchador.setVisible(false);
						cambiar.setVisible(false);
						interfazLuchador(info,titleInfo);
					}
					public void mousePressed(MouseEvent e) {
					}
					public void mouseReleased(MouseEvent e) {
					}
					public void mouseEntered(MouseEvent e) {
						titleInfo.setText("CAMBIAR");
						info.setText("\n\n\tCAMBIA TU PERSONAJE A LUCHADOR");
					}
					public void mouseExited(MouseEvent e) {
					}
				};
				MouseListener botonListenerCambiarEspadachin = new MouseListener() {

					public void mouseClicked(MouseEvent e) {
						btn1Mago.setVisible(false);
						btn2Mago.setVisible(false);
						btn3Mago.setVisible(false);
						cambiarEspadachin.setVisible(false);
						cambiarLuchador.setVisible(false);
						cambiar.setVisible(false);
						interfazEspadachin(info,titleInfo);
					}
					public void mousePressed(MouseEvent e) {
					}
					public void mouseReleased(MouseEvent e) {
					}
					public void mouseEntered(MouseEvent e) {
						titleInfo.setText("CAMBIAR");
						info.setText("\n\n\tCAMBIA TU PERSONAJE A ESPADACHIN");
					}
					public void mouseExited(MouseEvent e) {
					}
				};
				MouseListener botonListenerCambiar = new MouseListener() {

					public void mouseClicked(MouseEvent e) {

						cambiarEspadachin.setVisible(false);
						cambiarLuchador.setVisible(false);
						cambiar.setVisible(false);
						btn3Mago.setVisible(true);
					}
					public void mousePressed(MouseEvent e) {
					}
					public void mouseReleased(MouseEvent e) {
					}
					public void mouseEntered(MouseEvent e) {
					}
					public void mouseExited(MouseEvent e) {
					}
				};
				cambiarEspadachin.addMouseListener(botonListenerCambiarEspadachin);
				cambiarLuchador.addMouseListener(botonListenerCambiarLuchador);
				cambiar.addMouseListener(botonListenerCambiar);

				}

			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
				titleInfo.setText("CAMBIAR");
				info.setText("\n\n\tCAMBIA TU PERSONAJE A OTRO"
						+ "\n\n\t-   ESPADACHIN"
						+ "\n\n\t-   LUCHADOR");
			}

			public void mouseExited(MouseEvent e) {
				titleInfo.setText("Turno: "+playing.turno);
				info.setText("\n\n\tTu vida = "+playing.mago.vida+
						"\n\n\tFase del enemigo ="+playing.enemy.fase+
						"\n\n\tVida Enemiga ="+playing.enemy.vida+
						"\n\n\tArmadura Enemiga ="+playing.enemy.armadura);
			}
		};
		btn1Mago.addMouseListener(botonListenerAttack);
		btn2Mago.addMouseListener(botonListenerEspecial);
		btn3Mago.addMouseListener(botonListenerCambiar);
	}
	
	public static void interfazLuchador(JTextArea info, JLabel titleInfo) {
		
		System.out.println("ERES LUCHADOR");
		JButton btn1Luchador = new JButton("ATACAR - Luchador");
		btn1Luchador.setBounds(20,600, 300, 60);
		btn1Luchador.setVisible(true);
		btn1Luchador.setBackground(new Color(12, 12, 12));
		btn1Luchador.setBorder(BorderFactory.createLineBorder(Color.green));
		btn1Luchador.setForeground(Color.GREEN);
		panelInGame.add(btn1Luchador);
		
		JButton btn2Luchador = new JButton("ESPECIAL - Luchador");
		btn2Luchador.setBounds(340,600, 300, 60);
		btn2Luchador.setVisible(true);
		btn2Luchador.setBackground(new Color(12, 12, 12));
		btn2Luchador.setBorder(BorderFactory.createLineBorder(Color.green));
		btn2Luchador.setForeground(Color.GREEN);
		panelInGame.add(btn2Luchador);
		
		JButton btn3Luchador = new JButton("CAMBIAR - ERES LUCHADOR");
		btn3Luchador.setBounds(660,600, 300, 60);
		btn3Luchador.setVisible(true);
		btn3Luchador.setBackground(new Color(12, 12, 12));
		btn3Luchador.setBorder(BorderFactory.createLineBorder(Color.green));
		btn3Luchador.setForeground(Color.GREEN);
		panelInGame.add(btn3Luchador);
		
		MouseListener botonListenerAttack = new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				info.setText("\n\tATACASTE!");
				playing.luchador.ataque(playing.enemy, playing, info,panelInGame);
				playing.enemy.ataqueEnemigo(info, titleInfo, playing.enemy, playing.luchador);
				if (playing.ganar()) {
					JButton victoria = new JButton("¡ GANASTE !");
					info.setVisible(false);
					titleInfo.setVisible(false);
					btn1Luchador.setVisible(false);
					btn2Luchador.setVisible(false);
					btn3Luchador.setVisible(false);
					victoria.setBounds(340,300, 320, 60);
					victoria.setForeground(Color.GREEN);
					victoria.setBackground(new Color(12, 12, 12));
					victoria.setBorder(BorderFactory.createLineBorder(Color.green));
					panelInGame.add(victoria);
				}	else if (playing.perder()){
					JButton victoria = new JButton("¡ PERDISTE !");
					info.setVisible(false);
					titleInfo.setVisible(false);
					btn1Luchador.setVisible(false);
					btn2Luchador.setVisible(false);
					btn3Luchador.setVisible(false);
					victoria.setBounds(340,300, 320, 60);
					victoria.setForeground(Color.red);
					victoria.setBackground(new Color(12, 12, 12));
					victoria.setBorder(BorderFactory.createLineBorder(Color.red));
					panelInGame.add(victoria);
				}
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
				titleInfo.setText("ATACAR?");
				info.setText("\n\n\tTu daño = "+playing.luchador.daño+
						"\n\n\tLuchador: Tu personaje es fuerte atacando"
						+ "\n\n\tTIP: Aprovecha tus nivel de vida y defenza");
			}
			public void mouseExited(MouseEvent e) {
				titleInfo.setText("Turno: "+playing.turno);
				info.setText("\n\n\tTu vida = "+playing.luchador.vida+
						"\n\n\tFase del enemigo ="+playing.enemy.fase+
						"\n\n\tVida Enemiga ="+playing.enemy.vida+
						"\n\n\tArmadura Enemiga ="+playing.enemy.armadura);
			}
		};
		MouseListener botonListenerEspecial = new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {
				
				btn2Luchador.setVisible(false);
				JButton poder1 = new JButton("PROTECCIÓN ESCARLATA");
				JButton poder2 = new JButton("DESARMAR");
				JButton cerrarPoderes = new JButton("ESPECIAL - Luchador");
				poder1.setBounds(340,530, 300, 60);
				poder1.setBackground(new Color(12, 12, 12));
				poder1.setBorder(BorderFactory.createLineBorder(Color.green));
				poder1.setForeground(Color.GREEN);
				
				panelInGame.add(poder1);
				poder2.setBounds(340,460, 300, 60);
				poder2.setBackground(new Color(12, 12, 12));
				poder2.setBorder(BorderFactory.createLineBorder(Color.green));
				poder2.setForeground(Color.GREEN);
				
				panelInGame.add(poder2);
				cerrarPoderes.setBounds(340,600, 300, 60);
				cerrarPoderes.setBackground(new Color(12, 12, 12));
				cerrarPoderes.setBorder(BorderFactory.createLineBorder(Color.green));
				cerrarPoderes.setForeground(Color.GREEN);
				panelInGame.add(cerrarPoderes);

				info.setText("\n\tESPECIALES DE LUCHADOR!");
				poder1.setVisible(true);
				poder2.setVisible(true);
				cerrarPoderes.setVisible(true);

				MouseListener poder1Listener = new MouseListener() {

					public void mouseClicked(MouseEvent e) {
						playing.luchador.proteccionEscarlata(playing.espadachin, playing.mago, playing, info, panelInGame);
					}
					public void mousePressed(MouseEvent e) {
					}
					public void mouseReleased(MouseEvent e) {
					}
					public void mouseEntered(MouseEvent e) {
						titleInfo.setText("PROTECCIÓN ESCARLATA");
						info.setText("\n\n\tTUS ALIADOS RECIBEN\n\t"
								+ "5 PUNTOS DE ARMADURA!\n\n\tTiempo de recarga = 3 turno\n\n\tArmadura espadachín = "
								+playing.espadachin.armadura+"\n\n\tArmadura luchador = "
								+playing.luchador.armadura+"\n\n\tArmadura mago = "
								+playing.mago.armadura);
					}
					public void mouseExited(MouseEvent e) {
						titleInfo.setText("Turno: "+playing.turno);
						info.setText("\n\n\tTu vida = "+playing.luchador.vida+
								"\n\n\tFase del enemigo ="+playing.enemy.fase+
								"\n\n\tVida Enemiga ="+playing.enemy.vida+
								"\n\n\tArmadura Enemiga ="+playing.enemy.armadura);
					}
				};
				MouseListener poder2Listener = new MouseListener() {

					public void mouseClicked(MouseEvent e) {
						playing.luchador.desarmar(playing.enemy, playing, info, panelInGame);
					}
					public void mousePressed(MouseEvent e) {
					}
					public void mouseReleased(MouseEvent e) {
					}
					public void mouseEntered(MouseEvent e) {
						titleInfo.setText("DESARMAR");
						info.setText("\n\n\tReduce el daño enemigo a 0 por 3 turnos"+
								"\n\n\tTiempo de recarga: 7 turnos"
								+ "\n\n\tTIP: Úsalo especialmente cuando el\n\n\tenemigo este en fase de ataque");
					}
					public void mouseExited(MouseEvent e) {
						titleInfo.setText("Turno: "+playing.turno);
						info.setText("\n\n\tTu vida = "+playing.luchador.vida+
								"\n\n\tFase del enemigo ="+playing.enemy.fase+
								"\n\n\tVida Enemiga ="+playing.enemy.vida+
								"\n\n\tArmadura Enemiga ="+playing.enemy.armadura);
					}
				};
				MouseListener cerrarListener = new MouseListener() {

					public void mouseClicked(MouseEvent e) {
						info.setText("\n\tESPECIALES DE LUCHADOR");
						poder1.setVisible(false);
						poder2.setVisible(false);
						cerrarPoderes.setVisible(false);
						btn2Luchador.setVisible(true);
					}
					public void mousePressed(MouseEvent e) {
					}
					public void mouseReleased(MouseEvent e) {
					}
					public void mouseEntered(MouseEvent e) {
					}
					public void mouseExited(MouseEvent e) {
					}
				};
				poder1.addMouseListener(poder1Listener);
				poder2.addMouseListener(poder2Listener);
				cerrarPoderes.addMouseListener(cerrarListener);

			}

			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
				titleInfo.setText("ESPECIAL");
				info.setText("\n\n\tPROTECCIÓN ESCARLATA\n\n\tDESARMAR");
			}
			public void mouseExited(MouseEvent e) {
				titleInfo.setText("Turno: "+playing.turno);
				info.setText("\n\n\tTu vida = "+playing.luchador.vida+
						"\n\n\tFase del enemigo ="+playing.enemy.fase+
						"\n\n\tVida Enemiga ="+playing.enemy.vida+
						"\n\n\tArmadura Enemiga ="+playing.enemy.armadura);
			}
		};		
		MouseListener botonListenerCambiar = new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				
				JButton cambiarEspadachin = new JButton("CAMBIAR A ESPADACHIN");
				cambiarEspadachin.setBounds(660,530, 300, 60);
				cambiarEspadachin.setBackground(new Color(12, 12, 12));
				cambiarEspadachin.setBorder(BorderFactory.createLineBorder(Color.green));
				cambiarEspadachin.setForeground(Color.GREEN);
				
				panelInGame.add(cambiarEspadachin);
				JButton cambiarMago = new JButton("CAMBIAR A MAGO");
				cambiarMago.setBounds(660,460, 300, 60);
				cambiarMago.setBackground(new Color(12, 12, 12));
				cambiarMago.setBorder(BorderFactory.createLineBorder(Color.green));
				cambiarMago.setForeground(Color.GREEN);
				
				panelInGame.add(cambiarMago);
				btn3Luchador.setVisible(false);
				JButton cambiar = new JButton("CAMBIAR - ERES LUCHADOR");
				cambiar.setBounds(660,600, 300, 60);
				cambiar.setBackground(new Color(12, 12, 12));
				cambiar.setBorder(BorderFactory.createLineBorder(Color.green));
				cambiar.setForeground(Color.GREEN);
				panelInGame.add(cambiar);
				
				MouseListener botonListenerCambiarMago = new MouseListener() {

					public void mouseClicked(MouseEvent e) {
						btn1Luchador.setVisible(false);
						btn2Luchador.setVisible(false);
						btn3Luchador.setVisible(false);
						cambiarEspadachin.setVisible(false);
						cambiarMago.setVisible(false);
						cambiar.setVisible(false);
						interfazMago(info,titleInfo);
					}
					public void mousePressed(MouseEvent e) {
					}
					public void mouseReleased(MouseEvent e) {
					}
					public void mouseEntered(MouseEvent e) {
						titleInfo.setText("CAMBIAR");
						info.setText("\n\n\tCAMBIA TU PERSONAJE A MAGO");
					}
					public void mouseExited(MouseEvent e) {
					}
				};
				MouseListener botonListenerCambiarEspadachin = new MouseListener() {

					public void mouseClicked(MouseEvent e) {
						btn1Luchador.setVisible(false);
						btn2Luchador.setVisible(false);
						btn3Luchador.setVisible(false);
						cambiarEspadachin.setVisible(false);
						cambiarMago.setVisible(false);
						cambiar.setVisible(false);
						interfazEspadachin(info,titleInfo);
					}
					public void mousePressed(MouseEvent e) {
					}
					public void mouseReleased(MouseEvent e) {
					}
					public void mouseEntered(MouseEvent e) {
						titleInfo.setText("CAMBIAR");
						info.setText("\n\n\tCAMBIA TU PERSONAJE A ESPADACHIN");
					}
					public void mouseExited(MouseEvent e) {
					}
				};
				MouseListener botonListenerCambiar = new MouseListener() {

					public void mouseClicked(MouseEvent e) {

						cambiarEspadachin.setVisible(false);
						cambiarMago.setVisible(false);
						cambiar.setVisible(false);
						btn3Luchador.setVisible(true);
					}
					public void mousePressed(MouseEvent e) {
					}
					public void mouseReleased(MouseEvent e) {
					}
					public void mouseEntered(MouseEvent e) {
					}
					public void mouseExited(MouseEvent e) {
					}
				};
				cambiarEspadachin.addMouseListener(botonListenerCambiarEspadachin);
				cambiarMago.addMouseListener(botonListenerCambiarMago);
				cambiar.addMouseListener(botonListenerCambiar);
				}

			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
				titleInfo.setText("CAMBIAR");
				info.setText("\n\n\tCAMBIA TU PERSONAJE A OTRO"
						+ "\n\n\t-   ESPADACHIN"
						+ "\n\n\t-   MAGO");
			}

			public void mouseExited(MouseEvent e) {
				titleInfo.setText("Turno: "+playing.turno);
				info.setText("\n\n\tTu vida = "+playing.luchador.vida+
						"\n\n\tFase del enemigo ="+playing.enemy.fase+
						"\n\n\tVida Enemiga ="+playing.enemy.vida+
						"\n\n\tArmadura Enemiga ="+playing.enemy.armadura);
			}
		};
		btn1Luchador.addMouseListener(botonListenerAttack);
		btn2Luchador.addMouseListener(botonListenerEspecial);
		btn3Luchador.addMouseListener(botonListenerCambiar);
	}
	
	public static void interfazEspadachin(JTextArea info, JLabel titleInfo) {
		
		System.out.println("ERES LUCHADOR");
		JButton btn1Espadachin = new JButton("ATACAR - ESPADACHIN");
		btn1Espadachin.setBounds(20,600, 300, 60);
		btn1Espadachin.setBackground(new Color(12, 12, 12));
		btn1Espadachin.setBorder(BorderFactory.createLineBorder(Color.green));
		btn1Espadachin.setForeground(Color.GREEN);
		btn1Espadachin.setVisible(true);
		panelInGame.add(btn1Espadachin);
		
		JButton btn2Espadachin = new JButton("ESPECIAL - ESPADACHIN");
		btn2Espadachin.setBounds(340,600, 300, 60);
		btn2Espadachin.setBackground(new Color(12, 12, 12));
		btn2Espadachin.setBorder(BorderFactory.createLineBorder(Color.green));
		btn2Espadachin.setForeground(Color.GREEN);
		btn2Espadachin.setVisible(true);
		panelInGame.add(btn2Espadachin);
		
		JButton btn3Espadachin = new JButton("CAMBIAR - ERES ESPADACHIN");
		btn3Espadachin.setBounds(660,600, 300, 60);
		btn3Espadachin.setBackground(new Color(12, 12, 12));
		btn3Espadachin.setBorder(BorderFactory.createLineBorder(Color.green));
		btn3Espadachin.setForeground(Color.GREEN);
		btn3Espadachin.setVisible(true);
		panelInGame.add(btn3Espadachin);
		
		MouseListener botonListenerAttack = new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				info.setText("\n\tATACASTE!");
				playing.espadachin.ataque(playing.enemy,playing,info,panelInGame);
				playing.enemy.ataqueEnemigo(info, titleInfo, playing.enemy, playing.espadachin);
				if (playing.ganar()) {
					JButton victoria = new JButton("¡ GANASTE !");
					info.setVisible(false);
					titleInfo.setVisible(false);
					btn1Espadachin.setVisible(false);
					btn2Espadachin.setVisible(false);
					btn3Espadachin.setVisible(false);
					victoria.setBounds(340,300, 320, 60);
					victoria.setForeground(Color.GREEN);
					victoria.setBackground(new Color(12, 12, 12));
					victoria.setBorder(BorderFactory.createLineBorder(Color.green));
					panelInGame.add(victoria);
				}	else if (playing.perder()){
					JButton victoria = new JButton("¡ PERDISTE !");
					info.setVisible(false);
					titleInfo.setVisible(false);
					btn1Espadachin.setVisible(false);
					btn2Espadachin.setVisible(false);
					btn3Espadachin.setVisible(false);
					victoria.setBounds(340,300, 320, 60);
					victoria.setForeground(Color.red);
					victoria.setBackground(new Color(12, 12, 12));
					victoria.setBorder(BorderFactory.createLineBorder(Color.red));
					panelInGame.add(victoria);
				}
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
				titleInfo.setText("ATACAR?");
				info.setText("\n\n\tTu daño = "+playing.espadachin.daño+
						"\n\n\tLuchador: Tu personaje es extrmadamente \n\tfuerte atacando"
						+ "\n\n\tTIP: Aprovecha daño para derrotar \n\tmás rápido al enemigo");
			}
			public void mouseExited(MouseEvent e) {
				titleInfo.setText("Turno: "+playing.turno);
				info.setText("\n\n\tTu vida = "+playing.espadachin.vida+
						"\n\n\tFase del enemigo ="+playing.enemy.fase+
						"\n\n\tVida Enemiga ="+playing.enemy.vida+
						"\n\n\tArmadura Enemiga ="+playing.enemy.armadura);
			}
		};
		MouseListener botonListenerEspecial = new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {
				
				btn2Espadachin.setVisible(false);
				JButton poder1 = new JButton("HOJA PLATEADA");
				JButton poder2 = new JButton("ESTOCADA MORTAL");
				JButton cerrarPoderes = new JButton("ESPECIAL - ESPADACHIN");
				poder1.setBounds(340,530, 300, 60);
				poder1.setBackground(new Color(12, 12, 12));
				poder1.setBorder(BorderFactory.createLineBorder(Color.green));
				poder1.setForeground(Color.GREEN);
				
				panelInGame.add(poder1);
				poder2.setBounds(340,460, 300, 60);
				poder2.setBackground(new Color(12, 12, 12));
				poder2.setBorder(BorderFactory.createLineBorder(Color.green));
				poder2.setForeground(Color.GREEN);
				
				panelInGame.add(poder2);
				cerrarPoderes.setBounds(340,600, 300, 60);
				cerrarPoderes.setBackground(new Color(12, 12, 12));
				cerrarPoderes.setBorder(BorderFactory.createLineBorder(Color.green));
				cerrarPoderes.setForeground(Color.GREEN);
				panelInGame.add(cerrarPoderes);

				info.setText("\n\tESPECIALES DE ESPADACHÍN!");
				poder1.setVisible(true);
				poder2.setVisible(true);
				cerrarPoderes.setVisible(true);

				MouseListener poder1Listener = new MouseListener() {

					public void mouseClicked(MouseEvent e) {
						playing.espadachin.hojaPlateada(playing.enemy, playing, info, panelInGame);
					}
					public void mousePressed(MouseEvent e) {
					}
					public void mouseReleased(MouseEvent e) {
					}
					public void mouseEntered(MouseEvent e) {
						titleInfo.setText("HOJA PLATEADA");
						info.setText("\n\n\tREDUCE LA ARMADURA DEL ENEMIGO A 0!"
								+ "\n\n\tTiempo de recarga = 3 turnos\n\n\tDaño espadachín = "
								+playing.espadachin.daño+"\n\n\tArmadura enemiga = "
								+playing.enemy.armadura+"\n\n\tVida enemiga = "
								+playing.enemy.vida);
					}
					public void mouseExited(MouseEvent e) {
						titleInfo.setText("Turno: "+playing.turno);
						info.setText("\n\n\tTu vida = "+playing.espadachin.vida+
								"\n\n\tFase del enemigo ="+playing.enemy.fase+
								"\n\n\tVida Enemiga ="+playing.enemy.vida+
								"\n\n\tArmadura Enemiga ="+playing.enemy.armadura);
					}
				};
				MouseListener poder2Listener = new MouseListener() {

					public void mouseClicked(MouseEvent e) {
						playing.espadachin.estocadaMortal(playing.enemy,playing, info, panelInGame);
						if (playing.ganar()) {
							JButton victoria = new JButton("¡ GANASTE !");
							info.setVisible(false);
							titleInfo.setVisible(false);
							btn1Espadachin.setVisible(false);
							btn2Espadachin.setVisible(false);
							btn3Espadachin.setVisible(false);
							victoria.setBounds(340,300, 320, 60);
							victoria.setForeground(Color.GREEN);
							victoria.setBackground(new Color(12, 12, 12));
							victoria.setBorder(BorderFactory.createLineBorder(Color.green));
							panelInGame.add(victoria);
						}	else if (playing.perder()){
							JButton victoria = new JButton("¡ PERDISTE !");
							info.setVisible(false);
							titleInfo.setVisible(false);
							btn1Espadachin.setVisible(false);
							btn2Espadachin.setVisible(false);
							btn3Espadachin.setVisible(false);
							poder1.setVisible(false);
							poder2.setVisible(false);
							cerrarPoderes.setVisible(false);
							victoria.setBounds(340,300, 320, 60);
							victoria.setForeground(Color.red);
							victoria.setBackground(new Color(12, 12, 12));
							victoria.setBorder(BorderFactory.createLineBorder(Color.red));
							panelInGame.add(victoria);
						}
					}
					public void mousePressed(MouseEvent e) {
					}
					public void mouseReleased(MouseEvent e) {
					}
					public void mouseEntered(MouseEvent e) {
						titleInfo.setText("ESTOCADA MORTAL");
						info.setText("\n\n\tRealiza un ataque con daño multiplicado\n\tX5,"+
								"la multiplicación se acumula con los\n\tposibles críticos del espadachin"
								+ "\n\n\tTiempo de recarga: 5 turnos"
								+ "\n\n\tTIP: Úsalo especialmente cuando el\n\tenemigo tenga baja armadura");
					}
					public void mouseExited(MouseEvent e) {
						titleInfo.setText("Turno: "+playing.turno);
						info.setText("\n\n\tTu vida = "+playing.espadachin.vida+
								"\n\n\tFase del enemigo ="+playing.enemy.fase+
								"\n\n\tVida Enemiga ="+playing.enemy.vida+
								"\n\n\tArmadura Enemiga ="+playing.enemy.armadura);
					}
				};
				MouseListener cerrarListener = new MouseListener() {

					public void mouseClicked(MouseEvent e) {
						info.setText("\n\tESPECIAL - ESPADACHIN");
						poder1.setVisible(false);
						poder2.setVisible(false);
						cerrarPoderes.setVisible(false);
						btn2Espadachin.setVisible(true);
					}
					public void mousePressed(MouseEvent e) {
					}
					public void mouseReleased(MouseEvent e) {
					}
					public void mouseEntered(MouseEvent e) {

					}
					public void mouseExited(MouseEvent e) {
					}
				};
				poder1.addMouseListener(poder1Listener);
				poder2.addMouseListener(poder2Listener);
				cerrarPoderes.addMouseListener(cerrarListener);
			}

			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
				titleInfo.setText("ESPECIAL DE ESPADACHÍN");
				info.setText("\n\n\tHOJA PLATEADA\n\n\tESTOCADA MORTAL");
			}
			public void mouseExited(MouseEvent e) {
				titleInfo.setText("Turno: "+playing.turno);
				info.setText("\n\n\tTu vida = "+playing.espadachin.vida+
						"\n\n\tFase del enemigo ="+playing.enemy.fase+
						"\n\n\tVida Enemiga ="+playing.enemy.vida+
						"\n\n\tArmadura Enemiga ="+playing.enemy.armadura);
			}
		};		
		MouseListener botonListenerCambiar = new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				
				JButton cambiarLuchador = new JButton("CAMBIAR A LUCHADOR");
				cambiarLuchador.setBounds(660,530, 300, 60);
				cambiarLuchador.setBackground(new Color(12, 12, 12));
				cambiarLuchador.setBorder(BorderFactory.createLineBorder(Color.green));
				cambiarLuchador.setForeground(Color.GREEN);
				
				panelInGame.add(cambiarLuchador);
				JButton cambiarMago = new JButton("CAMBIAR A MAGO");
				cambiarMago.setBounds(660,460, 300, 60);
				cambiarMago.setBackground(new Color(12, 12, 12));
				cambiarMago.setBorder(BorderFactory.createLineBorder(Color.green));
				cambiarMago.setForeground(Color.GREEN);
				
				panelInGame.add(cambiarMago);
				btn3Espadachin.setVisible(false);
				JButton cambiar = new JButton("CAMBIAR - ERES ESPADACHIN");
				cambiar.setBounds(660,600, 300, 60);
				cambiar.setBackground(new Color(12, 12, 12));
				cambiar.setBorder(BorderFactory.createLineBorder(Color.green));
				cambiar.setForeground(Color.GREEN);
				panelInGame.add(cambiar);
				
				MouseListener botonListenerCambiarMago = new MouseListener() {

					public void mouseClicked(MouseEvent e) {
						btn1Espadachin.setVisible(false);
						btn2Espadachin.setVisible(false);
						btn3Espadachin.setVisible(false);
						cambiarLuchador.setVisible(false);
						cambiarMago.setVisible(false);
						cambiar.setVisible(false);
						interfazMago(info,titleInfo);
					}
					public void mousePressed(MouseEvent e) {
					}
					public void mouseReleased(MouseEvent e) {
					}
					public void mouseEntered(MouseEvent e) {
						titleInfo.setText("CAMBIAR");
						info.setText("\n\n\tCAMBIA TU PERSONAJE A MAGO");
					}
					public void mouseExited(MouseEvent e) {
					}
				};
				MouseListener botonListenerCambiarLuchador = new MouseListener() {

					public void mouseClicked(MouseEvent e) {
						btn1Espadachin.setVisible(false);
						btn2Espadachin.setVisible(false);
						btn3Espadachin.setVisible(false);
						cambiarLuchador.setVisible(false);
						cambiarMago.setVisible(false);
						cambiar.setVisible(false);
						interfazLuchador(info,titleInfo);
					}
					public void mousePressed(MouseEvent e) {
					}
					public void mouseReleased(MouseEvent e) {
					}
					public void mouseEntered(MouseEvent e) {
						titleInfo.setText("CAMBIAR");
						info.setText("\n\n\tCAMBIA TU PERSONAJE A LUCHADOR");
					}
					public void mouseExited(MouseEvent e) {
					}
				};
				MouseListener botonListenerCambiar = new MouseListener() {

					public void mouseClicked(MouseEvent e) {

						cambiarLuchador.setVisible(false);
						cambiarMago.setVisible(false);
						cambiar.setVisible(false);
						btn3Espadachin.setVisible(true);
					}
					public void mousePressed(MouseEvent e) {
					}
					public void mouseReleased(MouseEvent e) {
					}
					public void mouseEntered(MouseEvent e) {
					}
					public void mouseExited(MouseEvent e) {
					}
				};
				cambiarLuchador.addMouseListener(botonListenerCambiarLuchador);
				cambiarMago.addMouseListener(botonListenerCambiarMago);
				cambiar.addMouseListener(botonListenerCambiar);
				}

			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
				titleInfo.setText("CAMBIAR");
				info.setText("\n\n\tCAMBIA TU PERSONAJE A OTRO"
						+ "\n\n\t-   LUCHADOR"
						+ "\n\n\t-   MAGO");
			}

			public void mouseExited(MouseEvent e) {
				titleInfo.setText("Turno: "+playing.turno);
				info.setText("\n\n\tTu vida = "+playing.espadachin.vida+
						"\n\n\tFase del enemigo ="+playing.enemy.fase+
						"\n\n\tVida Enemiga ="+playing.enemy.vida+
						"\n\n\tArmadura Enemiga ="+playing.enemy.armadura);
			}
		};
		btn1Espadachin.addMouseListener(botonListenerAttack);
		btn2Espadachin.addMouseListener(botonListenerEspecial);
		btn3Espadachin.addMouseListener(botonListenerCambiar);
	}
	
	public static void inicioJuegoSettings(JTextArea info, JLabel titleInfo){
		
		info.setOpaque(true);
		info.setText("INFORMACIÓN !!!!!");
		info.setBounds(60,110,380,300);
		info.setBorder(null);
		
		info.setForeground(Color.GREEN);
		info.setBackground(new Color(12, 12, 12));
		info.setBorder(BorderFactory.createLineBorder(Color.green));
		
		info.setEditable(false);
		info.setLineWrap(true);
		info.setWrapStyleWord(true);
		info.setAlignmentX(CENTER_ALIGNMENT);
		info.setVisible(true);
		 
		titleInfo.setOpaque(true);
		titleInfo.setText("Title");
		
		titleInfo.setBounds(60,40,380,60);
		titleInfo.setBorder(BorderFactory.createLineBorder(Color.green));
		titleInfo.setForeground(Color.GREEN);
		titleInfo.setBackground(new Color(12, 12, 12));
		
		panelInGame.add(info);
		panelInGame.add(titleInfo);
	}

	public JPanel obtenerPanel() {
		return panel;
	}
	
	public static void main (String [] args) {
		new Ventana();
		System.out.println("Hola");
	}
	
}
