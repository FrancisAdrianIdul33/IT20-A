package contact.management.system;

import java.util.Collections;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class ContactManagementSystem extends javax.swing.JFrame {

    // linkedList to store contacts
    LinkedList<Contact> contactList = new LinkedList<>();
    LinkedList<Contact> deletedContacts = new LinkedList<>();
    DefaultTableModel model;

    public ContactManagementSystem() {
        initComponents();

        // Initialize the table model
        model = (DefaultTableModel) contactdatatable.getModel();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Title = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        update = new javax.swing.JButton();
        searchbutton = new javax.swing.JButton();
        retrieve = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        contactdatatable = new javax.swing.JTable();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Title.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        Title.setForeground(new java.awt.Color(255, 255, 255));
        Title.setText("Contact Management System");
        getContentPane().add(Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 500, -1));

        add.setText("ADD");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        getContentPane().add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 80, -1));

        delete.setText("DELETE");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        getContentPane().add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, -1, -1));

        update.setText("UPDATE");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        getContentPane().add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, -1, -1));

        searchbutton.setText("SEARCH");
        searchbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbuttonActionPerformed(evt);
            }
        });
        getContentPane().add(searchbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, -1, -1));

        retrieve.setText("RETRIEVE");
        retrieve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retrieveActionPerformed(evt);
            }
        });
        getContentPane().add(retrieve, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 90, -1));

        contactdatatable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "Number", "Email Address"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        contactdatatable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contactdatatableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(contactdatatable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 560, 240));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/back.png"))); // NOI18N
        background.setToolTipText("");
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 420));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:
        String name = JOptionPane.showInputDialog(this, "Enter Name:");
        String num = JOptionPane.showInputDialog(this, "Enter Phone Number:");
        String email = JOptionPane.showInputDialog(this, "Enter Email Address:");

        if (name.isEmpty() || num.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        try {
            Long.parseLong(num);
            Contact contact = new Contact(name, num, email);
            addContact(contact);
            updateTable();
            JOptionPane.showMessageDialog(this, "Contact added successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid phone number format.");
        }

    }//GEN-LAST:event_addActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        // TODO add your handling code here:

        if (contactList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Table is empty.");
            return;
        }

        int selectedRow = contactdatatable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a contact to update.");
            return;
        }

        String[] newData = {
            JOptionPane.showInputDialog(this, "Update Name:", contactdatatable.getValueAt(selectedRow, 0)),
            JOptionPane.showInputDialog(this, "Update Number:", contactdatatable.getValueAt(selectedRow, 1)),
            JOptionPane.showInputDialog(this, "Update Email:", contactdatatable.getValueAt(selectedRow, 2))
        };

        updateContact(selectedRow, newData);
        updateTable();
        JOptionPane.showMessageDialog(this, "Contact updated successfully");

    }//GEN-LAST:event_updateActionPerformed

    private void contactdatatableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contactdatatableMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_contactdatatableMouseClicked

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        if (contactList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Table is empty.");
            return;
        }

        int selectedRow = contactdatatable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a contact to delete.");
            return;
        }

        deleteContact(selectedRow);
        updateTable();
        JOptionPane.showMessageDialog(this, "Contact deleted successfully.");

    }//GEN-LAST:event_deleteActionPerformed

    private void retrieveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retrieveActionPerformed
        // TODO add your handling code here:
        if (deletedContacts.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No deleted contacts to retrieve.");
            return;
        }

        String selectedContact = (String) JOptionPane.showInputDialog(
                this,
                "Select a contact to retrieve:",
                "Retrieve Contact",
                JOptionPane.PLAIN_MESSAGE,
                null,
                deletedContacts.stream().map(Contact::getName).toArray(String[]::new),
                deletedContacts.get(0).getName()
        );

        if (selectedContact != null) {
            retrieveContact(selectedContact);
        }

    }//GEN-LAST:event_retrieveActionPerformed

    private void searchbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbuttonActionPerformed
        // TODO add your handling code here:
        if (contactList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Table is empty.");
            return;
        }

        String searchTerm = JOptionPane.showInputDialog("Enter search term:").toLowerCase();
        searchContact(searchTerm);
    }//GEN-LAST:event_searchbuttonActionPerformed

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
            java.util.logging.Logger.getLogger(ContactManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ContactManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ContactManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ContactManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        try {
            for (var info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ContactManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ContactManagementSystem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Title;
    public javax.swing.JButton add;
    public javax.swing.JLabel background;
    public javax.swing.JTable contactdatatable;
    public javax.swing.JButton delete;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JButton retrieve;
    public javax.swing.JButton searchbutton;
    public javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables

    // Method to update the table with data from contactList
    private void updateTable() {
        model.setRowCount(0);
        contactList.forEach(contact -> model.addRow(new Object[]{contact.getName(), contact.getNumber(), contact.getEmail()}));
    }

    // Method to add a contact to the list
    private void addContact(Contact contact) {
        contactList.add(contact);
        Collections.sort(contactList, (c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
    }

    // Method to update a contact in the list
    private void updateContact(int selectedRow, String[] newData) {
        contactList.set(selectedRow, new Contact(newData[0], newData[1], newData[2]));
        Collections.sort(contactList, (c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
    }

    // Method to delete a contact from the list
    private void deleteContact(int selectedRow) {
        deletedContacts.add(contactList.remove(selectedRow));
    }

    // Method to retrieve a deleted contact
    private void retrieveContact(String selectedContact) {
        Contact retrievedContact = deletedContacts.stream()
                .filter(c -> c.getName().equals(selectedContact))
                .findFirst()
                .orElse(null);

        if (retrievedContact != null) {
            contactList.add(retrievedContact);
            deletedContacts.remove(retrievedContact);
            Collections.sort(contactList, (c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
            updateTable();
            JOptionPane.showMessageDialog(this, "Contact retrieved successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Error retrieving contact.");
        }
    }

    // Method to search for a contact in the list
    private void searchContact(String searchTerm) {
        model.setRowCount(0);
        for (Contact contact : contactList) {
            if (contact.getName().toLowerCase().contains(searchTerm)
                    || contact.getNumber().contains(searchTerm)
                    || contact.getEmail().toLowerCase().contains(searchTerm)) {
                model.addRow(new Object[]{contact.getName(), contact.getNumber(), contact.getEmail()});
            }
        }
    }

    public class Contact {

        private String name;
        private String number;
        private String email;

        public Contact(String name, String number, String email) {
            this.name = name;
            this.number = number;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getNumber() {
            return number;
        }

        public String getEmail() {
            return email;
        }
    }
}
