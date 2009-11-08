package pl.edu.agh.iosr.controller;

/**
 * Mediator, koordynuje dzia�ania innych komponent�w,
 * nale�y unika� dodawania tu logiki za wyj�tkiem sterowania
 * */
public class TranslationMediator {
	
	private ConfigurationStorage configurationStorage;

	public ConfigurationStorage getConfigurationStorage() {
		return configurationStorage;
	}

	public void setConfigurationStorage(ConfigurationStorage configurationStorage) {
		this.configurationStorage = configurationStorage;
	}
	
}
