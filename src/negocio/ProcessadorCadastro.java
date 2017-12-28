package negocio;

import dao.GenericDAO;
import dao.GenericDAOImple;
import dominio.ObjetoPersistivel;
import dominio.Usuario;

public class ProcessadorCadastro extends ProcessadorComando {
	
	protected ObjetoPersistivel obj;
	
	@Override
	protected void iniciarExecucao() {
		GenericDAO dao = new GenericDAOImple();
		
		if (obj.getId() == 0){
			dao.create(obj);
		} else {
			dao.update(obj);
		}
	}

	@Override
	protected Object getResultado() {
		return obj;
	}

	public void setObj(Usuario usuario) {
		this.obj = (ObjetoPersistivel) usuario;
	}
	
}
