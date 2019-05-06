import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Oyun extends JFrame
  implements KeyListener, Runnable, MouseListener
{
  JLabel[] l;
  int kareLength;
  int yer;
  int fark;
  int decrease;
  Thread t;
  String iconName;
  ImageIcon[] icons;
  boolean isSpace;
  boolean keyPressed;
  Random r;
  Kare kare;
  static Oyun o;
  boolean direction = true; boolean exit = false;
  int k = 0; int delay = 80;

  public Oyun() {
    this.keyPressed = true;
    this.kare = new Kare();
    this.decrease = 0;
    this.fark = 0;
    this.yer = 0;
    int i = 0;
    this.isSpace = false;
    this.icons = new ImageIcon[3];
    for (i = 0; i < 3; ++i)
      this.icons[i] = new ImageIcon("C:\\temp/img/kare" + (i + 1) + ".gif");
    this.t = new Thread(this);
    this.l = new JLabel[10];
    for (i = 0; i < 10; ++i)
      this.l[i] = new JLabel();
    this.l[0].setIcon(this.icons[2]);
    setKareLength(0);
    this.l[0].setBounds(126, 534, 150, 50);

    setTitle("Oyun");
    setDefaultCloseOperation(3);
    setResizable(false);
    setBounds(370, 40, 500, 700);
    for (i = 0; i < 10; ++i)
      add(this.l[i]);
    add(this.kare);

    repaint();
    addKeyListener(this);
    addMouseListener(this);
    setVisible(true);

    this.t.start();
  }

  private void setKareLength(int i)
  {
    this.iconName = this.l[i].getIcon().toString();
    this.iconName = ""+this.iconName.charAt(16);
    this.kareLength = Integer.parseInt(this.iconName);
  }
  private int getKareLength(int i) {
    this.iconName = this.l[i].getIcon().toString();
    this.iconName = ""+this.iconName.charAt(16);
    return Integer.parseInt(this.iconName);
  }

  public static void main(String[] args)
  {
    o = new Oyun();
   // new AePlayWave("C:\\temp/sound/low.wav").start();
    Thread.currentThread();
    while (true)
    {
      try {
        Thread.sleep(209000L);
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
      //new AePlayWave("C:\\temp/sound/low.wav").start();
    }
  }

  public void keyPressed(KeyEvent e)
  {
    this.isSpace = true;
  }

  public void keyReleased(KeyEvent e)
  {
  }

  public void keyTyped(KeyEvent e)
  {
  }

  public void initialize()
  {
    this.l[0].setText("");
    this.l[1].setText("");
    this.l[2].setText("");
    this.l[1].setBounds(76, 484, 150, 50);
    for (int i = 0; i < this.l.length; ++i)
    {
      this.l[i].setIcon(null);
      this.l[i].setVisible(true);
    }
    this.l[0].setBounds(126, 534, 150, 50);
    this.l[0].setIcon(this.icons[2]);
    setKareLength(0);
    this.keyPressed = false;
    this.direction = true;
    this.exit = false;
    this.k = 0;
    this.delay = 80;
    this.decrease = 0;
    this.fark = 0;
    this.yer = 0;
    this.isSpace = false;
  }

  public void run() {
    Thread.currentThread();

    while (this.keyPressed)
    {
      initialize();
      while (!this.exit)
      {
        this.yer = 0;
        for (int i = 0; i < 5 - this.kareLength + 1; ++i)
        {
          if (this.isSpace)
          {
            if (this.k > 0)
            {
              boolean b1 = this.l[this.k].getX() < this.l[(this.k - 1)].getX();
              boolean b2 = this.l[this.k].getX() + 50 * getKareLength(this.k) > this.l[(this.k - 1)].getX() + 50 * getKareLength(this.k - 1);
              if ((b1) || (b2))
              {
                if (b1)
                  this.fark = ((this.l[(this.k - 1)].getX() - this.l[this.k].getX()) / 50);
                else {
                  this.fark = ((this.l[this.k].getX() + 50 * getKareLength(this.k) - (this.l[(this.k - 1)].getX() + 50 * getKareLength(this.k - 1))) / 50);
                }
                if (this.fark >= this.kareLength)
                {
                  //new AePlayWave("C:\\temp/sound/haha.wav").start();
                  try {
                    Thread.sleep(1000L);
                  }
                  catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                  for (i = 0; i < this.l.length; ++i)
                    this.l[i].setVisible(false);
                  gameOver();
                  try {
                    Thread.sleep(1000L);
                  }
                  catch (InterruptedException e1) {
                    e1.printStackTrace();
                  }
                  this.isSpace = false;
                  while (!this.isSpace);
                  this.keyPressed = true;
                  this.exit = true;
                  break;
                }

                //new AePlayWave("C:\\temp/sound/doh.wav").start();
                try {
                  Thread.sleep(1000L);
                }
                catch (InterruptedException e) {
                  e.printStackTrace();
                }
                this.l[this.k].setIcon(this.icons[(this.kareLength - this.fark - 1)]);
                if (b1)
                  this.l[this.k].setBounds(this.l[this.k].getX() + 50 * this.fark, this.l[this.k].getY(), this.l[this.k].getWidth(), this.l[this.k].getHeight());
                this.decrease += this.fark;
              }

              if ((this.k == 2) && (this.decrease == 1))
                this.decrease = 0;
            }
            this.k += 1;
            if (this.k == 10)
            {
              this.exit = true;
              break;
            }
            this.isSpace = false;
            if (this.k < 3)
            {
              this.l[this.k].setIcon(this.icons[(2 - this.decrease)]);
            }
            else if (this.k < 6)
            {
              if (this.decrease > 1)
                this.decrease = 1;
              this.l[this.k].setIcon(this.icons[(1 - this.decrease)]);
              this.delay = 65;
            }
            else
            {
              this.l[this.k].setIcon(this.icons[0]);
              if (this.k < 8)
                this.delay = 55;
              else if (this.k < 9)
                this.delay = 50;
              else
                this.delay = 45;
            }
            setKareLength(this.k);
            this.direction = true;
          }
          try {
            Thread.sleep(this.delay);
          }
          catch (InterruptedException e) {
            e.printStackTrace();
          }
          if (this.direction)
          {
            this.l[this.k].setBounds(176 + 50 * i, 534 - 50 * this.k, 50 * this.kareLength, 50);
          }
          else
          {
            this.l[this.k].setBounds(326 - this.kareLength * 50 - 50 * i, 534 - 50 * this.k, 50 * this.kareLength, 50);
          }
        }

        this.direction = (!this.direction);
        /*if (this.k == 10)
          new AePlayWave("C:\\temp/sound/woohoo.wav").start();*/
      }
    }
  }

  public void gameOver() {
    for (int i = 0; i < 3; ++i)
    {
      this.l[i].setIcon(null);
      if (i == 0)
        this.l[i].setText("GAME");
      else if (i == 1)
        this.l[i].setText("OVER");
      else
        this.l[i].setText("...To start press any key");
      if (i < 2)
        this.l[i].setFont(new Font("Comic Sans MS", 1, 80));
      else
        this.l[i].setFont(new Font("Comic Sans MS", 1, 20));
      if (i < 2)
        this.l[i].setForeground(new Color(35, 0, 255));
      else
        this.l[i].setForeground(new Color(200, 10, 10));
      if (i == 0)
        this.l[i].setBounds(76, 200, 500, 100);
      else if (i == 1)
        this.l[i].setBounds(200, 300, 500, 100);
      else
        this.l[i].setBounds(150, 375, 300, 100);
      this.l[i].setVisible(true);
    }
    //new AePlayWave("C:\\temp/sound/anykey.wav").start();
    try {
      Thread.sleep(3000L);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void mouseClicked(MouseEvent e)
  {
    this.isSpace = true;
  }

  public void mouseEntered(MouseEvent e)
  {
  }

  public void mouseExited(MouseEvent e)
  {
  }

  public void mousePressed(MouseEvent e)
  {
  }

  public void mouseReleased(MouseEvent e)
  {
  }
}