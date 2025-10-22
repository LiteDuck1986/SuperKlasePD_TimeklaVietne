package timeklaVietnePD;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TimeklaVietne {

	public static void main(String[] args) {
		int izvele;
		String[] logonIzveles = {"Ielogoties kā lietotājs", "Ielogoties kā administrators", "Beigt darbu"};
		
		ArrayList<Object> lietotaji = new ArrayList<>();
		ArrayList<Object> paroles = new ArrayList<>();
		
		JPanel logonIzvelne = new JPanel();
		logonIzvelne.add(new JLabel(new ImageIcon(".//bildes//duckbear.png")));
		
		
		do {
			izvele = JOptionPane.showOptionDialog(null, logonIzvelne, "Labdien! – DuckBear e-pasta tīmekļa vietne",
	                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
	                null, logonIzveles, null);
			
			if (izvele == -1 || izvele == 2)
				izvele = 0;
						
			switch(izvele) {

			}
			
				
			
		} while(izvele != 0);
		
	}

}
