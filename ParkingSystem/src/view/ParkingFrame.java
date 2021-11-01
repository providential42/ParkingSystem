package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

//import com.sun.xml.internal.stream.events.CommentEvent;

public class ParkingFrame extends JFrame {

	private JPanel contentPane;
	private JDesktopPane table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParkingFrame frame = new ParkingFrame();
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
	public ParkingFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ParkingFrame.class.getResource("/image/\u6C7D\u8F66.png")));
		setTitle("\u505C\u8F66\u573A\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u505C\u8F66\u529F\u80FD");
		mnNewMenu.setIcon(new ImageIcon(ParkingFrame.class.getResource("/image/\u6C7D\u8F66\u5934\u6807.png")));
		mnNewMenu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\u505C\u8F66");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CarinFrame().setVisible(true);
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(ParkingFrame.class.getResource("/image/\u6C7D\u8F66 \u7070.png")));
		mntmNewMenuItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u53D6\u8F66");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CostFrame().setVisible(true);
			}
		});
		mntmNewMenuItem_1.setIcon(new ImageIcon(ParkingFrame.class.getResource("/image/\u6C7D\u8F66 \u84DD.png")));
		mntmNewMenuItem_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("\u4FE1\u606F\u67E5\u8BE2");
		mnNewMenu_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		mnNewMenu_1.setIcon(new ImageIcon(ParkingFrame.class.getResource("/image/\u8D26\u6237.png")));
		mnNewMenu_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("\u67E5\u770B");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InfroLogin().setVisible(true);
			}
		});
		mntmNewMenuItem_2.setIcon(new ImageIcon(ParkingFrame.class.getResource("/image/\u4FE1\u606F\u67E5\u8BE2-01.png")));
		mntmNewMenuItem_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		mntmNewMenuItem_2.setForeground(new Color(0, 0, 0));
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("\u4FE1\u606F\u4FEE\u6539");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangeFrame().setVisible(true);
			}
		});
		mntmNewMenuItem_3.setBackground(new Color(255, 255, 255));
		mntmNewMenuItem_3.setIcon(new ImageIcon(ParkingFrame.class.getResource("/image/\u57FA\u672C\u4FE1\u606F-\u4FEE\u6539.png")));
		mntmNewMenuItem_3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		mntmNewMenuItem_3.setForeground(new Color(0, 0, 0));
		mnNewMenu_1.add(mntmNewMenuItem_3);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		table = new JDesktopPane();
		table.setBackground(Color.WHITE);
		contentPane.add(table, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		final JLabel background = new JLabel();
		URL resourse = this.getClass().getResource("/image/back.jpg");
		final ImageIcon icon=new ImageIcon(resourse);

		icon.setImage(icon.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		background.setIcon(new ImageIcon(ParkingFrame.class.getResource("/image/back.jpg")));
		background.setBounds(0, 0, this.getWidth(), this.getHeight());
		table.add(background,new Integer(Integer.MIN_VALUE));
		
		getContentPane().addComponentListener(new ComponentAdapter() {
			public void compentResized(ComponentEvent e) {
				int width=e.getComponent().getWidth();
				int height=e.getComponent().getHeight();
				icon.setImage(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
				background.setBounds(0, 0, width, height);
			}
		});

	}
}
