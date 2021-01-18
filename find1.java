import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
import java.io.*;

class find1 implements ActionListener {
	Dialog d;
	Frame f,f1;	
	Panel p1,p2;	
	Label l;	
	Button b1,b2;	
	TextField tf;	
	String word,content;
	TextArea t;
	int start,end,index;
	char c;
	boolean alertWindowShow = false; 
	
	public find1(Frame f, TextArea t) {
		this.f = f;
		this.t = t;
		
		d = new Dialog(f,"Find",false);
		d.setSize(190,100);
		GridLayout gl = new GridLayout(2,1);
		d.setLayout(gl);
		p1 = new Panel();
		l = new Label("Find Next");		
		p1.add(l);		
		tf = new TextField(10);		
		p1.add(tf);
		p2 = new Panel();
		b1 = new Button("Find");	
		b1.addActionListener(this);		
		p2.add(b1);		
		b2 = new Button("Close");	
		b2.addActionListener(this);		
		p2.add(b2);
		d.add(p1);		
		d.add(p2);
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

	public void actionPerformed(ActionEvent e){
		String s = e.getActionCommand();  //s = find;
		word = tf.getText();
		content = t.getText();
		index = t.getCaretPosition();
		start = t.getCaretPosition()+t.getSelectedText().length();
		content = content.replaceAll("\r\n","\n");
		t.setText(content);
		Pattern p = Pattern.compile(Pattern.quote(word));
		Matcher m = p.matcher(content);
		if(s.equals("Find")){
			if(alertWindowShow == false) {
				index = t.getCaretPosition();
				if(m.find(start)){
					f.toFront();
					start=m.start();
					end=m.end();
					t.select(start,end);
				} else {
					if(!m.find(start)){
						t.setCaretPosition(end);
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
	        			t.setCaretPosition(content.length());
	        			alertWindowShow = true;
	        			f.setLocation(500,200);
					}
				}
			}
		}
		else  {           
			t.setCaretPosition(index);
		 	d.dispose();
		 	d.setVisible(false);
		}
	}
}