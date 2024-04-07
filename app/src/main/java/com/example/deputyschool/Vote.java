package com.example.deputyschool;

import java.io.Serializable;

public class Vote implements Serializable {
    private int id;
    private String type;
    private String titre;
    private int votants;
    private int pour;
    private int contre;
    private int abstention;
    private String position;
    private String positionGroupe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getVotants() {
        return votants;
    }

    public void setVotants(int votants) {
        this.votants = votants;
    }

    public int getPour() {
        return pour;
    }

    public void setPour(int pour) {
        this.pour = pour;
    }

    public int getContre() {
        return contre;
    }

    public void setContre(int contre) {
        this.contre = contre;
    }

    public int getAbstention() {
        return abstention;
    }

    public void setAbstention(int abstention) {
        this.abstention = abstention;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPositionGroupe() {
        return positionGroupe;
    }

    public void setPositionGroupe(String positionGroupe) {
        this.positionGroupe = positionGroupe;
    }

    public Vote(int id, String type, String titre, int votants, int pour, int contre, int abstention, String position, String positionGroupe){
        this.id = id;
        this.type = type;
        this.titre= titre;
        this.votants= votants;
        this.pour=pour;
        this.contre=contre;
        this.abstention=abstention;
        this.position=position;
        this.positionGroupe=positionGroupe;

    }
}
