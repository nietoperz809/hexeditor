/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

interface LoadFunc
{
    String doIt(File file);
}

interface SaveFunc
{
    void doIt(String name);
}

/**
 *
 * @author Administrator
 */
public class FileUtility
{
    private static String load(String s1, String s2, Component parent, LoadFunc code) throws Exception
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setApproveButtonText("Get it!");
        chooser.setFileFilter(new FileNameExtensionFilter(s1, s2));
        if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION)
        {
            String name = chooser.getSelectedFile().getAbsolutePath();
            File file = new File(name); //for ex foo.txt
            return code.doIt(file);
        }
        return null;
    }

    private static void save(String s1, String s2, Component parent, String content, SaveFunc code) throws Exception
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setApproveButtonText("Save now!");
        chooser.setFileFilter(new FileNameExtensionFilter(s1, s2));
        if (chooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION)
        {
            String name = chooser.getSelectedFile().getAbsolutePath();
            code.doIt(name);
        }
    }

    /**
     *
     * @param parent
     * @return
     * @throws java.lang.Exception
     */
    public static String loadSource(Component parent) throws Exception
    {
        return load("6502 ASM code", "asm", parent, (File file) ->
        {
            String content = null;
            try (FileReader reader = new FileReader(file))
            {
                char[] chars = new char[(int) file.length()];
                reader.read(chars);
                content = new String(chars);
            }
            catch (IOException ex)
            {
            }
            return content;
        });
    }

    /**
     *
     * @param parent
     * @param content
     * @throws java.lang.Exception
     */
    public static void saveSource(Component parent, String content) throws Exception
    {
        save ("6502 ASM code", "asm", parent, content, (String name) ->
        {
            try (PrintStream out = new PrintStream(new FileOutputStream(name)))
            {
                out.print(content);
            }
            catch (FileNotFoundException ex)
            {
            }
        });
    }
}
