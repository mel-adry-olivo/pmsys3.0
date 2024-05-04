package org.pmsys.main.ui.components;


import org.pmsys.main.ui.components.base.FlatPanel;


public class FormTest extends FlatPanel {

    public FormTest() {
        
        initComponents();
        txtDueDateField.putClientProperty("JTextField.placeholderText", "dd MMM yyy (e.g., 20 Jan 2022)");
    }

    private void initComponents() {

        lbTitle = new javax.swing.JLabel();
        lblTitleField = new javax.swing.JLabel();
        txtTitleField = new javax.swing.JTextField();
        lblDescriptionField = new javax.swing.JLabel();
        lblOptional = new javax.swing.JLabel();
        txtDescriptionField = new javax.swing.JTextField();
        lblDueDateField = new javax.swing.JLabel();
        txtDueDateField = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();

        setConstraints("insets 20 30 20 30, fill");
        setBackground(new java.awt.Color(255, 255, 255));
        setArc(0);

        setFocusable(false);
        setMaximumSize(new java.awt.Dimension(370, 400));
        setMinimumSize(new java.awt.Dimension(370, 400));
        setName(""); // NOI18N
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(370, 400));
        setRowConstraints("[]5[]5[][]5[][]5[]15[]");

        lbTitle.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(38, 38, 38));
        lbTitle.setText("Create Project");
        lbTitle.setFocusable(false);
        lbTitle.setRequestFocusEnabled(false);
        lbTitle.setVerifyInputWhenFocusTarget(false);
        add(lbTitle, "wrap");

        lblTitleField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTitleField.setForeground(new java.awt.Color(38, 38, 38));
        lblTitleField.setText("Title");
        lblTitleField.setFocusable(false);
        lblTitleField.setRequestFocusEnabled(false);
        lblTitleField.setVerifyInputWhenFocusTarget(false);
        add(lblTitleField, "wrap");

        txtTitleField.setMargin(new java.awt.Insets(4, 10, 4, 6));
        txtTitleField.setMinimumSize(new java.awt.Dimension(310, 40));
        txtTitleField.setNextFocusableComponent(txtDescriptionField);
        txtTitleField.setPreferredSize(new java.awt.Dimension(280, 40));
        add(txtTitleField, "wrap, span 3 0");

        lblDescriptionField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDescriptionField.setForeground(new java.awt.Color(38, 38, 38));
        lblDescriptionField.setText("Description");
        lblDescriptionField.setFocusable(false);
        lblDescriptionField.setRequestFocusEnabled(false);
        lblDescriptionField.setVerifyInputWhenFocusTarget(false);
        add(lblDescriptionField, "split 2");

        lblOptional.setFont(new java.awt.Font("Inter Medium", 0, 10)); // NOI18N
        lblOptional.setForeground(new java.awt.Color(157, 157, 157));
        lblOptional.setText("(Optional)");
        lblOptional.setFocusable(false);
        lblOptional.setRequestFocusEnabled(false);
        lblOptional.setVerifyInputWhenFocusTarget(false);
        add(lblOptional, "span 2, wrap");

        txtDescriptionField.setMargin(new java.awt.Insets(4, 10, 4, 6));
        txtDescriptionField.setMinimumSize(new java.awt.Dimension(310, 40));
        txtDescriptionField.setNextFocusableComponent(txtDueDateField);
        txtDescriptionField.setPreferredSize(new java.awt.Dimension(280, 40));
        add(txtDescriptionField, "wrap, span 3 0");

        lblDueDateField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDueDateField.setForeground(new java.awt.Color(38, 38, 38));
        lblDueDateField.setText("Due Date");
        lblDueDateField.setFocusable(false);
        lblDueDateField.setRequestFocusEnabled(false);
        lblDueDateField.setVerifyInputWhenFocusTarget(false);
        add(lblDueDateField, "wrap");

        txtDueDateField.setMargin(new java.awt.Insets(4, 10, 4, 6));
        txtDueDateField.setMinimumSize(new java.awt.Dimension(310, 40));
        txtDueDateField.setNextFocusableComponent(btnCreate);
        txtDueDateField.setPreferredSize(new java.awt.Dimension(280, 40));
        add(txtDueDateField, "wrap, span 3 0");

        btnCancel.setBackground(new java.awt.Color(255, 255, 255));
        btnCancel.setFont(new java.awt.Font("Inter", 1, 11)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(38, 38, 38));
        btnCancel.setText("CANCEL");
        btnCancel.setBorderPainted(false);
        btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancel.setFocusPainted(false);
        btnCancel.setFocusable(false);
        btnCancel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnCancel.setMaximumSize(new java.awt.Dimension(75, 30));
        btnCancel.setMinimumSize(new java.awt.Dimension(75, 30));
        btnCancel.setPreferredSize(new java.awt.Dimension(75, 30));
        add(btnCancel, "x 175");

        btnCreate.setBackground(new java.awt.Color(47, 43, 67));
        btnCreate.setFont(new java.awt.Font("Inter", 1, 11)); // NOI18N
        btnCreate.setForeground(new java.awt.Color(255, 255, 255));
        btnCreate.setText("CREATE");
        btnCreate.setBorderPainted(false);
        btnCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCreate.setFocusPainted(false);
        btnCreate.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnCreate.setMaximumSize(new java.awt.Dimension(75, 30));
        btnCreate.setMinimumSize(new java.awt.Dimension(75, 30));
        btnCreate.setPreferredSize(new java.awt.Dimension(75, 30));
        add(btnCreate, "align right");
    }



    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCreate;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lblDescriptionField;
    private javax.swing.JLabel lblDueDateField;
    private javax.swing.JLabel lblOptional;
    private javax.swing.JLabel lblTitleField;
    private javax.swing.JTextField txtDescriptionField;
    private javax.swing.JTextField txtDueDateField;
    private javax.swing.JTextField txtTitleField;
    // End of variables declaration//GEN-END:variables

    
}
