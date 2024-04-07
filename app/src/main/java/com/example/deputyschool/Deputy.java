package com.example.deputyschool;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

public class Deputy implements Serializable {

    private int id;
    private String firstname;
    private String lastname;
    private String department;
    private int numCirco;
    private String nameCirco;

    private String sigle;

    private String party;

    private ArrayList<String> collabos, emails, adresses;

    public ArrayList<String> getCollabos() {
        return collabos;
    }

    public void setCollabos(ArrayList<String> collabos) {
        this.collabos = collabos;
    }

    public ArrayList<String> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<String> emails) {
        this.emails = emails;
    }

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public ArrayList<String> getAdresses() {
        return adresses;
    }

    public void setAdresses(ArrayList<String> adresses) {
        this.adresses = adresses;
    }

    public Deputy(int id, String firstname, String lastname, String department, int numCirco, String nameCirco, String party, String sigle) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.numCirco = numCirco;
        this.nameCirco = nameCirco;
        this.party = party;
        this.sigle = sigle;
        this.collabos = new ArrayList<String>();
        this.emails = new ArrayList<String>();
        this.adresses = new ArrayList<String>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getNumCirco() {
        return numCirco;
    }

    public void setNumCirco(int numCirco) {
        this.numCirco = numCirco;
    }

    public String getNameCirco() {
        return nameCirco;
    }

    public void setNameCirco(String nameCirco) {
        this.nameCirco = nameCirco;
    }


    public String getNameForAvatar(){
        return firstname.replace(' ', '-').toLowerCase()+
                "-"+lastname.replace(' ', '-').toLowerCase();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        Deputy d= (Deputy) obj;
        return id==d.getId();
    }

    public void addCollabo(String newCollabo){
        this.collabos.add(newCollabo);
    }

    public void addEmail(String newEmail){
        this.emails.add(newEmail);
    }
    public void addAdresse(String newAdresse){
        this.adresses.add(newAdresse);
    }
}
