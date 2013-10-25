package pxl.rest.client;

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

import pxl.cloudcal.MainActivity;
import pxl.model.Item;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.util.Log;

public class ItemRestClient {

	private final String BASE_URL;
	private final String ADD_Item;
	private static ItemRestClient crl = null;

	private ItemRestClient() {
		//10.0.2.2 is the localhost address. If you type localhost this will be the localhost of the emulator.
		this.BASE_URL = "http://10.0.2.2:8084/WSCloudCal/webresources/Items";
		this.ADD_Item = "/addItem";
	}
	
	public static ItemRestClient getInstance() {
		if(crl == null) {
			crl = new ItemRestClient();
		}
		return crl;
	}
	
	public void addItem(Item item) {
		Gson gson = new Gson();
		String jsonObj = gson.toJson(item);
		HttpClient client = new DefaultHttpClient();
		HttpPut httpPut = new HttpPut(BASE_URL + ADD_Item);
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

	public List<Item> getItems() {
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
				Type collectionType = new TypeToken<Collection<Item>>() {
				}.getType();
				Collection<Item> Items = gson.fromJson(
						builder.toString(), collectionType);
				List<Item> cList = new ArrayList<Item>(Items);
				return cList;
			} else {
				Log.e(MainActivity.class.toString(),
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
