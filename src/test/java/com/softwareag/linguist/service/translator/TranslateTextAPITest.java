package com.softwareag.linguist.service.translator;

import com.softwareag.linguist.service.translator.entity.TranslationResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by anki on 10/19/2018.
 */
public class TranslateTextAPITest {


    public static final String text = "Please enter your credentials here";

    public static void main(String[] args) {
        try {
            MicrosoftVirtualTranslator microsoftVirtualTranslator = new MicrosoftVirtualTranslator();
            List<Locale> locales = new ArrayList<Locale>();
            locales.add(Locale.GERMAN);
            locales.add(Locale.ITALIAN);
            locales.add(Locale.CHINESE);
            List<TranslationResult> translationResults= microsoftVirtualTranslator.translate (text, locales);
            for(TranslationResult.Translation translationResult : translationResults.get(0).getTranslations()){
                System.out.println(translationResult.getTo());
                System.out.println(translationResult.getText());
            }
        }
        catch (Exception e) {
            System.out.println (e);
        }
    }
}
