package DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.ReceptDTO;
import DTO.ReceptKompDTO;
import interfaces.DALException;
import interfaces.ReceptDAO;

public class ReceptContext implements ReceptDAO{
	Connector c = new Connector();
	ReceptKompContext rkc = new ReceptKompContext();

	@Override
	public ReceptDTO getRecept(int receptID) throws DALException {
		// Convert to Data Transfer Object
		ReceptDTO recept = null;

		//This function returns a receptDTO with the a chosen recpetID
		try {
			String query = "select recept_navn from recept_administration where recept_id ="+ 
					receptID+ " group by recept_navn";
			ResultSet result = c.doQuery(query);

			// is there a next row
			if(result.next()){
				recept = new ReceptDTO(receptID, result.getString(1));
			}
		} catch (Exception e) {
			System.out.println("Error in receiving table. ");
		}
		// return operator.
		return recept;

	}

	@Override
	public List<ReceptDTO> getReceptList() throws DALException {
		String query = "select recept_navn, recept_id from recept_administration group by recept_id";
		ResultSet result = c.doQuery(query);

		//This function returns a list of all recepts in the database
		List<ReceptDTO> list = new ArrayList<ReceptDTO>();
		try{
			while(result.next()) {
				list.add(new ReceptDTO(result.getInt(2), result.getString(1)));
			}	
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	//This function is used to create a recept, it needs an arraylist of receptkomponenter and a recept DTO
	@Override
	public void createRecept(ReceptDTO recept, ArrayList<ReceptKompDTO> komp) throws DALException {
		String queryRecept = "call opret_recept(" + recept.getReceptID() + ", '" + recept.getReceptNavn() + "');";
		c.doQuery(queryRecept);
		
		for (int i = 0; i < komp.size(); i++) {
			rkc.createReceptKomp(komp.get(i));
		}
	}
	
	//This function changes the name of a recept
	@Override
	public void updateRecept(ReceptDTO recept) throws DALException {
		int ID = getRecept(recept.getReceptID()).getReceptID();
		String query = "call aendre_recept(" + ID + ", '" + recept.getReceptNavn() + "');";
		c.doQuery(query);
	}


}
