public class MyTestingClass {
    private int index;

    public MyTestingClass(int index) {
        this.index = index;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + index;
        return hash;
    }
}
