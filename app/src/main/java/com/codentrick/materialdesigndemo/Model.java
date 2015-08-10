package com.codentrick.materialdesigndemo;

/**
 * Created by haint on 8/10/2015.
 */
public class Model {
    private String text;
    private String image;

    public Model(String text, String image) {
        this.text = text;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
