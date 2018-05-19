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
        recentLabel = new javax.swing.JLabel();
        recentPanel = new javax.swing.JPanel();
        recentScrollPane = new javax.swing.JScrollPane();
        recentList = new javax.swing.JList<>();
        addEntityButton = new javax.swing.JButton();
        infoPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        imagePreviewPanel = new javax.swing.JPanel();
        colorLabel = new javax.swing.JLabel();
        colorCodeTextField = new javax.swing.JTextField();
        colorDisplayTextField = new javax.swing.JTextField();
        revertButton = new javax.swing.JButton();
        applyButton = new javax.swing.JButton();
        keywordTextField = new javax.swing.JTextField();
        keywordLabel = new javax.swing.JLabel();
        selectButton = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        java.awt.GridBagLayout selectionPanelLayout = new java.awt.GridBagLayout();
        selectionPanelLayout.columnWidths = new int[] {0};
        selectionPanelLayout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        selectionPanel.setLayout(selectionPanelLayout);

        typePanel.setLayout(new java.awt.BorderLayout());

        typeLabel.setText("Type:");
        typePanel.add(typeLabel, java.awt.BorderLayout.WEST);

        typeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        typePanel.add(typeComboBox, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        selectionPanel.add(typePanel, gridBagConstraints);

        searchPanel.setLayout(new java.awt.BorderLayout());

        searchLabel.setText("Search:");
        searchPanel.add(searchLabel, java.awt.BorderLayout.WEST);

        searchTextField.setText("Name");
        searchPanel.add(searchTextField, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
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
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        selectionPanel.add(resultsPanel, gridBagConstraints);

        recentLabel.setText("Recent:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        selectionPanel.add(recentLabel, gridBagConstraints);

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
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        selectionPanel.add(recentPanel, gridBagConstraints);

        addEntityButton.setText("Add Entity");
        addEntityButton.setToolTipText("Add a new Entity to this project.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        selectionPanel.add(addEntityButton, gridBagConstraints);

        getContentPane().add(selectionPanel, java.awt.BorderLayout.WEST);

        nameLabel.setText("Name");

        imagePreviewPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout imagePreviewPanelLayout = new javax.swing.GroupLayout(imagePreviewPanel);
        imagePreviewPanel.setLayout(imagePreviewPanelLayout);
        imagePreviewPanelLayout.setHorizontalGroup(
            imagePreviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        imagePreviewPanelLayout.setVerticalGroup(
            imagePreviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        colorLabel.setText("Color:");

        colorCodeTextField.setText("000000");

        revertButton.setText("Revert");

        applyButton.setText("Apply");

        keywordLabel.setText("Keywords:");

        selectButton.setText("Select");

        javax.swing.GroupLayout infoPanelLayout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(infoPanelLayout.createSequentialGroup()
                                .addComponent(colorLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(colorCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imagePreviewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(44, Short.MAX_VALUE))
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(selectButton)
                            .addComponent(colorDisplayTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(keywordLabel)
                            .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(infoPanelLayout.createSequentialGroup()
                                    .addComponent(revertButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(applyButton))
                                .addComponent(keywordTextField)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        infoPanelLayout.setVerticalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addComponent(selectButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imagePreviewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(colorLabel)
                    .addComponent(colorCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(colorDisplayTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addComponent(keywordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(keywordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(revertButton)
                    .addComponent(applyButton))
                .addContainerGap())
        );

        getContentPane().add(infoPanel, java.awt.BorderLayout.CENTER);

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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addEntityButton;
    private javax.swing.JButton applyButton;
    private javax.swing.JTextField colorCodeTextField;
    private javax.swing.JTextField colorDisplayTextField;
    private javax.swing.JLabel colorLabel;
    private javax.swing.JPanel imagePreviewPanel;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JLabel keywordLabel;
    private javax.swing.JTextField keywordTextField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel recentLabel;
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
    private javax.swing.JButton selectButton;
    private javax.swing.JPanel selectionPanel;
    private javax.swing.JComboBox<String> typeComboBox;
    private javax.swing.JLabel typeLabel;
    private javax.swing.JPanel typePanel;
    // End of variables declaration//GEN-END:variables
}
