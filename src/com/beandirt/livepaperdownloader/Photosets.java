package com.beandirt.livepaperdownloader;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.beandirt.livepaperdownloader.FlickrWebService.PostMethod;

public class Photosets extends ListActivity {

	private static final String TAG = "Collections"; 
	
	List<PhotoSet> photosets;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photosets);
		
		AsyncTask<Object, Object, JSONObject> getCollections = new AsyncTask<Object, Object, JSONObject>() {

    		@Override
    		protected JSONObject doInBackground(Object... params) {
    			FlickrWebService service = new FlickrWebService();
    			return service.execute(PostMethod.GET_PHOTOSET_LIST);
    		}
    		
    		@Override
    		protected void onPostExecute(JSONObject response) {
    			try {
    				JSONArray responseArray = response.getJSONObject("photosets").getJSONArray("photoset");
    				photosets = new ArrayList<PhotoSet>();
    				for(int i = 0; i < responseArray.length(); i++){
    					String id = responseArray.getJSONObject(0).getString("id");
    					String name = responseArray.getJSONObject(0).getJSONObject("title").getString("_content");
    					//photosets.add(new PhotoSet(id, name, description));
    					populateList();
    				}
    			} catch (JSONException e) {
    				e.printStackTrace();
    			}
    		}
    	};
    	getCollections.execute();
	}
	
	private void populateList(){
		setListAdapter(new ArrayAdapter<PhotoSet>(this, R.layout.list_photoset_item, photosets){
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent){
				View v = convertView;
				if(v == null){
					LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					v = vi.inflate(R.layout.list_photoset_item, null);
				}
				
				PhotoSet photoset = photosets.get(position);
				TextView label = (TextView) v.findViewById(R.id.set_name);
				label.setText(photoset.getName());
				return v;
			}
		});
	}
}
