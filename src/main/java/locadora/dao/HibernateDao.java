package locadora.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateDao{
	   
	private static HibernateDao instance;
    protected EntityManager entityManager;
    
    public static HibernateDao getInstance(){
    	if (instance == null){
    		instance = new HibernateDao();
    	}
      
    	return instance;
    }

    private HibernateDao() {
    	entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("locadora");
    	if (entityManager == null) {
    		entityManager = factory.createEntityManager();
    	}

    	return entityManager;
    }

	public Object getById(Class<?> classe, Serializable id) {
		return entityManager.find(classe, id);
    }

    public List<?> findAll(Class<?> classe) {
    	return entityManager.createQuery("FROM " + classe.getName()).getResultList();
    }

    //Serve apenas para inserir um novo objeto no bd
    public void persist(Object object) {
    	try {
    		entityManager.getTransaction().begin();
    		entityManager.persist(object);
    		entityManager.getTransaction().commit();
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		entityManager.getTransaction().rollback();
    	}
    }

    //Pode tanto inserir um novo objeto no bd como tb atualizar um já existente
    public void merge(Object object) {
    	try {
    		entityManager.getTransaction().begin();
    		entityManager.merge(object);
    		entityManager.getTransaction().commit();
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		entityManager.getTransaction().rollback();
    	}
    }

    public void remove(Class<?> classe, Serializable id) {
    	try {
    		entityManager.getTransaction().begin();
    		Object object = entityManager.find(classe, id);
    		entityManager.remove(object);
    		entityManager.getTransaction().commit();
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		entityManager.getTransaction().rollback();
    	}
    }
}

