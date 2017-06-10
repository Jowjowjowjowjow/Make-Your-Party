package app.unirio.makeyourparty.Domain;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import app.unirio.makeyourparty.DAO.ConfiguracaoFirebase;

/**
 * Created by Gabriel on 19/11/2016.
 */
@SuppressWarnings("serial")
public class User implements Serializable {
    private String id;
    private long CPF;
    private String login;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String address;
    private String state;
    private String city;
    private String site;
    private Date birthDate;

    public User(){}


    /**
     * Retirei ID e Login do construtor do BD porque o id será gerado pelo próprio banco do firebase, e o firebase só permite login com email/senha
     * ou com google, facebook e etc...
     * @param CPF
     * @param email
     * @param password
     * @param name
     * @param address
     * @param phone
     * @param city
     * @param state
     * @param site
     * @param bornDate
     */
    //CONSTRUTOR PARA O BD
    public User(long CPF, String email, String password, String name, String address, String phone, String city, String state, String site, Date birthDate) {
        this.CPF = CPF;
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.city = city;
        this.state = state;
        this.site = site;
        this.birthDate = birthDate;
    }
    //CONSTRUTOR PARA USO INTERNO
    public User(String name, String phone, String address, String state, String city, String site, String login) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.state = state;
        this.city = city;
        this.site = site;
        this.login = login;
    }

    public void save(){
        DatabaseReference firebaseReference = ConfiguracaoFirebase.getFirebase();
        firebaseReference.child("Users").child(String.valueOf(getId())).setValue(this);
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> hashMapUser = new HashMap<>();
        hashMapUser.put("Id", getId());
        hashMapUser.put("CPF", getCPF());
        hashMapUser.put("Email", getEmail());
        hashMapUser.put("Password", getPassword());
        hashMapUser.put("Name", getName());
        hashMapUser.put("Address", getAddress());
        hashMapUser.put("Phone", getPhone());
        hashMapUser.put("City", getCity());
        hashMapUser.put("State", getState());
        hashMapUser.put("Site", getSite());
        hashMapUser.put("BirthDate", getBirthDate());

        return hashMapUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCPF() {
        return CPF;
    }

    public void setCPF(long CPF) {
        this.CPF = CPF;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
