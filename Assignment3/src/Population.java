import java.util.ArrayList;
import java.util.List;

public class Population {
    private List<Chromosome> chromosomes;

    public Population(int size) {
        chromosomes = new ArrayList<>(size);
    }

    public void initializePopulation(int chromosomeLength) {
        for (int i = 0; i < chromosomes.size(); i++) {
            String binaryString = Integer.toBinaryString((int) (Math.random() * (Math.pow(2, chromosomeLength) - 1)));
            while (binaryString.length() < chromosomeLength) {
                binaryString = "0" + binaryString; // Pad with leading zeros
            }
            Chromosome chromosome = new Chromosome(binaryString);
            chromosome.calculateFitness();
            chromosomes.add(chromosome);
        }
    }

    public List<Chromosome> getChromosomes() {
        return chromosomes;
    }

    public void setChromosomes(List<Chromosome> newGeneration) {
        this.chromosomes = newGeneration;
    }
}
