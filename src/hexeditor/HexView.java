
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexeditor;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextArea;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.NavigationFilter;
import javax.swing.text.PlainDocument;
import javax.swing.text.Position;

/**
 *
 * @author Administrator
 */
public class HexView extends JTextArea
{
    static final int LINECHARS = 41;
    static final int LEFTMARGIN = 6;
    static final int RIGHTMARGIN = 28;
    static final int TOPMARGIN = 0;
    static final int BOTTOMMARGIN = 8_191;
    final int[] memory;
    final static char[] digits = 
    {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };

    private int lastKey;
    int x;
    int y;

    public static String toHex8 (int in)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(digits[(in>>>4)&0x0f]);
        sb.append(digits[in&0x0f]);
        return sb.toString();
    }

    public static String toHex16 (int in)
    {
        StringBuilder sb = new StringBuilder();
        sb.append (toHex8(in>>8));
        sb.append (toHex8(in));
        return sb.toString();
    }
    
    public static int readHex (String in)
    {
        return Integer.parseInt(in.trim(), 16);
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
            System.out.println (e);
            if (e.getKeyCode() == 8 || e.getKeyCode() == 127)
            {
                e.consume(); // Eat DEL and BS
                return;
            }
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
            x = dot % LINECHARS;
            y = dot / LINECHARS;

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

            if ((x + 1) % 3 == 0)
            {
                if (lastKey == KeyEvent.VK_LEFT)
                {
                    x--;
                }
                else
                {
                    x++;
                }
            }

            if (x < LEFTMARGIN)
            {
                x = RIGHTMARGIN;
                if (y != 0)
                {
                    y--;
                }
                else
                {
                    y = BOTTOMMARGIN;
                }
            }
            else if (x > RIGHTMARGIN)
            {
                x = LEFTMARGIN;
                if (y < BOTTOMMARGIN)
                {
                    y++;
                }
                else
                {
                    y = TOPMARGIN;
                }
            }

            fb.setDot(y * LINECHARS + x, bias);
        }

        @Override
        public void moveDot(NavigationFilter.FilterBypass fb, int dot, Position.Bias bias)
        {
            //fb.moveDot(dot, bias);
            
        }
    };

    public HexView(int[] mem)
    {
        super();
        
        Highlighter.HighlightPainter Painter1 = new DefaultHighlighter.DefaultHighlightPainter(Color.black);
        Highlighter.HighlightPainter Painter2 = new DefaultHighlighter.DefaultHighlightPainter(Color.GRAY);
        try
        {
            Highlighter hl = getHighlighter();
            for (int s=0; s<8192*41; s+=41)
            {
                hl.addHighlight (s, s+5, Painter1);
                hl.addHighlight (s+32, s+40, Painter2);
            }
        }
        catch (BadLocationException ex)
        {
            System.out.println (ex);
        }
        
        this.setNavigationFilter(filter);
        this.addKeyListener(keyListener);
        this.setDocument(createOverwriteDocument());
        memory = mem;
        
        populate();
    }

    private int setHiNibble(int offset, int val)
    {
        int b = memory[offset];
        b = (b & 0x0f) | ((val << 4) & 0xf0);
        memory[offset] = (byte) b;
        return b;
    }

    private int setLoNibble(int offset, int val)
    {
        int b = memory[offset];
        b = (b & 0xf0) | (val & 0x0f);
        memory[offset] = (byte) b;
        return b;
    }

    private int getHexIndex(char c)
    {
        for (int n = 0; n < digits.length; n++)
        {
            if (Character.toUpperCase(c) == digits[n])
            {
                return n;
            }
        }
        return -1;
    }

    private Document createOverwriteDocument()
    {
        Document doc = new PlainDocument()
        {
            @Override
            public void insertString(int offs, String str, AttributeSet a)
                    throws BadLocationException
            {
                if (str.length() == 1)
                {
                    int n = getHexIndex(str.charAt(0));
                    if (n == -1)
                    {
                        return;
                    }

                    int xmem = x / 3 - LEFTMARGIN / 3;
                    int xr = x % 3;
                    int memoffset = xmem + y * 8;

                    int charidx = y * LINECHARS + 32 + xmem;

                    char nval;
                    if (xr == 0)
                    {
                        nval = (char) setHiNibble(memoffset, n);
                    }
                    else
                    {
                        nval = (char) setLoNibble(memoffset, n);
                    }
                    if (Character.isISOControl(nval))
                    {
                        nval = '.';
                    }
                    super.remove(charidx, 1);
                    super.insertString(charidx, "" + nval, a);

                    //System.out.println("X:" + xmem + " Y:" + y + " xr: "+ xr);
                    //System.out.println(memoffset);
                    super.remove(offs, 1);
                }
                super.insertString(offs, str, a);
            }
        };
        return doc;
    }

    String toHex(int b)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(digits[(b >>> 4) & 0x0f]);
        sb.append(digits[b & 0x0f]);
        return sb.toString();
    }

    private void populate()
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
