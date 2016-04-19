package DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.ProduktBatchAdminDTO;
import interfaces.DALException;
import interfaces.ProduktBatchAdminDAO;

public class ProduktBatchAdminContext implements ProduktBatchAdminDAO{

	Connector c = new Connector();
	String query;

	@Override
	public ProduktBatchAdminDTO getProduktBatchAdmin(int PbId) throws DALException {

		// Query and get result
		query = "Select * From produktbatch_administration where pb_id = " + PbId;
		ResultSet result = c.doQuery(query);

		// Throw exception if no results found
		if(result == null){
			throw new DALException("No produktBatch found");
		}

		// Convert to Data Transfer Object
		ProduktBatchAdminDTO pba = null;
		try {
			// is there a next row
			if(result.next()){
				pba = new ProduktBatchAdminDTO(result.getInt(1), result.getInt(2),result.getInt(3), result.getDate(4),result.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// return operator.
		return pba;
	}


	@Override
	public List getOperatoerList() throws DALException {
		query = "Select * From produktbatch_administration";
		ResultSet result = c.doQuery(query);
		
		// Throw exception if no results found
		if(result == null){
			throw new DALException("No produktbatches found");
		}
		
		List<ProduktBatchAdminDTO> produktBatchAdmin = new ArrayList<ProduktBatchAdminDTO>();
		try {
			// is there a next row
			while(result.next()){
				produktBatchAdmin.add(new ProduktBatchAdminDTO(result.getInt(1), result.getInt(2),result.getInt(3), result.getDate(4),result.getInt(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return produktBatchAdmin;
	}

}