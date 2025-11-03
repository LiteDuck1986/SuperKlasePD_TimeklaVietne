package timeklaVietnePD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
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
		
		ArrayList<User> lietotaji = new ArrayList<>();
		ArrayList<Object> paroles = new ArrayList<>();
		User lietotajs = null;
		
		Admin Admins = null;
		Admins = new Admin(0, "Admin", "admin123", "Admin@duckbear.lv");
		
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
				lietotajs = new User(0, 0, null, null, null, null, null, null);
				
				
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
				    // Mainīts, lai iegūtu tikai gadu kā int, atbilstoši User klasei
				    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
				    String dzimsanasGadsStr = sdf.format(izveletaisDat);
				    
				    try {
				    	int gads = Integer.parseInt(dzimsanasGadsStr);
				        lietotajs.setDzGads(gads); 
				    } catch (NumberFormatException e) {
				    	JOptionPane.showMessageDialog(null, "Kļūda dzimšanas gada iegūšanā.", "Kļūda", JOptionPane.ERROR_MESSAGE);
				    	break;
				    }
				    
				} else {
					break;
				}
				
				String epasts = lietotajs.getLietVards().trim() + "@duckbear.lv";
				JOptionPane.showMessageDialog(null, "Tava e-pasta adrese: " + epasts);
				lietotajs.setEpasts(epasts);
				
				// Te tiek izveidota jauna registrēta lietotāja klase
				lietotaji.add(lietotajs);
				paroles.add(lietotajs.getParole());
				
				break;
				
				// Autorizēšanās
			case 1:
				
				// Darbības
				boolean user = false;
				boolean admin = false;
			    String[] darbibasIzvele = {"Autorizēties kā lietotājs", "Autorizēties kā Admin", "Atgriezties"};
				
				boolean izvelesLogs = true;
			    while (izvelesLogs) {
			        String izvelesAut = (String) JOptionPane.showInputDialog(null, 
			                "Izvēlies darbību:", 
			                "DuckBear e-pasta vietne", 
			                JOptionPane.PLAIN_MESSAGE, null, darbibasIzvele, darbibasIzvele[0]);

			        if (izvelesAut == null || izvelesAut.equals("Atgriezties")) {
			            JOptionPane.showMessageDialog(null, "Jūs atgriezāties atpakaļ uz sākuma lapu!");
			            izvelesLogs = false;
			            break;
			        }

			        switch (izvelesAut) {
			            case "Autorizēties kā lietotājs":
			            	user = true;
			            	izvelesLogs = false;
			                break;

			            case "Autorizēties kā Admin":
			                	admin = true;
			                    izvelesLogs = false;
			                    break;
			                }
			                break;
			        }
			
				
				
				// User logs
			    if(user == true) {
			    
			    
			    String ievLiet = JOptionPane.showInputDialog("Ievadi lietotājvārdu:");
			    if (ievLiet == null || ievLiet.isEmpty())
			        break;

			    JPasswordField parole = new JPasswordField();
			    int opcija = JOptionPane.showConfirmDialog(null,
			    		parole, "Ievadi paroli:", JOptionPane.OK_CANCEL_OPTION,
			    		JOptionPane.PLAIN_MESSAGE);
			    
			    if (opcija != JOptionPane.OK_OPTION)
			        break;

			    String ievPar = new String(parole.getPassword());

			    boolean atrasts = false;
			    User atrastais = null;

			    for (User liet : lietotaji) {
			        if (liet.getLietVards().equals(ievLiet) && liet.getParole().equals(ievPar)) {
			            atrasts = true;
			            atrastais = liet;
			            break;
			        }
			    }

			    if (!atrasts) {
			        JOptionPane.showMessageDialog(null, "Nepareizs lietotājvārds vai parole!", 
			                                      "Kļūda", JOptionPane.ERROR_MESSAGE);
			        break;
			    }

			    JOptionPane.showMessageDialog(null, 
			        "Sveicināts, " + atrastais.getVards() + "!\nTavs e-pasts: " + atrastais.getEpasts(), 
			        "Autorizācija veiksmīga", JOptionPane.INFORMATION_MESSAGE);

			    // Darbības
			    String[] darbibas = {"Nosūtīt e-pastu", "Skatīt profilu", "Apskatīt ienākušās vēstules", 
			                         "Dzēst savu kontu", "Iziet no konta"};

			    boolean aktivs = true;
			    while (aktivs) {
			        String izveleStr = (String) JOptionPane.showInputDialog(null, 
			                "Izvēlies darbību:", 
			                "DuckBear e-pasta vietne", 
			                JOptionPane.PLAIN_MESSAGE, null, darbibas, darbibas[0]);

			        if (izveleStr == null || izveleStr.equals("Iziet no konta")) {
			            JOptionPane.showMessageDialog(null, "Izrakstīšanās veiksmīga!");
			            aktivs = false;
			            break;
			        }

			        switch (izveleStr) {
			            case "Skatīt profilu":
			                JOptionPane.showMessageDialog(null, 
			                    "Profils:\nVārds: " + atrastais.getVards() + 
			                    "\nUzvārds: " + atrastais.getUzvards() + 
			                    "\nE-pasts: " + atrastais.getEpasts() +
			                    "\nDzimšanas gads: " + atrastais.getDzGads());
			                break;

			            case "Apskatīt ienākušās vēstules":
			                if (atrastais.getVestules().isEmpty()) {
			                    JOptionPane.showMessageDialog(null, "Tev nav jaunu vēstuļu.");
			                } else {
			                    String str = "";
			                    for (String s : atrastais.getVestules()) {
			                        str += s + "\n============================================\n";
			                    }
			                    JTextArea ta = new JTextArea(str, 10, 40);
			                    ta.setEditable(false);
			                    JScrollPane sp = new JScrollPane(ta);
			                    sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			                    JOptionPane.showMessageDialog(null, sp, "Ienākušās vēstules", JOptionPane.PLAIN_MESSAGE);
			                }
			                break;

			            case "Nosūtīt e-pastu":
			                if (lietotaji.size() <= 1) {
			                    JOptionPane.showMessageDialog(null, "Nav citu lietotāju, kam nosūtīt vēstuli.");
			                    break;
			                }

			                //dropdown saraksts ar lietotājiem
			                ArrayList<String> adresati = new ArrayList<>();
			                for (User liet : lietotaji) {
			                    if (!liet.getLietVards().equals(atrastais.getLietVards())) {
			                        adresati.add(liet.getEpasts());
			                    }
			                }

			                String saņēmējs = (String) JOptionPane.showInputDialog(null, 
			                        "Izvēlies kam sūtīt:", "Nosūtīt vēstuli", 
			                        JOptionPane.PLAIN_MESSAGE, null, adresati.toArray(), adresati.get(0));

			                if (saņēmējs == null) break;

			                JTextArea ta = new JTextArea(10, 40);
			                JScrollPane sp = new JScrollPane(ta);
			                int s = JOptionPane.showConfirmDialog(null, sp, 
			                        "Ieraksti savu vēstuli", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			                
			                if (s == JOptionPane.OK_OPTION) {
			                    String teksts = ta.getText();
			                    if (teksts.trim().isEmpty()) {
			                        JOptionPane.showMessageDialog(null, "Vēstule ir tukša!");
			                        break;
			                    }

			                    // Atrodam adresātu un pievienojam vēstuli
			                    for (User liet : lietotaji) {
			                        if (liet.getEpasts().equals(saņēmējs)) {
			                        	liet.pievienoVestuli("No: " + atrastais.getEpasts() + "\n\n" + teksts);
			                            break;
			                        }
			                    }

			                    JOptionPane.showMessageDialog(null, "Vēstule nosūtīta!");
			                }
			                break;

			            case "Dzēst savu kontu":
			                int apstiprin = JOptionPane.showConfirmDialog(null, 
			                        "Vai tiešām vēlies dzēst savu kontu?", 
			                        "Apstiprinājums", JOptionPane.YES_NO_OPTION);
			                if (apstiprin == JOptionPane.YES_OPTION) {
			                    lietotaji.remove(atrastais);
			                    //Konts dzēsts :(
			                    JOptionPane.showMessageDialog(null, "Konts dzēsts veiksmīgi!");
			                    aktivs = false;
			                }
			                break;
			        }
			    }
			    break;
			    }
			    
			    
			    // Admin logs
			    if (admin == true) {
			    	
			    	 String ievLiet = JOptionPane.showInputDialog("Ievadi lietotājvārdu:");
					    if (ievLiet == null || ievLiet.isEmpty())
					        break;

					    JPasswordField parole = new JPasswordField();
					    int opcija = JOptionPane.showConfirmDialog(null,
					    		parole, "Ievadi paroli:", JOptionPane.OK_CANCEL_OPTION,
					    		JOptionPane.PLAIN_MESSAGE);
					    
					    if (opcija != JOptionPane.OK_OPTION)
					        break;

					    String ievPar = new String(parole.getPassword());

					    boolean atrasts = false;


					        if (Admins.getLietVards().equals(ievLiet) && Admins.getParole().equals(ievPar)) {
					            atrasts = true;
					        }

					    if (atrasts == false) {
					        JOptionPane.showMessageDialog(null, "Nepareizs lietotājvārds vai parole!", 
					                                      "Kļūda", JOptionPane.ERROR_MESSAGE);
					        break;
					    }

					    JOptionPane.showMessageDialog(null, 
					        "Sveicināts, " + Admins.getLietVards() + "!\nTavs e-pasts: " + Admins.getEpasts(), 
					        "Autorizācija veiksmīga", JOptionPane.INFORMATION_MESSAGE);

					    // Darbības
					    String[] darbibas = {"Skatīt profilu", "Skatīt reģistrētos lietotājus", "Rediģēt reģistrētos lietotājus", 
					                         "Dzēst ŗeģistrētos lietotājus", "Iziet no konta"};

					    boolean aktivs = true;
					    while (aktivs) {
					        String izveleStr = (String) JOptionPane.showInputDialog(null, 
					                "Izvēlies darbību:", 
					                "DuckBear e-pasta vietne", 
					                JOptionPane.PLAIN_MESSAGE, null, darbibas, darbibas[0]);

					        if (izveleStr == null || izveleStr.equals("Iziet no konta")) {
					            JOptionPane.showMessageDialog(null, "Izrakstīšanās veiksmīga!");
					            aktivs = false;
					            break;
					        }

					        switch (izveleStr) {
					        case "Skatīt profilu":
					            JOptionPane.showMessageDialog(null,
					                "Profils:\nLietotājvārds: " + Admins.getLietVards() +
					                "\nE-pasts: " + Admins.getEpasts());
					            break;

					        case "Skatīt reģistrētos lietotājus":
					            Admins.skatitLietotajus(lietotaji);
					            break;

					        case "Rediģēt reģistrētos lietotājus":
					            Admins.redigetLietotaju(lietotaji);
					            break;

					        case "Dzēst ŗeģistrētos lietotājus":
					            Admins.dzestLietotaju(lietotaji);
					            break;
					        }
					    }
					    break;
					    
			    }
			}
			
				
			
		} while(izvele != -1);
		
	}

}