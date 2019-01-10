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
		 	
		 	Integer bid = myBussiness.getID();//获取商家ID

	        this.setSize(550, 450);
	        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        this.setLocationRelativeTo(null);
	        
	        //默认关于我jp1
	      	JPanel jp1 = new JPanel();
	        jp1.setBackground(Color.white);
	        //初始化窗口标题和关于我界面
			if(myBussiness!=null) {
				String BussName=myBussiness.getBussName();
				 jp1.add(new JLabel("欢迎你！"+BussName));
				 this.setTitle("电子系统之商家----欢迎你！"+BussName);
			}

	        
	        //发布商品jp2
	      	JPanel jp2 = new JPanel(new GridLayout(5,2));
	        jp2.setBackground(Color.white);
	        
	        JTextField jTextField21 = new JTextField(25);
	        JTextField jTextField22 = new JTextField(25);
	        JTextField jTextField23 = new JTextField(25);
	        JTextField jTextField24 = new JTextField(25);
	        
	        JButton jb25 = new JButton("发布商品");
	        
	        JPanel jp21 =  new JPanel();
	        JPanel jp22 =  new JPanel();
	        JPanel jp23 =  new JPanel();
	        JPanel jp24 =  new JPanel();
	        JPanel jp25 =  new JPanel();
	      
	        JLabel jLabel21 = new JLabel("商品名");
	        JLabel jLabel22= new JLabel("商品详情");
	        JLabel jLabel23= new JLabel("商品价格");
	        JLabel jLabel24= new JLabel("商品图片");
	        
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
	       
	        //我的商品jp3
			//商城jp2
			 JPanel jp3= new JPanel();
			JButton jb31 = new JButton("删除商品");
			 MbussinessG htm = new  MbussinessG(myBussiness);
			 //根据 TableModel来创建 Table
		     JTable t = new JTable(htm);
		    JPanel jp31= new JPanel();
		    jp31.add(jb31);
		    JScrollPane jp32 = new JScrollPane(t);
		    jp3.add(jp31);
		    jp3.add(jp32);
			
			
	        //我的订单jp4
		     MbussinessO htm1 = new  MbussinessO(myBussiness);
			 //根据 TableModel来创建 Table
		     JTable t1 = new JTable(htm1);
		     JScrollPane jp4 = new JScrollPane(t1);
	        // 创建选项卡面板
	        final JTabbedPane tabbedPane = new JTabbedPane();


	        // 创建第 1 个选项卡
	        tabbedPane.addTab("关于我", jp1);

	        // 创建第 2 个选项卡
	        tabbedPane.addTab("发布商品", jp2);

	        // 创建第 3 个选项卡
	        tabbedPane.addTab("我的商品",jp3);
	        
	        // 创建第 4 个选项卡
	        tabbedPane.addTab("接收订单", jp4);


	        // 添加选项卡选中状态改变的监听器
	        tabbedPane.addChangeListener(new ChangeListener() {
	            @Override
	            public void stateChanged(ChangeEvent e) {
	                System.out.println("当前选中的选项卡: " + tabbedPane.getSelectedIndex());
	                if(tabbedPane.getSelectedIndex()==2) {
	                	
	                

	                }
	            }
	        });
	        // 使用selection监听器来监听table的哪个条目被选中
	        t.getSelectionModel().addListSelectionListener(
	                new ListSelectionListener() {
	  
	                    // 当选择了某一行的时候触发该事件
	                    public void valueChanged(ListSelectionEvent e) {
	                        // 获取哪一行被选中了
	                        int row = t.getSelectedRow();
	                        // 根据选中的行，到TableModel中获取对应的对象
	                        selectgoods = htm.g.get(row);
	                       
	                    }
	                });
	        //点击删除商品按钮
	        jb31.addActionListener(new ActionListener() {
	            
	            public void actionPerformed(ActionEvent e){
	            	if(selectgoods.getStatus()==0) {
	            		JOptionPane.showMessageDialog(null, "已被购买商品无法删除", "警告提示",JOptionPane.ERROR_MESSAGE);
	            	}else {
	            		int res = JOptionPane.showConfirmDialog(null, "删除商品后商城也会删除商品，确定是否删除"+selectgoods.getGoodsName(),"删除操作", JOptionPane.YES_NO_OPTION);
		        		if (res == JOptionPane.YES_OPTION) {
		        			try {
		        				pid =selectgoods.getID();
								if(deleteGoods(pid)) {
									htm.g= showallgoods(bid);//更新商城的数据
									 t.updateUI();//更新ui
								}
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		        		} else {
		        			System.out.println("选择否后执行的代码"); // 点击“否”后执行这个代码块
		        			return;
		        		}
					}

	         }});
	        // 设置默认选中的选项卡
	        tabbedPane.setSelectedIndex(0);

	        this.setContentPane(tabbedPane);
	        this.setVisible(true);
	        //发布商品模块的点击发布功能

	        jb25.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e){
	            	String gname= jTextField21.getText().trim();
	            	String gdetail= jTextField22.getText().trim();
	            	float gprice= Float.parseFloat(jTextField23.getText());
	            	String gimage= jTextField24.getText().trim();
	            	int res = JOptionPane.showConfirmDialog(null, "是否发布"+gname,"发布操作", JOptionPane.YES_NO_OPTION);
	        		if (res == JOptionPane.YES_OPTION) {
	            	try {
						boolean result = addgoods(gname,gimage,gdetail,gprice,bid);
						if(result) {
							System.out.println("商品发布成功");
							jTextField21.setText("");
							jTextField22.setText("");
							jTextField23.setText("");
							jTextField24.setText("");
							tabbedPane.setSelectedIndex(2);
							 MbussinessG.g = showallgoods(bid);//更新tablemodel的数据
							 t.updateUI();//更新ui
							
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}}else {
						System.out.println("选择否后执行的代码"); // 点击“否”后执行这个代码块
	        			return;
					}
	            	
	            }});
	 }
    public static void main(String[] args, bussiness myBussiness) throws Exception {
       new Mbussiness(myBussiness);
   
    }
    //删除上架商品
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
		ResultSet rs=pstmt.executeQuery();     //将sql语句传至数据库，返回的值为一个字符集用一个变量接收 
	    
		List<goods> result = new ArrayList<goods>();
		
	    while(rs.next()){    //next（）获取里面的内容
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
		
    //发布商品
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
