/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

/**
 *
 * @author Scott
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        selectionPanel = new javax.swing.JPanel();
        typePanel = new javax.swing.JPanel();
        typeLabel = new javax.swing.JLabel();
        typeComboBox = new javax.swing.JComboBox<>();
        searchPanel = new javax.swing.JPanel();
        searchLabel = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        resultsPanel = new javax.swing.JPanel();
        resultsScrollPane = new javax.swing.JScrollPane();
        resultsList = new javax.swing.JList<>();
        recentPanel = new javax.swing.JPanel();
        recentScrollPane = new javax.swing.JScrollPane();
        recentList = new javax.swing.JList<>();
        infoPanel = new javax.swing.JPanel();
        idPanel = new javax.swing.JPanel();
        imagePanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        copyPanel = new javax.swing.JPanel();
        copyButton = new javax.swing.JButton();
        colorPanel = new javax.swing.JPanel();
        colorLabel = new javax.swing.JLabel();
        colorCodeTextField = new javax.swing.JTextField();
        colorDisplayTextField = new javax.swing.JTextField();
        keywordPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        keywordScrollPane = new javax.swing.JScrollPane();
        keywordTextArea = new javax.swing.JTextArea();
        changeButtonPanel = new javax.swing.JPanel();
        revertButton = new javax.swing.JButton();
        applyButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        selectionPanel.setLayout(new java.awt.GridBagLayout());

        typeLabel.setText("Type:");
        typePanel.add(typeLabel);

        typeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        typePanel.add(typeComboBox);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        selectionPanel.add(typePanel, gridBagConstraints);

        searchLabel.setText("Search:");
        searchPanel.add(searchLabel);

        searchTextField.setText("Name");
        searchPanel.add(searchTextField);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        selectionPanel.add(searchPanel, gridBagConstraints);

        resultsPanel.setLayout(new java.awt.BorderLayout());

        resultsList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        resultsScrollPane.setViewportView(resultsList);

        resultsPanel.add(resultsScrollPane, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        selectionPanel.add(resultsPanel, gridBagConstraints);

        recentPanel.setLayout(new java.awt.BorderLayout());

        recentList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        recentScrollPane.setViewportView(recentList);

        recentPanel.add(recentScrollPane, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        selectionPanel.add(recentPanel, gridBagConstraints);

        getContentPane().add(selectionPanel, java.awt.BorderLayout.WEST);

        infoPanel.setLayout(new java.awt.GridBagLayout());

        imagePanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 64, Short.MAX_VALUE)
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 64, Short.MAX_VALUE)
        );

        idPanel.add(imagePanel);

        nameLabel.setText("Name");
        idPanel.add(nameLabel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        infoPanel.add(idPanel, gridBagConstraints);

        copyButton.setText("Copy");
        copyPanel.add(copyButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        infoPanel.add(copyPanel, gridBagConstraints);

        colorLabel.setText("Color:");
        colorPanel.add(colorLabel);

        colorCodeTextField.setText("000000");
        colorPanel.add(colorCodeTextField);
        colorPanel.add(colorDisplayTextField);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        infoPanel.add(colorPanel, gridBagConstraints);

        jLabel1.setText("keywordLabel:");
        keywordPanel.add(jLabel1);

        keywordTextArea.setColumns(20);
        keywordTextArea.setRows(5);
        keywordScrollPane.setViewportView(keywordTextArea);

        keywordPanel.add(keywordScrollPane);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        infoPanel.add(keywordPanel, gridBagConstraints);

        revertButton.setText("Revert");
        changeButtonPanel.add(revertButton);

        applyButton.setText("Apply");
        changeButtonPanel.add(applyButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        infoPanel.add(changeButtonPanel, gridBagConstraints);

        getContentPane().add(infoPanel, java.awt.BorderLayout.EAST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyButton;
    private javax.swing.JPanel changeButtonPanel;
    private javax.swing.JTextField colorCodeTextField;
    private javax.swing.JTextField colorDisplayTextField;
    private javax.swing.JLabel colorLabel;
    private javax.swing.JPanel colorPanel;
    private javax.swing.JButton copyButton;
    private javax.swing.JPanel copyPanel;
    private javax.swing.JPanel idPanel;
    private javax.swing.JPanel imagePanel;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel keywordPanel;
    private javax.swing.JScrollPane keywordScrollPane;
    private javax.swing.JTextArea keywordTextArea;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JList<String> recentList;
    private javax.swing.JPanel recentPanel;
    private javax.swing.JScrollPane recentScrollPane;
    private javax.swing.JList<String> resultsList;
    private javax.swing.JPanel resultsPanel;
    private javax.swing.JScrollPane resultsScrollPane;
    private javax.swing.JButton revertButton;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JPanel selectionPanel;
    private javax.swing.JComboBox<String> typeComboBox;
    private javax.swing.JLabel typeLabel;
    private javax.swing.JPanel typePanel;
    // End of variables declaration//GEN-END:variables
}
