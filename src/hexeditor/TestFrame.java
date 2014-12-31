/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexeditor;

import javax.swing.JCheckBox;
import main.java.com.pmeade.cpu.pm6502.Cpu6502;
import main.java.com.pmeade.cpu.pm6502.MemoryIO;
import main.java.com.pmeade.cpu.pm6502.PM6502;

/**
 *
 * @author Administrator
 */
public class TestFrame extends javax.swing.JFrame
{
    int[] memory = new int[0x10000];
    Cpu6502 cpu = new PM6502();

    MemoryIO io = new MemoryIO()
    {
        @Override
        public int read(int address)
        {
            return memory[address] & 0xff;
        }

        @Override
        public void write(int address, int data)
        {
            try
            {
                ((HexView) hexView).setByteInMemory(address, data);
            }
            catch (Exception ex)
            {

            }
        }
    };

    /**
     * Creates new form TestFrame
     */
    public TestFrame()
    {
        initComponents();
        cpu.setMemoryIO(io);
        
        checkers = new JCheckBox[]{flagC, flagZ, flagI, flagD, flagB, flagSpare, flagV, flagS};        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        hexView = new HexView(memory);
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textA = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textPC = new javax.swing.JTextField();
        textX = new javax.swing.JTextField();
        textP = new javax.swing.JTextField();
        textY = new javax.swing.JTextField();
        textS = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        disasmText = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        flagS = new javax.swing.JCheckBox();
        flagV = new javax.swing.JCheckBox();
        flagSpare = new javax.swing.JCheckBox();
        flagB = new javax.swing.JCheckBox();
        flagD = new javax.swing.JCheckBox();
        flagI = new javax.swing.JCheckBox();
        flagZ = new javax.swing.JCheckBox();
        flagC = new javax.swing.JCheckBox();
        runButton = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        asmTxt = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(815, 615));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(267, 101));

        hexView.setBackground(new java.awt.Color(0, 102, 102));
        hexView.setColumns(20);
        hexView.setFont(new java.awt.Font("Lucida Console", 1, 18)); // NOI18N
        hexView.setForeground(new java.awt.Color(255, 255, 102));
        hexView.setRows(5);
        jScrollPane1.setViewportView(hexView);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setBackground(new java.awt.Color(255, 204, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("A");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("X");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Y");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("P");

        textA.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("S");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("PC");

        textPC.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        textX.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        textP.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        textY.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        textS.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jButton1.setText("Step");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Compile");
        jButton3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton3ActionPerformed(evt);
            }
        });

        disasmText.setBackground(new java.awt.Color(51, 255, 255));
        disasmText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        disasmText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        disasmText.setOpaque(true);

        flagS.setText("S");
        flagS.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                flagSActionPerformed(evt);
            }
        });

        flagV.setText("V");
        flagV.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                flagVActionPerformed(evt);
            }
        });

        flagSpare.setText("-");
        flagSpare.setEnabled(false);

        flagB.setText("B");
        flagB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                flagBActionPerformed(evt);
            }
        });

        flagD.setText("D");
        flagD.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                flagDActionPerformed(evt);
            }
        });

        flagI.setText("I");
        flagI.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                flagIActionPerformed(evt);
            }
        });

        flagZ.setText("Z");
        flagZ.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                flagZActionPerformed(evt);
            }
        });

        flagC.setText("C");
        flagC.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                flagCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(flagS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(flagV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(flagSpare)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(flagB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(flagD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(flagI)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(flagZ)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(flagC)
                .addGap(44, 44, 44))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(flagS)
                    .addComponent(flagV)
                    .addComponent(flagSpare)
                    .addComponent(flagB)
                    .addComponent(flagD)
                    .addComponent(flagI)
                    .addComponent(flagZ)
                    .addComponent(flagC))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        runButton.setText("Run");
        runButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                runButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textA, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textX, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textY, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textS, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textPC, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(disasmText, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(runButton)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(textX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton1)
                    .addComponent(jLabel5)
                    .addComponent(textS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(textPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(textP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(disasmText, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(runButton))
                .addGap(0, 7, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        asmTxt.setColumns(20);
        asmTxt.setRows(5);
        jScrollPane2.setViewportView(asmTxt);

        getContentPane().add(jScrollPane2, java.awt.BorderLayout.EAST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void displayFlags()
    {
        int i = cpu.getSR();
        flagS.setSelected((i & 0x80) != 0);
        flagV.setSelected((i & 0x40) != 0);
        flagSpare.setSelected((i & 0x20) != 0);
        flagB.setSelected((i & 0x10) != 0);
        flagD.setSelected((i & 0x08) != 0);
        flagI.setSelected((i & 0x04) != 0);
        flagZ.setSelected((i & 0x02) != 0);
        flagC.setSelected((i & 0x01) != 0);
    }

    private int setFlags()
    {
        int i = 0;
        i |= flagS.isSelected() ? 0x80 : 0; 
        i |= flagV.isSelected() ? 0x40 : 0; 
        i |= flagSpare.isSelected() ? 0x20 : 0; 
        i |= flagB.isSelected() ? 0x10 : 0; 
        i |= flagD.isSelected() ? 0x08 : 0; 
        i |= flagI.isSelected() ? 0x04 : 0; 
        i |= flagZ.isSelected() ? 0x02 : 0; 
        i |= flagC.isSelected() ? 0x01 : 0;
        return i;
    }
    
    private void displayRegisters()
    {
        textA.setText(HexTools.toHex8(cpu.getAC()));
        textS.setText(HexTools.toHex8(cpu.getSP()));
        textP.setText(HexTools.toHex8(cpu.getSR()));
        textX.setText(HexTools.toHex8(cpu.getXR()));
        textY.setText(HexTools.toHex8(cpu.getYR()));
        textPC.setText(HexTools.toHex16(cpu.getPC()));
        displayFlags();
    }

    private void read()
    {
        cpu.setAC(HexTools.readHex(textA.getText()));
        cpu.setSP(HexTools.readHex(textS.getText()));
        cpu.setSR(HexTools.readHex(textP.getText()));
        cpu.setXR(HexTools.readHex(textX.getText()));
        cpu.setYR(HexTools.readHex(textY.getText()));
        cpu.setPC(HexTools.readHex(textPC.getText()));
    }
    
    private void disasm()
    {
        int offset = cpu.getPC();
        String txt = DisASM6502.disasm(memory, offset);
        disasmText.setText(txt);
    }

    public synchronized void step()
    {
        read();
        cpu.execute();
        displayRegisters();
        disasm();
    }
    
    public void reset ()
    {
        cpu.reset();
        displayRegisters();
        disasm();
    }
    
    /**
     * Step
     * @param evt 
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        step();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Reset
     * @param evt 
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton2ActionPerformed
    {//GEN-HEADEREND:event_jButton2ActionPerformed
        reset();
    }//GEN-LAST:event_jButton2ActionPerformed

    private int origin = 0;

    /**
     * Compile Button clicked
     *
     * @param evt
     */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton3ActionPerformed
    {//GEN-HEADEREND:event_jButton3ActionPerformed
        Compiler c = new Compiler ((HexView) hexView);
        try
        {
            c.compile(asmTxt.getText());
        }
        catch (Exception e)
        {
            System.out.println (e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed
    
    private void actOnCheckBox (int idx)
    {
        int i = cpu.getSR();
        JCheckBox cb = checkers[idx];
        int val = 1<<idx;
        if (cb.isSelected())
            i |= val;
        else
            i &= ~val;
        cpu.setSR(i);
        displayRegisters();
    }
    
    private void flagSActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_flagSActionPerformed
    {//GEN-HEADEREND:event_flagSActionPerformed
        actOnCheckBox(7);
    }//GEN-LAST:event_flagSActionPerformed

    private void flagVActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_flagVActionPerformed
    {//GEN-HEADEREND:event_flagVActionPerformed
        actOnCheckBox(6);
    }//GEN-LAST:event_flagVActionPerformed

    private void flagBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_flagBActionPerformed
    {//GEN-HEADEREND:event_flagBActionPerformed
        actOnCheckBox(4);
    }//GEN-LAST:event_flagBActionPerformed

    private void flagDActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_flagDActionPerformed
    {//GEN-HEADEREND:event_flagDActionPerformed
        actOnCheckBox(3);
    }//GEN-LAST:event_flagDActionPerformed

    private void flagIActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_flagIActionPerformed
    {//GEN-HEADEREND:event_flagIActionPerformed
        actOnCheckBox(2);
    }//GEN-LAST:event_flagIActionPerformed

    private void flagZActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_flagZActionPerformed
    {//GEN-HEADEREND:event_flagZActionPerformed
        actOnCheckBox(1);
    }//GEN-LAST:event_flagZActionPerformed

    private void flagCActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_flagCActionPerformed
    {//GEN-HEADEREND:event_flagCActionPerformed
        actOnCheckBox(0);
    }//GEN-LAST:event_flagCActionPerformed

    Runnable runner = new Runnable()
    {
       @Override
        public void run()
        {
            step();
        }
    };
    
    Clock clock = new Clock (runner, 100);
    
    private void runButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_runButtonActionPerformed
    {//GEN-HEADEREND:event_runButtonActionPerformed
       if (runButton.isSelected())
       {
           runButton.setText ("Stop");
           reset();
           clock.endPause();
       }
       else
       {
           runButton.setText("Run");
           clock.doPause();
       }
    }//GEN-LAST:event_runButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and displayRegisters the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new TestFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea asmTxt;
    private javax.swing.JLabel disasmText;
    private javax.swing.JCheckBox flagB;
    private javax.swing.JCheckBox flagC;
    private javax.swing.JCheckBox flagD;
    private javax.swing.JCheckBox flagI;
    private javax.swing.JCheckBox flagS;
    private javax.swing.JCheckBox flagSpare;
    private javax.swing.JCheckBox flagV;
    private javax.swing.JCheckBox flagZ;
    private javax.swing.JTextArea hexView;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToggleButton runButton;
    private javax.swing.JTextField textA;
    private javax.swing.JTextField textP;
    private javax.swing.JTextField textPC;
    private javax.swing.JTextField textS;
    private javax.swing.JTextField textX;
    private javax.swing.JTextField textY;
    // End of variables declaration//GEN-END:variables
    
    JCheckBox[] checkers; 
}
