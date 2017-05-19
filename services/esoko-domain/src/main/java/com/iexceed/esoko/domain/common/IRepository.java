package com.iexceed.esoko.domain.common;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Interface IRepository.
 * 
 * @param <T>
 *            the generic type
 * @param <ID>
 *            the generic type
 */
public interface IRepository<T, ID extends Serializable> {

	/**
	 * Save.
	 * 
	 * @param <S>
	 *            the generic type
	 * @param entity
	 *            the entity
	 * @return the s
	 */
	<S extends T> S save(S entity);
	
	/**
	 * Merge.
	 *
	 * @param <S> the generic type
	 * @param entity the entity
	 * @return the s
	 */
	<S extends T> S merge(S entity);

	/**
	 * Find one.
	 * 
	 * @param <S>
	 *            the generic type
	 * @param id
	 *            the id
	 * @return the t
	 */
	public <S extends T> T findOne(Class<? extends T> entity, ID id);

	/**
	 * Exists.
	 *
	 * @param <S>            the generic type
	 * @param entity            the entity
	 * @param id the id
	 * @return true, if successful
	 */
	<S extends T> boolean exists(Class<? extends T> entity,ID id);

	/**
	 * Find all.
	 * 
	 * @param <S>
	 *            the generic type
	 * @return the iterable
	 */
	<S extends T> Iterable<T> findAll();

	/**
	 * Find all.
	 * 
	 * @param ids
	 *            the ids
	 * @return the iterable
	 */
	Iterable<T> findAll(Iterable<ID> ids);

	/**
	 * Count.
	 * 
	 * @return the long
	 */
	long count();

	/**
	 * Delete.
	 * 
	 * @param id
	 *            the id
	 */
	public <S extends T> void delete(S entity);

	/**
	 * Delete.
	 * 
	 * @param entities
	 *            the entities
	 */
	void delete(Iterable<? extends T> entities);

}
