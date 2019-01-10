package com.frame;



import javax.swing.*;

import com.util.bussiness;
import com.util.myutil;
import com.util.user;

import java.awt.*;   //导入必要的包
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class load extends JFrame{
	
	private static myutil dbUtil = new myutil();
    static JTextField jTextField ;//定义文本框组件
    JPasswordField jPasswordField;//定义密码框组件
    JLabel jLabel1,jLabel2;
    JPanel jp1,jp2,jp3;
    static JButton jb1;//创建按钮
	JButton jb2;
    public load(){
    	this.setLocationRelativeTo(null);
        jTextField = new JTextField(12);
        jPasswordField = new JPasswordField(13);
        jLabel1 = new JLabel("用户名");
        jLabel2 = new JLabel("密码");
        jb1 = new JButton("用户");
        jb2 = new JButton("商家");
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        
        //设置布局
        this.setLayout(new GridLayout(3,1));
        
        jp1.add(jLabel1); 
        jp1.add(jTextField);//第一块面板添加用户名和文本框 
        
        jp2.add(jLabel2);
        jp2.add(jPasswordField);//第二块面板添加密码和密码输入框
        
        jp3.add(jb1);
        jp3.add(jb2); //第三块面板添加确认和取消
        
        //        jp3.setLayout(new FlowLayout());  　　//因为JPanel默认布局方式为FlowLayout，所以可以注销这段代码.
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);  //将三块面板添加到登陆框上面
        //设置显示
        this.setSize(300, 200);
        //this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("登陆");
        Object that=this;
        //检查用户登录
        jb1.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e){
            	 String userId = jTextField.getText().trim();
            	 String upsw = new String(jPasswordField.getPassword());
            	 Connection con;
				try {
					con = dbUtil.getCon();
					 String sql="select * from user,myaddress where u_name=? and u_password=? and user.u_id=myaddress.u_id";
	            	 PreparedStatement ptmt=con.prepareStatement(sql);
	            	 ptmt.setString(1, userId);
	            	 ptmt.setString(2, upsw);
	            	 ResultSet rs=ptmt.executeQuery();
	            	 user myuser = new user();
	            	
	            	 //从登录用户给出的账号密码来检测查询在数据库表中是否存在相同的账号密码
	            	 if(rs.next()){
	            		 myuser.setAddress(rs.getString("address"));
	            		 myuser.setUserPhone(rs.getString("phone"));
	            		 myuser.setID(rs.getInt("u_id"));
		            	 myuser.setUserName(rs.getString("u_name"));
		            	 Integer id=myuser.getID();
		            	 JOptionPane.showMessageDialog(null, "用户登录成功", "欢迎"+myuser.getUserName(), JOptionPane.QUESTION_MESSAGE);
		            	 System.out.println("用户登录成功！");
		            	 System.out.println(id);
		            	  new Muser(myuser);//为跳转的界面
		            	  (((Window) that) ).dispose();//销毁当前界
	            	 }else{
	            		 JOptionPane.showMessageDialog(null, "姓名或密码错误，请重试！", "警告提示",JOptionPane.ERROR_MESSAGE);
	            	 
	            	 }
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}        	
            }
        });
        
        //检查商家登录
        jb2.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e){
            	 String userId = jTextField.getText().trim();
            	 String upsw = new String(jPasswordField.getPassword());
            	 Connection con;
				try {
					con = dbUtil.getCon();
					 String sql="select * from bussiness where b_name=? and b_password=?";
	            	 PreparedStatement ptmt=con.prepareStatement(sql);
	            	 ptmt.setString(1, userId);
	            	 ptmt.setString(2, upsw);
	            	 ResultSet rs=ptmt.executeQuery();
	            	 //从登录用户给出的账号密码来检测查询在数据库表中是否存在相同的账号密码
	            	 bussiness myBussiness = new bussiness();
	            	 if(rs.next()){
	            		 myBussiness.setID(rs.getInt("b_id"));
	            		 myBussiness.setBussName(rs.getString("b_name"));
		            	 Integer id=myBussiness.getID();
		            	 JOptionPane.showMessageDialog(null, "商家登录成功", "欢迎"+myBussiness.getBussName(), JOptionPane.QUESTION_MESSAGE);
		            	 System.out.println("商家登录成功");
		            	 System.out.println(id);
		            	  new Mbussiness(myBussiness);//为跳转的界面
		            	  (((Window) that) ).dispose();//销毁当前界
	            	 }else{
	            		 JOptionPane.showMessageDialog(null, "姓名或密码错误，请重试！", "警告提示",JOptionPane.ERROR_MESSAGE);
	            	 
	            	 }
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	
            }
        });
    }
    public static void main(String[] args){
        new load();
       

    }
}