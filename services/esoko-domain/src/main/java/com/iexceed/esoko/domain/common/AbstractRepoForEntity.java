package com.iexceed.esoko.domain.common;

import java.io.Serializable;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractRepoForEntity.
 * 
 * @param <T>
 *            the generic type
 * @param <ID>
 *            the generic type
 */
public abstract class AbstractRepoForEntity<T, ID extends Serializable> extends
		AbstractRepository<T, ID> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iexceed.esoko.domain.common.IRepository#save(java.lang.Object)
	 */
	/** The log. */
	public static Logger log = LoggerUtils.getLogger();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iexceed.esoko.domain.common.IRepository#save(java.lang.Object)
	 */
	public <S extends T> S save(S entity) {
		try {
			entityManager.persist(entity);
		} catch (DataAccessException e) {
			DomainException domainException = new DomainException();
			domainException.setCode(ERROR_CODE.DM_SV_001);
			domainException.setMessage("Cannot save data to database");
			log.error(Utils.getStackTrace(e));
			throw domainException;
		} catch (EntityExistsException e) {
			DomainException domainException = new DomainException();
			domainException.setCode(ERROR_CODE.DM_SV_001);
			domainException.setMessage("Entity Already Exist");
			log.error(Utils.getStackTrace(e));
			throw domainException;
		} catch (RuntimeException e) {
			DomainException domainException = new DomainException();
			domainException.setCode(ERROR_CODE.DM_SV_001);
			domainException.setMessage("Cannot save data to database");
			log.error(Utils.getStackTrace(e));
			throw domainException;
		} finally {
			entityManager.close();
		}
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iexceed.esoko.domain.common.IRepository#merge(java.lang.Object)
	 */
	public <S extends T> S merge(S entity) {

		try {
			entityManager.merge(entity);
		} catch (DataAccessException dae) {
			DomainException domainException = new DomainException();
			domainException.setCode(ERROR_CODE.DM_SV_001);
			domainException.setMessage("Cannot save data to database");
			System.out.println(dae.getStackTrace());
			throw domainException;
		} catch (RuntimeException e) {
			DomainException domainException = new DomainException();
			domainException.setCode(ERROR_CODE.DM_SV_001);
			domainException.setMessage("Cannot save data to database");
			log.error(Utils.getStackTrace(e));
			throw domainException;
		} finally {
			entityManager.close();
		}
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iexceed.esoko.domain.common.IRepository#findOne(java.io.Serializable)
	 */
	public <S extends T> T findOne(Class<? extends T> entity, ID id) {
		try {
			System.out.println("Manager:::" + entityManager);
			return (T) entityManager.find(entity, id);
		} catch (DataAccessException dae) {
			DomainException domainException = new DomainException();
			domainException.setCode(ERROR_CODE.DM_SV_002);
			domainException.setMessage("Cannot Find Data");
			System.out.println(dae.getStackTrace());
			throw domainException;
		} catch (NoResultException e) {
			DomainException domainException = new DomainException();
			domainException.setCode(ERROR_CODE.DM_SV_002);
			domainException.setMessage("Cannot Find Data");
			System.out.println(e.getStackTrace());
			throw domainException;
		} catch (RuntimeException e) {
			e.printStackTrace();
			DomainException domainException = new DomainException();
			domainException.setCode(ERROR_CODE.DM_SV_002);
			domainException.setMessage("Cannot Find Data");
			System.out.println(e.getStackTrace());
			throw domainException;
		} finally {
			entityManager.close();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iexceed.esoko.domain.common.IRepository#exists(java.lang.Object)
	 */
	public <S extends T> boolean exists(Class<? extends T> entity, ID id) {
		boolean status = false;
		try {
			if (entityManager.find(entity, id) != null) {
				status = true;
			}
		} catch (RuntimeException e) {
			DomainException domainException = new DomainException();
			domainException.setCode(ERROR_CODE.DM_SV_002);
			domainException.setMessage("Cannot Find Data");
			System.out.println(e.getStackTrace());
			throw domainException;
		} finally {
			entityManager.close();
		}
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iexceed.esoko.domain.common.IRepository#findAll()
	 */
	@SuppressWarnings("unchecked")
	public <S extends T> Iterable<T> findAll() {
		Query queryObject = null;
		String query = null;
		try {
			query = "SELECT c FROM S c";
			queryObject = entityManager.createQuery(query);
		} catch (DataAccessException dae) {
			DomainException domainException = new DomainException();
			domainException.setCode(ERROR_CODE.DM_SV_002);
			domainException.setMessage("Cannot Find Data");
			System.out.println(dae.getStackTrace());
			throw domainException;
		} catch (NoResultException e) {
			DomainException domainException = new DomainException();
			domainException.setCode(ERROR_CODE.DM_SV_002);
			domainException.setMessage("Cannot Find Data");
			System.out.println(e.getStackTrace());
			throw domainException;
		} catch (RuntimeException e) {
			DomainException domainException = new DomainException();
			domainException.setCode(ERROR_CODE.DM_SV_002);
			domainException.setMessage("Cannot Find Data");
			System.out.println(e.getStackTrace());
			throw domainException;
		} finally {
			entityManager.close();
		}
		return queryObject.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iexceed.esoko.domain.common.IRepository#findAll(java.lang.Iterable)
	 */
	public Iterable<T> findAll(Iterable<ID> ids) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iexceed.esoko.domain.common.IRepository#count()
	 */
	public long count() {
		String query = "SELECT c FROM S c";
		Query queryObject = entityManager.createQuery(query);
		long count = queryObject.getResultList().size();
		entityManager.close();
		return count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iexceed.esoko.domain.common.IRepository#delete(java.io.Serializable)
	 */
	public <S extends T> void delete(S entity) {
		try {
			entityManager.remove(entity);
		} catch (DataAccessException dae) {
			DomainException domainException = new DomainException();
			domainException.setCode(ERROR_CODE.DM_SV_003);
			domainException.setMessage("Cannot Delete Data");
			System.out.println(dae.getStackTrace());
			throw domainException;
		} catch (RuntimeException dae) {
			DomainException domainException = new DomainException();
			domainException.setCode(ERROR_CODE.DM_SV_003);
			domainException.setMessage("Cannot Delete Data");
			System.out.println(dae.getStackTrace());
			throw domainException;
		} finally {
			entityManager.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iexceed.esoko.domain.common.IRepository#delete(java.lang.Iterable)
	 */
	public void delete(Iterable<? extends T> entities) {
		try {
			for (T t : entities) {
				entityManager.remove(t);
			}
		} catch (DataAccessException dae) {
			DomainException domainException = new DomainException();
			domainException.setCode(ERROR_CODE.DM_SV_003);
			domainException.setMessage("Cannot Delete Data");
			System.out.println(dae.getStackTrace());
			throw domainException;
		} catch (RuntimeException dae) {
			DomainException domainException = new DomainException();
			domainException.setCode(ERROR_CODE.DM_SV_003);
			domainException.setMessage("Cannot Delete Data");
			System.out.println(dae.getStackTrace());
			throw domainException;
		} finally {
			entityManager.close();
		}

	}
}
