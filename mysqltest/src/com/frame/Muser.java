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
        
        //默认关于我jp1
      	JPanel jp1 = new JPanel(new GridLayout(5,1,7,8));
        JPanel jp14 = new JPanel();
        JPanel jp11 = new JPanel();
        JPanel jp12 = new JPanel();
        JPanel jp13 = new JPanel();
        jp11.setBackground(Color.white);
        jp12.setBackground(Color.white);
        jp13.setBackground(Color.white);
        JButton jb11 = new JButton("修改号码");
        JButton jb12 = new JButton("修改地址");
        JLabel jl1 = new JLabel();
        JLabel jl2 = new JLabel();
        //初始化窗口标题和关于我界面
		if(myuser!=null) {
			String username=myuser.getUserName();
			this.setTitle("电商系统之用户----欢迎你！"+username);
			 jp11.add(new JLabel("昵称:"+username));
			 jl1.setText( "联系号码:"+myuser.getUserPhone()); 
			 jl2.setText("地址:"+myuser.getAddress());
		}
		jp13.add(jl1);
		jp13.add(jb11);
		jp12.add(jl2);
		jp12.add(jb12);
		jp1.add(jp14);
		jp1.add(jp11);
		jp1.add(jp13);
		jp1.add(jp12);
        
        //商城jp2
		 JPanel jp2= new JPanel();
		JButton jb1 = new JButton("购买商品");
     	MuserG htm = new  MuserG();
     	//根据 TableModel来创建 Table
	    JTable t = new JTable(htm);
	    JPanel jp21= new JPanel();
	    jp21.add(jb1);
	    JScrollPane jp22 = new JScrollPane(t);
	    jp2.add(jp21);
	    jp2.add(jp22);
    
        //我的订单jp3
      	
       Morder htm2 = new  Morder(myuser);
		 //根据 TableModel来创建 Table
	    JTable t2 = new JTable(htm2);
	    JScrollPane jp3 = new JScrollPane(t2);
        
        // 创建选项卡面板
        final JTabbedPane tabbedPane = new JTabbedPane();


        // 创建第 1 个选项卡
        tabbedPane.addTab("关于我", jp1);

        // 创建第 2 个选项卡
        tabbedPane.addTab("商城", jp2);

        // 创建第 3 个选项卡
        tabbedPane.addTab("我的订单",jp3);
        
   
      
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
        //点击修改号码按钮
        jb11.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e){
            	String inputValue = JOptionPane.showInputDialog("请输入修改后的号码");
            	 System.out.println(inputValue);
            	 try {
					if(updateUserPhone(inputValue,uid)) {
						 jl1.setText( "联系号码:"+inputValue); 
					 }
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
         }});
        //点击修改地址按钮
        jb12.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e){
            	String inputValue = JOptionPane.showInputDialog("请输入修改后的地址");
            	 System.out.println(inputValue);
            	 try {
					if(updateUserAddress(inputValue,uid)) {
						jl2.setText("地址:"+inputValue);
					 }
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
         }});
        //点击购买按钮
        jb1.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e){
            	int res = JOptionPane.showConfirmDialog(null, "是否购买"+selectgoods.getGoodsName(),"购买操作", JOptionPane.YES_NO_OPTION);
        		if (res == JOptionPane.YES_OPTION) {
        			try {
						if(buggoods(selectgoods.getID())&& addorder(uid,selectgoods)) {
							htm.g= showallgoods();//更新商城的数据
							 t.updateUI();//更新ui
							 Morder.o=showallorder(uid);
							 t2.updateUI();
							 tabbedPane.setSelectedIndex(2);
							
 				}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        		} else {
        			System.out.println("选择否后执行的代码"); // 点击“否”后执行这个代码块
        			return;
        		}

         }});

        // 设置默认选中的选项卡
        tabbedPane.setSelectedIndex(0);

        this.setContentPane(tabbedPane);
        this.setVisible(true);
        
        
	}
	public static void main(String[] args, user myuser) throws Exception {
      new Muser(myuser);
    }
	//修改手机号码
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
	//修改地址
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
	//购买商品
	public static boolean buggoods(int selectgoodsid) throws Exception {
		
		String sql = null;
		sql = "update products set status='0' where p_id=?";//status设为0为已购买
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
	//增加订单
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
	
	//展示在线商品
	public static List<goods> showallgoods() throws Exception {
		
		String sql = null;
		sql = "select p_id,p_name,detail,main_image,price,b_name from products,bussiness where status='1' and products.b_id=bussiness.b_id";//默认status设为1，为上线
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = con.prepareStatement(sql);
		 
		ResultSet rs=pstmt.executeQuery();     //将sql语句传至数据库，返回的值为一个字符集用一个变量接收 
	    
		List<goods> result = new ArrayList<goods>();
		
	    while(rs.next()){    //next（）获取里面的内容
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
	//获取所有订单
			public static List<order> showallorder(int uid) throws Exception {
				//CommonUtils.user = new user();
				String sql = null;
				sql = "select * from orders where u_id=?";
				Connection con = dbUtil.getCon();
				PreparedStatement pstmt = con.prepareStatement(sql);
				 pstmt.setInt(1, uid);
				ResultSet rs=pstmt.executeQuery();     //将sql语句传至数据库，返回的值为一个字符集用一个变量接收 
			    
				List<order> result = new ArrayList<order>();
				
			    while(rs.next()){    //next（）获取里面的内容
			    	
			    	 String gname =rs.getString("p_name");
				     String bussinessname = rs.getString("bussname");
				     float gprice = rs.getFloat("total_price");
				     String createtime = rs.getString("createTime");
			    	result.add(new order(uid, gname, gprice, createtime, bussinessname));
			    }
				
				
				return result;
			}
}
