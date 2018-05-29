/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

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

        mainSplitPane = new javax.swing.JSplitPane();
        selectionPanel = new javax.swing.JPanel();
        typePanel = new javax.swing.JPanel();
        typeLabel = new javax.swing.JLabel();
        searchModeComboBox = new javax.swing.JComboBox<>();
        searchPanel = new javax.swing.JPanel();
        searchLabel = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        addEntityButton = new javax.swing.JButton();
        listsSplitPane = new javax.swing.JSplitPane();
        resultsPanel = new javax.swing.JPanel();
        resultsScrollPane = new javax.swing.JScrollPane();
        resultsList = new javax.swing.JList<>();
        recentPanel = new javax.swing.JPanel();
        recentScrollPane = new javax.swing.JScrollPane();
        recentList = new javax.swing.JList<>();
        recentLabel = new javax.swing.JLabel();
        infoPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        selectButton = new javax.swing.JButton();
        sliderPanel = new javax.swing.JPanel();
        redSlider = new javax.swing.JSlider();
        greenSlider = new javax.swing.JSlider();
        blueSlider = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        redSpinner = new javax.swing.JSpinner();
        greenSpinner = new javax.swing.JSpinner();
        blueSpinner = new javax.swing.JSpinner();
        colorPanel = new javax.swing.JPanel();
        colorCodeTextField = new javax.swing.JTextField();
        colorLabel = new javax.swing.JLabel();
        imagePanel = new views.ImagePanel();
        applyButton = new javax.swing.JButton();
        revertButton = new javax.swing.JButton();
        includeHashTagCheckBox = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        typeComboBox = new javax.swing.JComboBox<>();
        changeImageButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        unityPrefabTextField = new javax.swing.JTextField();
        newTypeButton = new javax.swing.JButton();
        deleteImageButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newProjectMenuItem = new javax.swing.JMenuItem();
        openProjectMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        addEntityMenuItem = new javax.swing.JMenuItem();
        deleteEntityMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        mainSplitPane.setResizeWeight(0.4);
        mainSplitPane.setOneTouchExpandable(true);

        java.awt.GridBagLayout selectionPanelLayout = new java.awt.GridBagLayout();
        selectionPanelLayout.columnWidths = new int[] {0};
        selectionPanelLayout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0};
        selectionPanel.setLayout(selectionPanelLayout);

        typePanel.setLayout(new java.awt.BorderLayout());

        typeLabel.setText("Mode:");
        typePanel.add(typeLabel, java.awt.BorderLayout.WEST);

        searchModeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        typePanel.add(searchModeComboBox, java.awt.BorderLayout.CENTER);

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
        searchPanel.add(searchTextField, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        selectionPanel.add(searchPanel, gridBagConstraints);

        addEntityButton.setText("Add Entity");
        addEntityButton.setToolTipText("Add a new Entity to this project.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        selectionPanel.add(addEntityButton, gridBagConstraints);

        listsSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        listsSplitPane.setResizeWeight(0.8);

        resultsPanel.setLayout(new java.awt.BorderLayout());

        resultsList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        resultsScrollPane.setViewportView(resultsList);

        resultsPanel.add(resultsScrollPane, java.awt.BorderLayout.CENTER);

        listsSplitPane.setTopComponent(resultsPanel);

        recentPanel.setLayout(new java.awt.BorderLayout());

        recentList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        recentScrollPane.setViewportView(recentList);

        recentPanel.add(recentScrollPane, java.awt.BorderLayout.CENTER);

        recentLabel.setText("Recent:");
        recentPanel.add(recentLabel, java.awt.BorderLayout.PAGE_START);

        listsSplitPane.setBottomComponent(recentPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        selectionPanel.add(listsSplitPane, gridBagConstraints);

        mainSplitPane.setLeftComponent(selectionPanel);

        nameLabel.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        nameLabel.setText("Name:");

        selectButton.setText("Select");

        redSlider.setMaximum(255);
        redSlider.setValue(0);

        greenSlider.setMaximum(255);
        greenSlider.setValue(0);

        blueSlider.setMaximum(255);
        blueSlider.setValue(0);

        jLabel1.setText("Red:");

        jLabel2.setText("Green:");

        jLabel3.setText("Blue:");

        javax.swing.GroupLayout sliderPanelLayout = new javax.swing.GroupLayout(sliderPanel);
        sliderPanel.setLayout(sliderPanelLayout);
        sliderPanelLayout.setHorizontalGroup(
            sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sliderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(greenSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(redSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(blueSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(redSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(greenSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blueSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        sliderPanelLayout.setVerticalGroup(
            sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sliderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(redSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(redSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, sliderPanelLayout.createSequentialGroup()
                        .addComponent(greenSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(blueSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, sliderPanelLayout.createSequentialGroup()
                        .addGroup(sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(greenSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(blueSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(59, 59, 59))
        );

        colorPanel.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout colorPanelLayout = new javax.swing.GroupLayout(colorPanel);
        colorPanel.setLayout(colorPanelLayout);
        colorPanelLayout.setHorizontalGroup(
            colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        colorPanelLayout.setVerticalGroup(
            colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        colorCodeTextField.setColumns(5);
        colorCodeTextField.setText("000000");

        colorLabel.setText("Color:");

        imagePanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 84, Short.MAX_VALUE)
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 84, Short.MAX_VALUE)
        );

        applyButton.setText("Apply");

        revertButton.setText("Revert");

        includeHashTagCheckBox.setText("Include #");

        jLabel4.setText("Type:");

        typeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        changeImageButton.setText("Change");

        jLabel5.setText("Unity Prefab:");

        unityPrefabTextField.setText("Prefabs/APrefab");

        newTypeButton.setText("New");

        deleteImageButton.setText("Delete");

        jLabel6.setText("Image:");

        javax.swing.GroupLayout infoPanelLayout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameLabel)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameTextField)
                            .addGroup(infoPanelLayout.createSequentialGroup()
                                .addComponent(typeComboBox, 0, 1, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(newTypeButton))))
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(colorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(sliderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unityPrefabTextField))
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(infoPanelLayout.createSequentialGroup()
                                .addComponent(colorLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(colorCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(includeHashTagCheckBox))
                            .addComponent(selectButton))
                        .addGap(0, 46, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(revertButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(applyButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(changeImageButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteImageButton)))
                .addContainerGap())
        );
        infoPanelLayout.setVerticalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selectButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newTypeButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(changeImageButton)
                    .addComponent(deleteImageButton)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(colorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(colorLabel)
                    .addComponent(colorCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(includeHashTagCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sliderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(unityPrefabTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(applyButton)
                    .addComponent(revertButton))
                .addContainerGap())
        );

        mainSplitPane.setRightComponent(infoPanel);

        getContentPane().add(mainSplitPane);

        fileMenu.setText("File");

        newProjectMenuItem.setText("New Project");
        fileMenu.add(newProjectMenuItem);

        openProjectMenuItem.setText("Open Project");
        fileMenu.add(openProjectMenuItem);
        fileMenu.add(jSeparator1);

        addEntityMenuItem.setText("Add Entity");
        fileMenu.add(addEntityMenuItem);

        deleteEntityMenuItem.setText("Delete Entity");
        fileMenu.add(deleteEntityMenuItem);

        menuBar.add(fileMenu);

        setJMenuBar(menuBar);

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
    private javax.swing.JMenuItem addEntityMenuItem;
    private javax.swing.JButton applyButton;
    private javax.swing.JSlider blueSlider;
    private javax.swing.JSpinner blueSpinner;
    private javax.swing.JButton changeImageButton;
    private javax.swing.JTextField colorCodeTextField;
    private javax.swing.JLabel colorLabel;
    private javax.swing.JPanel colorPanel;
    private javax.swing.JMenuItem deleteEntityMenuItem;
    private javax.swing.JButton deleteImageButton;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JSlider greenSlider;
    private javax.swing.JSpinner greenSpinner;
    private views.ImagePanel imagePanel;
    private javax.swing.JCheckBox includeHashTagCheckBox;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSplitPane listsSplitPane;
    private javax.swing.JSplitPane mainSplitPane;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JMenuItem newProjectMenuItem;
    private javax.swing.JButton newTypeButton;
    private javax.swing.JMenuItem openProjectMenuItem;
    private javax.swing.JLabel recentLabel;
    private javax.swing.JList<String> recentList;
    private javax.swing.JPanel recentPanel;
    private javax.swing.JScrollPane recentScrollPane;
    private javax.swing.JSlider redSlider;
    private javax.swing.JSpinner redSpinner;
    private javax.swing.JList<String> resultsList;
    private javax.swing.JPanel resultsPanel;
    private javax.swing.JScrollPane resultsScrollPane;
    private javax.swing.JButton revertButton;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JComboBox<String> searchModeComboBox;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JButton selectButton;
    private javax.swing.JPanel selectionPanel;
    private javax.swing.JPanel sliderPanel;
    private javax.swing.JComboBox<String> typeComboBox;
    private javax.swing.JLabel typeLabel;
    private javax.swing.JPanel typePanel;
    private javax.swing.JTextField unityPrefabTextField;
    // End of variables declaration//GEN-END:variables

    public JButton getAddEntityButton() {
        return addEntityButton;
    }

    public JButton getApplyButton() {
        return applyButton;
    }

    public JTextField getColorCodeTextField() {
        return colorCodeTextField;
    }

    public JPanel getColorPanel() {
        return colorPanel;
    }

    public JList<String> getRecentList() {
        return recentList;
    }

    public JList<String> getResultsList() {
        return resultsList;
    }

    public JButton getRevertButton() {
        return revertButton;
    }

    public JTextField getSearchTextField() {
        return searchTextField;
    }

    public JButton getSelectButton() {
        return selectButton;
    }

    public JComboBox<String> getTypeComboBox() {
        return typeComboBox;
    }

    public ImagePanel getImagePanel() {
        return imagePanel;
    }

    public JSpinner getBlueSpinner() {
        return blueSpinner;
    }

    public JSpinner getGreenSpinner() {
        return greenSpinner;
    }

    public JSpinner getRedSpinner() {
        return redSpinner;
    }

    public JSlider getBlueSlider() {
        return blueSlider;
    }

    public JSlider getGreenSlider() {
        return greenSlider;
    }

    public JSlider getRedSlider() {
        return redSlider;
    }
    
    public JCheckBox getIncludeHashTagCheckBox() {
        return includeHashTagCheckBox;
    }

    public JMenuItem getDeleteEntityMenuItem() {
        return deleteEntityMenuItem;
    }

    public JMenuItem getAddEntityMenuItem() {
        return addEntityMenuItem;
    }

    public JMenuItem getNewProjectMenuItem() {
        return newProjectMenuItem;
    }

    public JMenuItem getOpenProjectMenuItem() {
        return openProjectMenuItem;
    }

    public JSplitPane getMainSplitPane() {
        return mainSplitPane;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JButton getChangeImageButton() {
        return changeImageButton;
    }

    public JTextField getUnityPrefabTextField() {
        return unityPrefabTextField;
    }

    public JComboBox<String> getSearchModeComboBox() {
        return searchModeComboBox;
    }

    public JButton getNewTypeButton() {
        return newTypeButton;
    }

    public JButton getDeleteImageButton() {
        return deleteImageButton;
    }
    
}
