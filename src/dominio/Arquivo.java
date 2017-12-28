package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Arquivo {
	@Id
    @GeneratedValue  
	@Column(name="id_arquivo", nullable = false)
	private int id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private byte[] bytes;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	
}
