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
        xOffsetCheckBox = new javax.swing.JCheckBox();
        yOffsetCheckBox = new javax.swing.JCheckBox();
        alphaCheckBox = new javax.swing.JCheckBox();
        alphaInHexCheckBox = new javax.swing.JCheckBox();

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

        xOffsetCheckBox.setText("Offset X");

        yOffsetCheckBox.setText("Offset Y");

        alphaCheckBox.setText("Alpha Value");

        alphaInHexCheckBox.setText("Alpha In Hex");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(enterCommandTextField)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 315, Short.MAX_VALUE)
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(setButton))
                    .addComponent(exampleTextField)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(greenCheckBox)
                                    .addComponent(colorHexValueCheckBox)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(alphaInHexCheckBox))
                                    .addComponent(redCheckBox))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(typeCheckBox)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(nameCheckBox)
                                            .addComponent(blueCheckBox)
                                            .addComponent(alphaCheckBox))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(xOffsetCheckBox)
                                            .addComponent(yOffsetCheckBox)
                                            .addComponent(unityPrefabCheckBox))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enterCommandTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(colorHexValueCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(alphaInHexCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(redCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(greenCheckBox)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exampleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(setButton)
                            .addComponent(cancelButton)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(blueCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(alphaCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(typeCheckBox))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(unityPrefabCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(xOffsetCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(yOffsetCheckBox)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox alphaCheckBox;
    private javax.swing.JCheckBox alphaInHexCheckBox;
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
    private javax.swing.JCheckBox xOffsetCheckBox;
    private javax.swing.JCheckBox yOffsetCheckBox;
    // End of variables declaration//GEN-END:variables

    public JCheckBox getBlueCheckBox() {
        return blueCheckBox;
    }

    public JCheckBox getAlphaCheckBox() {
        return alphaCheckBox;
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

    public JCheckBox getxOffsetCheckBox() {
        return xOffsetCheckBox;
    }

    public JCheckBox getyOffsetCheckBox() {
        return yOffsetCheckBox;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getSetButton() {
        return setButton;
    }

    public JCheckBox getAlphaInHexCheckBox() {
        return alphaInHexCheckBox;
    }
    
}
