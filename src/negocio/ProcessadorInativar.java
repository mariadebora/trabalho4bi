package negocio;

import dao.GenericDAO;
import dao.GenericDAOImple;
import dominio.ObjetoPersistivel;

public class ProcessadorInativar extends ProcessadorComando {
	
	public static final int INATIVAR = 1;
	public static final int REATIVAR = 2;
	
	private int operacao;
	
	private ObjetoPersistivel obj;
	
	@Override
	protected void iniciarExecucao() throws Exception {
		GenericDAO dao = new GenericDAOImple();
		
		if (operacao == INATIVAR) 
			dao.updateField(obj.getClass(), obj.getId(), "ativo", false);
		else if (operacao == REATIVAR)
			dao.updateField(obj.getClass(), obj.getId(), "ativo", true);
		else
			throw new Exception("Operação inválida!");
		dao.detach(obj);
	}

	@Override
	protected Object getResultado() {
		return obj;
	}

	public void setObj(ObjetoPersistivel obj) {
		this.obj = obj;
	}

	public void setOperacao(int operacao) {
		this.operacao = operacao;
	}
	
}
