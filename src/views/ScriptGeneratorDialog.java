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

        jLabel1 = new javax.swing.JLabel();
        desitnationFolderTextField = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        generateButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
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
        scriptsPanel = new javax.swing.JPanel();
        levelGeneratorCheckBox = new javax.swing.JCheckBox();
        entityCheckBox = new javax.swing.JCheckBox();
        tileScriptsPanel = new javax.swing.JPanel();
        tileEntityCheckBox = new javax.swing.JCheckBox();
        tileLevelGeneratorCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Destination Folder:");

        browseButton.setText("Browse");

        generateButton.setText("Generate");

        cancelButton.setText("Cancel");

        groupEntitiesByTypeCheckBox.setText("Group Entities by Type");

        jLabel2.setText("Type:");

        jLabel4.setText("Include:");

        typeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                .addComponent(gridSizeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
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

        levelGeneratorCheckBox.setSelected(true);
        levelGeneratorCheckBox.setText("LevelGenerator.cs");

        entityCheckBox.setSelected(true);
        entityCheckBox.setText("Entity.cs");

        javax.swing.GroupLayout scriptsPanelLayout = new javax.swing.GroupLayout(scriptsPanel);
        scriptsPanel.setLayout(scriptsPanelLayout);
        scriptsPanelLayout.setHorizontalGroup(
            scriptsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scriptsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(scriptsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(entityCheckBox)
                    .addComponent(levelGeneratorCheckBox)))
        );
        scriptsPanelLayout.setVerticalGroup(
            scriptsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scriptsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(entityCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(levelGeneratorCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(desitnationFolderTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(browseButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 242, Short.MAX_VALUE)
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(generateButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scriptsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tileScriptsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(groupEntitiesByTypeCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(gridSizePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cellSizeGapPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scriptsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tileScriptsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(groupEntitiesByTypeCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gridSizePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cellSizeGapPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(desitnationFolderTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generateButton)
                    .addComponent(cancelButton)))
        );

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
            java.util.logging.Logger.getLogger(ScriptGeneratorDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ScriptGeneratorDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ScriptGeneratorDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScriptGeneratorDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ScriptGeneratorDialog dialog = new ScriptGeneratorDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel cellSizeGapPanel;
    private javax.swing.JTextField desitnationFolderTextField;
    private javax.swing.JCheckBox entityCheckBox;
    private javax.swing.JButton generateButton;
    private javax.swing.JPanel gridSizePanel;
    private javax.swing.JTextField gridSizeTextField;
    private javax.swing.JCheckBox groupEntitiesByTypeCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JCheckBox levelGeneratorCheckBox;
    private javax.swing.JPanel scriptsPanel;
    private javax.swing.JCheckBox tileEntityCheckBox;
    private javax.swing.JCheckBox tileLevelGeneratorCheckBox;
    private javax.swing.JPanel tileScriptsPanel;
    private javax.swing.JComboBox<String> typeComboBox;
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

    public JPanel getScriptsPanel() {
        return scriptsPanel;
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
    
}
