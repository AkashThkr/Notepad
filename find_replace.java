import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
import java.io.*;

class find_replace implements ActionListener {
	Dialog d;
	Frame f;	
	Panel p1,p2,p3;	
	Label l;	
	Button b1,b2,b3,b4;	
	TextField tf1,tf2;	
	String word,content,str;
	TextArea t;
	String newword;
	editor ed;
	int start,end,index;
	char c;
	boolean alertWindowShow = false,alertWindowShow2 = false;

	public find_replace(Frame f, TextArea t,editor ed) {
		this.f = f;
		this.t = t;
		this.ed=ed;
		
		d = new Dialog(f,"Find & Replace",false);
		d.setSize(300,300);
		GridLayout gl = new GridLayout(3,1);
		d.setLayout(gl);
		p1 = new Panel();
		l = new Label(" Find          ");		
		p1.add(l);		
		tf1 = new TextField(10);		
		p1.add(tf1);
		p2 = new Panel();
		l = new Label("Replace");	
		p2.add(l);		
		tf2 = new TextField(10);	
		p2.add(tf2);
		p3 = new Panel();
		b1 = new Button("Find Next");	
		b1.addActionListener(this);		
		p3.add(b1);		
		b2 = new Button("Replace");		
		b2.addActionListener(this);		
		p3.add(b2);
		b3 = new Button("Replace All");	
		b3.addActionListener(this);		
		p3.add(b3);
		b4 = new Button("Close");		
		b4.addActionListener(this);		
		p3.add(b4);
		d.add(p1);		
		d.add(p2);	
		d.add(p3);
		d.setVisible(true);
	    
	    d.addWindowListener(new WindowAdapter() {
	 	    public void windowClosing(WindowEvent e) {
	 		  Window w=e.getWindow();
	 		  w.setVisible(false);
                 w.dispose();
	 	    }
	    });
	    d.setLocation(500,150);
	}
	
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		word = tf1.getText();
		newword = tf2.getText();
		content = t.getText();
		index = t.getCaretPosition();
		start = t.getCaretPosition()+t.getSelectedText().length();
		content = content.replaceAll("\r\n","\n");
		t.setText(content);
		Pattern p = Pattern.compile(Pattern.quote(word));
		Matcher m = p.matcher(content);
		if(s.equals("Find Next")){
			if(alertWindowShow == false) {
				if(m.find(start)){
					f.toFront();
					start = m.start();
					end = m.end();
					t.select(start, end);
				}
				else {
					if(!m.find(start)){
						t.setCaretPosition(content.length());
						Frame f = new Frame("Editor");
	       				f.setSize(60,80);
	        			f.setLayout(new GridLayout(1,0));
	        			Panel p2=new Panel();
				        Label l = new Label("Cannot Found");
	            		p2.add(l);
				        p2.setBackground(Color.red);
				        f.add(p2);
				        f.setVisible(true);
				        f.pack();

	        			f.addWindowListener(new WindowAdapter() {
	            		public void windowClosing(WindowEvent e) {
	              			Window w=e.getWindow();
	              				w.setVisible(false);
	              				w.dispose();
	              				alertWindowShow = false;
	            			}
	        			});
	        			alertWindowShow = true;
	        			f.setLocation(500,150);
	        		}	
				}	
			}
		}
		else if(s.equals("Replace")){
			if(alertWindowShow2 == false) {
				if(m.find(index)){
					f.toFront();
					start = m.start();
					end = m.end();
					System.out.println("start->"+ start);
					System.out.println("end->"+ end);
					t.select(start, end);

					// t.select(m.start(), m.end()+1);
					t.replaceText(newword,m.start(),m.end());
					if(word.length()>newword.length()){
						start = m.end()+1;
					}
					else {
						start = m.start()+1;
					}
				}
				else {
					if(!m.find(start)){
						t.setCaretPosition(content.length());
						Frame f = new Frame("Editor");
       					f.setSize(60,80);
        				f.setLayout(new GridLayout(1,0));
        				Panel p2=new Panel();
			        	Label l = new Label("Cannot Found");
            			p2.add(l);
			        	p2.setBackground(Color.red);
			        	f.add(p2);
			        	f.setVisible(true);
			        	f.pack();
						f.addWindowListener(new WindowAdapter() {
            				public void windowClosing(WindowEvent e) {
              					Window w=e.getWindow();
              					w.setVisible(false);
              					w.dispose();
              					alertWindowShow2 = false;
            				}
        				});
        				alertWindowShow2 = true;
        				t.setCaretPosition(content.length());
        				f.setLocation(500,150);
        			}	
				}	
			} 			ed.flag1=true;
		}
		else if(s.equals("Replace All")){
			if(m.find()){
				f.toFront();
				t.select(m.start(), m.end());
				t.setText(t.getText().replace(t.getSelectedText(),newword));
			}
			ed.flag1=true;
		}
		else{
			t.setCaretPosition(index);
			d.dispose();
			d.setVisible(false);
		}
    }
}