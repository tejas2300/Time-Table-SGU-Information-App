package com.example.ttsgu;

public class LectureModel {
    String s,f,t,l;

    public LectureModel() {
    }

    public LectureModel(String s, String f, String t, String l) {
        this.s = s;
        this.f = f;
        this.t = t;
        this.l = l;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }
}
