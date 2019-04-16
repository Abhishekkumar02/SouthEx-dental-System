package hospital;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Abhishek
 */
public class hospial2 extends javax.swing.JFrame {
 public JFrame frame;
ButtonGroup group = new ButtonGroup();
private String gender;
String num;
int sno;
int count=1;
int ridd;
 String ride;
 String rid;
String error;
DefaultTableModel  dm = new DefaultTableModel();
Connection gConn=null;
    /**
     * Creates new form hospial2
     */
    public hospial2() throws SQLException {
       initComponents();
        docterlist();
        treartmentlist();
        Show_Products_In_JTable();
        run();
        groupButton();
    }
  public void run(){
amt3.setVisible(true);
  
  this.setTitle("SouthEx dental");
    this.setIconImage(Toolkit.getDefaultToolkit().getImage("icon.jpg"));
}
  
    public void groupButton(){
        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);
        bg.add(other);
        male.setSelected(true);
    }
    public void clear(){
          txt_id.setText("");
                txt_name.setText("");
                txt_add.setText("");
                number.setText("");
               amt2.setText("");
               amt3.setText("");
               age.setText("");
txt_docter2.setSelectedIndex(0);
pm2.setSelectedIndex(0);
male.setSelected(false);
    female.setSelected(false);
    other.setSelected(false);
    dateofp.setDate(null); 
  
    }
      public void clear1(){
         amt3.setText("");
               age.setText("");
                txt_name.setText("");
                txt_add.setText("");
                number.setText("");
               amt2.setText("");
            
txt_docter2.setSelectedIndex(0);
pm2.setSelectedIndex(0);
male.setSelected(false);
    female.setSelected(false);
    other.setSelected(false);
    dateofp.setDate(null); 
    
  
    }
    
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
    
public void docterlist(){
     Statement stat=null;
        ResultSet rs=null;
         Connection con = getConnection();
    try{
       
       
        stat = con.createStatement();
                    String selectquery = "select name from docterinfo order by id desc";
         rs = stat.executeQuery(selectquery);
        while(rs.next()){
            txt_docter2.addItem(rs.getString("name"));
        }
    }catch(Exception e){
         JOptionPane.showMessageDialog(null, e.getMessage());
    }finally{
        try{
            stat.close();
            rs.close();
            con.close();
        }catch(Exception e){
            
        }
    }
}
public void treartmentlist(){
     Statement stat=null;
        ResultSet rs=null;
         Connection con = getConnection();
    try{
       
       
        stat = con.createStatement();
                    String selectquery = "select* from treatment order by id desc";
         rs = stat.executeQuery(selectquery);
        while(rs.next()){
            act.addItem(rs.getString("treatment"));
        }
    }catch(Exception e){
         JOptionPane.showMessageDialog(null, e.getMessage());
    }finally{
        try{
            stat.close();
            rs.close();
            con.close();
        }catch(Exception e){
            
        }
    }
}

 public String getSelectedOption() {
        Enumeration<AbstractButton> radioButtons = group.getElements();
        while (radioButtons.hasMoreElements()) {
            AbstractButton currentRadioButton = radioButtons.nextElement();
            if (currentRadioButton.isSelected()) {
                return currentRadioButton.getText();
            }
        }
        return null;
    }

 public ArrayList<patient> getProductList()
    {
            ArrayList<patient> patientList  = new ArrayList<patient>();
            Connection con = getConnection();
            String query = "SELECT * FROM patients order by id desc";
            sno=0;
            Statement st = null;
            ResultSet rs = null;
            
        try {
            
            st = con.createStatement();
            rs = st.executeQuery(query);
            patient patient1;
            
            while(rs.next())
            {
                
                patient1 = new patient(rs.getString("pid"),rs.getString("name"),rs.getString("gender"),rs.getString("address"),rs.getString("phone"),rs.getString("dob"),rs.getString("docter"),rs.getFloat("Amount"),rs.getString("PaymentMode"),rs.getString("pDate"),rs.getString("remark"),rs.getString("treatment"),rs.getString("dop"),rs.getString("print"));
                patientList.add(patient1);
                 sno++;
            }
            
           
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

 public void Show_Products_In_JTable() throws SQLException
    {
        ArrayList<patient> list = getProductList();
                 DefaultTableModel model = (DefaultTableModel)print.getModel();
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
            row1[2]="No of p: "+nop();
            row1[3]="";
            row1[4]="";
            row1[5]="";
            row1[6]="";
            row1[7] = "";
            row1[8]="Total: "+total();
      row1[9]="";
       row1[10]="";
       row1[11 ]="";
                    }
     model.addRow(row1);

    }

 public ArrayList<docter> getDocterList()
    {
            ArrayList<docter> docterList  = new ArrayList<docter>();
            Connection con = getConnection();
            String query = "SELECT name FROM docterinfo";
            sno=0;
            Statement st = null;
            ResultSet rs = null;
            
        try {
            
            st = con.createStatement();
            rs = st.executeQuery(query);
            docter docter1;
            
            while(rs.next())
            {
                
                docter1 = new docter(rs.getString("name"),rs.getString("docreg"),rs.getString("specialization"),rs.getString("docdegree"));
                docterList.add(docter1);
                 sno++;
            }
           
           
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
            
        return docterList; 
                
    }
public void  retrieve() throws SQLException{
    DefaultComboBoxModel dcm = new hospial2().dd();
    txt_docter2.setModel(dcm);
}
public void  treatment() throws SQLException{
    DefaultComboBoxModel dcm = new hospial2().tt();
    act.setModel(dcm);
}
 public DefaultComboBoxModel tt()
{
    DefaultComboBoxModel dmmm = new DefaultComboBoxModel();
    String query = "SELECT * FROM treatment";
    Statement st = null;
    Connection con = getConnection();
            ResultSet rs = null;
            
        try {
            
            st = con.createStatement();
            rs = st.executeQuery(query);
            String treatment;
            
            while(rs.next())
            {
                
               treatment = rs.getString(2);
               dmmm.addElement(treatment);
            }
            return dmmm;
           
           
        } catch (SQLException ex) {
            Logger.getLogger(hospial2.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            try{
                rs.close();
                st.close();
                con.close();
            }catch(Exception e){
                
            }
        }
}
 public DefaultComboBoxModel dd()
{
    DefaultComboBoxModel dmm = new DefaultComboBoxModel();
    String query = "SELECT name FROM docterinfo";
    Statement st = null;
    Connection con = getConnection();
            ResultSet rs = null;
            
        try {
            
            st = con.createStatement();
            rs = st.executeQuery(query);
            String docter1;
            
            while(rs.next())
            {
                
               docter1 = rs.getString(1);
               dmm.addElement(docter1);
            }
            return dmm;
           
           
        } catch (SQLException ex) {
            Logger.getLogger(hospial2.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            try{
                rs.close();
                st.close();
                con.close();
            }catch(Exception e){
                
            }
        }
}
  public void ShowItem(int index) throws ParseException
    {
            txt_id.setText((getProductList().get(index).getId()));
            txt_name.setText(getProductList().get(index).getName());
            txt_add.setText(getProductList().get(index).getAdd());
            txt_docter2.setSelectedItem(getProductList().get(index).getDocter());
            pm2.setSelectedItem(getProductList().get(index).getMode());
            String genderEdit=getProductList().get(index).getGender();

                    if(genderEdit.equals("Male"))
                    {
                         male.setSelected(true);
                          female.setSelected(false);
                          other.setSelected(false);
                    }
                    else if(genderEdit.equals("Female"))
                    {
                        male.setSelected(false);
                          female.setSelected(true);
                          other.setSelected(false);
                    }
                    else if(genderEdit.equals("Other"))
                    {
                         male.setSelected(false);
                          female.setSelected(false);
                          other.setSelected(true);
                    }
                    else
                    {
                    JOptionPane.showMessageDialog(null, "error !");
                    }
        amt2.setText(Float.toString(getProductList().get(index).getAmt()));
                   
            number.setText(getProductList().get(index).getPhone());
            age.setText(getProductList().get(index).getDob());
            amt3.setText(getProductList().get(index).getRemark());
              try {
           Date addDatee = null;
            addDatee = new SimpleDateFormat("dd-MMM-yyyy").parse((String)getProductList().get(index).getDop());
            dateofp.setDate(addDatee);
        } catch (ParseException ex) {
            Logger.getLogger(hospial2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }/*
  public boolean address(){
       String p = "^[a-zA-Z 0-9]{0,50}$";
        Pattern patt = Pattern.compile(p);
        Matcher match = patt.matcher(txt_add.getText());
        if(!match.matches()){
            error="invalid Address";
            return false;
        }else{
            return true;
        } 
  }*/
  public boolean name(){
         String p = "^[a-zA-Z ]{4,50}$";
        Pattern patt = Pattern.compile(p);
        Matcher match = patt.matcher(txt_name.getText());
        if(!match.matches()){
            error="invalid Name";
            return false;
        }else{
           return true;
        }
  }/*
  public boolean phone(){
        String p = "^[0-9]{0,10}$";
        Pattern patt = Pattern.compile(p);
        Matcher match = patt.matcher(number.getText());
        if(!match.matches()){
            error="invalid Phone";
            return false;
        }else{
           return true;
        }
  }*/
  public boolean amt(){
         String p = "^[0-9.]{0,10}$";
        Pattern patt = Pattern.compile(p);
        Matcher match = patt.matcher(amt2.getText());
        if(!match.matches()){
            error="invalid Amount";
           return false;
        }else{
           return true;
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

        jLabel1 = new javax.swing.JLabel();
        totalamt = new javax.swing.JTextField();
        txt_docter = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        totalamt1 = new javax.swing.JTextField();
        amt = new javax.swing.JTextField();
        pm = new javax.swing.JComboBox<>();
        amtv = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_dob = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        totalamt2 = new javax.swing.JTextField();
        txt_docter1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        totalamt3 = new javax.swing.JTextField();
        amt1 = new javax.swing.JTextField();
        pm1 = new javax.swing.JComboBox<>();
        amtv1 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_dob1 = new com.toedter.calendar.JDateChooser();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        print = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        up = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txt_docter2 = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        amt2 = new javax.swing.JTextField();
        pm2 = new javax.swing.JComboBox<>();
        jButton11 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        namev = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        male = new javax.swing.JRadioButton();
        female = new javax.swing.JRadioButton();
        other = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_name = new javax.swing.JTextField();
        txt_add = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        number = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        amt3 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        age = new javax.swing.JTextField();
        up1 = new javax.swing.JButton();
        l = new javax.swing.JLabel();
        act = new javax.swing.JComboBox<>();
        act1 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        dateofp = new com.toedter.calendar.JDateChooser();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Total Amount:");

        totalamt.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("No of Patient:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setText("Amount:");

        totalamt1.setEditable(false);

        pm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "card", "NEFT", "Paytm", "Other" }));

        amtv.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        amtv.setForeground(new java.awt.Color(255, 0, 51));

        jButton1.setText("Refresh");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Doctor:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("DOB:");

        txt_dob.setDateFormatString("yyyy-MM-dd");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Payment Mode:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Total Amount:");

        totalamt2.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("No of Patient:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setText("Amount:");

        totalamt3.setEditable(false);

        pm1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "card", "NEFT", "Paytm", "Other" }));

        amtv1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        amtv1.setForeground(new java.awt.Color(255, 0, 51));

        jButton10.setText("Refresh");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("Doctor:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("DOB:");

        txt_dob1.setDateFormatString("yyyy-MM-dd");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setText("Payment Mode:");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 0, 0));
        setForeground(new java.awt.Color(0, 204, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        print.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "P.id", "Date", "Name", "Age", "Gender", "Address", "Doctor", "Phone No", "Amount", "DOP", "Paymant mode", "Remark"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false, false, true, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        print.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                printMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(print);

        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton2KeyReleased(evt);
            }
        });

        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Doctor");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Reset");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Delete Doctor");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton8.setText("Exit");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        up.setText("Update");
        up.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upActionPerformed(evt);
            }
        });

        jButton9.setText("Export in xls");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel17.setText(" SouthEx Dental");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        txt_docter2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_docter2ActionPerformed(evt);
            }
        });
        txt_docter2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_docter2KeyPressed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setText("Amount:");

        amt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amt2ActionPerformed(evt);
            }
        });
        amt2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                amt2KeyReleased(evt);
            }
        });

        pm2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Card", "NEFT", "Paytm", "Other" }));
        pm2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pm2ActionPerformed(evt);
            }
        });

        jButton11.setText("Refresh");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setText("Doctor:");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel24.setText("Payment Mode:");

        namev.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        namev.setForeground(new java.awt.Color(255, 0, 51));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("Gender:");

        male.setText("Male");
        male.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        male.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maleActionPerformed(evt);
            }
        });

        female.setText("Female");

        other.setText("Other");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Patient Name:");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setText("Patient ID:");

        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });
        txt_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_idKeyReleased(evt);
            }
        });

        txt_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nameActionPerformed(evt);
            }
        });
        txt_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_nameKeyReleased(evt);
            }
        });

        txt_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_addActionPerformed(evt);
            }
        });
        txt_add.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_addKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Patient Address:");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel26.setText("Phone:");

        number.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberActionPerformed(evt);
            }
        });
        number.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                numberKeyReleased(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel27.setText("Remark:");

        amt3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amt3ActionPerformed(evt);
            }
        });
        amt3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                amt3KeyReleased(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel28.setText("Age:");

        age.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ageActionPerformed(evt);
            }
        });
        age.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ageKeyReleased(evt);
            }
        });

        up1.setText("Print");
        up1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                up1ActionPerformed(evt);
            }
        });

        l.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        l.setText("Treatment:");

        act.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actActionPerformed(evt);
            }
        });
        act.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                actKeyPressed(evt);
            }
        });

        act1.setText("Add Tment");
        act1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                act1ActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel29.setText("Date Of Payment:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1120, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton9)
                                .addGap(18, 18, 18)
                                .addComponent(up, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(up1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(act1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel13))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_id)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(male)
                                        .addGap(18, 18, 18)
                                        .addComponent(female)
                                        .addGap(18, 18, 18)
                                        .addComponent(other))
                                    .addComponent(namev, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_name, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel26)
                                    .addComponent(l))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_add)
                                    .addComponent(number)
                                    .addComponent(act, javax.swing.GroupLayout.Alignment.LEADING, 0, 201, Short.MAX_VALUE)))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(133, 133, 133)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel24)
                            .addComponent(jLabel23)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_docter2, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(amt2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pm2, javax.swing.GroupLayout.Alignment.TRAILING, 0, 220, Short.MAX_VALUE)
                            .addComponent(amt3)
                            .addComponent(age, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dateofp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(42, 42, 42)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(pm2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(amt3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(amt2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addGap(6, 6, 6)
                        .addComponent(namev, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(male)
                            .addComponent(female)
                            .addComponent(other))))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_add, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(dateofp, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel26)
                            .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_docter2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)
                            .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(act, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l))
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(age, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addGap(46, 46, 46)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(up, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(up1, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(act1, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void printMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printMouseClicked
        int index = print.getSelectedRow();
        namev.setText("");
       
        try {
            ShowItem(index);        // TODO add your handling code here:
        } catch (ParseException ex) {
            Logger.getLogger(hospial2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_printMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        if(txt_name.getText().equals("") ){
            JOptionPane.showMessageDialog(null, "Name Empty");
        
      
        }else if(amt2.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Amount Empty");
        }
       

        else{
            
            if(name() && amt() )
            {
               
                   
                    Connection con = getConnection();
                    PreparedStatement ps=null;
                    try {
                       
                        ps = con.prepareStatement("INSERT INTO patients(pid,name,gender,address,phone,dob,docter,Amount,PaymentMode,pDate,recipt,remark,treatment,dop,print)"
                                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
                        
                        ps.setString(1,insertSerial());
                        //txt_id.setText(insertSerial());
                        ps.setString(2, txt_name.getText());
                        if(male.isSelected()){
                            gender="Male";
                            
                        }
                        if(female.isSelected()){
                            gender="Female";
                        }
                        if(other.isSelected()){
                            gender="Other";
                        }
                        ps.setString(3, gender);
                        ps.setString(4, txt_add.getText());
                        ps.setString(5, number.getText());
                       ps.setString(6, age.getText());
                     
                        String docter;
                        docter = txt_docter2.getSelectedItem().toString();
                        ps.setString(7, docter);
                        num=amt2.getText();
                        
                        ps.setString(8,num);
                        String pmode = pm2.getSelectedItem().toString();
                        ps.setString(9, pmode);
                        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                        Date date1 = new Date();
                        String currentDate = dateFormat.format(date1);
                        ps.setString(10, currentDate);
                        ps.setString(11, rop());
                        ps.setString(12,amt3.getText());
                         String treatment;
                        treatment = act.getSelectedItem().toString();
                        ps.setString(13, treatment);
                        
                        SimpleDateFormat dateF = new SimpleDateFormat("dd-MMM-yyyy");
                String addDate = dateF.format(dateofp.getDate());
                ps.setString(14, addDate);
                 ps.setString(15, insertSerialprint());
                        ps.executeUpdate();
                        
                        Show_Products_In_JTable();
                        
                        JOptionPane.showMessageDialog(null, "Entry add Succssefully!");
                 /*       
              String docter1;
                        docter1 = txt_docter2.getSelectedItem().toString();
                String id=txt_id.getText();
               rid=rop();
                      recipt bil = new recipt(docter1,id,rid);
                bil.setVisible(true);
                bil.setSize(420, 430);
                bil.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
                PrinterJob job = PrinterJob.getPrinterJob();
                
                job.setPrintable(new Printable() {
                    public int print(Graphics pg, PageFormat pf, int pageNum) {
                        
                        if (pageNum > 0) {
                            return Printable.NO_SUCH_PAGE;
                        }
                        Graphics2D g2 = (Graphics2D) pg;
                        g2.translate(pf.getImageableX(), pf.getImageableY());
                        bil.paint(g2);
                        return Printable.PAGE_EXISTS;
                    }
                });
                boolean ok = job.printDialog();
                if (ok) {
                    try {
                        job.print();
                        
                    } catch (PrinterException ex) {
                        
                    }
                }
                     */   
                        clear();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                    finally{
                        try{
                            ps.close();
                            con.close();
                        }catch(Exception e){
                            
                        }
                    }
               

            }else{
                JOptionPane.showMessageDialog(null, error);
            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       
         if (JOptionPane.showConfirmDialog(frame, 
            "Do you want to Delete", "Delete?", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
     
        
        if(!txt_id.getText().equals(""))
        {Connection con = getConnection();
            PreparedStatement ps=null;
            try {

                ps = con.prepareStatement("DELETE FROM patients WHERE pid = ?");
                String id =(txt_id.getText());
                ps.setString(1, id);
                ps.executeUpdate();
                clear();
                Show_Products_In_JTable();
                JOptionPane.showMessageDialog(null, " Deleted");

            } catch (SQLException ex) {
                Logger.getLogger(hospial2.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, " Not Deleted");
            }finally{
                try{
                    ps.close();
                    con.close();
                }catch(Exception e){

                }
            }

        }else{
            JOptionPane.showMessageDialog(null, "Patients Not Deleted : No Id To Delete");
        }
         }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        docterinfo doc = new docterinfo();
        doc.setVisible(true);
        doc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        txt_id.setText("");
        txt_name.setText("");
        txt_add.setText("");
        number.setText("");
        amt2.setText("");
 amt3.setText("");
  age.setText("");  
    
        txt_docter2.setSelectedIndex(0);
        pm2.setSelectedIndex(0);
        male.setSelected(false);
        female.setSelected(false);
        other.setSelected(false);
        dateofp.setDate(null);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        deldocter doo = new deldocter();
        doo.setVisible(true);
        doo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
  
              File   file = new File("data.xls");
                    try {
                        Exporter exp=new Exporter();
                        exp.exortTable(print, file);

                        
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("not found");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            System.exit(0);
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        showdata p = new showdata();
        p.setVisible(true);
        p.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }//GEN-LAST:event_jButton9ActionPerformed

    private void txt_docter2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_docter2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_docter2ActionPerformed

    private void txt_docter2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_docter2KeyPressed
        try {
            retrieve();        // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(hospial2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txt_docter2KeyPressed

    private void amt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amt2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_amt2ActionPerformed

    private void amt2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amt2KeyReleased
        String p = "^[0-9]{0,10}$";
        Pattern patt = Pattern.compile(p);
        Matcher match = patt.matcher(amt2.getText());
        if(!match.matches()){
            amtv.setText("Amount is Invalid");
        }else{
            amtv.setText("");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_amt2KeyReleased

    private void pm2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pm2ActionPerformed
       
    }//GEN-LAST:event_pm2ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        try {
            retrieve();
            treatment();
        } catch (SQLException ex) {
            Logger.getLogger(hospial2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void maleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maleActionPerformed

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void txt_idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idKeyReleased
        PreparedStatement stat =null;
        ResultSet rs=null;
        Connection con = getConnection();
        try{

            String selectquery = "select * from patients where pid=?";

            stat = con.prepareStatement(selectquery);
            String id=txt_id.getText();
            stat.setString(1,id);
            rs=stat.executeQuery();
            while(rs.next()){

                txt_name.setText(rs.getString("name"));
                txt_add.setText(rs.getString("address"));
                number.setText(rs.getString("phone"));

                amt2.setText(rs.getString("Amount"));
                String genderEdit=(rs.getString("gender"));

                if(genderEdit.equals("Male"))
                {
                    male.setSelected(true);
                    female.setSelected(false);
                    other.setSelected(false);
                }
                else if(genderEdit.equals("Female"))
                {
                    male.setSelected(false);
                    female.setSelected(true);
                    other.setSelected(false);
                }
                else if(genderEdit.equals("Other"))
                {
                    male.setSelected(false);
                    female.setSelected(false);
                    other.setSelected(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "error !");
                }
                Date addDate2 = null;

                age.setText(rs.getString("dob"));
                amt3.setText(rs.getString("remark"));
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());

        }finally{
            try{
                rs.close();
                stat.close();
                con.close();
            }catch(Exception e){

            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idKeyReleased

    private void txt_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nameActionPerformed

    }//GEN-LAST:event_txt_nameActionPerformed

    private void txt_nameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nameKeyReleased
        String p = "^[a-zA-Z ]{4,50}$";
        Pattern patt = Pattern.compile(p);
        Matcher match = patt.matcher(txt_name.getText());
        if(!match.matches()){
            namev.setText("Name is Invalid");
        }else{
            namev.setText("");
        }
    }//GEN-LAST:event_txt_nameKeyReleased

    private void txt_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_addActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_addActionPerformed

    private void txt_addKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_addKeyReleased
             // TODO add your handling code here:
    }//GEN-LAST:event_txt_addKeyReleased

    private void numberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberActionPerformed

    }//GEN-LAST:event_numberActionPerformed

    private void numberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numberKeyReleased
    
    }//GEN-LAST:event_numberKeyReleased

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (JOptionPane.showConfirmDialog(frame, 
            "Are you sure you want to close this window?", "Close Window?", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
     
        }    
               File   file = new File("data.xls");
                    try {
                        Exporter exp=new Exporter();
                        exp.exortTable(print, file);

                        
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("not found");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            System.exit(0);
                

    }//GEN-LAST:event_formWindowClosing

    private void jButton2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2KeyReleased

    private void amt3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amt3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_amt3ActionPerformed

    private void amt3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amt3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_amt3KeyReleased

    private void ageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ageActionPerformed

    private void ageKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ageKeyReleased
       
    }//GEN-LAST:event_ageKeyReleased

    private void up1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_up1ActionPerformed
Connection con = getConnection();
ResultSet rs=null;
String  s1="";
                    PreparedStatement stat=null;
                    try {
 
                        String selectquery = "select * from patients where pid=?";

            stat = con.prepareStatement(selectquery);
            String id=txt_id.getText();
            stat.setString(1,id);
            rs=stat.executeQuery();
            while(rs.next()){

             s1 =  rs.getString("print");
   }                    
        String docter;
                        docter = txt_docter2.getSelectedItem().toString();
                    recipt bil;
        bil = new recipt(docter,txt_id.getText(),s1);
        
                    bil.setVisible(true);
                    bil.setSize(420, 430);
                    bil.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    

                    PrinterJob job = PrinterJob.getPrinterJob();

                    job.setPrintable(new Printable() {
                        public int print(Graphics pg, PageFormat pf, int pageNum) {

                            if (pageNum > 0) {
                                return Printable.NO_SUCH_PAGE;
                            }
                            Graphics2D g2 = (Graphics2D) pg;
                            g2.translate(pf.getImageableX(), pf.getImageableY());
                            bil.paint(g2);
                            return Printable.PAGE_EXISTS;
                        }
                    });
                    
                    boolean ok = job.printDialog();
                    if (ok) {
                        try {
                            job.print();

                        } catch (PrinterException ex) {

                        }
                    }
                    } catch (SQLException ex) {
        Logger.getLogger(hospial2.class.getName()).log(Level.SEVERE, null, ex);
    }finally{
            try{
                rs.close();
                stat.close();
                con.close();
            }catch(Exception e){

            }
                    }
                    clear();
    }//GEN-LAST:event_up1ActionPerformed

    private void actActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_actActionPerformed

    private void actKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_actKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_actKeyPressed

    private void act1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_act1ActionPerformed
        advancedocter doo = new advancedocter();
        doo.setVisible(true);
        doo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_act1ActionPerformed

    private void upActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upActionPerformed
        if(txt_name.getText().equals("") ){
            JOptionPane.showMessageDialog(null, "Name Empty");
        }
        else if(amt2.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Amount Empty");
        }

        else{
            if(name() && amt() )
            {

                PreparedStatement ps =null;
                ResultSet rs=null;
                Connection con = getConnection();
                try {
String UpdateQuery = "UPDATE patients SET pid=?, name=?,gender=?,address=?,phone=?,dob=?,docter=?,Amount=?,PaymentMode=?,pDate=?,recipt=?,remark=?,treatment=?,dop=?,print=? WHERE pid = ?";
                    ps = con.prepareStatement(UpdateQuery);
                  ps.setString(1,txt_id.getText());
                    ps.setString(2, txt_name.getText());
                    if(male.isSelected()){
                        gender="Male";

                    }
                    if(female.isSelected()){
                        gender="Female";
                    }
                    if(other.isSelected()){
                        gender="Other";
                    }
                    ps.setString(3, gender);
                    ps.setString(4, txt_add.getText());
                    ps.setString(5, number.getText());

                    ps.setString(6, age.getText());
                    String docter;
                    docter = txt_docter2.getSelectedItem().toString();
                    ps.setString(7, docter);
                    num=amt2.getText();

                    ps.setString(8,num);
                    String pmode = pm2.getSelectedItem().toString();
                    ps.setString(9, pmode);
                    DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                    Date date1 = new Date();
                    String currentDate = dateFormat.format(date1);
                    ps.setString(10, currentDate);
                    ps.setString(11, rop());
                    ps.setString(12, amt3.getText());
                    String treatment;
                        treatment = act.getSelectedItem().toString();
                        ps.setString(13, treatment);
SimpleDateFormat dateF = new SimpleDateFormat("dd-MMM-yyyy");
                String addDate = dateF.format(dateofp.getDate());
                ps.setString(14, addDate);
                ps.setString(15, insertSerialprint());
                   ps.setString(16,txt_id.getText());
                    ps.executeUpdate();
                    Show_Products_In_JTable();
            

                    Show_Products_In_JTable();
                    JOptionPane.showMessageDialog(null, "Update Succssefully!");

                } catch (SQLException ex) {
                    Logger.getLogger(hospial2.class.getName()).log(Level.SEVERE, null, ex);
                }}
                else{
                    JOptionPane.showMessageDialog(null, error);
                }}
    }//GEN-LAST:event_upActionPerformed

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
            java.util.logging.Logger.getLogger(hospial2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(hospial2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(hospial2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(hospial2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new hospial2().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(hospial2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> act;
    private javax.swing.JButton act1;
    private javax.swing.JTextField age;
    private javax.swing.JTextField amt;
    private javax.swing.JTextField amt1;
    private javax.swing.JTextField amt2;
    private javax.swing.JTextField amt3;
    private javax.swing.JLabel amtv;
    private javax.swing.JLabel amtv1;
    private com.toedter.calendar.JDateChooser dateofp;
    private javax.swing.JRadioButton female;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l;
    private javax.swing.JRadioButton male;
    private javax.swing.JLabel namev;
    private javax.swing.JTextField number;
    private javax.swing.JRadioButton other;
    private javax.swing.JComboBox<String> pm;
    private javax.swing.JComboBox<String> pm1;
    private javax.swing.JComboBox<String> pm2;
    public javax.swing.JTable print;
    private javax.swing.JTextField totalamt;
    private javax.swing.JTextField totalamt1;
    private javax.swing.JTextField totalamt2;
    private javax.swing.JTextField totalamt3;
    private javax.swing.JTextField txt_add;
    private com.toedter.calendar.JDateChooser txt_dob;
    private com.toedter.calendar.JDateChooser txt_dob1;
    private javax.swing.JComboBox<String> txt_docter;
    private javax.swing.JComboBox<String> txt_docter1;
    private javax.swing.JComboBox<String> txt_docter2;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JButton up;
    private javax.swing.JButton up1;
    // End of variables declaration//GEN-END:variables
int gatV;
    private String insertSerial() throws SQLException {
        gen("select count(id)+1 from patients");
       String serial ="P"+gatV;
       System.out.println(serial);
       return serial;
       
    }

    private void gen(String ps) throws SQLException{
        Statement stat=null;
        ResultSet rs=null;
         Connection con = getConnection();
         try{
       stat = con.createStatement();
        rs = stat.executeQuery(ps);
        if(rs.next()){
            gatV=Integer.parseInt(rs.getString(1));
    }
    }catch(Exception e){
        
    }finally{
        try{
           stat.close();
           rs.close();
            con.close();
        }catch(Exception e){
            
        }
    }
    }

    private void expot() {
             try {
                        Exporter exp=new Exporter();
                        exp.exortTable(print, new File("results.xls"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("not found");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
    }

    String gatValue1;
    private String total() throws SQLException {
        generate("select sum(Amount) from patients");
       String Amount =gatValue1;
       return Amount;
       
    }

    private void generate(String ps) throws SQLException{
        Statement stat=null;
        ResultSet rs=null;
         Connection con = getConnection();
         try{
       stat = con.createStatement();
        rs = stat.executeQuery(ps);
        if(rs.next()){
            gatValue1=(rs.getString(1));
    }
    }catch(Exception e){
        
    }finally{
        try{
           stat.close();
           rs.close();
            con.close();
        }catch(Exception e){
            
        }
    }
    }
    String gatValue3;
    private String nop() throws SQLException {
       genop("select count(id) from patients");
       String serial =gatValue3;
       System.out.println(serial);
       return serial;
       
    }

    private void genop(String ps) throws SQLException{
        Statement stat=null;
        ResultSet rs=null;
         Connection con = getConnection();
         try{
       stat = con.createStatement();
        rs = stat.executeQuery(ps);
        if(rs.next()){
            gatValue3=(rs.getString(1));
    }
    }catch(Exception e){
        
    }finally{
        try{
           stat.close();
           rs.close();
            con.close();
        }catch(Exception e){
            
        }
    }
    }

    
   String gatValue4;
    private String rop() throws SQLException {
       genrop("select count(recipt)+1 from patients");
       String serial =gatValue4;
       System.out.println(serial);
       return serial;
       
    }
    private void genrop(String ps) throws SQLException{
        Statement stat=null;
        ResultSet rs=null;
         Connection con = getConnection();
         try{
       stat = con.createStatement();
        rs = stat.executeQuery(ps);
        if(rs.next()){
            gatValue4=(rs.getString(1));
    }
    }catch(Exception e){
        
    }finally{
        try{
           stat.close();
           rs.close();
            con.close();
        }catch(Exception e){
            
        }
    }
    }
int gatValue10;
    private String insertSerialprint() throws SQLException {
        genprint("select max(print)+1 from patients");
       String serial =""+gatValue10;
       System.out.println(serial);
       return serial;
       
    }

    private void genprint(String ps) throws SQLException{
        Statement stat=null;
        ResultSet rs=null;
         Connection con = getConnection();
         try{
       stat = con.createStatement();
        rs = stat.executeQuery(ps);
        if(rs.next()){
            gatValue10=Integer.parseInt(rs.getString(1));
    }
    }catch(Exception e){
        
    }finally{
        try{
           stat.close();
           rs.close();
            con.close();
        }catch(Exception e){
            
        }
    }
    }
}


