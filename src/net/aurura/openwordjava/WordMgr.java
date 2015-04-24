/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.aurura.openwordjava;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jiangyue
 */
class WordMgr extends java.lang.Object implements Serializable {

    private ArrayList<Word> wordls;
    private Word sessionWord;
    private boolean sessionExist;
    private String workingFile;

    public WordMgr() {
        this.wordls = new ArrayList<>();
        this.workingFile = "words.md";
    }

    /**
     * Word addition of WordManager.
     *
     * @param text The word
     * @param define The define
     * @param note The notation
     */
    void addWord(String text, String define, String note) {
        Word wd = new Word();
        wd.setDefine(define);
        wd.setNote(note);
        wd.setText(text);
        wd.setStreak(5);
        /* Throw it into flood */
        wordls.add(wd);
        saveOnDisk();
    }

    void saveOnDisk() {

//        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
//                new FileOutputStream("words.data"), "utf-8"))) {
//            writer.write("something");
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(WordMgr.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(WordMgr.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(WordMgr.class.getName()).log(Level.SEVERE, null, ex);
//        }
        PrintWriter strm = null;
        try {
            strm = new PrintWriter(new FileWriter(workingFile));
            strm.printf("Words: %d\n\n", wordls.size());
            strm.println("| Word | Define | Notation | Streak |");
            strm.println("|------|--------|----------|--------|");
            for (int i = 0; i < wordls.size(); i++) {
                Word wd = (Word) wordls.get(i);
                strm.print("|");
                strm.print(wd.getText());
                strm.print("|");
                strm.print(wd.getDefine());
                strm.print("|");
                strm.print(wd.getNote());
                strm.print("|");
                strm.print(wd.getStreak());
                strm.println("|");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WordMgr.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WordMgr.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            strm.close();
        }

    }

    void loadOnDisk() {
        try {
            this.wordls = new ArrayList<Word>();
            BufferedReader strm = null;
            strm = new BufferedReader(new FileReader(workingFile));
            String wrds = strm.readLine();
            int count = Integer.valueOf(wrds.substring(7));
            strm.readLine(); strm.readLine(); strm.readLine();
            for(int i=0; i<count; i++) {
                wrds = strm.readLine();
                String[] result;
                result = wrds.split("\\|");
                Word wd = new Word();
                wd.setText(result[1]);
                wd.setDefine(result[2]);
                wd.setNote(result[3]);
                wd.setStreak(Integer.valueOf(result[4]));
                wordls.add(wd);
            }
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WordMgr.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WordMgr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
     void saveOnDisk() {
     FileOutputStream outstream = null;
     try {
     outstream = new FileOutputStream("test.txt");
     ObjectOutputStream out = new ObjectOutputStream(outstream);
     out.writeObject(wordls); //将这个对象写入流
     out.close();
     } catch (FileNotFoundException ex) {
     Logger.getLogger(WordMgr.class.getName()).log(Level.SEVERE, null, ex);
     } catch (IOException ex) {
     Logger.getLogger(WordMgr.class.getName()).log(Level.SEVERE, null, ex);
     } finally {
     try {
     outstream.close();
     } catch (IOException ex) {
     Logger.getLogger(WordMgr.class.getName()).log(Level.SEVERE, null, ex);
     }
     }
     }
    
     void loadOnDisk() {
     ObjectInputStream in = null;
     try {
     in = new ObjectInputStream(new FileInputStream("test.txt"));
     wordls = (WordList)in.readObject();
     in.close();
     } catch (FileNotFoundException ex) {
     Logger.getLogger(WordMgr.class.getName()).log(Level.SEVERE, null, ex);
     } catch (IOException ex) {
     Logger.getLogger(WordMgr.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
     Logger.getLogger(WordMgr.class.getName()).log(Level.SEVERE, null, ex);
     } finally {
     try {
     in.close();
     } catch (IOException ex) {
     Logger.getLogger(WordMgr.class.getName()).log(Level.SEVERE, null, ex);
     }
     }
     }
    
     */

    /**
     * Start a new Word Q&A Session.
     *
     * @return A string to be noticed by client
     */
    String startWordSession() {
        int count = wordls.size();
        int random = (int) (Math.random() * count);
        this.sessionWord = (Word) wordls.get(random);
        this.sessionExist = true;
        saveOnDisk();
        return sessionWord.getDefine();
    }

    boolean wordSessionCheck(String wordGiven) {
        boolean bool = (wordGiven.equals(sessionWord.getText()));
        if (bool) {
            sessionWord.setStreak(sessionWord.getStreak() - 1);
        }
        saveOnDisk();
        return (bool);
    }

    Word getSessionWord() {
        saveOnDisk();
        return sessionWord;
    }

    boolean isSessionExist() {
        saveOnDisk();
        return this.sessionExist;
    }

    void killSession() {
        sessionWord = null;
        this.sessionExist = false;
        saveOnDisk();
    }

    String getWorkingFile() {
        return this.workingFile;
    }

    void changeWorkingFile(String text) {
        saveOnDisk();
        this.wordls = new ArrayList<Word>();
        this.workingFile = text;
    }

}
