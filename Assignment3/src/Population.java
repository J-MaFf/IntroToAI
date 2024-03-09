public class Population {
    private Chromosome[] chromosomes;

    public Population(int size) {
        chromosomes = new Chromosome[size];
    }

    public void initializePopulation() {
        for (int i = 0; i < chromosomes.length; i++) {
            String binaryString = Integer.toBinaryString((int) (Math.random() * ((1 << 5) - 1))); // 5-bit binary
            chromosomes[i] = new Chromosome(binaryString);
            chromosomes[i].calculateFitness();
        }
    }

    public Chromosome[] getChromosomes() {
        return chromosomes;
    }

    // Additional methods for selection, crossover, and getting the fittest
    // chromosome will be added here
}
