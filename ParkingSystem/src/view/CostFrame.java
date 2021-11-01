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
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class CostFrame extends JFrame {

	private JPanel contentPane;
	private JTextField carIdTxt;
	private JTextField costTxt;
	private JTextField inTxt;
	private JTextField outTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CostFrame frame = new CostFrame();
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
	public CostFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CostFrame.class.getResource("/image/\u6C7D\u8F66.png")));
		setTitle("\u51FA\u5E93\u7F34\u8D39");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 260);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8F66\u724C\u53F7");
		lblNewLabel.setForeground(new Color(65, 105, 225));
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 24, 56, 26);
		contentPane.add(lblNewLabel);
		
		carIdTxt = new JTextField();
		carIdTxt.setBounds(98, 24, 114, 25);
		contentPane.add(carIdTxt);
		carIdTxt.setColumns(10);

		
		JLabel lblNewLabel_1 = new JLabel("\u82B1\u8D39");
		lblNewLabel_1.setForeground(new Color(65, 105, 225));
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 64, 56, 26);
		contentPane.add(lblNewLabel_1);
		
		costTxt = new JTextField();
		costTxt.setColumns(10);
		costTxt.setBounds(98, 66, 114, 25);
		contentPane.add(costTxt);
		
		JLabel lblNewLabel_1_1 = new JLabel("\u5165\u5E93\u65F6\u95F4");
		lblNewLabel_1_1.setForeground(new Color(65, 105, 225));
		lblNewLabel_1_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 100, 78, 26);
		contentPane.add(lblNewLabel_1_1);
		
		inTxt = new JTextField();
		inTxt.setColumns(10);
		inTxt.setBounds(98, 101, 114, 25);
		contentPane.add(inTxt);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(ChangeFrame.class.getResource("/image/\u6C7D\u8F66\u4FE1\u606F.png")));
		lblNewLabel_2.setBounds(257, 24, 64, 83);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("\u7F34\u8D39");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CaroutActionPerformed(e);
			}
		});
		btnNewButton.setBackground(new Color(135, 206, 250));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNewButton.setBounds(242, 130, 97, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("\u51FA\u5E93\u65F6\u95F4");
		lblNewLabel_1_1_1.setForeground(new Color(65, 105, 225));
		lblNewLabel_1_1_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(10, 136, 78, 26);
		contentPane.add(lblNewLabel_1_1_1);
		
		outTxt = new JTextField();
		outTxt.setColumns(10);
		outTxt.setBounds(98, 136, 114, 25);
		contentPane.add(outTxt);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
	
		OperationDB op = new OperationDB();
		op.connectBegin();
		String carId = LoginFrame.carID;
		System.out.println(carId);
		ArrayList<String> info = op.endPark(carId);
		/*try {
			ArrayList<String> info = op.endPark(carId);
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "您还没停车！");
            e.printStackTrace();//输出异常信息
        }*/
		carIdTxt.setText(carId);
		costTxt.setText(info.get(0));
		inTxt.setText(info.get(1));
		outTxt.setText(info.get(2));
		/*
		OperationDB op = new OperationDB();
		op.connectBegin();
		String carId=LoginFrame.carID;
		ArrayList<String> Infor =op.getUserInformation(carId);
	    carIdTxt.setText(Infor.get(0));
	    noteTxt.setText(Infor.get(1));
	    nameTxt.setText(Infor.get(2));
	    phoneTxt.setText(Infor.get(3));
	    */
		//cost
		//op.endPark();
		
	}

	private void CaroutActionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "缴费成功！");
	}
}
