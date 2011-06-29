package com.beandirt.livepaperdownloader;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.beandirt.livepaperdownloader.FlickrWebService.PostMethod;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class Photosets extends ListActivity {

	List<PhotoSet> photosets;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photosets);
		
		AsyncTask<Object, Object, JSONObject> getPhotosets = new AsyncTask<Object, Object, JSONObject>() {

    		@Override
    		protected JSONObject doInBackground(Object... params) {
    			FlickrWebService service = new FlickrWebService();
    			return service.execute(PostMethod.GET_PHOTOSET_LIST);
    		}
    		
    		@Override
    		protected void onPostExecute(JSONObject response) {
    			try {
    				JSONArray responseArray = response.getJSONObject("photosets").getJSONArray("photoset");
    				List<PhotoSet> photosets = new ArrayList<PhotoSet>();
    				for(int i = 0; i < responseArray.length(); i++){
    					String id = responseArray.getJSONObject(0).getString("id");
    					String name = responseArray.getJSONObject(0).getJSONObject("title").getString("_content");
    					photosets.add(new PhotoSet(id, name));
    					populateList();
    				}
    			} catch (JSONException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    	};
    	getPhotosets.execute();
	}
	
	private void populateList(){
		setListAdapter(new ArrayAdapter<PhotoSet>(this, R.layout.list_photoset_item, photosets){
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent){
				View row = null;
				return row;
			}
		});
	}
}
