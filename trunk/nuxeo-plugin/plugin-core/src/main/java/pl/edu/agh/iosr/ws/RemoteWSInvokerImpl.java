package pl.edu.agh.iosr.ws;

import static pl.edu.agh.iosr.util.IosrLogger.log;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.contexts.Contexts;

import pl.edu.agh.iosr.controller.Mediator;
import pl.edu.agh.iosr.model.LangPair;
import pl.edu.agh.iosr.model.Operation;
import pl.edu.agh.iosr.model.TranslationOption;
import pl.edu.agh.iosr.model.TranslationOrder;
import pl.edu.agh.iosr.model.TranslationServiceDescription;
import pl.edu.agh.iosr.nuxeo.schema.translationresult.FileResultRequestWrapper;
import pl.edu.agh.iosr.nuxeo.schema.translator.LanguagePairs;
import pl.edu.agh.iosr.nuxeo.schema.translator.Operations;
import pl.edu.agh.iosr.nuxeo.schema.translator.Options;
import pl.edu.agh.iosr.nuxeo.schema.translator.SourceTypes;
import pl.edu.agh.iosr.nuxeo.schema.translator.TranslationQualities;
import pl.edu.agh.iosr.nuxeo.schema.translator.TranslationRequest;
import pl.edu.agh.iosr.nuxeo.wsdl.translationresult.TranslationResultPortType;
import pl.edu.agh.iosr.nuxeo.wsdl.translator.GoogleTranslatorService;
import pl.edu.agh.iosr.nuxeo.wsdl.translator.TranslatorPortType;
import pl.edu.agh.iosr.ws.translator.ResponseTranslator;
import pl.edu.agh.iosr.ws.translator.TranslationOrderTranslator;


/**
 * Implementacja interfejsu wywolujacego operacje na webservisach tlumaczacych,
 * klasa ta moze wywolywac operacje na wszystkich webservicach zgodnych z Translator.wsdl
 * @author lewickitom
 * */  
@Name("remoteWSInvoker")
@Scope(ScopeType.APPLICATION)
public class RemoteWSInvokerImpl implements RemoteWSInvoker {
	
	@In("#{mediator}")
	private Mediator mediator;
	
	private Service translationService=new GoogleTranslatorService();
	private ResponseTranslator responseTranslator=new ResponseTranslator();
	private TranslationOrderTranslator translationOrderTranslator=new TranslationOrderTranslator();
	private TranslatorPortType port;
	
	@Create
	public void init() {
		this.translationService=new GoogleTranslatorService();
		this.port=(TranslatorPortType)translationService.getPort(TranslatorPortType.class);	
	
	}
	/*
	public RemoteWSInvokerImpl()throws IllegalArgumentException{
			
		this.translationService=new GoogleTranslatorService();
		this.port=(TranslatorPortType)translationService.getPort(TranslatorPortType.class);	
		
	}*/
	
	private void setTranslationServiceEndpoint(String translationServiceEndpoint){
		
		BindingProvider bp=(BindingProvider)port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, translationServiceEndpoint);
    	
	
	}
	
	public List<String> getSuppportedFileFormats(
			TranslationServiceDescription webservice) {
		
			return null;
	}

	
	public List<Operation> getSuppportedOperations(
			TranslationServiceDescription webservice) {
		
	//	setTranslationServiceEndpoint(webservice.getEndpoint());
		Operations supportedOperations=port.getSupportedOperations("whatever");	
		return responseTranslator.translateOperations(supportedOperations);
	
	}

	
	public List<String> getSuppportedQualities(
			TranslationServiceDescription webservice) {
		setTranslationServiceEndpoint(webservice.getEndpoint());
		TranslationQualities translationQualities=port.getSupportedQualities("whatever");
		
		return responseTranslator.translateQualities(translationQualities);
		
	}

	
	public List<String> getSuppportedSourceTypes(
			TranslationServiceDescription webservice) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<TranslationOption> getSuppportedTranslationOptions(
			TranslationServiceDescription webservice) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<LangPair> getSuppportedTranslations(
			TranslationServiceDescription webservice) {
		
		setTranslationServiceEndpoint(webservice.getEndpoint());
		LanguagePairs languagePairs=port.getSupportedTranslations("whatever");
		return responseTranslator.translateLanguagePairs(languagePairs);
		
	}

	
	public List<Locale> getSuppportedTranslationsPerLanguage(
			TranslationServiceDescription webservice, Locale language) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void traslateAsync(TranslationServiceDescription webservice,
			TranslationOrder request, File content) {
		
		File f;
	      f=new File("findme");
	      if(!f.exists()){
	    	  try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      }
	  	if(mediator==null)		//tylko by przetestowac wstrzykiwanie
	  		log(this.getClass(), "\n\n MEDIATOR TO NULL\n\n");
	  	else
			log(this.getClass(), "\n\n MEDIATOR OK\n\n");
		  
	  	
	    request.setXliff(f.getAbsolutePath());
		TranslationRequest translationRequest=translationOrderTranslator.translate(request);
		//port.translate(translationRequest);
		SourceTypes sourceTypes=port.getSupportedSourceTypes(null);
		log(this.getClass(), "\n\n CALLED OK\n\n");
		log(this.getClass(), "\n\n "+sourceTypes.getSourceTypes().size() +"+OK\n\n");
		Options options=port.getSupportedOptions(null);
		log(this.getClass(), "\n\n OPTIONS CALLED OK\n\n");
		
		log(this.getClass(), "\n\n "+options.getOptions() +"+OK\n\n");
		port.translate(translationRequest);
		log(this.getClass(), "\n\n TRANSLATE CALLED+OK\n\n");
		
		
	}
	
	

	

}