package timeklaVietnePD;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Admin extends VietnesApmekletajs{
	
	public Admin(int Nosutiti, String lietVards, String parole, String epasts) {
		super(Nosutiti, lietVards, parole, epasts);
	}
		
		//Skatīt lietotājus
	    public void skatitLietotajus(ArrayList<User > lietotaji) {
	        if (lietotaji.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Nav reģistrētu lietotāju.");
	            return;
	        }

	        String[] lietVardi = new String[lietotaji.size()];
	        for (int i = 0; i < lietotaji.size(); i++) {
	            lietVardi[i] = ((User) lietotaji.get(i)).getLietVards();
	        }
	        
	        String izveletais = (String) JOptionPane.showInputDialog(null,
	                "Izvēlies lietotāju, lai apskatītu datus:",
	                "Reģistrētie lietotāji",
	                JOptionPane.PLAIN_MESSAGE, null, lietVardi, lietVardi[0]);

	        if (izveletais == null) return;

	        for (int i = 0; i < lietotaji.size(); i++) {
	            User liet = (User) lietotaji.get(i);
	            if (liet.getLietVards().equals(izveletais)) {
	                JOptionPane.showMessageDialog(null,
	                        "Vārds: " + liet.getVards() +
	                        "\nUzvārds: " + liet.getUzvards() +
	                        "\nLietotājvārds: " + liet.getLietVards() +
	                        "\nE-pasts: " + liet.getEpasts() +
	                        "\nDzimšanas gads: " + liet.getDzGads());
	                break;
	            }
	        }
	    }

	    //Rediģēt lietotāju
	    public void redigetLietotaju(ArrayList<User> lietotaji) {
	        if (lietotaji.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Nav lietotāju, ko rediģēt.");
	            return;
	        }

	        String[] lietVardi = new String[lietotaji.size()];
	        for (int i = 0; i < lietotaji.size(); i++) {
	            lietVardi[i] = lietotaji.get(i).getLietVards();
	        }

	        String izveletaisLietVards = (String) JOptionPane.showInputDialog(null,
	                "Izvēlies lietotāju, kuru rediģēt:",
	                "Rediģēt lietotāju",
	                JOptionPane.PLAIN_MESSAGE, null, lietVardi, lietVardi[0]);

	        if (izveletaisLietVards == null) return;

	        User redigetaisLietotajs = null;
	        int indekss = -1;
	        for (int i = 0; i < lietotaji.size(); i++) {
	            if (lietotaji.get(i).getLietVards().equals(izveletaisLietVards)) {
	                redigetaisLietotajs = lietotaji.get(i);
	                indekss = i;
	                break;
	            }
	        }

	        if (redigetaisLietotajs == null) return;

	        String[] redigesanasOpcijas = {"Vārds", "Uzvārds", "Lietotājvārds", "Parole", "E-pasts", "Atcelt"};
	        
	        String opcija;
	        do {
	            opcija = (String) JOptionPane.showInputDialog(null,
	                    "Ko vēlies rediģēt lietotājam " + redigetaisLietotajs.getLietVards() + "?",
	                    "Rediģēšana",
	                    JOptionPane.PLAIN_MESSAGE, null, redigesanasOpcijas, redigesanasOpcijas[0]);

	            if (opcija == null || opcija.equals("Atcelt")) {
	                break;
	            }
	            
	            String jaunaVerta;
	            switch (opcija) {
	                case "Vārds":
	                    jaunaVerta = TimeklaVietne.virknesParbaude("Ievadi jauno vārdu (pašreizējais: " + redigetaisLietotajs.getVards() + "):", redigetaisLietotajs.getVards(), false);
	                    if (jaunaVerta != null) {
	                        redigetaisLietotajs.setVards(jaunaVerta);
	                        JOptionPane.showMessageDialog(null, "Vārds nomainīts!");
	                    }
	                    break;
	                case "Uzvārds":
	                    jaunaVerta = TimeklaVietne.virknesParbaude("Ievadi jauno uzvārdu (pašreizējais: " + redigetaisLietotajs.getUzvards() + "):", redigetaisLietotajs.getUzvards(), false);
	                    if (jaunaVerta != null) {
	                        redigetaisLietotajs.setUzvards(jaunaVerta);
	                        JOptionPane.showMessageDialog(null, "Uzvārds nomainīts!");
	                    }
	                    break;
	                case "Lietotājvārds":
	                    jaunaVerta = TimeklaVietne.virknesParbaude("Ievadi jauno lietotājvārdu (pašreizējais: " + redigetaisLietotajs.getLietVards() + "):", redigetaisLietotajs.getLietVards(), false);
	                    if (jaunaVerta != null) {
	                        redigetaisLietotajs.setLietVards(jaunaVerta);
	                        // Atjauninām e-pastu, ja mainās lietotājvārds
	                        redigetaisLietotajs.setEpasts(jaunaVerta.trim() + "@duckbear.lv");
	                        JOptionPane.showMessageDialog(null, "Lietotājvārds un e-pasts nomainīti!");
	                    }
	                    break;
	                case "Parole":
	                    char[] paroleMas = null;
	                    boolean paroleOK = false;
	                    do {
	                        // Ievadiet paroli (izmantojot to pašu loģiku, kas reģistrācijā)
	                        JPanel panel = new JPanel();
	                        // Tiek izmantots `TimeklaVietne.parolesIevadePanelis()` vai jāpārvieto loģika uz `Admin` klasi vai jāpiekļūst statiski no `TimeklaVietne`
	                        // Vienkāršības labad izmantojam showInputDialog
	                        String newPass = JOptionPane.showInputDialog("Ievadi jauno paroli (vismaz 8 simboli):");
	                        if (newPass == null) {
	                            paroleOK = true; // Atcelt
	                            break;
	                        }
	                        if (newPass.length() >= 8) {
	                            redigetaisLietotajs.setParole(newPass);
	                            JOptionPane.showMessageDialog(null, "Parole nomainīta!");
	                            paroleOK = true;
	                        } else {
	                            JOptionPane.showMessageDialog(null, "Parole par īsu!", "Informācija", JOptionPane.WARNING_MESSAGE);
	                        }
	                    } while (!paroleOK);
	                    break;
	                case "E-pasts":
	                    // E-pasts mainās automātiski, kad maina lietotājvārdu, bet atļausim arī manuālu ievadi kā VietnesApmekletajs
	                    jaunaVerta = TimeklaVietne.virknesParbaude("Ievadi jauno e-pastu (pašreizējais: " + redigetaisLietotajs.getEpasts() + "):", redigetaisLietotajs.getEpasts(), false);
	                    if (jaunaVerta != null) {
	                        redigetaisLietotajs.setEpasts(jaunaVerta);
	                        JOptionPane.showMessageDialog(null, "E-pasts nomainīts!");
	                    }
	                    break;
	            }
	        } while (opcija != null && !opcija.equals("Atcelt"));
	    }
	    
	    //Dzēst lietotāju
	    public void dzestLietotaju(ArrayList<User > lietotaji) {
	        if (lietotaji.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Nav lietotāju, ko dzēst.");
	            
	            return;
	        }

	        
	        String[] lietVardi = new String[lietotaji.size()];
	        for (int i = 0; i < lietotaji.size(); i++) {
	            lietVardi[i] =  ((User) lietotaji.get(i)).getLietVards();
	        }

	        String izvēletais = (String) JOptionPane.showInputDialog(null,
	                "Izvēlies lietotāju, kuru dzēst:", "Dzēst lietotāju",
	                JOptionPane.PLAIN_MESSAGE, null, lietVardi, lietVardi[0]);

	        
	        if (izvēletais == null) return;
	        

	        for (int i = 0; i < lietotaji.size(); i++) {
	            User liet = (User) lietotaji.get(i);
	            if (liet.getLietVards().equals(izvēletais)) {
	                int apstiprin = JOptionPane.showConfirmDialog(null,
	                        "Vai tiešām vēlies dzēst lietotāju " + liet.getLietVards() + "?",
	                        "Apstiprinājums", JOptionPane.YES_NO_OPTION);
	                if (apstiprin == JOptionPane.YES_OPTION) {
	                	
	                    lietotaji.remove(i);
	                    JOptionPane.showMessageDialog(null, "Lietotāja konts veiksmīgi dzēsts!");
	                    
	                    }
	                break;
	            }
	        }
	    }
}