import java.util.ArrayList;
import java.util.List;

public class GeneticAlgorithm {
    private Population population;
    private int populationSize = 4;
    private int maxGenerations = 10; // May need to change

    public GeneticAlgorithm() {
        this.population = new Population(populationSize);
        this.population.initializePopulation();
    }

    public void run() {
        int generation = 0;
        while (generation < maxGenerations) {
            // Perform selection
            // Perform crossover
            // Optionally, perform mutation
            // Evaluate fitness of new population
            // Possibly update best solution found
            // Prepare for next generation
            generation++;
        }
        // Output the best solution found
    }

    // Methods for running the GA, selection, crossover, etc., will go here

    /**
     * Selects parents from the population based on their fitness for reproduction.
     * 
     * @return A list of selected parents.
     */
    public List<Chromosome> selectParents() {
        List<Chromosome> selectedParents = new ArrayList<>();
        double totalFitness = calculateTotalFitness();

        for (int i = 0; i < populationSize; i++) { // Selecting parents for the population
            double random = Math.random() * totalFitness;
            double runningSum = 0;

            for (Chromosome chromosome : population.getChromosomes()) {
                runningSum += chromosome.getFitness();
                if (runningSum >= random) {
                    selectedParents.add(chromosome);
                    break;
                }
            }
        }
        return selectedParents;
    }

    /**
     * Calculates the total fitness of the population.
     * 
     * @return The total fitness of the population.
     */
    private double calculateTotalFitness() {
        double total = 0;
        for (Chromosome chromosome : population.getChromosomes()) {
            total += chromosome.getFitness();
        }
        return total;
    }

    public void selection() { // Using roulette wheel selection
        // Select the fittest chromosomes from the population
    }

    public List<Chromosome> crossover(List<Chromosome> parents) {
        List<Chromosome> offspring = new ArrayList<>();
        for (int i = 0; i < parents.size(); i += 2) { // Assume even number of parents
            Chromosome parent1 = parents.get(i);
            Chromosome parent2 = parents.get(i + 1); // Pairwise parents
            int len = parent1.getBinaryString().length();

            int crossoverPoint1 = (int) (Math.random() * len);
            int crossoverPoint2 = (int) (Math.random() * len);

            // Ensure crossoverPoint1 < crossoverPoint2
            if (crossoverPoint1 > crossoverPoint2) {
                int temp = crossoverPoint1;
                crossoverPoint1 = crossoverPoint2;
                crossoverPoint2 = temp;
            }

            String offspring1 = parent1.getBinaryString().substring(0, crossoverPoint1) +
                    parent2.getBinaryString().substring(crossoverPoint1, crossoverPoint2) +
                    parent1.getBinaryString().substring(crossoverPoint2);

            String offspring2 = parent2.getBinaryString().substring(0, crossoverPoint1) +
                    parent1.getBinaryString().substring(crossoverPoint1, crossoverPoint2) +
                    parent2.getBinaryString().substring(crossoverPoint2);

            offspring.add(new Chromosome(offspring1));
            offspring.add(new Chromosome(offspring2));
        }
        return offspring;
    }

}
