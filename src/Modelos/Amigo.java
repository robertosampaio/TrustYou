package Modelos;

import java.util.ArrayList;

public class Amigo {

	private long id;
	private long idFoto;
	private String nome;
	ArrayList<Evento> eventos;

	public Amigo() {
		eventos = new ArrayList<Evento>();
		idFoto = 0;

		Evento ev = new Evento();
		ev.setDescricao("Me deu um soco na cara");
		ev.setEventoBom(false);
		ev.setGravidade((short) 3);
		ev.setNomeEvento("Socão");

		eventos.add(ev);

	}

	public Amigo(String nome) {
		this();
		this.nome = nome;
	}

	public long getId() {
		return id;
	}

	public long getIdFoto() {
		return idFoto;
	}

	public void setIdFoto(long idFoto) {
		this.idFoto = idFoto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(ArrayList<Evento> eventos) {
		this.eventos = eventos;
	}
}
