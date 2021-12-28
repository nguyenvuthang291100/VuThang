package controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Thang
 */
public class ClockThread extends Thread {

    private JButton button;

    public ClockThread(JButton button) {
        this.button = button;
    }

    @Override
    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss   aa");
        while (true) {
            Date now = new Date();
            String st = sdf.format(now);
            button.setText(st);
            try{
                Thread.sleep(1000);
            }catch  ( InterruptedException ex){
                
            }
            
        }
    }
}
