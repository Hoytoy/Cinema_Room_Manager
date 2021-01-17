class Application {

    String name;

    void run(String[] args) {
        System.out.println(this.name);
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}