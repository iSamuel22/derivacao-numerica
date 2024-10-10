package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.lang.Math;

public class DerivacaoNumerica {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			criarEMostrarGUI();
		});
	}

	public static double f(double x) {
		return Math.pow(x, 2);
	}

	private static void criarEMostrarGUI() {
		JFrame frame = new JFrame("Derivação Numérica");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);
		frame.setLayout(new BorderLayout());

		JPanel painelEntrada = new JPanel(new GridLayout(5, 2, 10, 10));
		painelEntrada.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JLabel rotuloOpcao = new JLabel("Escolha uma opção:");
		rotuloOpcao.setFont(new Font("Arial", Font.BOLD, 14));
		JComboBox<String> comboBoxOpcao = new JComboBox<>(new String[] { "---------- Selecione um método ----------",
				"Derivada por Interpolação Numérica", "Derivada por Diferença Finita (Taylor)",
				"Segunda Derivada por Diferença Finita", "Expressões Derivadas - Representação" });

		JLabel titulo = new JLabel("Derivação Numérica");
		titulo.setFont(new Font("Arial", Font.BOLD, 24));
		titulo.setHorizontalAlignment(JLabel.CENTER);

		JLabel rotuloFuncao = new JLabel("Digite a função (Apenas números e x):");
		rotuloFuncao.setFont(new Font("Arial", Font.BOLD, 14));
		JTextField campoTextoFuncao = new JTextField();

		JLabel rotuloX = new JLabel("Digite o valor de x:");
		rotuloX.setFont(new Font("Arial", Font.BOLD, 14));
		JTextField campoTextoX = new JTextField(10);

		JLabel rotuloH = new JLabel("Digite o valor de h:");
		rotuloH.setFont(new Font("Arial", Font.BOLD, 14));
		JTextField campoTextoH = new JTextField(10);

		painelEntrada.add(rotuloOpcao);
		painelEntrada.add(comboBoxOpcao);
		painelEntrada.add(rotuloFuncao);
		painelEntrada.add(campoTextoFuncao);
		painelEntrada.add(rotuloX);
		painelEntrada.add(campoTextoX);
		painelEntrada.add(rotuloH);
		painelEntrada.add(campoTextoH);

		JButton botaoCalcular = new JButton("Calcular");
		botaoCalcular.setFont(new Font("Arial", Font.BOLD, 14));
		botaoCalcular.setBackground(new Color(70, 130, 180));
		botaoCalcular.setForeground(Color.WHITE);
		botaoCalcular.setPreferredSize(new Dimension(150, 40));

		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setFont(new Font("Arial", Font.BOLD, 14));
		botaoLimpar.setBackground(new Color(70, 130, 180));
		botaoLimpar.setForeground(Color.WHITE);
		botaoLimpar.setPreferredSize(new Dimension(150, 40));

		frame.getRootPane().setDefaultButton(botaoCalcular);

		JTextArea areaTextoResultado = new JTextArea(3, 30);
		areaTextoResultado.setEditable(false);
		areaTextoResultado.setWrapStyleWord(true);
		areaTextoResultado.setLineWrap(true);
		areaTextoResultado.setBackground(new Color(230, 230, 230));

		rotuloFuncao.setVisible(false);
		campoTextoFuncao.setVisible(false);
		rotuloX.setVisible(false);
		campoTextoX.setVisible(false);
		rotuloH.setVisible(false);
		campoTextoH.setVisible(false);

		comboBoxOpcao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String opcaoSelecionada = (String) comboBoxOpcao.getSelectedItem();
				boolean mostrarCampos = !opcaoSelecionada.equals("---------- Selecione um método ----------");

				rotuloFuncao.setVisible(opcaoSelecionada.equals("Expressões Derivadas - Representação"));
				campoTextoFuncao.setVisible(opcaoSelecionada.equals("Expressões Derivadas - Representação"));

				if (opcaoSelecionada.equals("Expressões Derivadas - Representação")) {
					rotuloFuncao.setVisible(true);
					campoTextoFuncao.setVisible(true);
					rotuloX.setVisible(false);
					campoTextoX.setVisible(false);
					rotuloH.setVisible(false);
					campoTextoH.setVisible(false);
				} else {
					rotuloFuncao.setVisible(false);
					campoTextoFuncao.setVisible(false);
					rotuloX.setVisible(mostrarCampos);
					campoTextoX.setVisible(mostrarCampos);
					rotuloH.setVisible(mostrarCampos);
					campoTextoH.setVisible(mostrarCampos);
				}
			}
		});

		botaoCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String opcao = (String) comboBoxOpcao.getSelectedItem();
				boolean mostrarEntradaFuncao = opcao.equals("Expressões Derivadas - Representação");

				if (mostrarEntradaFuncao && (campoTextoFuncao.getText().isEmpty())) {
					JOptionPane.showMessageDialog(null, "Insira uma expressão válida para a função.", "Erro de Entrada",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (opcao.equals("---------- Selecione um método ----------")) {
					JOptionPane.showMessageDialog(frame, "Por favor, selecione um método antes de calcular.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					rotuloFuncao.setVisible(mostrarEntradaFuncao);
					campoTextoFuncao.setVisible(mostrarEntradaFuncao);
					rotuloX.setVisible(!mostrarEntradaFuncao);
					campoTextoX.setVisible(!mostrarEntradaFuncao);
					rotuloH.setVisible(!mostrarEntradaFuncao);
					campoTextoH.setVisible(!mostrarEntradaFuncao);

					if (mostrarEntradaFuncao) {

						String expressao = campoTextoFuncao.getText();
						String expressaoDerivada1 = calcularPrimeiraDerivada(expressao);
						String expressaoDerivada2 = calcularSegundaDerivada(expressaoDerivada1);

						areaTextoResultado
								.setText("f'(x) = " + expressaoDerivada1 + "\nf''(x) = " + expressaoDerivada2);
					} else {
						rotuloFuncao.setVisible(false);
						campoTextoFuncao.setVisible(false);

						try {
							double x = Double.parseDouble(campoTextoX.getText());
							double h = Double.parseDouble(campoTextoH.getText());

							if (h <= 0) {
								JOptionPane.showMessageDialog(null, "O valor de h não pode ser menor ou igual a zero.",
										"Erro de Cálculo", JOptionPane.ERROR_MESSAGE);
								return;
							}

							StringBuilder resultado = new StringBuilder();
							DecimalFormat formatoDecimal = new DecimalFormat("0.0000");

							if (opcao.equals("Derivada por Interpolação Numérica")) {
								double dp1 = derivadaProgressivaOrdem1(x, h);
								double dr1 = derivadaRegressivaOrdem1(x, h);
								double dc2 = derivadaCentralOrdem2(x, h);
								double dp2 = derivadaProgressivaOrdem2(x, h);
								double dr2 = derivadaRegressivaOrdem2(x, h);
								double dc4 = derivadaCentralOrdem4(x, h);

								double derivadaExata = -2 * x * Math.exp(-x * x);

								double erroP1 = Math.abs(derivadaExata - dp1);
								double erroR1 = Math.abs(derivadaExata - dr1);
								double erroC2 = Math.abs(derivadaExata - dc2);
								double erroP2 = Math.abs(derivadaExata - dp2);
								double erroR2 = Math.abs(derivadaExata - dr2);
								double erroC4 = Math.abs(derivadaExata - dc4);

								double erroRelativoP1 = erroP1 / Math.abs(derivadaExata);
								double erroRelativoR1 = erroR1 / Math.abs(derivadaExata);
								double erroRelativoC2 = erroC2 / Math.abs(derivadaExata);
								double erroRelativoP2 = erroP2 / Math.abs(derivadaExata);
								double erroRelativoR2 = erroR2 / Math.abs(derivadaExata);
								double erroRelativoC4 = erroC4 / Math.abs(derivadaExata);

								resultado.append("Progressivas de ordem 1: ").append(formatoDecimal.format(dp1))
										.append("\n");
								resultado.append("Erro absoluto P1: ").append(formatoDecimal.format(erroP1))
										.append("\n");
								resultado.append("Erro relativo P1: ").append(formatoDecimal.format(erroRelativoP1))
										.append("\n\n");

								resultado.append("Regressivas de ordem 1: ").append(formatoDecimal.format(dr1))
										.append("\n");
								resultado.append("Erro absoluto R1: ").append(formatoDecimal.format(erroR1))
										.append("\n");
								resultado.append("Erro relativo R1: ").append(formatoDecimal.format(erroRelativoR1))
										.append("\n\n");

								resultado.append("Central de ordem 2: ").append(formatoDecimal.format(dc2))
										.append("\n");
								resultado.append("Erro absoluto C2: ").append(formatoDecimal.format(erroC2))
										.append("\n");
								resultado.append("Erro relativo C2: ").append(formatoDecimal.format(erroRelativoC2))
										.append("\n\n");

								resultado.append("Progressivas de ordem 2: ").append(formatoDecimal.format(dp2))
										.append("\n");
								resultado.append("Erro absoluto P2: ").append(formatoDecimal.format(erroP2))
										.append("\n");
								resultado.append("Erro relativo P2: ").append(formatoDecimal.format(erroRelativoP2))
										.append("\n\n");

								resultado.append("Regressivas de ordem 2: ").append(formatoDecimal.format(dr2))
										.append("\n");
								resultado.append("Erro absoluto R2: ").append(formatoDecimal.format(erroR2))
										.append("\n");
								resultado.append("Erro relativo R2: ").append(formatoDecimal.format(erroRelativoR2))
										.append("\n\n");

								resultado.append("Central de ordem 4: ").append(formatoDecimal.format(dc4))
										.append("\n");
								resultado.append("Erro absoluto C4: ").append(formatoDecimal.format(erroC4))
										.append("\n");
								resultado.append("Erro relativo C4: ").append(formatoDecimal.format(erroRelativoC4));
							} else if (opcao.equals("Derivada por Diferença Finita (Taylor)")) {
								double taylor1 = taylorOrdem1(x, h);
								double taylor2 = taylorOrdem2(x, h);

								double derivadaExata = -2 * x * Math.exp(-x * x);

								double erroTaylor1 = Math.abs(derivadaExata - taylor1);
								double erroTaylor2 = Math.abs(derivadaExata - taylor2);

								double erroRelativoTaylor1 = erroTaylor1 / Math.abs(derivadaExata);
								double erroRelativoTaylor2 = erroTaylor2 / Math.abs(derivadaExata);

								resultado.append("Derivada de ordem 1 (Taylor): ")
										.append(formatoDecimal.format(taylor1)).append("\n");
								resultado.append("Erro absoluto Taylor1: ").append(formatoDecimal.format(erroTaylor1))
										.append("\n");
								resultado.append("Erro relativo Taylor1: ")
										.append(formatoDecimal.format(erroRelativoTaylor1)).append("\n\n");

								resultado.append("Derivada de ordem 2 (Taylor): ")
										.append(formatoDecimal.format(taylor2)).append("\n");
								resultado.append("Erro absoluto Taylor2: ").append(formatoDecimal.format(erroTaylor2))
										.append("\n");
								resultado.append("Erro relativo Taylor2: ")
										.append(formatoDecimal.format(erroRelativoTaylor2));
							} else if (opcao.equals("Segunda Derivada por Diferença Finita")) {
								double segundaDerivada = segundaDerivadaDiferencaFinita(x, h);
								double segundaDerivadaExata = -2 * Math.exp(-x * x) + 4 * x * x * Math.exp(-x * x);
								double erroSegunda = Math.abs(segundaDerivadaExata - segundaDerivada);
								double erroRelativoSegunda = erroSegunda / Math.abs(segundaDerivadaExata);

								resultado.append("Segunda Derivada por Diferença Finita: ")
										.append(formatoDecimal.format(segundaDerivada)).append("\n");
								resultado.append("Erro absoluto Segunda: ").append(formatoDecimal.format(erroSegunda))
										.append("\n");
								resultado.append("Erro relativo Segunda: ")
										.append(formatoDecimal.format(erroRelativoSegunda));
							}

							areaTextoResultado.setText(resultado.toString());
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "Insira valores numéricos válidos para x e h.",
									"Erro de Entrada", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});

		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String opcao = (String) comboBoxOpcao.getSelectedItem();

				if (opcao.equals("Expressões Derivadas - Representação")) {
					campoTextoFuncao.setText("");
					campoTextoX.setText("");
					campoTextoH.setText("");
					areaTextoResultado.setText("");
					rotuloFuncao.setVisible(true);
					campoTextoFuncao.setVisible(true);
					rotuloX.setVisible(false);
					campoTextoX.setVisible(false);
					rotuloH.setVisible(false);
					campoTextoH.setVisible(false);
				} else {
					campoTextoFuncao.setText("");
					campoTextoX.setText("");
					campoTextoH.setText("");
					areaTextoResultado.setText("");
					rotuloFuncao.setVisible(false);
					campoTextoFuncao.setVisible(false);
					rotuloX.setVisible(true);
					campoTextoX.setVisible(true);
					rotuloH.setVisible(true);
					campoTextoH.setVisible(true);
				}
			}
		});

		JPanel painelBotao = new JPanel();

		painelBotao.add(botaoCalcular);
		painelBotao.add(botaoLimpar);

		frame.add(painelEntrada, BorderLayout.NORTH);
		frame.add(new JScrollPane(areaTextoResultado), BorderLayout.CENTER);
		frame.add(painelBotao, BorderLayout.SOUTH);

		frame.setVisible(true);
	}

	public static double derivadaProgressivaOrdem1(double x, double h) {
		return (f(x + h) - f(x)) / h;
	}

	public static double derivadaRegressivaOrdem1(double x, double h) {
		return (f(x) - f(x - h)) / h;
	}

	public static double derivadaCentralOrdem2(double x, double h) {
		return (f(x + h) - f(x - h)) / (2 * h);
	}

	public static double derivadaProgressivaOrdem2(double x, double h) {
		return (-3 * f(x) + 4 * f(x + h) - f(x + 2 * h)) / (2 * h);
	}

	public static double derivadaRegressivaOrdem2(double x, double h) {
		return (f(x - 2 * h) - 4 * f(x - h) + 3 * f(x)) / (2 * h);
	}

	public static double derivadaCentralOrdem4(double x, double h) {
		return (f(x - 2 * h) - 8 * f(x - h) + 8 * f(x + h) - f(x + 2 * h)) / (12 * h);
	}

	public static double taylorOrdem1(double x, double h) {
		return (f(x + h) - f(x - h)) / (2 * h);
	}

	public static double taylorOrdem2(double x, double h) {
		return (f(x - h) - 2 * f(x) + f(x + h)) / (h * h);
	}

	public static double segundaDerivadaDiferencaFinita(double x, double h) {
		return (f(x - h) - 2 * f(x) + f(x + h)) / (h * h);
	}

	public static String calcularPrimeiraDerivada(String expressao) {
		try {
			expressao = expressao.replace(" ", "");

			String[] termos = expressao.split("(?=[+-])");
			StringBuilder derivada = new StringBuilder();

			for (String termo : termos) {
				if (termo.contains("^") && !termo.startsWith("^") && !termo.endsWith("^")) {
					String[] partes = termo.split("x\\^");
					if (partes.length == 2 && partes[0].length() > 0 && partes[1].length() > 0) {
						int coeficiente = parseCoeficiente(partes[0]);
						int expoente = Integer.parseInt(partes[1]);

						int novoCoeficiente = coeficiente * expoente;
						int novoExpoente = expoente - 1;

						if (novoCoeficiente != 0) {
							if (derivada.length() > 0) {
								derivada.append(" + ");
							}
							if (novoCoeficiente != 1 || novoExpoente == 0) {
								derivada.append(novoCoeficiente);
							}
							if (novoExpoente > 0) {
								derivada.append("x");
								if (novoExpoente != 1) {
									derivada.append("^").append(novoExpoente);
								}
							}
						}
					}
				} else if (termo.contains("x")) {
					if (derivada.length() > 0) {
						derivada.append(" + ");
					}
					int coeficiente = parseCoeficiente(termo.replace("x", ""));
					derivada.append(coeficiente);
				} else if (!termo.trim().isEmpty()) {
					continue;
				}
			}

			if (derivada.length() == 0) {
				return "0";
			}
			return derivada.toString().replace("+ -", "- ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao calcular a derivada", "Erro", JOptionPane.ERROR_MESSAGE);
			return " ";
		}
	}

	public static String calcularSegundaDerivada(String expressao) {
		try {
			expressao = expressao.replace(" ", "");

			String[] termos = expressao.split("\\+");
			StringBuilder derivada = new StringBuilder();

			for (String termo : termos) {
				if (termo.contains("x^") && !termo.startsWith("^") && !termo.endsWith("^")) {
					String[] partes = termo.split("x\\^");
					if (partes.length == 2) {
						int coeficiente = partes[0].isEmpty() ? 1 : Integer.parseInt(partes[0]);
						int expoente = Integer.parseInt(partes[1]);

						int novoCoeficiente = coeficiente * expoente;
						int novoExpoente = expoente - 1;

						if (novoCoeficiente != 0) {
							if (derivada.length() > 0) {
								derivada.append(" + ");
							}
							derivada.append(novoCoeficiente * novoExpoente);
							if (novoExpoente > 1) {
								derivada.append("x^").append(novoExpoente - 1);
							} else if (novoExpoente == 1) {
								derivada.append("x");
							}
						}
					}
				} else if (termo.contains("x")) {
					if (derivada.length() > 0) {
						derivada.append(" + ");
					}
					derivada.append(termo.replace("x", ""));
				} else if (!termo.trim().isEmpty()) {
					continue;
				}
			}

			if (derivada.length() == 0) {
				return " ";
			}

			return derivada.toString();

		} catch (Exception e) {
			return " ";
		}
	}

	private static int parseCoeficiente(String coeficienteStr) {
		if (coeficienteStr.equals("-")) {
			return -1;
		} else if (coeficienteStr.equals("") || coeficienteStr.equals("+")) {
			return 1;
		} else {
			return Integer.parseInt(coeficienteStr);
		}
	}
}