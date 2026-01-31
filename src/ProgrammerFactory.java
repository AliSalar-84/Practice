public class ProgrammerFactory {

    public static Programmer getProgrammer(String type) {
        Programmer programmer = null;
        switch (type) {
            case "php", "PHP" -> programmer = new Programmer() {
                @Override
                public void programmer() {
                    System.out.println("new php programmer added! ".toUpperCase());
                }

                @Override
                public void test() {
                    System.out.println("php tester added! ".toUpperCase());
                }
            };
            case "java", "Java" -> programmer = new Programmer() {
                @Override
                public void programmer() {
                    System.out.println("new java programmer added! ");
                }

                @Override
                public void test() {
                    System.out.println("java tester added! ");
                }
            };
            default -> new Programmer() {
                @Override
                public void programmer() {
                    System.out.println("new programmer");
                }

                @Override
                public void test() {
                    System.out.println("new test");
                }
            };
        }

        return programmer;
    }

}
