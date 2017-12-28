package dao;

import java.util.List;

import javax.persistence.EntityManager;

import dominio.ObjetoPersistivel;



public interface GenericDAO {
	
	
	public abstract EntityManager getEm();
	
	public abstract void clear();
	
	public abstract void detach(ObjetoPersistivel p);
	
	public abstract void refresh(ObjetoPersistivel p);

	public abstract void create(ObjetoPersistivel c);

	public abstract void update(ObjetoPersistivel c);
	
	public abstract void createOrUpdate(ObjetoPersistivel c);

	public abstract void delete(ObjetoPersistivel c);
	
	public abstract void flush();

	public abstract void update(String sql);

	public abstract <T extends ObjetoPersistivel> T findByPrimaryKey(int id, Class<T> classe);

	public abstract <T extends ObjetoPersistivel> List<T> findAll(Class<T> classe);

	public abstract <T extends ObjetoPersistivel> List<T> findAllAtivos(Class<T> classe);

	public abstract <T extends ObjetoPersistivel> List<T> findAllAtivos(Class<T> classe, String orderBy);

	public abstract <T extends ObjetoPersistivel> List<T> findAllLike(String coluna, String valor, String orderby, Class<T> classe);
	
	public abstract <T extends ObjetoPersistivel> List<T> findByExactField(String coluna, Object valor, Class<T> classe);

	public abstract <T extends ObjetoPersistivel> List<T> findByExactField(String coluna, Object valor, String orderBy, Class<T> classe);
	
	public <T extends ObjetoPersistivel> List<T> findByExactFields(String[] colunas, Object[] valores, Class<T> classe);
	
	public <T extends ObjetoPersistivel> T findByExactFields(String[] colunas, Object[] valores, boolean limit, Class<T> classe);

	public <T extends ObjetoPersistivel> void updateField(Class<T> classe, int id, String coluna, Object valor);
}
