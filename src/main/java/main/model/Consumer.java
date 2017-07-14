package main.model;

/**
 * Created by video on 14.07.2017.
 */
public class Consumer {
    private long id;
    private String name;
    private String surname;
    private String tel;
    private String address;

    public Consumer(long id, String name, String surname, String tel, String address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.tel = tel;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
