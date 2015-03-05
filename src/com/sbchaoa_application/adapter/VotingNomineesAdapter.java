package com.sbchaoa_application.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sbchaoa_application.R;

public class VotingNomineesAdapter extends BaseAdapter {

	private Activity activity;
	private ArrayList<HashMap<String, String>> listCatagory;
	private LayoutInflater inflater = null;
	HashMap<String, String> mCatagory = new HashMap<String, String>();

	public VotingNomineesAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
		activity = a;
		listCatagory = d;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	public int getCount() {
		return listCatagory.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return getCustomView(position, convertView, parent);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return getCustomView(position, convertView, parent);
	}

	public View getCustomView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// return super.getView(position, convertView, parent);
		inflater = activity.getLayoutInflater();
		View row = inflater.inflate(R.layout.fragment_nominesslistinfo, parent, false);
		TextView name = (TextView) row.findViewById(R.id.name);
		
		mCatagory = listCatagory.get(position);
	
		name.setText(mCatagory.get("name"));
		
	

		return row;
	}
}
