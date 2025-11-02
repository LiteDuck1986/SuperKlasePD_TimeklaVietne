package timeklaVietnePD;

import java.util.ArrayList;

import javax.swing.JOptionPane;

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
	    public void redigetLietotaju(ArrayList<User > lietotaji) {
	        if (lietotaji.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Nav lietotāju, ko rediģēt.");
	            
	            return;
	        }
	        // To do.. (123)
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



