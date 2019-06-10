package Ex4;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

public class NumbersGame extends JFrame implements ActionListener {
	private static int[] arr;//array
	private static int sum1;//current player1 sum		
	private static int sum2;//current player sum2
	private static int sumComputer;//final computer
	private static int sumStudent1;//final student1 sum
	private static int sumStudent2;//final student2 sum
	private static int winner_point;//winner point
	private static String winner;//
	
	//GUI
	private JButton Array=new JButton("Array");// Array JButton
	private JButton Computer;    	//Computer JButton
	private JButton Student1;    	//Student1 JButton
	private JButton Student2;   	 //Student2 JButton
	private JButton Winner;   		// Winner JButton
	
	private JTextField ArrayValues;  // JTextField Current ArrayValues
	private JTextField ComputerValues; // JTextField Computer Final Value
	private JTextField Student1Values; // JTextField Student1 Final Value
	private JTextField Student2Values; //JTextField Student2 Final Value
	private JTextField WinnerValues; //JTextField Winner Final Value
	
private int[] s1_ar;
private int[] s2_ar;
private int i=0,j=0;
int x=0,x2=0;
	//Constructor
	NumbersGame(int[] arr){
		this.arr=arr;
		this.s1_ar=new int[arr.length/2];
		this.s2_ar=new int[arr.length/2];
		
	}
	public static int getSum1() {
		return sum1;
	}
	public static int getSum2() {
		return sum2;
	}
	public static int getSumComputer() {
		return sumComputer;
	}
	public static int getSumStudent1() {
		return sumStudent1;
	}
	public static int getSumStudent2() {
		return sumStudent2;
	}
	public static String getWinner() {
		return winner;
	}
	public static int[] getArr() {
		return arr;

	}

	//zugi
	public static int sumEven(int left, int right){
		int sum = 0;
		for(int k=left+1; k<=right; k=k+2)sum = sum + arr[k];
		return sum;
	}
	//ezugi
	public static int sumOdd( int left, int right){
		int sum = 0;
		for(int k=left; k<=right; k=k+2) sum = sum + arr[k];
		return sum;
	}

	//GameImproved

	public  int ComputerVsStudent(){
		boolean odd=true;
		int left=0, right=arr.length-1;
		int second;
		int se = sumEven(left, right);
		int so = sumOdd(left, right);
		this.setBounds(200,200,400,150);
		setTitle("GameGrapic");
		setSize(600,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//setVisible(true);
		Container cp = getContentPane();

		cp.setLayout(new GridLayout(6, 6));   // The content-pane sets its layout
		cp.add(Array);

		ArrayValues=new JTextField(100);
		add(ArrayValues);
		Array.addActionListener(this);
		

			Computer = new JButton("Computer");
			add(Computer);
			ComputerValues = new JTextField(10);
			add(ComputerValues);
			Computer.addActionListener(this);

			Student1 = new JButton("Student1");
			add(Student1);
			Student1Values = new JTextField(10);
			add(Student1Values);
			Student1.addActionListener(this);
			
			Winner = new JButton("The winner is");
			add(Winner);
			WinnerValues = new JTextField(5);
			add(WinnerValues);
			Winner.addActionListener(this);

		while(left<right){
			odd=true;
			if (so < se) {
				odd = false;
			}
			else if (se == so){
				if (arr[left] < arr[right]){
					if (right%2==1) odd = false;
				}
				else if(arr[left] > arr[right]){
					if (left%2==1) odd = false;
				}
			}
			if (left%2==0){//left odd
				if (odd) {//if odd is greeter
					sumComputer = sumComputer + arr[left];
					s1_ar[i++]=arr[left];
					System.out.println(sum1);
					so = so-arr[left++];

				}
				else{
					sumComputer = sumComputer + arr[right];
					s1_ar[i++]=arr[right];
					se = se - arr[right--];
				}
			}
			else{// left even
				if (odd){
					sumComputer = sumComputer + arr[right];
					s1_ar[i++]=arr[right];
					so = so - arr[right--];
				}
				else{
					sumComputer = sumComputer + arr[left];
					s1_ar[i++]=arr[left];
					se = se - arr[left++];
				}
			}
			sumStudent1 = sumStudent1+Math.max(arr[left], arr[right]);
			s2_ar[j++]=Math.max(arr[left], arr[right]);
			if(Math.max(arr[left], arr[right])==arr[left]) {
				second=left++;
				if(second%2==0) so = so - arr[second];
				else se = se - arr[second];
			}
			else {
				second=right--;
				if(second%2==0) so = so - arr[second];
				else se = se - arr[second];
			}
		}
		SwingUtilities.invokeLater(new Runnable() {

			String Computer = "Computer",player1 = "Student1",player2="Student2",Array = "Array";
			@Override
			public void run() {
				setVisible(true); // Let the constructor do the job

			}
		});
		System.out.println("Computer="+sumComputer+", Student="+sumStudent1);
		winner_point=Math.max(sumComputer, sumStudent1);
		if(sumComputer>sumStudent1) winner = "Computer";
		else winner = "Student1";
		return winner_point;
	}

	//StudentvsStudent
	public static int StudentVsStudent(){
		int left=0, right=arr.length-1;
		int sumStudent_1=0,sumStudent_2=0,first, second;

		first = getrandomNum(sumStudent_1, sumStudent_2);
		if(first==sumStudent_1) second = sumStudent_2;
		else second = sumStudent_1;
		while(left<right){
			if(first==sumStudent_1) {
				int s1 = getrandomNum(left, right);
				sumStudent_1 = sumStudent_1+arr[s1];
				if(s1==left) left++;
				else right--;
				int s2 = getrandomNum(left, right);
				sumStudent_2 = sumStudent_2+arr[s2];
				if(s2==left) left++;
				else right--;
			}
			else {
				int s1 = getrandomNum(left, right);
				sumStudent_2 = sumStudent_2+arr[s1];
				if(s1==left) left++;
				else right--;
				int s2 = getrandomNum(left, right);
				sumStudent_1 = sumStudent_1+arr[s2];
				if(s2==left) left++;
				else right--;
			}
		}
		System.out.println("sumStudent_1="+sumStudent_1+", sumStudent_2="+sumStudent_2);
		return sumStudent_1;
	}


	//get random number between left index or right
	public static int getrandomNum(int left,int right) {
		Random random = new Random();
		boolean isleft = random.nextBoolean();
		if (isleft) return left;
		else return right;
	}
	public void actionPerformed(ActionEvent e) {
		
		
		
		String valueToBeInserted="";
		for( int j=0;j< NumbersGame.arr.length;j++)
		{
			valueToBeInserted=valueToBeInserted + " "+" "+ NumbersGame.arr[j]+" "+ "" +"," ;
		}
		
		ArrayValues.setText(valueToBeInserted);
		
		if(e.getSource()==Computer) {
			sum1=sum1+s1_ar[x++];
			ComputerValues.setText("sum ="+" "+ sum1);
			
		}
		if(e.getSource()==Student1) {
			sum2=sum2+s2_ar[x2++];
			Student1Values.setText("sum ="+" "+sum2);
		}
		if(e.getSource()==Student2) {
			Student2Values.setText("sum ="+" " +sum2);
		}
		if(e.getSource()==Winner) {
			WinnerValues.setText(getWinner());
		}
	}
//	static class GuiGraphic extends JFrame implements ActionListener{
//
//		private JButton Array=new JButton("Array");// Array JButton
//		private JButton Computer;    	//Computer JButton
//		private JButton Student1;    	//Student1 JButton
//		private JButton Student2;   	 //Student2 JButton
//		private JButton Winner;   		// Winner JButton
//		
//		private JTextField ArrayValues;  // JTextField Current ArrayValues
//		private JTextField ComputerValues; // JTextField Computer Final Value
//		private JTextField Student1Values; // JTextField Student1 Final Value
//		private JTextField Student2Values; //JTextField Student2 Final Value
//		private JTextField WinnerValues; //JTextField Winner Final Value
//		private int sum1 = 0,smu2 = 0 ;        // Accumulated sum, init to 0
//
////		public GuiGraphic(String player_1,String player_2){
////			sum1	= sum1+NumbersGame.getSum1();
////			sum2 =smu2+NumbersGame.getSum2();    
//////			sum1=NumbersGame.getSum1();
//////			sum2=NumbersGame.getSum2()
////			this.setBounds(200,200,400,150);
////			setTitle("GameGrapic");
////			setSize(600,300);
////			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////
////			setVisible(true);
////			Container cp = getContentPane();
////
////			cp.setLayout(new GridLayout(6, 6));   // The content-pane sets its layout
////			cp.add(Array);
////
////			ArrayValues=new JTextField(100);
////			add(ArrayValues);
//			Array.addActionListener(this);
//
//			//ComputervsStudent
//			if (player_1=="Computer") {
//
//		
//				
//				Computer = new JButton("Computer");
//				add(Computer);
//				ComputerValues = new JTextField(10);
//				add(ComputerValues);
//				Computer.addActionListener(this);
//
//				Student1 = new JButton(player_2);
//				add(Student1);
//				Student1Values = new JTextField(10);
//				add(Student1Values);
//				Student1.addActionListener(this);
//				
//				Winner = new JButton("The winner is");
//				add(Winner);
//				WinnerValues = new JTextField(5);
//				add(WinnerValues);
//				Winner.addActionListener(this);
//			}
//			//StudentVsStudent
//			else if(player_1=="Student_1") {
//				Student1 = new JButton(player_1);
//				add(Student1);
//				Student1Values = new JTextField(5);
//				add(Student1Values);
//				Student1.addActionListener(this);
//
//				Student2 = new JButton(player_2);
//				add(Student2);
//				Student2Values = new JTextField(10);
//				add(Student2Values);
//				Student2.addActionListener(this);
//				
//				Winner = new JButton("The winner is");
//				add(Winner);
//				WinnerValues = new JTextField(5);
//				add(WinnerValues);
//				Winner.addActionListener(this);
//
//			}
//			else {
//				Student1 = new JButton(player_1);
//				add(Student1);
//				Student1Values = new JTextField(10);
//				add(Student1Values);
//				Student1.addActionListener(this);
//
//				Student2 = new JButton(player_2);
//				add(Student2);
//				Student2Values = new JTextField(10);
//				add(Student2Values);
//				Student2.addActionListener(this);
//				
//				Winner = new JButton("The winner is");
//				add(Winner);
//				WinnerValues = new JTextField(5);
//				add(WinnerValues);
//				Winner.addActionListener(this);
//			}
//
//		}
//
	//	@Override
//		public void actionPerformed(ActionEvent e) {
//			String valueToBeInserted="";
//			for( int j=0;j< NumbersGame.arr.length;j++)
//			{
//				valueToBeInserted=valueToBeInserted + " "+" "+ NumbersGame.arr[j]+" "+ "" +"," ;
//			}
//			
//			ArrayValues.setText(valueToBeInserted);
//			
//			if(e.getSource()==Computer) {
//
//				ComputerValues.setText("sum ="+" "+ sum1);
//				
//			}
//			if(e.getSource()==Student1) {
//				Student1Values.setText("sum ="+" "+ NumbersGame.getSum2());
//			}
//			if(e.getSource()==Student2) {
//				Student2Values.setText("sum ="+" " +NumbersGame.getSum2());
//			}
//			if(e.getSource()==Winner) {
//				WinnerValues.setText(getWinner());
//			}
//		}

	public static void main(String[] args) {
		System.out.println("THIS IS A GAME:");
		//int []arr = {7, 1, 3, 9, 6, 0, 3, 2, 2, 7};
		//		int []arr = {10,9,17,14,17,1};
		int []arr = {1,3,6,1,3,6};
		//		int []arr = {6, 0, 2, 7, 4, 4, 1, 3};//g2=14, gop=17
		//		int []arr = {10,9,17,14,17,16,17,0 };
		NumbersGame d = new NumbersGame(arr);
		d.ComputerVsStudent();
		d.StudentVsStudent();
	}
}
