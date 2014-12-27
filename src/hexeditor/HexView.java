/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexeditor;

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

    int old_dot1;
    int old_dot2;

    NavigationFilter filter = new NavigationFilter()
    {
        @Override
        public void setDot(NavigationFilter.FilterBypass fb, int dot, Position.Bias bias)
        {
            old_dot1 = old_dot2;
            old_dot2 = dot;

            int x = dot % 41;
            int y = dot / 41;

            System.out.println("" + x + " - " + y);
            System.out.println(dot - old_dot1);

            if (x < 6)
            {
                x = 28;
                y--;
            }
            else if (x >= 29)
            {
                x = 6;
                y++;
            }

            if (y == lines)
            {
                y = 0;
            }
            else if (y == 0)
            {
                y = lines-1;
            }

            if (dot - old_dot1 > 0)
            {
                if ((x + 1) % 3 == 0)
                {
                    x++;
                }
            }
            else
            {
                if ((x + 1) % 3 == 0)
                {
                    x--;
                }
            }

            dot = y * 41 + x;
            fb.setDot(dot, bias);

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

                String text = this.getText(0, this.getLength());

                if (offs < text.length())
                {
                    super.remove(offs, str.length());
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
        this.setText(sb.toString());
    }
}
