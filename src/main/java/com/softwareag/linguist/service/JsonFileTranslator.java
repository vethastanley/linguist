package com.softwareag.linguist.service;

import com.softwareag.linguist.service.translator.MicrosoftVirtualTranslator;
import com.softwareag.linguist.service.translator.entity.TranslationResult;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;
/**
 * Created by anki on 10/23/2018.
 */
@Service
public class JsonFileTranslator {

    private MicrosoftVirtualTranslator microsoftVirtualTranslator;

    public JsonFileTranslator(MicrosoftVirtualTranslator microsoftVirtualTranslator){
        this.microsoftVirtualTranslator = microsoftVirtualTranslator;
    }

    public void translateAndCreate(String filePath, Locale source, List<Locale> translationLocales) throws Exception {
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
            ObjectMapper mapper = new ObjectMapper();
            // read JSON from a file
            Map<String, Object> map = mapper.readValue(
                new File(filePath),
                new TypeReference<Map<String, Object>>() {
                });
            System.out.println(map);
            translateValue(map, locale);
            String json = new ObjectMapper().writeValueAsString(map);
            mapper.writeValue(new File(targetFilePath), json);
        }
    }
    private void translateValue(Object object, Locale locale) throws Exception {
        if(object instanceof LinkedHashMap || object instanceof Map){
            Map<String, Object> entriesMap = (LinkedHashMap<String,Object>)object;
            for(Map.Entry<String, Object> entry : entriesMap.entrySet()){
                if(entry.getValue() instanceof  String){
                    List<TranslationResult> translate = microsoftVirtualTranslator.translate((String) entry.getValue(), locale);
                    entry.setValue(translate.get(0).getTranslations().get(0).getText());
                }else{
                    translateValue(entry.getValue(), locale);
                }
            }
        }
    }
}
