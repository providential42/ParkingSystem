package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Util.OperationDB;
import Util.StringUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class RegFrame extends JFrame {

	private JPanel contentPane;
	private JTextField carIdTxt;
	private JLabel lblNewLabel_1;
	private JLabel phone;
	private JTextField phoneTxt;
	private JLabel lblNewLabel_3;
	private JTextField nameTxt;
	private JLabel lblNewLabel_4;
	private JTextField passwordTxt;
	private JLabel lblNewLabel;
	OperationDB op;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					RegFrame frame = new RegFrame();
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
	public RegFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegFrame.class.getResource("/image/\u6C7D\u8F66.png")));
		setTitle("\u505C\u8F66\u573A\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		carIdTxt = new JTextField();
		carIdTxt.setBounds(544, 100, 107, 26);
		carIdTxt.setFont(new Font("新宋体", Font.PLAIN, 13));
		carIdTxt.setColumns(10);
		contentPane.add(carIdTxt);
		
		lblNewLabel_1 = new JLabel("\u8F66\u724C\u53F7");
		lblNewLabel_1.setBounds(490, 100, 90, 26);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("\u6CE8\u518C");
		btnNewButton.setBounds(490, 253, 161, 26);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegActionPerformed(e);
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(70, 130, 180));
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 17));
		contentPane.add(btnNewButton);
		
		phone = new JLabel("\u624B\u673A\u53F7");
		phone.setBounds(490, 136, 45, 20);
		phone.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(phone);
		
		phoneTxt = new JTextField();
		phoneTxt.setBounds(545, 131, 107, 26);
		phoneTxt.setFont(new Font("新宋体", Font.PLAIN, 13));
		phoneTxt.setColumns(10);
		contentPane.add(phoneTxt);
		
		lblNewLabel_3 = new JLabel("\u59D3\u540D");
		lblNewLabel_3.setBounds(490, 170, 45, 20);
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_3);
		
		nameTxt = new JTextField();
		nameTxt.setBounds(544, 164, 107, 26);
		nameTxt.setFont(new Font("新宋体", Font.PLAIN, 13));
		nameTxt.setColumns(10);
		contentPane.add(nameTxt);
		
		lblNewLabel_4 = new JLabel("\u5BC6\u7801");
		lblNewLabel_4.setBounds(490, 202, 90, 20);
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_4);
		
		passwordTxt = new JTextField();
		passwordTxt.setBounds(544, 200, 107, 26);
		passwordTxt.setFont(new Font("新宋体", Font.PLAIN, 13));
		passwordTxt.setColumns(10);
		contentPane.add(passwordTxt);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(RegFrame.class.getResource("/image/Parking1.jpg")));
		lblNewLabel.setBounds(0, 0, 800, 450);
		contentPane.add(lblNewLabel);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	private void RegActionPerformed(ActionEvent e) {
	
		String carId = this.carIdTxt.getText();
		String phone=this.phoneTxt.getText();
		String name = this.nameTxt.getText();
		String password=this.passwordTxt.getText();
		
		op=new OperationDB();
		op.connectBegin();
		boolean flag=false;
		
		if(StringUtil.isEmpty(carId)) {
			JOptionPane.showMessageDialog(null, "车牌号不能为空");
			return;
		}
		else if(StringUtil.isEmpty(name)) {
			JOptionPane.showMessageDialog(null, "名字不能为空");
			return;
		}
		else if(StringUtil.isEmpty(phone)) {
			JOptionPane.showMessageDialog(null, "手机号不能为空");
			return;
		}
		else if(StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "密码不能为空");
			return;
		}
		//newUser(String carId,String password,String name,String phone) 
		if(flag!=op.newUser(carId, password, name, phone)) {
			JOptionPane.showMessageDialog(null, "注册成功！");
			dispose();
			new LoginFrame().setVisible(true);
			return;
		}
		else {
			JOptionPane.showMessageDialog(null, "注册失败！");
			return;
		}
	}
}
