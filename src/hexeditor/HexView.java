/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexeditor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextArea;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.NavigationFilter;
import javax.swing.text.PlainDocument;
import javax.swing.text.Position;

/**
 *
 * @author Administrator
 */
public class HexView extends JTextArea
{
    final byte[] memory;
    final int lines;
    final char[] digits =
    {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };
    static final int LINECHARS = 41;
    static final int LEFTMARGIN = 6;
    static final int RIGHTMARGIN = 28;
    static final int TOPMARGIN = 0;
    static final int BOTTOMMARGIN = 8191;
    
    private int lastKey;

    private boolean isHexChar(char c)
    {
        for (char d : digits)
        {
            if (d == Character.toUpperCase(c))
            {
                return true;
            }
        }
        return false;
    }

    KeyListener keyListener = new KeyListener()
    {
        @Override
        public void keyTyped(KeyEvent e)
        {
        }

        @Override
        public void keyPressed(KeyEvent e)
        {
            lastKey = e.getKeyCode();
        }

        @Override
        public void keyReleased(KeyEvent e)
        {
        }
    };
    
    NavigationFilter filter = new NavigationFilter()
    {
        int topdetect;
        int downdetect;
        
        @Override
        public void setDot(NavigationFilter.FilterBypass fb, int dot, Position.Bias bias)
        {
            int x = dot % LINECHARS;
            int y = dot / LINECHARS;

            System.out.println("X:" + x + " Y:" + y);
            System.out.println("movement:" + lastKey);

            if (lastKey == KeyEvent.VK_UP && y == TOPMARGIN)
            {
                topdetect++;
                if (topdetect == 2)
                {
                    y = BOTTOMMARGIN;
                    topdetect = 0;
                }
            }
            else
            {
                topdetect = 0;
            }

            if (lastKey == KeyEvent.VK_DOWN && y == BOTTOMMARGIN)
            {
                downdetect++;
                if (downdetect == 2)
                {
                    y = TOPMARGIN;
                    downdetect = 0;
                }
            }
            else
            {
                downdetect = 0;
            }
            
            if (x < LEFTMARGIN)
            {
                x = RIGHTMARGIN;
            }
            else if (x > RIGHTMARGIN)
            {
                x = LEFTMARGIN;
            }
            
            if ((x + 1) % 3 == 0)
            {
                if (lastKey != KeyEvent.VK_LEFT)
                {
                    x++;
                }
                else
                {
                    x--;
                }
            }

            fb.setDot(y * LINECHARS + x, bias);
        }

        @Override
        public void moveDot(NavigationFilter.FilterBypass fb, int dot, Position.Bias bias)
        {
            System.out.println("Moving: " + dot);
            fb.moveDot(dot, bias);
        }
    };

    private Document createOverwriteDocument()
    {
        Document doc = new PlainDocument()
        {
            @Override
            public void insertString(int offs, String str, AttributeSet a)
                    throws BadLocationException
            {
                System.out.println(str + " " + offs);
                if (str.length() == 1)
                {
                    if (!isHexChar(str.charAt(0)))
                    {
                        return;
                    }

                    super.remove(offs, 1);
                }
                super.insertString(offs, str, a);
            }
        };
        return doc;
    }

    public HexView(byte[] mem)
    {
        super();
        this.setNavigationFilter(filter);
        this.addKeyListener(keyListener);
        this.setDocument(createOverwriteDocument());
        memory = mem;
        lines = mem.length / 8;

        memory[7] = (byte) 'h';
        memory[8] = (byte) 'a';

        pollute();
    }

    String toHex(byte b)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(digits[(b >>> 4) & 0x0f]);
        sb.append(digits[b & 0x0f]);
        return sb.toString();
    }

    private void pollute()
    {
        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < memory.length; n += 8)
        {
            sb.append(toHex((byte) (n >> 8)));
            sb.append(toHex((byte) n));
            sb.append(':');
            sb.append(' ');
            for (int m = 0; m < 8; m++)
            {
                sb.append(toHex(memory[n + m]));
                sb.append(' ');
            }
            sb.append('-');
            sb.append(' ');
            for (int m = 0; m < 8; m++)
            {
                char c = (char) memory[n + m];
                if (Character.isISOControl(c))
                {
                    c = '.';
                }
                sb.append(c);
            }
            sb.append('\n');
        }
        sb.deleteCharAt(sb.length() - 1);
        this.setText(sb.toString());
    }
}
