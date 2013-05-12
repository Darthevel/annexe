package com.ehret.jug.facade;

import com.ehret.jug.domain.Framework;
import com.ehret.jug.domain.ProgrammingLanguage;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

/**
 * My facade
 */
public class FrameworkFacade implements IFrameworkFacade{

    @Override
    public Framework getById(Long id) {
        Preconditions.checkNotNull(id, "Framework id is required");
        return new FrameworkBuilder().create("Junit", "4.11", ProgrammingLanguage.JAVA)
                .addId(id).addReleaseDate(new Date()).build();
    }

    @Override
    public List<Framework> getAll() {
        return Lists.newArrayList(
                new FrameworkBuilder().create("Junit", "4.11", ProgrammingLanguage.JAVA)
                        .addId(1L).addReleaseDate(new Date()).build(),
                new FrameworkBuilder().create("TestNG", "6.1.1", ProgrammingLanguage.JAVA)
                        .addId(2L).addReleaseDate(new Date()).build(),
                new FrameworkBuilder().create("Hamcrest", "1.3", ProgrammingLanguage.JAVA)
                        .addId(2L).addReleaseDate(new Date()).build()
        );
    }

}
