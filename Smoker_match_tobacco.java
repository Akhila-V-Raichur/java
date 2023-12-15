import java.util.concurrent.Semaphore;

class Smoker extends Thread {
    private Semaphore tobacco;
    private Semaphore paper;
    private Semaphore matches;
    private Semaphore agentSemaphore;

    public Smoker(Semaphore tobacco, Semaphore paper, Semaphore matches, Semaphore agentSemaphore) {
        this.tobacco = tobacco;
        this.paper = paper;
        this.matches = matches;
        this.agentSemaphore = agentSemaphore;
    }

    public void run() {
         
       while(true)
       {
        try {
                // Wait for the agent to place two items on the table
                agentSemaphore.acquire();

                // Check which items are on the table
                if (tobacco.availablePermits() == 1 && paper.availablePermits() == 1) {
                    // If tobacco and paper are on the table, make a cigarette and smoke
                    System.out.println("Vendor produced tobacco and paper");
                    System.out.println("Smoker with matches: Making a cigarette and smoking...");
                    Thread.sleep(1000); // Simulate smoking
                } else if (paper.availablePermits() == 1 && matches.availablePermits() == 1) {
                    // If paper and matches are on the table, make a cigarette and smoke
                    System.out.println("Vendor produced matches and paper");
                    System.out.println("Smoker with tobacco: Making a cigarette and smoking...");
                    Thread.sleep(1000); // Simulate smoking
                } else if (matches.availablePermits() == 1 && tobacco.availablePermits() == 1) {
                    // If matches and tobacco are on the table, make a cigarette and smoke
                    System.out.println("Vendor produced tobacco and matches");
                    System.out.println("Smoker with paper: Making a cigarette and smoking...");
                    Thread.sleep(1000); // Simulate smoking
                }

                // Signal the agent to place items on the table again
                agentSemaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Agent extends Thread {
    private Semaphore tobacco;
    private Semaphore paper;
    private Semaphore matches;
    private Semaphore agentSemaphore;

    public Agent(Semaphore tobacco, Semaphore paper, Semaphore matches, Semaphore agentSemaphore) {
        this.tobacco = tobacco;
        this.paper = paper;
        this.matches = matches;
        this.agentSemaphore = agentSemaphore;
    }

    public void run() {
        for(int i=0;i<5;i++) {
            try {
                // Randomly choose two items to place on the table
                int choice = (int) (Math.random() * 3);

                switch (choice) {
                    case 0:
                        tobacco.release();
                        paper.release();
                        break;
                    case 1:
                        paper.release();
                        matches.release();
                        break;
                    case 2:
                        matches.release();
                        tobacco.release();
                        break;
                }

                // Signal the smokers that items are on the table
                agentSemaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Smoker_match_tobacco {
    public static void main(String[] args) {
        Semaphore tobacco = new Semaphore(0);
        Semaphore paper = new Semaphore(0);
        Semaphore matches = new Semaphore(0);
        Semaphore agentSemaphore = new Semaphore(1);

        Smoker smoker1 = new Smoker(tobacco, paper, matches, agentSemaphore);
        Smoker smoker2 = new Smoker(paper, matches, tobacco, agentSemaphore);
        Smoker smoker3 = new Smoker(matches, tobacco, paper, agentSemaphore);
        Agent agent = new Agent(tobacco, paper, matches, agentSemaphore);

        smoker1.start();
        smoker2.start();
        smoker3.start();
        agent.start();
    }
}