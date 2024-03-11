import java.util.ArrayList;
import java.util.List;

/**
 * Represents a population of chromosomes.
 */
public class Population {
    private List<Chromosome> chromosomes;

    /**
     * Constructs a population with the specified size.
     *
     * @param size the size of the population
     */
    public Population(int size) {
        chromosomes = new ArrayList<>(size);
        // Initialize with nulls to set the correct size
        for (int i = 0; i < size; i++) {
            chromosomes.add(null);
        }
    }

    /**
     * Initializes the population with random chromosomes of the specified length.
     *
     * @param chromosomeLength the length of each chromosome
     */
    public void initializePopulation(int chromosomeLength) {
        for (int i = 0; i < chromosomes.size(); i++) {
            String binaryString = Integer.toBinaryString((int) (Math.random() * (Math.pow(2, chromosomeLength) - 1)));
            while (binaryString.length() < chromosomeLength) {
                binaryString = "0" + binaryString; // Pad with leading zeros
            }
            Chromosome chromosome = new Chromosome(binaryString);
            chromosome.calculateFitness();
            chromosomes.set(i, chromosome); // Correctly set the chromosome
        }
    }

    /**
     * Returns the list of chromosomes in the population.
     *
     * @return the list of chromosomes
     */
    public List<Chromosome> getChromosomes() {
        return chromosomes;
    }

    /**
     * Sets the list of chromosomes in the population.
     *
     * @param newGeneration the new list of chromosomes
     */
    public void setChromosomes(List<Chromosome> newGeneration) {
        this.chromosomes = newGeneration;
    }
}
