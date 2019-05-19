/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.axiostheos.grader3000;

/**
 *
 * @author Dave
 */
public class ModelTable {

    String name;
    String nationality;
    int currentsem;
    String nepcode;
    String modoftrain;
    String major;

    public ModelTable(String name, String nationality, int currentsem, String nepcode, String modoftrain, String major) {
        this.name = name;
        this.nationality = nationality;
        this.currentsem = currentsem;
        this.nepcode = nepcode;
        this.modoftrain = modoftrain;
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getCurrentsem() {
        return currentsem;
    }

    public void setCurrentsem(int currentsem) {
        this.currentsem = currentsem;
    }

    public String getNepcode() {
        return nepcode;
    }

    public void setNepcode(String nepcode) {
        this.nepcode = nepcode;
    }

    public String getModoftrain() {
        return modoftrain;
    }

    public void setModoftrain(String modoftrain) {
        this.modoftrain = modoftrain;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

}
