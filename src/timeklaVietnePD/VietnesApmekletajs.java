package timeklaVietnePD;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class VietnesApmekletajs implements Serializable, Comparable<VietnesApmekletajs>{
	/**
	 *  Ģenerēts serialVersionUID
	 */
	private static final long serialVersionUID = 5672751866842530800L;
	
		int DzGads;

		// Atribūti
		private int Nosutiti;
		private String lietVards, parole, vards, uzvards, valsts, epasts;
		private List<String> vestules = new ArrayList<>();
		
		// Konstruktors
		public VietnesApmekletajs(int dzGads, int Nosutiti, String lietVards, String parole, String vards, String uzvards, String valsts, String epasts) {
			this.DzGads = dzGads;
			this.Nosutiti = Nosutiti;
			this.lietVards = lietVards;
			this.parole = parole;
			this.vards = vards;
			this.uzvards = uzvards;
			this.epasts = epasts;
			
		}
		
		// Getter metodes
		public String getVards() {
			return vards;
		}
		
		public String getUzvards() {
			return uzvards;
		}
		
		public String getLietVards() {
			return lietVards;
		}
		
		public String getParole() {
			return parole;
		}
		
		public String getValsts() {
			return valsts;
		}
		
		public String getEpasts() {
			return epasts;
		}
		
		public int getDzGads() {
			return DzGads;
		}
		
		public int getNosutiti() {
			return Nosutiti;
		}
		
		public List<String> getVestules() {
	        return vestules;
	    }
		
		
		
		// Setter metodes
		public void setVards(String vards) {
			this.vards = vards;
		}
		
		public void setUzvards(String uzvards) {
			this.uzvards = uzvards;
		}
		
		public void setLietVards(String lietVards) {
			this.lietVards = lietVards;
		}
		
		public void setParole(String parole) {
			this.parole = parole;
		}
		
		public void setValsts(String valsts) {
			this.valsts = valsts;
		}
		
		public void setEpasts(String epasts) {
			this.epasts = epasts;
		}
		
		public void setDzGads(int dzGads) {
			this.DzGads = dzGads;
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
