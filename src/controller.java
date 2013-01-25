import java.util.ArrayList;


public class controller {

	private database db;
	public controller(){
		this.db = new database();	
		
	}
	
	public void init(){
		if(!db.connect()){
			print("polaczono!");
		}
		login("kali","kali");
		getAllTypes();
		getCompaniesByType("drukarka");
		getAllCompanies();
		getAllModels();
		getAllDevices();
		getAllProducers();
		getAllSoftware();
	}
	
	public void print(String s){
		System.out.println(s);
	}
	public void print(int i){
		System.out.println(i);
	}
	/**
	 * login user
	 * @param String login
	 * @param String password
	 * @return 0 - success, -1 - bad login or pass
	 */
	public boolean login(String login, String password){
		if(db.checkLogin(login, password)){
			print("User istnieje");
			return true;
		}else print("Zle dane");
		return false;
	}
	
	/**
	 * Get all device's types
	 * @return ArrayList of types
	 */
	public ArrayList<String> getAllTypes(){
		return db.getAllTypes();
	}
	
	public ArrayList<String> getAllCompanies(){
		return db.getAllCompanies();
	}
	
	public ArrayList<String> getCompaniesByType(String type){
		return db.getCompaniesByType(type);
	}
	
	public ArrayList<String> getAllModels(){
		return db.getAllModels();
	}
	
	public ArrayList<String> getAllDevices(){
		return db.getAllDevices();
	}
	
	public ArrayList<String> getAllProducers(){
		return db.getAllProducers();
	}
	
	public ArrayList<String> getAllSoftware(){
		return db.getAllSoftware();
	}
}
