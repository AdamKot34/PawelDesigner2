package app; //nazwa pakietu

import java.awt.*; //import bibliotek
import java.awt.event.*;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.*;
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
	static JLabel[] etykietar = {new JLabel("R0"), new JLabel("R1"), new JLabel("R2"),
			new JLabel("R3"), new JLabel("R4"), new JLabel("R5"),
			new JLabel("R6"), new JLabel("R7"), new JLabel("R8")};
	static JTextArea reguly = new JTextArea(9,20);
	/**
	 * Konstruktor służy do stworzenia okna programu.
	 */
	public PawelDesigner2()
	{
		setTitle("PawelDesigner2");
		setSize(250,390);
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
		for (int i=0;i<9;i++)
		{
			panel.add(etykietar[i]);
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
	private static void resetEtykiet()
	{
		for (int i=0;i<9;i++)
		{
			etykietar[i].setForeground(Color.LIGHT_GRAY);
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
			System.out.println("---------");
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
					System.out.println("x0M = " +x0M);
					System.out.println("x0S = " +x0S);
					System.out.println("x0D = " +x0D);
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
					System.out.println("x1M = " +x1M);
					System.out.println("x1S = " +x1S);
					System.out.println("x1D = " +x1D);
				}
			}
			/**
			 * Metoda wnioskowania MIN-MAX.
			 */
			if (zdarzenie.getSource()==(przycisk[0]))
			{
				try
				{
					double yY[] = {0,0,0,0,0,0,0,0,0};
					double yYBM[] = {0,0,0,0,0,0,0,0,0};
					double yYM[] = {0,0,0,0,0,0,0,0,0};
					double yYS[] = {0,0,0,0,0,0,0,0,0};
					double yYD[] = {0,0,0,0,0,0,0,0,0};
					double yYBD[] = {0,0,0,0,0,0,0,0,0};
					if (x0M > 0 && x1M > 0)
					{
						etykietar[0].setForeground(Color.BLACK);
						yY[0] = x0M;
						if (x1M < yY[0])
						{
							yY[0] = x1M;
						}
						String rr0 = Files.readAllLines(Paths.get("reguly.txt")).get(0);
						String r0 = rr0.substring(34);
						if (r0.equals("BM"))
						{
							yYBM[0] = yY[0];
						}
						if (r0.equals("M"))
						{
							yYM[0] = yY[0];
						}
						if (r0.equals("S"))
						{
							yYS[0] = yY[0];
						}
						if (r0.equals("D"))
						{
							yYD[0] = yY[0];
						}
						if (r0.equals("BD"))
						{
							yYBD[0] = yY[0];
						}
					}
					if (x0M > 0 && x1S > 0)
					{
						etykietar[1].setForeground(Color.BLACK);
						yY[1] = x0M;
						if (x1S < yY[1])
						{
							yY[1] = x1S;
						}
						String rr1 = Files.readAllLines(Paths.get("reguly.txt")).get(1);
						String r1 = rr1.substring(34);
						if (r1.equals("BM"))
						{
							yYBM[1] = yY[1];
						}
						if (r1.equals("M"))
						{
							yYM[1] = yY[1];
						}
						if (r1.equals("S"))
						{
							yYS[1] = yY[1];
						}
						if (r1.equals("D"))
						{
							yYD[1] = yY[1];
						}
						if (r1.equals("BD"))
						{
							yYBD[1] = yY[1];
						}
					}
					if (x0M > 0 && x1D > 0)
					{
						etykietar[2].setForeground(Color.BLACK);
						yY[2] = x0M;
						if (x1D < yY[2])
						{
							yY[2] = x1D;
						}
						String rr2 = Files.readAllLines(Paths.get("reguly.txt")).get(2);
						String r2 = rr2.substring(34);
						if (r2.equals("BM"))
						{
							yYBM[2] = yY[2];
						}
						if (r2.equals("M"))
						{
							yYM[2] = yY[2];
						}
						if (r2.equals("S"))
						{
							yYS[2] = yY[2];
						}
						if (r2.equals("D"))
						{
							yYD[2] = yY[2];
						}
						if (r2.equals("BD"))
						{
							yYBD[2] = yY[2];
						}
					}
					if (x0S > 0 && x1M > 0)
					{
						etykietar[3].setForeground(Color.BLACK);
						yY[3] = x0S;
						if (x1M < yY[3])
						{
							yY[3] = x1M;
						}
						String rr3 = Files.readAllLines(Paths.get("reguly.txt")).get(3);
						String r3 = rr3.substring(34);
						if (r3.equals("BM"))
						{
							yYBM[3] = yY[3];
						}
						if (r3.equals("M"))
						{
							yYM[3] = yY[3];
						}
						if (r3.equals("S"))
						{
							yYS[3] = yY[3];
						}
						if (r3.equals("D"))
						{
							yYD[3] = yY[3];
						}
						if (r3.equals("BD"))
						{
							yYBD[3] = yY[3];
						}
					}
					if (x0S > 0 && x1S > 0)
					{
						etykietar[4].setForeground(Color.BLACK);
						yY[4] = x0S;
						if (x1S < yY[4])
						{
							yY[4] = x1S;
						}
						String rr4 = Files.readAllLines(Paths.get("reguly.txt")).get(4);
						String r4 = rr4.substring(34);
						if (r4.equals("BM"))
						{
							yYBM[4] = yY[4];
						}
						if (r4.equals("M"))
						{
							yYM[4] = yY[4];
						}
						if (r4.equals("S"))
						{
							yYS[4] = yY[4];
						}
						if (r4.equals("D"))
						{
							yYD[4] = yY[4];
						}
						if (r4.equals("BD"))
						{
							yYBD[4] = yY[4];
						}
					}
					if (x0S > 0 && x1D > 0)
					{
						etykietar[5].setForeground(Color.BLACK);
						yY[5] = x0S;
						if (x1D < yY[5])
						{
							yY[5] = x1D;
						}
						String rr5 = Files.readAllLines(Paths.get("reguly.txt")).get(5);
						String r5 = rr5.substring(34);
						if (r5.equals("BM"))
						{
							yYBM[5] = yY[5];
						}
						if (r5.equals("M"))
						{
							yYM[5] = yY[5];
						}
						if (r5.equals("S"))
						{
							yYS[5] = yY[5];
						}
						if (r5.equals("D"))
						{
							yYD[5] = yY[5];
						}
						if (r5.equals("BD"))
						{
							yYBD[5] = yY[5];
						}
					}
					if (x0D > 0 && x1M > 0)
					{
						etykietar[6].setForeground(Color.BLACK);
						yY[6] = x0D;
						if (x1M < yY[6])
						{
							yY[6] = x1M;
						}
						String rr6 = Files.readAllLines(Paths.get("reguly.txt")).get(6);
						String r6 = rr6.substring(34);
						if (r6.equals("BM"))
						{
							yYBM[6] = yY[6];
						}
						if (r6.equals("M"))
						{
							yYM[6] = yY[6];
						}
						if (r6.equals("S"))
						{
							yYS[6] = yY[6];
						}
						if (r6.equals("D"))
						{
							yYD[6] = yY[6];
						}
						if (r6.equals("BD"))
						{
							yYBD[6] = yY[6];
						}
					}
					if (x0D > 0 && x1S > 0)
					{
						etykietar[7].setForeground(Color.BLACK);
						yY[7] = x0D;
						if (x1S < yY[7])
						{
							yY[7] = x1S;
						}
						String rr7 = Files.readAllLines(Paths.get("reguly.txt")).get(7);
						String r7 = rr7.substring(34);
						if (r7.equals("BM"))
						{
							yYBM[7] = yY[7];
						}
						if (r7.equals("M"))
						{
							yYM[7] = yY[7];
						}
						if (r7.equals("S"))
						{
							yYS[7] = yY[7];
						}
						if (r7.equals("D"))
						{
							yYD[7] = yY[7];
						}
						if (r7.equals("BD"))
						{
							yYBD[7] = yY[7];
						}
					}
					if (x0D > 0 && x1D > 0)
					{
						etykietar[8].setForeground(Color.BLACK);
						yY[8] = x0D;
						if (x1D < yY[8])
						{
							yY[8] = x1D;
						}
						String rr8 = Files.readAllLines(Paths.get("reguly.txt")).get(8);
						String r8 = rr8.substring(34);
						if (r8.equals("BM"))
						{
							yYBM[8] = yY[8];
						}
						if (r8.equals("M"))
						{
							yYM[8] = yY[8];
						}
						if (r8.equals("S"))
						{
							yYS[8] = yY[8];
						}
						if (r8.equals("D"))
						{
							yYD[8] = yY[8];
						}
						if (r8.equals("BD"))
						{
							yYBD[8] = yY[8];
						}
					}
					double yBM = 0, yM = 0, yS = 0, yD = 0, yBD = 0;
					for (int i=0;i<9;i++)
					{
						if (yYBM[i] > yBM)
						{
							yBM = yYBM[i];
						}
					}
					for (int i=0;i<9;i++)
					{
						if (yYM[i] > yM)
						{
							yM = yYM[i];
						}
					}
					for (int i=0;i<9;i++)
					{
						if (yYS[i] > yS)
						{
							yS = yYS[i];
						}
					}
					for (int i=0;i<9;i++)
					{
						if (yYD[i] > yD)
						{
							yD = yYD[i];
						}
					}
					for (int i=0;i<9;i++)
					{
						if (yYBD[i] > yBD)
						{
							yBD = yYBD[i];
						}
					}
					System.out.println("yBM = " +yBM);
					System.out.println("yM = " +yM);
					System.out.println("yS = " +yS);
					System.out.println("yD = " +yD);
					System.out.println("yBD = " +yBD);
					/**
					 * Wyjście Y.
					 */
					int BM = 0, M = 25, S = 50, D = 75, BD = 100;
					double yy = (yBM*BM+yM*M+yS*S+yD*D+yBD*BD)/(yBM+yM+yS+yD+yBD);
					int y = (int) yy;
					pasek.setValue(y);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			/**
			 * Metoda wnioskowania MAX-PROD.
			 */
			if (zdarzenie.getSource()==(przycisk[1]))
			{
				try
				{
					double yY[] = {0,0,0,0,0,0,0,0,0};
					double yYBM[] = {0,0,0,0,0,0,0,0,0};
					double yYM[] = {0,0,0,0,0,0,0,0,0};
					double yYS[] = {0,0,0,0,0,0,0,0,0};
					double yYD[] = {0,0,0,0,0,0,0,0,0};
					double yYBD[] = {0,0,0,0,0,0,0,0,0};
					if (x0M > 0 && x1M > 0)
					{
						etykietar[0].setForeground(Color.BLACK);
						yY[0] = x0M * x1M;
						String rr0 = Files.readAllLines(Paths.get("reguly.txt")).get(0);
						String r0 = rr0.substring(34);
						if (r0.equals("BM"))
						{
							yYBM[0] = yY[0];
						}
						if (r0.equals("M"))
						{
							yYM[0] = yY[0];
						}
						if (r0.equals("S"))
						{
							yYS[0] = yY[0];
						}
						if (r0.equals("D"))
						{
							yYD[0] = yY[0];
						}
						if (r0.equals("BD"))
						{
							yYBD[0] = yY[0];
						}
					}
					if (x0M > 0 && x1S > 0)
					{
						etykietar[1].setForeground(Color.BLACK);
						yY[1] = x0M * x1S;
						String rr1 = Files.readAllLines(Paths.get("reguly.txt")).get(1);
						String r1 = rr1.substring(34);
						if (r1.equals("BM"))
						{
							yYBM[1] = yY[1];
						}
						if (r1.equals("M"))
						{
							yYM[1] = yY[1];
						}
						if (r1.equals("S"))
						{
							yYS[1] = yY[1];
						}
						if (r1.equals("D"))
						{
							yYD[1] = yY[1];
						}
						if (r1.equals("BD"))
						{
							yYBD[1] = yY[1];
						}
					}
					if (x0M > 0 && x1D > 0)
					{
						etykietar[2].setForeground(Color.BLACK);
						yY[2] = x0M * x1D;
						String rr2 = Files.readAllLines(Paths.get("reguly.txt")).get(2);
						String r2 = rr2.substring(34);
						if (r2.equals("BM"))
						{
							yYBM[2] = yY[2];
						}
						if (r2.equals("M"))
						{
							yYM[2] = yY[2];
						}
						if (r2.equals("S"))
						{
							yYS[2] = yY[2];
						}
						if (r2.equals("D"))
						{
							yYD[2] = yY[2];
						}
						if (r2.equals("BD"))
						{
							yYBD[2] = yY[2];
						}
					}
					if (x0S > 0 && x1M > 0)
					{
						etykietar[3].setForeground(Color.BLACK);
						yY[3] = x0S * x1M;
						String rr3 = Files.readAllLines(Paths.get("reguly.txt")).get(3);
						String r3 = rr3.substring(34);
						if (r3.equals("BM"))
						{
							yYBM[3] = yY[3];
						}
						if (r3.equals("M"))
						{
							yYM[3] = yY[3];
						}
						if (r3.equals("S"))
						{
							yYS[3] = yY[3];
						}
						if (r3.equals("D"))
						{
							yYD[3] = yY[3];
						}
						if (r3.equals("BD"))
						{
							yYBD[3] = yY[3];
						}
					}
					if (x0S > 0 && x1S > 0)
					{
						etykietar[4].setForeground(Color.BLACK);
						yY[4] = x0S * x1S;
						String rr4 = Files.readAllLines(Paths.get("reguly.txt")).get(4);
						String r4 = rr4.substring(34);
						if (r4.equals("BM"))
						{
							yYBM[4] = yY[4];
						}
						if (r4.equals("M"))
						{
							yYM[4] = yY[4];
						}
						if (r4.equals("S"))
						{
							yYS[4] = yY[4];
						}
						if (r4.equals("D"))
						{
							yYD[4] = yY[4];
						}
						if (r4.equals("BD"))
						{
							yYBD[4] = yY[4];
						}
					}
					if (x0S > 0 && x1D > 0)
					{
						etykietar[5].setForeground(Color.BLACK);
						yY[5] = x0S * x1D;
						String rr5 = Files.readAllLines(Paths.get("reguly.txt")).get(5);
						String r5 = rr5.substring(34);
						if (r5.equals("BM"))
						{
							yYBM[5] = yY[5];
						}
						if (r5.equals("M"))
						{
							yYM[5] = yY[5];
						}
						if (r5.equals("S"))
						{
							yYS[5] = yY[5];
						}
						if (r5.equals("D"))
						{
							yYD[5] = yY[5];
						}
						if (r5.equals("BD"))
						{
							yYBD[5] = yY[5];
						}
					}
					if (x0D > 0 && x1M > 0)
					{
						etykietar[6].setForeground(Color.BLACK);
						yY[6] = x0D * x1M;
						String rr6 = Files.readAllLines(Paths.get("reguly.txt")).get(6);
						String r6 = rr6.substring(34);
						if (r6.equals("BM"))
						{
							yYBM[6] = yY[6];
						}
						if (r6.equals("M"))
						{
							yYM[6] = yY[6];
						}
						if (r6.equals("S"))
						{
							yYS[6] = yY[6];
						}
						if (r6.equals("D"))
						{
							yYD[6] = yY[6];
						}
						if (r6.equals("BD"))
						{
							yYBD[6] = yY[6];
						}
					}
					if (x0D > 0 && x1S > 0)
					{
						etykietar[7].setForeground(Color.BLACK);
						yY[7] = x0D * x1S;
						String rr7 = Files.readAllLines(Paths.get("reguly.txt")).get(7);
						String r7 = rr7.substring(34);
						if (r7.equals("BM"))
						{
							yYBM[7] = yY[7];
						}
						if (r7.equals("M"))
						{
							yYM[7] = yY[7];
						}
						if (r7.equals("S"))
						{
							yYS[7] = yY[7];
						}
						if (r7.equals("D"))
						{
							yYD[7] = yY[7];
						}
						if (r7.equals("BD"))
						{
							yYBD[7] = yY[7];
						}
					}
					if (x0D > 0 && x1D > 0)
					{
						etykietar[8].setForeground(Color.BLACK);
						yY[8] = x0D * x1D;
						String rr8 = Files.readAllLines(Paths.get("reguly.txt")).get(8);
						String r8 = rr8.substring(34);
						if (r8.equals("BM"))
						{
							yYBM[8] = yY[8];
						}
						if (r8.equals("M"))
						{
							yYM[8] = yY[8];
						}
						if (r8.equals("S"))
						{
							yYS[8] = yY[8];
						}
						if (r8.equals("D"))
						{
							yYD[8] = yY[8];
						}
						if (r8.equals("BD"))
						{
							yYBD[8] = yY[8];
						}
					}
					double yBM = 0, yM = 0, yS = 0, yD = 0, yBD = 0;
					for (int i=0;i<9;i++)
					{
						if (yYBM[i] > yBM)
						{
							yBM = yYBM[i];
						}
					}
					for (int i=0;i<9;i++)
					{
						if (yYM[i] > yM)
						{
							yM = yYM[i];
						}
					}
					for (int i=0;i<9;i++)
					{
						if (yYS[i] > yS)
						{
							yS = yYS[i];
						}
					}
					for (int i=0;i<9;i++)
					{
						if (yYD[i] > yD)
						{
							yD = yYD[i];
						}
					}
					for (int i=0;i<9;i++)
					{
						if (yYBD[i] > yBD)
						{
							yBD = yYBD[i];
						}
					}
					System.out.println("yBM = " +yBM);
					System.out.println("yM = " +yM);
					System.out.println("yS = " +yS);
					System.out.println("yD = " +yD);
					System.out.println("yBD = " +yBD);
					/**
					 * Wyjście Y.
					 */
					int BM = 0, M = 25, S = 50, D = 75, BD = 100;
					double yy = (yBM*BM+yM*M+yS*S+yD*D+yBD*BD)/(yBM+yM+yS+yD+yBD);
					int y = (int) yy;
					pasek.setValue(y);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			/**
			 * Metoda wnioskowania MAX-AV.
			 */
			if (zdarzenie.getSource()==(przycisk[2]))
			{
				try
				{
					double yY[] = {0,0,0,0,0,0,0,0,0};
					double yYBM[] = {0,0,0,0,0,0,0,0,0};
					double yYM[] = {0,0,0,0,0,0,0,0,0};
					double yYS[] = {0,0,0,0,0,0,0,0,0};
					double yYD[] = {0,0,0,0,0,0,0,0,0};
					double yYBD[] = {0,0,0,0,0,0,0,0,0};
					if (x0M > 0 && x1M > 0)
					{
						etykietar[0].setForeground(Color.BLACK);
						yY[0] = x0M + x1M;
						String rr0 = Files.readAllLines(Paths.get("reguly.txt")).get(0);
						String r0 = rr0.substring(34);
						if (r0.equals("BM"))
						{
							yYBM[0] = yY[0];
						}
						if (r0.equals("M"))
						{
							yYM[0] = yY[0];
						}
						if (r0.equals("S"))
						{
							yYS[0] = yY[0];
						}
						if (r0.equals("D"))
						{
							yYD[0] = yY[0];
						}
						if (r0.equals("BD"))
						{
							yYBD[0] = yY[0];
						}
					}
					if (x0M > 0 && x1S > 0)
					{
						etykietar[1].setForeground(Color.BLACK);
						yY[1] = x0M + x1S;
						String rr1 = Files.readAllLines(Paths.get("reguly.txt")).get(1);
						String r1 = rr1.substring(34);
						if (r1.equals("BM"))
						{
							yYBM[1] = yY[1];
						}
						if (r1.equals("M"))
						{
							yYM[1] = yY[1];
						}
						if (r1.equals("S"))
						{
							yYS[1] = yY[1];
						}
						if (r1.equals("D"))
						{
							yYD[1] = yY[1];
						}
						if (r1.equals("BD"))
						{
							yYBD[1] = yY[1];
						}
					}
					if (x0M > 0 && x1D > 0)
					{
						etykietar[2].setForeground(Color.BLACK);
						yY[2] = x0M + x1D;
						String rr2 = Files.readAllLines(Paths.get("reguly.txt")).get(2);
						String r2 = rr2.substring(34);
						if (r2.equals("BM"))
						{
							yYBM[2] = yY[2];
						}
						if (r2.equals("M"))
						{
							yYM[2] = yY[2];
						}
						if (r2.equals("S"))
						{
							yYS[2] = yY[2];
						}
						if (r2.equals("D"))
						{
							yYD[2] = yY[2];
						}
						if (r2.equals("BD"))
						{
							yYBD[2] = yY[2];
						}
					}
					if (x0S > 0 && x1M > 0)
					{
						etykietar[3].setForeground(Color.BLACK);
						yY[3] = x0S + x1M;
						String rr3 = Files.readAllLines(Paths.get("reguly.txt")).get(3);
						String r3 = rr3.substring(34);
						if (r3.equals("BM"))
						{
							yYBM[3] = yY[3];
						}
						if (r3.equals("M"))
						{
							yYM[3] = yY[3];
						}
						if (r3.equals("S"))
						{
							yYS[3] = yY[3];
						}
						if (r3.equals("D"))
						{
							yYD[3] = yY[3];
						}
						if (r3.equals("BD"))
						{
							yYBD[3] = yY[3];
						}
					}
					if (x0S > 0 && x1S > 0)
					{
						etykietar[4].setForeground(Color.BLACK);
						yY[4] = x0S + x1S;
						String rr4 = Files.readAllLines(Paths.get("reguly.txt")).get(4);
						String r4 = rr4.substring(34);
						if (r4.equals("BM"))
						{
							yYBM[4] = yY[4];
						}
						if (r4.equals("M"))
						{
							yYM[4] = yY[4];
						}
						if (r4.equals("S"))
						{
							yYS[4] = yY[4];
						}
						if (r4.equals("D"))
						{
							yYD[4] = yY[4];
						}
						if (r4.equals("BD"))
						{
							yYBD[4] = yY[4];
						}
					}
					if (x0S > 0 && x1D > 0)
					{
						etykietar[5].setForeground(Color.BLACK);
						yY[5] = x0S + x1D;
						String rr5 = Files.readAllLines(Paths.get("reguly.txt")).get(5);
						String r5 = rr5.substring(34);
						if (r5.equals("BM"))
						{
							yYBM[5] = yY[5];
						}
						if (r5.equals("M"))
						{
							yYM[5] = yY[5];
						}
						if (r5.equals("S"))
						{
							yYS[5] = yY[5];
						}
						if (r5.equals("D"))
						{
							yYD[5] = yY[5];
						}
						if (r5.equals("BD"))
						{
							yYBD[5] = yY[5];
						}
					}
					if (x0D > 0 && x1M > 0)
					{
						etykietar[6].setForeground(Color.BLACK);
						yY[6] = x0D + x1M;
						String rr6 = Files.readAllLines(Paths.get("reguly.txt")).get(6);
						String r6 = rr6.substring(34);
						if (r6.equals("BM"))
						{
							yYBM[6] = yY[6];
						}
						if (r6.equals("M"))
						{
							yYM[6] = yY[6];
						}
						if (r6.equals("S"))
						{
							yYS[6] = yY[6];
						}
						if (r6.equals("D"))
						{
							yYD[6] = yY[6];
						}
						if (r6.equals("BD"))
						{
							yYBD[6] = yY[6];
						}
					}
					if (x0D > 0 && x1S > 0)
					{
						etykietar[7].setForeground(Color.BLACK);
						yY[7] = x0D + x1S;
						String rr7 = Files.readAllLines(Paths.get("reguly.txt")).get(7);
						String r7 = rr7.substring(34);
						if (r7.equals("BM"))
						{
							yYBM[7] = yY[7];
						}
						if (r7.equals("M"))
						{
							yYM[7] = yY[7];
						}
						if (r7.equals("S"))
						{
							yYS[7] = yY[7];
						}
						if (r7.equals("D"))
						{
							yYD[7] = yY[7];
						}
						if (r7.equals("BD"))
						{
							yYBD[7] = yY[7];
						}
					}
					if (x0D > 0 && x1D > 0)
					{
						etykietar[8].setForeground(Color.BLACK);
						yY[8] = x0D + x1D;
						String rr8 = Files.readAllLines(Paths.get("reguly.txt")).get(8);
						String r8 = rr8.substring(34);
						if (r8.equals("BM"))
						{
							yYBM[8] = yY[8];
						}
						if (r8.equals("M"))
						{
							yYM[8] = yY[8];
						}
						if (r8.equals("S"))
						{
							yYS[8] = yY[8];
						}
						if (r8.equals("D"))
						{
							yYD[8] = yY[8];
						}
						if (r8.equals("BD"))
						{
							yYBD[8] = yY[8];
						}
					}
					double yBM = 0, yM = 0, yS = 0, yD = 0, yBD = 0;
					for (int i=0;i<9;i++)
					{
						if (yYBM[i] > yBM)
						{
							yBM = yYBM[i];
						}
					}
					for (int i=0;i<9;i++)
					{
						if (yYM[i] > yM)
						{
							yM = yYM[i];
						}
					}
					for (int i=0;i<9;i++)
					{
						if (yYS[i] > yS)
						{
							yS = yYS[i];
						}
					}
					for (int i=0;i<9;i++)
					{
						if (yYD[i] > yD)
						{
							yD = yYD[i];
						}
					}
					for (int i=0;i<9;i++)
					{
						if (yYBD[i] > yBD)
						{
							yBD = yYBD[i];
						}
					}
					yBM = yBM/2;
					yM = yM/2;
					yS = yS/2;
					yD = yD/2;
					yBD = yBD/2;
					System.out.println("yBM = " +yBM);
					System.out.println("yM = " +yM);
					System.out.println("yS = " +yS);
					System.out.println("yD = " +yD);
					System.out.println("yBD = " +yBD);
					/**
					 * Wyjście Y.
					 */
					int BM = 0, M = 25, S = 50, D = 75, BD = 100;
					double yy = (yBM*BM+yM*M+yS*S+yD*D+yBD*BD)/(yBM+yM+yS+yD+yBD);
					int y = (int) yy;
					pasek.setValue(y);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
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
		resetEtykiet();
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
