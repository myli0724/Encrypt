package Encrypt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    public static void main(String[] args) {
        new GUI();
    }

    private Container container = getContentPane();
    private JLabel inputlabel = new JLabel("请输入文件路径:");
    private JTextField textField = new JTextField();
    private JLabel ResultLabel = new JLabel("加密文件路径");
    private JTextField resultfiled = new JTextField();
    private JButton encryptBtn = new JButton("加密");
    private JButton decryptBtn = new JButton("解密");
    private JButton cancelBtn = new JButton("清空");
    private JButton ifoBtn = new JButton("关于");

    public GUI() {
        setTitle("加密数据");
        // 设计窗体大小
        setBounds(600, 200, 500, 180);
        // 添加一块桌布
        container.setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 初始化窗口
        init();
        // 设计窗口可见
        setVisible(true);
    }

    private void init() {
        /*输入部分--Center*/
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(null);
        inputlabel.setBounds(10, 20, 200, 25);
        ResultLabel.setBounds(10, 60, 200, 25);
        fieldPanel.add(inputlabel);
        fieldPanel.add(ResultLabel);
        textField.setBounds(180, 20, 280, 25);
        resultfiled.setBounds(180, 60, 280, 25);
        fieldPanel.add(textField);
        fieldPanel.add(resultfiled);
        container.add(fieldPanel, "Center");

        /*按钮部分--South*/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(encryptBtn);
        buttonPanel.add(decryptBtn);
        buttonPanel.add(cancelBtn);
        buttonPanel.add(ifoBtn);
        container.add(buttonPanel, "South");
        listerner();
    }

    public void listerner() {
        encryptBtn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String input = textField.getText();
                        if (null == input || input.trim().length() == 0) {
                            JOptionPane.showMessageDialog(null, "输入不能为空");
                        } else {
                            try {
                                //  Block of code to try
                                resultfiled.setText(encryptor.encrypt(input, 10086));
                            } catch (Exception exception) {
                                //  Block of code to handle errors
                                JOptionPane.showMessageDialog(null, "输入格式有误，请重新输入！");
                            }
                        }
                    }
                });
        /** 清空输入信息 */
        cancelBtn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        textField.setText("");
                        resultfiled.setText("");
                    }
                });

        decryptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = resultfiled.getText();
                if (null == input || input.trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "输入不能为空");
                } else {
                    try {
                        //  Block of code to try
                        encryptor.decryptor(input, 10086);
                    } catch (Exception exception) {
                        //  Block of code to handle errors
                        JOptionPane.showMessageDialog(null, "输入格式有误，请重新输入！");
                    }
                }
            }
        });

        //关于本项目的介绍页面：
        ifoBtn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null, "网络编程作业二\n一个转换十进制小数的GUI程序，\n整数部分和小数部分分别逆序转换，\n支持正负数，采用了Java的swing来创建交互界面;\n关于：网络201张进华\nemail:2006200014@e.gzhu.edu.cn\nGithub:\nhttps://github.com/myli0724/Reverse");
                    }
                }
        );
    }
}
