package Ex4;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;
/**
 * @author mulugeta
 * Date - June 16-2019
 *
 *this class has two programs and GUI  Graphics
 *the first program is numbers game, computer vs student using algorithm
 * the computer calculate every round odd index values and even index values
 *then choose the best option to win the game
 *the second program is student vs student without algorithm
 */
public class NumbersGame  {
	private static int[] arr;//array
	private static int sum1;//current player1 sum		
	private static int sum2;//current player sum2
	private static int sum3;//current player sum2
	private static int sumComputer;//final computer
	private static int sumStudent1;//final student1 sum
	private static int sumStudent2;//final student2 sum
	private static int winner_point1,winner_point2;//winners point
	private static String winner1,winner2;//

	//arrays that hold every player chosen  number
	private static int[] s1_ar;
	private static int[] s2_ar;
	private static int[] s3_ar;
	private static int[] s4_ar;
	//array index
	private static int i=0;
	private static int j=0;

	// array index for GUI
	private static int x1=0;
	private static int x2=0;
	private static int x3=0;
	private	static int x4=0;
	//Constructor
	/**
	 * @param arr the given array 
	 */
	NumbersGame(int[] arr){
		if((arr.length%2)!=0) {System.out.println("Array length must be even");return;}
		NumbersGame.arr=arr;
		NumbersGame.s1_ar=new int[arr.length/2];
		NumbersGame.s2_ar=new int[arr.length/2];
		NumbersGame.s3_ar=new int[arr.length/2];
		NumbersGame.s4_ar=new int[arr.length/2];

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
	public static String getWinner1() {
		return winner1;
	}
	public static String getWinner2() {
		return winner2;
	}
	public static int[] getArr() {
		return arr;

	}

	//calculate the values in even index
	/**
	 * @param left left side of the array
	 * @param right right of the array
	 * @return sum of all numbers in even index
	 */
	public static int sumEven(int left, int right){
		int sum = 0;
		for(int k=left+1; k<=right; k=k+2)sum = sum + arr[k];
		return sum;
	}
	//calculate the values in odd index
	/**
	 * @param left left side of the array
	 * @param right right of the array
	 * @return sum of all numbers in odd index
	 */
	public static int sumOdd( int left, int right){
		int sum = 0;
		for(int k=left; k<=right; k=k+2) sum = sum + arr[k];
		return sum;
	}



	/**
	 * Computer Vs Student
	 * Computer choose first and win the game
	 * @return winner sum value
	 */
	public static  int ComputerVsStudent(){
		System.out.println("This is the game");
		for (int i = 0; i < arr.length; i++) {
			System.err.print( arr[i]+",");
		}
		System.out.println();


		boolean odd=true;
		//choose option, right or left side of the array
		int left=0, right=arr.length-1;
		int second;
		//sum of even and odd index values
		int sumE = sumEven(left, right);
		int sumO = sumOdd(left, right);

		while(left<right){
			odd=true;
			/*every round computer check which index values are 
			greater(odd or even)
			 */
			if (sumO < sumE) {
				odd = false;
			}
			else if (sumE == sumO){
				if (arr[left] < arr[right]){
					if (right%2==1) odd = false;
				}
				else if(arr[left] > arr[right]){
					if (left%2==1) odd = false;
				}
			}
			if (left%2==0){//left odd
				if (odd) {//if odd is greeter
					//computer choose the value
					sumComputer = sumComputer + arr[left];
					System.out.println("Computer choose :"+arr[left]);
					//s1 hold every value that the computer choose
					s1_ar[i++]=arr[left];
					/*
					 * subtract chosen value,it help for the next round to 
					 * now greater index values
					 */
					sumO = sumO-arr[left++];

				}
				else{//if even is greater
					sumComputer = sumComputer + arr[right];
					System.out.println("Computer choose :"+arr[right]);
					s1_ar[i++]=arr[right];
					sumE = sumE - arr[right--];
				}
			}
			else{// left even
				if (odd){//if odd is greater
					sumComputer = sumComputer + arr[right];
					System.out.println("Computer choose :"+arr[right]);
					s1_ar[i++]=arr[right];
					sumO = sumO - arr[right--];
				}
				else{//even is greater
					sumComputer = sumComputer + arr[left];
					System.out.println("Computer choose :"+arr[left]);
					s1_ar[i++]=arr[left];
					sumE = sumE - arr[left++];
				}
			}
			boolean chek_num=true;
			//Student turn
			while(chek_num) {
				/*
				 * student can choose right side of array or left
				 * if the student choose number that is not in the correct side
				 * it will print message to choose again
				 */
				Scanner in = new Scanner(System.in);
				System.out.println("Choose Number Only From The Right Side Or Left Side!\"");
				System.err.println(arr[left]+" or "+arr[right]);
				int studentChoose = in.nextInt();
				if(studentChoose==arr[left]) {
					sumStudent1=sumStudent1+arr[left];
					s2_ar[j++]=arr[left];
					second=left++;
					if(second%2==0) sumO = sumO - arr[second];
					else sumE = sumE - arr[second];
					chek_num =false;
				}
				else if(studentChoose==arr[right]) {
					sumStudent1=sumStudent1+arr[right];
					s2_ar[j++]=arr[right];
					second=right--;
					if(second%2==0) sumO = sumO - arr[second];
					else sumE = sumE - arr[second];
					chek_num =false;
				}
				else {
					System.out.println("Pleas Choose Number Only From The Right Side Or Left Side!");
				}

				String equal="";
				if(sumComputer==sumStudent1) equal="Draw";

				winner_point1=Math.max(sumComputer, sumStudent1);
				if(sumComputer>sumStudent1) winner1 = "Computer";
				else if(sumComputer<sumStudent1) winner1 = "Student1";
				else winner1=equal;

			}

		}
		System.out.println("Computer="+sumComputer+", Student="+sumStudent1);
		/*
		 * 	
		 *  Running GUI that show gui graphic with players point
		 *  and the value for every round

		 * SwingUtilities.invokeLater() places an object on the Swing Event Queue. 
		 * Once all current events on the queue are processed,
		 *  the run() method of the object will be called, on the event processing thread.
		 *It is used to ensure that all UI updates are concurrency-safe.
		 */

		SwingUtilities.invokeLater(new Runnable() {
			String Computer = "Computer",player1 = "Student1";
			@Override
			public void run() {
				new GuiGraphic(Computer, player1) ;// Let the constructor do the job

			}
		});
		return winner_point1;
	}//end ComputerVsStudent


	/**
	 * @param size array size
	 * @return array , in the array random integer numbers
	 */
	public static int[] randoomArray(int size) {
		int ans[] =new int[size];
		for (int i = 0; i < ans.length; i++) {
			ans[i]=(int)((Math.random())*100);
		}
		return ans;
	}

	//StudentvsStudent
	/**
	 * in this program there is no algorithm
	 * so every round the student choose in his turn
	 * random side, left side of the array or right
	 * at the end of every game winner can be change
	 * @return winner point
	 */
	public static int StudentVsStudent(){
		//equate to zero
		x1=0;x2=0;
		i=0;j=0;
		//choose option, right or left side of the array
		int left=0, right=arr.length-1;
		int sumStudent_1=0,sumStudent_2=0,first, second;
		//get random number to determine who start first
		first = getrandomNum(sumStudent_1, sumStudent_2);

		if(first==sumStudent_1) second = sumStudent_2;
		else second = sumStudent_1;
		while(left<right){
			if(first==sumStudent_1) {

				//get random side
				int s1 = getrandomNum(left, right);
				sumStudent_1 = sumStudent_1+arr[s1];
				//s3 hold ever numbers  that the student1 choose
				s3_ar[i++] = arr[s1];
				if(s1==left) left++;
				else right--;
				int s2 = getrandomNum(left, right);
				sumStudent_2 = sumStudent_2+arr[s2];
				//s4 hold ever numbers  that the student1 choose
				s4_ar[j++] = arr[s2];
				if(s2==left) left++;
				else right--;
			}
			else {
				int s1 = getrandomNum(left, right);
				sumStudent_2 = sumStudent_2+arr[s1];
				s4_ar[j++] = arr[s1];
				if(s1==left) left++;
				else right--;
				int s2 = getrandomNum(left, right);
				sumStudent_1 = sumStudent_1+arr[s2];
				s3_ar[i++] = arr[s2];
				if(s2==left) left++;
				else right--;
			}
		}
		String equal="";
		if(sumStudent_1==sumStudent_2) equal="Draw";


		if(sumStudent_1>sumStudent_2) winner2 = "Student1";
		else if(sumStudent_1<sumStudent_2) winner2 = "Student2";
		else winner2=equal;
		winner_point2=Math.max(sumStudent_1, sumStudent_2);
		System.out.println("sumStudent_1="+sumStudent_1+", sumStudent_2="+sumStudent_2);

		/*
		 *  Running GUI that show gui graphic with players point
		 *  and the value for every round
		 */
		SwingUtilities.invokeLater(new Runnable() {
			String player1 = "Student1",player2 = "Student2";
			@Override
			public void run() {
				new GuiGraphic(player1, player2) ;// Let the constructor do the job

			}
		});
		return winner_point2;
	}



	//get random number between left index or right
	/**
	 *  @param left left side of the array
	 * @param right right of the array
	 * @return random side left or right
	 */
	public static int getrandomNum(int left,int right) {
		Random random = new Random();
		boolean isleft = random.nextBoolean();
		if (isleft) return left;
		else return right;
	}


	//GUI
	/**
	 * @author mulugeta
	 *
	 *GuiGraphic with JButton,JTextField,Icon
	 *for every game it will show
	 *{the given array,the number every player choose in current round
	 *	total points and winners name									
	 */
	static class GuiGraphic extends JFrame implements ActionListener{

		private JButton Array=new JButton("Array");// Array JButton
		//computerVSstudent JButtons
		private JButton Computer;    
		private JButton Student1;    
		//studentVSstudent JButtons
		private JButton Studentvs1;   	 
		private JButton Studentvs2;   	 
		// Winner JButton
		private JButton Winner1;   		
		private JButton Winner2;   		
		// JTextField Current ArrayValues
		private JTextField ArrayValues;  
		// JTextField Computer Final Value
		private JTextField ComputerValues; 
		// JTextField Student1 Final Value
		private JTextField Student1Values; 
		//JTextField Studentvs1 Final Value
		private JTextField Studentvs1Values;
		//JTextField Studentvs2 Final Value
		private JTextField Studentvs2Values;
		//JTextField WinnerValues Final Value
		private JTextField Winner1Values; 
		private JTextField Winner2Values;
		private int sum1 = 0 ;        // Accumulated sum, init to 0

		/**
		 * @param player_1  player 1
		 * @param player_2  player 2
		 */
		public GuiGraphic(String player_1,String player_2){

			this.setBounds(200,200,400,150);
			setTitle("GameGrapic");
			setSize(1000,450);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			setVisible(true);
			Container cp = getContentPane();
			Container c = getContentPane();
			c.setLayout(new FlowLayout());
			// The content-pane sets its layout
			cp.setLayout(new GridLayout(4, 4));  

			cp.add(Array);
			//add jtextfield that shows array value
			ArrayValues=new JTextField(100);
			add(ArrayValues);
			//font color and size
			ArrayValues.setFont(new Font("Arial", Font.BOLD, 14));
			ArrayValues.setForeground(Color.black);
			ArrayValues.setBackground(Color.yellow);
			//set icon
			Array.setIcon(new ImageIcon("res//Array4.png"));
			//Action listener allows to do something the button press
			Array.addActionListener(this);

			/*
			 * if the game is computer and student
			 * ComputervsStudent
			 */
			if ((player_1=="Computer")) {
				//Create and add new jbutton name computer
				Computer = new JButton("Computer");
				add(Computer);
				//create and add new jtexfield that shows computer current value
				//and total  point
				ComputerValues = new JTextField(10);
				add(ComputerValues);
				//color and font 
				ComputerValues.setFont(new Font("Arial", Font.HANGING_BASELINE, 20));
				ComputerValues.setForeground(Color.blue);
				ComputerValues.setBackground(Color.yellow);
				//if computer jbutton has pressed it will show total point and current value
				ComputerValues.addActionListener(this);
				Computer.addActionListener(this);
				//add icon to computer jbutton
				Computer.setIcon(new ImageIcon("res//computer1.png"));

				//create and add new jbutton for student (second player)
				Student1 = new JButton(player_2);
				add(Student1);
				//add new jtextfield for student that shows  current value
				//and total  point
				Student1Values = new JTextField(10);
				add(Student1Values);
				//font and color
				Student1Values.setFont(new Font("Arial", Font.HANGING_BASELINE, 20));
				Student1Values.setForeground(Color.blue);
				Student1Values.setBackground(Color.yellow);
				//if student jbutton has pressed it will show total point and current value
				Student1Values.addActionListener(this);
				Student1.addActionListener(this);
				//add icon
				Student1.setIcon(new ImageIcon("res//student1.png"));

				//creat and add jbutton for winner 
				Winner1 = new JButton("The winner is");
				add(Winner1);
				//add new jtextfield for winner that show the winner name and total point
				Winner1Values = new JTextField(5);
				add(Winner1Values);
				//color and font
				Winner1Values.setFont(new Font("Arial", Font.HANGING_BASELINE, 30));
				Winner1Values.setForeground(Color.blue);
				Winner1Values.setBackground(Color.GREEN);
				//if winner jbutton has pressed it will show total winner point and  name
				Winner1Values.addActionListener(this);
				Winner1.addActionListener(this);
				//add icon
				Winner1.setIcon(new ImageIcon("res//winner1.png"));
			}
			//if the game is student Vs student
			//StudentVsStudent
			else if((player_1=="Student1")||(player_1=="Student2")) {
				//creat and add jbutton for student1
				Studentvs1 = new JButton(player_1);
				add(Studentvs1);
				//add new jtextfield for student1 that shows student current value and total point
				Studentvs1Values = new JTextField(5);
				add(Studentvs1Values);
				//color and point
				Studentvs1Values.setFont(new Font("Ariel",Font.HANGING_BASELINE,20));
				Studentvs1Values.setForeground(Color.blue);
				Studentvs1Values.setBackground(Color.yellow);
				//if  student1 jbutton has pressed it will show total point and current value
				Studentvs1.addActionListener(this);
				Studentvs1.setIcon(new ImageIcon("res//student1.png"));
				//creat and add jbutton for student2
				Studentvs2 = new JButton(player_2);
				add(Studentvs2);
				//add new jtextfield for student2 that shows student current value and total point
				Studentvs2Values = new JTextField(10);
				add(Studentvs2Values);
				//collor and font
				Studentvs2Values.setFont(new Font("Ariel",Font.HANGING_BASELINE,20));
				Studentvs2Values.setForeground(Color.blue);
				Studentvs2Values.setBackground(Color.yellow);
				//if  student2 jbutton has pressed it will show total point and current value
				Studentvs2.addActionListener(this);
				Studentvs2.setIcon(new ImageIcon("res//student2.png"));

				//creat and add jbutton for winner 
				Winner2 = new JButton("The winner is");
				add(Winner2);
				//add new jtextfield for winner that show the winner name and total point
				Winner2Values = new JTextField(5);
				add(Winner2Values);
				//color and font
				Winner2Values.setFont(new Font("Ariel",Font.HANGING_BASELINE,20));
				Winner2Values.setForeground(Color.blue);
				Winner2Values.setBackground(Color.green);
				//if winner jbutton has pressed it will show total winner point and  name
				Winner2.addActionListener(this);
				//add icon
				Winner2.setIcon(new ImageIcon("res//winner1.png"));

			}

		}

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			int choosenNum=0;
			String valueToBeInserted="";

			for( int j=0;j< NumbersGame.arr.length;j++)
			{
				valueToBeInserted=valueToBeInserted + " "+" "+ NumbersGame.arr[j]+" "+ "" +"," ;
			}

			ArrayValues.setText(valueToBeInserted);

			/*
			 * if  computer (computer vs student) button has pressed it will 
			 * show total point and current value
			 */
			if(e.getSource()==Computer) {
				//If finished to show all computer values button will be enable to press 
				if(x1==s1_ar.length) Computer.setEnabled(false);
				if(x1<s1_ar.length) {
					choosenNum=s1_ar[x1];
					sum1=sum1+s1_ar[x1++];
					ComputerValues.setText("sum   "+" "+ sum1+" ,    "+"Computer Choose : "
							+ choosenNum);
				}

			}
			/*
			 * if  Student  (computer vs student) button has pressed it will 
			 * show total point and current value
			 */
			if(e.getSource()==Student1) {
				//If finished to show all student values button will be enable to press 
				if(x2==s2_ar.length) { Student1.setEnabled(false);}
				if(x2<s2_ar.length) {
					choosenNum=s2_ar[x2];
					sum2=sum2+s2_ar[x2++];
					Student1Values.setText("sum   "+" "+ sum2 + " ,    "+"Student Choose : "
							+ choosenNum);
				}
			}
			/*
			 * if  Student1  (student1 vs student2) button has pressed it will 
			 * show total point and current value
			 */
			if(e.getSource()==Studentvs1) {
				//If finished to show all student1 values button will be enable to press 
				if(x2==s3_ar.length) { Studentvs1.setEnabled(false);}
				if(x2<s3_ar.length) {
					choosenNum=s3_ar[x2];
					sum2=sum2+s3_ar[x2++];
					Studentvs1Values.setText("sum  "+sum2+ " ,    "+"Student 1 Choose : "
							+ choosenNum);
				}
			}
			/*
			 * if  Student1  (student1 vs student2) button has pressed it will 
			 * show total point and current value
			 */
			if(e.getSource()==Studentvs2) {
				//If finished to show all student2 values button will be enable to press 
				if(x3==s4_ar.length) { Studentvs2.setEnabled(false);}
				else if(x3<s4_ar.length) {
					choosenNum=s4_ar[x3];
					sum3=sum3+s4_ar[x3++];
					Studentvs2Values.setText("sum   "+sum3 + " ,    "+"Student 2 Choose : "
							+ choosenNum);
				}
			}
			if(e.getSource()==Winner1) {
				Winner1Values.setText("winner      "+NumbersGame.getWinner1());
			}
			if(e.getSource()==Winner2) {
				Winner2Values.setText("winner       "+NumbersGame.getWinner2());
			}
		}
	}
	public static void main(String[] args) {
		NumbersGame d = new NumbersGame(randoomArray(10));
		d.ComputerVsStudent();
		d.StudentVsStudent();

	}
}
