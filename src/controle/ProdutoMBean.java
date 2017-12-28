package controle;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;


import conexao.Conexao;
import dominio.Produto;
import filtro.Controle;

@SuppressWarnings("serial")
@ManagedBean
public class ProdutoMBean extends Controle {

	private List<Produto> listaprodutos;
	private Produto produto;
	private final Integer TAM_MAXIMO_ARQUIVO = 2097152;
	private final String[] FORMATOS_PERMITIDOS = {"png", "jpg", "jpeg"};
	
	@PostConstruct
	private void init() {
		produto = new Produto();
		listaprodutos = new ArrayList<>();
	}
	

	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public Integer getTAM_MAXIMO_ARQUIVO() {
		return TAM_MAXIMO_ARQUIVO;
	}


	public String[] getFORMATOS_PERMITIDOS() {
		return FORMATOS_PERMITIDOS;
	}


	public String cadastrar(){
		try{					
			produto.setUsuario(getUsuarioLogado());
			
			EntityManager em = Conexao.getConection(); 					
			em.getTransaction().begin();
			em.persist(produto);					
			em.getTransaction().commit();
			return "Cadastro realizado!";
		}catch (Exception e) {
			e.printStackTrace();
			return "Cadastro Inválido";
		}									
	}
	
	public void update(){
		try{					
			EntityManager em = Conexao.getConection(); 					
			em.getTransaction().begin();
			em.merge(produto);					
			em.getTransaction().commit();									
		}catch (Exception e) {
			e.printStackTrace();
		}						
		
}
	
	public void buscar(){
			
}
	
	public void delete(){
		try{					
			EntityManager em = Conexao.getConection(); 					
			em.getTransaction().begin();
			em.remove(produto);					
			em.getTransaction().commit();									
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}


	public List<Produto> getListaprodutos() {
		return listaprodutos;
	}


	public void setListaprodutos(List<Produto> listaprodutos) {
		this.listaprodutos = listaprodutos;
	}
	
}
