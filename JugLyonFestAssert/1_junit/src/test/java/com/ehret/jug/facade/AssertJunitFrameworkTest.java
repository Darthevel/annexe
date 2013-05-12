package com.ehret.jug.facade;

import com.ehret.jug.domain.Framework;
import com.ehret.jug.domain.ProgrammingLanguage;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test de la classe {@link FrameworkFacade}
 */
public class AssertJunitFrameworkTest {

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
        org.junit.Assert.fail("Doit planter avant");
    }

    /**
     * test du cas nominal {@link IFrameworkFacade#getById(Long)}
     *
     */
    @Test
    public void getByIdOk(){

        Framework fwk = classeATester.getById(1L);

        //[Chaine explicative], Attendu, Actuelle
        Assert.assertEquals(Long.valueOf(1), fwk.getId());
        Assert.assertEquals(ProgrammingLanguage.JAVA, fwk.getLanguage());
        Assert.assertEquals("Junit", fwk.getName());
        Assert.assertEquals("4.11", fwk.getVersion());

        //SameObject
        Assert.assertSame(10, 10);
        Framework fwk1 = classeATester.getById(1L);
        Assert.assertNotSame(fwk, fwk1);
        //l'intérêt ???

        Assert.assertNull(null);
        Assert.assertTrue(true);
    }


}
