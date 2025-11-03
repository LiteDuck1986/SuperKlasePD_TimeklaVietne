package timeklaVietnePD;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class VietnesApmekletajs implements Serializable, Comparable<VietnesApmekletajs>{
	/**
	 *  Ģenerēts serialVersionUID
	 */
	private static final long serialVersionUID = 5672751866842530800L;

		// Atribūti
		private int Nosutiti;
		private String lietVards, parole, epasts;
		private List<String> vestules = new ArrayList<>();
		
		// Konstruktors
		public VietnesApmekletajs(int Nosutiti, String lietVards, String parole, String epasts) {
			this.Nosutiti = Nosutiti;
			this.lietVards = lietVards;
			this.parole = parole;
			this.epasts = epasts;
			
		}
		
		// Getter metodes
		public String getLietVards() {
			return lietVards;
		}
		
		public String getParole() {
			return parole;
		}
		
		public String getEpasts() {
			return epasts;
		}
		
		public int getNosutiti() {
			return Nosutiti;
		}
		
		public List<String> getVestules() {
	        return vestules;
	    }
		
		
		
		// Setter metodes
		
		public void setLietVards(String lietVards) {
			this.lietVards = lietVards;
		}
		
		public void setParole(String parole) {
			this.parole = parole;
		}
		
		public void setEpasts(String epasts) {
			this.epasts = epasts;
		}
		
		public void setNosutiti(int Nosutiti) {
			this.Nosutiti = Nosutiti;
		}
		
		public void pievienoVestuli(String s) {
	        vestules.add(s);
	    }
			
		@Override
		public int compareTo(VietnesApmekletajs o) {
			// TODO Auto-generated method stub
			return 0;
		}

		public void setDzGads(String dzimsanasGads) {
			// TODO Auto-generated method stub
			
		}
}
