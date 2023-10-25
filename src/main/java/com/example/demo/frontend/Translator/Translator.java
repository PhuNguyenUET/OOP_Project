package com.example.demo.frontend.Translator;

public class Translator {
    private String sourceText;
    private String targetText;
    private boolean engToViet = true;

    public String getSourceText() {
        return sourceText;
    }

    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }

    public String getTargetText() {
        return targetText;
    }

    public void translate() {
        //Get the translation
    }

    public boolean isEngToViet() {
        return engToViet;
    }

    public void changeLanguage() {
        this.engToViet = !engToViet;
    }
}
