package controle;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;


import conexao.Conexao;
import dao.UsuarioDAO;
import dominio.Usuario;
import filtro.Controle;
import util.Criptografia;
import util.Validator;

@ManagedBean
public class UsuarioBean extends Controle{	
	private Usuario usuario;

	@PostConstruct
	public void init() {		
		usuario = new Usuario();
	}
	public String cadastrar(){
		try{					
			usuario.setSenha(Criptografia.criptografarMD5(usuario.getSenha()));
			EntityManager em = Conexao.getConection(); 					
			em.getTransaction().begin();
			em.persist(usuario);					
			em.getTransaction().commit();
			return "login";
		}catch (Exception e) {
			e.printStackTrace();
			return "Pagina1";
		}									
	}
	public void update(){
		try{					
			EntityManager em = Conexao.getConection(); 					
			em.getTransaction().begin();
			em.merge(usuario);					
			em.getTransaction().commit();									
		}catch (Exception e) {
			e.printStackTrace();
		}						
		
		/*UsuarioDAO dao = new UsuarioDAO();
		if(dao.inserirUsuario(usuario)){
			usuario.setLogged(true);
			return "index";
		}else{
			usuario.setLogged(false);
			return "criar_conta";
		}*/
	}
	public void delete(){
		try{					
			EntityManager em = Conexao.getConection(); 					
			em.getTransaction().begin();
			em.remove(usuario);					
			em.getTransaction().commit();									
		}catch (Exception e) {
			e.printStackTrace();
		}						
		
		/*UsuarioDAO dao = new UsuarioDAO();
		if(dao.inserirUsuario(usuario)){
			usuario.setLogged(true);
			return "index";
		}else{
			usuario.setLogged(false);
			return "criar_conta";
		}*/
	}	
	
	public String autenticar(){
		addMsgError("Usuário/Senha incorretos.");
		System.out.println("1");
		if (!validarLogin()){
			return null;
		}
		System.out.println("2");
		try {
			UsuarioDAO dao = new UsuarioDAO();
			usuario = dao.findUsuarioByLoginSenha(usuario.getEmail(), Criptografia.criptografarMD5(usuario.getSenha()));
			
			if (Validator.isEmpty(usuario)){
				usuario = new Usuario();
				addMsgError("Usuário/Senha incorretos.");
				
				System.out.println("login");
				getCurrentSession().setAttribute("usuarioLogado", null);
				return "login";									
			}else{
				System.out.println("index");
				getCurrentSession().setAttribute("usuarioLogado", usuario);
				return "index";					
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
			tratamentoErroPadrao(e);
			return "login";
		} 		
	}
	public boolean validarLogin(){
		if (usuario == null || (Validator.isEmpty(usuario.getEmail()) && 
				Validator.isEmpty(usuario.getSenha()))){
			addMsgError("Usuário/senha não informados.");
			return false;
		}
		
		if (Validator.isEmpty(usuario.getEmail())){
			addMsgError("Usuário: campo obrigatório não informado.");
			return false;
		}
		
		if (Validator.isEmpty(usuario.getSenha())){
			addMsgError("Senha: campo obrigatório não informado.");
			return false;
		}
		
		return true;
	}
	public void redirencionar() throws IOException{
		System.out.println("ok");
		FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String loggout(){		
		setUsuario(new Usuario());
		return "index";
	}
	
	
}
