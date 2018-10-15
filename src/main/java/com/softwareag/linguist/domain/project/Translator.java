package com.softwareag.linguist.domain.project;

/**
 * Created by VST on 11/10/2018.
 */
public class Translator {

    private String name;

    private Language language;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Translator)) return false;

        Translator that = (Translator) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return language == that.language;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (language != null ? language.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Translator{" +
            "name='" + name + '\'' +
            ", language=" + language +
            '}';
    }
}
