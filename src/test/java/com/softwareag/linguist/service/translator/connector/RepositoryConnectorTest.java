package com.softwareag.linguist.service.translator.connector;

import com.softwareag.linguist.service.VirtualTranslator;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by anki on 10/22/2018.
 */
public class RepositoryConnectorTest {

    public static void main(String[] args) throws Exception{
       /* RepositoryConnector repositoryConnector = new RepositoryConnector();
        VirtualTranslator virtualTranslator = new VirtualTranslator();
        List<Locale> locales = new ArrayList<Locale>();
        locales.add(Locale.GERMAN);
        locales.add(Locale.ITALIAN);
        locales.add(Locale.CHINESE);

        List<String> localizedFilePaths = repositoryConnector.findLocalizedProperties("https://github.com/vethastanley/linguist.git", "c://repo//");
        for(String locaizedFilePath : localizedFilePaths){
            virtualTranslator.translateAndCreate(locaizedFilePath, Locale.ENGLISH, locales);
        }*/
    }
}
