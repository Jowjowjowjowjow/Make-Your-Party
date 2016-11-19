package app.unirio.makeyourparty.Domain;

import java.util.Date;

/**
 * Created by Gabriel on 18/11/2016.
 */
public class Event {
    private int photo;
    private String name;
    private String date;
    private String description;
    private String hour;
    private String city;
    private String adress;
    private double price;

    public Event(){}

    public Event(int ph, String n, String dt, String d, String h, String c, String a, Double p){
        this.photo = ph;
        this.name = n;
        this.date = dt;
        this.description = d;
        this.hour = h;
        this.city = c;
        this.adress = a;
        this.price = p;
    }

    public Event(int p, String n, String dt, String d, String c, String a) {
        this.photo = p;
        this.name = n;
        this.date = dt;
        this.description = d;
        this.city = c;
        this.adress = a;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
