package dominio;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

import org.primefaces.model.UploadedFile;

import util.Criptografia;
import util.Validator;
/** 
 * Entidade que armazena os dados de um usuário do sistema.
 * @author Débora
 */

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Usuario implements ObjetoPersistivel {
	/** Colunas que deverão ser criadas no BD e seus getters e setters logo abaixo */
	@Id
    @GeneratedValue  
	@Column(name="id_usuario", nullable = false)
	private int id;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String senha;
	
	@Column(nullable=false)
	private String tipoUsuario;
	
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	@Column(name="nome", nullable = false)
	private String nome;
	
	@Column(name="rg", nullable = false)
	private String rg;
	
	@Column(name="cpf", nullable = false)
	private String cpf;
	
	@Column(name="nascimento", nullable = false)
	private Date nascimento;
	
	@Column(name="sexo", nullable = false)
	private char sexo;	
	@Column(nullable = false)
	private boolean ativo = true;

	@Column(name="id_foto")
	private Integer idFoto;
	
	@Transient
	private UploadedFile arquivo;
	
	@Transient
	private String novaSenhaConfirmacao;
	
	
	public String getDescricaoSexo(){
		if (Validator.isEmpty(sexo))
			return "-";
		else if (sexo =='M'){
			return "Masculino";
		} else {
			return "Feminino";
		}
	}
	public boolean isSexoMasculino(){
		if (Validator.isNotEmpty(sexo)&& sexo == 'M'){
			return true;
		} 
		return false;
	}

	
	public String getUrlFotoUsuario(){
		return "/verArquivo?"
				+ "idArquivo=" + getIdFoto() //id do arquivo
				+"&key=" + Criptografia.criptografarMD5(String.valueOf(getIdFoto())) 
				+ "&salvar=false"; 
	}
	/** Cria um código que identifica unicamente um usuário, auxiliando a verificar a 
	 * igualdade entre eles */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	/** Verifica se um usuário é igual a outro ou não */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}



	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
	public Integer getIdFoto() {
		return idFoto;
	}

	public void setIdFoto(Integer idFoto) {
		this.idFoto = idFoto;
	}
	
	public String getNovaSenhaConfirmacao() {
		return novaSenhaConfirmacao;
	}

	public void setNovaSenhaConfirmacao(String novaSenhaConfirmacao) {
		this.novaSenhaConfirmacao = novaSenhaConfirmacao;
	}

	public UploadedFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(UploadedFile arquivo) {
		this.arquivo = arquivo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	
}
