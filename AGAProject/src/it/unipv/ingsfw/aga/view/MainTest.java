package it.unipv.ingsfw.aga.view;

import javax.swing.*;
import it.unipv.ingsfw.aga.controller.Controller;
import java.awt.*;

public class MainTest {
    public static void main(String[] args) {

        JFrame frame = new JFrame("AGA");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        
        

        Controller controller = new Controller();

        frame.add(controller.getContainerPanel());

        frame.setVisible(true);
    }
}
