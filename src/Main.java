public class Main {
    public static void main(String[] args) throws Exception {
        Robot robot = new Robot();

        while (true) {
            try {
                Thread.sleep(30000);
            } catch (InterruptedException exc) {
                System.out.println(exc + " error Thread sleep class main");
            }
            robot.robot();
        }
    }
}