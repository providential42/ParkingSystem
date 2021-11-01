package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OperationDB {
	   public Connection con=null;
//	   public Statement stmt=null;//���ڽ��ܽ��
//	   public ResultSet rs=null;//�����
	   public void connectBegin(){//��ʼ����
		   try {
		        // ��������
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        // ��ȡ���Ӷ���
		        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carparking", "root", "i7r8g7h2");

		   }catch(Exception e) {
			   e.printStackTrace();
			   return;
		   }
	   }
	   
	   //��¼���������true / false
	   public boolean logIn(String carId,String password) {
		   	String sql = "select * from login where carId = ?";//�½���ѯ���
		   	ResultSet result=null;
	        // ����ִ�ж���
			try {
				PreparedStatement information = con.prepareStatement(sql);//Ԥ����
				information.setString(1, carId);
				result = information.executeQuery();//��ѯ
				if(result.next()) {
					String pwd=result.getString("password");
					if(pwd.equals(password))
						return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		   return false;
	   }
	   
	   //ע��
	   public boolean newUser(String carId,String password,String name,String phone) {
		   	String sql = "insert into login values(?,?,?,?,?,?)";
	        // ����ִ�ж���
			try {
				PreparedStatement information = con.prepareStatement(sql);//Ԥ����
				information.setString(1, carId);
				information.setString(2, password);
				information.setString(3, "��ͨ");
				information.setString(4, name);
				information.setString(5, phone);
				information.setString(6, "0");
				information.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		   return true;
	   }
	   
	   
	   //�鿴ͣ��λ״��,����parkId\isEmpty�������
	   //���磺1 true 2 false 3 true 4 true
	   public ArrayList<String> checkAllParks(){
		   ArrayList<String> parks = new ArrayList<String>();	
		   String sql = "select * from parking";//�½���ѯ���
		   ResultSet result=null;
	        // ����ִ�ж���
			try {
				PreparedStatement information = con.prepareStatement(sql);//Ԥ����
				result = information.executeQuery();//��ѯ
				while(result.next()) {
					parks.add(result.getString("parkId"));
					parks.add(result.getString("isEmpty"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		   return parks;
	   }
	   
	   //�鿴������Ϣ
	   public ArrayList<String> getUserInformation(String carId){
		   ArrayList<String> infor = new ArrayList<String>();
		   String sql = "select * from login where carId = ?";//�½���ѯ���
		   ResultSet result=null;
	        // ����ִ�ж���
			try {
				PreparedStatement information = con.prepareStatement(sql);//Ԥ����
				information.setString(1, carId);
				result = information.executeQuery();//��ѯ
				if(result.next()) {
					infor.add(result.getString("carId"));
					//infor.add(result.getString("password"));
					infor.add(result.getString("note"));
					infor.add(result.getString("name"));
					infor.add(result.getString("phone"));
					//infor.add(result.getString("rewards"));
				}
				sql = "select * from carpark where carId = ?";//�½���ѯ���
				PreparedStatement information2 = con.prepareStatement(sql);//Ԥ����
				information2.setString(1, carId);
				result = information2.executeQuery();//��ѯ
				while(result.next()) {
					infor.add(result.getString("parkId"));
					infor.add(result.getString("timein"));
					infor.add(result.getString("timeout"));
					infor.add(result.getString("cost"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		   return infor;
	   }
	   
	   //ͣ��
	   public boolean newPark(String carId,String parkId) {
		   	String sql = "insert into carpark (carId,parkId,timeIn) values(?,?,?)";
	        // ����ִ�ж���
			try {
				PreparedStatement information = con.prepareStatement(sql);//Ԥ����
				information.setString(1, carId);
				information.setString(2, parkId);
				//��ȡ��ǰʱ��
				java.util.Date dt = new java.util.Date();
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String currentTime = sdf.format(dt);
				//���ʱ��
				information.setString(3,currentTime);
				information.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		   return true;
	   }
	   
	 //�޸ĸ�����Ϣ
	   public boolean updateInfor(String carId,String password,String name,String phone) {
	      String sql = "update login set password = ?,name = ?, phone = ? where carid = ?";
	         // ����ִ�ж���
	   try {
	    PreparedStatement information = con.prepareStatement(sql);//Ԥ����
	    information.setString(1, password);
	    information.setString(2, name);
	    information.setString(3, phone);
	    information.setString(4, carId);
	    information.executeUpdate();
	   } catch (SQLException e) {
	    e.printStackTrace();
	    return false;
	   }
	     return true;
	    }
	   
	 //����
	    public  ArrayList<String> endPark(String carId) {
	      ArrayList<String> info=new ArrayList<>();
	      
	      String sql = "select * from carpark where carid = ?";
	      String sql2 = "update carpark set timeout = ?,cost = ? where id = ?";
	      ResultSet result=null;
	      String id=null,timein = null;
	         // ����ִ�ж���
	   try {
	    PreparedStatement information = con.prepareStatement(sql);//Ԥ����
	    PreparedStatement information2 = con.prepareStatement(sql2);//Ԥ����
	    information.setString(1, carId);
	    result = information.executeQuery();//��ѯ
	    while(result.next()) {
	     if(result.getString("timeOut")==null) {
	      id=result.getString("id");
	      timein=result.getString("timein");
	      break; 
	     }
	    }
	    //��ȡ��ǰʱ��
	    java.util.Date dt = new java.util.Date();
	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String currentTime = sdf.format(dt);
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	             java.util.Date d1 = format.parse(timein);
	             java.util.Date d2 = format.parse(currentTime);

	             //����ms
	             long diff = d2.getTime() - d1.getTime();
	             int diffHours = (int)(diff / (60 * 60 * 1000) % 24);
	             int cost=diffHours*2;
	    //���ʱ��
	             information2.setString(1,currentTime);
	    information2.setString(2,Integer.toString(cost));
	    information2.setString(3,id);
	    information2.executeUpdate();
	    info.add(Integer.toString(cost));
	    info.add(timein);
	    info.add(currentTime);    
	   } catch (Exception e) {
	    e.printStackTrace();
	    return null;
	   }
	     return info;
	}
	   
	   
}

