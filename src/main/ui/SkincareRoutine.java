package ui;


import javax.swing.*;


public class SkincareRoutine {
    static JFrame mainFrame;
    static JFrame shelfFrame;
    static JFrame routineFrame;

    static String ShelfFile = "shelf";
    static String RoutineFile = "routines";


    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainMenuDisplay mainMenuDisplay = new MainMenuDisplay();
        mainFrame.add(mainMenuDisplay.mainMenuPanel);
        mainFrame.setSize(600,400);
        mainFrame.setVisible(true);

    }

}