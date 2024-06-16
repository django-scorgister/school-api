package net.argus.school.api;

import java.awt.Desktop;
import java.io.IOException;
import java.net.BindException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;

import net.argus.instance.CardinalProgram;
import net.argus.instance.Instance;
import net.argus.instance.Program;
import net.argus.plugin.InitializationPlugin;
import net.argus.plugin.PluginEvent;
import net.argus.plugin.PluginRegister;
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
	
	public static final Version VERSION = new Version("1.2.1");
	
	private static final EventSchool EVENT = new EventSchool();
	
	public void main(String[] args) {
		InitializationSystem.initSystem(args);
		InitializationPlugin.register();
		try {
			PluginRegister.preInit(new PluginEvent(this));

			APIServer srv = new APIServer();			
			
			srv.addHandle(new APIVersionHandler());
			srv.addHandle(new APIPluginHandler());
			srv.addHandle(new APIExitHandler());
			srv.addHandle(new APIUploadStudentHandler());
			srv.addHandle(new APIUploadMaterialHandler());
			
			srv.addHandle(new APIResetHandler());

			
			srv.addHandle(new APIStudentsHandler());
			srv.addHandle(new APIMaterialsHandler());
			srv.addHandle(new APIMaterialHandler());
			srv.addHandle(new APIQuantityHandler());
			srv.addHandle(new APIHandler("") {
	
				@Override
				public void doGet(HttpExchange exchange) throws IOException {
					send404(exchange);
				}
	
				@Override
				public void doPost(HttpExchange exchange) throws IOException {
					doGet(exchange);
				}
			});

			srv.addHandle(new FileHandler("/school/", Instance.SYSTEM.getRootPath() + "/www/"));
			srv.addHandle(new FileHandler("", Instance.SYSTEM.getRootPath() + "/res/"));
			
			addSchoolListener(new BasicSchoolListener());
			
			PluginRegister.init(new PluginEvent(srv));
			
			
			srv.start();
		} catch(IOException e) {
			if(e instanceof BindException) {
				Debug.log("Server already start", Info.ERROR);
			}else
				e.printStackTrace();
		}
		
		try {
			Desktop.getDesktop().browse(new URI("http://localhost:8000/school/"));
		}catch(IOException | URISyntaxException e) {e.printStackTrace();}
		
		PluginRegister.postInit(new PluginEvent(this));
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

}
