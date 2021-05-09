package no.hvl.dat110.ac.restservice;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.gson.Gson;

public class AccessLog {

	// atomic integer used to obtain identifiers for each access entry
	private AtomicInteger cid;
	protected ConcurrentHashMap<Integer, AccessEntry> log;

	public AccessLog() {
		this.log = new ConcurrentHashMap<Integer, AccessEntry>();
		cid = new AtomicInteger(0);
	}

	// TODO: add an access entry to the log for the provided message and return
	// assigned id
	public int add(String message) {

		int id = cid.getAndIncrement();
		AccessEntry aEntry = new AccessEntry(id, message);
		log.put(id, aEntry);
		return id;
	}

	// TODO: retrieve a specific access entry from the log
	public AccessEntry get(int id) {
		AccessEntry aEntry = log.get(id);
		return aEntry;

	}

	// TODO: clear the access entry log
	public void clear() {
		cid.set(0);
		log.clear();
	}

	// TODO: return JSON representation of the access log
	public String toJson() {

		String json = null;
		int aEntryId = cid.get();
		json = "[";
		for (int i = 0; i < aEntryId; i++) {
			json += "{";
			json += "id: " + i + ",";
			json += "message: " + log.get(i).getMessage();
			json += "}";
			if (i != aEntryId - 1) {
				json += ",";
			}
		}
		json += "]";
		return json;
	}
}
