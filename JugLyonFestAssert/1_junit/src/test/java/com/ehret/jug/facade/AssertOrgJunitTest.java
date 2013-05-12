package com.ehret.jug.facade;

import com.ehret.jug.domain.Framework;
import com.ehret.jug.domain.ProgrammingLanguage;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test de la classe {@link com.ehret.jug.facade.FrameworkFacade}
 */
public class AssertOrgJunitTest {

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
     * test du cas nominal {@link com.ehret.jug.facade.IFrameworkFacade#getById(Long)}
     *
     */
    @Test
    public void getByIdShouldReturnAFramework(){
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

        //La difference ==> possibilite de  tester des tableaux d'objet primitif
        Assert.assertArrayEquals(new String[]{"A","B"},
                new String[]{"A","B"});
    }


}
