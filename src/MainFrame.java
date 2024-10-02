import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import library.Password;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainFrame extends JFrame {
    final private Font mainFont = new Font("Segeo print", Font.BOLD, 18);
    final private Font titleFont = new Font("Segeo print", Font.BOLD, 30);
    JCheckBoxMenuItem includesNumberBox, includesSpecialBox, includesUpperBox, includesLowerBox;
    JLabel lbTitle, lbPrompt;

    public void initialize() {
        /************************  CheckBox Panel  ************************/

        includesNumberBox = new JCheckBoxMenuItem("Includes Numbers", true);
        includesNumberBox.setFont(mainFont);
        includesSpecialBox = new JCheckBoxMenuItem("Includes Special Characters", true);
        includesSpecialBox.setFont(mainFont);
        includesUpperBox = new JCheckBoxMenuItem("Includes Upper Characters", true);
        includesUpperBox.setFont(mainFont);
        includesLowerBox = new JCheckBoxMenuItem("Includes Lower Characters", true);
        includesLowerBox.setFont(mainFont);
        
        JPanel CheckBoxPanel = new JPanel();
        CheckBoxPanel.setLayout(new GridLayout(4, 1, 5, 5));
        CheckBoxPanel.setOpaque(false);
        CheckBoxPanel.add(includesNumberBox);
        CheckBoxPanel.add(includesSpecialBox);
        CheckBoxPanel.add(includesUpperBox);
        CheckBoxPanel.add(includesLowerBox);
        CheckBoxPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 10));


         /************************  Title Panel  ************************/

        lbTitle = new JLabel("Password Generator");
        lbTitle.setFont(titleFont);


        /************************  Generator Panel  ************************/

        JLabel lbPrompt = new JLabel();
        lbPrompt.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lbPrompt.setText("password thingy");
        lbPrompt.setFont(mainFont);


        /************************  Scrollable selector  ************************/

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 100, 30);
        JLabel statusLabel = new JLabel();
        statusLabel.setText("The Character limit is 30");

        slider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                // TODO Auto-generated method stub
                statusLabel.setText("The character limit is " + ((JSlider)e.getSource()).getValue());
            }
            
        });

        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new GridLayout(2, 1));
        sliderPanel.setOpaque(false);
        sliderPanel.add(statusLabel);
        sliderPanel.add(slider);

        /************************  Buttons Panel  ************************/

        JButton btnOK = new JButton("OK");
        btnOK.setFont(mainFont);
        btnOK.addActionListener(new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent e) {
                Password password = new Password(slider.getValue(),
                                                includesNumberBox.getState(), 
                                                includesSpecialBox.getState(), 
                                                includesUpperBox.getState(), 
                                                includesLowerBox.getState());
                password.generate();
                lbPrompt.setText(password.getPassword());
            }
                    
        });

        JButton btnCopy = new JButton("Copy");
        btnCopy.setFont(mainFont);
        btnCopy.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                StringSelection stringSelection = new StringSelection(lbPrompt.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }
            
        });
        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2, 1));
        buttonsPanel.setOpaque(false);
        buttonsPanel.add(btnOK);
        buttonsPanel.add(btnCopy);

        /************************  Main Panel  ************************/


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(45, 46, 45));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(CheckBoxPanel, BorderLayout.WEST);
        mainPanel.add(lbTitle, BorderLayout.NORTH);
        mainPanel.add(buttonsPanel, BorderLayout.EAST);
        mainPanel.add(lbPrompt, BorderLayout.CENTER);
        mainPanel.add(sliderPanel, BorderLayout.SOUTH);

        add(mainPanel);
 
        setTitle("Test");
        setSize(getPreferredSize());
        setMinimumSize(new Dimension(800, 400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static void main(String[] args) {
        MainFrame myFrame = new MainFrame();
        myFrame.initialize();
    }
}
