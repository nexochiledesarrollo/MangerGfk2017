package cl.nexo.manager.obj.upload;

import java.util.ArrayList;
import java.util.List;

public class ObjStatusResponse {
	private Boolean success;
	private List<String> message;
	
	public ObjStatusResponse() {
		this.message = new ArrayList<String>();
	}
	
	public ObjStatusResponse(Boolean success) {
		super();
		this.success = success;
		this.message = new ArrayList<String>();
	}
	
	public ObjStatusResponse(Boolean success, String message) {
		super();
		this.success = success;
		this.message = new ArrayList<String>();
		this.message.add(message);
	}
	
	public ObjStatusResponse(Boolean success, List<String> message) {
		super();
		this.success = success;
		this.message = message;
	}
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	public List<String> getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message.add(message);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String mess: message) {
			sb.append(mess +", ");
		}
		
		return "StatusResponse [success=" + success + ", message=" + sb.toString() + "]";
	}
}
