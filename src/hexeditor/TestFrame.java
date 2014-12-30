/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexeditor;

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

        jButton3.setText("Complie");
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
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textX, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textS, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textPC, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textY, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(disasmText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(jButton3))
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
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(textPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(disasmText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        asmTxt.setColumns(20);
        asmTxt.setRows(5);
        jScrollPane2.setViewportView(asmTxt);

        getContentPane().add(jScrollPane2, java.awt.BorderLayout.EAST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void display()
    {
        textA.setText(HexTools.toHex8(cpu.getAC()));
        textS.setText(HexTools.toHex8(cpu.getSP()));
        textP.setText(HexTools.toHex8(cpu.getSR()));
        textX.setText(HexTools.toHex8(cpu.getXR()));
        textY.setText(HexTools.toHex8(cpu.getYR()));
        textPC.setText(HexTools.toHex16(cpu.getPC()));
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

    /**
     * Step
     * @param evt 
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        read();
        cpu.execute();
        display();
        disasm();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Reset
     * @param evt 
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton2ActionPerformed
    {//GEN-HEADEREND:event_jButton2ActionPerformed
        cpu.reset();
        display();
        disasm();
    }//GEN-LAST:event_jButton2ActionPerformed

    private int origin = 0;

    /**
     * Compile Button clicked
     *
     * @param evt
     */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton3ActionPerformed
    {//GEN-HEADEREND:event_jButton3ActionPerformed
        origin = 0;
        String[] txt = asmTxt.getText().split("\n");
        out:
        for (String txt1 : txt)
        {
            int sep = txt1.indexOf(' ');
            String cmd;
            String args = null;
            if (sep == -1)
            {
                cmd = txt1;
            }
            else
            {
                cmd = txt1.substring(0, sep).trim().toUpperCase();
                args = txt1.substring(sep).replaceAll("\\s", "").toUpperCase();
            }
            
            if (cmd.isEmpty())
                continue;
            
            int val;
            switch (cmd)
            {
                case ".ORG":
                    val = HexTools.readHex6502(args);
                    if (val == -1)
                    {
                        System.out.println("wrong arg");
                        break out;
                    }
                    origin = val;
                    break;

                case ".BYT":
                    String[] bytes = args.split(",");
                    for (String byte1 : bytes)
                    {
                        val = HexTools.readHex6502Byte(byte1);
                        if (val == -1)
                        {
                            System.out.println("err in bytes");
                            break out;
                        }
                        try
                        {
                            ((HexView) hexView).setByteInMemory(origin, val);
                            origin++;
                        }
                        catch (Exception ex)
                        {
                            System.out.println("Byte inser err");
                            break out;
                        }
                    }
                    break;

                default:
                {
                    try
                    {
                        ASM6502 p = ASM6502.parse(cmd, args);
                        ((HexView) hexView).setByteInMemory(origin, p.parsed_instruction);
                        origin++;
                        if (p.parsed_length == 3)
                        {
                            ((HexView) hexView).setByteInMemory(origin, p.parsed_operand);    
                            origin++;
                            ((HexView) hexView).setByteInMemory(origin, p.parsed_operand>>>8);    
                            origin++;
                        }
                        if (p.parsed_length == 2)
                        {
                            ((HexView) hexView).setByteInMemory(origin, p.parsed_operand);    
                            origin++;
                        }
                    }
                    catch (Exception ex)
                    {
                        System.out.println (ex);
                        break out;
                    }
                }
                break;
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

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

        /* Create and display the form */
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField textA;
    private javax.swing.JTextField textP;
    private javax.swing.JTextField textPC;
    private javax.swing.JTextField textS;
    private javax.swing.JTextField textX;
    private javax.swing.JTextField textY;
    // End of variables declaration//GEN-END:variables
}
