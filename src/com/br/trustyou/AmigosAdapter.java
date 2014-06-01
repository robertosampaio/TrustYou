package com.br.trustyou;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Modelos.Amigo;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
	
	public void notificaMudanca(){
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Amigo amigo = amigos.get(position);
		String nome = amigo.getNome();
		long idFoto = amigo.getIdFoto();
		
		if (convertView == null) {
			LayoutInflater inflater = ((Activity) activity).getLayoutInflater();
			convertView = inflater.inflate(R.layout.lv_item, parent, false);
		}

		//coloca o nome
		TextView textViewItem = (TextView) convertView
				.findViewById(R.id.tvText);
		textViewItem.setText(nome);
		//
		
		//coloca a foto
		if(idFoto != 0){
			ImageView ivFoto = (ImageView) convertView.findViewById(R.id.ivFotoList);
			File f = new File(Environment.getExternalStorageDirectory(),
					"amigo_" + idFoto + ".jpg");
			Bitmap bm;
			BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
			bm = BitmapFactory.decodeFile(f.getAbsolutePath(), btmapOptions);
			bm = Bitmap.createScaledBitmap(bm, 200, 200, true);
			ivFoto.setImageBitmap(bm);
		}
		//
		
		return convertView;
	}
	
}
