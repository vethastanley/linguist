package com.softwareag.linguist.service;

import com.softwareag.linguist.service.translator.MicrosoftVirtualTranslator;
import com.softwareag.linguist.service.translator.entity.TranslationResult;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

/**
 * Created by anki on 10/23/2018.
 */
@Service
public class PropertyFileTranslator {

    private MicrosoftVirtualTranslator microsoftVirtualTranslator;

    public PropertyFileTranslator(MicrosoftVirtualTranslator microsoftVirtualTranslator){
        this.microsoftVirtualTranslator = microsoftVirtualTranslator;
    }
    public void translateAndCreate(String filePath, Locale source, List<Locale> translationLocales) throws Exception {
        Map<String, Properties> propertiesMap = new LinkedHashMap<String, Properties>();
        Map<String, String> localizationFiles = new LinkedHashMap<String, String>();
        File sourcePath = new File(filePath);
        String fileNameWithExtension = sourcePath.getName();
        String folderLocation = sourcePath.getParent();
        System.out.println("fileName :"+fileNameWithExtension);
        System.out.println("filePath : "+folderLocation);
        for(Locale locale : translationLocales){
            String targetFilePath = folderLocation + File.separatorChar + fileNameWithExtension.substring(0, fileNameWithExtension.indexOf(".")) + "_"+locale.getLanguage()+fileNameWithExtension.substring(fileNameWithExtension.indexOf("."));
            File targetFile = new File(targetFilePath);
            if(!targetFile.exists()){
                targetFile.createNewFile();
            }
            Properties properties = new Properties();
            propertiesMap.put(locale.getLanguage(), properties);
            localizationFiles.put(locale.getLanguage(),targetFilePath);
        }
        Map<String, TranslationResult> translationResultMap = translate(filePath, source, translationLocales);
        Set<Map.Entry<String, TranslationResult>> entries = translationResultMap.entrySet();
        for(Map.Entry<String, TranslationResult> entry : entries){
            String key = entry.getKey();
            TranslationResult translationResult = entry.getValue();
            for(TranslationResult.Translation translation : translationResult.getTranslations()){
                String language = translation.getTo();
                String text = translation.getText();
                System.out.println("language:"+language);
                System.out.println("text:"+ text);
                Properties properties = propertiesMap.get(language);
                if(properties != null){
                    properties.put(key, text);
                    Writer outputStreamWriter =new OutputStreamWriter(new FileOutputStream(localizationFiles.get(language)), "UTF-8");
                    properties.store(outputStreamWriter, "Updated with translated text");

                }
            }
        }
    }

    public Map<String, TranslationResult> translate(String filePath, Locale source, List<Locale> translation) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream(filePath));
        return translate(properties, source, translation);
    }

    public Map<String, TranslationResult> translate(Properties properties, Locale source, List<Locale> translation) throws Exception {
        Map<String, TranslationResult> translationResultMap = new LinkedHashMap<String, TranslationResult>();
        Set<Map.Entry<Object, Object>> entries = properties.entrySet();
        for(Map.Entry<Object, Object> entry : entries){
            List<TranslationResult> translate = microsoftVirtualTranslator.translate((String) entry.getValue(), translation);
            translationResultMap.put((String)entry.getKey(),translate.get(0));
        }

        return translationResultMap;
    }
}
