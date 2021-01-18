import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
import java.io.*;

class New implements ActionListener  {
	Dialog d;		
	String name,path,content,opfile,str;
	TextArea t;
	File file;				
	editor ed;
	boolean flag1,flag2;
	Frame f;	
	Panel p;	
	Label l;	
	Button b1,b2,b3;		
	
	public New(editor ed,Frame f,File file, TextArea t, boolean flag1,boolean flag2, String name,
		String path, String content,String opfile, String str) {
		
		this.ed = ed;
		this.f = f;
		this.file = file;
		this.t = t;
		this.flag1 = flag1;
		this.flag2 = flag2;
		this.name = name;
		this.path = path;
		this.content = content;
		this.opfile = opfile;
		this.str = str;
		
		d = new Dialog(f,"New",true);
		d.setSize(200,200);
		GridLayout gl = new GridLayout(2,1);
		d.setLayout(gl);
		l = new Label("Do you want to save changes");
		p = new Panel();
		b1 = new Button("Save");		
		b1.addActionListener(this);		
		p.add(b1);		
		b2 = new Button("Don't save");	
		b2.addActionListener(this);		p.add(b2);
		b3 = new Button("Cancel");		
		b3.addActionListener(this);		
		p.add(b3);
		d.add(l);		
		d.add(p);
		d.setLocation(100,100);
		d.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if(str.equals("Save")) {
			try {
				if(file == null){
				 ed.saveas();
				}
				else{
					ed.save();
				}
			}
			catch(IOException e6){
				System.out.println(e6.getMessage());
			}
			file = null;
			t.setText("");
			
			ed.flag1 = false;
			ed.flag2 = false;
			name = "";
			path = "";
			content = "";
			opfile = "";
			str = "";
			f.setTitle("Untitled"); //
			d.dispose();
			d.setVisible(false);
		}
		
		else if(str.equals("Don't save")){
			t.setCaretPosition(0);
			t.setText("");
			file = null;
			ed.flag1 = false;
			ed.flag2 = false;
			name = "";
			path = "";
			content = "";
			opfile = "";
			str = "";
			f.setTitle("Untitled"); //
			d.dispose();
			d.setVisible(false);
		}
		else {
			d.dispose();	
			d.setVisible(false);
		}
	}
}