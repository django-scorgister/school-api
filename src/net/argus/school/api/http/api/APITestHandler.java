package net.argus.school.api.http.api;

import net.argus.instance.Instance;
import net.argus.school.api.http.api.formdata.APIFormDataHandler;

public class APITestHandler extends APIFormDataHandler {

	public APITestHandler() {
		super("test", Instance.SYSTEM.getRootPath() + "/test/");
	}

}
