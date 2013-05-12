package com.ehret.jug.facade;

import com.ehret.jug.domain.Framework;
import com.ehret.jug.domain.ProgrammingLanguage;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test de la classe {@link FrameworkFacade}
 */
public class AssertHamcrestJunitTest {

    private IFrameworkFacade classeATester;

    @Before
    public void init(){
        classeATester = new FrameworkFacade();
    }

    /**
     * test de {@link com.ehret.jug.facade.IFrameworkFacade#getById(Long)} avec argument vide
     */
    @Test(expected = NullPointerException.class)
    public void getByIdWithIdNullShouldReturnAnException(){
        classeATester.getById(null);
        Assert.fail("Doit planter avant");
    }

    /**
     * test du cas nominal {@link IFrameworkFacade#getById(Long)}
     */
    @Test
    public void getByIdShouldReturnAFramework(){
        Framework fwk = classeATester.getById(1L);

        assertThat(fwk.getId(), equalTo(1L));
        assertThat(fwk.getLanguage(), equalTo(ProgrammingLanguage.JAVA));
        assertThat(fwk.getName(), equalTo("Junit"));
        assertThat(fwk.getVersion(), equalTo("4.11"));

    }


}
