package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Util.OperationDB;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class CarinFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField newCarin;

	/**
	 * Launch the application.
	 * 
	 */
	/*
	 * */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CarinFrame frame = new CarinFrame();
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
	public CarinFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CarinFrame.class.getResource("/image/\u6C7D\u8F66.png")));
		setTitle("\u505C\u8F66\u60C5\u51B5");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 563, 385);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(true);
		scrollPane.setBounds(10, 10, 330, 328);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8F66\u4F4D\u53F7", "\u60C5\u51B5"
			}
		));
		scrollPane.setViewportView(table);
		fillTable();
		
		newCarin = new JTextField();
		newCarin.setBounds(424, 33, 79, 27);
		contentPane.add(newCarin);
		newCarin.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u8F66\u4F4D\u53F7");
		lblNewLabel.setForeground(new Color(65, 105, 225));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel.setBounds(369, 33, 58, 30);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\u505C\u8F66");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CarinActionPerformed(e);
			}
		});
		btnNewButton.setBackground(new Color(100, 149, 237));
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setIcon(new ImageIcon(CarinFrame.class.getResource("/image/\u6C7D\u8F66\u5934\u6807.png")));
		btnNewButton.setBounds(369, 86, 134, 30);
		contentPane.add(btnNewButton);
		
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}
	private void CarinActionPerformed(ActionEvent e) {
		OperationDB op = new OperationDB();
		op.connectBegin();
		String carId=LoginFrame.carID;
		System.out.println(carId);
		String parkId=this.newCarin.getText();
		boolean flag = false;
		if(flag!=op.newPark(carId, parkId)) 
		{
			JOptionPane.showMessageDialog(null, "停车成功！");
		}
		//
	}

	//初始化表格
	public void fillTable() {
		
		//查询所有车辆
		OperationDB op = new OperationDB();
		op.connectBegin();
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		ArrayList<String> parks =op.checkAllParks();
	    int length=parks.size();
	    for(int i=0;i<length-1;i=i+2)
	    {
	    	Vector v =new Vector<>();
	    	System.out.println(parks.get(i)+" "+parks.get(i+1));
	    	v.add(parks.get(i));
	    	v.add(parks.get(i+1));
	    	dtm.addRow(v);
	    }
	    		
	}

}
