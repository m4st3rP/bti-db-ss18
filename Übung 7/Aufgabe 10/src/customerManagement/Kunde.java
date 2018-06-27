package customerManagement;

/**
 * @author Finn-Frederik Jannsen, Philipp Schwarz
 * @version 1.0
 *
 *	Customer-Class representing one row from the Customer-Table (Database)
 */
public class Kunde {
	private int KdNr;
	private String Name;
	private String Street;
	private int HouseNr;
	private String Town;
	
	public Kunde(int kdNr, String name, String street, int houseNr,
			String town) {
		setKdNr(kdNr);
		setName(name);
		setStreet(street);
		setHouseNr(houseNr);
		setTown(town);
	}

	public int getKdNr() {
		return KdNr;
	}

	public void setKdNr(int kdNr) {
		KdNr = kdNr;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getStreet() {
		return Street;
	}

	public void setStreet(String street) {
		Street = street;
	}

	public int getHouseNr() {
		return HouseNr;
	}

	public void setHouseNr(int houseNr) {
		HouseNr = houseNr;
	}

	public String getTown() {
		return Town;
	}

	public void setTown(String town) {
		Town = town;
	}
	
	
}
