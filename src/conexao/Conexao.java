package conexao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 *Classe que faz a conexao com o BD. 
 * @author Cinthia
 */
public class Conexao {
		public static EntityManager getConection(){		
			try {
				EntityManagerFactory emf = Persistence
						.createEntityManagerFactory("produtosmari"); //nome que está no arquivo persistence.xml
				EntityManager em = emf.createEntityManager();
				
				return em;
			} catch (Exception e) {
				// TODO Auto-generated catch block			
				e.printStackTrace();
				return null;
			}		
		}
	}

