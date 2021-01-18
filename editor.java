import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
class editor extends WindowAdapter implements ActionListener, KeyListener{
	Frame f;
	boolean flag1,flag2,flag3;
	TextArea t;
	CheckboxMenuItem bld,itlc;
	MenuBar mb;
	Menu m1,m2;
	MenuItem nw,open,save,exit,saveas,find,replace;
	String str;
	String opfile;
	FileDialog fd;
	String content="";
	String path,name;
	int ch;
	File file;
	FileInputStream fis;
	FileOutputStream fos;
	
	public editor() {
		f = new Frame("Editor");
		f.setSize(400,400);
		t = new TextArea();
		//t.setFont(fn);
		f.add(t);
		mb = new MenuBar();
		m1 = new Menu("File");
		m2 = new Menu("Edit");
		nw = new MenuItem("New");					
		nw.addActionListener(this);
		open = new MenuItem("Open...");				
		open.addActionListener(this);
		save = new MenuItem("Save");				
		save.addActionListener(this);
		saveas = new MenuItem("Save As...");		
		saveas.addActionListener(this);
		exit = new MenuItem("Exit");				
		exit.addActionListener(this);
		find = new MenuItem("Find...");				
		find.addActionListener(this);
		replace = new MenuItem("Replace...");		
		replace.addActionListener(this);
		bld=new CheckboxMenuItem("Bold");
        itlc=new CheckboxMenuItem("Italic");  
        itlc.setState(true);
		
		m2.add(bld);  
        m2.add(itlc); 
        m2.addSeparator(); 

		m2.add(find); 
		m2.add(replace);
		m1.add(nw); 
		m1.add(open); 
		m1.add(save); 
		m1.add(saveas); 
		m1.addSeparator(); 
		m1.add(exit);
		mb.add(m1); 
		mb.add(m2);
		f.setMenuBar(mb);
		f.setVisible(true);
		t.addKeyListener(this);
		f.addWindowListener(this);
		
	}
	public void actionPerformed(ActionEvent e){
		str = e.getActionCommand();
		if(str.equals("Open...")){
			try {
				open();
			}
			catch (IOException e1){
				System.out.println(e1.getMessage());
			}
		}
		else if(str.equals("Save As...")){
			try {
				saveas();
			}
			catch (IOException e2){
				System.out.println(e2.getMessage());
			}
		}
		else if(str.equals("Find...")){

			find1 fn  = new find1(f,t);
		}
		else if(str.equals("Replace...")){
			find_replace fr = new find_replace(f,t,this);
		}
		else if(str.equals("Save")){
			try {
				save();
			}
			catch (IOException e2){
				System.out.println(e2.getMessage());
			}
		}
		else if(str.equals("New")){
			New();
		}
		else if(str.equals("Exit")){	
			if(flag1 == true){
				exit ex = new exit(this,f,file);
			}
			else if(flag1 == false){
				f.dispose();
				f.setVisible(false);
				System.exit(1);
			}
		}
	}
	public void open() throws IOException{
		if(t.getText().equals("")){
			fd = new FileDialog(f,"Select and Open",FileDialog.LOAD);
			//fd.setSize(600,650);
			fd.setVisible(true);
			name = fd.getFile();
			path = fd.getDirectory();
			if(name==null){
				return;
			}
			f.setTitle(name);
			file = new File(path,name);
			fis = new FileInputStream(file); 
			while((ch = fis.read()) != -1){
				content += (char)ch;
				t.append((char)ch+"");
			}
			fis.close();
			flag2 = true;
			flag1 = false;
			t.setCaretPosition(0);

			
		}
		else if(!t.getText().equals("")){
			if(flag1 == true){
				Open nw = new Open(this,f,file,t,flag1,flag2,name,path,content,opfile,str);
				fd = new FileDialog(f,"Select and Open",FileDialog.LOAD);
				fd.setSize(100,150);
				fd.setVisible(true);
				name = fd.getFile();
				path = fd.getDirectory();
				if(name==null)
				return;
				f.setTitle(name);
				t.setText("");
				file = new File(path,name);
				fis = new FileInputStream(file); 
				while((ch = fis.read()) != -1){
					content += (char)ch;
					t.append((char)ch+"");
				}
				fis.close();
				flag2 = true;
				flag1 = false;
				t.setCaretPosition(0);
			}
			else {
				fd = new FileDialog(f,"Select and Open",FileDialog.LOAD);
				fd.setSize(100,150);
				fd.setVisible(true);
				name = fd.getFile();
				path = fd.getDirectory();
				if(name ==null){
					return;
				}
				f.setTitle(name);
				t.setText("");
				file = new File(path,name);
				fis = new FileInputStream(file); 
				while((ch = fis.read()) != -1){
					content += (char)ch;
					t.append((char)ch+"");
				}
				fis.close();
				flag2 = true;
				flag1 = false;
				t.setCaretPosition(0);
			}
		}
	}
	public void saveas() throws IOException{
		fd = new FileDialog(f,"Save As",FileDialog.SAVE);
		fd.setSize(100,150);
		fd.setVisible(true);
		name = fd.getFile();
		path = fd.getDirectory();
		if(name==null){
			return;
		}
		
		file = new File(path,name);
		fos = new FileOutputStream(file);
		opfile = t.getText();
		for(int i = 0; i<opfile.length();i++){
			char c = opfile.charAt(i);
			fos.write(c);
		}
		flag2 = true;
		flag1 = false;
		f.setTitle(name);
	}
	public void save() throws IOException{
		if(flag2 == false){
			saveas();
		}
		else if(flag2 == true){
			name = fd.getFile();
			path = fd.getDirectory();
			if(name==null){
				return;
			}
			file = new File(path,name);
			fos = new FileOutputStream(file);
			opfile = t.getText();
			for(int i = 0; i < opfile.length();i++){
				char c = opfile.charAt(i);
				fos.write(c);
			}
			flag1 =  false;
		}
	}
	public void New(){
		if(flag1 == true) {
			New nw = new New(this,f,file,t,flag1,flag2,name,path,content,opfile,str);
		}
		else {
			file = null;
			t.setText("");
			f.setTitle("Untitled"); 
			path = "";
			name = "";
			str ="";
			opfile="";
			flag1 = flag2 = flag3 = false;
			content = "";
			fis = null;
			fos = null;
		}
	}
	
	public void windowClosing(WindowEvent e3){
		if(flag1 == true){
			exit ex = new exit(this,f,file);
		}
		
		else{
			Window w = e3.getWindow();
			w.setVisible(false);
			w.dispose();
			System.exit(1);
		}
	}
	public void keyPressed(KeyEvent ke){
		flag1 = true;
	}
	public void keyReleased(KeyEvent ke1) {}
	//Font fn = new Font("Arial",Font.PLAIN,20);
	//Font fn = new Font();
	public void keyTyped(KeyEvent ke2)    {}
	
	public static void main(String args[]){
		editor ed = new editor();
	}
}