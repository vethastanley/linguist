package com.softwareag.linguist.service.translator.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anki on 10/19/2018.
 */
public class TranslationResult {

    private DetectedLanguage detectedLanguage;

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }

    List<Translation> translations = new ArrayList<Translation>();

    public DetectedLanguage getDetectedLanguage() {
        return detectedLanguage;
    }

    public void setDetectedLanguage( DetectedLanguage detectedLanguage ) {
        this.detectedLanguage = detectedLanguage;
    }

    public class DetectedLanguage {
        private String language;
        private float score;

        public String getLanguage() {
            return language;
        }

        public float getScore() {
            return score;
        }

        public void setLanguage( String language ) {
            this.language = language;
        }

        public void setScore( float score ) {
            this.score = score;
        }
    }

    public class Translation{

        private String text;
        private String to;

        public String getText(){
            return text;
        }

        public String getTo(){
            if(to.contains("-")){
                return to.substring(0, to.indexOf("-"));
            }
            return to;
        }

        public void setText(){
            this.text = text;
        }

        public void setTo(){
            this.to = to;
        }

    }
}
