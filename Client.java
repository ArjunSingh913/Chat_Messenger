import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 public class Client extends WindowAdapter implements ActionListener
{                                                                                                                   
JButton b,b1;
Label l,m;
Frame f1;
static JTextArea  tf[]=new JTextArea[3];
Socket s;
ArrayList al=new ArrayList();
 JScrollPane pane2, pane1,pane3;
 static String userName=" ";
DataInputStream din;
DataOutputStream dout;
	public Client()
	{	
	f1=new Frame("Client Server");
		Font ff = new Font("Arjun",Font.BOLD,30);
		Font ff1 = new Font("Arjun",Font.BOLD,25);
		Font ff2 = new Font("Arjun",Font.BOLD,20);
     
	      String labelText =
      "<html><B><FONT COLOR=RED>Welcome</FONT>  To" +
      "<FONT COLOR=BLUE>  Real</FONT>   "+
	  "<FONT COLOR=RED>   Messenger</FONT></B></html>";
	   JLabel coloredLabel =new JLabel(labelText,JLabel.CENTER);
coloredLabel.setFont(ff1);
    coloredLabel.setBorder(BorderFactory.createTitledBorder("The Real Messenger"));
    f1.add(coloredLabel, BorderLayout.NORTH);
   JLabel boldLabel=new JLabel("", JLabel.CENTER);
    boldLabel.setBorder(BorderFactory.createTitledBorder("Chating Inbox"));
   f1.add(boldLabel, BorderLayout.CENTER);
		 f1.addWindowListener(this);
		for(int i=0;i<tf.length;i++)
		{
			tf[i]=new JTextArea(100,50);
			boldLabel.add(tf[i]);
		tf[i].setFont(ff);
		 tf[i].setWrapStyleWord(true);
        tf[i].setLineWrap(true);
		 tf[i].setBackground(Color.BLACK);
tf[i].setForeground(Color.GREEN);
		}
		tf[0].setBounds(20,60,350,500);
		tf[1].setBounds(370,60,300,500);
		tf[2].setBounds(20,560,650,145);
		tf[0].setEditable(false);
		 tf[1].setEditable(false);
		l=new Label("YOUR FRIENDS");
		l.setBounds(430,10,300,50);
		boldLabel.add(l);
		l.setFont(ff1);
		l.setBackground(Color.GREEN);	
		 m=new Label("YOUR CHAT");
		m.setBounds(120,10,300,50);
		boldLabel.add(m);
		m.setFont(ff1);
		m.setBackground(Color.GREEN);
		 b1=new JButton("Emoji");
b1.addActionListener(this);
b1.setBounds(670,650,100,50);
		b=new JButton("Send");
		b.setBounds(670,600,100,50);
		boldLabel.add(b);
		boldLabel.add(b1);
		b.setFont(ff1);
		b1.setFont(ff2);
		 b.addActionListener(this);	 
		 pane1 = new JScrollPane(tf[0]);
        pane1.setBounds(20, 60, 350, 500);
        pane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      boldLabel.add(pane1);	  
  pane2 = new JScrollPane(tf[1]);
        pane2.setBounds(370, 60, 300, 500);
        pane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      boldLabel.add(pane2);
	   pane3 = new JScrollPane(tf[2]);
        pane3.setBounds(20, 560, 650, 145);
        pane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      boldLabel.add(pane3);
		   f1.setSize(860,800);
		   f1.setVisible(true);
f1.setBackground(Color.GREEN);
		 userName = JOptionPane.showInputDialog("User Name (Client)");
		 if(userName==null){
		 System.exit(0);}
		 else{
		 al.add(userName);}
		try
		{
			s=new Socket("LocalHost",10);
			din=new DataInputStream(s.getInputStream());
			dout=new DataOutputStream(s.getOutputStream());
		clientChat();
		}
		catch(Exception e){}
	}
	public void clientChat() throws IOException
	{
		String s1;
		My m=new My(din);
		Thread t1=new Thread(m);
		t1.start();
		Iterator i=al.iterator();
		while(i.hasNext())
		{
			 s1=(String)i.next();
			 tf[1].append("\n " +s1);}
				//BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));		
	dout.writeUTF(userName + " :");
		  dout.flush();
	}
	public void windowClosing(WindowEvent e) {
  f1.dispose();
}
	public void actionPerformed(ActionEvent e1)
{  
try{
	if(e1.getSource()==b){
//	EmogiChooser.dialog.dispose();
		dout.writeUTF(userName + " :" + tf[2].getText());
      dout.flush();
  
	tf[2].setText("");}
	
	if(e1.getSource()==b1){
		EmogiChooser ec=new EmogiChooser();
	}
	}catch(IOException e){System.out.println(e);}
}
	public static void main(String ar[]) throws IOException
	{
	Client kl=new Client();
}}
class My implements Runnable    
{
	DataInputStream din;
	My(DataInputStream din)
	{
		this.din=din;
	}
	public void run() 
	{
		String s2="";
		do
		{
			try
			{
			s2=din.readUTF();	
      Client.tf[0].append("\n " +s2);
			}
			catch(Exception e){}
		}while(!s2.equals("stop"));				
	}	
}

class EmogiChooser extends WindowAdapter 
{                                                                                                                   
 JFrame f2;
JLabel label;
static Dialog dialog;
JPanel p1;
String day = "";
Font ff1 = new Font("Arjun",Font.BOLD,25);
Font ff = new Font("Arjun",Font.BOLD,20);
JButton[] button = new JButton[49];
public EmogiChooser()
	{
		 f2=new JFrame();
	 dialog = new JDialog(f2,"Emoji Chooser");
label = new JLabel();
label.setHorizontalAlignment(SwingConstants.CENTER);

String Text =
      "<html><B><FONT COLOR=RED>Welcome</FONT>  To" +
      "<FONT COLOR=BLUE>  Emoji</FONT>   "+
	  "<FONT COLOR=RED>   World</FONT></B></html>";
label.setText(Text);
label.setFont(ff1);
/**************************************************************************************************************/
 JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.add(label,BorderLayout.CENTER);
	dialog.add(panel,BorderLayout.NORTH);
	String[] Days = { "😄", "🐈", "❤️", "🎂", "🙋", "👍", "💒" };
JPanel p1 = new JPanel(new GridLayout(7, 7));
p1.setPreferredSize(new Dimension(300, 120));
for (int x = 0; x < button.length; x++) {
final int selection = x;
button[x] = new JButton();
if (x > 6){
	button[x].setFont(ff);
button[x].addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
day = button[selection].getActionCommand();
Client.tf[2].append(day);
}
});
}
button[x].setBackground(Color.white);
/***********************************************************************************************************/
if (x < 7) {
	final int selection1 = x;
button[x].setText(Days[x]);
button[x].setFont(ff1);
button[x].setForeground(Color.red);
button[x].addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
	if(selection1==0){
	emoji1();}
	if(selection1==1){
	emoji2();}
	if(selection1==2){
	emoji3();}
	if(selection1==3){
	emoji4();}
	if(selection1==4){
	emoji5();}
	if(selection1==5){
	emoji6();}
	if(selection1==6){
	emoji7();}
}
});
}
p1.add(button[x]);
}
dialog.add(p1);
dialog.setSize(560, 520);
dialog.setVisible(true);
	emoji1();
	}
/*************************************************************************************************************************/
 void emoji1() {
	 String emoj[]={"😄","😴","😈","😍","😘","😚","😗","😜","😝","😙","😛","😳","😊","😉","😁","😗","😌","😒","😞","😣","😚","😂","😭","😪","😥","😈","😏","😑","😅","😓","😩","😫","😨","😱","😠","💀","😤","😖","😆","😋","😷","😎","😮","😬","😄","😕","😍","😴","😘" };
	 for (int x = 7; x < button.length; x++){
   button[x].setText(emoj[x]);}
	}
	void emoji2() {
		String emoji[]={"🐵","🙈","🙉","🙊","🐒","🐶","🐺","🐈","🐱","😻","😽","😼","🙀","🐤","😹","🙉","🐭","🐁","🐹","🐸","🐯","🐨","🐻","🐷","🐽","🐮","🐗","🐴","🐑","🐘","🐝","🐜","🐚","🐠","🐬","🐳","🐒","🐞","🐌","🐙","🐼","🐧","🐳","🐇","🐕","🐓","🐂","🐫","🐍"};
   for (int x = 7; x < button.length; x++){
   button[x].setText(emoji[x]);}
	}
	void emoji3() {
		String emoji[]={"❤","💛","💙","💜","💚","❤","💔","💗","💓","💕","💖","💞","💘","💝","💋","💍","💎","👤","👥","💬","👣","💭","❤","👬"," 👽","💏","💩","👪","💌","💟","👓","🌷","👲","👳","💔","👷","💂","👶","👦","👧","👨","👩","👴","👵","👱","👼" ,"👸","👹","👺"};
	 for (int x = 7; x < button.length; x++){
   button[x].setText(emoji[x]);}
	}
	void emoji4() {
		String emoji[]={"🍵","🍖","🍝","🍛","🍤","🍱","🍣","🍥","🍙","🍘","🍚","🍜","🍲","🍢","🍡","🍳","🍞","🍩","🍮","🍦","🍨","🍧","🎂","🍔","🍕","🍷","🍹","☕","🍼","🍣","🌿","🌾","🍄","🌵","🌴","🌲","🌳","🌰","🌱","🌼","💐","🌸","🍏","🍊","🍋","🍒","🍇","🍉","🍓"};
	 for (int x = 7; x < button.length; x++){
   button[x].setText(emoji[x]);}
	}
	void emoji5() {
		String emoji[]={"👂","👀","👃","👅","👄","🚶","🏃","💃","👯","🙆","🙅","💁","🙋","💆","🎍","🎎","🎒","🎓","🎏","🚶","🎃","👻","🎅","🎄","🎁","🎉","👀","🎈","🙆","🔆","💣","🔪","👶","👦","👧","👃","👩","👴","👵","👱","👼","👸","👹","👺","👂","👽","💩","👅","👄" };
	 for (int x = 7; x < button.length; x++){
   button[x].setText(emoji[x]);}
	}
	void emoji6() {
		String emoji[]={"💅","🔺","🔻","➡","↔","↕","🔄","🔀","👍","👎","👌","👊","✊","✌","👋","✋","👆","👇","👉","👈","🙌","🙏","☝","👏","💪","🖖","✍️","💅","🤳","🤞","🤙","🤛","🤜","🤚","🤝","🤟","🤏","♀️🤌","🔁","🔂","🔝","🔚","🔙","🔛","🔜","🔃","🔼","🔽","⏪"};
	 for (int x = 7; x < button.length; x++){
   button[x].setText(emoji[x]);}
	}
	void emoji7() {
		String emoji[]={"🏠","🏡","🏫","🏢","🏣","🏥","🏦","🏪","🏩","🏨","💒","⛪","🏬","🏤","🌇","🌆","🏯","🏰","⛺","🏭","🗼","🗾","🗻","🌄","🌅","🌃","🗽","🌉","🎠","🎡","⛲","🎢","🏘","🏰","🏯","🏟","🏠","🏡","🏚","🏢","🏬","🏣","🏤","🏥","🏦","🏨","🏩","🏪","🏫"};
	 for (int x = 7; x < button.length; x++){
   button[x].setText(emoji[x]);}
	}

}