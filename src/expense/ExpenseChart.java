package expense;
import javax.swing.*;
import java.awt.*;
public class ExpenseChart {
public void displayChart(double foodTotal,
double shoppingTotal,
double transportTotal) {
 JFrame frame = new JFrame();
 frame.setTitle("Expense Chart");
 JPanel panel = new JPanel() {
@Override
protected void paintComponent(Graphics g) {
 super.paintComponent(g);
 double[] totals = { foodTotal, shoppingTotal, transportTotal };
String[] categories = {"Food","Shopping","Transport"};
java.awt.Color[] colors = { java.awt.Color.BLUE,java.awt.Color.GREEN,java.awt.Color.ORANGE };
double max = Math.max(foodTotal,Math.max(shoppingTotal, transportTotal));
if (max == 0) {
max = 1;
 }
int baseY = 300;
int chartHeight = 220;
int barWidth = 80;
for (int i = 0; i < 3; i++) {
int x = 90 + (i * 140);
int barHeight = (int) (totals[i] / max * chartHeight);
int y = baseY - barHeight;
g.setColor(colors[i]);
g.fillRect(x, y, barWidth, barHeight);
g.setColor(java.awt.Color.BLACK);
g.drawString(String.valueOf(totals[i]),x + 20,y - 10);
g.drawString( categories[i], x + 10, baseY + 25);
}
  }
};
panel.setPreferredSize(new Dimension(500, 380));
frame.add(panel);
frame.pack();
frame.setLocationRelativeTo(null);
frame.setDefaultCloseOperation(2);
frame.setVisible(true);

    }

}