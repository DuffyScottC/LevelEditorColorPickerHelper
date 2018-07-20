/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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
        alphaSlider = new javax.swing.JSlider();
        jLabel7 = new javax.swing.JLabel();
        alphaSpinner = new javax.swing.JSpinner();
        colorCodeTextField = new javax.swing.JTextField();
        colorLabel = new javax.swing.JLabel();
        imagePanel = new views.ImagePanel();
        applyButton = new javax.swing.JButton();
        revertButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        typeComboBox = new javax.swing.JComboBox<>();
        changeImageButton = new javax.swing.JButton();
        deleteImageButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        colorPanel = new views.ColorPanel();
        offsetPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        xOffsetSpinner = new javax.swing.JSpinner();
        yOffsetSpinner = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        classPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        classComboBox = new javax.swing.JComboBox<>();
        mainMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newProjectMenuItem = new javax.swing.JMenuItem();
        openProjectMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        addEntityMenuItem = new javax.swing.JMenuItem();
        deleteEntityMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        preferencesMenuItem = new javax.swing.JMenuItem();
        viewMenu = new javax.swing.JMenu();
        verticalCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        swapCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        toolsMenu = new javax.swing.JMenu();
        generateScriptsMenuItem = new javax.swing.JMenuItem();
        commandMenu = new javax.swing.JMenu();
        useCommandCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        setCommandMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        alphaSlider.setMaximum(255);
        alphaSlider.setMinimum(1);
        alphaSlider.setValue(255);

        jLabel7.setText("Alpha:");

        javax.swing.GroupLayout sliderPanelLayout = new javax.swing.GroupLayout(sliderPanel);
        sliderPanel.setLayout(sliderPanelLayout);
        sliderPanelLayout.setHorizontalGroup(
            sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sliderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sliderPanelLayout.createSequentialGroup()
                        .addGroup(sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(greenSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                            .addComponent(redSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                            .addComponent(blueSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(redSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                            .addComponent(greenSpinner)
                            .addComponent(blueSpinner)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sliderPanelLayout.createSequentialGroup()
                        .addComponent(alphaSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(alphaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        sliderPanelLayout.setVerticalGroup(
            sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sliderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(redSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(redSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(greenSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(greenSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(blueSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addComponent(blueSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(alphaSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(alphaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addContainerGap())
        );

        colorCodeTextField.setColumns(6);
        colorCodeTextField.setText("00000000");

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

        jLabel4.setText("Type:");

        typeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        changeImageButton.setText("Change");

        deleteImageButton.setText("Delete");

        jLabel6.setText("Image:");

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

        jLabel9.setText("x:");

        jLabel8.setText("y:");

        jLabel10.setText("Offset:");

        javax.swing.GroupLayout offsetPanelLayout = new javax.swing.GroupLayout(offsetPanel);
        offsetPanel.setLayout(offsetPanelLayout);
        offsetPanelLayout.setHorizontalGroup(
            offsetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, offsetPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(xOffsetSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(yOffsetSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        offsetPanelLayout.setVerticalGroup(
            offsetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(offsetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(yOffsetSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(xOffsetSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel8)
                .addComponent(jLabel9)
                .addComponent(jLabel10))
        );

        jLabel5.setText("Class:");

        classComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GameObject", "Tile" }));

        javax.swing.GroupLayout classPanelLayout = new javax.swing.GroupLayout(classPanel);
        classPanel.setLayout(classPanelLayout);
        classPanelLayout.setHorizontalGroup(
            classPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(classPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(classComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        classPanelLayout.setVerticalGroup(
            classPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(classPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel5)
                .addComponent(classComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout infoPanelLayout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sliderPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addComponent(nameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameTextField))
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(typeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(colorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(revertButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(applyButton))
                    .addComponent(classPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(selectButton)
                            .addGroup(infoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(changeImageButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteImageButton))
                            .addGroup(infoPanelLayout.createSequentialGroup()
                                .addComponent(colorLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(colorCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(offsetPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(changeImageButton)
                    .addComponent(deleteImageButton)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(colorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(colorLabel)
                    .addComponent(colorCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sliderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(classPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(offsetPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(applyButton)
                    .addComponent(revertButton))
                .addContainerGap())
        );

        mainSplitPane.setRightComponent(infoPanel);

        getContentPane().add(mainSplitPane, java.awt.BorderLayout.CENTER);

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
        fileMenu.add(jSeparator2);

        preferencesMenuItem.setText("Preferences");
        fileMenu.add(preferencesMenuItem);

        mainMenuBar.add(fileMenu);

        viewMenu.setText("View");

        verticalCheckBoxMenuItem.setText("Vertical Layout");
        viewMenu.add(verticalCheckBoxMenuItem);

        swapCheckBoxMenuItem.setText("Swap Panels");
        viewMenu.add(swapCheckBoxMenuItem);

        mainMenuBar.add(viewMenu);

        toolsMenu.setText("Tools");

        generateScriptsMenuItem.setText("Generate Scripts");
        toolsMenu.add(generateScriptsMenuItem);

        commandMenu.setText("Command");

        useCommandCheckBoxMenuItem.setText("Use Command");
        commandMenu.add(useCommandCheckBoxMenuItem);

        setCommandMenuItem.setText("Set Command");
        commandMenu.add(setCommandMenuItem);

        toolsMenu.add(commandMenu);

        mainMenuBar.add(toolsMenu);

        setJMenuBar(mainMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addEntityButton;
    private javax.swing.JMenuItem addEntityMenuItem;
    private javax.swing.JSlider alphaSlider;
    private javax.swing.JSpinner alphaSpinner;
    private javax.swing.JButton applyButton;
    private javax.swing.JSlider blueSlider;
    private javax.swing.JSpinner blueSpinner;
    private javax.swing.JButton changeImageButton;
    private javax.swing.JComboBox<String> classComboBox;
    private javax.swing.JPanel classPanel;
    private javax.swing.JTextField colorCodeTextField;
    private javax.swing.JLabel colorLabel;
    private views.ColorPanel colorPanel;
    private javax.swing.JMenu commandMenu;
    private javax.swing.JMenuItem deleteEntityMenuItem;
    private javax.swing.JButton deleteImageButton;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem generateScriptsMenuItem;
    private javax.swing.JSlider greenSlider;
    private javax.swing.JSpinner greenSpinner;
    private views.ImagePanel imagePanel;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JSplitPane listsSplitPane;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JSplitPane mainSplitPane;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JMenuItem newProjectMenuItem;
    private javax.swing.JPanel offsetPanel;
    private javax.swing.JMenuItem openProjectMenuItem;
    private javax.swing.JMenuItem preferencesMenuItem;
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
    private javax.swing.JMenuItem setCommandMenuItem;
    private javax.swing.JPanel sliderPanel;
    private javax.swing.JCheckBoxMenuItem swapCheckBoxMenuItem;
    private javax.swing.JMenu toolsMenu;
    private javax.swing.JComboBox<String> typeComboBox;
    private javax.swing.JLabel typeLabel;
    private javax.swing.JPanel typePanel;
    private javax.swing.JCheckBoxMenuItem useCommandCheckBoxMenuItem;
    private javax.swing.JCheckBoxMenuItem verticalCheckBoxMenuItem;
    private javax.swing.JMenu viewMenu;
    private javax.swing.JSpinner xOffsetSpinner;
    private javax.swing.JSpinner yOffsetSpinner;
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

    public ColorPanel getColorPanel() {
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

    public JComboBox getClassComboBox() {
        return classComboBox;
    }

    public JComboBox<String> getSearchModeComboBox() {
        return searchModeComboBox;
    }

    public JButton getDeleteImageButton() {
        return deleteImageButton;
    }

    public JSplitPane getListsSplitPane() {
        return listsSplitPane;
    }

    public JMenu getFileMenu() {
        return fileMenu;
    }

    public JMenuItem getSwapCheckBoxMenuItem() {
        return swapCheckBoxMenuItem;
    }

    public JMenuItem getVerticalCheckBoxMenuItem() {
        return verticalCheckBoxMenuItem;
    }

    public JPanel getInfoPanel() {
        return infoPanel;
    }

    public JPopupMenu.Separator getjSeparator1() {
        return jSeparator1;
    }

    public JPanel getSelectionPanel() {
        return selectionPanel;
    }

    public JMenuItem getSetCommandMenuItem() {
        return setCommandMenuItem;
    }

    public JCheckBoxMenuItem getUseCommandCheckBoxMenuItem() {
        return useCommandCheckBoxMenuItem;
    }

    public JMenu getCommandMenu() {
        return commandMenu;
    }

    public JMenuItem getGenerateScriptsMenuItem() {
        return generateScriptsMenuItem;
    }

    public JSlider getAlphaSlider() {
        return alphaSlider;
    }

    public JSpinner getAlphaSpinner() {
        return alphaSpinner;
    }

    public JSpinner getxOffsetSpinner() {
        return xOffsetSpinner;
    }

    public JSpinner getyOffsetSpinner() {
        return yOffsetSpinner;
    }

    public JPanel getOffsetPanel() {
        return offsetPanel;
    }

    public JMenuItem getPreferencesMenuItem() {
        return preferencesMenuItem;
    }

    public JPanel getClassPanel() {
        return classPanel;
    }

    public JMenuBar getMainMenuBar() {
        return mainMenuBar;
    }
    
}
