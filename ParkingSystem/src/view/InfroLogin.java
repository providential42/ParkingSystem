package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Util.CarIdIn;
import Util.OperationDB;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InfroLogin extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField carIdTxt;
	private JTextField nameTxt;
	private JTextField phoneTxt;
	private JTextField noteTxt;
	CarIdIn car=new CarIdIn();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfroLogin frame = new InfroLogin();
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
	public InfroLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InfroLogin.class.getResource("/image/\u6C7D\u8F66.png")));
		setTitle("\u4E2A\u4EBA\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 529, 483);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 26, 495, 270);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("ËÎÌå", Font.PLAIN, 14));
		table.setForeground(Color.BLACK);
		table.setBackground(Color.WHITE);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8F66\u4F4D\u53F7", "\u5165\u5E93\u65F6\u95F4", "\u51FA\u5E93\u65F6\u95F4", "\u82B1\u8D39"
			}
		));
		scrollPane.setViewportView(table);
		fillTable();
		
		lblNewLabel = new JLabel("\u8F66\u724C\u53F7");
		lblNewLabel.setForeground(new Color(65, 105, 225));
		lblNewLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 321, 58, 21);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("\u59D3\u540D");
		lblNewLabel_1.setForeground(new Color(65, 105, 225));
		lblNewLabel_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 373, 58, 18);
		contentPane.add(lblNewLabel_1);
		
		carIdTxt = new JTextField();
		carIdTxt.setBounds(78, 321, 127, 27);
		contentPane.add(carIdTxt);
		carIdTxt.setColumns(10);
		
		nameTxt = new JTextField();
		nameTxt.setColumns(10);
		nameTxt.setBounds(78, 367, 127, 27);
		contentPane.add(nameTxt);
		
		JLabel lblNewLabel_2 = new JLabel("\u624B\u673A\u53F7");
		lblNewLabel_2.setForeground(new Color(65, 105, 225));
		lblNewLabel_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(249, 321, 58, 21);
		contentPane.add(lblNewLabel_2);
		
		phoneTxt = new JTextField();
		phoneTxt.setColumns(10);
		phoneTxt.setBounds(330, 321, 133, 27);
		contentPane.add(phoneTxt);
		
		JLabel lblNewLabel_2_1 = new JLabel("\u7B49\u7EA7");
		lblNewLabel_2_1.setForeground(new Color(65, 105, 225));
		lblNewLabel_2_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
		lblNewLabel_2_1.setBounds(249, 373, 58, 18);
		contentPane.add(lblNewLabel_2_1);
		
		noteTxt = new JTextField();
		noteTxt.setColumns(10);
		noteTxt.setBounds(330, 367, 133, 27);
		contentPane.add(noteTxt);
		
		OperationDB op = new OperationDB();
		op.connectBegin();
		String carId=LoginFrame.carID;
		ArrayList<String> Infor =op.getUserInformation(carId);
	    carIdTxt.setText(Infor.get(0));
	    noteTxt.setText(Infor.get(1));
	    nameTxt.setText(Infor.get(2));
	    phoneTxt.setText(Infor.get(3));
		
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}
	public void fillTable() {
			
			//²éÑ¯ËùÓÐ³µÁ¾
		OperationDB op = new OperationDB();
		op.connectBegin();
		String carId=LoginFrame.carID;
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		System.out.println(carId);
		ArrayList<String> Infor =op.getUserInformation(carId);
	    int length=Infor.size();
	    for(int i=4;i<length-1;i=i+4)
	    {
	    	Vector v =new Vector<>();
	    	System.out.println(Infor.get(i)+" "+Infor.get(i+1));
	    	v.add(Infor.get(i));
	    	v.add(Infor.get(i+1));
	    	v.add(Infor.get(i+2));
	    	v.add(Infor.get(i+3));
	    	dtm.addRow(v);
	    }		

	}
}
