package dk.dtu.cdiofinal.client.serverconnection;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.dtu.cdiofinal.shared.OperatoerDTO;

public interface ServiceClientInt {
	void isLoggedIn(AsyncCallback<Integer> callback);
	void login(int oprId, String password, AsyncCallback<Boolean> callback);
	void getOperators(AsyncCallback<List<OperatoerDTO>> callback);
	void getOperator(AsyncCallback<OperatoerDTO> callback);
	void updateOperator(OperatoerDTO opr, AsyncCallback<Boolean> callback);
	void createOperator(OperatoerDTO opr, AsyncCallback<Boolean> callback);	
}
