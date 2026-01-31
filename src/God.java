public class God {

    static {
        System.out.println("GodClass Loaded");
    }

    private static final God god = new God();

    private God() {}

    public static God getInstance() {
        return god;
    }
}
class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        God god = God.getInstance();
        God god2 = God.getInstance();
        God god3 = God.getInstance();
        System.out.println(god);
        System.out.println(god2);
        System.out.println(god3);
    }
}