import java.util.*;
class Init {
public static int n;
}
class Fib extends Thread
{ 
 public void run()
 {
 Scanner sc=new Scanner(System.in);
 System.out.println("Enter the number of terms for fabonacci: ");
int n = sc.nextInt(); 
 Init.n=n;
 Fibonacci fs=new Fibonacci();
 fs.start();
} 
}
class Fibonacci extends Thread
{
 public void run()
 {
 int a=0, b=1, c=0,x; 
 try
 { 
 System.out.println();
 System.out.println("Fibonacci series:");
 while (Init.n>0)
 {
 System.out.print(c+" ");
 a=b;
 b=c;
 c=a+b;
 Init.n-=1;
 }
 }
 catch (Exception ex)
 {
 System.out.println("invalid input");
 }
 }
 public static void main(String args[])
 {
 Fib f=new Fib();
 try{
 f.start();
 }
 catch (Exception ex)
 {
 ex.printStackTrace();
 } 
}
}