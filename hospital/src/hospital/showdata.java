package hospital;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Abhishek
 */
public class showdata extends javax.swing.JFrame {

    /**
     * Creates new form print
     */
    int sno;
    public showdata() {
        initComponents();
         this.setTitle("Exporter");
 
    this.setIconImage(Toolkit.getDefaultToolkit().getImage("icon.jpg"));
        
    }
        double total=0;
        int patient=0;
    public Connection getConnection(){
    Connection con=null;
        try {
            con=(Connection) DriverManager.getConnection("jdbc:sqlite:pp.db");
            
          
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(hospial2.class.getName()).log(Level.SEVERE, null, ex);
           
            return con;
        }
}
public ArrayList<patient> getProductList()
    {
    PreparedStatement st =null;
            ResultSet rs = null;
            ArrayList<patient> patientList  = new ArrayList<patient>();
            Connection con = getConnection();
 
        try {      

                     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
Date lower = start.getDate();
String strDate = dateFormat.format(lower);
DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
Date upper = end.getDate();
String endDate = dateFormat1.format(upper);
String sql = "SELECT * FROM patients WHERE pDate BETWEEN ? AND ?;";
st = con.prepareStatement(sql);
st.setString(1,strDate);
st.setString(2, endDate);
 rs = st.executeQuery();
            

            
            patient patient1;
            
            while(rs.next())
            {
                
                patient1 = new patient(rs.getString("pid"),rs.getString("name"),rs.getString("gender"),rs.getString("address"),rs.getString("phone"),rs.getString("dob"),rs.getString("docter"),rs.getFloat("Amount"),rs.getString("PaymentMode"),rs.getString("pDate"),rs.getString("remark"),rs.getString("treatment"),rs.getString("dop"),rs.getString("print"));
                patientList.add(patient1);
                total+=Double.parseDouble(rs.getString("Amount"));

                 sno++;
                 
            }
            patient=sno;
             System.out.println(total);
           System.out.println(sno);
        } catch (SQLException ex) {
            Logger.getLogger(hospial2.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
                rs.close();
                st.close();
                con.close();
            }catch(Exception e){
                
            }
        }
            
        return patientList; 
                
    }
DefaultTableModel  dm = new DefaultTableModel();
 public void Show_Products_In_JTable() throws SQLException
    {
        ArrayList<patient> list = getProductList();
        DefaultTableModel model = (DefaultTableModel)print1.getModel();
        // clear jtable content
        model.setRowCount(0);
        Object[] row = new Object[15];
        for(int i = 0; i < list.size(); i++)
        {
                    row[0] = list.get(i).getId();
             row[1] = list.get(i).getDate();
            row[2] = list.get(i).getName();
            row[3] = list.get(i).getDob();
             row[4] = list.get(i).getGender();
            row[5] = list.get(i).getAdd();
            row[6] = list.get(i).getDocter();
             row[7] = list.get(i).getPhone();
            row[8] = list.get(i).getAmt();
            row[9] = list.get(i).getDop();
            row[10] = list.get(i).getMode();
             row[11] = list.get(i).getRemark();
            
            model.addRow(row);
        }
           Object[] row1 = new Object[15];
     for(int i = 0; i < list.size(); i++)
        {
            
            
                row1[0]="";
            row1[1] = "";
            row1[2]="No of p: "+patient;
            row1[3]="";
            row1[4]="";
            row1[5]="";
            row1[6]="";
            row1[7] = "";
            row1[8]="Total: "+total;
      row1[9]="";
       row1[10]="";
        row1[11]="";
        
                    }
     model.addRow(row1);
    
    }
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        print1 = new javax.swing.JTable();
        start = new com.toedter.calendar.JDateChooser();
        end = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Start date:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("End date:");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Export in xls");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        print1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "P.id", "Date", "Name", "Age", "Gender", "Doctor", "Adresss", "Phone No", "Amount", "DOP", "Paymant mode", "Remark"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, false, false, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        print1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                print1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(print1);

        start.setDateFormatString("yyyy-MM-dd");

        end.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(end, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1085, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(start, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(end, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        try {
            Show_Products_In_JTable();
            start.setCalendar(null);
            end.setCalendar(null);
        } catch (SQLException ex) {
            Logger.getLogger(showdata.class.getName()).log(Level.SEVERE, null, ex);
        }
        export();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void print1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print1MouseClicked

    }//GEN-LAST:event_print1MouseClicked

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
            java.util.logging.Logger.getLogger(showdata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(showdata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(showdata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(showdata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new showdata().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser end;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable print1;
    private com.toedter.calendar.JDateChooser start;
    // End of variables declaration//GEN-END:variables

    private void export() {
        try{
     JFileChooser fileChooser = new JFileChooser();
    int retval = fileChooser.showSaveDialog(jButton1);

    if (retval == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        if (file != null) {
            if (!file.getName().toLowerCase().endsWith(".xls")) {
                file = new File(file.getParentFile(), file.getName() + ".xls");



            }

            try {
                Exporter exp=new Exporter();
                if(print1==null){
                System.out.println("error");
                }else{
                exp.exortTable(print1, file);
                }

                Desktop.getDesktop().open(file);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("not found");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        }


   }catch(Exception e){
       System.out.println("shit");
   }
    }

}
