package com.iexceed.esoko.domain.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;

public class DomainExceptionTranslator implements
		PersistenceExceptionTranslator {

	public DataAccessException translateExceptionIfPossible(RuntimeException r) {

		DataAccessException dae = new DataAccessException(r.getMessage()) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		};
		dae.setStackTrace(r.getStackTrace());
		return dae;
	}

}
