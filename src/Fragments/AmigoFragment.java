package Fragments;

import java.io.File;
import java.util.ArrayList;

import Modelos.Amigo;
import Modelos.Evento;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.br.trustyou.MainActivity;
import com.br.trustyou.R;

public class AmigoFragment extends Fragment implements OnClickListener {

	private final int REQUEST_CAMERA = 100;
	private final int TAKE_PICTURE = 115;

	Amigo amigo;
	TextView tvNome;
	TextView tvSaldo;
	ImageButton ibFoto;
	Button btSalvarEvento;
	LinearLayout llEventos;
	public ArrayList<Evento> eventos;

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
		tvSaldo = (TextView) getActivity().findViewById(R.id.tvSaldo);
		ibFoto = (ImageButton) getActivity().findViewById(R.id.ibFoto);
		btSalvarEvento = (Button) getActivity().findViewById(
				R.id.btSalvarEvento);
		ibFoto.setOnClickListener(this);
		btSalvarEvento.setOnClickListener(this);
		llEventos = (LinearLayout) getActivity().findViewById(R.id.llEventos);
		eventos = amigo.getEventos();

		setaFoto();
		desenhaTela();
		super.onActivityCreated(savedInstanceState);
	}

	private void desenhaTela() {
		tvNome.setText("Nome: " + amigo.getNome());
		int saldo = 0;
		for (Evento evento : eventos) {
			adicionaEventoNaTela(evento);
			saldo += evento.isEventoBom() ? evento.getGravidade() : -evento.getGravidade();
		}
		tvSaldo.setText("Saldo: " + saldo);

	}

	private void adicionaEventoNaTela(Evento evento) {
		final int posicao = eventos.indexOf(evento);
		LayoutInflater li = LayoutInflater.from(getActivity());
		final LinearLayout llEvento = (LinearLayout) li.inflate(
				R.layout.evento, llEventos, false);
		TextView tvNomeEvento = (TextView) llEvento
				.findViewById(R.id.tvNomeEvento);
		tvNomeEvento.setText(evento.getNomeEvento());
		TextView tvCaracteristicas = (TextView) llEvento
				.findViewById(R.id.tvCaracteristicas);
		tvCaracteristicas.setText("Evento "
				+ (evento.isEventoBom() ? "bom" : "ruim") + "; "
				+ "Gravidade: " + evento.getGravidade());
		TextView tvDescricao = (TextView) llEvento
				.findViewById(R.id.tvDescricao);
		tvDescricao.setText(evento.getDescricao());
		Button btPerdoar = (Button) llEvento.findViewById(R.id.btPerdoar);
		btPerdoar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				llEventos.removeView(llEvento);
				eventos.remove(posicao);
				llEventos.invalidate();
			}
		});
		llEvento.setTag(evento.getId());
		llEventos.addView(llEvento);
	}

	private void setaFoto() {
		long idFoto = amigo.getIdFoto();
		if (idFoto != 0) {
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
		case R.id.btSalvarEvento:
			LinearLayout llNovoEvento = (LinearLayout) getActivity()
					.findViewById(R.id.llNovoEvento);
			String descricao = ((EditText) llNovoEvento
					.findViewById(R.id.etDescricao)).getText().toString();
			boolean eventoBom = ((CheckBox) llNovoEvento
					.findViewById(R.id.cbEventoBom)).isChecked();
			String gravidade = ((EditText) llNovoEvento
					.findViewById(R.id.etGravidade)).getText().toString();

			String nomeEvento = ((EditText) llNovoEvento
					.findViewById(R.id.etNomeEvento)).getText().toString();
			Evento ev = new Evento();

			if (!descricao.isEmpty() && !gravidade.isEmpty()
					&& !nomeEvento.isEmpty()) {

				ev.setDescricao(descricao);
				ev.setEventoBom(eventoBom);
				ev.setGravidade(Integer.parseInt(gravidade));
				ev.setNomeEvento(nomeEvento);
				amigo.getEventos().add(ev);

				((EditText) llNovoEvento.findViewById(R.id.etDescricao))
						.setText("");
				((EditText) llNovoEvento.findViewById(R.id.etDescricao))
						.setText("");
				((EditText) llNovoEvento.findViewById(R.id.etGravidade))
						.setText("");
				((CheckBox) llNovoEvento.findViewById(R.id.cbEventoBom))
						.setChecked(false);
				((EditText) llNovoEvento.findViewById(R.id.etNomeEvento))
						.setText("");
				adicionaEventoNaTela(ev);
				// llEventos.invalidate();

				Toast.makeText(getActivity(), "Evento Salvo",
						Toast.LENGTH_SHORT).show();

			} else {
				Toast.makeText(getActivity(),
						"Insira todos os campos para salvar o evento!",
						Toast.LENGTH_SHORT).show();
			}
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
				((MainActivity) getActivity()).getLvAmigos().invalidateViews();
			}
		}
	}
}
