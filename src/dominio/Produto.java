package dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
/** 
 * Entidade que armazena os dados dos produtos cadastrados pelos usuários do sistema.
 * @author Débora
 */
@Entity
public class Produto {
	/** Produto associada ao usuário do sistema. */
	@ManyToOne
	@JoinColumn(name= "id_user")
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

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

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public int getLote() {
		return lote;
	}

	public void setLote(int lote) {
		this.lote = lote;
	}
	/** Colunas que deverão ser criadas no BD e seus getters e setters logo acima */
	@Id
    @GeneratedValue  
	@Column(name="id_produto", nullable = false)
	private int id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String preco;
	
	@Column(nullable = false)
	private Date validade;
	
	@Column(nullable = false)
	private int lote;
	
}
