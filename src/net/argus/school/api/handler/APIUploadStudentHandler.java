package net.argus.school.api.handler;

import net.argus.instance.Instance;
import net.argus.web.http.api.formdata.APIFormDataHandler;

public class APIUploadStudentHandler extends APIFormDataHandler {

	//100 x 100 img
	
	public APIUploadStudentHandler() {
		super("upload/student", Instance.SYSTEM.getRootPath() + "/www/images/student/");
	}

}
