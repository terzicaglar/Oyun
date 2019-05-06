import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

public class Kare extends JComponent
{
  int r;
  int g;
  int b;

  public void paint(Graphics g)
  {
    for (int j = 0; j < 70; ++j)
    {
      for (int i = 0; i < 30; ++i)
      {
        g.setColor(new Color(0, 170 + i, 170 + i));
        g.drawRect(75 + j / 10 * 50 + 1 * i, 84 + j % 10 * 50 + 1 * i, 50 - 2 * i, 50 - 2 * i);
      }
    }
    g.setColor(Color.BLACK);
    for (int i = 0; i < 8; ++i)
      g.drawLine(75 + i * 50, 84, 75 + i * 50, 584);
    for (int i = 0; i < 11; ++i)
      g.drawLine(75, 84 + i * 50, 425, 84 + i * 50);
  }
}