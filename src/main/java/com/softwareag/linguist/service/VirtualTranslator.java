package com.softwareag.linguist.service;

import com.softwareag.linguist.domain.project.Project;
import com.softwareag.linguist.service.translator.MicrosoftVirtualTranslator;
import com.softwareag.linguist.service.translator.connector.RepositoryConnector;
import com.softwareag.linguist.service.translator.entity.TranslationResult;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by anki on 10/20/2018.
 */
@Service
public class VirtualTranslator {

    public static final String PROPERTIES = "properties";
    public static final String JSON = "json";
    public static final String DEFAULT_PATTERN = "src/main/webapp/i18n/en/" + "([^(_)]*).json";

    private RepositoryConnector connector;

    private PropertyFileTranslator propertyFileTranslator;

    private JsonFileTranslator jsonFileTranslator;

    private final String tempLocation = System.getProperty("java.io.tmpdir");

    public VirtualTranslator(RepositoryConnector connector, PropertyFileTranslator propertyFileTranslator, JsonFileTranslator jsonFileTranslator) {
        this.connector = connector;
        this.propertyFileTranslator = propertyFileTranslator;
        this.jsonFileTranslator = jsonFileTranslator;
    }

    public void triggerTranslation(Project project) throws Exception {
        String projectTemp = tempLocation + project.getId();
        System.out.println("projectTemp :"+projectTemp);
        File temp = new File(projectTemp);
        if (!temp.exists()) {
            temp.mkdir();
        }
        String pattern = project.getPattern();
        if(pattern == null || pattern.isEmpty()){
            pattern = DEFAULT_PATTERN;
        }
        List<String> localizedProperties = new ArrayList<>();
        String[] regArray = pattern.split(",");
        for(String regEx : regArray){
            localizedProperties.addAll(connector.findLocalizedProperties(project.getUrl(), projectTemp, regEx));
        }
        System.out.println("localizedProperties :"+localizedProperties);
        localizedProperties.forEach(localizedProperty -> {
            try {
                translateAndCreate(localizedProperty, Locale.forLanguageTag(project.getSource().name()),
                    project.getDestination().stream().map(language -> Locale.forLanguageTag(language.name())).collect(Collectors.toList()));
            } catch (Exception e) {
                System.out.println("Error handling localization");
            }
        });
        System.out.println("Commiting the changes");
        connector.pushCommand(projectTemp, project.getUrl(), "Files commited by Linguist", project.getUsername(), project.getPassword());
    }

    private void translateAndCreate(String localizedProperty, Locale locale, List<Locale> collect) throws Exception {
        if(localizedProperty.substring(0, localizedProperty.indexOf(".")+1).equalsIgnoreCase(PROPERTIES)){
            propertyFileTranslator.translateAndCreate(localizedProperty,locale, collect);
        }else if(localizedProperty.substring(0, localizedProperty.indexOf(".")+1).equalsIgnoreCase(JSON)){
            jsonFileTranslator.translateAndCreate(localizedProperty,locale, collect);
        }else{
            System.out.println("Unsupported format");
        }
    }

}
