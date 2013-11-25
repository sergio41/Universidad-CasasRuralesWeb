package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        StarRater starRater = new StarRater(10, 0, 0);
        starRater.addStarListener(
            new StarRater.StarListener()   {

                public void handleSelection(int selection) {
                    System.out.println(selection);
                }
            });
        panel.add(starRater);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}