package com.sbchaoa_application.adminfragment;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sbchaoa_application.R;
import com.sbchaoa_application.R.id;
import com.sbchaoa_application.R.layout;
import com.sbchaoa_application.adapter.LazyAdapter;
import com.sbchaoa_application.common.FileUtility;
import com.sbchaoa_application.common.ServiceHandler;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class CreateNominationFragment extends Fragment implements OnClickListener {

	
	public CreateNominationFragment() {
	}

	private Button button;
	private EditText meditText;
	private ListView listView;
	private ProgressDialog pDialog;
	ArrayList<HashMap<String, String>> listCatagory;
	private static String getUrl = "http://172.16.35.244:8080/nominationcatagory/list";
	private static String postUrl ="http://172.16.35.244:8080/nominationcatagory/create";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_create_nomination, container,
				false);
		
		button = (Button) rootView.findViewById(R.id.create);
		meditText = (EditText) rootView.findViewById(R.id.edittext);
		listView = (ListView) rootView.findViewById(R.id.list);
		button.setOnClickListener(this);
		new GetContacts().execute();
		return rootView;
	}
	
	private class GetContacts extends AsyncTask<String, Void, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected String doInBackground(String... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(getUrl, ServiceHandler.GET);

			Log.d("Response: ", "> " + jsonStr);
			
		   listCatagory = new ArrayList<HashMap<String, String>>();
		 
			try {
				JSONArray jsonarray = new JSONArray(jsonStr);
				for (int i = 0; i < jsonarray.length(); i++) {
					JSONObject obj = jsonarray.getJSONObject(i);
					String name = obj.getString("catagoryName");
				HashMap<String,String> mCatagory = new HashMap<String, String>();
					mCatagory.put("catagoryName", name);

					listCatagory.add(mCatagory);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return jsonStr;
		}

		@Override
		protected void onPostExecute(String result) {
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
			if (result.equalsIgnoreCase("[]")) {
				Toast.makeText(getActivity(), "No Records Found", Toast.LENGTH_LONG).show();
			}
			
			
			listView.setAdapter(new LazyAdapter(getActivity(), listCatagory));
	
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

	/*private class HttpAsyncTask extends AsyncTask<String, Void, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected String doInBackground(String... urls) {

			JSONObject jsonObject = new JSONObject();

			try {

				jsonObject.accumulate("catagoryName", meditText.getText()
						.toString() );

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return FileUtility.POST(urls[0], jsonObject);
		}

		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
			if (pDialog.isShowing())
				pDialog.dismiss();
			try {
				JSONArray jsonarray = new JSONArray(result);
				for (int i = 0; i < jsonarray.length(); i++) {
					JSONObject obj = jsonarray.getJSONObject(i);
					String name = obj.getString("catagoryName");
					HashMap<String, String> mCatagory = new HashMap<String, String>();
					mCatagory.put("catagoryName", name);

					listCatagory.add(mCatagory);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ListAdapter adapter = new SimpleAdapter(getActivity(),
					listCatagory, R.layout.list_item,
					new String[] { "catagoryName" },
					new int[] { R.id.textView1 });
			new GetContacts().execute();
			
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		new HttpAsyncTask().execute(postUrl);
	}
	
	*/

}