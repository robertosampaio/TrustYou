package com.br.trustyou;

import java.util.ArrayList;
import java.util.List;

import Modelos.Amigo;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AmigosAdapter extends BaseAdapter {

	List<Amigo> amigos = new ArrayList<Amigo>();
	Activity activity;
	
	public AmigosAdapter(Activity activity, ArrayList<Amigo> amigos) {
		this.activity = activity;
		this.amigos = amigos;
	}
	
	public void addAmigo(Amigo amigo){
		amigos.add(amigo);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return amigos.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return amigos.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = ((Activity) activity).getLayoutInflater();
			convertView = inflater.inflate(R.layout.lv_item, parent, false);
		}

		TextView textViewItem = (TextView) convertView
				.findViewById(R.id.tvText);
		textViewItem.setText(amigos.get(position).getNome());
		
		return convertView;
	}

}
