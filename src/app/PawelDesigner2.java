package app; //nazwa pakietu

import java.awt.*; //import bibliotek
import java.awt.event.*;
import javax.swing.*;

/**
 * Klasa PawelDesigner2 zawiera konstruktor i metody actionPerformed() i main().
 * @author Paweł Dudzik.
 */
public class PawelDesigner2 extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 0; //serializacja programu
	JPanel panel = new JPanel();
	JProgressBar pasek = new JProgressBar();
	JSlider[] slider = {new JSlider(JSlider.HORIZONTAL, 0, 10, 0),
			new JSlider(JSlider.HORIZONTAL, 0, 10, 0)};
	JButton[] przycisk = {new JButton("MIN-MAX"), new JButton("MAX-PROD"),
			new JButton("MAX-AV")};
	JRadioButton[] r = {new JRadioButton(), new JRadioButton(),
			new JRadioButton(), new JRadioButton(), new JRadioButton(),
			new JRadioButton(), new JRadioButton(), new JRadioButton(),
			new JRadioButton()};
	JLabel[] er = {new JLabel("R0: IF X0 = M AND X1 = M THEN Y = BM"),
			new JLabel("R1: IF X0 = M AND X1 = S THEN Y = M"),
			new JLabel("R2: IF X0 = M AND X1 = D THEN Y = S"),
			new JLabel("R3: IF X0 = S AND X1 = M THEN Y = M"),
			new JLabel("R4: IF X0 = S AND X1 = S THEN Y = S"),
			new JLabel("R5: IF X0 = S AND X1 = D THEN Y = D"),
			new JLabel("R6: IF X0 = D AND X1 = M THEN Y = S"),
			new JLabel("R7: IF X0 = D AND X1 = S THEN Y = D"),
			new JLabel("R8: IF X0 = D AND X1 = D THEN Y = BD")};
	JLabel[] etykieta = {new JLabel("X0"), new JLabel("X1")};
	JLabel[] etykietar = {new JLabel("  M       BM"),
			new JLabel("          M"), new JLabel("          S"),
			new JLabel("  S         M"), new JLabel("           S"),
			new JLabel("          D"), new JLabel("  D         S"),
			new JLabel("           D"), new JLabel("        BD")};
	JLabel etykietarr = new JLabel("X0/X1         M                     S                    D");
	JLabel etykietay = new JLabel("           Y");
	/**
	 * Konstruktor służy do stworzenia okna programu.
	 */
	public PawelDesigner2()
	{
		setTitle("PawelDesigner2");
		setSize(250,510);
		setLocation(350,0);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(panel);
		resetEtykiet();
		pasek.setStringPainted(true);
		for (int i=0;i<2;i++)
		{
			slider[i].setMajorTickSpacing(5);
			slider[i].setMinorTickSpacing(1);
			slider[i].setPaintLabels(true);
			slider[i].setPaintTicks(true);
			panel.add(etykieta[i]);
			panel.add(slider[i]);
		}
		panel.add(etykietarr);
		for (int i=0;i<9;i++)
		{
			panel.add(etykietar[i]);
			panel.add(r[i]);
		}
		for (int i=0;i<9;i++)
		{
			panel.add(er[i]);
		}
		panel.add(etykietay);
		panel.add(pasek);
		for (int i=0;i<3;i++)
		{
			przycisk[i].addActionListener(this);
			panel.add(przycisk[i]);
		}
	}
	private void resetEtykiet()
	{
		for (int i=0;i<9;i++)
		{
			r[i].setSelected(false);
			er[i].setForeground(Color.LIGHT_GRAY);
		}
	}
	/**
	 * Metoda actionPerformed() jest potrzebna do działania programu.
	 * @param zdarzenie Służy do obsługi zdarzeń.
	 */
	public void actionPerformed(ActionEvent zdarzenie)
	{
		if (zdarzenie.getSource()==(przycisk[0]) || zdarzenie.getSource()==(przycisk[1])
				|| zdarzenie.getSource()==(przycisk[2]))
		{
			resetEtykiet();
			/**
			 * Wejście X0.
			 */
			int x0 = slider[0].getValue();
			double x0M = 0, x0S = 0, x0D = 0;
			for (int i=0;i<11;i++)
			{
				if (x0 == i)
				{
					if (x0 < 6)
					{
						x0M = (5-i)/5.0;
						x0S = i/5.0;
						x0D = 0;
					}
					else
					{
						x0M = 0;
						x0S = (10-i)/5.0;
						x0D = (i-5)/5.0;
					}
				}
			}
			/**
			 * Wejście X1.
			 */
			int x1 = slider[1].getValue();
			double x1M = 0, x1S = 0, x1D = 0;
			for (int i=0;i<11;i++)
			{
				if (x1 == i)
				{
					if (x1 < 6)
					{
						x1M = (5-i)/5.0;
						x1S = i/5.0;
						x1D = 0;
					}
					else
					{
						x1M = 0;
						x1S = (10-i)/5.0;
						x1D = (i-5)/5.0;
					}
				}
			}
			/**
			 * Metoda wnioskowania MIN-MAX.
			 */
			if (zdarzenie.getSource()==(przycisk[0]))
			{
				double yBM = 0, yM1 = 0, yM2 = 0;
				double yS1 = 0, yS2 = 0, yS3 = 0;
				double yD1 = 0, yD2 = 0, yBD = 0;
				if (x0M > 0 && x1M > 0)
				{
					r[0].setSelected(true);
					er[0].setForeground(Color.BLACK);
					yBM = x0M;
					if (x1M < yBM)
					{
						yBM = x1M;
					}
				}
				if (x0M > 0 && x1S > 0)
				{
					r[1].setSelected(true);
					er[1].setForeground(Color.BLACK);
					yM1 = x0M;
					if (x1S < yM1)
					{
						yM1 = x1S;
					}
				}
				if (x0M > 0 && x1D > 0)
				{
					r[2].setSelected(true);
					er[2].setForeground(Color.BLACK);
					yS1 = x0M;
					if (x1D < yS1)
					{
						yS1 = x1D;
					}
				}
				if (x0S > 0 && x1M > 0)
				{
					r[3].setSelected(true);
					er[3].setForeground(Color.BLACK);
					yM2 = x0S;
					if (x1M < yM2)
					{
						yM2 = x1M;
					}
				}
				if (x0S > 0 && x1S > 0)
				{
					r[4].setSelected(true);
					er[4].setForeground(Color.BLACK);
					yS2 = x0S;
					if (x1S < yS2)
					{
						yS2 = x1S;
					}
				}
				if (x0S > 0 && x1D > 0)
				{
					r[5].setSelected(true);
					er[5].setForeground(Color.BLACK);
					yD1 = x0S;
					if (x1D < yD1)
					{
						yD1 = x1D;
					}
				}
				if (x0D > 0 && x1M > 0)
				{
					r[6].setSelected(true);
					er[6].setForeground(Color.BLACK);
					yS3 = x0D;
					if (x1M < yS3)
					{
						yS3 = x1M;
					}
				}
				if (x0D > 0 && x1S > 0)
				{
					r[7].setSelected(true);
					er[7].setForeground(Color.BLACK);
					yD2 = x0D;
					if (x1S < yD2)
					{
						yD2 = x1S;
					}
				}
				if (x0D > 0 && x1D > 0)
				{
					r[8].setSelected(true);
					er[8].setForeground(Color.BLACK);
					yBD = x0D;
					if (x1D < yBD)
					{
						yBD = x1D;
					}
				}
				double yM = 0, yS = 0, yD = 0;
				yM = yM1;
				if (yM2 > yM)
				{
					yM = yM2;
				}
				yS = yS1;
				if (yS2 > yS)
				{
					yS = yS2;
				}
				if (yS3 > yS)
				{
					yS = yS3;
				}
				yD = yD1;
				if (yD2 > yD)
				{
					yD = yD2;
				}
				/**
				 * Wyjście Y.
				 */
				int BM = 0, M = 25, S = 50, D = 75, BD = 100;
				double yy = (yBM*BM+yM*M+yS*S+yD*D+yBD*BD)/(yBM+yM+yS+yD+yBD);
				int y = (int) yy;
				pasek.setValue(y);
			}
			/**
			 * Metoda wnioskowania MAX-PROD.
			 */
			if (zdarzenie.getSource()==(przycisk[1]))
			{
				double yBM = 0, yM1 = 0, yM2 = 0;
				double yS1 = 0, yS2 = 0, yS3 = 0;
				double yD1 = 0, yD2 = 0, yBD = 0;
				if (x0M > 0 && x1M > 0)
				{
					r[0].setSelected(true);
					er[0].setForeground(Color.BLACK);
					yBM = x0M * x1M;
				}
				if (x0M > 0 && x1S > 0)
				{
					r[1].setSelected(true);
					er[1].setForeground(Color.BLACK);
					yM1 = x0M * x1S;
				}
				if (x0M > 0 && x1D > 0)
				{
					r[2].setSelected(true);
					er[2].setForeground(Color.BLACK);
					yS1 = x0M * x1D;
				}
				if (x0S > 0 && x1M > 0)
				{
					r[3].setSelected(true);
					er[3].setForeground(Color.BLACK);
					yM2 = x0S * x1M;
				}
				if (x0S > 0 && x1S > 0)
				{
					r[4].setSelected(true);
					er[4].setForeground(Color.BLACK);
					yS2 = x0S * x1S;
				}
				if (x0S > 0 && x1D > 0)
				{
					r[5].setSelected(true);
					er[5].setForeground(Color.BLACK);
					yD1 = x0S * x1D;
				}
				if (x0D > 0 && x1M > 0)
				{
					r[6].setSelected(true);
					er[6].setForeground(Color.BLACK);
					yS3 = x0D * x1M;
				}
				if (x0D > 0 && x1S > 0)
				{
					r[7].setSelected(true);
					er[7].setForeground(Color.BLACK);
					yD2 = x0D * x1S;
				}
				if (x0D > 0 && x1D > 0)
				{
					r[8].setSelected(true);
					er[8].setForeground(Color.BLACK);
					yBD = x0D * x1D;
				}
				double yM = 0, yS = 0, yD = 0;
				yM = yM1;
				if (yM2 > yM)
				{
					yM = yM2;
				}
				yS = yS1;
				if (yS2 > yS)
				{
					yS = yS2;
				}
				if (yS3 > yS)
				{
					yS = yS3;
				}
				yD = yD1;
				if (yD2 > yD)
				{
					yD = yD2;
				}
				/**
				 * Wyjście Y.
				 */
				int BM = 0, M = 25, S = 50, D = 75, BD = 100;
				double yy = (yBM*BM+yM*M+yS*S+yD*D+yBD*BD)/(yBM+yM+yS+yD+yBD);
				int y = (int) yy;
				pasek.setValue(y);
			}
			/**
			 * Metoda wnioskowania MAX-AV.
			 */
			if (zdarzenie.getSource()==(przycisk[2]))
			{
				double yBM = 0, yM1 = 0, yM2 = 0;
				double yS1 = 0, yS2 = 0, yS3 = 0;
				double yD1 = 0, yD2 = 0, yBD = 0;
				if (x0M > 0 && x1M > 0)
				{
					r[0].setSelected(true);
					er[0].setForeground(Color.BLACK);
					yBM = x0M + x1M;
				}
				if (x0M > 0 && x1S > 0)
				{
					r[1].setSelected(true);
					er[1].setForeground(Color.BLACK);
					yM1 = x0M + x1S;
				}
				if (x0M > 0 && x1D > 0)
				{
					r[2].setSelected(true);
					er[2].setForeground(Color.BLACK);
					yS1 = x0M + x1D;
				}
				if (x0S > 0 && x1M > 0)
				{
					r[3].setSelected(true);
					er[3].setForeground(Color.BLACK);
					yM2 = x0S + x1M;
				}
				if (x0S > 0 && x1S > 0)
				{
					r[4].setSelected(true);
					er[4].setForeground(Color.BLACK);
					yS2 = x0S + x1S;
				}
				if (x0S > 0 && x1D > 0)
				{
					r[5].setSelected(true);
					er[5].setForeground(Color.BLACK);
					yD1 = x0S + x1D;
				}
				if (x0D > 0 && x1M > 0)
				{
					r[6].setSelected(true);
					er[6].setForeground(Color.BLACK);
					yS3 = x0D + x1M;
				}
				if (x0D > 0 && x1S > 0)
				{
					r[7].setSelected(true);
					er[7].setForeground(Color.BLACK);
					yD2 = x0D + x1S;
				}
				if (x0D > 0 && x1D > 0)
				{
					r[8].setSelected(true);
					er[8].setForeground(Color.BLACK);
					yBD = x0D + x1D;
				}
				double yM = 0, yS = 0, yD = 0;
				yM = yM1;
				if (yM2 > yM)
				{
					yM = yM2;
				}
				yS = yS1;
				if (yS2 > yS)
				{
					yS = yS2;
				}
				if (yS3 > yS)
				{
					yS = yS3;
				}
				yD = yD1;
				if (yD2 > yD)
				{
					yD = yD2;
				}
				yBM = yBM/2;
				yM = yM/2;
				yS = yS/2;
				yD = yD/2;
				yBD = yBD/2;
				/**
				 * Wyjście Y.
				 */
				int BM = 0, M = 25, S = 50, D = 75, BD = 100;
				double yy = (yBM*BM+yM*M+yS*S+yD*D+yBD*BD)/(yBM+yM+yS+yD+yBD);
				int y = (int) yy;
				pasek.setValue(y);
			}
		}
	}
	/**
	 * Metoda main() inicjuje działanie programu.
	 * @param args Tablica argumentów.
	 */
	public static void main(String[] args)
	{
		PawelDesigner2 program = new PawelDesigner2();
		program.setVisible(true);
	}
}
