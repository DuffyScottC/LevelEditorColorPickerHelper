/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
        entityCheckBox = new javax.swing.JCheckBox();
        entityArrayDrawerCheckBox = new javax.swing.JCheckBox();
        entityArrayAttributeCheckBox = new javax.swing.JCheckBox();
        levelGeneratorCheckBox = new javax.swing.JCheckBox();
        groupEntitiesByTypeCheckBox = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Destination Folder:");

        browseButton.setText("Browse");

        generateButton.setText("Generate");

        cancelButton.setText("Cancel");

        entityCheckBox.setSelected(true);
        entityCheckBox.setText("Entity.cs");

        entityArrayDrawerCheckBox.setSelected(true);
        entityArrayDrawerCheckBox.setText("EntityArrayDrawer.cs");

        entityArrayAttributeCheckBox.setSelected(true);
        entityArrayAttributeCheckBox.setText("EntityArrayAttribute.cs");

        levelGeneratorCheckBox.setSelected(true);
        levelGeneratorCheckBox.setText("LevelGenerator.cs");

        groupEntitiesByTypeCheckBox.setText("Group Entities by Type");

        jLabel2.setText("Include:");

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
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(generateButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(entityCheckBox)
                            .addComponent(entityArrayDrawerCheckBox)
                            .addComponent(entityArrayAttributeCheckBox)
                            .addComponent(levelGeneratorCheckBox)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(groupEntitiesByTypeCheckBox)))
                        .addContainerGap(216, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(entityCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(entityArrayDrawerCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(entityArrayAttributeCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(levelGeneratorCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(groupEntitiesByTypeCheckBox)
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
    private javax.swing.JTextField desitnationFolderTextField;
    private javax.swing.JCheckBox entityArrayAttributeCheckBox;
    private javax.swing.JCheckBox entityArrayDrawerCheckBox;
    private javax.swing.JCheckBox entityCheckBox;
    private javax.swing.JButton generateButton;
    private javax.swing.JCheckBox groupEntitiesByTypeCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JCheckBox levelGeneratorCheckBox;
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

    public JCheckBox getEntityArrayAttributeCheckBox() {
        return entityArrayAttributeCheckBox;
    }

    public JCheckBox getEntityArrayDrawerCheckBox() {
        return entityArrayDrawerCheckBox;
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
    
}
