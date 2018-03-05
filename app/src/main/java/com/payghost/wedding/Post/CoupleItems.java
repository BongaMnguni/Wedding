package com.payghost.wedding.Post;

/**
 * Created by Payghost on 2/23/2018.
 */

public class CoupleItems {

    private String image;
    private String description;
    private String id;


    public CoupleItems( String id, String image, String description) {
        this.image = image;
        this.description = description;
        this.id = id;
    }
    public CoupleItems(String image, String description) {
        this.image = image;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
