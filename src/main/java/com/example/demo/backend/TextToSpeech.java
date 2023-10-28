package com.example.demo.backend;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.util.Locale;

public class TextToSpeech {
    private static Synthesizer synthesizer;

    public static void initializeSynthesizer() {
        try {
            System.setProperty(
                    "freetts.voices",
                    "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

            Central.registerEngineCentral(
                    "com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");

            synthesizer = Central.createSynthesizer(
                    new SynthesizerModeDesc(Locale.US));
            synthesizer.allocate();
            synthesizer.resume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void processTextToSpeech(String inputText) {
        if (synthesizer == null) {
            initializeSynthesizer();
        }

        try {
            synthesizer.speakPlainText(inputText, null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeSynthesizer() throws EngineException {
        if (synthesizer != null) {
            synthesizer.deallocate();
        }
    }
}

