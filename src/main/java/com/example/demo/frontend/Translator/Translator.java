package com.example.demo.frontend.Translator;

import com.example.demo.backend.TranslateBackend.GoogleTranslate;

import java.io.IOException;

public class Translator {
    private String sourceText;
    private String targetText;
    private boolean engToViet = false;

    public String getSourceText() {
        return sourceText;
    }

    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }

    public String getTargetText() {
        return targetText;
    }

    public void translate(){
        try {
            if (engToViet) {
                targetText = GoogleTranslate.translate("en", "vi", sourceText);
            } else {
                targetText = GoogleTranslate.translate("vi", "en", sourceText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isEngToViet() {
        return engToViet;
    }

    public void changeLanguage() {
        this.engToViet = !engToViet;
    }
}
