package pl.edu.agh.iosr.controller;

/**
 * Zawiera informacje niezb�dne do wywo�ania zdalnego WSa
 * jak kto� b�dzie wiedzia� co dok�adnie, to jazda
 * */
public class RemoteWSDescription {

	private String name, endpoint, description;
	
	public RemoteWSDescription() {
		super();
	}
	
	public RemoteWSDescription(String name, String endpoint) {
		super();
		this.name = name;
		this.endpoint = endpoint;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
