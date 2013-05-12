package com.ehret.jug.facade;

import com.ehret.jug.domain.Framework;
import com.ehret.jug.domain.ProgrammingLanguage;

import java.util.Date;

public class FrameworkBuilder {
    /**
     * Built
     */
    Framework myFramework;

    /**
     * Create a Framework
     *
     * @param name
     * @param version
     * @return
     */
    public FrameworkBuilder create(String name, String version, ProgrammingLanguage language) {
        myFramework = new Framework(name, version, language);
        return this;
    }

    public FrameworkBuilder addReleaseDate(Date releaseDate) {
        myFramework.setLastrelease(releaseDate);
        return this;
    }

    public FrameworkBuilder addId(Long id) {
        myFramework.setId(id);
        return this;
    }

    public Framework build(){
        return myFramework;
    }
}