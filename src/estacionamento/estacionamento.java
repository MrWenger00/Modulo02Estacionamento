/*GUILHERME WENGER
 *    */
package estacionamento;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

public class estacionamento {
	static JFrame apresentacao;
	static JFrame telaInicial;
	static JFrame telaEntrada;
	static JFrame telaMapa;

	static JTable vagasOcupadas;

	static JScrollPane tabelavagasOcupadas;

	static ImageIcon fundo = new ImageIcon("wenger-logo.png");

	static JButton botaoComecar;
	static JButton botaoEntrada;
	static JButton botaoSaida;
	static JButton botaoConsultar;
	static JButton botaoOk;
	static JButton botaoCancelar;
	static JButton botaoSair;
	static JButton botaoProximo; 
	static JButton botaoAnterior;
	static JButton botaoOkMapa; 

	static JRadioButton rbConvencional;
	static JRadioButton rbPrioritario;
	static JRadioButton rbMoto;

	static JPanel painelApresentacao;
	static JPanel painelInicial;
	static JPanel painelEntrada;
	static JPanel painelMapa;

	static JEditorPane vagas;

	static JProgressBar carregarInicio;

	static JFormattedTextField campoPlaca;
	static MaskFormatter mascara = null;

	static JLabel lbApresentacao;
	static JLabel lbApresentacao1;
	static JLabel lbApresentacao2;
	static JLabel lbApresentacao3;
	static JLabel lbLogo;
	static JLabel lbTabelaVagas;
	static JLabel lbStatusGeral;
	static JLabel lbPlaca;
	static JLabel lbTipoVaga;
	static JLabel lbTituloMapa;
	static JLabel lbQtdeVagasGeral; 
	static JLabel lbConvencionalGeral;
	static JLabel lbPrioritarioGeral; 
	static JLabel lbMotoGeral; 
	static JLabel lbLegenda1;
	static JLabel lbLegenda2;
	static JLabel lbLegenda3;
	static JLabel lbLegenda4;

	static int vagasCarros = 30;
	static int camposVeiculos = 5;
	static int vagasMotos = 15;
	static int andar = 1;
	
	static int totalVagasConvencional =  60;
	static int totalVagasPrioritario = 30;
	static int totalVagasMoto = 45;
	static int totalVagasOcupadas = 0;

	static String[][] carrosPrimeiroAndar = new String[vagasCarros][camposVeiculos];
	static String[][] carrosSegundoAndar = new String[vagasCarros][camposVeiculos];
	static String[][] carrosTerceiroAndar = new String[vagasCarros][camposVeiculos];

	static String[][] motoPrimeiroAndar = new String[vagasMotos][camposVeiculos];
	static String[][] motoSegundoAndar = new String[vagasMotos][camposVeiculos];
	static String[][] motoTerceiroAndar = new String[vagasMotos][camposVeiculos];
	static String[] movimentacao = new String[7];

	static DefaultTableModel modeloEstacionamento;

	static String[] camposVaga = new String[] { "Número", "Andar", "Estado", "Placa", "Tipo da vaga" };
	static String[] campos = new String[] { "Andar","Vaga","Placa", "Tipo do evento" };
	static String tipoVaga = "";
	

	public static void main(String[] args) {
		liberarVagasCarros();
		liberarVagasMotos();
		criarTelaApresentacao();
	}
	
	static void reiniciar(){
		andar =1;
		tipoVaga="";
	}
	static void liberarVagasCarros() {
		for (int i = 0; i < vagasCarros; i++) {
			carrosPrimeiroAndar[i][0] = String.valueOf(i + 1);
			carrosSegundoAndar[i][0] = String.valueOf(i + 1);
			carrosTerceiroAndar[i][0] = String.valueOf(i + 1);
			
			carrosPrimeiroAndar[i][1] = String.valueOf(1);
			carrosSegundoAndar[i][1] = String.valueOf(2);
			carrosTerceiroAndar[i][1] = String.valueOf(3);
			
			carrosPrimeiroAndar[i][2] = "livre";
			carrosSegundoAndar[i][2] = "livre";
			carrosTerceiroAndar[i][2] = "livre";
			
			carrosPrimeiroAndar[i][3] = " ";
			carrosSegundoAndar[i][3] = " ";
			carrosTerceiroAndar[i][3] = " ";
			
			if(i<=10){
				carrosPrimeiroAndar[i][4] ="Prioritário";
				carrosSegundoAndar[i][4] = "Prioritário";
				carrosTerceiroAndar[i][4] = "Prioritário";
			}else{
				carrosPrimeiroAndar[i][4] ="Convencional";
				carrosSegundoAndar[i][4] = "Convencional";
				carrosTerceiroAndar[i][4] = "Convencional";
			}
		}
	}

	static void liberarVagasMotos() {
		for (int i = 0; i < vagasMotos; i++) {
			motoPrimeiroAndar[i][0] = String.valueOf(i + 1);
			motoSegundoAndar[i][0] = String.valueOf(i + 1);
			motoTerceiroAndar[i][0] = String.valueOf(i + 1);
	
			motoPrimeiroAndar[i][4] = "Moto";
			motoSegundoAndar[i][4] = "Moto";
			motoTerceiroAndar[i][4] = "Moto";
			
			motoPrimeiroAndar[i][2] = "livre";
			motoSegundoAndar[i][2] = "livre";
			motoTerceiroAndar[i][2] = "livre";
			
			motoPrimeiroAndar[i][3] = " ";
			motoSegundoAndar[i][3] = " ";
			motoTerceiroAndar[i][3] = " ";
		}
	}

	static void criarTelaApresentacao() {
		apresentacao = new JFrame("Boas vindas");
		apresentacao.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		iniciarComponentesApresentacao();
	}

	static void iniciarComponentesApresentacao() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			// erro
		}

		painelApresentacao = new JPanel();
		painelApresentacao.setLayout(null);
		painelApresentacao.setVisible(true);

		carregarInicio = new JProgressBar();
		carregarInicio.setMinimum(0);
		carregarInicio.setMaximum(100);
		carregarInicio.setLocation(100, 250);
		carregarInicio.setSize(400, 20);
		carregarInicio.setStringPainted(true);
		carregarInicio.setBackground(Color.YELLOW);
		carregarInicio.setForeground(Color.blue);
		carregarInicio.setIndeterminate(false);
		carregarInicio.setVisible(true);

		botaoComecar = new JButton("Começar");
		botaoComecar.setLayout(null);
		botaoComecar.setVisible(false);
		botaoComecar.setSize(100, 20);
		botaoComecar.setLocation(250, 300);

		lbLogo = new JLabel(fundo);
		lbLogo.setBounds(150, 130, 300, 100);
		lbLogo.setVisible(true);

		lbApresentacao = new JLabel("Seja Bem-vindo ao módulo: Controle de estacionamento");
		lbApresentacao.setBounds(100, 10, 500, 30);
		lbApresentacao.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 16));
		lbApresentacao.setVisible(true);

		lbApresentacao1 = new JLabel("Desenvolvido por: Guilherme Wenger");
		lbApresentacao1.setBounds(170, 350, 400, 30);
		lbApresentacao1.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 12));
		lbApresentacao1.setVisible(true);

		lbApresentacao2 = new JLabel("E-mail: grw090@gmail.com");
		lbApresentacao2.setBounds(430, 350, 400, 30);
		lbApresentacao2.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 12));
		lbApresentacao2.setVisible(true);

		lbApresentacao3 = new JLabel("versão 1.0");
		lbApresentacao3.setBounds(0, 350, 100, 30);
		lbApresentacao3.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 12));
		lbApresentacao3.setForeground(Color.blue);
		lbApresentacao3.setVisible(true);

		mostrarApresentacao();
	}

	static void mostrarApresentacao() {
		travarTela(apresentacao, 600, 405);
		apresentacao.setVisible(true);
		apresentacao.add(painelApresentacao);
		painelApresentacao.add(carregarInicio);
		painelApresentacao.add(botaoComecar);
		painelApresentacao.add(lbApresentacao);
		painelApresentacao.add(lbApresentacao1);
		painelApresentacao.add(lbApresentacao2);
		painelApresentacao.add(lbApresentacao3);
		painelApresentacao.add(lbLogo);
		movimentarBarra();
	}

	static void criarTelaInicial() {
		telaInicial = new JFrame("Estacionamento");
		telaInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		iniciarComponentesTelaInicial();
	}

	static void iniciarComponentesTelaInicial() {

		lbTabelaVagas = new JLabel("Movimentação");
		lbTabelaVagas.setBounds(10, 10, 400, 30);
		lbTabelaVagas.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 12));
		lbTabelaVagas.setVisible(true);

		lbStatusGeral = new JLabel("Há vagas para: ");
		lbStatusGeral.setBounds(10, 260, 400, 30);
		lbStatusGeral.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 12));
		lbStatusGeral.setVisible(true);

		painelInicial = new JPanel();
		painelInicial = new JPanel();
		painelInicial.setLayout(null);
		painelInicial.setVisible(true);

		botaoEntrada = new JButton("Entrada");
		botaoEntrada.setLayout(null);
		botaoEntrada.setVisible(true);
		botaoEntrada.setSize(100, 20);
		botaoEntrada.setLocation(10, 300);

		botaoSaida = new JButton("Saída");
		botaoSaida.setLayout(null);
		botaoSaida.setVisible(true);
		botaoSaida.setSize(100, 20);
		botaoSaida.setLocation(170, 300);

		botaoConsultar = new JButton("Mapa");
		botaoConsultar.setLayout(null);
		botaoConsultar.setVisible(true);
		botaoConsultar.setSize(100, 20);
		botaoConsultar.setLocation(330, 300);

		botaoSair = new JButton("Sair");
		botaoSair.setLayout(null);
		botaoSair.setVisible(true);
		botaoSair.setSize(100, 20);
		botaoSair.setLocation(480, 300);

		modeloEstacionamento = new DefaultTableModel(campos, 0) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		vagasOcupadas = new JTable();
		vagasOcupadas.setLayout(null);
		vagasOcupadas.setBounds(10, 30, 570, 230);
		vagasOcupadas.setVisible(true);
		vagasOcupadas.setModel(modeloEstacionamento);

		tabelavagasOcupadas = new JScrollPane(vagasOcupadas);
		tabelavagasOcupadas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		tabelavagasOcupadas.setBounds(10, 30, 570, 230);
		tabelavagasOcupadas.setVisible(true);
		tabelavagasOcupadas.setViewportView(vagasOcupadas);

		mostrarTelaInicial();

	}

	static void mostrarTelaInicial() {
		travarTela(telaInicial, 600, 405);
		telaInicial.setVisible(true);
		telaInicial.add(painelInicial);
		painelInicial.add(botaoEntrada);
		painelInicial.add(botaoSaida);
		painelInicial.add(botaoConsultar);
		painelInicial.add(botaoSair);
		painelInicial.add(tabelavagasOcupadas);
		painelInicial.add(lbTabelaVagas);
		painelInicial.add(lbStatusGeral);
		chamarAcoesTelaInicial();
	}

	static void criarTelaEntradaSaida() {
		telaEntrada = new JFrame();
		telaEntrada.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		iniciarComponentesTelaEntrada();
	}

	static void iniciarComponentesTelaEntrada() {
		painelEntrada = new JPanel();
		painelEntrada.setLayout(null);
		painelEntrada.setVisible(true);

		lbTipoVaga = new JLabel("Selecione o Tipo de vaga");
		lbTipoVaga.setBounds(10, 05, 220, 30);
		lbTipoVaga.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 12));
		lbTipoVaga.setVisible(true);

		lbPlaca = new JLabel("Informe a Placa");
		lbPlaca.setBounds(10, 60, 220, 30);
		lbPlaca.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 12));
		lbPlaca.setVisible(true);

		rbConvencional = new JRadioButton("Convecional");
		rbConvencional.setSelected(true);
		rbConvencional.setLayout(null);
		rbConvencional.setBounds(10, 30, 100, 20);
		rbConvencional.setVisible(true);

		rbPrioritario = new JRadioButton("Prioritário");
		rbPrioritario.setSelected(false);
		rbPrioritario.setLayout(null);
		rbPrioritario.setBounds(140, 30, 100, 20);
		rbPrioritario.setVisible(true);

		rbMoto = new JRadioButton("Moto");
		rbMoto.setSelected(false);
		rbMoto.setLayout(null);
		rbMoto.setBounds(270, 30, 100, 20);
		rbMoto.setVisible(true);

		botaoOk = new JButton("Ok");
		botaoOk.setLayout(null);
		botaoOk.setVisible(true);
		botaoOk.setSize(100, 20);
		botaoOk.setLocation(10, 140);

		botaoCancelar = new JButton("Cancelar");
		botaoCancelar.setLayout(null);
		botaoCancelar.setVisible(true);
		botaoCancelar.setSize(100, 20);
		botaoCancelar.setLocation(230, 140);

		try {
			mascara = new MaskFormatter("AAA-####");
			mascara.setPlaceholderCharacter('_');
		} catch (ParseException excp) {
			System.err.println(excp.getMessage());
			System.exit(-1);
		}

		campoPlaca = new JFormattedTextField(mascara);
		campoPlaca.setLayout(null);
		campoPlaca.setBounds(10, 90, 70, 30);
		campoPlaca.setVisible(true);

		mostrarTelaEntrada();
	}

	static void mostrarTelaEntrada() {
		travarTela(telaEntrada, 350, 190);
		telaEntrada.setVisible(true);
		telaEntrada.add(painelEntrada);
		painelEntrada.add(lbTipoVaga);
		painelEntrada.add(rbConvencional);
		painelEntrada.add(rbPrioritario);
		painelEntrada.add(rbMoto);
		painelEntrada.add(botaoOk);
		painelEntrada.add(botaoCancelar);
		painelEntrada.add(lbPlaca);
		painelEntrada.add(campoPlaca);

		chamarAcoesTelaEntrada();
	}

	static void criarTelaMapa() {
		telaMapa = new JFrame();
		telaMapa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		iniciarComponentesMapa();
	}

	static void iniciarComponentesMapa() {
		vagas = new JEditorPane();
		vagas.setEditable(false);
		vagas.setContentType("text/html");
		vagas.setSize(800, 460);
		vagas.setLocation(0, 0);

		painelMapa = new JPanel();
		painelMapa.setLayout(null);
		painelMapa.setVisible(true);

		botaoOkMapa = new JButton("Ok");
		botaoOkMapa.setLayout(null);
		botaoOkMapa.setVisible(true);
		botaoOkMapa.setSize(100, 20);
		botaoOkMapa.setLocation(350, 600);

		botaoAnterior = new JButton("Anterior");
		botaoAnterior.setLayout(null);
		botaoAnterior.setVisible(true);
		botaoAnterior.setSize(100, 20);
		botaoAnterior.setLocation(10, 600);
		botaoAnterior.setEnabled(false);
		
		botaoProximo = new JButton("Próximo");
		botaoProximo.setLayout(null);
		botaoProximo.setVisible(true);
		botaoProximo.setSize(100, 20);
		botaoProximo.setLocation(690, 600);
		botaoProximo.setEnabled(true);
		
		lbQtdeVagasGeral = new JLabel("Quantidade de vagas livres:");
		lbQtdeVagasGeral.setBounds(10, 470, 300, 20);
		lbQtdeVagasGeral.setVisible(true);
		lbQtdeVagasGeral.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 12));
		
		lbConvencionalGeral = new JLabel("Quantidade de vagas convencionais Livre: "+totalVagasConvencional);
		lbConvencionalGeral.setBounds(10, 495, 300, 20);
		lbConvencionalGeral.setVisible(true);
		lbConvencionalGeral.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 12));
		
		lbPrioritarioGeral = new JLabel("Quantidade de vagas livres: "+totalVagasPrioritario);
		lbPrioritarioGeral.setBounds(10, 520, 300, 20);
		lbPrioritarioGeral.setVisible(true);
		lbPrioritarioGeral.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 12));
		
		lbMotoGeral = new JLabel("Quantidade de vagas livres: "+totalVagasMoto);
		lbMotoGeral.setBounds(10, 545, 300, 20);
		lbMotoGeral.setVisible(true);
		lbMotoGeral.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 12));
		
		lbLegenda1 = new JLabel("Vagas para carrros convencionais");
		lbLegenda1.setBounds(30, 360, 300, 20);
		lbLegenda1.setVisible(true);
		lbLegenda1.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 10));
		
		lbLegenda2 = new JLabel("Vagas para carros prioritários");
		lbLegenda2.setBounds(30, 390, 300, 20);
		lbLegenda2.setVisible(true);
		lbLegenda2.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 10));
		
		lbLegenda3 = new JLabel("Vagas para motos");
		lbLegenda3.setBounds(30, 415, 300, 20);
		lbLegenda3.setVisible(true);
		lbLegenda3.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 10));
		
		lbLegenda4 = new JLabel("Vagas Ocupadas");
		lbLegenda4.setBounds(30, 440, 300, 20);
		lbLegenda4.setVisible(true);
		lbLegenda4.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 10));
		
		mostrarMapa();
	}

	static void mostrarMapa() {
		travarTela(telaMapa, 800, 700);
		telaMapa.setVisible(true);
		telaMapa.add(painelMapa);
		painelMapa.add(botaoOkMapa);
		painelMapa.add(vagas);
		painelMapa.add(botaoAnterior);
		painelMapa.add(botaoProximo);
		painelMapa.add(lbQtdeVagasGeral);
		painelMapa.add(lbConvencionalGeral);
		painelMapa.add(lbMotoGeral);
		painelMapa.add(lbPrioritarioGeral);
		vagas.add(lbLegenda1);
		vagas.add(lbLegenda2);
		vagas.add(lbLegenda3);
		vagas.add(lbLegenda4);
		criarTabelas();
		chamarAcoesMapa();
	}

	static void criarTabelas() {
		vagas.setText("<BODY>" + "<CENTER><FONT COLOR=BLUE SIZE=6>Mapa de vagas no " + andar + "º Andar</FONT></CENTER>"
				+ "<BR>" + "<TABLE BORDER=1 align=center>" + "<TR> " 
				+ "<TD bgcolor=" + definirCor(andar, 0, "p") + " style=" + "'width: 50px'" + "><CENTER>01 </TD>" 
				+ "<TD bgcolor=" + definirCor(andar, 1, "p") + " style=" + "'width: 50px'" + "><CENTER>02 </TD>"
				+ "<TD bgcolor=" + definirCor(andar, 2, "p") + " style=" + "'width: 50px'" + "><CENTER>03 </TD>"
				+ "<TD bgcolor=" + definirCor(andar, 3, "p") + " style=" + "'width: 50px'" + "><CENTER>04 </TD>"
				+ "<TD bgcolor=" + definirCor(andar, 4, "p") + " style=" + "'width: 50px'" + "><CENTER>05 </TD>"
				+ "<TD bgcolor=" + definirCor(andar, 5, "p") + " style=" + "'width: 50px'" + "><CENTER>06 </TD>"
				+ "<TD bgcolor=" + definirCor(andar, 6, "p") + " style=" + "'width: 50px'" + "><CENTER>07 </TD>"
				+ "<TD bgcolor=" + definirCor(andar, 7, "p") + " style=" + "'width: 50px'" + "><CENTER>08 </TD>"
				+ "<TD bgcolor=" + definirCor(andar, 8, "p") + " style=" + "'width: 50px'" + "><CENTER>09 </TD>"
				+ "<TD bgcolor=" + definirCor(andar, 9, "p") + " style=" + "'height: 60px'" + "><CENTER>10 </TD>"
				+ "</TR>" + "<TR>" 
				+ "<TD bgcolor=" + definirCor(andar, 10, "c") + " style=" + "'height: 60px'" + "><CENTER>11 </TD>"
				+ "<TD bgcolor=" + definirCor(andar, 11, "c") + "><CENTER>12 </TD>"
				+ "<TD bgcolor=" + definirCor(andar, 12, "c") + "><CENTER>13 </TD>"
				+ "<TD bgcolor=" + definirCor(andar, 13, "c") + "><CENTER>14 </TD>"
				+ "<TD bgcolor=" + definirCor(andar, 14, "c") + "><CENTER>15 </TD>" 
				+ "<TD bgcolor=" + definirCor(andar, 15, "c") + "><CENTER>16 </TD>"
				+ "<TD bgcolor=" + definirCor(andar, 16, "c") + "><CENTER>17 </TD>"
				+ "<TD bgcolor=" + definirCor(andar, 17, "c") + "><CENTER>18 </TD>"
				+ "<TD bgcolor=" + definirCor(andar, 18, "c") + "><CENTER>19 </TD>"
				+ "<TD bgcolor=" + definirCor(andar, 19, "c") + " style="+ "'width: 50px'" + "><CENTER>20 </TD>"
				+ "</TR>" + "<TR>"
				+ "<TD bgcolor=" + definirCor(andar, 20, "c") + "><CENTER>21 </TD>" 
				+ "<TD bgcolor=" + definirCor(andar, 21, "c") + "><CENTER>22 </TD>" 
				+ "<TD bgcolor=" + definirCor(andar, 22, "c") + "><CENTER>23 </TD>" 
				+ "<TD bgcolor=" + definirCor(andar, 23, "c") + "><CENTER>24 </TD>" 
				+ "<TD bgcolor=" + definirCor(andar, 24, "c") + "><CENTER>25 </TD>" 
				+ "<TD bgcolor=" + definirCor(andar, 25, "c") + "><CENTER>26 </TD>" 
				+ "<TD bgcolor=" + definirCor(andar, 26, "c") + "><CENTER>27 </TD>" 
				+ "<TD bgcolor=" + definirCor(andar, 27, "c") + "><CENTER>28 </TD>" 
				+ "<TD bgcolor=" + definirCor(andar, 28, "c") + "><CENTER>29 </TD>" 
				+ "<TD bgcolor=" + definirCor(andar, 29, "c") + " style=" + "'height: 60px'" + "><CENTER>30 </TD>"
				+ "</TR>" + "</TABLE>" 
				+ "<BR>"  
				+ "<TABLE BORDER=1 align=center>" + "<TR>"
				+ "<TD bgcolor=" + definirCor(andar, 0, "m") + " style=" + "'width: 35px'" + "><CENTER>01 </TD>" 
				+ "<TD bgcolor=" + definirCor(andar, 1, "m") + " style=" + "'width: 35px'" + "><CENTER>01 </TD>" 
				+ "<TD bgcolor=" + definirCor(andar, 2, "m") + " style=" + "'width: 35px'" + "><CENTER>02 </TD>"
				+ "<TD bgcolor=" + definirCor(andar, 3, "m") + " style=" + "'width: 35px'" + "><CENTER>03 </TD>"
				+ "<TD bgcolor=" + definirCor(andar, 4, "m") + " style=" + "'width: 35px'" + "><CENTER>04 </TD>"
				+ "<TD bgcolor=" + definirCor(andar, 5, "m") + " style=" + "'width: 35px'" + "><CENTER>05 </TD>"
				+ "<TD bgcolor=" + definirCor(andar, 6, "m") + " style=" + "'width: 35px'" + "><CENTER>06 </TD>"
				+ "<TD bgcolor=" + definirCor(andar, 7, "m") + " style=" + "'width: 35px'" + "><CENTER>07 </TD>"
				+ "<TD bgcolor=" + definirCor(andar, 8, "m") + " style=" + "'width: 35px'" + "><CENTER>08 </TD>"
				+ "<TD bgcolor=" + definirCor(andar, 9, "m") + " style=" + "'width: 35px'" + "><CENTER>09 </TD>"
				+ "<TD bgcolor=" + definirCor(andar, 10, "m") + " style=" + "'width: 35px'" + "><CENTER>10 </TD>"
				+ "<TD bgcolor=" + definirCor(andar, 11, "m") + " style=" + "'width: 35px'" + "><CENTER>11 </TD>" 
				+ "<TD bgcolor=" + definirCor(andar, 12, "m") + " style=" + "'width: 35px'" + "><CENTER>12 </TD>"
				+ "<TD bgcolor=" + definirCor(andar, 13, "m") + " style=" + "'width: 35px'" + "><CENTER>13 </TD>"
				+ "<TD bgcolor=" + definirCor(andar, 14, "m") + " style=" + "'width: 35px'"	+ "><CENTER>14 </TD>"
				+ "</TR>" + "</TABLE>" 
				+ "<BR>"
				+ "<TABLE  align=left>" + "<TR>"
				+ "<TD bgcolor=" + "green></TD></TR></TABLE>"
				+ "<TABLE  align=left>" + "<TR>"
				+ "<TD bgcolor=" + "blue></TD></TR></TABLE>"
				+ "<TABLE  align=left><TR>"
				+ "<TD bgcolor=" + "yellow></TD></TR></TABLE>"
				+ "<TABLE  align=left> <TR>"
				+ "<TD bgcolor=" + "red style=" + "'height: 15px'></TD></TR></TABLE>"
				+ "</BODY>" + "</HTML>");   
	}

	static String definirCor(int andar, int vaga, String tipo) {
		String cor = "white";
		if ((verificarVagaLivre(andar, vaga, tipo) && vaga > 9) && tipo.equals("c")) {
			cor = "green";
		} else if (verificarVagaLivre(andar, vaga, tipo) && vaga <= 9 && tipo.equals("p")) {
			cor = "blue";
		} else if (verificarVagaLivre(andar, vaga, tipo) && tipo.equals("m")) {
			cor = "yellow";
		} else {
			cor = "red";
		}
		return cor;
	}

	static boolean verificarVagaLivre(int andar, int vaga, String tipo) {
		boolean livre = true;		
		int v = 0;
		if(vaga==0){
			v = 0;
		}else{
			v=vaga-1;
		}
		
		if (andar == 1) {
			if (tipo.equals("c")||tipo.equals("p")) {
				String status = carrosPrimeiroAndar[v][2];
				if (status.equals("livre")) {
					livre = true;
				} else {
					livre = false;
				}
			} else if (tipo.equals("m")) {
				String status = motoPrimeiroAndar[v][2];
				if (status.equals("livre")) {
					livre = true;
				} else {
					livre = false;
				}
			}
		} else if (andar == 2) {
			if (tipo.equals("c")||tipo.equals("p")) {				
				String status = carrosSegundoAndar[v][2];
				if (status.equals("livre")) {
					livre = true;
				} else {
					livre = false;
				}
			} else if (tipo.equals("m")) {
				String status = motoSegundoAndar[v][2];
				if (status.equals("livre")) {
					livre = true;
				} else {
					livre = false;
				}
			}
		} else if (andar == 3) {
			if (tipo.equals("c")||tipo.equals("p")) {
				String status = carrosTerceiroAndar[v][2];
				if (status.equals("livre")) {
					livre = true;
				} else {
					livre = false;
				}
			} else if (tipo.equals("m")) {
				String status = motoTerceiroAndar[v][2];
				if (status.equals("livre")) {
					livre = true;
				} else {
					livre = false;
				}
			}
		}
		return livre;
	}

	static void movimentarBarra() {
		for (int i = 0; i <= 100; i++) {
			carregarInicio.setValue(i);
			try {
				Thread.sleep(20);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			if (i == 100) {
				botaoComecar.setVisible(true);
				chamarAcoesApresentacao();
			}
		}
	}

	static void chamarAcoesApresentacao() {
		botaoComecar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				apresentacao.dispose();
				criarTelaInicial();
			}
		});
	}

	static void chamarAcoesTelaInicial() {
		telaInicial.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				/* Método sem implementação necessária */				
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				if(!haVagas()){
					botaoEntrada.setEnabled(false);
					lbStatusGeral.setText("Não há vagas");
				}else{
					botaoEntrada.setEnabled(true);
					lbStatusGeral.setText("Há vagas para: "+verificarSetoresLivres());
					if(totalVagasOcupadas<1){
						botaoSaida.setEnabled(false);
					}else{
						botaoSaida.setEnabled(true);
					}
				}
				
			}
		});
		botaoEntrada.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				criarTelaEntradaSaida();
			}
		});
		
		botaoSaida.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int andar = selecionarAndar();
				tipoVaga = selcionarCategoria();
				int vaga  = selecionarVaga();
				
				if(!verificarVagaLivre(andar, vaga, tipoVaga)){
					if(tipoVaga.equals("c")||tipoVaga.equals("p")){
						sairCarro(andar, vaga);
					}else{
						sairMoto(andar, vaga);
					}
				}else{
					botaoSaida.doClick();
				}
				reiniciar();
			}

			private String selcionarCategoria() {
				String cat = "cpm";
				Random gerador = new Random(); 
				String sorteado = String.valueOf((cat.charAt(gerador.nextInt(cat.length()))));
				return sorteado;
			}
		});
		
		botaoSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);

			}
		});

		botaoConsultar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				criarTelaMapa();
			}
		});
	}

	static void chamarAcoesTelaEntrada() {
		telaEntrada.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				if(haVagas()){
					if(totalVagasConvencional==0){
						rbConvencional.setEnabled(false);
					}
				
					if(totalVagasMoto==0){
						rbMoto.setEnabled(false);
					}
				
					if(totalVagasPrioritario==0){
						rbPrioritario.setEnabled(false);
					}
				}				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				/* Método sem implementação necessária */				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				/* Método sem implementação necessária */				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				/* Método sem implementação necessária */				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				/* Método sem implementação necessária */				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				/* Método sem implementação necessária */				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				/* Método sem implementação necessária */				
			}
		});
		rbConvencional.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				/* Método sem implementação necessária */
			}

			@Override
			public void mousePressed(MouseEvent e) {
				/* Método sem implementação necessária */
			}

			@Override
			public void mouseExited(MouseEvent e) {
				/* Método sem implementação necessária */
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				rbConvencional.setToolTipText("Vaga para carro convencional");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if(rbConvencional.isEnabled()){
					rbMoto.setSelected(false);
					rbPrioritario.setSelected(false);
					rbConvencional.setSelected(true);
				}

			}
		});

		rbPrioritario.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				/* Método sem implementação necessária */
			}

			@Override
			public void mousePressed(MouseEvent e) {
				/* Método sem implementação necessária */
			}

			@Override
			public void mouseExited(MouseEvent e) {
				/* Método sem implementação necessária */
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				rbPrioritario.setToolTipText("Vaga para idosos ou deficientes.");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if(rbPrioritario.isEnabled()){
					rbMoto.setSelected(false);
					rbPrioritario.setSelected(true);
					rbConvencional.setSelected(false);
				}

			}
		});

		rbMoto.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				/* Método sem implementação necessária */
			}

			@Override
			public void mousePressed(MouseEvent e) {
				/* Método sem implementação necessária */
			}

			@Override
			public void mouseExited(MouseEvent e) {
				/* Método sem implementação necessária */
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				rbMoto.setToolTipText("Vaga para moto.");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if(rbMoto.isEnabled()){
					rbMoto.setSelected(true);
					rbPrioritario.setSelected(false);
					rbConvencional.setSelected(false);
				}
			}
		});

		botaoCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				telaEntrada.dispose();
				reiniciar();
			}
		});
		
		botaoOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(rbConvencional.isSelected() && rbConvencional.isEnabled()){
					tipoVaga = "c";
					int andar = selecionarAndar();
					int vaga = selecionarVaga();					
					String placa = campoPlaca.getText();
					if(verificarVagaLivre(andar, vaga, "c")){
						estacionarCarro(andar, vaga, placa);
						totalVagasConvencional--;
						totalVagasOcupadas++;
					}else{
						botaoOk.doClick();
					}
					telaEntrada.dispose();
				}else if(rbPrioritario.isSelected() && rbPrioritario.isEnabled()){
					int tentativas = 0;
					if(tentativas<30){
						tipoVaga = "p";
					}else{
						tipoVaga = "c";
					}
					int andar = selecionarAndar();
					int vaga = selecionarVaga();					
					String placa = campoPlaca.getText();
					if(verificarVagaLivre(andar, vaga, tipoVaga)){
						estacionarCarro(andar, vaga, placa);
						totalVagasPrioritario--;
						totalVagasOcupadas++;
					}else{
						tentativas++;
						botaoOk.doClick();
					}
					telaEntrada.dispose();
				}else if(rbMoto.isSelected() && rbMoto.isEnabled()){
					tipoVaga = "m";
					int andar = selecionarAndar();
					int vaga = selecionarVaga();					
					String placa = campoPlaca.getText();
					if(verificarVagaLivre(andar, vaga, "m")){
						estacionarMoto(andar, vaga, placa);
						totalVagasMoto--;
						totalVagasOcupadas++;
					}else{
						if(totalVagasMoto>0){
							botaoOk.doClick();
						}
					}
					telaEntrada.dispose();
				}
				reiniciar();
			}
		});
	}
	
	static void chamarAcoesMapa(){
		botaoOkMapa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				telaMapa.dispose();
				reiniciar();
			}
		});
		
		botaoProximo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(botaoProximo.isEnabled()){
					andar++;				
					botaoAnterior.setEnabled(true);
					criarTabelas();
					if(andar==3){
						botaoProximo.setEnabled(false);
					}
				}
			}
		});
		
		botaoAnterior.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(botaoAnterior.isEnabled()){
					andar--;				
					botaoProximo.setEnabled(true);
					criarTabelas();
					if(andar==1){
						botaoAnterior.setEnabled(false);
					}
				}
				
			}
		});
	}

	static boolean haVagas(){
		if (totalVagasConvencional==0 && totalVagasMoto==0 && totalVagasPrioritario==0) {
			return false;
		}else{
			return true;
		}
	}
	
	static String verificarSetoresLivres(){
		String texto ="";
		if(totalVagasConvencional>0){
			texto += "Carro convencional,";
		}
	
		if(totalVagasMoto>0){
			texto +=" Moto,";
		}
	
		if(totalVagasPrioritario>0){
			texto +=" Carro prioritário.";
		}
		return texto;
	}
	
	static int selecionarAndar() {
		Random ran = new Random();
		return ran.nextInt(3) + 1;
	}

	static int selecionarVaga() {
		Random ran = new Random();
		int vaga = 0;
		if (tipoVaga.equals("c")) {
			vaga = ran.nextInt(21) + 10;
			if (vaga == 10) {
				vaga++;
			}
		} else if (tipoVaga.equals("p")) {
			vaga = ran.nextInt(10) + 1;
		} else if (tipoVaga.equals("m")) {
			vaga = ran.nextInt(15) + 1;
		}
		return vaga;
	}
	
	static void estacionarCarro(int andar, int vaga, String placa){
		int v = 0;
		if(vaga==0 || vaga==1){
			v = 0;
		}else{
			v=vaga-1;
		}
		
		if(andar==1){
			gravarEvento(String.valueOf(andar), String.valueOf(vaga+1),placa, "Entrada");
			carrosPrimeiroAndar[v][2] = "Ocupado";
			carrosPrimeiroAndar[v][3] = placa;
		}else if(andar==2){
			gravarEvento(String.valueOf(andar), String.valueOf(vaga+1),placa, "Entrada");
			carrosSegundoAndar[v][2] = "Ocupado";
			carrosSegundoAndar[v][3] = placa;
		}else if(andar==3){
			gravarEvento(String.valueOf(andar), String.valueOf(vaga+1),placa, "Entrada");
			carrosTerceiroAndar[v][2] = "Ocupado";
			carrosTerceiroAndar[v][3] = placa;
		}
	}
	static void sairCarro(int andar, int vaga){
		int v = 0;
		if(vaga==0 || vaga==1){
			v = 0;
		}else{
			v=vaga-1;
		}
		
		if(andar==1){
			String placa = carrosPrimeiroAndar[v][3];
			gravarEvento(String.valueOf(andar), String.valueOf(vaga+1),placa, "Saída");
			carrosPrimeiroAndar[v][2] = "livre";
			carrosPrimeiroAndar[v][3] = "";
		}else if(andar==2){
			String placa = carrosSegundoAndar[v][3];
			gravarEvento(String.valueOf(andar), String.valueOf(vaga+1),placa, "Saída");
			carrosSegundoAndar[v][2] = "livre";
			carrosSegundoAndar[v][3] = "";
		}else if(andar==3){
			String placa = carrosTerceiroAndar[v][3];
			gravarEvento(String.valueOf(andar), String.valueOf(vaga+1),placa, "Saída");
			carrosTerceiroAndar[v][2] = "livre";
			carrosTerceiroAndar[v][3] = "";
		}
	}
	static void sairMoto(int andar, int vaga){
		int v = 0;
		if(vaga==0 || vaga==1){
			v = 0;
		}else{
			v=vaga-1;
		}
		
		if(andar==1){
			String placa = motoPrimeiroAndar[v][3];
			gravarEvento(String.valueOf(andar), String.valueOf(vaga+1),placa, "Saída");
			motoPrimeiroAndar[v][2] = "livre";
			motoPrimeiroAndar[v][3] = "";
		}else if(andar==2){
			String placa = motoSegundoAndar[v][3];
			gravarEvento(String.valueOf(andar), String.valueOf(vaga+1),placa, "Saída");
			motoSegundoAndar[v][2] = "livre";
			motoSegundoAndar[v][3] = "";
		}else if(andar==3){
			String placa = motoTerceiroAndar[v][3];
			gravarEvento(String.valueOf(andar), String.valueOf(vaga+1),placa, "Saída");
			motoTerceiroAndar[v][2] = "livre";
			motoTerceiroAndar[v][3] = "";
		}
	}
	
	static void estacionarMoto(int andar, int vaga, String placa){
		int v = 0;
		if(vaga==0 || vaga==1){
			v = 0;
		}else{
			v=vaga-1;
		}
		if(andar==1){
			gravarEvento(String.valueOf(andar), String.valueOf(vaga+1),placa, "Entrada");
			motoPrimeiroAndar[v][2] = "Ocupado";
			motoPrimeiroAndar[v][3] = placa;
		}else if(andar==2){
			gravarEvento(String.valueOf(andar), String.valueOf(vaga+1),placa, "Entrada");
			motoSegundoAndar[v][2] = "Ocupado";
			motoSegundoAndar[v][3] = placa;
		}else if(andar==3){
			gravarEvento(String.valueOf(andar), String.valueOf(vaga+1),placa, "Entrada");
			motoTerceiroAndar[v][2] = "Ocupado";
			motoTerceiroAndar[v][3] = placa;
		}
	}
	
	static void gravarEvento(String andar, String vaga, String placa, String tpEvento){
		String[] dados = new String[4];
		dados[0] = andar;
		dados[1] = vaga;
		dados[2] = placa;
		dados[3] = tpEvento;
		modeloEstacionamento.addRow(dados);
	}
	
	static void travarTela(JFrame tela, int l, int a) {
		/**/
		Insets in = Toolkit.getDefaultToolkit().getScreenInsets(tela.getGraphicsConfiguration());
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		/* recupera a largura e altura do monitor */
		int width = d.width - (in.left + in.top);
		int height = d.height - (in.top + in.bottom);
		/* Define largura e altura da tela */
		int largura = l;
		int altura = a;
		/* Centraliza a tela no monitor */
		tela.setLocation(((width - largura) / 2), ((height - altura) / 2));
		/* Cria a tela com o tamanho definido */
		tela.setSize(largura, altura);
		/* define que o tamanho da tela não poderá ser alterado pelo usuário */
		tela.setResizable(false);
		tela.addComponentListener(/**/

		new ComponentAdapter() {
			@Override
			public void componentMoved(ComponentEvent e) {
				tela.setEnabled(true);
			}
		}/**/);
	}
}
