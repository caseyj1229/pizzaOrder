import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PizzaOrder extends JFrame
        implements ActionListener, ItemListener
{
    private JPanel panel1, panel2, panel3;
    private JRadioButton small, medium, large, extra_large;
    private JTextField order;
    private JLabel toppings;
    private JCheckBox pepperoni, sausage, peppers, extra_cheese;
    private String size_picked = "Small ";
    private String toppings_picked = "";
    private int toppingCount = 4;
    private String mystr1 = " with ";
   // private ButtonGroup sizes;
    private JButton order_placer;
    protected double smcost = 8.25;
    protected double mdcost = 10.50;
    protected double lgcost = 12.75;
    protected double elcost = 14.00;
    protected double toppingcost = .75;
    protected int toppingcnt;
    protected double total;

    public PizzaOrder()
    {

        super("Pizza Order");


        setLayout(new FlowLayout());


        order = new JTextField("Small Pizza",30);
        order.setEditable(false);

        ButtonGroup pizzaSizes = new ButtonGroup();
        small = new JRadioButton("Small Pizza", true);
        small.setForeground(Color.black);

        medium = new JRadioButton("Medium Pizza", false);
        medium.setForeground(Color.black);

        large = new JRadioButton("Large Pizza", false);
        large.setForeground(Color.black);

        extra_large = new JRadioButton("Extra Large Pizza", false);
        extra_large.setForeground(Color.black);

        pizzaSizes.add(small);
        pizzaSizes.add(medium);
        pizzaSizes.add(large);
        pizzaSizes.add(extra_large);

        JPanel panel1 = new JPanel();
        panel1.add(small);
        panel1.add(medium);
        panel1.add(large);
        panel1.add(extra_large);
        small.addActionListener(this);
        medium.addActionListener(this);
        large.addActionListener(this);
        extra_large.addActionListener(this);

        JPanel panel3 = new JPanel();
        //JLabel tops = new JLabel("Toppings ($.75 each)");
        pepperoni = new JCheckBox("Pepperoni");
        sausage = new JCheckBox("Sausage");
        peppers = new JCheckBox("Peppers");
        extra_cheese = new JCheckBox("Extra Cheese");

        add(small);
        add(medium);
        add(large);
        add(extra_large);
        add(order);

        add(pepperoni);
        add(sausage);
        add(peppers);
        add(extra_cheese);
        pepperoni.addItemListener(this);
        sausage.addItemListener(this);
        peppers.addItemListener(this);
        extra_cheese.addItemListener(this);


        order_placer = new JButton("Place Order");
        order_placer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double orderPrice = cost();

                order.setText("The total is: " + Double.toString(orderPrice));
            }
        });
        add(order_placer);

        setSize(375, 250);
        setVisible(true);
    }
    public void itemStateChanged(ItemEvent e)
    {
        toppings_picked = "";
        if (pepperoni.isSelected())
            toppings_picked += " Pepperoni ";
        if (sausage.isSelected())
            toppings_picked += " Sausage ";
        if (peppers.isSelected())
            toppings_picked += " Peppers ";
        if (extra_cheese.isSelected())
            toppings_picked += " Extra Cheese ";
        order.setText(size_picked + mystr1 +toppings_picked);
    }


    public double cost()
    {
        double total = 0;
        ArrayList<JCheckBox> tops = new ArrayList<>();
        tops.add(pepperoni);
        tops.add(sausage);
        tops.add(peppers);
        tops.add(extra_cheese);
        for(JCheckBox topping : tops) {
            if(topping.isSelected()){
                toppingcnt++;
            }
        }
        total += (toppingcnt * 0.75);
        toppingcnt = 0;
        if(small.isSelected()) {
            total += smcost;
        }
        else if(medium.isSelected()) {
            total += mdcost;
        }
        else if(large.isSelected()) {
            total += lgcost;
        }
        else{
            total += elcost;
        }
        return total;
    }

    public void actionPerformed(ActionEvent e)
    {
        if	   (e.getSource() == small ||
                e.getSource() == medium ||
                e.getSource() == large ||
                e.getSource() == extra_large)
        {
            size_picked = e.getActionCommand();
            order.setText(size_picked + mystr1 + toppings_picked);
        }

    }

    public static void main(String args[])
    {
        PizzaOrder myOrder = new PizzaOrder();

    }
}