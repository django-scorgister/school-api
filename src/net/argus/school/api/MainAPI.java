package net.argus.school.api;

import java.awt.Desktop;
import java.io.IOException;
import java.net.BindException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;

import net.argus.analytics.client.AnalyticsModule;
import net.argus.analytics.client.AnalyticsSender;
import net.argus.analytics.client.DefaultAnalyticsSender;
import net.argus.instance.CardinalProgram;
import net.argus.instance.Instance;
import net.argus.instance.Program;
import net.argus.plugin.InitializationPlugin;
import net.argus.plugin.PluginEvent;
import net.argus.plugin.PluginRegister;
import net.argus.school.api.analytics.SchoolAnalyticsNode;
import net.argus.school.api.event.EventSchool;
import net.argus.school.api.event.SchoolEvent;
import net.argus.school.api.event.SchoolListener;
import net.argus.school.api.event.SchoolResetEntry;
import net.argus.school.api.handler.APIMaterialHandler;
import net.argus.school.api.handler.APIMaterialsHandler;
import net.argus.school.api.handler.APIPluginHandler;
import net.argus.school.api.handler.APIQuantityHandler;
import net.argus.school.api.handler.APIResetHandler;
import net.argus.school.api.handler.APIStudentsHandler;
import net.argus.school.api.handler.APIUploadMaterialHandler;
import net.argus.school.api.handler.APIUploadStudentHandler;
import net.argus.system.InitializationSystem;
import net.argus.util.Version;
import net.argus.util.debug.Debug;
import net.argus.util.debug.Info;
import net.argus.web.http.APIHandler;
import net.argus.web.http.APIServer;
import net.argus.web.http.FileHandler;
import net.argus.web.http.api.APIExitHandler;
import net.argus.web.http.api.APIVersionHandler;

@Program(instanceName = "school-main")
public class MainAPI extends CardinalProgram {
	
	public static final Version VERSION = new Version("1.2.2b");
	
	private static final EventSchool EVENT = new EventSchool();

	
	public static final AnalyticsModule ANALYTICS_MODULE = new AnalyticsModule("school-analytics", AnalyticsModule.DEFAULT_INTERVAL);
	public static final AnalyticsSender ANALYTICS_SENDER = new DefaultAnalyticsSender(ANALYTICS_MODULE, "localhost");
	
	private static APIServer serveur;
	private static boolean alreadyStarted;
	
	public void main(String[] args) {
		InitializationSystem.initSystem(args);
		InitializationPlugin.register();
		
		ANALYTICS_MODULE.addNode(new SchoolAnalyticsNode());
		
		try {
			PluginRegister.preInit(new PluginEvent(this));

			serveur = new APIServer();			
			
			serveur.addHandle(new APIVersionHandler());
			serveur.addHandle(new APIPluginHandler());
			serveur.addHandle(new APIExitHandler());
			serveur.addHandle(new APIUploadStudentHandler());
			serveur.addHandle(new APIUploadMaterialHandler());
			
			serveur.addHandle(new APIResetHandler());
			
			serveur.addHandle(new APIStudentsHandler());
			serveur.addHandle(new APIMaterialsHandler());
			serveur.addHandle(new APIMaterialHandler());
			serveur.addHandle(new APIQuantityHandler());
			serveur.addHandle(new APIHandler("") {
	
				@Override
				public void doGet(HttpExchange exchange) throws IOException {
					send404(exchange);
				}
	
				@Override
				public void doPost(HttpExchange exchange) throws IOException {
					doGet(exchange);
				}
			});

			serveur.addHandle(new FileHandler("/school/", Instance.SYSTEM.getRootPath() + "/www/"));
			serveur.addHandle(new FileHandler("", Instance.SYSTEM.getRootPath() + "/res/"));
			
			addSchoolListener(new BasicSchoolListener());
			
			PluginRegister.init(new PluginEvent(serveur));
			
			
			serveur.start();
		} catch(IOException e) {
			if(e instanceof BindException) {
				alreadyStarted = true;
				Debug.log("Server already start", Info.ERROR);
			}else
				e.printStackTrace();
		}
		
		try {
			Desktop.getDesktop().browse(new URI("http://localhost:8000/school/"));
		}catch(IOException | URISyntaxException e) {e.printStackTrace();}
		
		PluginRegister.postInit(new PluginEvent(this));

		ANALYTICS_MODULE.start();
		ANALYTICS_SENDER.startSenderLoop();
	}
	
	public static void addSchoolListener(SchoolListener listener) {
		EVENT.addListener(listener);
	}
	
	public static void invokeEvent(int event, SchoolEvent e) {
		EVENT.invokeEvent(event, e);
	}
	
	public static List<List<SchoolResetEntry>> invokeResetRequestEvent(SchoolEvent e) {
		return EVENT.invokeResetRequestEvent(e);
	}
	
	public static boolean isAlreadyStarted() {
		return alreadyStarted;
	}
	
	public static APIServer getServeur() {
		return serveur;
	}

}
