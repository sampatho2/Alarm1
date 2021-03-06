/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alarm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javazoom.jl.decoder.JavaLayerException;

/**
 *
 * @author vinnu
 */
public class Alarm_Clock {

    static JFrame backFrame = new JFrame("Alarm");
    JComboBox hrs = new JComboBox();
    JComboBox min = new JComboBox();
    JComboBox ampm = new JComboBox();
    JPanel backPane = new JPanel();
    JPanel timeHold = new JPanel();
    JPanel bottom = new JPanel();
    static JButton set = new JButton("Start");
    JButton tone = new JButton("Tone");

    public static void main(String[] args) {
        Alarm_Clock a1 = new Alarm_Clock();

        a1.prepInterfc();
        
        a1.listenAdd();



    }

    void prepInterfc() {
        bottom.add(set);
        bottom.add(tone);
        backPane.setLayout(new BoxLayout(backPane, BoxLayout.Y_AXIS));
        backPane.add(timeHold);
        setCombo(hrs);
        timeHold.add(hrs);
        setCombo1(min);
        timeHold.add(min);
        ampm.addItem("AM");
        ampm.addItem("PM");
        timeHold.add(ampm);
        backPane.add(bottom);
        backFrame.add(backPane);
        backFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        backFrame.pack();
        backFrame.setResizable(false);
        backFrame.setVisible(true);


    }

    void listenAdd() {
        set.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IsTime it = new IsTime();
            
                if (set.getText().equals("Start") ) {
                    int hh = Integer.parseInt(hrs.getSelectedItem().toString());
                    
                    int mm = Integer.parseInt(min.getSelectedItem().toString());
                    int apm;
                    if (ampm.getSelectedItem().equals("AM")) {
                        apm = Calendar.AM;
                        
                    } else {
                        apm = Calendar.PM;
                        
                    }
                    if ((getFile.fiLoc==null)) {
                        JOptionPane.showMessageDialog(backFrame, "Please select a tone for the alarm");

                        /*URL turl = this.getClass().getResource("rsrc/bb.mp3");
                         String s1=new File(turl.getFile()).getAbsolutePath();
                         System.out.print(turl);
                         try {
                         new mp3Player(s1);
                         } catch (FileNotFoundException ex) {
                         Logger.getLogger(Alarm_Clock.class.getName()).log(Level.SEVERE, null, ex);
                         } catch (JavaLayerException ex) {
                         Logger.getLogger(Alarm_Clock.class.getName()).log(Level.SEVERE, null, ex);
                         }*/
                    }
                    else if(!(getFile.fiLoc.contains(".mp3") )){
                        JOptionPane.showMessageDialog(backFrame, "Please select a tone for the alarm");
                    }

                    else{
                    try {
                        it.setCal(hh, mm, apm);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Alarm_Clock.class.getName()).log(Level.SEVERE, null, ex);
                    }}

                } else if(set.getText().equals("Stop")) {
                    mp3Player.plyr.close();
                }
                
            }
        });
        tone.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new getFile().getFile();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Alarm_Clock.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JavaLayerException ex) {
                    Logger.getLogger(Alarm_Clock.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Alarm_Clock.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    void setCombo(JComboBox h) {
        for (int f = 0; f <= 12; f++) {
            h.addItem(Integer.toString(f));
        }
        h.setSelectedIndex(Calendar.getInstance().get(Calendar.HOUR));

    }

    void setCombo1(JComboBox h) {
        for (int f = 0; f <= 59; f++) {
            h.addItem(Integer.toString(f));
        }
        h.setSelectedIndex((Calendar.getInstance().get(Calendar.MINUTE) + 1) % 60);
    }

    public void setAmpm(JComboBox ampm) {
        this.ampm = ampm;
    }
}
