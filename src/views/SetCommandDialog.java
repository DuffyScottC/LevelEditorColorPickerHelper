/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

/**
 *
 * @author Scott
 */
public class SetCommandDialog extends javax.swing.JDialog {

    /**
     * Creates new form SetCommandDialog
     */
    public SetCommandDialog(java.awt.Frame parent, boolean modal) {
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
        enterCommandTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        nameCheckBox = new javax.swing.JCheckBox();
        typeCheckBox = new javax.swing.JCheckBox();
        unityPrefabCheckBox = new javax.swing.JCheckBox();
        colorHexValueCheckBox = new javax.swing.JCheckBox();
        redCheckBox = new javax.swing.JCheckBox();
        greenCheckBox = new javax.swing.JCheckBox();
        blueCheckBox = new javax.swing.JCheckBox();
        exampleTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        setButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Enter Command:");

        jLabel2.setText("Include Entity Attributes:");

        nameCheckBox.setText("Name");

        typeCheckBox.setText("Type");

        unityPrefabCheckBox.setText("Unity Prefab");

        colorHexValueCheckBox.setText("Color Hex Value");

        redCheckBox.setText("Red Value");

        greenCheckBox.setText("Green Value");

        blueCheckBox.setText("Blue Value");

        jLabel3.setText("Example:");

        setButton.setText("Set");

        cancelButton.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(enterCommandTextField)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(blueCheckBox)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(greenCheckBox)
                                    .addComponent(redCheckBox)
                                    .addComponent(colorHexValueCheckBox))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nameCheckBox)
                                    .addComponent(typeCheckBox)
                                    .addComponent(unityPrefabCheckBox)))
                            .addComponent(jLabel3))
                        .addGap(0, 303, Short.MAX_VALUE))
                    .addComponent(exampleTextField)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(setButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enterCommandTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(colorHexValueCheckBox, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nameCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeCheckBox)
                    .addComponent(redCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unityPrefabCheckBox)
                    .addComponent(greenCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(blueCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exampleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(setButton)
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
            java.util.logging.Logger.getLogger(SetCommandDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SetCommandDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SetCommandDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SetCommandDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SetCommandDialog dialog = new SetCommandDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JCheckBox blueCheckBox;
    private javax.swing.JButton cancelButton;
    private javax.swing.JCheckBox colorHexValueCheckBox;
    private javax.swing.JTextField enterCommandTextField;
    private javax.swing.JTextField exampleTextField;
    private javax.swing.JCheckBox greenCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JCheckBox nameCheckBox;
    private javax.swing.JCheckBox redCheckBox;
    private javax.swing.JButton setButton;
    private javax.swing.JCheckBox typeCheckBox;
    private javax.swing.JCheckBox unityPrefabCheckBox;
    // End of variables declaration//GEN-END:variables

    public JCheckBox getBlueCheckBox() {
        return blueCheckBox;
    }

    public JCheckBox getColorHexValueCheckBox() {
        return colorHexValueCheckBox;
    }

    public JTextField getEnterCommandTextField() {
        return enterCommandTextField;
    }

    public JTextField getExampleTextField() {
        return exampleTextField;
    }

    public JCheckBox getGreenCheckBox() {
        return greenCheckBox;
    }

    public JCheckBox getNameCheckBox() {
        return nameCheckBox;
    }

    public JCheckBox getRedCheckBox() {
        return redCheckBox;
    }

    public JCheckBox getTypeCheckBox() {
        return typeCheckBox;
    }

    public JCheckBox getUnityPrefabCheckBox() {
        return unityPrefabCheckBox;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getSetButton() {
        return setButton;
    }
    
}
