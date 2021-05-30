package KoInMaster.TestModules.FB;

public class test {
    public static void main(String[] args) {
        DataGet test =new DataGet();
        try {
            test.setElement();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
