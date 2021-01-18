import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
import java.io.*;
class exit implements ActionListener{
	Dialog d;
	File file;
	editor ed;
	Frame f;	
	Panel p;	
	Label l;	
	Button b1,b2,b3;		
	public exit(editor ed,Frame f,File file){
		this.ed = ed;
		this.f = f;
		this.file = file;
		d = new Dialog(f,"Exit",true);
		d.setSize(200,200);
		GridLayout gl = new GridLayout(2,1);
		d.setLayout(gl);
		l = new Label("Do you want to save changes");
		p = new Panel();
		b1 = new Button("Save");		
		b1.addActionListener(this);		
		p.add(b1);		
		b2 = new Button("Don't save");	
		b2.addActionListener(this);		
		p.add(b2);
		b3 = new Button("Cancel");		
		b3.addActionListener(this);		
		p.add(b3);
		d.add(l);		
		d.add(p);
		d.setLocation(100,100);
		d.setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		String str = e.getActionCommand();
		if(str.equals("Save")){
			try{
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
			if(ed.name!=null){
				f.dispose();
				f.setVisible(false);
				System.exit(1);
			}
			else{
				
				d.dispose();
				d.setVisible(false);
			}
		}
		else if(str.equals("Don't save")){
			System.exit(1);
		}
		else{
			d.dispose();
			d.setVisible(false);
		}
	}
}