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
        // Convert binary string to integer value of x
        int x = Integer.parseInt(binaryString, 2) - 10; // Adjust range from 0-20 to -10-10
        // Calculate fitness (fitness is the value of the function f(x))
        this.fitness = Math.pow(x, 2) + 6 * x + 5;
    }
}