package Encrypt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainGUI extends JFrame {
    public static void main(String[] args) {
        new MainGUI();
    }

    private String msg = "\n使用说明:\n1,先点击选择文件按钮,在弹出窗口中找到并且选中文件,或者手动输入文件所在绝对路径;" +
            "\n2,输入密码，需要为纯数字，且不要溢出;" +
            "\n3,可以自行勾选“备份原文件”，若选择备份，会在选择文件同目录下生成.backup格式的备份文件,去掉后缀即可恢复，" +
            "\n    否则会被加密后的文件替代;" +
            "\n4,点击加密即可对文件进行加密，会在选中的文件同目录下生成.data格式的加密文件;" +
            "\n    选择.data格式的加密文件输入正确密码，点击解密即可以解密,若密码错误会导致解密结果乱码;";

    private Container container = getContentPane();
    private JLabel fileurlLable = new JLabel("请选择文件或者手动输入文件路径:");
    private JTextField fileurl = new JTextField();
    private JLabel pwdLable = new JLabel("请输入密码(仅纯数字):");
    private JTextField pwd = new JTextField();
    private JLabel result = new JLabel("请选择文件，可点击-说明-按钮查看详细步骤");
    private  JButton selectBtn = new JButton("选择文件");
    private JButton encryptBtn = new JButton("加密");
    private JButton decryptBtn = new JButton("解密");
    private JButton cancelBtn = new JButton("清空");
    private JButton infoBtn = new JButton("说明");

    private JCheckBox checkBox = new JCheckBox("备份原文件");
    private boolean backup = false;

    public MainGUI(){
        setTitle("加解密文件");
        setBounds(600,200,700,300);
        container.setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        setVisible(true);
    }

    private void init(){
        /*输入部分--Center*/
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(null);
        fileurlLable.setBounds(10, 20, 300, 25);
        pwdLable.setBounds(10, 60, 300, 25);
        result.setBounds(10,100,690,100);
        fieldPanel.add(fileurlLable);
        fieldPanel.add(pwdLable);
        fieldPanel.add(result);
        fileurl.setBounds(320, 20, 360, 25);
        pwd.setBounds(220, 60, 320, 25);
        selectBtn.setBounds(220,20,100,25);
        checkBox.setBounds(560,60,100,25);
        fieldPanel.add(checkBox);
        fieldPanel.add(selectBtn);
        fieldPanel.add(fileurl);
        fieldPanel.add(pwd);
        container.add(fieldPanel, "Center");

        /*按钮部分--South*/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(encryptBtn);
        buttonPanel.add(decryptBtn);
        buttonPanel.add(cancelBtn);
        buttonPanel.add(infoBtn);
        container.add(buttonPanel, "South");
        listerner();
    }

    private void listerner() {
        selectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                jfc.showDialog(new JLabel(),"选择");
                File file = jfc.getSelectedFile();
                try {
                    if (file.isDirectory()) {
                        JOptionPane.showMessageDialog(null, "不能选择文件夹!");
                        result.setText("未能选中文件，请重试");
                    } else if (file.isFile()) {
                        fileurl.setText(file.getAbsolutePath());
                        result.setText("已选择：" + jfc.getSelectedFile().getName());
                    }
                }catch (Exception e1){
                    JOptionPane.showMessageDialog(null,"未选择文件");
                }
            }
        });

        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkBox.isSelected())
                    backup = true;
            }
        });

        encryptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = fileurl.getText();
                if (null == input || input.trim().length() == 0||pwd.getText()==null||pwd.getText().trim().length()==0) {
                    JOptionPane.showMessageDialog(null, "路径以及密码不能为空");
                } else {
                    int key = Integer.parseInt(pwd.getText());
                    try {
                        //  Block of code to try
                        result.setText("已经对选中文件加密，加密文件路径为选中的文件同目录下："+encryptor.encrypt(input, key));
                        if(backup==false){
                            encryptor.delete(input);
                        }
                        else {
                            encryptor.backup(input);
                        }
                    } catch (Exception exception) {
                        //  Block of code to handle errors
                        JOptionPane.showMessageDialog(null, "所选择文件不存在或者打开失败！");
                    }
                }
            }
        }
        );
        decryptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = fileurl.getText();
                if (null == input || input.trim().length() == 0||pwd.getText()==null||pwd.getText().trim().length()==0) {
                    JOptionPane.showMessageDialog(null, "路径以及密码不能为空");
                } else {
                    int key = Integer.parseInt(pwd.getText());
                    try {
                        //  Block of code to try
                        result.setText("已经对选中文件解密，解密文件路径为选中的文件同目录下："+encryptor.decryptor(input,key));
                        if(backup==false){
                            encryptor.delete(input);
                        }
                        else {
                            encryptor.backup(input);
                        }
                    } catch (Exception exception) {
                        //  Block of code to handle errors
                        JOptionPane.showMessageDialog(null, "所选择文件不存在或者并非为.data格式加密文件,打开失败！！");
                    }
                }
            }
        });


        /** 清空输入信息 */
        cancelBtn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fileurl.setText("");
                        pwd.setText("");
                        result.setText("请选择文件，可点击-说明-按钮查看详细步骤");
                    }
                });

        //关于本项目的介绍页面：
        infoBtn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null, "网络编程作业二\n可以对文件数据进行加解密，采用了Java的swing来创建交互界面;\n"+msg+"\n\n关于：\n网络201张进华\nemail:2006200014@e.gzhu.edu.cn\nGithub:\nhttps://github.com/myli0724/Encrypt");
                    }
                }
        );
    }
}
