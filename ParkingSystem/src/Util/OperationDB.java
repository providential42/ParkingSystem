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
//	   public Statement stmt=null;//用于接受结果
//	   public ResultSet rs=null;//结果集
	   public void connectBegin(){//开始连接
		   try {
		        // 加载驱动
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        // 获取连接对象
		        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carparking", "root", "i7r8g7h2");

		   }catch(Exception e) {
			   e.printStackTrace();
			   return;
		   }
	   }
	   
	   //登录，返回真假true / false
	   public boolean logIn(String carId,String password) {
		   	String sql = "select * from login where carId = ?";//新建查询语句
		   	ResultSet result=null;
	        // 创建执行对象
			try {
				PreparedStatement information = con.prepareStatement(sql);//预处理
				information.setString(1, carId);
				result = information.executeQuery();//查询
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
	   
	   //注册
	   public boolean newUser(String carId,String password,String name,String phone) {
		   	String sql = "insert into login values(?,?,?,?,?,?)";
	        // 创建执行对象
			try {
				PreparedStatement information = con.prepareStatement(sql);//预处理
				information.setString(1, carId);
				information.setString(2, password);
				information.setString(3, "普通");
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
	   
	   
	   //查看停车位状况,返回parkId\isEmpty数组队列
	   //例如：1 true 2 false 3 true 4 true
	   public ArrayList<String> checkAllParks(){
		   ArrayList<String> parks = new ArrayList<String>();	
		   String sql = "select * from parking";//新建查询语句
		   ResultSet result=null;
	        // 创建执行对象
			try {
				PreparedStatement information = con.prepareStatement(sql);//预处理
				result = information.executeQuery();//查询
				while(result.next()) {
					parks.add(result.getString("parkId"));
					parks.add(result.getString("isEmpty"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		   return parks;
	   }
	   
	   //查看个人信息
	   public ArrayList<String> getUserInformation(String carId){
		   ArrayList<String> infor = new ArrayList<String>();
		   String sql = "select * from login where carId = ?";//新建查询语句
		   ResultSet result=null;
	        // 创建执行对象
			try {
				PreparedStatement information = con.prepareStatement(sql);//预处理
				information.setString(1, carId);
				result = information.executeQuery();//查询
				if(result.next()) {
					infor.add(result.getString("carId"));
					//infor.add(result.getString("password"));
					infor.add(result.getString("note"));
					infor.add(result.getString("name"));
					infor.add(result.getString("phone"));
					//infor.add(result.getString("rewards"));
				}
				sql = "select * from carpark where carId = ?";//新建查询语句
				PreparedStatement information2 = con.prepareStatement(sql);//预处理
				information2.setString(1, carId);
				result = information2.executeQuery();//查询
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
	   
	   //停车
	   public boolean newPark(String carId,String parkId) {
		   	String sql = "insert into carpark (carId,parkId,timeIn) values(?,?,?)";
	        // 创建执行对象
			try {
				PreparedStatement information = con.prepareStatement(sql);//预处理
				information.setString(1, carId);
				information.setString(2, parkId);
				//获取当前时间
				java.util.Date dt = new java.util.Date();
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String currentTime = sdf.format(dt);
				//添加时间
				information.setString(3,currentTime);
				information.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		   return true;
	   }
	   
	 //修改个人信息
	   public boolean updateInfor(String carId,String password,String name,String phone) {
	      String sql = "update login set password = ?,name = ?, phone = ? where carid = ?";
	         // 创建执行对象
	   try {
	    PreparedStatement information = con.prepareStatement(sql);//预处理
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
	   
	 //出库
	    public  ArrayList<String> endPark(String carId) {
	      ArrayList<String> info=new ArrayList<>();
	      
	      String sql = "select * from carpark where carid = ?";
	      String sql2 = "update carpark set timeout = ?,cost = ? where id = ?";
	      ResultSet result=null;
	      String id=null,timein = null;
	         // 创建执行对象
	   try {
	    PreparedStatement information = con.prepareStatement(sql);//预处理
	    PreparedStatement information2 = con.prepareStatement(sql2);//预处理
	    information.setString(1, carId);
	    result = information.executeQuery();//查询
	    while(result.next()) {
	     if(result.getString("timeOut")==null) {
	      id=result.getString("id");
	      timein=result.getString("timein");
	      break; 
	     }
	    }
	    //获取当前时间
	    java.util.Date dt = new java.util.Date();
	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String currentTime = sdf.format(dt);
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	             java.util.Date d1 = format.parse(timein);
	             java.util.Date d2 = format.parse(currentTime);

	             //毫秒ms
	             long diff = d2.getTime() - d1.getTime();
	             int diffHours = (int)(diff / (60 * 60 * 1000) % 24);
	             int cost=diffHours*2;
	    //添加时间
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

