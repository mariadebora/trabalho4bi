package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import dominio.Usuario;
import util.Validator;


public class UsuarioDAO extends GenericDAOImple {
	/** Encontra um usuário a partir de seu login e senha. */
	
	public Usuario findUsuarioByLoginSenha(String login, String senha){
		EntityManager em = getEm();
		
		String hql = "SELECT usuario ";
		hql += " FROM Usuario usuario WHERE usuario.email = :login and usuario.senha = :senha ";
		
		Query q = em.createQuery(hql);
		q.setParameter("login", login);
		q.setParameter("senha", senha);
		
		try {
			Usuario usuario = (Usuario) q.getSingleResult();
			return usuario;
		} catch (NoResultException e){
			return null;
		}
	}
	
	/**
	 * Método que permite listar usuários através da busca por
	 * diversos atributos.
	 */
	public List<Usuario> findUsuarioGeral(String email, String nome, String sobrenome, 
			String ordenarPor){
		EntityManager em = getEm();
		
		String hql = "SELECT u ";
		hql += " FROM Usuario u WHERE 1=1";
		
		if (Validator.isNotEmpty(email)){
			hql += " AND u.email = :email ";
		}
		if (Validator.isNotEmpty(nome)){
			hql += " AND upper(u.pessoa.nome) like :nome ";
		}
		if (Validator.isNotEmpty(sobrenome)){
			hql += " AND upper(u.pessoa.sobrenome) like :sobrenome ";
		}
		if (Validator.isNotEmpty(ordenarPor)){
			hql += " ORDER BY :ordenarPor ";
		}
		
		Query q = em.createQuery(hql);
		
		if (Validator.isNotEmpty(email)){
			q.setParameter("email", email);
		}
		if (Validator.isNotEmpty(nome)){
			q.setParameter("nome", "%" + nome.toUpperCase() + "%");
		}
		if (Validator.isNotEmpty(sobrenome)){
			q.setParameter("sobrenome", "%" + sobrenome.toUpperCase() + "%");
		}
		if (Validator.isNotEmpty(ordenarPor)){
			q.setParameter("ordernarPor", ordenarPor);
		}
		
		try {
			@SuppressWarnings("unchecked")
			List<Usuario> result = q.getResultList();
			
			return result;
		} catch (NoResultException e){
			return null;
		}
	}
}
