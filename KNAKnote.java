import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class KNAKnote extends JFrame implements ActionListener {

	JMenuBar menuBar;
	JMenu menuPlik, menuNarz�dzia, menuPomoc, menuKalkulatory, menuSzukaj, menuWygl�d;
	JMenuItem mOtw�rz, mZapisz, mWyj�cie, mNarz1, mMetryStopy, mCelToFar, mSzukaj, mKolor, mRozmiar, mOProgramie, mpKopiuj, mpWklej, mpDo��cz;
	JCheckBoxMenuItem chZawijanie;
	
	JTextArea notatnik;
	JPopupMenu popup;
	//JComboBox colorCombo;
	
	String tekstSchowek;

	public KNAKnote() {
		setTitle("KNAKnote");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		menuBar = new JMenuBar();
		menuPlik = new JMenu("Plik");
		mOtw�rz = new JMenuItem("Otw�rz", 'O');
		mZapisz = new JMenuItem("Zapisz");
		mWyj�cie = new JMenuItem("Wyj�cie");
		mWyj�cie.addActionListener(this);
		mWyj�cie.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));

		menuPlik.add(mOtw�rz);
		mOtw�rz.addActionListener(this);
		menuPlik.add(mZapisz);
		mZapisz.addActionListener(this);
		menuPlik.addSeparator();
		menuPlik.add(mWyj�cie);

		menuNarz�dzia = new JMenu("Narz�dzia");
		mNarz1 = new JMenuItem("Narz�dzie1");
		mNarz1.setEnabled(false);
		mMetryStopy = new JMenuItem("Metry => stopy");
		mMetryStopy.addActionListener(this);

		menuNarz�dzia.add(mNarz1);
		
		menuKalkulatory = new JMenu("Kalkulatory");
		mCelToFar = new JMenuItem("Celsjusz / Fahrenheit");
		menuKalkulatory.add(mMetryStopy);
		menuKalkulatory.add(mCelToFar);
		
		menuNarz�dzia.add(menuKalkulatory);
		
		menuSzukaj = new JMenu("Znajd�");
		mSzukaj = new JMenuItem("Znajd�");
		menuSzukaj.add(mSzukaj);
		mSzukaj.addActionListener(this);
		

		menuPomoc = new JMenu("Pomoc");
		mOProgramie = new JMenuItem("O programie");
		mOProgramie.addActionListener(this);
		menuPomoc.add(mOProgramie);
		
		menuWygl�d = new JMenu ("Wygl�d");
		mKolor = new JMenuItem("Kolor tekstu");
		menuWygl�d.add(mKolor);
		mKolor.addActionListener(this);
		mRozmiar = new JMenuItem("Rozmiar tekstu");
		menuWygl�d.add(mRozmiar);
		mRozmiar.addActionListener(this);
		chZawijanie = new JCheckBoxMenuItem("Zawijanie wierszy");
		chZawijanie.addActionListener(this);
		menuWygl�d.add(chZawijanie);
		
		setJMenuBar(menuBar);
		menuBar.add(menuPlik);
	//	menuBar.add(menuNarz�dzia);
	//	menuBar.add(menuSzukaj);
		menuBar.add(menuWygl�d);
		menuBar.add(menuPomoc);

		
		popup = new JPopupMenu();
		mpKopiuj = new JMenuItem("Kopiuj");
		mpKopiuj.addActionListener(this);
		mpWklej = new JMenuItem("Wklej");
		mpWklej.addActionListener(this);
		//mpDo��cz = new JMenuItem("Do��cz");
		//mpDo��cz.addActionListener(this);
		

		
		popup.add(mpKopiuj);
		popup.add(mpWklej);
		//popup.add(mpDo��cz);
		
		
		notatnik = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(notatnik, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		notatnik.setWrapStyleWord(true);
		scrollPane.setBounds(5,5,700 ,500 );
		
		add(scrollPane);
		notatnik.setLineWrap(false);
		notatnik.setComponentPopupMenu(popup); 
		



	}

	public static void main(String[] args) {
		KNAKnote appMenu = new KNAKnote();
		appMenu.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object �r�d�o = e.getSource();
		if (�r�d�o==mSzukaj) {
			String tekst = notatnik.getText();
			String znajd� = JOptionPane.showInputDialog("");
			String wyst�pienia ="";
			int i = 0;
			int index;
			int startIndex = 0;
			while ((index = tekst.indexOf(znajd�, startIndex))!= -1) {
				startIndex = index + znajd�.length();
				i++;
				wyst�pienia = wyst�pienia + " " + index;
				
			}
			JOptionPane.showMessageDialog(null, znajd� + " wyst�pi�o " +  i + " razy:" + wyst�pienia);
			
			
			
		}
		else if (�r�d�o==mOtw�rz){
			JFileChooser fc = new JFileChooser();
			if (fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
				File plik = fc.getSelectedFile();
				//JOptionPane.showMessageDialog(null, "Wybrany plik to: " + plik.getAbsolutePath());
				try {
					notatnik.setText("");
					setTitle("Kombajn (" + plik.getName() +")");
					Scanner skaner = new Scanner(plik);
					while(skaner.hasNext())
						notatnik.append(skaner.nextLine() + "\n");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if (�r�d�o==mZapisz){
			JFileChooser fc = new JFileChooser();
			if (fc.showSaveDialog(null)==JFileChooser.APPROVE_OPTION) {
				File plik = fc.getSelectedFile();
				try {
					PrintWriter writer = new PrintWriter(plik);
					Scanner skaner = new Scanner(notatnik.getText());
					while (skaner.hasNext()){
						writer.println(skaner.nextLine() + "\n");
						writer.close();}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		else if (�r�d�o == mWyj�cie) {
			int odpowied� = JOptionPane.showConfirmDialog(this, "Czy napewno chcesz wyj��?", "",
					JOptionPane.YES_NO_OPTION);
			if (odpowied� == JOptionPane.YES_OPTION) {
				dispose();
			} else if (odpowied� == JOptionPane.CLOSED_OPTION) {

			}

		}

		else if (�r�d�o == chZawijanie) {
			if (chZawijanie.isSelected()) {
				notatnik.setLineWrap(true);
			} else if (!chZawijanie.isSelected()) {
				mNarz1.setEnabled(false);
			}

		}
		else if (�r�d�o == mCelToFar) {
//			CelsiusToFarenheit.CelsiusToFarenheit();
//			CelsiusToFarenheit.CelsiusToFarenheit.setVisible(true);
		}
		if (�r�d�o == mOProgramie) {
			JOptionPane.showMessageDialog(this, "Notatnik stworzony w ramach nauki SWING przez Bart�omiej Szcz�niak", "O programie",	JOptionPane.INFORMATION_MESSAGE);
		}
		if (�r�d�o == mMetryStopy) {
			String sMetry = JOptionPane.showInputDialog("Podaj d�ugo�� w metrach");
			double metry = Double.parseDouble(sMetry);
			double stopy = metry / 0.3048;
			String sStopy = String.format("%f.2", stopy);
			JOptionPane.showMessageDialog(this, metry + " metr�w =" + sStopy + " st�p." + sStopy, "", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (�r�d�o==mKolor){
			Color wybranyKolor = JColorChooser.showDialog(this, "Wybierz kolor czcionki", Color.BLACK);
			notatnik.setForeground(wybranyKolor);
		}
		else if (�r�d�o==mRozmiar){
			String sRozmiarCzcionki = JOptionPane.showInputDialog(this, "Podaj rozmiar tekstu");
			int iRozmiarCzcionki = Integer.parseInt(sRozmiarCzcionki);
			notatnik.setFont(new Font ("SansSerif", Font.PLAIN, iRozmiarCzcionki));
		}
		else if (�r�d�o==mpKopiuj){
			tekstSchowek = notatnik.getSelectedText();
			
		}
		else if (�r�d�o==mpWklej){
			notatnik.insert(tekstSchowek, notatnik.getCaretPosition());
			
		}
		else if (�r�d�o==mpDo��cz){
			
		}
	}

}
