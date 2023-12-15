import java.util.*;
public class FCFS {
public static void main(String args[])
{
Scanner sc = new Scanner(System.in);
System.out.println("FIRST COME FIRST SERVE (FCFS) ");
System.out.println("enter no of process: ");
int n = sc.nextInt();
int pid[] = new int[n]; // process ids
int ar[] = new int[n+1]; // arrival times
int bt[] = new int[n]; // burst or execution times
int ta[] = new int[n]; // turn around times
int wt[] = new int[n]; // waiting times
float avgwt=0,avgta=0;
int j;
for( j = 0; j < n; j++)
{
System.out.println("enter process " + (j+1) + " arrival time: ");
ar[j] = sc.nextInt();
System.out.println("enter process " + (j+1) + " brust time: ");
bt[j] = sc.nextInt();
pid[j] = j+1;
}
ar[j]=-1;
System.out.println();
System.out.println("GANTT CHART :");
int x=0,c=0;
for(int i=0;i<n;i++)
{
 if(i<n)
 {
 if(i==0)
 System.out.print(ar[i]+"| process "+ pid[i]+" |"+(ar[i]+bt[i]));
 x+=bt[i];
 System.out.print("| process "+ pid[i]+" |"+(x));
 ta[i]=x-ar[i];
 wt[i]=ta[i]-bt[i];
 avgwt+=wt[i];
 avgta+=ta[i];
 }
 else
 break;
 if(ar[i+1]>ar[i]+bt[i]&&(ar[i+1]>=0))
{
 {
 c=ar[i+1]-(ar[i]+bt[i]);
 System.out.print("| idle |"+(c+(ar[i]+bt[i])));
 x=(c+ar[i]+bt[i]);
 } 
}
}
System.out.println();
System.out.println("\npid arrival brust turn waiting");
for(int i = 0 ; i< n; i++)
System.out.println(pid[i] + "\t " + ar[i] + "\t" + bt[i] + "\t" + ta[i] + 
"\t" + wt[i] ) ;
System.out.println("\naverage waiting time: "+ (avgwt/n)); 
System.out.println("average turnaround time:"+(avgta/n)); 
}
}