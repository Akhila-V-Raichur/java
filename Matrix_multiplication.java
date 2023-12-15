import java.util.*;
class Init {
public static int m,n,k;
public static int[][] a = new int[m][k] ;
public static int[][] b = new int[k][n] ;
public static int[][] c = new int[m][n] ;
}
class Read extends Thread
{ 
 public void run()
 {
 Scanner sc=new Scanner(System.in);
 System.out.println("Enter the number of rows for matrix A ");
 int m= sc.nextInt(); 
 System.out.println("Enter the number of columns for matrix A ");
 int k = sc.nextInt(); 
 Init.m=m;
 Init.k=k;
 System.out.println("Enter the number of rows for matrix B ");
 int x= sc.nextInt(); 
 System.out.println("Enter the number of columns for matrix B ");
 int n= sc.nextInt();Init.n=n; 
 if(x!=k)
 System.out.print("Invalid");
 else
 {
 Ary ar=new Ary();
 ar.start();
 }
}
 
}
class Ary extends Thread
{
 public void run()
 {
 int[][] a = new int[Init.m][Init.k];
 int[][] b = new int[Init.k][Init.n];
 Scanner sc=new Scanner(System.in);
 try
 { 
 System.out.println("Enter terms of matrix A ");
 for(int i=0;i<Init.m;i++)
 for(int j=0;j<Init.k;j++)
 a[i][j]=sc.nextInt();
 
 System.out.println("Enter terms of matrix B ");
 for(int i=0;i<Init.k;i++)
 for(int j=0;j<Init.n;j++)
 b[i][j]=sc.nextInt(); 
 Init.a=a;
 Init.b=b;
 } 
 catch (Exception ex)
 {
 System.out.println("Something went wrong");
 }
 Mul mat=new Mul();
 mat.start();
 }
 class Mul extends Thread
{
 public void run()
 {
 int[][] c = new int[Init.m][Init.n];
 for(int i = 0; i <Init.m; i++) 
 for (int j = 0; j < Init.n; j++) 
 for (int p = 0; p < Init.k; p++) 
 c[i][j] += Init.a[i][p] * Init.b[p][j];
 Init.c=c;
 Display d=new Display();
 d.start();
 } 
 
}
//display 
 class Matrix_multiplication extends Thread
{
 public void run()
 {
 
 System.out.println("Result :");
 for(int i = 0; i <Init.m; i++) 
 {
System.out.println();
 for (int j = 0; j < Init.n; j++) 
 System.out.print(Init.c[i][j]+"\t"); 
 } 
 } 
}
 public static void main(String args[])
 {
 Read r=new Read();
 try{
 r.start();
 }
 catch (Exception ex)
 {
 ex.printStackTrace();
 }
 
}
}
