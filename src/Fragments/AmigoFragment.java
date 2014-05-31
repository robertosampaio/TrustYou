package Fragments;

import java.io.File;

import Modelos.Amigo;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.br.trustyou.MainActivity;
import com.br.trustyou.R;

public class AmigoFragment extends Fragment implements OnClickListener {

	private final int REQUEST_CAMERA = 100;
	private final int TAKE_PICTURE = 115;

	Amigo amigo;
	TextView tvNome;
	ImageButton ibFoto;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.tela_amigo, container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		amigo = ((MainActivity) getActivity()).getAmigo();
		tvNome = (TextView) getActivity().findViewById(R.id.tvNome);
		ibFoto = (ImageButton) getActivity().findViewById(R.id.ibFoto);
		setaFoto();
		ibFoto.setOnClickListener(this);

		tvNome.setText("Nome: " + amigo.getNome());
		super.onActivityCreated(savedInstanceState);
	}

	private void setaFoto() {
		long idFoto = amigo.getIdFoto();
		if (idFoto != 0) {
			Log.d("bla", "depois:" + idFoto);
			File f = new File(Environment.getExternalStorageDirectory(),
					"amigo_" + idFoto + ".jpg");
			Bitmap bm;
			BitmapFactory.Options btmapOptions = new BitmapFactory.Options();

			bm = BitmapFactory.decodeFile(f.getAbsolutePath(), btmapOptions);
			bm = Bitmap.createScaledBitmap(bm, 200, 200, true);

			ibFoto.setImageBitmap(bm);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ibFoto:
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			long idFoto = System.currentTimeMillis();
			File f = new File(Environment.getExternalStorageDirectory(),
					"amigo_" + idFoto + ".jpg");
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
			startActivityForResult(intent, REQUEST_CAMERA);
			amigo.setIdFoto(idFoto);
			break;
		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case REQUEST_CAMERA:
			if (resultCode == MainActivity.RESULT_OK) {
				setaFoto();
				Log.d("bla", "heuah");
			}
		}
	}
}
