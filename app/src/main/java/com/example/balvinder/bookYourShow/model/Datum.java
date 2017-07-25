package com.example.balvinder.bookYourShow.model;

import java.util.List;

public class Datum {

    private String id;
    private String n;
    private String sh;
    private String l;
    private String mf;
    private String lc;
    private String ul;
    private String c;
    private List<Object> mfs = null;
    private String lng;
    private String imf;
    private String t;
    private String i;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The n
     */
    public String getN() {
        return n;
    }

    /**
     *
     * @param n
     * The n
     */
    public void setN(String n) {
        this.n = n;
    }

    /**
     *
     * @return
     * The sh
     */
    public String getSh() {
        return sh;
    }

    /**
     *
     * @param sh
     * The sh
     */
    public void setSh(String sh) {
        this.sh = sh;
    }

    /**
     *
     * @return
     * The l
     */
    public String getL() {
        return l;
    }

    /**
     *
     * @param l
     * The l
     */
    public void setL(String l) {
        this.l = l;
    }

    /**
     *
     * @return
     * The mf
     */
    public String getMf() {
        return mf;
    }

    /**
     *
     * @param mf
     * The mf
     */
    public void setMf(String mf) {
        this.mf = mf;
    }

    /**
     *
     * @return
     * The lc
     */
    public String getLc() {
        return lc;
    }

    /**
     *
     * @param lc
     * The lc
     */
    public void setLc(String lc) {
        this.lc = lc;
    }

    /**
     *
     * @return
     * The ul
     */
    public String getUl() {
        return ul;
    }

    /**
     *
     * @param ul
     * The ul
     */
    public void setUl(String ul) {
        this.ul = ul;
    }

    /**
     *
     * @return
     * The c
     */
    public String getC() {
        return c;
    }

    /**
     *
     * @param c
     * The c
     */
    public void setC(String c) {
        this.c = c;
    }

    /**
     *
     * @return
     * The mfs
     */
    public List<Object> getMfs() {
        return mfs;
    }

    /**
     *
     * @param mfs
     * The mfs
     */
    public void setMfs(List<Object> mfs) {
        this.mfs = mfs;
    }

    /**
     *
     * @return
     * The lng
     */
    public String getLng() {
        return lng;
    }

    /**
     *
     * @param lng
     * The lng
     */
    public void setLng(String lng) {
        this.lng = lng;
    }

    /**
     *
     * @return
     * The imf
     */
    public String getImf() {
        return imf;
    }

    /**
     *
     * @param imf
     * The imf
     */
    public void setImf(String imf) {
        this.imf = imf;
    }

    /**
     *
     * @return
     * The t
     */
    public String getT() {
        return t;
    }

    /**
     *
     * @param t
     * The t
     */
    public void setT(String t) {
        this.t = t;
    }

    /**
     *
     * @return
     * The i
     */
    public String getI() {
        return i;
    }

    /**
     *
     * @param i
     * The i
     */
    public void setI(String i) {
        this.i = i;
    }


//private String id;
//private String n;
//private String sh;
//private String l;
//private String mf;
//private String lc;
//private String ul;
//private String c;
//
//private String lng;
//private String imf;
//private String t;
//private String i;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getN() {
//        return n;
//    }
//
//    public void setN(String n) {
//        this.n = n;
//    }
//
//    public String getL() {
//        return l;
//    }
//
//    public void setL(String l) {
//        this.l = l;
//    }
//
//    public String getSh() {
//        return sh;
//    }
//
//    public void setSh(String sh) {
//        this.sh = sh;
//    }
//
//    public String getMf() {
//        return mf;
//    }
//
//    public void setMf(String mf) {
//        this.mf = mf;
//    }
//
//    public String getLc() {
//        return lc;
//    }
//
//    public void setLc(String lc) {
//        this.lc = lc;
//    }
//
//    public String getUl() {
//        return ul;
//    }
//
//    public void setUl(String ul) {
//        this.ul = ul;
//    }
//
//    public String getC() {
//        return c;
//    }
//
//    public void setC(String c) {
//        this.c = c;
//    }
//
//    public String getLng() {
//        return lng;
//    }
//
//    public void setLng(String lng) {
//        this.lng = lng;
//    }
//
//    public String getImf() {
//        return imf;
//    }
//
//    public void setImf(String imf) {
//        this.imf = imf;
//    }
//
//    public String getT() {
//        return t;
//    }
//
//    public void setT(String t) {
//        this.t = t;
//    }
//
//    public String getI() {
//        return i;
//    }
//
//    public void setI(String i) {
//        this.i = i;
//    }
    public Datum(String name, String detail, String thumbnail) {
        this.n = name;
        this.sh = detail;
        this.i = thumbnail;
    }

}