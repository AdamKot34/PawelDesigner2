package app; //nazwa pakietu

import java.awt.event.*; //import bibliotek
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
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
			new JButton("MAX-AV"), new JButton("Zapisz")};
	JLabel[] etykieta = {new JLabel("X0"), new JLabel("X1")};
	JLabel etykietay = new JLabel("           Y");
	static JTextArea reguly = new JTextArea(9,20);
	/**
	 * Konstruktor służy do stworzenia okna programu.
	 */
	public PawelDesigner2()
	{
		setTitle("PawelDesigner2");
		setSize(250,375);
		setLocation(350,0);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(panel);
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
		panel.add(reguly);
		panel.add(etykietay);
		panel.add(pasek);
		for (int i=0;i<4;i++)
		{
			przycisk[i].addActionListener(this);
			panel.add(przycisk[i]);
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
					yBM = x0M;
					if (x1M < yBM)
					{
						yBM = x1M;
					}
				}
				if (x0M > 0 && x1S > 0)
				{
					yM1 = x0M;
					if (x1S < yM1)
					{
						yM1 = x1S;
					}
				}
				if (x0M > 0 && x1D > 0)
				{
					yS1 = x0M;
					if (x1D < yS1)
					{
						yS1 = x1D;
					}
				}
				if (x0S > 0 && x1M > 0)
				{
					yM2 = x0S;
					if (x1M < yM2)
					{
						yM2 = x1M;
					}
				}
				if (x0S > 0 && x1S > 0)
				{
					yS2 = x0S;
					if (x1S < yS2)
					{
						yS2 = x1S;
					}
				}
				if (x0S > 0 && x1D > 0)
				{
					yD1 = x0S;
					if (x1D < yD1)
					{
						yD1 = x1D;
					}
				}
				if (x0D > 0 && x1M > 0)
				{
					yS3 = x0D;
					if (x1M < yS3)
					{
						yS3 = x1M;
					}
				}
				if (x0D > 0 && x1S > 0)
				{
					yD2 = x0D;
					if (x1S < yD2)
					{
						yD2 = x1S;
					}
				}
				if (x0D > 0 && x1D > 0)
				{
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
					yBM = x0M * x1M;
				}
				if (x0M > 0 && x1S > 0)
				{
					yM1 = x0M * x1S;
				}
				if (x0M > 0 && x1D > 0)
				{
					yS1 = x0M * x1D;
				}
				if (x0S > 0 && x1M > 0)
				{
					yM2 = x0S * x1M;
				}
				if (x0S > 0 && x1S > 0)
				{
					yS2 = x0S * x1S;
				}
				if (x0S > 0 && x1D > 0)
				{
					yD1 = x0S * x1D;
				}
				if (x0D > 0 && x1M > 0)
				{
					yS3 = x0D * x1M;
				}
				if (x0D > 0 && x1S > 0)
				{
					yD2 = x0D * x1S;
				}
				if (x0D > 0 && x1D > 0)
				{
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
					yBM = x0M + x1M;
				}
				if (x0M > 0 && x1S > 0)
				{
					yM1 = x0M + x1S;
				}
				if (x0M > 0 && x1D > 0)
				{
					yS1 = x0M + x1D;
				}
				if (x0S > 0 && x1M > 0)
				{
					yM2 = x0S + x1M;
				}
				if (x0S > 0 && x1S > 0)
				{
					yS2 = x0S + x1S;
				}
				if (x0S > 0 && x1D > 0)
				{
					yD1 = x0S + x1D;
				}
				if (x0D > 0 && x1M > 0)
				{
					yS3 = x0D + x1M;
				}
				if (x0D > 0 && x1S > 0)
				{
					yD2 = x0D + x1S;
				}
				if (x0D > 0 && x1D > 0)
				{
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
		/**
		 * Zapis reguł rozmytych.
		 */
		if (zdarzenie.getSource()==(przycisk[3]))
		{
			try
			{
				PrintWriter plik = new PrintWriter("reguly.txt");
				String dane = reguly.getText();
				plik.println(dane);
				plik.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
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
		/**
		 * Odczyt reguł rozmytych.
		 */
		try
		{
			File plik = new File("reguly.txt");
			Scanner skaner = new Scanner(plik);
			String dane = "";
			int i = 0;
			while (skaner.hasNextLine())
            {
                if (i == 0)
                {
                	dane = dane + skaner.nextLine();
                	i = 1;
                }
                else
                {
                	dane = dane + "\n" + skaner.nextLine();
                }
            }
			reguly.setText(dane);
			skaner.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
