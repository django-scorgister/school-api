package net.argus.school.api;

import java.awt.Desktop;
import java.io.IOException;
import java.net.BindException;
import java.net.URI;
import java.net.URISyntaxException;

import com.sun.net.httpserver.HttpExchange;

import net.argus.instance.CardinalProgram;
import net.argus.instance.Instance;
import net.argus.instance.Program;
import net.argus.plugin.InitializationPlugin;
import net.argus.plugin.PluginEvent;
import net.argus.plugin.PluginRegister;
import net.argus.school.api.http.APIHandler;
import net.argus.school.api.http.APIServer;
import net.argus.school.api.http.FileHandler;
import net.argus.school.api.http.api.APIExitHandler;
import net.argus.school.api.http.api.APIMaterialHandler;
import net.argus.school.api.http.api.APIMaterialsHandler;
import net.argus.school.api.http.api.APIPluginHandler;
import net.argus.school.api.http.api.APIQuantityHandler;
import net.argus.school.api.http.api.APIStudentsHandler;
import net.argus.school.api.http.api.APIUploadMaterialHandler;
import net.argus.school.api.http.api.APIUploadStudentHandler;
import net.argus.school.api.http.api.APIVersionHandler;
import net.argus.system.InitializationSystem;
import net.argus.util.Version;
import net.argus.util.debug.Debug;
import net.argus.util.debug.Info;

@Program(instanceName = "school-main")
public class MainAPI extends CardinalProgram {
	
	public static final Version VERSION = new Version("1.1.1");
	
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

}
