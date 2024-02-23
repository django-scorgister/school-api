package net.argus.school.api.handler;

import net.argus.instance.Instance;
import net.argus.web.http.api.formdata.APIFormDataHandler;

public class APIUploadMaterialHandler extends APIFormDataHandler {

	public APIUploadMaterialHandler() {
		super("upload/material", Instance.SYSTEM.getRootPath() + "/www/images/material/");
	}

}
