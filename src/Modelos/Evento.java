package Modelos;

public class Evento {

	private long id;
	private long idAmigo;
	private boolean eventoBom;
	private int gravidade;
	private String nomeEvento;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdAmigo() {
		return idAmigo;
	}

	public void setIdAmigo(long idAmigo) {
		this.idAmigo = idAmigo;
	}

	public boolean isEventoBom() {
		return eventoBom;
	}

	public void setEventoBom(boolean eventoBom) {
		this.eventoBom = eventoBom;
	}

	public int getGravidade() {
		return gravidade;
	}

	public void setGravidade(int gravidade) {
		this.gravidade = gravidade;
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;

	public Evento() {
		// TODO Auto-generated constructor stub
	}
}
