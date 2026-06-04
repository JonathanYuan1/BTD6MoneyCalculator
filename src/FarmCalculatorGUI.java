import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class FarmCalculatorGUI {

    private static ArrayList<BananaFarm> farms = new ArrayList<>();
    public static void main(String[] args){
        JFrame frame = new JFrame("BTD6 Banana Farm Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);

        JPanel panel = new JPanel(new GridLayout(7,2,20,20));

        JTextField topPath = new JTextField();
        JTextField midPath = new JTextField();
        JTextField botPath = new JTextField();
        JTextField start = new JTextField();
        JTextField end = new JTextField();

        JLabel farmCount = new JLabel("Farms: 0");
        JLabel total = new JLabel("Total Income: 0");

        JButton addButton = new JButton("Add Farm");
        JButton calculateButton = new JButton("Calculate");

        panel.add(new JLabel("Top Path:"));
        panel.add(topPath);

        panel.add(new JLabel("Middle Path:"));
        panel.add(midPath);

        panel.add(new JLabel("Bottom Path:"));
        panel.add(botPath);

        panel.add(new JLabel("Starting Round:"));
        panel.add(start);

        panel.add(new JLabel("Ending Round:"));
        panel.add(end);

        panel.add(addButton);
        panel.add(farmCount);

        panel.add(calculateButton);
        panel.add(total);

        addButton.addActionListener(e -> {
            int top = Integer.parseInt(topPath.getText());
            int middle = Integer.parseInt(midPath.getText());
            int bottom = Integer.parseInt(botPath.getText());

            BananaFarm farm = new BananaFarm(top,middle,bottom,true, 0);

            farms.add(farm);
            farmCount.setText("Farms: " + farms.size());
        });

        calculateButton.addActionListener(e -> {
            double income = 0;
            for (BananaFarm farm : farms){
                income += farm.incomePerRound() * (Integer.parseInt(end.getText())-Integer.parseInt(start.getText()));
            }
            total.setText("Total Income: " + income);
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
