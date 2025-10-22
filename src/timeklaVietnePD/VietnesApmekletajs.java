package timeklaVietnePD;

import java.io.Serializable;
import java.util.Random;


public class VietnesApmekletajs implements Serializable, Comparable<VietnesApmekletajs>{
	/**
	 *  Ģenerēts serialVersionUID
	 */
	private static final long serialVersionUID = 5672751866842530800L;
	
		// Atribūti
		private int DzGads, Nosutiti;
		private String lietVards, parole, vards, uzvards, valsts, epasts;
		
		// Konstruktors
		public VietnesApmekletajs(int DzGads, int Nosutiti, String lietVards, String parole, String vards, String uzvards, String valsts, String epasts) {
			this.DzGads = DzGads;
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
		
		public void setDzGads(int DzGads) {
			this.DzGads = DzGads;
		}
		
		public void setNosutiti(int Nosutiti) {
			this.Nosutiti = Nosutiti;
		}
			
		@Override
		public int compareTo(VietnesApmekletajs o) {
			// TODO Auto-generated method stub
			return 0;
		}
}
