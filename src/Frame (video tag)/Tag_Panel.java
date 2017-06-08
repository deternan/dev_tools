
/*
 *  Menu:
 *  http://stackoverflow.com/questions/11980151/java-jmenuitem-actionlistener
 *  Time:
 *  
 *  Version: February 4, 2016	09:18 AM
 * 	Last revision: February 25, 2016	11:07 AM
 * 
 *  Jar
 *  joda-time-2.4.jar
 *  json-simple-1.1.1.jar
 */

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.TimeZone;

import javax.swing.JLabel;
import javax.swing.JButton;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;









import javax.swing.JComboBox;
import javax.swing.JTextArea;

import java.awt.SystemColor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Tag_Panel extends JFrame  implements ActionListener
{	
	// Menu
	JMenuBar menuBar;
	// Time
	Calendar now;
	final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		// ISO 8601
		String nowAsISO;
	private Timer timer;
	Calendar press_time;
	
	// Member
	JComboBox comboBox_member;
	// Task
	JLabel NewLabel_Task;
	
	private JPanel contentPane;
	// Panel_1
	JTextField textField_1;
	JButton Button_1;
	JLabel lblNewLabel_time;	
	JPanel panel_1;	
	// Panel
	Hashtable<Integer,Color> table;	
	// Panel_2.a
	JPanel panel_2a;
	private JComboBox comboBox_2a;
	private JComboBox comboBox_2b;
	JLabel Label_2a_min;
	JLabel Label_2a_sec;
	JButton Button_2a_start;
	JButton Button_2a_record;
	// Panel 2.b
	JLabel Label_2b_min;
	JLabel Label_2b_sec;
	JButton Button_2b_start;
	JButton Button_2b_record;
	// Panel Note
	JTextArea textArea;
		// Save
		JButton Save_Button;
	// JSON
	JSONArray Arr_list;
	JSONObject Json_all;
	String Json_all_str = "";
	
	// Data get
	private int Get_hour;
	private int Get_minute;
	private int Get_sec;
	
	// Get String or Selected
	private int Sel_member;
		// Task
		private String Task_str;
		// Task 1
		private String Input_str;
		// Task 2.a
		private String CB_2a_select_str;
		private int Time_gap_2a;
		// Task 2.b
		private String CB_2b_select_str;
		private int Time_gap_2b;
		// Task 2.c
		private String CB_2c_select_str;
		
	// Time (Check)	
	private boolean time_gap_2a_check = false;		// Exist: true;  non exist: false
	private boolean time_gap_2b_check = false;		// Exist: true;  non exist: false
	// Task
	private String task;
	
	// Timer
	JLabel Sec_NewLabel;
	JLabel Min_NewLabel;
	JLabel Hour_NewLabel;
	private int time_count = 0;
	JButton start_Button;
	JButton stop_Button;
	Timer displayTimer;
	private int timer_sec;
	private int timer_min;
	private int timer_hour;
		// 2a
		Timer displayTimer_2a;
		private int time_count_2a;
		private int timer_sec_2a;
		private int timer_min_2a;
		// 2b
		Timer displayTimer_2b;
		private int time_count_2b;
		private int timer_sec_2b;
		private int timer_min_2b;
	// Output
	private String output_fold = "C:\\";	
	private String output_name;
	private String output_path;
		
	// Temp String
	private String textArea_str = "";
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() 
			{				
				try {
					Tag_Panel frame = new Tag_Panel();
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
	public Tag_Panel() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Label (Initialize)
				lblNewLabel_time = new JLabel("New label");
				lblNewLabel_time.setBounds(10, 10, 144, 15);
				contentPane.add(lblNewLabel_time);
				// Task
				NewLabel_Task = new JLabel("選擇要記錄哪一個Task");
				NewLabel_Task.setBounds(140, 10, 140, 15);
				contentPane.add(NewLabel_Task);
				NewLabel_Task.setEnabled(false);
				// Member 
				comboBox_member = new JComboBox();
				comboBox_member.setBounds(292, 7, 120, 21);
				contentPane.add(comboBox_member);				
				comboBox_member.addItem("請選擇人員");
				comboBox_member.addItem("1");
				comboBox_member.addItem("2");
				comboBox_member.addItem("3");
				comboBox_member.addItem("4");
				comboBox_member.addItem("5");
				comboBox_member.addItem("6");
				comboBox_member.addItem("7");
				comboBox_member.setActionCommand("comboBox_member");        
				comboBox_member.addActionListener(this);
		
		// Panel 1 (Task 1)
		Panel_1();				
		// Panel 2 (Task 2.a)
		Panel_2a();
		// Panel 2 (Task 2.b)
		Panel_2b();		
		// Note
		Panel_Note();
		
			// Timer
			// Sec
			Sec_NewLabel = new JLabel("0");
			Sec_NewLabel.setBounds(184, 52, 30, 15);
			contentPane.add(Sec_NewLabel);
			// 
			JLabel lblNewLabel_1 = new JLabel(" : ");
			lblNewLabel_1.setBounds(167, 52, 16, 15);
			contentPane.add(lblNewLabel_1);
			// Min
			Min_NewLabel = new JLabel("0");
			Min_NewLabel.setBounds(152, 52, 22, 15);
			contentPane.add(Min_NewLabel);
			//
			JLabel lblNewLabel_3 = new JLabel(" : ");
			lblNewLabel_3.setBounds(127, 52, 16, 15);
			contentPane.add(lblNewLabel_3);
			// Hour
			Hour_NewLabel = new JLabel("0");
			Hour_NewLabel.setBounds(109, 52, 18, 15);
			contentPane.add(Hour_NewLabel);
						
			// Time Listener 
			Time_Listener();
			
			// Start
			start_Button = new JButton("Start");
			start_Button.setBounds(228, 48, 87, 23);
			contentPane.add(start_Button);
			start_Button.setActionCommand("Timer Start");  
			start_Button.addActionListener(this);
			start_Button.setEnabled(false);
			// Stop
			stop_Button = new JButton("Pause");
			stop_Button.setBounds(325, 48, 87, 23);
			contentPane.add(stop_Button);
			stop_Button.setActionCommand("Timer Pause");        
			stop_Button.addActionListener(this);						
			stop_Button.setEnabled(false);
		// Menu
		Menu();
		
		// Time
		Time_Now();
		
		//
		JSON_initional();
		
	}
	
	private void Panel_1()
	{
		panel_1 = new JPanel();
		panel_1.setBounds(10, 81, 414, 61);
		panel_1.setToolTipText("Task 1");
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setLayout(null);		
		contentPane.add(panel_1);
			// TextField
			textField_1 = new JTextField();
			textField_1.setBounds(10, 10, 311, 21);
			textField_1.setColumns(10);
			panel_1.add(textField_1);	
			textField_1.setEnabled(false);
			// Button
			Button_1 = new JButton("Submit");
			Button_1.setBounds(327, 9, 77, 23);
			Button_1.setActionCommand("Button 1a");        
			Button_1.addActionListener(this);
			panel_1.add(Button_1);
			Button_1.setEnabled(false);
	}
	
	private void Panel_2a()
	{
		panel_2a = new JPanel();
		panel_2a.setToolTipText("Task 2.a");
		panel_2a.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2a.setBounds(10, 152, 414, 61);
		panel_2a.setLayout(null);
		contentPane.add(panel_2a);
			// JComboBox			
			Create_comboBox_2a();
			panel_2a.add(comboBox_2a);
			comboBox_2a.setEnabled(false);
			comboBox_2a.setActionCommand("comboBox 2.a"); 
			comboBox_2a.addActionListener(this);
			
			Label_2a_min = new JLabel("min");
			Label_2a_min.setBounds(214, 13, 30, 15);
			panel_2a.add(Label_2a_min);
			
			JLabel lblNewLabel_5 = new JLabel(" : ");
			lblNewLabel_5.setBounds(232, 13, 10, 15);
			panel_2a.add(lblNewLabel_5);
			
			Label_2a_sec = new JLabel("sec");
			Label_2a_sec.setBounds(247, 13, 30, 15);
			panel_2a.add(Label_2a_sec);
			
			Button_2a_start = new JButton("Start");
			Button_2a_start.setBounds(290, 9, 75, 23);
			panel_2a.add(Button_2a_start);
			Button_2a_start.setActionCommand("Button_2a_Start"); 
			Button_2a_start.addActionListener(this);
			
			Button_2a_record = new JButton("Record");
			Button_2a_record.setBounds(290, 35, 75, 23);
			panel_2a.add(Button_2a_record);
			Button_2a_record.setActionCommand("Button_2a_Record");
			Button_2a_record.addActionListener(this);
		
		Button_2a_start.setEnabled(false);	
		Button_2a_record.setEnabled(false);
	}
	
	private void Panel_2b()
	{
		JPanel panel_2b = new JPanel();		
		panel_2b.setLayout(null);
		panel_2b.setToolTipText("Task 2.a");
		panel_2b.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2b.setBounds(10, 223, 414, 61);
		contentPane.add(panel_2b);
			// JComboBox			
			Create_comboBox_2b();
			panel_2b.add(comboBox_2b);
			comboBox_2b.setEnabled(false);
			comboBox_2b.setActionCommand("comboBox 2.b");
			
			Label_2b_min = new JLabel("min");
			Label_2b_min.setBounds(214, 13, 30, 15);
			panel_2b.add(Label_2b_min);
			
			JLabel lblNewLabel_2 = new JLabel(" : ");
			lblNewLabel_2.setBounds(232, 13, 10, 15);
			panel_2b.add(lblNewLabel_2);
			
			Label_2b_sec = new JLabel("sec");
			Label_2b_sec.setBounds(247, 13, 30, 15);
			panel_2b.add(Label_2b_sec);
			
			Button_2b_start = new JButton("Start");
			Button_2b_start.setBounds(290, 9, 75, 23);
			panel_2b.add(Button_2b_start);
			Button_2b_start.setActionCommand("Button_2b_Start"); 
			Button_2b_start.addActionListener(this);
			
			Button_2b_record = new JButton("Record");
			Button_2b_record.setBounds(290, 35, 75, 23);
			panel_2b.add(Button_2b_record);
			comboBox_2b.addActionListener(this);
			Button_2b_record.setActionCommand("Button_2b_Record");
			Button_2b_record.addActionListener(this);
			
			// Panel 2.c
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel.setBounds(10, 294, 414, 61);
			panel.setLayout(null);
			contentPane.add(panel);
						
		
		Button_2b_start.setEnabled(false);	
		Button_2b_record.setEnabled(false);
	}
	
	private void Panel_Note()
	{
		JPanel panel_note = new JPanel();
		panel_note.setBounds(10, 365, 414, 100);
		panel_note.setLayout(null);
		contentPane.add(panel_note);
				
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setVisible(true);
		textArea.setForeground(SystemColor.windowText);
		//textArea.setBounds(0, 0, 260, 66);
		//panel_note.add(textArea);
		/*
		JScrollPane scroll = new JScrollPane (textArea);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	          scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    scroll.setVisible(true);
		panel_note.add(textArea);
		panel_note.add(scroll);
		*/
		JScrollPane scr = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		textArea.setBounds(0, 0, 260, 100);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scr.setBounds(0, 0, 260, 100);
		panel_note.add(scr);
		
		// Save
		Save_Button = new JButton("Save");
		Save_Button.setBounds(280, 50, 87, 23);
		panel_note.add(Save_Button);
		Save_Button.setEnabled(false);
		Save_Button.setActionCommand("Save");
		Save_Button.addActionListener(this);
	}
	
	private void Time_Listener()
	{
		ActionListener listener = new ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
			    //System.out.println(time_count);
	    		time_count++;
	    		Timer_transfer(time_count);
	    		
	    		// Timer Sec	    		
	    		Sec_NewLabel.setText(String.valueOf(timer_sec));
	    		// Timer Min
	    		Min_NewLabel.setText(String.valueOf(timer_min));
	    		// Timer Hour
	    		Hour_NewLabel.setText(String.valueOf(timer_hour));
			  }
			};						
			displayTimer = new Timer(1000, listener);
			displayTimer.setInitialDelay(0);
			//displayTimer.start();
	}
	
	private void Timer_transfer(int time_count)
	{
		timer_sec = time_count % 60;
		timer_min =  (time_count / 60) % 60;
		timer_hour = (time_count / (60 * 60)) % 24;
		//timer_sec = time_count % 3;
		//timer_min =  (time_count / 3) % 3;
		//timer_hour = (time_count / (3 * 3)) % 24;
		
		//System.out.println(timer_hour+":"+timer_min+":"+timer_sec);
	}
	
	// -----------------------------
	
	private void Create_comboBox_2a()
	{
		table = new Hashtable<Integer,Color>();
        table.put(1, Color.RED);
        table.put(2, Color.RED);
        table.put(3, Color.BLACK);
        table.put(4, Color.BLACK);
        
        comboBox_2a = new JComboBox();        
        comboBox_2a.setBounds(10, 10, 160, 21);	
		comboBox_2a.addItem("請選擇動作");
		comboBox_2a.addItem("手放在桌上");
		comboBox_2a.addItem("托腮");
		comboBox_2a.addItem("舉手");
		comboBox_2a.addItem("伸懶腰");	
		
		comboBox_2a.setRenderer(new MyListCellRenderer(table));
	}
	
	private void Create_comboBox_2b()
	{
		table = new Hashtable<Integer,Color>();
        table.put(1, Color.RED);
        table.put(2, Color.RED);
        table.put(3, Color.RED);
        table.put(4, Color.RED);
        table.put(5, Color.RED);
        table.put(6, Color.RED);
        table.put(7, Color.RED);
        table.put(8, Color.BLACK);
        table.put(9, Color.BLACK);
        table.put(10, Color.BLACK);
        table.put(11, Color.BLACK);
        table.put(12, Color.BLACK);
        table.put(13, Color.BLACK);
        
        
        comboBox_2b = new JComboBox();						
		comboBox_2b.setBounds(10, 10, 160, 21);
		comboBox_2b.addItem("請選擇動作");
		comboBox_2b.addItem("手放在桌上");
		comboBox_2b.addItem("手放在桌下 (玩手機)");
		comboBox_2b.addItem("托腮 (手靠近臉頰)");
		comboBox_2b.addItem("手交叉胸前(雙手環胸)");
		comboBox_2b.addItem("頭轉向特定方位(非教學處)");
		comboBox_2b.addItem("打瞌睡 (閉起眼睛)");
		comboBox_2b.addItem("低頭");
		comboBox_2b.addItem("舉手");
		comboBox_2b.addItem("伸懶腰");
		comboBox_2b.addItem("打哈欠 (嘴巴打開)");
		comboBox_2b.addItem("點頭");
		comboBox_2b.addItem("搖頭");
		comboBox_2b.addItem("轉筆");
		
		comboBox_2b.setMaximumRowCount(14);		
		comboBox_2b.setRenderer(new MyListCellRenderer(table));	
	}
	
	// Menu
	private void Menu() 
	{
		// Creates a menubar for a JFrame
        menuBar = new JMenuBar();         
        // Add the menubar to the frame
        setJMenuBar(menuBar);         
        // Define and add two drop down menu to the menubar
        JMenu fileMenu = new JMenu("Task");       
        menuBar.add(fileMenu);        
                 
        JMenuItem Task1 = new JMenuItem("Task 1", KeyEvent.VK_N);
        Task1.setActionCommand("Task 1");        
        Task1.addActionListener(this);
        JMenuItem Task2_a = new JMenuItem("Task 2.a", KeyEvent.VK_N);
        Task2_a.setActionCommand("Task 2.a");        
        Task2_a.addActionListener(this);
        JMenuItem Task2_b = new JMenuItem("Task 2.b", KeyEvent.VK_N);
        Task2_b.setActionCommand("Task 2.b");        
        Task2_b.addActionListener(this);
        JMenuItem Task2_c = new JMenuItem("Task 2.c", KeyEvent.VK_N);
        Task2_c.setActionCommand("Task 2.c");        
        Task2_c.addActionListener(this);
        
        fileMenu.add(Task1);        
        fileMenu.add(Task2_a);
        fileMenu.add(Task2_b);
        fileMenu.add(Task2_c);
	}
	
	private void Time_Now()
	{				
		new Timer(1000, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) 
	        {
	            now = Calendar.getInstance();
	            lblNewLabel_time.setText(dateFormat.format(now.getTime()));
	            //if(press_time!=null)
	            {
	            	//second_gap = now.getTimeInMillis() - press_time.getTimeInMillis();
		            //second =  second_gap/1000;		            
	            }
	        }
	    }).start();
	}
	
	private void Time_ISO8601()
	{
		//TimeZone tz = TimeZone.getTimeZone("UTC");		
		TimeZone tz = TimeZone.getTimeZone(ZoneOffset.of("+08:00"));
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");		
		df.setTimeZone(tz);
		nowAsISO = df.format(new Date());
	}
	
	private void JSON_initional()
	{
		Arr_list = new JSONArray();
		Json_all = new JSONObject();
		
		Json_all_str = "[";
	}
	
	private void Remove_redundace()
	{
		//Json_all_str = Json_all_str.substring(0, Json_all_str.length() - 1);
		//String last_str = Json_all_str.substring(Json_all_str.length()-1, Json_all_str.length());
		Json_all_str = Json_all_str.substring(0, Json_all_str.length()-1);
		Json_all_str += "]";
		Json_all_str = Json_all_str.replace("}]{", "},{");

		//System.out.println(last_str);
	}
	
	private void JSON_build()
	{		
		JSONObject obj = new JSONObject();
		// Name		Sel_member
		obj.put("Name", Sel_member);
		// Task
		obj.put("Task", Task_str);
		// Task 1 (Input)
		obj.put("Type input", Input_str);
		// Task 2.a	
		if(task.equalsIgnoreCase("Task2a")){
			obj.put("Activity", CB_2a_select_str);
			
			if(time_gap_2a_check == true){
				obj.put("Time gap", Time_gap_2a);
			}else{
				obj.put("Time gap", 0);
			}			
		}else if(task.equalsIgnoreCase("Task2b")){
			obj.put("Activity", CB_2b_select_str);
			
			if(time_gap_2b_check == true){
				obj.put("Time gap", Time_gap_2b);
			}else{
				obj.put("Time gap", 0);
			}
		}else if(task.equalsIgnoreCase("Task2c")){
			
		}
		
		// Time 
		obj.put("Time", time_count);
		// Timestamp
		obj.put("Timestamp", nowAsISO);			
		
		Json_all_str += obj.toString()+",";
	}
	
	public void actionPerformed(ActionEvent e)
    {
        // Menu
		Menu_actionPerformed(e);		        
        // Member
		Member_actionPerformed(e);        
        // Panel 1 Button (Task 1)
		Panel_1_actionPerformed(e);		
        // Panel 2 Button (Task 2.a)
		Panel_2a_actionPerformed(e);
		// Panel 2 
		Panel_2b_actionPerformed(e);
		
		if(e.getActionCommand().equalsIgnoreCase("Timer Start")){
			displayTimer.start();
			stop_Button.setEnabled(true);
			start_Button.setEnabled(false);
			//System.out.println("Start");
			if(displayTimer_2a!=null){
				displayTimer_2a.start();
			}
			if(displayTimer_2b!=null){
				displayTimer_2b.start();
			}
		}
		
		if(e.getActionCommand().equalsIgnoreCase("Timer Pause")){			
			displayTimer.stop();
			if(displayTimer_2a!=null){
				displayTimer_2a.stop();		
			}
			if(displayTimer_2b!=null){
				displayTimer_2b.stop();
			}
			start_Button.setEnabled(true);
			stop_Button.setEnabled(false);
			//System.out.println("Stop");
		}
		
		// Panel Note	
		if(e.getActionCommand().equalsIgnoreCase("Save")){
			textArea.removeAll();
			//textArea.setText(Json_all_str);
			System.out.println(Json_all_str);
						 
			// Output
			Output_file();
			
			File fout = new File(output_path);
			try {
				FileOutputStream fos = new FileOutputStream(fout);
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
				
				bw.write(Json_all_str);
				bw.close();
				System.out.println("Output Finished");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
    }
	
	private void Menu_actionPerformed(ActionEvent e)
	{
		if("Task 1".equals(e.getActionCommand())){
            //JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
        	System.out.println("Task 1");
        	task = "Task1";
        	NewLabel_Task.setText("Task 1");
        	Task_str = "Task 1";
        	CB_2a_select_str = "";
        	CB_2b_select_str = "";
        	CB_2c_select_str = "";
        	textField_1.setEnabled(true);
        	Button_1.setEnabled(true);        	
        	comboBox_2a.setEnabled(false);
        	comboBox_2a.setSelectedIndex(0);
        	comboBox_2b.setEnabled(false);
        	comboBox_2b.setSelectedIndex(0);
        	Button_2a_start.setEnabled(false);
        	Button_2a_record.setEnabled(false);
        	Button_2b_start.setEnabled(false);
        	Button_2b_record.setEnabled(false);
        }
        if("Task 2.a".equals(e.getActionCommand())){
        	System.out.println("Task 2.a");
        	task = "Task2a";
        	NewLabel_Task.setText("Task 2.a");
        	Task_str = "Task 2.a";
        	CB_2b_select_str = "";
        	CB_2c_select_str = "";
        	textField_1.setEnabled(false);
        	Button_1.setEnabled(false);
        	comboBox_2a.setEnabled(true);
        	comboBox_2b.setEnabled(false);
        	comboBox_2b.setSelectedIndex(0);
        	//Button_2a_start.setEnabled(true);
        	//Button_2a_record.setEnabled(true);
        	Button_2b_start.setEnabled(false);
        	Button_2b_record.setEnabled(false);
        }
        if("Task 2.b".equals(e.getActionCommand())){
        	System.out.println("Task 2.b");
        	task = "Task2b";
        	NewLabel_Task.setText("Task 2.b");
        	Task_str = "Task 2.b";
        	CB_2a_select_str = "";
        	CB_2c_select_str = "";
        	textField_1.setEnabled(false);
        	Button_1.setEnabled(false);
        	comboBox_2a.setSelectedIndex(0);        	        	        	
        	comboBox_2a.setEnabled(false);
        	comboBox_2b.setEnabled(true);
        	//Button_2b_start.setEnabled(true);
        	//Button_2b_record.setEnabled(true);
        	Button_2a_start.setEnabled(false);;
        	Button_2a_record.setEnabled(false);
        }
        if("Task 2.c".equals(e.getActionCommand())){
        	System.out.println("Task 2.c");
        	task = "Task2c";
        	NewLabel_Task.setText("Task 2.c");
        	Task_str = "Task 2.c";
        	CB_2a_select_str = "";
        	CB_2b_select_str = "";
        	textField_1.setEnabled(false);
        	Button_1.setEnabled(false);        	        	
        	comboBox_2a.setEnabled(false);
        	comboBox_2b.setEnabled(false);
        	Button_2a_start.setEnabled(false);
        	Button_2a_record.setEnabled(false);
        	Button_2b_start.setEnabled(false);
        	Button_2b_record.setEnabled(false);
        }
	}

	private void Member_actionPerformed(ActionEvent e)
	{
		if("comboBox_member".equalsIgnoreCase(e.getActionCommand())){
        	if(comboBox_member.getSelectedIndex() == 1){
        		Sel_member = 1;        		
        		start_Button.setEnabled(true);
        		Save_Button.setEnabled(true);
        	}else if(comboBox_member.getSelectedIndex() == 2){
        		Sel_member = 2;
        		start_Button.setEnabled(true);
        		Save_Button.setEnabled(true);
        	}else if(comboBox_member.getSelectedIndex() == 3){
        		Sel_member = 3;
        		start_Button.setEnabled(true);
        		Save_Button.setEnabled(true);
        	}else {
        		Sel_member = 0;
        		start_Button.setEnabled(false);
        		Save_Button.setEnabled(false);
        	}
        	//System.out.println(Sel_member);
        }
	}
	
	private void Panel_1_actionPerformed(ActionEvent e)
	{
		if("Button 1a".equals(e.getActionCommand())){        	
        	// Time (8601)
        	Time_ISO8601();
        	
        	//System.out.println(dateFormat.format(now.getTime().getHours()));
        	//System.out.println(dateFormat.format(now.getTime().getMinutes()));
        	//System.out.println(now.getTime());
        	Input_str = textField_1.getText();       	
        	//System.out.println(Input_str+"	"+nowAsISO);
        	
        	JSON_build();
        	Remove_redundace();
        	
        	textField_1.setText("");
        	//Button_1.setEnabled(false);
        	
        	//System.out.println(Json_all_str);
        }
	}
	
	private void Panel_2a_actionPerformed(ActionEvent e)
	{
		textArea_str = "";
		
		// Button
		if(e.getActionCommand().equalsIgnoreCase("Button_2a_Start"))
		{        	
			Listener_2a_Button();
			Button_2a_record.setEnabled(true);		
			Button_2a_start.setEnabled(false);
			textArea_str = "";
			textArea.setText(textArea_str);
			//System.out.println("Button 2a Start");
        }
		if(e.getActionCommand().equalsIgnoreCase("Button_2a_Record"))
		{
			if(time_gap_2a_check == true){
				Button_2a_start.setEnabled(true);
			}
			
			
			if(time_gap_2a_check == true){
				Button_2a_record.setEnabled(false);
			}else{
				Button_2a_record.setEnabled(true);
			}
			
			
			Time_ISO8601();
        	// Rest Time
        		press_time = Calendar.getInstance();
        		Time_Now();
			
			Time_gap_2a =  time_count_2a;		
			JSON_build();
			Remove_redundace();
			//System.out.println("Button 2a Record");
			//System.out.println(Json_all_str);
			
			// textArea
			textArea_str();
			
			
			displayTimer_2a.stop();
			displayTimer_2a.restart();
			displayTimer_2a.stop();
			
			Label_2a_sec.setText("0");
			Label_2a_min.setText("0");	
			
			Label_2a_sec.setText("sec");
    		Label_2a_min.setText("min");
		}
		
        // ComboBox
        if(e.getActionCommand().equalsIgnoreCase("comboBox 2.a"))
        {        	
        	if(comboBox_2a.getSelectedIndex() == 1){
        		time_gap_2a_check = true;
        		CB_2a_select_str = "手放在桌上";
        		// Rest Time        			
        		//press_time = Calendar.getInstance();
        		//Time_Now();
        		
        		Button_2a_start.setEnabled(true);
        		Button_2a_start.setVisible(true);
        		Button_2a_record.setEnabled(false);
        		Button_2a_record.setVisible(true);
        		
        	}else if(comboBox_2a.getSelectedIndex() == 2){
        		time_gap_2a_check = true;
        		
        		CB_2a_select_str = "托腮";        		
        		// Rest Time        			
        		//press_time = Calendar.getInstance();
        		//Time_Now();
        		
        		Button_2a_start.setEnabled(true);
        		Button_2a_start.setVisible(true);
        		Button_2a_record.setEnabled(false);
        		Button_2a_record.setVisible(true);
        		        		
        	}else if(comboBox_2a.getSelectedIndex() == 3){ 
        		time_gap_2a_check = false;        		
        		CB_2a_select_str = "舉手";
        		
        		Button_2a_start.setEnabled(false);
        		//Button_2a_start.setVisible(true);
        		Button_2a_record.setEnabled(true);
        		//Button_2a_record.setVisible(true);
        		Label_2a_sec.setText("sec");
        		Label_2a_min.setText("min");
        		
        	}else if(comboBox_2a.getSelectedIndex() == 4){  
        		time_gap_2a_check = false;        		
        		CB_2a_select_str = "伸懶腰";
        		
        		Button_2a_start.setEnabled(false);        		
        		Button_2a_record.setEnabled(true);
        		Label_2a_sec.setText("sec");
        		Label_2a_min.setText("min");
        		
        	}else {       
        		time_gap_2a_check = false;        		
        		CB_2a_select_str = "";        
        	}        	
        }
	}
	
	private void Panel_2b_actionPerformed(ActionEvent e)
	{
		textArea_str = "";
		
		// Button
		if(e.getActionCommand().equalsIgnoreCase("Button_2b_Start"))
		{        	
			Listener_2b_Button();
			Button_2b_record.setEnabled(true);
			Button_2b_start.setEnabled(false);
			textArea_str = "";
			textArea.setText(textArea_str);
			
			//System.out.println("Button 2b Start");
        }
		if(e.getActionCommand().equalsIgnoreCase("Button_2b_Record"))
		{
			if(time_gap_2b_check == true){
				Button_2b_start.setEnabled(true);
			}			
			
			if(time_gap_2b_check == true){
				Button_2b_record.setEnabled(false);
			}else{
				Button_2b_record.setEnabled(true);
			}
			
			Time_ISO8601();
        	// Rest Time
        		press_time = Calendar.getInstance();
        		Time_Now();
			
			Time_gap_2b =  time_count_2b;		
			JSON_build();
			Remove_redundace();
			//System.out.println("Button 2a Record");
			//System.out.println(Json_all_str);
			
			// textArea
			textArea_str();
			
			displayTimer_2b.stop();
			displayTimer_2b.restart();
			displayTimer_2b.stop();
			
			Label_2b_sec.setText("0");
			Label_2b_min.setText("0");
			
			Label_2b_sec.setText("sec");
    		Label_2b_min.setText("min");
        	//System.out.println("Button 2b Record");
		}
		
		// ComboBox
		if(e.getActionCommand().equalsIgnoreCase("comboBox 2.b"))
        {
        	if(comboBox_2b.getSelectedIndex() == 1){
        		time_gap_2b_check = true;        		
        		CB_2b_select_str = "手放在桌上";
        		Button_2b_start.setEnabled(true);
            	Button_2b_record.setEnabled(false);
        		
        	}else if(comboBox_2b.getSelectedIndex() == 2){
        		time_gap_2b_check = true;        		
        		CB_2b_select_str = "手放在桌下 (玩手機)";
        		Button_2b_start.setEnabled(true);
        		Button_2b_record.setEnabled(false);
            			        	
        	}else if(comboBox_2b.getSelectedIndex() == 3){
        		time_gap_2b_check = true;        		
        		CB_2b_select_str = "托腮 (手靠近臉頰)";
        		Button_2b_start.setEnabled(true);
        		Button_2b_record.setEnabled(false);        		
        		
        	}else if(comboBox_2b.getSelectedIndex() == 4){
        		time_gap_2b_check = true;        		
        		CB_2b_select_str = "手交叉胸前(雙手環胸)";
        		Button_2b_start.setEnabled(true);
        		Button_2b_record.setEnabled(false);        		

        	}else if(comboBox_2b.getSelectedIndex() == 5){
        		time_gap_2b_check = true;        		
        		CB_2b_select_str = "頭轉向特定方位(非教學處)";
        		Button_2b_start.setEnabled(true);
        		Button_2b_record.setEnabled(false);
        		
        	}else if(comboBox_2b.getSelectedIndex() == 6){
        		time_gap_2b_check = true;        		
        		CB_2b_select_str = "打瞌睡 (閉起眼睛)";
        		Button_2b_start.setEnabled(true);
        		Button_2b_record.setEnabled(false);
        		
        	}else if(comboBox_2b.getSelectedIndex() == 7){
        		time_gap_2b_check = true;        		
        		CB_2b_select_str = "低頭";
        		Button_2b_start.setEnabled(true);
        		Button_2b_record.setEnabled(false);        		
            	
        	}else if(comboBox_2b.getSelectedIndex() == 8){
        		time_gap_2b_check = false;
        		CB_2b_select_str = "舉手";
        		Button_2b_start.setEnabled(false);
            	Button_2b_record.setEnabled(true);
            	Label_2b_sec.setText("sec");
        		Label_2b_min.setText("min");
            	
        	}else if(comboBox_2b.getSelectedIndex() == 9){
        		time_gap_2b_check = false;        		
        		CB_2b_select_str = "伸懶腰";        		
        		Button_2b_start.setEnabled(false);
        		Button_2b_record.setEnabled(true);
        		Label_2b_sec.setText("sec");
        		Label_2b_min.setText("min");
        		
        	}else if(comboBox_2b.getSelectedIndex() == 10){
        		time_gap_2b_check = false;        		
        		CB_2b_select_str = "打哈欠 (嘴巴打開)";
        		Button_2b_start.setEnabled(false);
        		Button_2b_record.setEnabled(true);
        		Label_2b_sec.setText("sec");
        		Label_2b_min.setText("min");
        		
        	}else if(comboBox_2b.getSelectedIndex() == 11){
        		time_gap_2b_check = false;        		
        		CB_2b_select_str = "點頭";
        		Button_2b_start.setEnabled(false);
        		Button_2b_record.setEnabled(true);
        		Label_2b_sec.setText("sec");
        		Label_2b_min.setText("min");
        		
        	}else if(comboBox_2b.getSelectedIndex() == 12){
        		time_gap_2b_check = false;        		
        		CB_2b_select_str = "搖頭";
        		Button_2b_start.setEnabled(false);
        		Button_2b_record.setEnabled(true);
        		Label_2b_sec.setText("sec");
        		Label_2b_min.setText("min");
        		
        	}else if(comboBox_2b.getSelectedIndex() == 13){
        		time_gap_2b_check = false;        		
        		CB_2b_select_str = "轉筆";
        		Button_2b_start.setEnabled(false);
        		Button_2b_record.setEnabled(true);
        		Label_2b_sec.setText("sec");
        		Label_2b_min.setText("min");
        		
        	}else{
        		time_gap_2b_check = false;        		
        		CB_2b_select_str = "";
        		Button_2b_start.setEnabled(false);
            	Button_2b_record.setEnabled(false);
            	Label_2b_sec.setText("sec");
        		Label_2b_min.setText("min");
        	}
        	
        }
	}

	private void Listener_2a_Button()
	{
		time_count_2a = 0;
		
		ActionListener listener = new ActionListener(){			
			public void actionPerformed(ActionEvent event)
			{				
				//System.out.println(time_count_2a);
				time_count_2a++;
				
				timer_sec_2a = time_count_2a % 60;
				timer_min_2a =  (time_count_2a / 60) % 60;				
	    		
	    		// Timer Sec	    		
				Label_2a_sec.setText(String.valueOf(timer_sec_2a));
	    		// Timer Min
				Label_2a_min.setText(String.valueOf(timer_min_2a));
			  }
			};
			if(displayTimer_2a!=null){				
				displayTimer_2a.restart();				
			}else{
				displayTimer_2a = new Timer(1000, listener);
				displayTimer_2a.setInitialDelay(0);			
				displayTimer_2a.start();
			}
	}

	private void Listener_2b_Button()
	{
		time_count_2b = 0;
		
		ActionListener listener = new ActionListener(){			
			public void actionPerformed(ActionEvent event)
			{				
				System.out.println(time_count_2b);
				time_count_2b++;
				
				timer_sec_2b = time_count_2b % 60;
				timer_min_2b =  (time_count_2b / 60) % 60;				
	    		
	    		// Timer Sec	    		
				Label_2b_sec.setText(String.valueOf(timer_sec_2b));
	    		// Timer Min
				Label_2b_min.setText(String.valueOf(timer_min_2b));
			  }
			};
			if(displayTimer_2b!=null){				
				displayTimer_2b.restart();				
			}else{
				displayTimer_2b = new Timer(1000, listener);
				displayTimer_2b.setInitialDelay(0);			
				displayTimer_2b.start();
			}
	}
	
	private void textArea_str()
	{
		textArea_str+= "目前影片時間: "+String.valueOf(time_count)+"\n";
		
		if(task.equalsIgnoreCase("Task1")){
			
		}else if(task.equalsIgnoreCase("Task2a")){
			if(time_gap_2a_check == true){
				textArea_str+= "動作: "+CB_2a_select_str+"\n";
				textArea_str+= "花費時間: "+String.valueOf(time_count_2a);			
			}else{
				textArea_str+= "動作: "+CB_2a_select_str+"\n";
				textArea_str+= "已記錄";
			}
			
		}else if(task.equalsIgnoreCase("Task2b")){
			if(time_gap_2b_check == true){
				textArea_str+= "動作: "+CB_2b_select_str+"\n";
				textArea_str+= "花費時間: "+String.valueOf(time_count_2b);
			}else{
				textArea_str+= "動作: "+CB_2b_select_str+"\n";
				textArea_str+= "已記錄";
			}
			
		}else if(task.equalsIgnoreCase("Task2c")){
			
		}
		
		
		textArea.setText(textArea_str);
	}
	
	private void Output_file()
	{
		// Time Now
		SimpleDateFormat nowdate = new java.text.SimpleDateFormat("yyyy-MM-dd HHmmss");
		nowdate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		output_name = nowdate.format(new java.util.Date());			
		output_path = output_fold + output_name + ".txt";
	}
	
}

// --------------------------------------------------------------------------------------------

class MyListCellRenderer extends DefaultListCellRenderer
{
	Hashtable<Integer,Color> table;

    public MyListCellRenderer(Hashtable<Integer,Color> table)
    {
        this.table=table;

        // Set opaque for the background to be visible
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList jc,Object val,int idx,boolean isSelected,boolean cellHasFocus)
    {
        // Set text (mandatory)
        setText(val.toString());

        // Set the foreground according to the selected index
        setForeground(table.get(idx));

            // Set your custom selection background, background
            // Or you can get them as parameters as you got the table
            if(isSelected) setBackground(Color.LIGHT_GRAY);
            else setBackground(Color.WHITE);

        return this;
    }
}



