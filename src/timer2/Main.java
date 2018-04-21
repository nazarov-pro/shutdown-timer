/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timer2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Sammy Guergachi <sguergachi@gmail.com>
 */
public class Main extends javax.swing.JFrame {

    private final File settings = new File(System.getProperty("java.io.tmpdir") + "ASTSetting.properties");
    int hour = 0;
    int minute = 0;
    int second = 0;
    int millsecond = 0;
    int mode = 0;//0 edit//1 running//2 pause
    boolean startover = false;
    boolean always_top = false;

    public Main() {
        initComponents();
        data.setVisible(false);
        start();
        startThread2();
    }

    public void startThread2() {
        new Move2().start();
    }

    class Move2 extends Thread {

        @Override
        public void run() {
            int count1 = 0, count2 = 0, count3 = 0;
            while (true) {

                try {
                    String mounth = null, weekk = null;
                    Calendar cal = new GregorianCalendar();
                    cal.setTime(new Date());
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    int month = cal.get(Calendar.MONTH);
                    int week = cal.get(Calendar.DAY_OF_WEEK);
                    int year = cal.get(Calendar.YEAR);

                    switch (week) {
                        case 1: {
                            weekk = "Sun";
                            break;
                        }
                        case 2: {
                            weekk = "Mon";
                            break;
                        }
                        case 3: {
                            weekk = "Tue";
                            break;
                        }
                        case 4: {
                            weekk = "Wed";
                            break;
                        }
                        case 5: {
                            weekk = "Thu";
                            break;
                        }
                        case 6: {
                            weekk = "Fri";
                            break;
                        }
                        case 7: {
                            weekk = "Sat";
                            break;
                        }
                    }
                    switch (month) {
                        case 0: {
                            mounth = "Jan";
                            break;
                        }
                        case 1: {
                            mounth = "Feb";
                            break;
                        }
                        case 2: {
                            mounth = "Mar";
                            break;
                        }
                        case 3: {
                            mounth = "Apr";
                            break;
                        }
                        case 4: {
                            mounth = "May";
                            break;
                        }
                        case 5: {
                            mounth = "Jun";
                            break;
                        }
                        case 6: {
                            mounth = "Jul";
                            break;
                        }
                        case 7: {
                            mounth = "Aug";
                            break;
                        }
                        case 8: {
                            mounth = "Sep";
                            break;
                        }
                        case 9: {
                            mounth = "Oct";
                            break;
                        }
                        case 10: {
                            mounth = "Nov";
                            break;
                        }
                        case 11: {
                            mounth = "Dec";
                            break;
                        }

                    }
                    int second = cal.get(Calendar.SECOND);
                    int minute = cal.get(Calendar.MINUTE);
                    int hour = cal.get(Calendar.HOUR);
                    int ampm = cal.get(Calendar.AM_PM);

                    if (ampm == 1) {
                        hour = hour + 12;
                        if (hour == 24) {
                            hour = 00;
                        }
                    }
                    String dataa = day + "    " + mounth + ".   " + year + "  " + weekk + ".";
                    String time = hour + "  :  " + minute + "  :  " + second;
                    Thread.sleep(1);
                    count1++;
                    if (count1 == 5000) {
                        count1 = 0;
                        if (count2 == 0) {
                            count2 = 1;
                        } else {
                            count2 = 0;
                        }
                    }

                    if (count2 == 0) {
                        data.setText(time);
                    } else {
                        data.setText(dataa);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                TransparencyMouseClicked(null);
            }

        }
    }

    public void startThread() {
        new Move().start();
    }

    class Move extends Thread {

        @Override
        public void run() {

            while (true) {
                if (mode != 1) {
                    break;
                }

                millsecond--;
                seconds = String.valueOf(second);
                hours = String.valueOf(hour);
                minutes = String.valueOf(minute);

                if (minutes.length() == 1) {
                    minutes = "0" + minutes;
                }
                if (hours.length() == 1) {
                    hours = "0" + hours;
                }
                if (seconds.length() == 1) {
                    seconds = "0" + seconds;
                }

                if (startover) {
                    second = 59;
                    seconds = String.valueOf("59");

                    millsecond = 999;
                    minute--;
                    minutes = String.valueOf(minute);
                    startover = false;
                }
                if (millsecond < 0) {
                    second--;
                    millsecond = 999;
                }
                if (second < 0) {
                    minute--;
                    second = 59;
                    millsecond = 999;
                }
                if (minute < 0) {
                    hour--;
                    minute = 59;
                    second = 59;
                    millsecond = 999;
                }
                if (hour == 0 && minute == 0 && second == 15 && !always_top) {
                    always_tMouseClicked(null);
                }
                lbl_main.setText(hours + ":" + minutes + ":" + seconds);
                if (hour == 0 && minute == 0) {
                    lbl_main.setForeground(Color.red);
                }
                if ((hour == 0 && minute == 0 && second == 00) || minute < 0 || hour < 0) {
                    lbl_main.setForeground(Color.GREEN);
                    lbl_main.setText("BYE");
                    Runtime runtime = Runtime.getRuntime();

                    // System.out.println("Heyy Nigaaa");
                    //Console console = System.console();
                    switch (cmb_select.getSelectedIndex()) {
                        case 0:
                            try {

                                Process proc = runtime.exec("shutdown -s -t 0");

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;

                        case 1:
                            try {
                                Process proc = runtime.exec("shutdown -r -t 0");

                            } catch (Exception e) {
                            }
                            break;

                        case 2:
                            try {

                                Process proc = runtime.exec("shutdown /h");

                                btn_2MouseClicked(null);
                            } catch (Exception e) {

                            }

                            break;

                        case 3:
                            try {

                                Process proc = runtime.exec("shutdown /l");

                            } catch (Exception e) {
                            }
                            break;
                        case 4:
                            try {

                                Process proc = runtime.exec("rundll32.exe powrprof.dll,SetSuspendState 0,1,0");

                            } catch (Exception e) {
                            }
                            break;
                    }
                    break;
                }
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                }

            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Transparency = new javax.swing.JSlider();
        always_t = new javax.swing.JLabel();
        lbl_alwys = new javax.swing.JLabel();
        lbl_moving = new javax.swing.JLabel();
        lbl_after = new javax.swing.JLabel();
        btn_2 = new javax.swing.JLabel();
        data = new javax.swing.JLabel();
        chk_defolt = new javax.swing.JCheckBox();
        btn_1 = new javax.swing.JLabel();
        btn_3 = new javax.swing.JLabel();
        lbl_main = new javax.swing.JLabel();
        cmb_select = new javax.swing.JComboBox();
        btn_d1 = new javax.swing.JLabel();
        btn_u2 = new javax.swing.JLabel();
        btn_u1 = new javax.swing.JLabel();
        btn_d2 = new javax.swing.JLabel();
        btn_icon = new javax.swing.JLabel();
        btn_set = new javax.swing.JLabel();
        btn_help = new javax.swing.JLabel();
        lbl_background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CountDownTimer By Shaheen");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("az/s/h/a/h/i/n/ICON2.png")));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        Transparency.setMajorTickSpacing(5);
        Transparency.setMinimum(20);
        Transparency.setMinorTickSpacing(10);
        Transparency.setOrientation(javax.swing.JSlider.VERTICAL);
        Transparency.setPaintTicks(true);
        Transparency.setToolTipText("Transparency 0%");
        Transparency.setValue(100);
        Transparency.setFocusTraversalPolicyProvider(true);
        Transparency.setOpaque(false);
        Transparency.setVerifyInputWhenFocusTarget(false);
        Transparency.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                TransparencyMouseWheelMoved(evt);
            }
        });
        Transparency.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TransparencyMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TransparencyMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TransparencyMouseReleased(evt);
            }
        });
        Transparency.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TransparencyKeyPressed(evt);
            }
        });
        getContentPane().add(Transparency);
        Transparency.setBounds(3, 70, 30, 130);
        Transparency.getAccessibleContext().setAccessibleName("");

        always_t.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/t_off.png"))); // NOI18N
        always_t.setToolTipText("Always on top");
        always_t.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        always_t.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                always_tMouseClicked(evt);
            }
        });
        getContentPane().add(always_t);
        always_t.setBounds(340, 50, 60, 20);

        lbl_alwys.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_alwys.setText("Always on top");
        getContentPane().add(lbl_alwys);
        lbl_alwys.setBounds(320, 26, 100, 20);

        lbl_moving.setToolTipText("Drag and Move");
        lbl_moving.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                lbl_movingMouseDragged(evt);
            }
        });
        lbl_moving.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbl_movingMousePressed(evt);
            }
        });
        getContentPane().add(lbl_moving);
        lbl_moving.setBounds(30, 0, 320, 27);

        lbl_after.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        lbl_after.setText("after");
        getContentPane().add(lbl_after);
        lbl_after.setBounds(200, 30, 100, 30);

        btn_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/q.png"))); // NOI18N
        btn_2.setToolTipText("Close program");
        btn_2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_2MouseExited(evt);
            }
        });
        getContentPane().add(btn_2);
        btn_2.setBounds(310, 210, 100, 40);

        data.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        data.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        data.setText("23:20:18");
        getContentPane().add(data);
        data.setBounds(110, 210, 200, 40);

        chk_defolt.setBackground(new java.awt.Color(255, 255, 255));
        chk_defolt.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        chk_defolt.setForeground(new java.awt.Color(51, 51, 51));
        chk_defolt.setText("Set as default");
        chk_defolt.setBorder(null);
        chk_defolt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chk_defolt.setInheritsPopupMenu(true);
        chk_defolt.setOpaque(false);
        chk_defolt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_defoltActionPerformed(evt);
            }
        });
        getContentPane().add(chk_defolt);
        chk_defolt.setBounds(120, 210, 180, 40);

        btn_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/s.png"))); // NOI18N
        btn_1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_1MouseExited(evt);
            }
        });
        getContentPane().add(btn_1);
        btn_1.setBounds(10, 210, 100, 40);

        btn_3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/p.png"))); // NOI18N
        btn_3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_3MouseExited(evt);
            }
        });
        getContentPane().add(btn_3);
        btn_3.setBounds(10, 210, 100, 40);

        lbl_main.setFont(new java.awt.Font("Courier New", 0, 80)); // NOI18N
        lbl_main.setForeground(new java.awt.Color(255, 255, 255));
        lbl_main.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_main.setText("00:00");
        lbl_main.setToolTipText("");
        getContentPane().add(lbl_main);
        lbl_main.setBounds(25, 90, 400, 90);

        cmb_select.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Shutdown", "Restart", "Hibernate", "Log Off", "Sleep (deactivate hibernate)" }));
        cmb_select.setToolTipText("Select");
        cmb_select.setOpaque(false);
        cmb_select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_selectActionPerformed(evt);
            }
        });
        getContentPane().add(cmb_select);
        cmb_select.setBounds(10, 30, 180, 30);

        btn_d1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/down.png"))); // NOI18N
        btn_d1.setToolTipText("--");
        btn_d1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_d1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_d1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_d1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_d1MouseExited(evt);
            }
        });
        getContentPane().add(btn_d1);
        btn_d1.setBounds(110, 180, 87, 20);

        btn_u2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/up.png"))); // NOI18N
        btn_u2.setToolTipText("++");
        btn_u2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_u2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_u2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_u2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_u2MouseExited(evt);
            }
        });
        getContentPane().add(btn_u2);
        btn_u2.setBounds(250, 70, 87, 20);

        btn_u1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/up.png"))); // NOI18N
        btn_u1.setToolTipText("++");
        btn_u1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_u1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_u1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_u1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_u1MouseExited(evt);
            }
        });
        getContentPane().add(btn_u1);
        btn_u1.setBounds(110, 70, 87, 20);

        btn_d2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/down.png"))); // NOI18N
        btn_d2.setToolTipText("--");
        btn_d2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_d2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_d2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_d2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_d2MouseExited(evt);
            }
        });
        getContentPane().add(btn_d2);
        btn_d2.setBounds(250, 180, 87, 20);

        btn_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/minim.png"))); // NOI18N
        btn_icon.setToolTipText("Minimize");
        btn_icon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_iconMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_iconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_iconMouseExited(evt);
            }
        });
        getContentPane().add(btn_icon);
        btn_icon.setBounds(388, 0, 30, 27);

        btn_set.setBackground(new java.awt.Color(102, 0, 102));
        btn_set.setForeground(new java.awt.Color(255, 255, 153));
        btn_set.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/set.png"))); // NOI18N
        btn_set.setToolTipText("Setting");
        btn_set.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_set.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_setMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_setMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_setMouseExited(evt);
            }
        });
        getContentPane().add(btn_set);
        btn_set.setBounds(0, 0, 30, 27);

        btn_help.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/help.png"))); // NOI18N
        btn_help.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_help.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_helpMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_helpMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_helpMouseEntered(evt);
            }
        });
        getContentPane().add(btn_help);
        btn_help.setBounds(355, 0, 30, 27);

        lbl_background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/timer.png"))); // NOI18N
        getContentPane().add(lbl_background);
        lbl_background.setBounds(0, 0, 420, 250);
    }// </editor-fold>//GEN-END:initComponents

    private void chk_defoltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_defoltActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_defoltActionPerformed

    private int result = 0;
    private void start() {

        Dimension screenresalution = Toolkit.getDefaultToolkit().getScreenSize();

        int screenx = (int) screenresalution.getWidth();
        int screeny = (int) screenresalution.getHeight();
        if (System.getProperty("os.name").equals("Linux")) {
            this.setBounds(screenx - 420, screeny - 250, 420, 250);
            JOptionPane.showMessageDialog(rootPane, "This program not working on Linux.");
        } else {
            this.setBounds(screenx - 420, screeny - 295, 420, 250);
        }
        int backtrans = 100;
        try {
            Scanner input;
            input = new Scanner(settings);
            if (input.hasNext()) {
                hour = input.nextInt();
                minute = input.nextInt();
                cmb_select.setSelectedIndex(input.nextInt());
                backtrans = input.nextInt();
                int a = input.nextInt();
                if (a == 1) {
                    always_tMouseClicked(null);
                }
                if(input.hasNext()){
                    result = input.nextInt();
                }
                input.close();
                TransparencyMouseClicked(null);
                btn_1.setVisible(false);
                mode = 2;
                btn_1MouseClicked(null);
            }
        } catch (Exception e) {
            settings.setWritable(true);
        }
        Transparency.setValue(backtrans);
    }

    private void btn_2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_2MouseEntered
        btn_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/q2.png")));
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_2MouseEntered

    private void btn_2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_2MouseExited

        btn_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/q.png")));
// TODO add your handling code here:
    }//GEN-LAST:event_btn_2MouseExited

    private void btn_helpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_helpMouseEntered
        btn_help.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/help2.png")));        // TODO add your handling code here:
    }//GEN-LAST:event_btn_helpMouseEntered

    private void btn_helpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_helpMouseExited
        btn_help.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/help.png")));
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_helpMouseExited

    private void btn_iconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_iconMouseEntered
        btn_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/minim2.png")));

// TODO add your handling code here:
    }//GEN-LAST:event_btn_iconMouseEntered

    private void btn_iconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_iconMouseExited
        btn_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/minim.png")));
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_iconMouseExited

    private void btn_setMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_setMouseEntered
        btn_set.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/set2.png")));
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_setMouseEntered

    private void btn_setMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_setMouseExited
        btn_set.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/set.png")));         // TODO add your handling code here:
    }//GEN-LAST:event_btn_setMouseExited

    private void btn_2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_2MouseClicked

        System.exit(0);

// TODO add your handling code here:
    }//GEN-LAST:event_btn_2MouseClicked

    private void btn_d2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_d2MouseExited
        btn_d2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/down.png")));
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_d2MouseExited

    private void btn_d2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_d2MouseEntered
        btn_d2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/down2.png")));
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_d2MouseEntered

    private void btn_d1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_d1MouseExited
        btn_d1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/down.png")));        // TODO add your handling code here:
    }//GEN-LAST:event_btn_d1MouseExited

    private void btn_d1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_d1MouseEntered
        btn_d1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/down2.png")));

// TODO add your handling code here:
    }//GEN-LAST:event_btn_d1MouseEntered

    private void btn_u2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_u2MouseExited
        btn_u2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/up.png")));        // TODO add your handling code here:
    }//GEN-LAST:event_btn_u2MouseExited

    private void btn_u2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_u2MouseEntered
        btn_u2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/up2.png")));        // TODO add your handling code here:
    }//GEN-LAST:event_btn_u2MouseEntered

    private void btn_u1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_u1MouseExited
        btn_u1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/up.png")));        // TODO add your handling code here:
    }//GEN-LAST:event_btn_u1MouseExited

    private void btn_u1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_u1MouseEntered
        btn_u1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/up2.png")));

// TODO add your handling code here:
    }//GEN-LAST:event_btn_u1MouseEntered

    private void btn_1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_1MouseExited

        btn_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/s.png")));

// TODO add your handling code here:
    }//GEN-LAST:event_btn_1MouseExited

    private void btn_1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_1MouseEntered

        btn_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/s2.png")));
    }//GEN-LAST:event_btn_1MouseEntered
    String hours = "";
    String minutes = "";
    private void btn_u1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_u1MouseClicked
        // TODO add your handling code here:
        hour++;
        if (hour > 23) {
            hour = 0;
        }
        lbl_main.setForeground(Color.ORANGE);
        if (minute == 0 && hour == 0) {
            lbl_main.setForeground(Color.red);
        }
        hours = String.valueOf(hour);
        minutes = String.valueOf(minute);
        if (minutes.length() == 1) {
            minutes = "0" + minutes;
        }
        if (hours.length() == 1) {
            hours = "0" + hours;
        }

        lbl_main.setText(hours + ":" + minutes);
    }//GEN-LAST:event_btn_u1MouseClicked

    private void btn_d1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_d1MouseClicked
        hour--;
        if (hour < 0) {
            hour = 23;
        }
        String lbltext = lbl_main.getText();
        hours = String.valueOf(hour);
        minutes = String.valueOf(minute);
        lbl_main.setForeground(Color.ORANGE);
        if (minute == 0 && hour == 0) {
            lbl_main.setForeground(Color.red);
        }
        if (minutes.length() == 1) {
            minutes = "0" + minutes;
        }
        if (hours.length() == 1) {
            hours = "0" + hours;
        }
        lbl_main.setText(hours + ":" + minutes);

// TODO add your handling code here:
    }//GEN-LAST:event_btn_d1MouseClicked

    private void btn_u2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_u2MouseClicked
        minute++;
        if (minute > 59) {
            minute = 0;
        }
        hours = String.valueOf(hour);
        minutes = String.valueOf(minute);
        lbl_main.setForeground(Color.ORANGE);
        if (minute == 0 && hour == 0) {
            lbl_main.setForeground(Color.red);
        }
        if (minutes.length() == 1) {
            minutes = "0" + minutes;
        }
        if (hours.length() == 1) {
            hours = "0" + hours;
        }

        lbl_main.setText(hours + ":" + minutes);
// TODO add your handling code here:
    }//GEN-LAST:event_btn_u2MouseClicked

    private void btn_d2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_d2MouseClicked
        minute--;
        if (minute < 0) {
            minute = 59;
        }
        lbl_main.setForeground(Color.ORANGE);
        if (minute == 0 && hour == 0) {
            lbl_main.setForeground(Color.red);
        }
        hours = String.valueOf(hour);
        minutes = String.valueOf(minute);

        if (minutes.length() == 1) {
            minutes = "0" + minutes;
        }
        if (hours.length() == 1) {
            hours = "0" + hours;
        }

        lbl_main.setText(hours + ":" + minutes);
    }//GEN-LAST:event_btn_d2MouseClicked

    private void btn_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_1MouseClicked
        
        if (chk_defolt.isSelected()) {
            try {
                int boolconvert = 0;
                if (always_top) {
                    boolconvert = 1;
                }
                PrintWriter output;
                if (!settings.exists()) {
                    settings.createNewFile();
                }
                output = new PrintWriter(settings);
                output.println(hour);
                output.println(minute);
                output.println(cmb_select.getSelectedIndex());
                output.println(Transparency.getValue());
                output.println(boolconvert);
                output.println(result);
                output.close();
            } catch (Exception e) {
                lbl_after.setText("Error");
            }
        }
        lbl_main.setForeground(Color.white);
        if (minute == 0 && hour == 0) {
            lbl_main.setForeground(Color.red);
        }
        if (mode == 2) {
            lbl_main.setText(hours + ":" + minutes + ":" + seconds);
            mode = 1;
            startThread();
            falsing();
        } else if (mode == 0) {
            if (minute == 0 && hour == 0) {
                lbl_main.setForeground(Color.red);
            } else {
                startover = true;
                lbl_main.setText(hours + ":" + minutes + ":" + seconds);
                mode = 1;
                falsing();
                startThread();
            }
         if (cmb_select.getSelectedIndex() == 4 && result != 1) {
                    Object[] choices = {"Ok","Do not ask again"};
                    Object defaultChoice = choices[1];
                    result = JOptionPane.showOptionDialog(rootPane,
                            "If you need to use sleep mode you must disable hibernate mode (you can use one of methods which describe below):\n"
                                    + "1)You can disable it with command line with this command 'powercfg.exe -h off' make sure you use it with adminstrator role (you can activate it again with this command 'powercfg.exe -h on')\n"
                                    + "2)Follow this path Control Panel\\All Control Panel Items\\Power Options\\Edit Plan Settings\\Change advanced power settings  -and expand sleep section then Allow hybrid sleep set Settings to False",
                            "Sleep mode requirement",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            choices,
                            defaultChoice);
         }
        }


    }//GEN-LAST:event_btn_1MouseClicked
    String seconds = "";

    private void falsing() {
        boolean a = false;
        chk_defolt.setVisible(a);
        data.setVisible(true);
        btn_1.setVisible(a);
        btn_d1.setVisible(a);
        btn_d2.setVisible(a);
        btn_u1.setVisible(a);
        btn_u2.setVisible(a);
        cmb_select.setEnabled(a);
        btn_3.setVisible(true);

    }

    private void btn_iconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_iconMouseClicked
        this.setState(Main.ICONIFIED);
// TODO add your handling code here:
    }//GEN-LAST:event_btn_iconMouseClicked

    private void btn_setMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_setMouseClicked

        new Move().stop();

        btn_3MouseClicked(evt);
        lbl_main.setForeground(Color.orange);
        boolean a = true;
        chk_defolt.setVisible(a);
        data.setVisible(false);
        btn_1.setVisible(a);
        btn_d1.setVisible(a);
        btn_d2.setVisible(a);
        btn_u1.setVisible(a);
        btn_u2.setVisible(a);

        cmb_select.setEnabled(a);
        hours = String.valueOf(hour);
        minutes = String.valueOf(minute);

        if (minutes.length() == 1) {
            minutes = "0" + minutes;
        }
        if (hours.length() == 1) {
            hours = "0" + hours;
        }
        mode = 0;
        if (minute == 0 && hour == 0) {
            lbl_main.setForeground(Color.red);
        }
        lbl_main.setText(hours + ":" + minutes);
// TODO add your handling code here:
    }//GEN-LAST:event_btn_setMouseClicked
    private void btn_helpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_helpMouseClicked

        JOptionPane.showMessageDialog(rootPane, "           Countdown Timer \nOperating System : " + System.getProperty("os.name") + "\nVersion : 2.8.0\nE-Mail : shaheennazarov@gmail.com\nWeb Site: shahin-nazarov.appcloud.com\n"
                + "Copyright © 2016 by Shaheen Nezerov"
                + "\nAll rights reserved",
                "About", JOptionPane.INFORMATION_MESSAGE, new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/ICON1.png")));
    }//GEN-LAST:event_btn_helpMouseClicked


    private void btn_3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_3MouseClicked
        lbl_main.setForeground(Color.GRAY);
        mode = 2;
        new Move().stop();

        btn_1.setVisible(true);
        btn_3.setVisible(false);
    }//GEN-LAST:event_btn_3MouseClicked

    private void btn_3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_3MouseEntered
        // TODO add your handling code here:
        btn_3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/p2.png")));
    }//GEN-LAST:event_btn_3MouseEntered

    private void btn_3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_3MouseExited
        btn_3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/p.png")));
// TODO add your handling code here:
    }//GEN-LAST:event_btn_3MouseExited

    private void TransparencyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TransparencyMouseClicked
//        System.out.println(Window);
        double optiacy = Transparency.getValue();
        if (optiacy < 10) {
            optiacy = 10;
        }
        this.setOpacity((float) optiacy / 100);
        Transparency.setToolTipText("Transparency " + (100 - Transparency.getValue()) + "%");

// TODO add your handling code here:
    }//GEN-LAST:event_TransparencyMouseClicked

    private void TransparencyMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_TransparencyMouseWheelMoved
        TransparencyMouseClicked(evt);
        // TODO add your handling code here:
    }//GEN-LAST:event_TransparencyMouseWheelMoved

    private void TransparencyMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TransparencyMousePressed
        TransparencyMouseClicked(evt);
        // TODO add your handling code here:
    }//GEN-LAST:event_TransparencyMousePressed

    private void TransparencyMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TransparencyMouseReleased
        TransparencyMouseClicked(evt);
        // TODO add your handling code here:
    }//GEN-LAST:event_TransparencyMouseReleased

    private void TransparencyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TransparencyKeyPressed
        double optiacy = Transparency.getValue();
        if (optiacy < 20) {
            optiacy = 20;
        }

        this.setOpacity((float) optiacy / 100);
        Transparency.setToolTipText("Transparency " + (100 - Transparency.getValue()) + "%");
        Transparency.createToolTip();
        // TODO add your handling code here:
    }//GEN-LAST:event_TransparencyKeyPressed
    int xmouse, ymouse;
    private void lbl_movingMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_movingMousePressed
        xmouse = evt.getX();
        ymouse = evt.getY();
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_movingMousePressed

    private void lbl_movingMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_movingMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xmouse - lbl_moving.getX(), y - ymouse);        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_movingMouseDragged

    private void always_tMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_always_tMouseClicked
        if (always_top) {
            this.setAlwaysOnTop(!always_top);
            always_t.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/t_off.png")));
            always_top = false;
        } else {
            this.setAlwaysOnTop(!always_top);
            always_t.setIcon(new javax.swing.ImageIcon(getClass().getResource("/az/s/h/a/h/i/n/t_on.png")));
            always_top = true;
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_always_tMouseClicked

    private void cmb_selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_selectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_selectActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName()) && System.getProperty("os.name").equals("Linux")) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                } else if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider Transparency;
    private javax.swing.JLabel always_t;
    private javax.swing.JLabel btn_1;
    private javax.swing.JLabel btn_2;
    private javax.swing.JLabel btn_3;
    private javax.swing.JLabel btn_d1;
    private javax.swing.JLabel btn_d2;
    private javax.swing.JLabel btn_help;
    private javax.swing.JLabel btn_icon;
    private javax.swing.JLabel btn_set;
    private javax.swing.JLabel btn_u1;
    private javax.swing.JLabel btn_u2;
    private javax.swing.JCheckBox chk_defolt;
    private javax.swing.JComboBox cmb_select;
    private javax.swing.JLabel data;
    private javax.swing.JLabel lbl_after;
    private javax.swing.JLabel lbl_alwys;
    private javax.swing.JLabel lbl_background;
    private javax.swing.JLabel lbl_main;
    private javax.swing.JLabel lbl_moving;
    // End of variables declaration//GEN-END:variables
}
