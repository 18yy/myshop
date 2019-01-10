package com.frame;

import javax.swing.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;

import com.util.CommonUtils;
import com.util.bussiness;
import com.util.goods;
import com.util.myutil;
import com.util.order;
import com.util.user;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Muser extends JFrame{
	private static myutil dbUtil = new myutil();
	private static goods selectgoods = new goods(); 
	
    public Muser(user myuser) throws Exception {
		// TODO Auto-generated constructor stub
    	
    	Integer uid = myuser.getID();
    	this.setSize(500, 420);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        //Ĭ�Ϲ�����jp1
      	JPanel jp1 = new JPanel(new GridLayout(5,1,7,8));
        JPanel jp14 = new JPanel();
        JPanel jp11 = new JPanel();
        JPanel jp12 = new JPanel();
        JPanel jp13 = new JPanel();
        jp11.setBackground(Color.white);
        jp12.setBackground(Color.white);
        jp13.setBackground(Color.white);
        JButton jb11 = new JButton("�޸ĺ���");
        JButton jb12 = new JButton("�޸ĵ�ַ");
        JLabel jl1 = new JLabel();
        JLabel jl2 = new JLabel();
        //��ʼ�����ڱ���͹����ҽ���
		if(myuser!=null) {
			String username=myuser.getUserName();
			this.setTitle("����ϵͳ֮�û�----��ӭ�㣡"+username);
			 jp11.add(new JLabel("�ǳ�:"+username));
			 jl1.setText( "��ϵ����:"+myuser.getUserPhone()); 
			 jl2.setText("��ַ:"+myuser.getAddress());
		}
		jp13.add(jl1);
		jp13.add(jb11);
		jp12.add(jl2);
		jp12.add(jb12);
		jp1.add(jp14);
		jp1.add(jp11);
		jp1.add(jp13);
		jp1.add(jp12);
        
        //�̳�jp2
		 JPanel jp2= new JPanel();
		JButton jb1 = new JButton("������Ʒ");
     	MuserG htm = new  MuserG();
     	//���� TableModel������ Table
	    JTable t = new JTable(htm);
	    JPanel jp21= new JPanel();
	    jp21.add(jb1);
	    JScrollPane jp22 = new JScrollPane(t);
	    jp2.add(jp21);
	    jp2.add(jp22);
    
        //�ҵĶ���jp3
      	
       Morder htm2 = new  Morder(myuser);
		 //���� TableModel������ Table
	    JTable t2 = new JTable(htm2);
	    JScrollPane jp3 = new JScrollPane(t2);
        
        // ����ѡ����
        final JTabbedPane tabbedPane = new JTabbedPane();


        // ������ 1 ��ѡ�
        tabbedPane.addTab("������", jp1);

        // ������ 2 ��ѡ�
        tabbedPane.addTab("�̳�", jp2);

        // ������ 3 ��ѡ�
        tabbedPane.addTab("�ҵĶ���",jp3);
        
   
      
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
        //����޸ĺ��밴ť
        jb11.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e){
            	String inputValue = JOptionPane.showInputDialog("�������޸ĺ�ĺ���");
            	 System.out.println(inputValue);
            	 try {
					if(updateUserPhone(inputValue,uid)) {
						 jl1.setText( "��ϵ����:"+inputValue); 
					 }
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
         }});
        //����޸ĵ�ַ��ť
        jb12.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e){
            	String inputValue = JOptionPane.showInputDialog("�������޸ĺ�ĵ�ַ");
            	 System.out.println(inputValue);
            	 try {
					if(updateUserAddress(inputValue,uid)) {
						jl2.setText("��ַ:"+inputValue);
					 }
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
         }});
        //�������ť
        jb1.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e){
            	int res = JOptionPane.showConfirmDialog(null, "�Ƿ���"+selectgoods.getGoodsName(),"�������", JOptionPane.YES_NO_OPTION);
        		if (res == JOptionPane.YES_OPTION) {
        			try {
						if(buggoods(selectgoods.getID())&& addorder(uid,selectgoods)) {
							htm.g= showallgoods();//�����̳ǵ�����
							 t.updateUI();//����ui
							 Morder.o=showallorder(uid);
							 t2.updateUI();
							 tabbedPane.setSelectedIndex(2);
							
 				}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        		} else {
        			System.out.println("ѡ����ִ�еĴ���"); // ������񡱺�ִ����������
        			return;
        		}

         }});

        // ����Ĭ��ѡ�е�ѡ�
        tabbedPane.setSelectedIndex(0);

        this.setContentPane(tabbedPane);
        this.setVisible(true);
        
        
	}
	public static void main(String[] args, user myuser) throws Exception {
      new Muser(myuser);
    }
	//�޸��ֻ�����
	public static boolean updateUserPhone(String v,int uid) throws Exception {
		
		Connection con = dbUtil.getCon();
		 String sql="update myaddress set phone=? where u_id=?";
	   	 PreparedStatement ptmt=con.prepareStatement(sql);
 	   	ptmt.setString(1, v);
	   	ptmt.setInt(2, uid);
	   	int i = ptmt.executeUpdate();
		if (i == 1) {
			dbUtil.close(ptmt, con);
			return true;
		} else {
			dbUtil.close(ptmt, con);
			return false;
		}	
	}
	//�޸ĵ�ַ
		public static boolean updateUserAddress(String v,int uid) throws Exception {
			
			Connection con = dbUtil.getCon();
			 String sql="update myaddress set address=? where u_id=?";
		   	 PreparedStatement ptmt=con.prepareStatement(sql);
	 	   	ptmt.setString(1, v);
		   	ptmt.setInt(2, uid);
		   	int i = ptmt.executeUpdate();
			if (i == 1) {
				dbUtil.close(ptmt, con);
				return true;
			} else {
				dbUtil.close(ptmt, con);
				return false;
			}	
		}
	//������Ʒ
	public static boolean buggoods(int selectgoodsid) throws Exception {
		
		String sql = null;
		sql = "update products set status='0' where p_id=?";//status��Ϊ0Ϊ�ѹ���
		Connection con = dbUtil.getCon();
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		 pstmt.setInt(1, selectgoodsid);
		int i = pstmt.executeUpdate();
		if (i == 1) {
			dbUtil.close(pstmt, con);
			return true;
		} else {
			dbUtil.close(pstmt, con);
			return false;
		}

	}
	//���Ӷ���
	public static boolean addorder(Integer uid,goods g) throws Exception {
		
		CommonUtils.bussiness = new bussiness();
		String sql = null;
		sql = "insert into orders(u_id,p_id,p_name,total_price,bussname,createtime) values(?,?,?,?,?,now())";
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = con.prepareStatement(sql);
		 pstmt.setInt(1, uid);
		 pstmt.setInt(2, g.getID());
		 pstmt.setString(3, g.getGoodsName());
		 pstmt.setFloat(4, g.getPrice());
		 pstmt.setString(5, g.getGoodsBussName());
		
		int i = pstmt.executeUpdate();
		if (i == 1) {
			dbUtil.close(pstmt, con);
			return true;
		} else {
			dbUtil.close(pstmt, con);
			return false;
		}
		}
	
	//չʾ������Ʒ
	public static List<goods> showallgoods() throws Exception {
		
		String sql = null;
		sql = "select p_id,p_name,detail,main_image,price,b_name from products,bussiness where status='1' and products.b_id=bussiness.b_id";//Ĭ��status��Ϊ1��Ϊ����
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = con.prepareStatement(sql);
		 
		ResultSet rs=pstmt.executeQuery();     //��sql��䴫�����ݿ⣬���ص�ֵΪһ���ַ�����һ���������� 
	    
		List<goods> result = new ArrayList<goods>();
		
	    while(rs.next()){    //next������ȡ���������
	    	 Integer goodsId = rs.getInt("p_id");
	    	 String goodsBussName = rs.getString("b_name");
	    	 String gname =rs.getString("p_name");
		     String gdetail = rs.getString("detail");
		     String gimage = rs.getString("main_image");
		     float gprice = rs.getFloat("price");
	    	result.add(new goods(goodsId,gname, gdetail,gimage,goodsBussName, gprice));
	    	
	   }		   			
		return result;
	}	
	//��ȡ���ж���
			public static List<order> showallorder(int uid) throws Exception {
				//CommonUtils.user = new user();
				String sql = null;
				sql = "select * from orders where u_id=?";
				Connection con = dbUtil.getCon();
				PreparedStatement pstmt = con.prepareStatement(sql);
				 pstmt.setInt(1, uid);
				ResultSet rs=pstmt.executeQuery();     //��sql��䴫�����ݿ⣬���ص�ֵΪһ���ַ�����һ���������� 
			    
				List<order> result = new ArrayList<order>();
				
			    while(rs.next()){    //next������ȡ���������
			    	
			    	 String gname =rs.getString("p_name");
				     String bussinessname = rs.getString("bussname");
				     float gprice = rs.getFloat("total_price");
				     String createtime = rs.getString("createTime");
			    	result.add(new order(uid, gname, gprice, createtime, bussinessname));
			    }
				
				
				return result;
			}
}
