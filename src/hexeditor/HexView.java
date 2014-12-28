
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexeditor;

import java.awt.Color;
import java.awt.Point;
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
    static final int BOTTOMMARGIN = 8191;
    final int[] memory;

    private int lastKey;
    private final Point CurrentEditorPos = new Point();

    private final KeyListener keyListener = new KeyListener()
    {
        @Override
        public void keyTyped(KeyEvent e)
        {
        }

        @Override
        public void keyPressed(KeyEvent e)
        {
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

    private final NavigationFilter filter = new NavigationFilter()
    {
        int topdetect;
        int downdetect;

        @Override
        public void setDot(NavigationFilter.FilterBypass fb, int dot, Position.Bias bias)
        {
            CurrentEditorPos.x = dot % LINECHARS;
            CurrentEditorPos.y = dot / LINECHARS;

            if (lastKey == KeyEvent.VK_UP && CurrentEditorPos.y == TOPMARGIN)
            {
                topdetect++;
                if (topdetect == 2)
                {
                    CurrentEditorPos.y = BOTTOMMARGIN;
                    topdetect = 0;
                }
            }
            else
            {
                topdetect = 0;
            }

            if (lastKey == KeyEvent.VK_DOWN && CurrentEditorPos.y == BOTTOMMARGIN)
            {
                downdetect++;
                if (downdetect == 2)
                {
                    CurrentEditorPos.y = TOPMARGIN;
                    downdetect = 0;
                }
            }
            else
            {
                downdetect = 0;
            }

            if ((CurrentEditorPos.x + 1) % 3 == 0)
            {
                if (lastKey == KeyEvent.VK_LEFT)
                {
                    CurrentEditorPos.x--;
                }
                else
                {
                    CurrentEditorPos.x++;
                }
            }

            if (CurrentEditorPos.x < LEFTMARGIN)
            {
                CurrentEditorPos.x = RIGHTMARGIN;
                if (CurrentEditorPos.y != 0)
                {
                    CurrentEditorPos.y--;
                }
                else
                {
                    CurrentEditorPos.y = BOTTOMMARGIN;
                }
            }
            else if (CurrentEditorPos.x > RIGHTMARGIN)
            {
                CurrentEditorPos.x = LEFTMARGIN;
                if (CurrentEditorPos.y < BOTTOMMARGIN)
                {
                    CurrentEditorPos.y++;
                }
                else
                {
                    CurrentEditorPos.y = TOPMARGIN;
                }
            }

            fb.setDot(CurrentEditorPos.y * LINECHARS + CurrentEditorPos.x, bias);
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
            for (int s = 0; s < 8192 * 41; s += 41)
            {
                hl.addHighlight(s, s + 5, Painter1);
                hl.addHighlight(s + 32, s + 40, Painter2);
            }
        }
        catch (BadLocationException ex)
        {
            System.out.println(ex);
        }

        this.setNavigationFilter(filter);
        this.addKeyListener(keyListener);
        this.setDocument(plainDoc);
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


    private final Document plainDoc = new PlainDocument()
    {
        @Override
        public void insertString(int offs, String str, AttributeSet a)
                throws BadLocationException
        {
            if (str.length() == 1)
            {
                int n = HexTools.getHexIndex(str.charAt(0));
                if (n == -1)
                {
                    return;
                }

                int xmem = CurrentEditorPos.x / 3 - LEFTMARGIN / 3;
                int xr = CurrentEditorPos.x % 3;
                int memoffset = xmem + CurrentEditorPos.y * 8;

                int charidx = CurrentEditorPos.y * LINECHARS + 32 + xmem;

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

                super.remove(offs, 1);
            }
            super.insertString(offs, str, a);
        }
    };


    private void populate()
    {
        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < memory.length; n += 8)
        {
            sb.append(HexTools.toHex16(n));
            sb.append(':');
            sb.append(' ');
            for (int m = 0; m < 8; m++)
            {
                sb.append(HexTools.toHex8(memory[n + m]));
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
