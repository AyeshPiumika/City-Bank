/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package city_bank;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Ayesh Piyumika
 */
public class CashierFunctions extends javax.swing.JFrame {

    /**
     * Creates new form CashierFunctions
     */
    public CashierFunctions() {
        initComponents();
        DisplayHolders();
    }
    
    public void DisplayHolders() {
        try {
            Con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/db_city_bank","root","");
                St = Con.createStatement();
                Rs = St.executeQuery("select*from tbl_account");
            lstAccHolders.setModel(DbUtils.resultSetToTableModel(Rs));
        } catch (SQLException e) {
            System.out.println("Error :"+e.getMessage());
        }
    }
    
    public void DisplayTransaction() {
        try {
            Con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/db_city_bank","root","");
                St = Con.createStatement();
                Rs = St.executeQuery("select*from tbl_transaction");
            lstTransaction.setModel(DbUtils.resultSetToTableModel(Rs));
        } catch (SQLException e) {
            System.out.println("Error :"+e.getMessage());
        }
    }
    
    public void Reset()
    {
        txtAccID.setText("");
        txtAccName.setText("");
        txtAccType.setText("");
        txtAccAddress.setText("");
        txtAccConNO.setText("");
        txtAccAmount.setText("");
    }
    
    public void Clear(){
        txtPreviousAmount.setText("");
        txtDepWith.setText("");
    }
    
    public void FilterByID() {
        if(!txtAccID.getText().isEmpty()){
            try {
                Con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/db_city_bank","root","");
                St = Con.createStatement();
                Rs = St.executeQuery("select*from tbl_account where AccID="+txtAccID.getText());
                
                if(Rs.next()==true){
                    txtAccID.setText(Rs.getString("AccID"));
                    txtAccName.setText(Rs.getString("AccName"));
                    txtAccType.setText(Rs.getString("AccType"));
                    txtAccAddress.setText(Rs.getString("AccAddress"));
                    txtAccConNO.setText(Rs.getString("AccContactNO"));
                    txtAccAmount.setText(Rs.getString("AccAmount"));
                    txtPreviousAmount.setText(Rs.getString("AccAmount"));
                }
                
                lstAccHolders.setModel(DbUtils.resultSetToTableModel(Rs));
                
            } catch (SQLException e) {
                System.out.println("Error :"+e.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(this,"Please Provide the Account Holder's ID");
        }
        
    }
    
    public void FilterByName() {
        if(!txtAccName.getText().isEmpty()){
            try {
                Con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/db_city_bank","root","");
                St = Con.createStatement();
                Rs = St.executeQuery("select*from tbl_account where AccName='"+txtAccName.getText()+"'");
                lstAccHolders.setModel(DbUtils.resultSetToTableModel(Rs));
            } catch (SQLException e) {
                System.out.println("Error :"+e.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(this,"Please Provide the Account Holder's Name");
        }
        
    }
    
    public void FilterByType() {
        if(!txtAccType.getText().isEmpty()){
            try {
            Con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/db_city_bank","root","");
                St = Con.createStatement();
                Rs = St.executeQuery("select*from tbl_account where AccType='"+txtAccType.getText()+"'");
            lstAccHolders.setModel(DbUtils.resultSetToTableModel(Rs));
        } catch (SQLException e) {
            System.out.println("Error :"+e.getMessage());
        }
        }else{
            JOptionPane.showMessageDialog(this,"Please Provide the Account Holder's Account Type");
        }
        
    }
    
    public void Update(){
        if(txtAccID.getText().isEmpty()|| txtAccName.getText().isEmpty()||txtAccType.getText().isEmpty()|| txtAccAddress.getText().isEmpty()|| txtAccConNO.getText().isEmpty()|| txtAccAmount.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this,"Missing Information");
        }  else{
            try{
                String Query = "Update tbl_account set 	AccName='"+txtAccName.getText()+"',AccType='"+txtAccType.getText()+"',AccAddress='"+txtAccAddress.getText()+"',AccContactNO="+txtAccConNO.getText()+", AccAmount="+txtAccAmount.getText()+" where AccID="+txtAccID.getText();
                Statement Delete = Con.createStatement();
                Delete.executeUpdate(Query);
                JOptionPane.showMessageDialog(this,"Update Sucessfull");
                DisplayHolders();
            }
            catch (HeadlessException | SQLException e){
                System.out.println("Error :"+e.getMessage());
            }
        }
    }
    
    public void InsertWithdrow(){
        if(txtAccID.getText().isEmpty()|| txtDepWith.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this,"Missing Information");
        }  else{
            try{
                PreparedStatement add = (PreparedStatement) Con.prepareStatement("insert into tbl_transaction values(?,?)");
                add.setInt(1,Integer.valueOf(txtAccID.getText()));
                add.setInt(2,Integer.valueOf("-"+txtDepWith.getText()));
                
                int row = add.executeUpdate();
                JOptionPane.showMessageDialog(this,"Save Succesfull");
//                DisplayTransaction();
                Reset();
            }
            catch (HeadlessException | NumberFormatException | SQLException e){
                System.out.println("Error :"+e.getMessage());
            }
        }
    }
    
    public void InsertDeposit(){
        if(txtAccID.getText().isEmpty()|| txtDepWith.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this,"Missing Information");
        }  else{
            try{
                PreparedStatement add = (PreparedStatement) Con.prepareStatement("insert into tbl_transaction values(?,?)");
                add.setInt(1,Integer.valueOf(txtAccID.getText()));
                add.setInt(2,Integer.valueOf("+"+txtDepWith.getText()));
                
                int row = add.executeUpdate();
                JOptionPane.showMessageDialog(this,"Save Succesfull");
//                DisplayTransaction();
                Reset();
            }
            catch (HeadlessException | NumberFormatException | SQLException e){
                System.out.println("Error :"+e.getMessage());
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

        txtAccID = new javax.swing.JTextField();
        txtAccName = new javax.swing.JTextField();
        txtAccType = new javax.swing.JTextField();
        txtAccAddress = new javax.swing.JTextField();
        txtAccConNO = new javax.swing.JTextField();
        txtAccAmount = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstAccHolders = new javax.swing.JTable();
        btnAccInsert = new javax.swing.JButton();
        btnAccUpdate = new javax.swing.JButton();
        btnAccDelete = new javax.swing.JButton();
        btnFBI = new javax.swing.JButton();
        btnFBT = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtDepWith = new javax.swing.JTextField();
        btnWithdrow = new javax.swing.JButton();
        btnDeposit = new javax.swing.JButton();
        txtPreviousAmount = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnFBN = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstTransaction = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Account Holder's ID");

        jLabel2.setText("Account Holder's Name");

        jLabel3.setText("Account Type");

        jLabel4.setText("Account Holder's Address");

        jLabel5.setText("Account Holder's Contact NO");

        jLabel6.setText("Total Money Amount (Rs)");

        lstAccHolders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        lstAccHolders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstAccHoldersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstAccHolders);

        btnAccInsert.setBackground(new java.awt.Color(102, 255, 255));
        btnAccInsert.setText("INSERT");
        btnAccInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccInsertActionPerformed(evt);
            }
        });

        btnAccUpdate.setBackground(new java.awt.Color(102, 255, 255));
        btnAccUpdate.setText("UPDATE");
        btnAccUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccUpdateActionPerformed(evt);
            }
        });

        btnAccDelete.setBackground(new java.awt.Color(102, 255, 255));
        btnAccDelete.setText("DELETE");
        btnAccDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccDeleteActionPerformed(evt);
            }
        });

        btnFBI.setBackground(new java.awt.Color(153, 153, 255));
        btnFBI.setText("FILTER BY ACCOUNT ID");
        btnFBI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFBIActionPerformed(evt);
            }
        });

        btnFBT.setBackground(new java.awt.Color(153, 153, 255));
        btnFBT.setText("FILTER BY ACCOUNT TYPE");
        btnFBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFBTActionPerformed(evt);
            }
        });

        jLabel8.setText("Withdrowal or Deposit Money Amount (Rs)");

        btnWithdrow.setBackground(new java.awt.Color(255, 102, 102));
        btnWithdrow.setText("WITHDROW");
        btnWithdrow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWithdrowActionPerformed(evt);
            }
        });

        btnDeposit.setBackground(new java.awt.Color(153, 255, 153));
        btnDeposit.setText("DEPOSIT");
        btnDeposit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositActionPerformed(evt);
            }
        });

        txtPreviousAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPreviousAmountActionPerformed(evt);
            }
        });

        jLabel9.setText("Prevoius Money Amount (Rs)");

        btnFBN.setBackground(new java.awt.Color(153, 153, 255));
        btnFBN.setText("FILTER BY NAME");
        btnFBN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFBNActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(102, 255, 255));
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(255, 51, 0));
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        lstTransaction.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {}
            },
            new String [] {

            }
        ));
        lstTransaction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstTransactionMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lstTransaction);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Manage Bank Holders Accounts");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel7)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(404, 404, 404)
                        .addComponent(btnFBT)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6)
                                        .addComponent(btnAccInsert))
                                    .addGap(36, 36, 36)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtAccID)
                                            .addComponent(txtAccName, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtAccType, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtAccConNO, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtAccAddress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtAccAmount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnFBI, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnAccUpdate))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(55, 55, 55)
                                            .addComponent(btnAccDelete)
                                            .addGap(55, 55, 55)
                                            .addComponent(btnClear))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(30, 30, 30)
                                            .addComponent(btnFBN, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9))
                                        .addGap(18, 18, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(btnWithdrow)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnDeposit))
                                            .addComponent(txtPreviousAmount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtDepWith, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(36, 36, 36))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(205, 205, 205))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(393, 393, 393))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAccID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAccName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtPreviousAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAccType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addComponent(txtDepWith, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtAccAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDeposit)
                        .addComponent(btnWithdrow)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAccConNO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAccAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAccInsert)
                            .addComponent(btnAccUpdate)
                            .addComponent(btnAccDelete)
                            .addComponent(btnClear))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFBI)
                            .addComponent(btnFBN)
                            .addComponent(btnFBT))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 24, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    Connection Con=null;
    Statement St = null;
    ResultSet Rs = null;
    private void btnAccInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccInsertActionPerformed
        if(txtAccID.getText().isEmpty()|| txtAccName.getText().isEmpty()||txtAccType.getText().isEmpty()|| txtAccAddress.getText().isEmpty()|| txtAccConNO.getText().isEmpty()|| txtAccAmount.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this,"Missing Information");
        }  else{
            try{
                PreparedStatement add = (PreparedStatement) Con.prepareStatement("insert into tbl_account values(?,?,?,?,?,?)");
                add.setInt(1,Integer.valueOf(txtAccID.getText()));
                add.setString(2,txtAccName.getText());
                add.setString(3,txtAccType.getText());
                add.setString(4,txtAccAddress.getText());
                add.setInt(5,Integer.valueOf(txtAccConNO.getText()));
                add.setInt(6,Integer.valueOf(txtAccAmount.getText()));
                
                int row = add.executeUpdate();
                JOptionPane.showMessageDialog(this,"Save Succesfull");
                DisplayHolders();
                Reset();
            }
            catch (HeadlessException | NumberFormatException | SQLException e){
                System.out.println("Error :"+e.getMessage());
            }
        }
    }//GEN-LAST:event_btnAccInsertActionPerformed

    private void lstAccHoldersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstAccHoldersMouseClicked
        DefaultTableModel model=(DefaultTableModel)lstAccHolders.getModel();
        int MyIndex = lstAccHolders.getSelectedRow();
        txtAccID.setText(model.getValueAt(MyIndex,0).toString());
        txtAccName.setText(model.getValueAt(MyIndex,1).toString());
        txtAccType.setText(model.getValueAt(MyIndex,2).toString());
        txtAccAddress.setText(model.getValueAt(MyIndex,3).toString());
        txtAccConNO.setText(model.getValueAt(MyIndex,4).toString());
        txtAccAmount.setText(model.getValueAt(MyIndex,5).toString());
    }//GEN-LAST:event_lstAccHoldersMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        Reset();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnAccUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccUpdateActionPerformed
        if(txtAccID.getText().isEmpty()|| txtAccName.getText().isEmpty()||txtAccType.getText().isEmpty()|| txtAccAddress.getText().isEmpty()|| txtAccConNO.getText().isEmpty()|| txtAccAmount.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this,"Missing Information");
        }  else{
            try{
                String Query = "Update tbl_account set 	AccName='"+txtAccName.getText()+"',AccType='"+txtAccType.getText()+"',AccAddress='"+txtAccAddress.getText()+"',AccContactNO="+txtAccConNO.getText()+", AccAmount="+txtAccAmount.getText()+" where AccID="+txtAccID.getText();
                Statement Delete = Con.createStatement();
                Delete.executeUpdate(Query);
                JOptionPane.showMessageDialog(this,"Update Sucessfull");
                DisplayHolders();
                Reset();
            }
            catch (HeadlessException | SQLException e){
                System.out.println("Error :"+e.getMessage());
            }
        }
    }//GEN-LAST:event_btnAccUpdateActionPerformed

    private void btnAccDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccDeleteActionPerformed
        if(txtAccID.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this,"Missing Information");
        }  else{
            try{
                String Query = "Delete from tbl_account where AccID="+txtAccID.getText();
                Statement Delete = Con.createStatement();
                Delete.executeUpdate(Query);
                JOptionPane.showMessageDialog(this,"Delete Sucessfull");
                DisplayHolders();
                Reset();
            }
            catch (HeadlessException | SQLException e){
                System.out.println("Error :"+e.getMessage());
                //e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnAccDeleteActionPerformed

    private void btnFBIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFBIActionPerformed
        FilterByID();
    }//GEN-LAST:event_btnFBIActionPerformed

    private void btnFBNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFBNActionPerformed
        FilterByName();
    }//GEN-LAST:event_btnFBNActionPerformed

    private void btnFBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFBTActionPerformed
        FilterByType();
    }//GEN-LAST:event_btnFBTActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnWithdrowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWithdrowActionPerformed
        int FirstNum = Integer.parseInt(txtPreviousAmount.getText());
        int SecNum = Integer.parseInt(txtDepWith.getText());
        int Withdrow = FirstNum-SecNum;
        txtAccAmount.setText(""+Withdrow);
        Update();
        InsertWithdrow();
        DisplayTransaction();
        Clear();
    }//GEN-LAST:event_btnWithdrowActionPerformed

    private void btnDepositActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositActionPerformed
        int FirstNum = Integer.parseInt(txtPreviousAmount.getText());
        int SecNum = Integer.parseInt(txtDepWith.getText());
        int Withdrow = FirstNum+SecNum;
        txtAccAmount.setText(""+Withdrow);
        Update();
        InsertDeposit();
        DisplayTransaction();
        Clear();
    }//GEN-LAST:event_btnDepositActionPerformed

    private void txtPreviousAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPreviousAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPreviousAmountActionPerformed

    private void lstTransactionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstTransactionMouseClicked
        DefaultTableModel model=(DefaultTableModel)lstTransaction.getModel();
        int MyIndex = lstTransaction.getSelectedRow();
        txtAccID.setText(model.getValueAt(MyIndex,0).toString());
        txtDepWith.setText(model.getValueAt(MyIndex,1).toString());
    }//GEN-LAST:event_lstTransactionMouseClicked

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
            java.util.logging.Logger.getLogger(CashierFunctions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CashierFunctions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CashierFunctions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CashierFunctions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CashierFunctions().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccDelete;
    private javax.swing.JButton btnAccInsert;
    private javax.swing.JButton btnAccUpdate;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDeposit;
    private javax.swing.JButton btnFBI;
    private javax.swing.JButton btnFBN;
    private javax.swing.JButton btnFBT;
    private javax.swing.JButton btnWithdrow;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable lstAccHolders;
    private javax.swing.JTable lstTransaction;
    private javax.swing.JTextField txtAccAddress;
    private javax.swing.JTextField txtAccAmount;
    private javax.swing.JTextField txtAccConNO;
    private javax.swing.JTextField txtAccID;
    private javax.swing.JTextField txtAccName;
    private javax.swing.JTextField txtAccType;
    private javax.swing.JTextField txtDepWith;
    private javax.swing.JTextField txtPreviousAmount;
    // End of variables declaration//GEN-END:variables
}
