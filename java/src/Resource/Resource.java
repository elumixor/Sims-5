package Resource;

public abstract class Resource {
    public float getQuantity() {
        return quantity;
    }
    public float getCost() {
        return cost;
    }
    private final String name;
    float quantity;
    float cost;

    Resource(String name, float initialQuantity, float costPerUnit) {
        this.name = name;
        cost = costPerUnit;
        quantity = initialQuantity;
    }

    public void buy(float q) {
        quantity += q;
        Money.instance.consume(q * cost);
    }

    public void changeCost(float costPerUnit) {
        cost = costPerUnit;
    }

    public void consume(float q) {
        quantity -= q;
        System.out.printf("%s:\t%s%.2f\t=>\t%.2f%n", name, q < 0 ? "+" : "-", q, quantity);
    }
}
