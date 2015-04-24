/*
 * OpenWord 1.0
 * Multi-platform Français, English wording engine
 * Development & Maintain +AururaTeam on Google+.
 *
 * Published under GPLv3 License. No right reserved.
 */
package net.aurura.openwordjava;

/**
 *
 * @author jiangyue
 */
public class ClassLoader {
    
    /* General Settings Panel */
    public int STREAK_LEVEL = 5;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Data side ready */
        WordMgr manager = new WordMgr();
        /* Launch Interface */
        WordDashboard board = new WordDashboard(manager);
    }
    
}
