package timeklaVietnePD;

import java.awt.Font;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TimeklaVietne {
	
	public static String virknesParbaude(String zinojums, String noklusejums, boolean irParole) {
		String virkne;
		
		do {
			virkne = JOptionPane.showInputDialog(zinojums, noklusejums);
			
			if(virkne == null)
				return null;
			
			virkne = virkne.trim();
		} while(!Pattern.matches("^[\\p{L} .]+$", virkne));
		
		return virkne;
	}

	public static void main(String[] args) {
		int izvele;
		String[] logonIzveles = {"Reģistrēties", "Autorizēties", "Beigt darbu"};
		
		ArrayList<Object> lietotaji = new ArrayList<>();
		ArrayList<Object> paroles = new ArrayList<>();
		VietnesApmekletajs lietotajs = null;
		
		JPanel logonIzvelne = new JPanel();
		logonIzvelne.add(new JLabel(new ImageIcon(".//bildes//duckbear.png")));
		
		
		do {
			izvele = JOptionPane.showOptionDialog(null, logonIzvelne, "Labdien! – DuckBear e-pasta tīmekļa vietne",
	                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
	                null, logonIzveles, null);
			
			if (izvele == -1 || izvele == 2)
				izvele = -1;
						
			switch(izvele) {
			case 0:
				lietotajs = new VietnesApmekletajs();
				
				String v = virknesParbaude("Ievadi vārdu: ", "Intars", false);
				if (v == null)
					break;
				lietotajs.setVards(v);
				
				String u = virknesParbaude("Ievadi uzvārdu: ", "Jakubovičs", false);
				if (u == null)
					break;
				lietotajs.setUzvards(v);
				
				String lietVards = virknesParbaude("Ievadi lietotājvārdu: ", "Jakubovičs", false);
				if (lietVards == null)
					break;
				lietotajs.setLietVards(v);
				
				String parole = virknesParbaude("Ievadi paroli (vismaz 8 simboli): ", null, true);
				if (parole == null || parole.length() < 8)
					break;
				lietotajs.setParole(v);
				
				break;
			case 1:
				
				break;
			}
			
				
			
		} while(izvele != -1);
		
	}

}
