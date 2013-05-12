package com.ehret.jug.facade;

import com.ehret.jug.domain.Framework;
import com.ehret.jug.domain.ProgrammingLanguage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.fest.assertions.Assertions.*;




/**
 * Test de la classe {@link com.ehret.jug.facade.FrameworkFacade}
 */
public class AssertFestJunitTest {

    private IFrameworkFacade classeATester;

    @Before
    public void init(){
        classeATester = new FrameworkFacade();
    }

    /**
     * test de {@link com.ehret.jug.facade.IFrameworkFacade#getById(Long)} avec argument vide
     */
    @Test
    public void getByIdWithIdNullShouldReturnAnException(){
        try{
            classeATester.getById(null);
            Assert.fail("Doit planter avant");
        }
        catch (RuntimeException e){
            assertThat(e).isInstanceOf(NullPointerException.class).hasMessage("Framework id is required");
        }
    }

    /**
     * test du cas nominal {@link com.ehret.jug.facade.IFrameworkFacade#getById(Long)}
     */
    @Test
    public void getByIdShouldReturnAFramework(){
        Framework fwk = classeATester.getById(1L);

        assertThat(fwk.getId()).isEqualTo(1L);
        assertThat(fwk.getLanguage()).isNotNull().isIn(ProgrammingLanguage.values())
                .isEqualTo(ProgrammingLanguage.JAVA);
        assertThat(fwk.getName()).hasSize(5).isEqualTo("Junit");
        assertThat(fwk.getVersion()).startsWith("4.").isEqualTo("4.11");

        FrameworkAssert.assertThat(fwk).hasUniqueKey();
    }

    /**
     * test du cas nominal {@link IFrameworkFacade#getAll()}
     */
    @Test
    public void getAllShouldReturnSeveralFrameworks(){
        List<Framework> fwks = classeATester.getAll();
        assertThat(fwks).isNotEmpty().hasSize(3)
                .onProperty("name").containsExactly("Junit","TestNG","Hamcrest");

    }


}
