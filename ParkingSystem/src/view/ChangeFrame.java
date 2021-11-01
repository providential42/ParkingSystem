package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Util.OperationDB;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ChangeFrame extends JFrame {

	private JPanel contentPane;
	private JTextField passWordTxt;
	private JTextField nameTxt;
	private JTextField phoneTxt;
	OperationDB op;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangeFrame frame = new ChangeFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChangeFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ChangeFrame.class.getResource("/image/\u6C7D\u8F66.png")));
		setTitle("\u4FE1\u606F\u4FEE\u6539");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 260);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u5BC6\u7801");
		lblNewLabel.setForeground(new Color(65, 105, 225));
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 24, 56, 26);
		contentPane.add(lblNewLabel);
		
		passWordTxt = new JTextField();
		passWordTxt.setBounds(76, 26, 114, 25);
		contentPane.add(passWordTxt);
		passWordTxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u59D3\u540D");
		lblNewLabel_1.setForeground(new Color(65, 105, 225));
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 80, 56, 26);
		contentPane.add(lblNewLabel_1);
		
		nameTxt = new JTextField();
		nameTxt.setColumns(10);
		nameTxt.setBounds(76, 80, 114, 25);
		contentPane.add(nameTxt);
		
		JLabel lblNewLabel_1_1 = new JLabel("\u624B\u673A\u53F7");
		lblNewLabel_1_1.setForeground(new Color(65, 105, 225));
		lblNewLabel_1_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 128, 56, 26);
		contentPane.add(lblNewLabel_1_1);
		
		phoneTxt = new JTextField();
		phoneTxt.setColumns(10);
		phoneTxt.setBounds(76, 130, 114, 25);
		contentPane.add(phoneTxt);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(ChangeFrame.class.getResource("/image/\u6C7D\u8F66\u4FE1\u606F.png")));
		lblNewLabel_2.setBounds(245, 24, 64, 83);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("\u4FEE\u6539");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeActionPerformed(e);
			}
		});
		btnNewButton.setBackground(new Color(135, 206, 250));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNewButton.setBounds(224, 130, 97, 23);
		contentPane.add(btnNewButton);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	private void changeActionPerformed(ActionEvent e) {
		String newname = this.nameTxt.getText();
		String newphone = this.phoneTxt.getText();
		String carId = LoginFrame.carID;
		String password = this.passWordTxt.getText();
		op=new OperationDB();
		op.connectBegin();
		boolean flag=false;
		if(flag != op.updateInfor(carId, password, newname, newphone)) {
			JOptionPane.showMessageDialog(null, "修改成功！");
		}
		
	}
}
