package negocio;

import dao.GenericDAO;
import dao.GenericDAOImple;
import dominio.ObjetoPersistivel;

public class ProcessadorRemocao extends ProcessadorComando {
	
	/** 
	 * Objeto que se quer remover do banco. 
	 */
	protected ObjetoPersistivel obj;
	
	@Override
	protected void iniciarExecucao() throws Exception {
		GenericDAO dao = new GenericDAOImple();
		obj = dao.findByPrimaryKey(obj.getId(), obj.getClass());
		dao.delete(obj);
	}

	@Override
	protected Object getResultado() {
		return obj;
	}

	public void setObj(ObjetoPersistivel obj) {
		this.obj = obj;
	}

}
