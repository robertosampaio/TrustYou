package com.br.trustyou;

import java.util.ArrayList;

import Fragments.AmigoFragment;
import Modelos.Amigo;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {
	FrameLayout flContainer;
	private Amigo amigo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		ListView lvAmigos;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
			ArrayList<Amigo> amigos = new ArrayList<Amigo>();
			AmigosAdapter amigosAdapter = new AmigosAdapter(getActivity(),
					amigos);
			lvAmigos = (ListView) getActivity().findViewById(R.id.lvAmigos);
			lvAmigos.setAdapter(amigosAdapter);
			amigosAdapter.amigos.add(new Amigo("Roberto"));
			((MainActivity) getActivity()).setAmigo(((AmigosAdapter) amigosAdapter).amigos.get(0));
			setClickListener();
		}

		public void setClickListener() {
			lvAmigos.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
					((MainActivity) getActivity()).setAmigo(((AmigosAdapter) parent.getAdapter()).amigos.get(position));
					
					FragmentManager fragmentManager = getActivity()
							.getFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager
							.beginTransaction();
					
					AmigoFragment fragment = new AmigoFragment();
					fragmentTransaction.replace(R.id.flContainer, fragment);
					fragmentTransaction.commit();
					
				}
			});
		}
	}
	
	public Amigo getAmigo(){
		return amigo;
	}
	
	public void setAmigo(Amigo amigo){
		this.amigo = amigo;
	}
}
