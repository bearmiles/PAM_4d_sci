package com.example.splashscreen;

public class SettingsContext {
    int progress;
    boolean darkMode;
    double timer;

    int radio_id;

    public int getProgress() {
        return progress;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    public double getTimer() {
        return timer;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public void setRadio_id(int radio_id) {
        this.radio_id = radio_id;
    }

    public void setTimer(double timer) {
        this.timer = timer;
    }

    public boolean isDarkMode() {
        return darkMode;
    }

    public int getRadio_id() {
        return radio_id;
    }
}
