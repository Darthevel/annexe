package com.ehret.jug.facade;

import com.ehret.jug.domain.Framework;

import java.util.List;

/**
 * Method to manage {@link com.ehret.jug.domain.Framework}
 */
public interface IFrameworkFacade {
    /**
     * Get e framework by his id
     * @param id
     * @return
     */
    Framework getById(Long id);

    /**
     * Read all the frameworks
     * @return
     */
    List<Framework> getAll();

}
