package pxl.kbw.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.util.Log;

public class ContactsRestClient {

	private final String BASE_URL;
	private final String ADD_CONTACT;
	private static ContactsRestClient crl = null;

	private ContactsRestClient() {
		//10.0.2.2 is the localhost address. If you type localhost this will be the localhost of the emulator.
		this.BASE_URL = "http://10.0.2.2:8084/ContactsWSTomcat/webresources/contacts";
		this.ADD_CONTACT = "/addcontact";
	}
	
	public static ContactsRestClient getInstance() {
		if(crl == null) {
			crl = new ContactsRestClient();
		}
		return crl;
	}
	
	public void addContact(Contact contact) {
		Gson gson = new Gson();
		String jsonObj = gson.toJson(contact);
		HttpClient client = new DefaultHttpClient();
		HttpPut httpPut = new HttpPut(BASE_URL + ADD_CONTACT);
		httpPut.addHeader("Content-Type", "application/json");
		httpPut.addHeader("Accept", "application/json");
		try {
			httpPut.setEntity(new StringEntity(jsonObj));
			client.execute(httpPut);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Contact> getContacts() {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(BASE_URL);
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
				Gson gson = new Gson();
				Type collectionType = new TypeToken<Collection<Contact>>() {
				}.getType();
				Collection<Contact> contacts = gson.fromJson(
						builder.toString(), collectionType);
				List<Contact> cList = new ArrayList<Contact>(contacts);
				return cList;
			} else {
				Log.e(ContactListActivity.class.toString(),
						"Failed to download file");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
