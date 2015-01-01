/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Administrator
 */
public class FileUtility
{
    /**
     *
     * @param parent
     * @return
     * @throws java.lang.Exception
     */
    public static String loadSource(Component parent) throws Exception
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setApproveButtonText("Get it!");
        chooser.setFileFilter(new FileNameExtensionFilter("6502 ASM code", "asm"));
        if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION)
        {
            String name = chooser.getSelectedFile().getAbsolutePath();
            File file = new File(name); //for ex foo.txt
            String content;
            try (FileReader reader = new FileReader(file))
            {
                char[] chars = new char[(int) file.length()];
                reader.read(chars);
                content = new String(chars);
            }
            return content;
        }
        return null;
    }

    /**
     *
     * @param parent
     * @param content
     * @throws java.lang.Exception
     */
    public static void saveSource(Component parent, String content) throws Exception
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setApproveButtonText("Save now!");
        chooser.setFileFilter(new FileNameExtensionFilter("6502 ASM code", "asm"));
        if (chooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION)
        {
            String name = chooser.getSelectedFile().getAbsolutePath();
            try (PrintStream out = new PrintStream(new FileOutputStream(name)))
            {
                out.print(content);
            }
        }
    }
}
