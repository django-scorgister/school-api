package net.argus.school.api;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

import net.argus.instance.Instance;
import net.argus.school.api.http.APIHandler;
import net.argus.school.api.http.APIServer;
import net.argus.school.api.http.FileHandler;
import net.argus.school.api.http.api.APIMaterialHandler;
import net.argus.school.api.http.api.APIMaterialsHandler;
import net.argus.school.api.http.api.APIQuantityHandler;
import net.argus.school.api.http.api.APIStudentsHandler;
import net.argus.school.api.http.api.APIUploadStudentHandler;
import net.argus.school.api.http.api.APIVersionHandler;
import net.argus.util.Version;

public class MainAPI {
	
	public static final Version VERSION = new Version("1.0a");
	
	public static void main(String[] args) throws IOException {	
		APIServer srv = new APIServer();
		
		srv.addHandle(new APIVersionHandler());
		srv.addHandle(new APIUploadStudentHandler());
		
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
		srv.addHandle(new FileHandler(Instance.SYSTEM.getRootPath() + "/www/"));
		
		
		
		srv.start();
	}

}
