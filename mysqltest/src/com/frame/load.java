package com.frame;



import javax.swing.*;

import com.util.bussiness;
import com.util.myutil;
import com.util.user;

import java.awt.*;   //�����Ҫ�İ�
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class load extends JFrame{
	
	private static myutil dbUtil = new myutil();
    static JTextField jTextField ;//�����ı������
    JPasswordField jPasswordField;//������������
    JLabel jLabel1,jLabel2;
    JPanel jp1,jp2,jp3;
    static JButton jb1;//������ť
	JButton jb2;
    public load(){
    	this.setLocationRelativeTo(null);
        jTextField = new JTextField(12);
        jPasswordField = new JPasswordField(13);
        jLabel1 = new JLabel("�û���");
        jLabel2 = new JLabel("����");
        jb1 = new JButton("�û�");
        jb2 = new JButton("�̼�");
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        
        //���ò���
        this.setLayout(new GridLayout(3,1));
        
        jp1.add(jLabel1); 
        jp1.add(jTextField);//��һ���������û������ı��� 
        
        jp2.add(jLabel2);
        jp2.add(jPasswordField);//�ڶ�����������������������
        
        jp3.add(jb1);
        jp3.add(jb2); //������������ȷ�Ϻ�ȡ��
        
        //        jp3.setLayout(new FlowLayout());  ����//��ΪJPanelĬ�ϲ��ַ�ʽΪFlowLayout�����Կ���ע����δ���.
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);  //�����������ӵ���½������
        //������ʾ
        this.setSize(300, 200);
        //this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("��½");
        Object that=this;
        //����û���¼
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
	            	
	            	 //�ӵ�¼�û��������˺�����������ѯ�����ݿ�����Ƿ������ͬ���˺�����
	            	 if(rs.next()){
	            		 myuser.setAddress(rs.getString("address"));
	            		 myuser.setUserPhone(rs.getString("phone"));
	            		 myuser.setID(rs.getInt("u_id"));
		            	 myuser.setUserName(rs.getString("u_name"));
		            	 Integer id=myuser.getID();
		            	 JOptionPane.showMessageDialog(null, "�û���¼�ɹ�", "��ӭ"+myuser.getUserName(), JOptionPane.QUESTION_MESSAGE);
		            	 System.out.println("�û���¼�ɹ���");
		            	 System.out.println(id);
		            	  new Muser(myuser);//Ϊ��ת�Ľ���
		            	  (((Window) that) ).dispose();//���ٵ�ǰ��
	            	 }else{
	            		 JOptionPane.showMessageDialog(null, "������������������ԣ�", "������ʾ",JOptionPane.ERROR_MESSAGE);
	            	 
	            	 }
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}        	
            }
        });
        
        //����̼ҵ�¼
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
	            	 //�ӵ�¼�û��������˺�����������ѯ�����ݿ�����Ƿ������ͬ���˺�����
	            	 bussiness myBussiness = new bussiness();
	            	 if(rs.next()){
	            		 myBussiness.setID(rs.getInt("b_id"));
	            		 myBussiness.setBussName(rs.getString("b_name"));
		            	 Integer id=myBussiness.getID();
		            	 JOptionPane.showMessageDialog(null, "�̼ҵ�¼�ɹ�", "��ӭ"+myBussiness.getBussName(), JOptionPane.QUESTION_MESSAGE);
		            	 System.out.println("�̼ҵ�¼�ɹ�");
		            	 System.out.println(id);
		            	  new Mbussiness(myBussiness);//Ϊ��ת�Ľ���
		            	  (((Window) that) ).dispose();//���ٵ�ǰ��
	            	 }else{
	            		 JOptionPane.showMessageDialog(null, "������������������ԣ�", "������ʾ",JOptionPane.ERROR_MESSAGE);
	            	 
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