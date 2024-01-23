/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author owner
 */
public class event {
    private int eventId;
    private String eventName;
    private String eventCategory;
    private Date eventDate;
    private int price;
    private int specialPrice;
    private String imageUrl;

    public event(int eventId, String eventName, String eventCategory, Date eventDate, int price, int specialPrice, String imageUrl) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventCategory = eventCategory;
        this.eventDate = eventDate;
        this.price = price;
        this.specialPrice = specialPrice;
        this.imageUrl = imageUrl;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(int specialPrice) {
        this.specialPrice = specialPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    
}
