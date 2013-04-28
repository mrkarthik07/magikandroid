/**
 * 
 */
package com.magik.tasks;

import java.io.File;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import com.recomendacion.servicio.WebServiceConnection;

import android.os.AsyncTask;
import android.util.Base64;

/**
 * @author User
 *
 */
public class SendPDFTask extends AsyncTask<Object, Void, String> {

	
	
	
	private WebServiceConnection connection;
	
	public SendPDFTask()
	{
		
	}
	
	
	@Override
	protected String doInBackground(Object... params) {
		String resp = null;
		try 
		{
			File file = (File)params[0];
			//Comentar cuando le pasen el c�digo de los servicios.
			String url = "http://chimpaweb.com/K/prueba.php";
			HttpPost httpPost = new HttpPost(url);
			MultipartEntity entity = new MultipartEntity();
			entity.addPart("title", new StringBody(file.getName(), Charset.forName("UTF-8")));
			FileBody body = new FileBody(file);
			entity.addPart("file",body);
			
			httpPost.setEntity(entity);			
			HttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(httpPost);
			Header[] head = response.getAllHeaders();
			resp = String.valueOf(response.getStatusLine().getStatusCode());
			response.getEntity().consumeContent();
			
			//TODO
			/*
			 * Descomentar cuando le pasen el c�digo de los servicios.
			connection = WebServiceConnection.darInctancia();			
			String[] nParams = {"base64", "interes", "archivo"};
			Object[] p = new Object[3];
			byte[] data = FileUtils.readFileToByteArray(file);
			String base64 = Base64.encodeToString(data, Base64.DEFAULT);
			p[0] = base64;
			String interes = "LECTURA";
			p[1] = interes;
			String[] campos = file.getName().split(".");
			String archivo = campos[campos.length-1];
			p[2] = archivo;
			
			resp = (String) connection.accederServicio((String)params[1], nParams, p);			
			*/
			
			url = null;
			file = null;
			httpPost = null;
			entity = null;
			body = null;
			client = null;
			response = null;
			head = null;
			/*
			nParams = null;
			p = null;
			data = null;
			base64 = null;
			interes = null;
			campos = null;
			archivo = null;
			p = null;
			*/
			System.gc();
			
			
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		return resp;
	}
	
	

}