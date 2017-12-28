package controle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import dao.UsuarioDAO;
import dominio.Usuario;
import filtro.Controle;
import util.Criptografia;
import util.Validator;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class LoginMBean extends Controle{

	/** Armazena os dados informados na tela de login. */
	private Usuario usuario;
	
	/** Armazena os dados iniciais de cadastro do usuário. */
	private Usuario usuarioCadastro;
	
	@PostConstruct
	private void init(){
		usuario = new Usuario();
		usuarioCadastro = new Usuario();				
	}
	
	/** Autentica o usuário e faz login no sistema. */
	public String autenticar(){
		if (!validarLogin()){
			return AdmPaginas.CADASTRAR_PRODUTO;
		}
		
		try {
			UsuarioDAO dao = new UsuarioDAO();
			usuario = dao.findUsuarioByLoginSenha(usuario.getEmail(), Criptografia.criptografarMD5(usuario.getSenha()));
			
			if (!Validator.isEmpty(usuario)){
				getCurrentSession().setAttribute("usuarioLogado", usuario);
				
				return AdmPaginas.CADASTRAR_PRODUTO;				
			} else {
				init();
				addMsgError("Usuário/Senha incorretos.");
				return null;
			}
			
		} catch (Exception e) {
			tratamentoErroPadrao(e);
			return null;
		} 
		
	}
	
	/** Verifica se os dados para login estão corretos */
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
	
	/** Realiza logoff do sistema. */
	public String logoff(){
		//getCurrentSession().invalidate();
		return AdmPaginas.LOGIN_PAGE;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	
			
}
