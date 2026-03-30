package com.mycompany.q4;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.sql.*;

public class CustomerForm extends JFrame {

    JTextField txtName, txtHomePhone, txtBusinessPhone, txtMobile,
            txtMarital, txtChildren, txtIncome;
    JTextArea txtHomeAddress, txtBusinessAddress;

    JButton btnAdd, btnSave, btnDelete, btnFind,
            btnFirst, btnPrev, btnNext, btnLast, btnAvg;

    Connection con;
    ResultSet rs;
    Statement stmt;

    public CustomerForm() {

        con = DBConnection.getConnection();

        if (con == null) {
            JOptionPane.showMessageDialog(this,
                    "Database connection failed!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        setTitle("Customer Management System");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        add(createFormPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        loadData();
        buttonActions();
    }

    // ================= FORM PANEL =================
    private JPanel createFormPanel() {

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtName = new JTextField(20);
        txtHomePhone = new JTextField(15);
        txtBusinessPhone = new JTextField(15);
        txtMobile = new JTextField(15);
        txtMarital = new JTextField(10);
        txtChildren = new JTextField(5);
        txtIncome = new JTextField(10);

        txtHomeAddress = new JTextArea(3, 20);
        txtBusinessAddress = new JTextArea(3, 20);

        int y = 0;

        addRow(panel, gbc, y++, "Name", txtName, "Home Phone", txtHomePhone);
        addRow(panel, gbc, y++, "Home Address", new JScrollPane(txtHomeAddress),
                "Business Phone", txtBusinessPhone);
        addRow(panel, gbc, y++, "Business Address", new JScrollPane(txtBusinessAddress),
                "Mobile", txtMobile);
        addRow(panel, gbc, y++, "Marital Status", txtMarital,
                "No. of Children", txtChildren);
        addRow(panel, gbc, y++, "Annual Income", txtIncome, "", new JLabel());

        return panel;
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, int y,
                        String l1, Component c1, String l2, Component c2) {

        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel(l1), gbc);

        gbc.gridx = 1;
        panel.add(c1, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel(l2), gbc);

        gbc.gridx = 3;
        panel.add(c2, gbc);
    }

    // ================= BUTTON PANEL =================
    private JPanel createButtonPanel() {

        JPanel panel = new JPanel(new GridLayout(2, 5, 10, 10));
        panel.setBorder(new EmptyBorder(10, 20, 15, 20));

        btnAdd = new JButton("Add");
        btnSave = new JButton("Save");
        btnDelete = new JButton("Delete");
        btnFind = new JButton("Find");
        btnAvg = new JButton("Avg Married Income");

        btnFirst = new JButton("First");
        btnPrev = new JButton("Previous");
        btnNext = new JButton("Next");
        btnLast = new JButton("Last");

        panel.add(btnAdd);
        panel.add(btnSave);
        panel.add(btnDelete);
        panel.add(btnFind);
        panel.add(btnAvg);

        panel.add(btnFirst);
        panel.add(btnPrev);
        panel.add(btnNext);
        panel.add(btnLast);

        return panel;
    }

    // ================= DATABASE METHODS =================
    void loadData() {
        try {
            stmt = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("SELECT * FROM customers");
            if (rs.next()) showData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void showData() throws Exception {
        txtName.setText(rs.getString("name"));
        txtHomeAddress.setText(rs.getString("home_address"));
        txtHomePhone.setText(rs.getString("home_phone"));
        txtBusinessAddress.setText(rs.getString("business_address"));
        txtBusinessPhone.setText(rs.getString("business_phone"));
        txtMobile.setText(rs.getString("mobile"));
        txtMarital.setText(rs.getString("marital_status"));
        txtChildren.setText(rs.getString("children"));
        txtIncome.setText(rs.getString("annual_income"));
    }

    void clearFields() {
        txtName.setText("");
        txtHomeAddress.setText("");
        txtHomePhone.setText("");
        txtBusinessAddress.setText("");
        txtBusinessPhone.setText("");
        txtMobile.setText("");
        txtMarital.setText("");
        txtChildren.setText("");
        txtIncome.setText("");
    }

    void buttonActions() {

        // ADD
        btnAdd.addActionListener(e -> clearFields());

        // SAVE
        btnSave.addActionListener(e -> {
            try {
                String sql = "INSERT INTO customers " +
                        "(name, home_address, home_phone, business_address, " +
                        "business_phone, mobile, marital_status, children, annual_income) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, txtName.getText());
                ps.setString(2, txtHomeAddress.getText());
                ps.setString(3, txtHomePhone.getText());
                ps.setString(4, txtBusinessAddress.getText());
                ps.setString(5, txtBusinessPhone.getText());
                ps.setString(6, txtMobile.getText());
                ps.setString(7, txtMarital.getText());
                ps.setInt(8, Integer.parseInt(txtChildren.getText()));
                ps.setDouble(9, Double.parseDouble(txtIncome.getText()));

                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Record Saved");
                loadData();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // DELETE
        btnDelete.addActionListener(e -> {
            try {
                PreparedStatement ps =
                        con.prepareStatement("DELETE FROM customers WHERE name=?");
                ps.setString(1, txtName.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Record Deleted");
                loadData();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // FIND
        btnFind.addActionListener(e -> {
            try {
                PreparedStatement ps =
                        con.prepareStatement("SELECT * FROM customers WHERE name=?");
                ps.setString(1, txtName.getText());
                rs = ps.executeQuery();
                if (rs.next()) showData();
                else JOptionPane.showMessageDialog(this, "Record Not Found");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // NAVIGATION
        btnFirst.addActionListener(e -> { try { rs.first(); showData(); } catch (Exception ex) {}});
        btnPrev.addActionListener(e -> { try { rs.previous(); showData(); } catch (Exception ex) {}});
        btnNext.addActionListener(e -> { try { rs.next(); showData(); } catch (Exception ex) {}});
        btnLast.addActionListener(e -> { try { rs.last(); showData(); } catch (Exception ex) {}});

        // STORED PROCEDURE
        btnAvg.addActionListener(e -> {
            try {
                CallableStatement cs =
                        con.prepareCall("{call avg_married_income(?)}");
                cs.registerOutParameter(1, Types.DOUBLE);
                cs.execute();
                JOptionPane.showMessageDialog(this,
                        "Average Income (Married): " + cs.getDouble(1));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
