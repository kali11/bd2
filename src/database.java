import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Formatter;


public class database {

	private Connection conn;
	private final String CONNECTION_URL = "jdbc:oracle:thin:@//ikar.elka.pw.edu.pl:1521/elka.elka.pw.edu.pl";
	public database(){
		
	}
	
	/**
	 * connect to db
	 * @return 1 if error else 0
	 */
	public boolean connect(){
		 try
	        {
	            Class.forName( "oracle.jdbc.driver.OracleDriver" ).newInstance();
	            this.conn = DriverManager.getConnection( CONNECTION_URL, "pkalino2", "pkalino2");
	        }catch( Exception e )
	        {
	            System.out.println( "B³¹d po³¹czenia" );
	            System.out.println( e.getMessage() );
	            e.printStackTrace();
	            return true;
	        }
		
		return false;
	}
	
	/**
	 * 
	 * @return 1 - user exists
	 * 0 - bad data
	 */
	public boolean checkLogin(String login, String pass){
		String sha = encryptPassword(pass);
		Statement stmt = null;
		int result = 0;
		String query = "SELECT count(*) FROM PKALINO2.\"Uzytkownik\" WHERE LOGIN='"+login+"' AND PASSWORD='"+sha+"'";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				 System.out.println(rs.getInt(1));
				 result = rs.getInt(1);
			}
			
		stmt.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result == 1) return true;
		return false;
	}
	
	
	public void commit(){
		Statement stmt = null;
		String query ="INSERT INTO \"PKALINO2\".\"Incydent\" (\"id_incydent\", \"id_rodzaj\", \"id_status\", \"id_pracownik\", \"id_uzytkownik\", \"id_oprogramowanie\", \"numer seryjny\", \"opis\", \"data zgloszenia\", \"data realizacji\", ID_MARKA, ID_MODEL) VALUES(0,)";
		try {
			stmt = conn.createStatement();
			stmt.executeQuery(query);
			stmt.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * get all types
	 * @return ArrayList types
	 */
	public ArrayList<String> getAllTypes(){
		Statement stmt = null;
		ArrayList<String> wynik = new ArrayList<String>();
		String query = "SELECT * FROM PKALINO2.\"Typ sprzetu\"";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				 //System.out.println(rs.getNString(2));
				 wynik.add(rs.getNString(2));
			}
			
		stmt.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wynik;
	}
	
	/**
	 * get all comapnies
	 * @return ArrayList Companies
	 */
	public ArrayList<String> getAllCompanies(){
		Statement stmt = null;
		ArrayList<String> wynik = new ArrayList<String>();
		String query = "SELECT * FROM PKALINO2.\"Marka sprzetu\"";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				// System.out.println(rs.getNString(2));
				 wynik.add(rs.getNString(2));
			}
			
		stmt.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wynik;
	}
	
	/**
	 * get all models
	 * @return ArrayList Models
	 */
	public ArrayList<String> getAllModels(){
		Statement stmt = null;
		ArrayList<String> wynik = new ArrayList<String>();
		String query = "SELECT \"nazwa_model\" FROM PKALINO2.\"Model sprzetu\"";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				 //System.out.println(rs.getNString(1));
				 wynik.add(rs.getNString(1));
			}
			
		stmt.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wynik;
		
	}
	
	public ArrayList<String> getAllDevices(){
		Statement stmt = null;
		ArrayList<String> wynik = new ArrayList<String>();
		String query = "SELECT \"numer seryjny\" FROM PKALINO2.\"Urzadzenie\"";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				 //System.out.println(rs.getNString(1));
				 wynik.add(rs.getNString(1));
			}
			
		stmt.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wynik;
		
	}
	
	public ArrayList<String> getAllProducers(){
		Statement stmt = null;
		ArrayList<String> wynik = new ArrayList<String>();
		String query = "SELECT * FROM PKALINO2.\"Producent\"";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				 //System.out.println(rs.getNString(2));
				 wynik.add(rs.getNString(2));
			}
			
		stmt.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wynik;
	}
	
	public ArrayList<String> getAllSoftware(){
		Statement stmt = null;
		ArrayList<String> wynik = new ArrayList<String>();
		String query = "SELECT \"nazwa_oprogramowanie\" FROM PKALINO2.\"Oprogramowanie\"";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				 //System.out.println(rs.getNString(1));
				 wynik.add(rs.getNString(1));
			}
			
		stmt.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wynik;
	}
	public ArrayList<String> getCompaniesByType(String type){
		Statement stmt = null;
		ArrayList<String> wynik = new ArrayList<String>();
		String query = "SELECT distinct \"nazwa_marka\" from PKALINO2.\"Model sprzetu\" m join PKALINO2.\"Typ sprzetu\" t on t.\"id_typ\" =  m.\"id_typ\" join PKALINO2.\"Marka sprzetu\" s on m.\"id_marka\" = s.\"id_marka\" where t.\"nazwa_sprzêt\" = '"+type+"'";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				 //System.out.println(rs.getNString(1));
				 wynik.add(rs.getNString(1));
			}
			
		stmt.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wynik;
		
		
	}
	/**
	 * encryption functions
	 * @param String password
	 * @return String
	 */
	private static String encryptPassword(String password)
	{
	    String sha1 = "";
	    try
	    {
	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	        crypt.reset();
	        crypt.update(password.getBytes("UTF-8"));
	        sha1 = byteToHex(crypt.digest());
	    }
	    catch(NoSuchAlgorithmException e)
	    {
	        e.printStackTrace();
	    }
	    catch(UnsupportedEncodingException e)
	    {
	        e.printStackTrace();
	    }
	    return sha1;
	}

	private static String byteToHex(final byte[] hash)
	{
	    Formatter formatter = new Formatter();
	    for (byte b : hash)
	    {
	        formatter.format("%02x", b);
	    }
	    String result = formatter.toString();
	    formatter.close();
	    return result;
	}
}
