package timeklaVietnePD;

public class User extends VietnesApmekletajs{
	
	// Atribūti
	private String vards, uzvards, valsts;
	private int DzGads;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public User(int DzGads, int Nosutiti, String lietVards, String parole, String vards, String uzvards,
			String valsts, String epasts) {
		super(Nosutiti, lietVards, parole, epasts);
		this.vards = vards;
		this.uzvards = uzvards;
		this.valsts = valsts;
		this.DzGads = DzGads;
		
	}
	
	// Getter metodes
			public String getVards() {
				return vards;
			}
			
			public String getUzvards() {
				return uzvards;
			}
				
			public String getValsts() {
				return valsts;
			}
				
			public int getDzGads() {
				return DzGads;
			}
			
			
			
			// Setter metodes
			public void setVards(String vards) {
				this.vards = vards;
			}
			
			public void setUzvards(String uzvards) {
				this.uzvards = uzvards;
			}
			
			public void setValsts(String valsts) {
				this.valsts = valsts;
			}
			
			public void setDzGads(int dzGads) {
				this.DzGads = dzGads;
			}
			
	
	
	
} 