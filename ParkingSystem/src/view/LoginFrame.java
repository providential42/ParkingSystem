package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Util.CarIdIn;
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
import javax.swing.JPasswordField;
import java.awt.Toolkit;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField carIdTxt;
	private JComboBox noteJCB;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1_2;
	private JPasswordField passwordTxt;
	public static String carID;
	public static  OperationDB op;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//链接数据库
		op=new OperationDB();
		op.connectBegin();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	//
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/image/\u6C7D\u8F66.png")));
		
		setTitle("\u505C\u8F66\u573A\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("\u5BC6\u7801");
		lblNewLabel_1_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(490, 179, 45, 26);
		contentPane.add(lblNewLabel_1_1);
		
		carIdTxt = new JTextField();
		carIdTxt.setFont(new Font("新宋体", Font.PLAIN, 15));
		carIdTxt.setColumns(10);
		carIdTxt.setBounds(544, 136, 107, 26);
		contentPane.add(carIdTxt);
		
		lblNewLabel_1 = new JLabel("\u8F66\u724C\u53F7");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(490, 136, 71, 26);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("\u767B\u5F55");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerformed(e);
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(70, 130, 180));
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 17));
		btnNewButton.setBounds(490, 215, 161, 26);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("\u6CE8\u518C");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RegFrame().setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 10));
		btnNewButton_1.setForeground(new Color(0, 0, 139));
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setBounds(593, 251, 59, 26);
		contentPane.add(btnNewButton_1);
		
		lblNewLabel_1_2 = new JLabel("\u7B49\u7EA7");
		lblNewLabel_1_2.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(490, 251, 29, 26);
		contentPane.add(lblNewLabel_1_2);
		
		
		passwordTxt = new JPasswordField();
		passwordTxt.setBackground(new Color(255, 255, 255));
		passwordTxt.setBounds(545, 179, 106, 25);
		contentPane.add(passwordTxt);
		
		noteJCB = new JComboBox();
		noteJCB.setModel(new DefaultComboBoxModel(new String[] {"\u666E\u901A", "VIP"}));
		noteJCB.setBounds(522, 252, 61, 23);
		contentPane.add(noteJCB);
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/image/Parking.jpg")));
		lblNewLabel.setBounds(0, 0, 800, 450);
		contentPane.add(lblNewLabel);

		this.setLocationRelativeTo(null);
	}

	private void loginActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		boolean flag=false;
		String carId=this.carIdTxt.getText();
		String password=new String(this.passwordTxt.getPassword());  //密码返回的是串？
		if(StringUtil.isEmpty(carId)) {
			JOptionPane.showMessageDialog(null, "车位名不能为空");
			return;
		}
		if(StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "密码不能为空");
			return;
		}
		if(flag==op.logIn(carId, password)) {
			JOptionPane.showMessageDialog(null, "车牌号或密码不对！");
		}else {
			System.out.println(carId);
			carID=carId;
			CarIdIn car= new CarIdIn();
			car.setCarId(carId);
			dispose();
			new ParkingFrame().setVisible(true);
		}
		
		
	}
}
