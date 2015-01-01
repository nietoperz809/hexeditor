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
    Object doIt(File file, FileReader reader);
}

interface SaveFunc
{
    void doIt(FileOutputStream fo);
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
            FileReader reader = new FileReader(file);
            return (String)code.doIt (file, reader);
        }
        return null;
    }

    private static void save(String s1, String s2, Component parent, SaveFunc code) throws Exception
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setApproveButtonText("Save now!");
        chooser.setFileFilter(new FileNameExtensionFilter(s1, s2));
        if (chooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION)
        {
            String name = chooser.getSelectedFile().getAbsolutePath();
            code.doIt(new FileOutputStream(name));
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
        return load("6502 ASM code", "asm", parent, (File file, FileReader reader) ->
        {
            char[] chars = new char[(int) file.length()];
            try
            {
                reader.read(chars);
            }
            catch (IOException ex)
            {
                
            }
            return new String(chars);
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
        save ("6502 ASM code", "asm", parent, (FileOutputStream fo) ->
        {
            PrintStream out = new PrintStream(fo);
            out.print(content);
        });
    }
}
