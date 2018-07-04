package org.opensrp.web.rest.rapid;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import org.joda.time.DateTime;
import org.opensrp.common.AllConstants.BaseEntity;
import org.opensrp.domain.Client;
import org.opensrp.domain.Event;
import org.opensrp.domain.Obs;
import org.opensrp.domain.RapidProContact;
import org.opensrp.search.EventSearchBean;
import org.opensrp.service.ClientService;
import org.opensrp.service.EventService;
import org.opensrp.util.DateTimeTypeConverter;
import org.opensrp.web.rest.RestResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.opensrp.common.AllConstants.Event.*;
import static org.opensrp.web.rest.RestUtils.*;

@Controller
@RequestMapping(value = "/rest/repidpro")
public class RapidProResource {

	private static Logger logger = LoggerFactory.getLogger(RapidProResource.class.toString());

	private EventService eventService;

	private ClientService clientService;

	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
			.registerTypeAdapter(DateTime.class, new DateTimeTypeConverter()).create();

	@Autowired
	public RapidProResource(ClientService clientService, EventService eventService) {
		this.clientService = clientService;
		this.eventService = eventService;
	}

	@RequestMapping(value = "/sync", method = RequestMethod.GET)
	@ResponseBody
	protected ResponseEntity<String> sync(HttpServletRequest request) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			String serverVersion = getStringFilter(BaseEntity.SERVER_VERSIOIN, request);
			String eventType = getStringFilter(EVENT_TYPE, request);

			Long lastSyncedServerVersion = null;
			if (serverVersion != null) {
				lastSyncedServerVersion = Long.valueOf(serverVersion) + 1;
			}
			Integer limit = getIntegerFilter("limit", request);
			if (limit == null || limit.intValue() == 0) {
				limit = 25;
			}

			List<Event> events;
			List<RapidProContact> rapidProContacts = new ArrayList<>();
			RapidProContact rapidProContact;
			Client contactMother;
			List<Obs> obs;

			if (eventType != null || serverVersion != null) {
				EventSearchBean eventSearchBean = new EventSearchBean();
				eventSearchBean.setServerVersion(lastSyncedServerVersion);
				eventSearchBean.setEventType(eventType);
				events = eventService.findEvents(eventSearchBean, BaseEntity.SERVER_VERSIOIN, "asc", limit);

				if (!events.isEmpty()) {
					for (Event event : events) {
						if (event.getBaseEntityId() != null && !event.getBaseEntityId().isEmpty()
								&& event.getEventType() != null && event.getEventType().equals(BIRTH_REGISTRATION)) {
							contactMother = clientService.getByBaseEntityId(
									clientService.getByBaseEntityId(event.getBaseEntityId()).getRelationships().get("mother")
											.get(0));
							rapidProContact = new RapidProContact();
							rapidProContact.setMotherName(contactMother.getFirstName() + " " + contactMother.getLastName());
							rapidProContact.setMotherZeirID(contactMother.getIdentifier("M_ZEIR_ID"));

							obs = event.getObs();
							for (Obs obs2 : obs) {
								if (obs2 != null && obs2.getFieldType().equals("concept") && obs2.getFormSubmissionField()
										.equals("Mother_Guardian_Number") && obs2.getValue() != null) {
									rapidProContact.setMotherTel(obs2.getValue().toString());

								}
							}
							rapidProContacts.add(rapidProContact);
						}
					}
				}
			}

			JsonArray mVaccArray = (JsonArray) gson.toJsonTree(rapidProContacts, new TypeToken<List<RapidProContact>>() {

			}.getType());

			response.put("contacts", mVaccArray);

			return new ResponseEntity<>(gson.toJson(response), HttpStatus.OK);
		}
		catch (Exception e) {
			response.put("msg", "Error occurred");
			logger.error("", e);
			return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
