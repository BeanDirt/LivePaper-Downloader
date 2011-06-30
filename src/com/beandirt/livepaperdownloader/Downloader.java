// http://api.flickr.com/services/rest/?method=flickr.test.echo&name=value
// 8f83b57f890d9f2e358d8a111a9b809f
// ea4b47fa2b62c7cf


package com.beandirt.livepaperdownloader;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.beandirt.livepaperdownloader.FlickrWebService.PostMethod;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Downloader extends Activity {
	
	private static final String TAG = "Downloader";
	private static String FROB;
	private List<PhotoSet> photosets;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void completeAuthorization(View v){
    	AsyncTask<Object, Object, JSONObject> getAuthToken = new AsyncTask<Object, Object, JSONObject>() {

    		@Override
    		protected JSONObject doInBackground(Object... params) {
    			FlickrWebService service = new FlickrWebService(FROB);
    			return service.execute(PostMethod.GET_AUTH_TOKEN);
    		}
    		
    		@Override
    		protected void onPostExecute(JSONObject response) {
    			Log.d(TAG, response.toString());
    		}
    	};
        
    	getAuthToken.execute();
    }
    
    public void authorize(View v){
    	AsyncTask<Object, Object, JSONObject> getLoginLink = new AsyncTask<Object, Object, JSONObject>() {

    		@Override
    		protected JSONObject doInBackground(Object... params) {
    			FlickrWebService service = new FlickrWebService();
    			return service.execute(PostMethod.GET_FROB);
    		}
    		
    		@Override
    		protected void onPostExecute(JSONObject response) {
				try {
					
					String url = response.getString("url")+ "?" + "api_key=" +  
									response.getString("api_key") + "&perms=" + 
									response.getString("perms") + "&frob=" + 
									response.getString("frob") + "&api_sig=" + 
									response.getString("api_sig");
					FROB = response.getString("frob");
					
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
					startActivity(browserIntent);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Log.d(TAG, response.toString());
    		}
    	};
        
    	getLoginLink.execute();
    }
    
    public void getPhotosets(View v){
    	Intent intent = new Intent(this, Collections.class);
    	startActivity(intent);
    }
}