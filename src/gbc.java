import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
public class gbc {
   public static void main(String[] args) {
      JFrame frame = new JFrame("Demo Frame");
      JPanel panel = new JPanel();
      GridBagLayout layout = new GridBagLayout();
      panel.setLayout(layout);
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.fill = GridBagConstraints.NONE;
      JComboBox cb = new JComboBox<>();
      cb.setPreferredSize(new Dimension(100,100));
      cb.setMinimumSize(cb.getPreferredSize());
      JButton button1 = new JButton("Hello");
      button1.setPreferredSize(new Dimension(100,100));
      button1.setMinimumSize(cb.getPreferredSize());
      JButton button2 = new JButton("Hello");
      button2.setPreferredSize(new Dimension(100,100));
      button2.setMinimumSize(cb.getPreferredSize());
      JButton button3 = new JButton("Hello");
      JButton button4 = new JButton("Hello");
      gbc.anchor = GridBagConstraints.FIRST_LINE_START;
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.insets = new Insets(50, 0, 0, 0);
      gbc.weighty = 50;
      gbc.weightx = 10;
      layout.setConstraints(cb, gbc);
    //   gbc.ipadx = 50;
      panel.add(cb);
        gbc.gridx = 1;
      gbc.gridy = 0;
      gbc.weightx = 100;
      layout.setConstraints(button1, gbc);
      panel.add(button1);
      gbc.gridx = 2;
      gbc.gridy = 0;
      gbc.weightx = 10;
      layout.setConstraints(button2, gbc);
      panel.add(button2);
      gbc.gridx = 3;
      gbc.gridy = 0;
      gbc.weightx = 10;
      layout.setConstraints(button3, gbc);
      panel.add(button3);
      layout.setConstraints(button4, gbc);
      panel.add(button4);
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      frame.add(panel);
      frame.setSize(550, 400);
      frame.setMinimumSize(new Dimension(300,400));
      frame.setVisible(true);
   }
}