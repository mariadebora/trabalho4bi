package negocio;

import javax.persistence.EntityManager;

import dao.Database;



/**
 * Classe que representa um processador de dados. Deve ser estendido
 * por todos os outros processadores do sistema.
 * É esta class quem realiza operações cruciais do sistema, como
 * cadastro, edição, remoção, etc.
 * 
 * @author Renan
 *
 */
public abstract class ProcessadorComando {
	
	public final Object execute() throws Exception {
		EntityManager em = null;
		
		try {
			em = Database.getInstance().getEntityManager();
			
			em.getTransaction().begin();
			
			iniciarExecucao();
			
			em.getTransaction().commit();
			
			return getResultado();
			
		} catch (Exception e){
			e.printStackTrace();
			
			em = Database.getInstance().getEntityManager();
			
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			
			throw new Exception(e);
		} finally {
			//Limpando caches
//			if (em != null)
//				em.clear();
		}
	}
	
	
	protected abstract void iniciarExecucao() throws Exception;
	
	protected abstract Object getResultado();
	
}
