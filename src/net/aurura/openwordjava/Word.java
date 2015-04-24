/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.aurura.openwordjava;

/**
 *
 * @author jiangyue
 */
public class Word {
    
    private String text;
    private String define;
    private String note;
    private int streak;
    
    public String getText() {
        return this.text;
    }
    
    public void setText(String t) {
        this.text = t;
    }
    
    public String getDefine() {
        return this.define;
    }
    
    public void setDefine(String t) {
        this.define = t;
    }
    
    public String getNote() {
        return this.note;
    }
    
    public void setNote(String t) {
        this.note = t;
    }
    
    public int getStreak() {
        return this.streak;
    }
    
    public void setStreak(int t) {
        this.streak = t;
    }
    
    
}
