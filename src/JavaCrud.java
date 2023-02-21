import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;

public class JavaCrud {

	private JFrame frame;
	private JTextField txtname;
	private JTextField txtusn;
	private JTextField txtsem;
	private JTextField txtcgpa;
	private JTextField txtsearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaCrud window = new JavaCrud();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JavaCrud() {
		initialize();
		Connect();
		table_load();
		
	}

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	String name,usn,semester,cgpa,lsession,dob;
	int date,month,year,choice,dindex,mindex,yindex,cindex;
	private JTable table;
	
	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/proctor","root","");
		}
		catch(ClassNotFoundException es) {
			
		}
		catch(SQLException ex) {
			
		}
	}
	
	  public void table_load()
	    {
	     try
	     {
	    pst = con.prepareStatement("select * from student");
	    rs = pst.executeQuery();
	    table.setModel(DbUtils.resultSetToTableModel(rs));
	}
	     catch (SQLException e)
	     {
	     e.printStackTrace();
	  }
	    }
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 874, 561);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PROCTOR DIARY");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(220, 20, 259, 67);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "STUDENT REGISTRATION", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(23, 97, 380, 272);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("NAME");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(28, 25, 94, 36);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("USN");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(28, 57, 94, 36);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("SEMESTER");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(28, 93, 94, 36);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("CGPA");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_3.setBounds(28, 132, 94, 30);
		panel.add(lblNewLabel_1_3);
		
		txtname = new JTextField();
		txtname.setBounds(132, 36, 227, 19);
		panel.add(txtname);
		txtname.setColumns(10);
		
		txtusn = new JTextField();
		txtusn.setColumns(10);
		txtusn.setBounds(132, 68, 227, 19);
		panel.add(txtusn);
		
		txtsem = new JTextField();
		txtsem.setColumns(10);
		txtsem.setBounds(132, 104, 227, 19);
		panel.add(txtsem);
		
		txtcgpa = new JTextField();
		txtcgpa.setColumns(10);
		txtcgpa.setBounds(132, 137, 227, 19);
		panel.add(txtcgpa);
		
		JLabel lblNewLabel_2 = new JLabel("Attended last meeting?");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(28, 217, 200, 21);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("DATE OF BIRTH");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(28, 168, 136, 30);
		panel.add(lblNewLabel_3);
		
		String dateopt[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        JComboBox<String> cdate = new JComboBox<>(dateopt);
		cdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cdate.setBounds(167, 175, 45, 21);
		panel.add(cdate);
		
		 String monthopt[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	     JComboBox<String> cmonth = new JComboBox<>(monthopt);
		cmonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmonth.setBounds(222, 175, 55, 21);
		panel.add(cmonth);
		
		String yearopt[] = {"1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005"};
        JComboBox<String> cyear = new JComboBox<>(yearopt);
		cyear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cyear.setBounds(287, 175, 58, 21);
		panel.add(cyear);
		
		String choiceopt[] = {"Yes", "No"};
        JComboBox<String> cchoice = new JComboBox<>(choiceopt);
		cchoice.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		cchoice.setBounds(250, 218, 54, 21);
		panel.add(cchoice);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				name = txtname.getText();
				usn = txtusn.getText();
				semester = txtsem.getText();
				cgpa = txtcgpa.getText();
				date = cdate.getSelectedIndex();
				month = cmonth.getSelectedIndex();
				year = cyear.getSelectedIndex();
				dob = cdate.getSelectedItem().toString()+cmonth.getSelectedItem().toString()+cyear.getSelectedItem().toString();
				lsession = cchoice.getSelectedItem().toString();
				
				
				
				try {
					pst = con.prepareStatement("INSERT INTO student(name,usn,semester,cgpa,dob,last_session) values(?,?,?,?,?,?)");
					pst.setString(1, name);
					pst.setString(2, usn);
					pst.setString(3,semester);
					pst.setString(4, cgpa);
					pst.setString(5, dob);
					pst.setString(6, lsession);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Added!");
					table_load();
					          
					txtname.setText("");
					txtusn.setText("");
					txtsem.setText("");
					txtcgpa.setText("");
					cdate.setSelectedIndex(-1);
					cmonth.setSelectedIndex(-1);
					cyear.setSelectedIndex(-1);
					cchoice.setSelectedIndex(-1);
					txtname.requestFocus();
					   }
					 
					catch (SQLException e1)
					        {
					e1.printStackTrace();
					}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(23, 379, 110, 47);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExit.setBounds(146, 379, 110, 47);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtname.setText("");
				txtusn.setText("");
				txtsem.setText("");
				txtcgpa.setText("");
				cdate.setSelectedIndex(-1);
				cmonth.setSelectedIndex(-1);
				cyear.setSelectedIndex(-1);
				cchoice.setSelectedIndex(-1);
				txtname.requestFocus();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnClear.setBounds(266, 379, 110, 47);
		frame.getContentPane().add(btnClear);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(23, 436, 353, 67);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("USN");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(22, 14, 40, 36);
		panel_1.add(lblNewLabel_1_1_1);
		
		txtsearch = new JTextField();
		txtsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
			          
		            String id = txtsearch.getText();
		 
		                pst = con.prepareStatement("select name,usn,semester,cgpa,dob,last_session from student where usn = ?");
		                pst.setString(1, id);
		                ResultSet rs = pst.executeQuery();
		 
		            if(rs.next()==true)
		            {
		              
		                String name = rs.getString(1);
		                String usn = rs.getString(2);
		                String semester = rs.getString(3);
		                String cgpa = rs.getString(4);
		                String dob = rs.getString(5);
		                String lsession = rs.getString(6);
		                
		                
		                txtname.setText(name);
		                txtusn.setText(usn);
		                txtsem.setText(semester);
		                txtcgpa.setText(cgpa);
		                
		                if(dob.substring(0,2).equals("01")) dindex = 0;
		                if(dob.substring(0,2).equals("02")) dindex = 1;
		                if(dob.substring(0,2).equals("03")) dindex = 2;
		                if(dob.substring(0,2).equals("04")) dindex = 3;
		                if(dob.substring(0,2).equals("05")) dindex = 4;
		                if(dob.substring(0,2).equals("06")) dindex = 5;
		                if(dob.substring(0,2).equals("07")) dindex = 6;
		                if(dob.substring(0,2).equals("08")) dindex = 7;
		                if(dob.substring(0,2).equals("09")) dindex = 8;
		                if(dob.substring(0,2).equals("10")) dindex = 9;
		                if(dob.substring(0,2).equals("11")) dindex = 10;
		                if(dob.substring(0,2).equals("12")) dindex = 11;
		                if(dob.substring(0,2).equals("13")) dindex = 12;
		                if(dob.substring(0,2).equals("14")) dindex = 13;
		                if(dob.substring(0,2).equals("15")) dindex = 14;
		                if(dob.substring(0,2).equals("16")) dindex = 15;
		                if(dob.substring(0,2).equals("17")) dindex = 16;
		                if(dob.substring(0,2).equals("18")) dindex = 17;
		                if(dob.substring(0,2).equals("19")) dindex = 18;
		                if(dob.substring(0,2).equals("20")) dindex = 19;
		                if(dob.substring(0,2).equals("21")) dindex = 20;
		                if(dob.substring(0,2).equals("22")) dindex = 21;
		                if(dob.substring(0,2).equals("23")) dindex = 22;
		                if(dob.substring(0,2).equals("24")) dindex = 23;
		                if(dob.substring(0,2).equals("25")) dindex = 24;
		                if(dob.substring(0,2).equals("26")) dindex = 25;
		                if(dob.substring(0,2).equals("27")) dindex = 26;
		                if(dob.substring(0,2).equals("28")) dindex = 27;
		                if(dob.substring(0,2).equals("29")) dindex = 28;
		                if(dob.substring(0,2).equals("30")) dindex = 29;
		                if(dob.substring(0,2).equals("31")) dindex = 30;
		                		                		                		             
		                if(dob.substring(2,5).equals("Jan")) mindex = 0;
		                if(dob.substring(2,5).equals("Feb")) mindex = 1;
		                if(dob.substring(2,5).equals("Mar")) mindex = 2;
		                if(dob.substring(2,5).equals("Apr")) mindex = 3;
		                if(dob.substring(2,5).equals("May")) mindex = 4;
		                if(dob.substring(2,5).equals("Jun")) mindex = 5;
		                if(dob.substring(2,5).equals("Jul")) mindex = 6;
		                if(dob.substring(2,5).equals("Aug")) mindex = 7;
		                if(dob.substring(2,5).equals("Sep")) mindex = 8;
		                if(dob.substring(2,5).equals("Oct")) mindex = 9;
		                if(dob.substring(2,5).equals("Nov")) mindex = 10;
		                if(dob.substring(2,5).equals("Dec")) mindex = 11;
		          
		                if(dob.substring(5,9).equals("1998")) yindex = 0;
		                if(dob.substring(5,9).equals("1999")) yindex = 1;
		                if(dob.substring(5,9).equals("2000")) yindex = 2;
		                if(dob.substring(5,9).equals("2001")) yindex = 3;
		                if(dob.substring(5,9).equals("2002")) yindex = 4;
		                if(dob.substring(5,9).equals("2003")) yindex = 5;
		                if(dob.substring(5,9).equals("2004")) yindex = 6;
		                if(dob.substring(5,9).equals("2005")) yindex = 7;
		                
		                
	                    cdate.setSelectedIndex(dindex);
						cmonth.setSelectedIndex(mindex);
						cyear.setSelectedIndex(yindex);
		                cchoice.setSelectedIndex(choice);
		                
						if((lsession.equals("Yes"))) cindex=0;
						else cindex = 1;
						cchoice.setSelectedIndex(cindex);
		                
		            }  
		            else
		            {
		             txtname.setText("");
		             txtusn.setText("");
		             txtcgpa.setText("");
		             txtsem.setText("");
		             cdate.setSelectedIndex(-1);
					 cmonth.setSelectedIndex(-1);
					 cyear.setSelectedIndex(-1);
					 cchoice.setSelectedIndex(-1);
		            }
		            
		 
		 
		        }
		catch (SQLException ex) {
		          
		        }	
			}
		});
		txtsearch.setColumns(10);
		txtsearch.setBounds(72, 21, 244, 30);
		panel_1.add(txtsearch);
		
		JButton btnUpdatee = new JButton("UPDATE");
		btnUpdatee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name,usn,semester,cgpa,sid,dob,lsession;
				
				name = txtname.getText();
				usn = txtusn.getText();
				semester = txtsem.getText();
				cgpa = txtcgpa.getText();
				dob = cdate.getSelectedItem().toString()+cmonth.getSelectedItem().toString()+cyear.getSelectedItem().toString();

				date = cdate.getSelectedIndex();
				month = cmonth.getSelectedIndex();
				year = cyear.getSelectedIndex();
				
				lsession = cchoice.getSelectedItem().toString();
				sid = txtsearch.getText();				
				try {
					pst = con.prepareStatement("update student set name=?,usn=?,semester=?,cgpa=?,dob=?,last_session=? where usn=?");
					pst.setString(1, name);
					pst.setString(2, usn);
					pst.setString(3,semester);
					pst.setString(4, cgpa);
					pst.setString(5, dob);
					pst.setString(6, lsession);
					pst.setString(7, sid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated!");
					table_load();
					          
					txtname.setText("");
					txtusn.setText("");
					txtsem.setText("");
					txtcgpa.setText("");
					
					cdate.setSelectedIndex(-1);
					cmonth.setSelectedIndex(-1);
					cyear.setSelectedIndex(-1);
					cchoice.setSelectedIndex(-1);
					txtname.requestFocus();
					   }
					 
					catch (SQLException e1)
					        {
					e1.printStackTrace();
					}
				
				
			}
		});
		btnUpdatee.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnUpdatee.setBounds(411, 436, 119, 47);
		frame.getContentPane().add(btnUpdatee);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sid;
				
				sid = txtsearch.getText();
				
				try {
					pst = con.prepareStatement("delete from student where usn = ?");
					pst.setString(1, sid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Deleted!");
					table_load();
					          
					txtname.setText("");
					txtusn.setText("");
					txtsem.setText("");
					txtcgpa.setText("");
					
					cdate.setSelectedIndex(-1);
					cmonth.setSelectedIndex(-1);
					cyear.setSelectedIndex(-1);
					cchoice.setSelectedIndex(-1);
					txtname.requestFocus();
					   }
					 
					catch (SQLException e1)
					        {
					e1.printStackTrace();
					}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelete.setBounds(555, 437, 110, 47);
		frame.getContentPane().add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(406, 88, 429, 338);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
