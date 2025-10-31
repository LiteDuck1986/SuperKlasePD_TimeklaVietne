package timeklaVietnePD;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;

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
		
		Calendar kal = Calendar.getInstance();
		kal.add(Calendar.YEAR, -120);
		Date minDatums = kal.getTime();
		
		SpinnerDateModel datumaDiap = new SpinnerDateModel(new Date(), minDatums, new Date(), Calendar.DAY_OF_MONTH);
		JSpinner datumaSpinner = new JSpinner(datumaDiap);
		JSpinner.DateEditor formats = new JSpinner.DateEditor(datumaSpinner, "dd.MM.yyyy");
		datumaSpinner.setEditor(formats);
		
		JPanel datumaPanelis = new JPanel();
		datumaPanelis.add(new JLabel("Ievadiet dzimšanas datumu:"));
		datumaPanelis.add(datumaSpinner);
		
		String dzimsanasGads;
		
		int izvele;
		String[] logonIzveles = {"Reģistrēties", "Autorizēties", "Beigt darbu"};
		
		ArrayList<User> lietArray = new ArrayList<>();
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
				lietotajs = new VietnesApmekletajs(0, 0, null, null, null, null, null);
				
				
				String v = virknesParbaude("Ievadi vārdu: ", "Intars", false);
				if (v == null)
					break;
				lietotajs.setVards(v);
				
				String u = virknesParbaude("Ievadi uzvārdu: ", "Jakubovičs", false);
				if (u == null)
					break;
				lietotajs.setUzvards(u);
				
				String lietVards = virknesParbaude("Ievadi lietotājvārdu: ", "integerSys", false);
				if (lietVards == null)
					break;
				lietotajs.setLietVards(lietVards);
				
				char[] paroleMas = null;
				
				do {
					JPanel panel = new JPanel();
					panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
					JLabel label = new JLabel("Ievadi paroli:");
					JPasswordField par = new JPasswordField(15);
					JCheckBox showPassword = new JCheckBox("Rādīt paroli");
					
					showPassword.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							JCheckBox cb = (JCheckBox) e.getSource();
							if (cb.isSelected()) {
								par.setEchoChar((char) 0);
							} else {
								par.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
							}
						}
					});
					
					panel.add(label);
					panel.add(par);
					panel.add(showPassword);
					
					String[] options = new String[]{"Ok", "Cancel"};
					int opcija = (JOptionPane.showOptionDialog(null, panel, "Paroles ievade",
					                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
					                         null, options, options[0]));
					if (opcija == 0) {
						paroleMas = par.getPassword();
						if (paroleMas.length < 8)
							JOptionPane.showMessageDialog(null, "Parole par īsu!", "Informācija", JOptionPane.WARNING_MESSAGE);
						
					} else if (opcija == 1 || opcija == -1) {
						paroleMas = new char[] {' '};
						break;
					}
					
				} while(paroleMas.length < 8);
				
				if (paroleMas.length >= 8) {
					lietotajs.setParole(new String(paroleMas));
					Arrays.fill(paroleMas, '0');
				} else {
					break;
				}
				
				int datumaIzv = JOptionPane.showConfirmDialog(null, datumaPanelis, "Ievadi dzimšanas datumu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				 
				if (datumaIzv == JOptionPane.OK_OPTION) {
				    Date izveletaisDat = (Date) datumaSpinner.getValue();
				    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				    dzimsanasGads = sdf.format(izveletaisDat);
				    lietotajs.setDzGads(dzimsanasGads);
				} else {
					break;
				}
				
				String epasts = lietotajs.getLietVards().trim() + "@duckbear.lv";
				JOptionPane.showMessageDialog(null, "Tava e-pasta adrese: " + epasts);
				User liet = new User(lietotajs.getDzGads(), 0, lietotajs.getLietVards(), lietotajs.getParole(), lietotajs.getVards(), lietotajs.getUzvards(), lietotajs.getValsts(), epasts);
				lietArray.add(liet);
				
				break;
			case 1:
				
				String ievade = JOptionPane.showInputDialog(null, "Ievadi savu lietotājvārdu: ", "Autorizēšanās", JOptionPane.PLAIN_MESSAGE);
				if (ievade == null) break;
				
				int userIndex = -1; 
				for (int i = 0; i < lietArray.size(); i++) {
					if (ievade.equals(lietArray.get(i).getLietVards())) {
						userIndex = i;
						break;
					}
				}
				
				if (userIndex == -1) {
					JOptionPane.showMessageDialog(null, "Lietotājvārds netika atrasts", "Kļūda", JOptionPane.ERROR_MESSAGE);
					break;
				}
				
				paroleMas = null;
				boolean irParole = false;
				
				do {
					JPanel panel = new JPanel();
					panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
					JLabel label = new JLabel("Ievadi paroli:");
					JPasswordField par = new JPasswordField(15);
					JCheckBox showPassword = new JCheckBox("Rādīt paroli");
					
					showPassword.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							JCheckBox cb = (JCheckBox) e.getSource();
							if (cb.isSelected()) {
								par.setEchoChar((char) 0);
							} else {
								par.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
							}
						}
					});
					
					panel.add(label);
					panel.add(par);
					panel.add(showPassword);
					
					String[] options = new String[]{"Ok", "Cancel"};
					int opcija = (JOptionPane.showOptionDialog(null, panel, "Paroles ievade", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]));
					
					if (opcija == 0) {
						paroleMas = par.getPassword();
						if (paroleMas.length < 8)
							JOptionPane.showMessageDialog(null, "Parole par īsu!", "Informācija", JOptionPane.WARNING_MESSAGE);
						
					} else if (opcija == 1 || opcija == -1) {
						paroleMas = new char[] {' '};
						break;
					}
					
				} while(paroleMas.length < 8);
				
				if (paroleMas.length >= 8) {
					
					char[] saglabataParole = lietArray.get(userIndex).getParole().toCharArray();
					
					if (Arrays.equals(paroleMas, saglabataParole)) {
						irParole = true;
						JOptionPane.showMessageDialog(null, "Autorizācija veiksmīga! Sveicināts, " + lietArray.get(userIndex).getVards() + "!", "Veiksmīgi", JOptionPane.INFORMATION_MESSAGE);
					}
					
					Arrays.fill(saglabataParole, '0');
					Arrays.fill(paroleMas, '0'); 
					
					if (!irParole)
						JOptionPane.showMessageDialog(null, "Nepareiza parole!", "Kļūda", JOptionPane.ERROR_MESSAGE);
					
				} 
				
				break;
			}
			
		} while(izvele != -1);
		
	}

}