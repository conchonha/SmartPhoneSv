package edu.abcd.smartphone.domain.model;

import java.util.Objects;

//class define giusp display information on ui
public class SettingDisplay {
    public int title;
    public TypeDisplayUI typeDisplayUI;
    public int color;
    public String content;
    public Boolean isLine;
    public Boolean isSwipe;
    public Boolean isChecked;

    public SettingDisplay(int title, TypeDisplayUI typeDisplayUI, int color, String content, Boolean isLine, Boolean isSwipe) {
        this.title = title;
        this.typeDisplayUI = typeDisplayUI;
        this.color = color;
        this.content = content;
        this.isLine = isLine;
        this.isSwipe = isSwipe;
    }

    public SettingDisplay(int title, TypeDisplayUI typeDisplayUI, int color, String content) {
        this.title = title;
        this.typeDisplayUI = typeDisplayUI;
        this.color = color;
        this.content = content;
    }

    public SettingDisplay(int title, TypeDisplayUI typeDisplayUI, int color) {
        this.title = title;
        this.typeDisplayUI = typeDisplayUI;
        this.color = color;
    }

    public SettingDisplay(int title, TypeDisplayUI typeDisplayUI, int color, String content, Boolean isLine, Boolean isSwipe, Boolean isChecked) {
        this.title = title;
        this.typeDisplayUI = typeDisplayUI;
        this.color = color;
        this.content = content;
        this.isLine = isLine;
        this.isSwipe = isSwipe;
        this.isChecked = isChecked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SettingDisplay that = (SettingDisplay) o;
        return title == that.title && color == that.color && typeDisplayUI == that.typeDisplayUI && Objects.equals(content, that.content) && Objects.equals(isLine, that.isLine) && Objects.equals(isSwipe, that.isSwipe) && Objects.equals(isChecked, that.isChecked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, typeDisplayUI, color, content, isLine, isSwipe, isChecked);
    }
}
