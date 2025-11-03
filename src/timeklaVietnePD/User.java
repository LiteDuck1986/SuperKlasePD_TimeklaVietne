package timeklaVietnePD;

public class User extends VietnesApmekletajs{
	
	private static final long serialVersionUID = 1L;
	
	private String epasts;

	public User(int DzGads, int Nosutiti, String lietVards, String parole, String vards, String uzvards,
			String valsts, String epasts) {
		super(DzGads, Nosutiti, lietVards, parole, vards, uzvards, valsts, epasts);
		this.epasts = epasts;
	}

	public String getEpasts() {
		return epasts;
	}

	public void setEpasts(String epasts) {
		this.epasts = epasts;
	}
	
	
	
	
	
} 