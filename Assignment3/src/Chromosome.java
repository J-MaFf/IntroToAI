public class Chromosome {
    private String binaryString;
    private double fitness;

    public Chromosome(String binaryString) {
        this.binaryString = binaryString;
        this.fitness = 0.0;
    }

    public String getBinaryString() {
        return binaryString;
    }

    public void setBinaryString(String binaryString) {
        this.binaryString = binaryString;
    }

    public double getFitness() {
        return fitness;
    }

    public void calculateFitness() {
        // Convert binary string to an integer value of x, considering the range [-10,
        // 10]
        int x = Integer.parseInt(binaryString, 2) - 10;
        // Calculate the function value as fitness
        this.fitness = Math.pow(x, 2) + 6 * x + 5;
    }
}
