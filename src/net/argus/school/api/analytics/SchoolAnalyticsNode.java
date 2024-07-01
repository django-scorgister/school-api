package net.argus.school.api.analytics;

import net.argus.analytics.client.AnalyticsContext;
import net.argus.analytics.client.AnalyticsNode;
import net.argus.cjson.CJSONBuilder;
import net.argus.cjson.value.CJSONInteger;
import net.argus.cjson.value.CJSONObject;
import net.argus.school.api.MainAPI;
import net.argus.web.http.CardinalHandler;

public class SchoolAnalyticsNode implements AnalyticsNode {

	@Override
	public CJSONObject analyse(AnalyticsContext context) {
		CJSONBuilder builder = new CJSONBuilder();

		for(CardinalHandler handler : MainAPI.getServeur().getHandlers()) {
			CJSONObject handlerObj = new CJSONObject();
			
			handlerObj.addItem("number_use", new CJSONInteger(handler.getNumberOfUse()));
			handlerObj.addItem("number_use_get", new CJSONInteger(handler.getNumberOfUseGET()));
			handlerObj.addItem("number_use_post", new CJSONInteger(handler.getNumberOfUsePOST()));
			
			builder.addObject(".", handler.getName(), handlerObj);
		}
		
		return builder.getMainObject();
	}

	@Override
	public String getNodeName() {
		return "school_node";
	}

}
