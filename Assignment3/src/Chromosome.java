/**
 * Represents a chromosome in a genetic algorithm.
 */
public class Chromosome {
    private String binaryString;
    private double fitness;

    /**
     * Constructs a new Chromosome object with the specified binary string.
     *
     * @param binaryString the binary string representation of the chromosome
     */
    public Chromosome(String binaryString) {
        this.binaryString = binaryString;
        this.fitness = 0.0;
    }

    /**
     * Returns the binary string representation of the chromosome.
     *
     * @return the binary string representation of the chromosome
     */
    public String getBinaryString() {
        return binaryString;
    }

    /**
     * Sets the binary string representation of the chromosome.
     *
     * @param binaryString the binary string representation of the chromosome
     */
    public void setBinaryString(String binaryString) {
        this.binaryString = binaryString;
    }

    /**
     * Returns the fitness value of the chromosome.
     *
     * @return the fitness value of the chromosome
     */
    public double getFitness() {
        return fitness;
    }

    /**
     * Calculates the fitness value of the chromosome based on its binary string
     * representation.
     */
    public void calculateFitness() {
        // Convert binary string to an integer value of x, mapped to the range [-10, 10]
        int decimalValue = Integer.parseInt(binaryString, 2);
        int range = 10 - (-10);
        double step = range / (Math.pow(2, binaryString.length()) - 1);
        double x = -10 + step * decimalValue; // Adjusting the range mapping
        // Calculate the function value as fitness
        this.fitness = Math.pow(x, 2) + 6 * x + 5;
    }
}
