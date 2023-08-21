package net.argus.school.api.http.api;

import net.argus.instance.Instance;
import net.argus.school.api.http.api.formdata.APIFormDataHandler;

public class APIUploadMaterialHandler extends APIFormDataHandler {

	public APIUploadMaterialHandler() {
		super("upload/material", Instance.SYSTEM.getRootPath() + "/www/images/material/");
	}

}
