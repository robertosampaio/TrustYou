package Modelos;

public class Amigo {

	private long id;
	private long idFoto;
	private String nome;

	public Amigo() {
		// TODO Auto-generated constructor stub
	}
	
	public Amigo(String nome){
		this.nome = nome;
		idFoto = 0;
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
}
