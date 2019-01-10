package com.frame;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.util.CommonUtils;
import com.util.MbussinessO;
import com.util.bussiness;
import com.util.goods;
import com.util.myutil;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PipedInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Mbussiness extends JFrame{
	private static myutil dbUtil = new myutil();
	private static JTable jt;
	private static goods selectgoods = new goods(); 
	private static int pid =0;
	 public Mbussiness(bussiness myBussiness) throws Exception{
		 	
		 	Integer bid = myBussiness.getID();//��ȡ�̼�ID

	        this.setSize(550, 450);
	        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        this.setLocationRelativeTo(null);
	        
	        //Ĭ�Ϲ�����jp1
	      	JPanel jp1 = new JPanel();
	        jp1.setBackground(Color.white);
	        //��ʼ�����ڱ���͹����ҽ���
			if(myBussiness!=null) {
				String BussName=myBussiness.getBussName();
				 jp1.add(new JLabel("��ӭ�㣡"+BussName));
				 this.setTitle("����ϵͳ֮�̼�----��ӭ�㣡"+BussName);
			}

	        
	        //������Ʒjp2
	      	JPanel jp2 = new JPanel(new GridLayout(5,2));
	        jp2.setBackground(Color.white);
	        
	        JTextField jTextField21 = new JTextField(25);
	        JTextField jTextField22 = new JTextField(25);
	        JTextField jTextField23 = new JTextField(25);
	        JTextField jTextField24 = new JTextField(25);
	        
	        JButton jb25 = new JButton("������Ʒ");
	        
	        JPanel jp21 =  new JPanel();
	        JPanel jp22 =  new JPanel();
	        JPanel jp23 =  new JPanel();
	        JPanel jp24 =  new JPanel();
	        JPanel jp25 =  new JPanel();
	      
	        JLabel jLabel21 = new JLabel("��Ʒ��");
	        JLabel jLabel22= new JLabel("��Ʒ����");
	        JLabel jLabel23= new JLabel("��Ʒ�۸�");
	        JLabel jLabel24= new JLabel("��ƷͼƬ");
	        
	        jp21.add(jLabel21); 
			jp21.add(jTextField21);
			
			jp22.add(jLabel22); 
			jp22.add(jTextField22);
	       
			jp23.add(jLabel23); 
			jp23.add(jTextField23);
			

			jp24.add(jLabel24); 
			jp24.add(jTextField24);
			
			jp25.add(jb25);
			
			jp2.add(jp21);
			jp2.add(jp22);
			jp2.add(jp23);
			jp2.add(jp24);
			jp2.add(jp25);
	       
	        //�ҵ���Ʒjp3
			//�̳�jp2
			 JPanel jp3= new JPanel();
			JButton jb31 = new JButton("ɾ����Ʒ");
			 MbussinessG htm = new  MbussinessG(myBussiness);
			 //���� TableModel������ Table
		     JTable t = new JTable(htm);
		    JPanel jp31= new JPanel();
		    jp31.add(jb31);
		    JScrollPane jp32 = new JScrollPane(t);
		    jp3.add(jp31);
		    jp3.add(jp32);
			
			
	        //�ҵĶ���jp4
		     MbussinessO htm1 = new  MbussinessO(myBussiness);
			 //���� TableModel������ Table
		     JTable t1 = new JTable(htm1);
		     JScrollPane jp4 = new JScrollPane(t1);
	        // ����ѡ����
	        final JTabbedPane tabbedPane = new JTabbedPane();


	        // ������ 1 ��ѡ�
	        tabbedPane.addTab("������", jp1);

	        // ������ 2 ��ѡ�
	        tabbedPane.addTab("������Ʒ", jp2);

	        // ������ 3 ��ѡ�
	        tabbedPane.addTab("�ҵ���Ʒ",jp3);
	        
	        // ������ 4 ��ѡ�
	        tabbedPane.addTab("���ն���", jp4);


	        // ���ѡ�ѡ��״̬�ı�ļ�����
	        tabbedPane.addChangeListener(new ChangeListener() {
	            @Override
	            public void stateChanged(ChangeEvent e) {
	                System.out.println("��ǰѡ�е�ѡ�: " + tabbedPane.getSelectedIndex());
	                if(tabbedPane.getSelectedIndex()==2) {
	                	
	                

	                }
	            }
	        });
	        // ʹ��selection������������table���ĸ���Ŀ��ѡ��
	        t.getSelectionModel().addListSelectionListener(
	                new ListSelectionListener() {
	  
	                    // ��ѡ����ĳһ�е�ʱ�򴥷����¼�
	                    public void valueChanged(ListSelectionEvent e) {
	                        // ��ȡ��һ�б�ѡ����
	                        int row = t.getSelectedRow();
	                        // ����ѡ�е��У���TableModel�л�ȡ��Ӧ�Ķ���
	                        selectgoods = htm.g.get(row);
	                       
	                    }
	                });
	        //���ɾ����Ʒ��ť
	        jb31.addActionListener(new ActionListener() {
	            
	            public void actionPerformed(ActionEvent e){
	            	if(selectgoods.getStatus()==0) {
	            		JOptionPane.showMessageDialog(null, "�ѱ�������Ʒ�޷�ɾ��", "������ʾ",JOptionPane.ERROR_MESSAGE);
	            	}else {
	            		int res = JOptionPane.showConfirmDialog(null, "ɾ����Ʒ���̳�Ҳ��ɾ����Ʒ��ȷ���Ƿ�ɾ��"+selectgoods.getGoodsName(),"ɾ������", JOptionPane.YES_NO_OPTION);
		        		if (res == JOptionPane.YES_OPTION) {
		        			try {
		        				pid =selectgoods.getID();
								if(deleteGoods(pid)) {
									htm.g= showallgoods(bid);//�����̳ǵ�����
									 t.updateUI();//����ui
								}
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		        		} else {
		        			System.out.println("ѡ����ִ�еĴ���"); // ������񡱺�ִ����������
		        			return;
		        		}
					}

	         }});
	        // ����Ĭ��ѡ�е�ѡ�
	        tabbedPane.setSelectedIndex(0);

	        this.setContentPane(tabbedPane);
	        this.setVisible(true);
	        //������Ʒģ��ĵ����������

	        jb25.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e){
	            	String gname= jTextField21.getText().trim();
	            	String gdetail= jTextField22.getText().trim();
	            	float gprice= Float.parseFloat(jTextField23.getText());
	            	String gimage= jTextField24.getText().trim();
	            	int res = JOptionPane.showConfirmDialog(null, "�Ƿ񷢲�"+gname,"��������", JOptionPane.YES_NO_OPTION);
	        		if (res == JOptionPane.YES_OPTION) {
	            	try {
						boolean result = addgoods(gname,gimage,gdetail,gprice,bid);
						if(result) {
							System.out.println("��Ʒ�����ɹ�");
							jTextField21.setText("");
							jTextField22.setText("");
							jTextField23.setText("");
							jTextField24.setText("");
							tabbedPane.setSelectedIndex(2);
							 MbussinessG.g = showallgoods(bid);//����tablemodel������
							 t.updateUI();//����ui
							
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}}else {
						System.out.println("ѡ����ִ�еĴ���"); // ������񡱺�ִ����������
	        			return;
					}
	            	
	            }});
	 }
    public static void main(String[] args, bussiness myBussiness) throws Exception {
       new Mbussiness(myBussiness);
   
    }
    //ɾ���ϼ���Ʒ
	public static boolean deleteGoods(int pid) throws Exception {
		
		Connection con = dbUtil.getCon();
		 String sql="delete from products where p_id=?";
	   	 PreparedStatement ptmt=con.prepareStatement(sql);
 	   	ptmt.setInt(1, pid);
	  
	   	int i = ptmt.executeUpdate();
		if (i == 1) {
			dbUtil.close(ptmt, con);
			return true;
		} else {
			dbUtil.close(ptmt, con);
			return false;
		}	
	}
	
    public static List<goods> showallgoods(Integer bid) throws Exception {
		
		String sql = null;
		sql = "select * from products where  b_id=?";
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = con.prepareStatement(sql);
		 pstmt.setInt(1, bid);
		ResultSet rs=pstmt.executeQuery();     //��sql��䴫�����ݿ⣬���ص�ֵΪһ���ַ�����һ���������� 
	    
		List<goods> result = new ArrayList<goods>();
		
	    while(rs.next()){    //next������ȡ���������
	    	 Integer gid = rs.getInt("p_id");
	    	 String gname =rs.getString("p_name");
		     String gdetail = rs.getString("detail");
		     String gimage = rs.getString("main_image");
		     float gprice = rs.getFloat("price");
		     Integer status = rs.getInt("status");
		    result.add(new goods(gid,gname, gdetail,gimage, gprice,status));
	    	
	    }
	   
		
		return result;
	}
		
    //������Ʒ
    public static boolean addgoods(String gname,String gimage,String gdetail,float gprice,Integer bid) throws Exception {
		CommonUtils.bussiness = new bussiness();
		String sql = null;
		sql = "insert into products(p_name,main_image,detail,price,b_id) value(?,?,?,?,?)";
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = con.prepareStatement(sql);
		 pstmt.setString(1, gname);
    	 pstmt.setString(2, gimage);
    	 pstmt.setString(3, gdetail);
    	 pstmt.setFloat(4, gprice);
    	 pstmt.setInt(5, bid);
		int i = pstmt.executeUpdate();
		if (i == 1) {
			dbUtil.close(pstmt, con);
			return true;
		} else {
			dbUtil.close(pstmt, con);
			return false;
		}
	}

}
