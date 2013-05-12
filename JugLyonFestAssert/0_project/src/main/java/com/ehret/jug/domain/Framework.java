package com.ehret.jug.domain;

import java.util.Date;

/**
 * Framework
 */
public class Framework {

    private Long id;

    private String name;

    private String version;

    private Date lastrelease;

    private ProgrammingLanguage language;

    /**
     * Constructeur
     * @param name
     * @param version
     */
    public Framework(String name, String version, ProgrammingLanguage language) {
        this.name = name;
        this.version = version;
        this.language=language;
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public ProgrammingLanguage getLanguage() {
        return language;
    }

    /**
     *
     * @param language
     */
    public void setLanguage(ProgrammingLanguage language) {
        this.language = language;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getVersion() {
        return version;
    }

    /**
     *
     * @param version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     *
     * @return
     */
    public Date getLastrelease() {
        return lastrelease;
    }

    /**
     *
     * @param lastrelease
     */
    public void setLastrelease(Date lastrelease) {
        this.lastrelease = lastrelease;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Framework framework = (Framework) o;

        if (id != null ? !id.equals(framework.id) : framework.id != null) return false;
        if (lastrelease != null ? !lastrelease.equals(framework.lastrelease) : framework.lastrelease != null)
            return false;
        if (!name.equals(framework.name)) return false;
        if (!version.equals(framework.version)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + version.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Framework{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", lastrelease=" + lastrelease +
                ", language=" + language +
                '}';
    }
}
