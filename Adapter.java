import java.awt.*;
import java.awt.event.*;
class Adapter implements ActionListener {
    Frame f;
    Button b;
    String str;
    public Adapter() {
        f = new Frame("Editor");
        f.setSize(200,100);
        f.setLayout(new GridLayout(2,0));
        Panel p1=new Panel();
        Label l = new Label("Cannot Found");
        p1.add(l);
		f.add(p1);
        Panel p2=new Panel();
        b = new Button("OK");
        b.setBounds(50,200,300,30);
        b.addActionListener(this);
        p2.add(b);
        p2.setBackground(Color.red);
        f.add(p2);
        f.setVisible(true);
     	f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
              Window w=e.getWindow();
              w.setVisible(false);
              w.dispose();
            }
        });
    }
    
	public void actionPerformed(ActionEvent e1){
		str = e1.getActionCommand();
		if(str.equals("OK")){
			f.dispose();
		 	f.setVisible(false);
		}
	}
	public static void main(String args[]){
		Adapter a=new Adapter();
	}
}
	
