/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basketballreservationsystem;

import config.Session;
import config.connectDB;
import config.passwordHasher;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class LOGIN1 extends javax.swing.JFrame {

    /**
     * Creates new form LOGIN
     */
    public LOGIN1() {
        initComponents();
    }
 static String status;
    
public static boolean loginnAcc(String username, String password) throws NoSuchAlgorithmException {
    connectDB connector = new connectDB();
    try {
        String query = "SELECT id, firstname, lastname, username, Email, usertype, status, password FROM users WHERE username = ?";
        PreparedStatement pstmt = connector.getConnection().prepareStatement(query);
        pstmt.setString(1, username); // Only set username
        ResultSet resultSet = pstmt.executeQuery();

        if (resultSet.next()) {
            String hashedPass = resultSet.getString("password"); // Get stored hashed password
            String rehashedPass = passwordHasher.hashPassword(password); // Hash the entered password

            if (hashedPass.equals(rehashedPass)) {
                status = resultSet.getString("status");

                Session sess = Session.getInstance();
                sess.setId(resultSet.getInt("id"));
                sess.setFirstname(resultSet.getString("firstname"));
                sess.setLastname(resultSet.getString("lastname"));
                sess.setUsername(resultSet.getString("username"));
                sess.setEmail(resultSet.getString("Email"));
                sess.setUsertype(resultSet.getString("usertype"));
                sess.setStatus(status);

                return true; 
            } else {
                JOptionPane.showMessageDialog(null, "Invalid password. Please try again.");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username. Please try again.");
            return false;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;
    } finally {
        connector.closeConnection(); 
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        username = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Victor Wembanyama — San Antonio Spurs.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");

        jLabel4.setText("jLabel4");

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo basketball 300.jpg"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 300, 440));

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Log in");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, 140, -1));
        jPanel2.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 210, 30));
        jPanel2.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 210, 30));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel2.setText("Log in");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, -1, -1));

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel3.setText("Password:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, -1));

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel6.setText("User Name:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        jCheckBox1.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        jCheckBox1.setText("Remember me");
        jPanel2.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, -1));

        jLabel7.setFont(new java.awt.Font("SansSerif", 2, 11)); // NOI18N
        jLabel7.setText("Click SignUp");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 360, -1, 20));

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        jLabel8.setText("Terms and Condition");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, -1, 20));

        jLabel9.setFont(new java.awt.Font("SansSerif", 2, 11)); // NOI18N
        jLabel9.setText("Do you have an account? ");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, -1, 20));

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        jLabel10.setText("Forgot Password");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, -1, 40));

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        jLabel11.setText("Privacy & Policy");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, -1, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        REGISTER r = new REGISTER();
        r.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    String user = username.getText();
    String pass = new String(password.getPassword());

    if (user.isEmpty() || pass.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill in both username and password fields.");
    } else {
        try {
        if (loginnAcc(user, pass)) {
            Session sess = Session.getInstance();
            if (sess == null) {
                JOptionPane.showMessageDialog(null, "Session error. Please contact admin.");
                return;
            }
            
            String status = sess.getStatus();
            if (!"Active".equalsIgnoreCase(status)) {
                JOptionPane.showMessageDialog(null, "IN-ACTIVE ACCOUNT, CONTACT THE ADMIN");
                return;
            }

            String userType = sess.getUsertype();
            JOptionPane.showMessageDialog(null, "LOG IN SUCCESSFULLY");

            switch (userType.toLowerCase()) {
                case "admin": 
                    AdminDashboardd ad = new AdminDashboardd();
                    ad.setVisible(true);
                    this.dispose();
                    break;
                case "staff": 
                    UserDashboard rc = new UserDashboard();
                    rc.setVisible(true);
                    this.dispose();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Unknown user type. Contact the admin.");
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "INVALID ACCOUNT");
        }
    } catch (NoSuchAlgorithmException ex) {
        Logger.getLogger(LOGIN1.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    }//GEN-LAST:event_jButton2ActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LOGIN1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LOGIN1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LOGIN1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LOGIN1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LOGIN1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
