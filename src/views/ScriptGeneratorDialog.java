/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Scott
 */
public class ScriptGeneratorDialog extends javax.swing.JDialog {

    /**
     * Creates new form ScriptGeneratorDialog
     * @param parent
     * @param modal
     */
    public ScriptGeneratorDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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

        mainPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        groupEntitiesByTypeCheckBox = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        typeComboBox = new javax.swing.JComboBox<>();
        gridSizePanel = new javax.swing.JPanel();
        gridSizeTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cellSizeGapPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        xCellSizeTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        zCellSizeTextField = new javax.swing.JTextField();
        yCellSizeTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        xCellGapTextField = new javax.swing.JTextField();
        yCellGapTextField = new javax.swing.JTextField();
        zCellGapTextField = new javax.swing.JTextField();
        gameObjectScriptsPanel = new javax.swing.JPanel();
        levelGeneratorCheckBox = new javax.swing.JCheckBox();
        entityCheckBox = new javax.swing.JCheckBox();
        tileScriptsPanel = new javax.swing.JPanel();
        tileEntityCheckBox = new javax.swing.JCheckBox();
        tileLevelGeneratorCheckBox = new javax.swing.JCheckBox();
        useImagesCheckBox = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        buttonPanel = new javax.swing.JPanel();
        cancelButton = new javax.swing.JButton();
        generateButton = new javax.swing.JButton();
        imagePathPanel = new javax.swing.JPanel();
        imageFolderTextField = new javax.swing.JTextField();
        imageBrowseButton = new javax.swing.JButton();
        destinationPathPanel = new javax.swing.JPanel();
        desitnationFolderTextField = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        mainPanel.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Destination Folder:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        mainPanel.add(jLabel1, gridBagConstraints);

        groupEntitiesByTypeCheckBox.setText("Group Entities by Type");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        mainPanel.add(groupEntitiesByTypeCheckBox, gridBagConstraints);

        jLabel2.setText("Type:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        mainPanel.add(jLabel2, gridBagConstraints);

        jLabel4.setText("Include:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        mainPanel.add(jLabel4, gridBagConstraints);

        typeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        typeComboBox.setToolTipText("Choose the type of level generator\\nthat this project should be.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        mainPanel.add(typeComboBox, gridBagConstraints);

        gridSizeTextField.setColumns(4);
        gridSizeTextField.setText("32.0");

        jLabel3.setText("Grid Size:");

        javax.swing.GroupLayout gridSizePanelLayout = new javax.swing.GroupLayout(gridSizePanel);
        gridSizePanel.setLayout(gridSizePanelLayout);
        gridSizePanelLayout.setHorizontalGroup(
            gridSizePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gridSizePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gridSizeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                .addContainerGap())
        );
        gridSizePanelLayout.setVerticalGroup(
            gridSizePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gridSizePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gridSizePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(gridSizeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        mainPanel.add(gridSizePanel, gridBagConstraints);

        jLabel5.setText("Cell Size:");

        xCellSizeTextField.setText("1");

        jLabel6.setText("Cell Gap:");

        zCellSizeTextField.setText("0");

        yCellSizeTextField.setText("1");

        jLabel7.setText("x");

        jLabel8.setText("y");

        jLabel9.setText("z");

        xCellGapTextField.setText("0");

        yCellGapTextField.setText("0");

        zCellGapTextField.setText("0");

        javax.swing.GroupLayout cellSizeGapPanelLayout = new javax.swing.GroupLayout(cellSizeGapPanel);
        cellSizeGapPanel.setLayout(cellSizeGapPanelLayout);
        cellSizeGapPanelLayout.setHorizontalGroup(
            cellSizeGapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cellSizeGapPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cellSizeGapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cellSizeGapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cellSizeGapPanelLayout.createSequentialGroup()
                        .addComponent(xCellSizeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yCellSizeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cellSizeGapPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel8))
                    .addGroup(cellSizeGapPanelLayout.createSequentialGroup()
                        .addComponent(xCellGapTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yCellGapTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cellSizeGapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(zCellSizeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zCellGapTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cellSizeGapPanelLayout.setVerticalGroup(
            cellSizeGapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cellSizeGapPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cellSizeGapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cellSizeGapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(xCellSizeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yCellSizeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zCellSizeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cellSizeGapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(xCellGapTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yCellGapTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zCellGapTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        mainPanel.add(cellSizeGapPanel, gridBagConstraints);

        levelGeneratorCheckBox.setSelected(true);
        levelGeneratorCheckBox.setText("LevelGenerator.cs");

        entityCheckBox.setSelected(true);
        entityCheckBox.setText("Entity.cs");

        javax.swing.GroupLayout gameObjectScriptsPanelLayout = new javax.swing.GroupLayout(gameObjectScriptsPanel);
        gameObjectScriptsPanel.setLayout(gameObjectScriptsPanelLayout);
        gameObjectScriptsPanelLayout.setHorizontalGroup(
            gameObjectScriptsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gameObjectScriptsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gameObjectScriptsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(entityCheckBox)
                    .addComponent(levelGeneratorCheckBox)))
        );
        gameObjectScriptsPanelLayout.setVerticalGroup(
            gameObjectScriptsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gameObjectScriptsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(entityCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(levelGeneratorCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        mainPanel.add(gameObjectScriptsPanel, gridBagConstraints);

        tileEntityCheckBox.setSelected(true);
        tileEntityCheckBox.setText("TileEntity.cs");

        tileLevelGeneratorCheckBox.setSelected(true);
        tileLevelGeneratorCheckBox.setText("TileLevelGenerator.cs");

        javax.swing.GroupLayout tileScriptsPanelLayout = new javax.swing.GroupLayout(tileScriptsPanel);
        tileScriptsPanel.setLayout(tileScriptsPanelLayout);
        tileScriptsPanelLayout.setHorizontalGroup(
            tileScriptsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tileScriptsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tileScriptsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tileEntityCheckBox)
                    .addComponent(tileLevelGeneratorCheckBox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tileScriptsPanelLayout.setVerticalGroup(
            tileScriptsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tileScriptsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tileEntityCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tileLevelGeneratorCheckBox))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        mainPanel.add(tileScriptsPanel, gridBagConstraints);

        useImagesCheckBox.setText("Use Image Analysis");
        useImagesCheckBox.setToolTipText("<html> \tGoes through all images in the folder and scans their pixels<br> \tand generates scripts using only the Entities whose colors<br> \tare used, excluding Entities with unused colors.<br> </html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        mainPanel.add(useImagesCheckBox, gridBagConstraints);

        jLabel12.setText("Special:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        mainPanel.add(jLabel12, gridBagConstraints);

        buttonPanel.setLayout(new javax.swing.BoxLayout(buttonPanel, javax.swing.BoxLayout.X_AXIS));

        cancelButton.setText("Cancel");
        buttonPanel.add(cancelButton);

        generateButton.setText("Generate");
        buttonPanel.add(generateButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        mainPanel.add(buttonPanel, gridBagConstraints);

        imagePathPanel.setLayout(new javax.swing.BoxLayout(imagePathPanel, javax.swing.BoxLayout.X_AXIS));
        imagePathPanel.add(imageFolderTextField);

        imageBrowseButton.setText("Browse");
        imagePathPanel.add(imageBrowseButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        mainPanel.add(imagePathPanel, gridBagConstraints);

        destinationPathPanel.setLayout(new javax.swing.BoxLayout(destinationPathPanel, javax.swing.BoxLayout.X_AXIS));
        destinationPathPanel.add(desitnationFolderTextField);

        browseButton.setText("Browse");
        destinationPathPanel.add(browseButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        mainPanel.add(destinationPathPanel, gridBagConstraints);

        getContentPane().add(mainPanel, java.awt.BorderLayout.NORTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel cellSizeGapPanel;
    private javax.swing.JTextField desitnationFolderTextField;
    private javax.swing.JPanel destinationPathPanel;
    private javax.swing.JCheckBox entityCheckBox;
    private javax.swing.JPanel gameObjectScriptsPanel;
    private javax.swing.JButton generateButton;
    private javax.swing.JPanel gridSizePanel;
    private javax.swing.JTextField gridSizeTextField;
    private javax.swing.JCheckBox groupEntitiesByTypeCheckBox;
    private javax.swing.JButton imageBrowseButton;
    private javax.swing.JTextField imageFolderTextField;
    private javax.swing.JPanel imagePathPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JCheckBox levelGeneratorCheckBox;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JCheckBox tileEntityCheckBox;
    private javax.swing.JCheckBox tileLevelGeneratorCheckBox;
    private javax.swing.JPanel tileScriptsPanel;
    private javax.swing.JComboBox<String> typeComboBox;
    private javax.swing.JCheckBox useImagesCheckBox;
    private javax.swing.JTextField xCellGapTextField;
    private javax.swing.JTextField xCellSizeTextField;
    private javax.swing.JTextField yCellGapTextField;
    private javax.swing.JTextField yCellSizeTextField;
    private javax.swing.JTextField zCellGapTextField;
    private javax.swing.JTextField zCellSizeTextField;
    // End of variables declaration//GEN-END:variables

    public JButton getBrowseButton() {
        return browseButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JTextField getDesitnationFolderTextField() {
        return desitnationFolderTextField;
    }

    public JButton getGenerateButton() {
        return generateButton;
    }

    public JCheckBox getEntityCheckBox() {
        return entityCheckBox;
    }

    public JCheckBox getLevelGeneratorCheckBox() {
        return levelGeneratorCheckBox;
    }

    public JCheckBox getGroupEntitiesByTypeCheckBox() {
        return groupEntitiesByTypeCheckBox;
    }

    public JTextField getGridSizeTextField() {
        return gridSizeTextField;
    }

    public JPanel getCellSizeGapPanel() {
        return cellSizeGapPanel;
    }

    public JPanel getGridSizePanel() {
        return gridSizePanel;
    }

    public JPanel getGameObjectScriptsPanel() {
        return gameObjectScriptsPanel;
    }

    public JCheckBox getTileEntityCheckBox() {
        return tileEntityCheckBox;
    }

    public JCheckBox getTileLevelGeneratorCheckBox() {
        return tileLevelGeneratorCheckBox;
    }

    public JPanel getTileScriptsPanel() {
        return tileScriptsPanel;
    }

    public JComboBox<String> getTypeComboBox() {
        return typeComboBox;
    }

    public JTextField getxCellGapTextField() {
        return xCellGapTextField;
    }

    public JTextField getxCellSizeTextField() {
        return xCellSizeTextField;
    }

    public JTextField getyCellGapTextField() {
        return yCellGapTextField;
    }

    public JTextField getyCellSizeTextField() {
        return yCellSizeTextField;
    }

    public JTextField getzCellGapTextField() {
        return zCellGapTextField;
    }

    public JTextField getzCellSizeTextField() {
        return zCellSizeTextField;
    }

    public JTextField getImageFolderTextField() {
        return imageFolderTextField;
    }

    public JCheckBox getUseImagesCheckBox() {
        return useImagesCheckBox;
    }

    public JButton getImageBrowseButton() {
        return imageBrowseButton;
    }
    
}
