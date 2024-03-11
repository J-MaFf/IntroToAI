import java.util.ArrayList;
import java.util.List;

/**
 * Represents a population of chromosomes.
 */
public class GeneticAlgorithm {
    private Population population;
    private int populationSize = 4;
    private int maxGenerations = 100; // Example stopping condition

    /**
     * Constructs a new GeneticAlgorithm object with the specified population size.
     */
    public GeneticAlgorithm() {
        this.population = new Population(populationSize);
        this.population.initializePopulation(5); // Assuming 5 bits for binary representation
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

    /**
     * Selects parents from the population based on their fitness for reproduction.
     * The selection is done using a roulette wheel selection algorithm.
     * 
     * @return A list of selected parents.
     */
    public List<Chromosome> selectParents() {
        List<Chromosome> selectedParents = new ArrayList<>();
        double totalFitness = calculateTotalFitness();

        for (int i = 0; i < populationSize; i++) {
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
     * Performs crossover operation on a list of parent chromosomes to generate
     * offspring chromosomes.
     * Crossover is a genetic operator that combines genetic material from two
     * parent chromosomes to create new offspring.
     * The method randomly selects two crossover points and swaps the genetic
     * material between the parents at those points.
     * The resulting offspring chromosomes are added to a new list and returned.
     *
     * @param parents the list of parent chromosomes to perform crossover on
     * @return a list of offspring chromosomes generated through crossover
     */
    public List<Chromosome> crossover(List<Chromosome> parents) {
        List<Chromosome> offspring = new ArrayList<>();
        for (int i = 0; i < parents.size(); i += 2) {
            Chromosome parent1 = parents.get(i);
            Chromosome parent2 = parents.get(i + 1);
            int len = parent1.getBinaryString().length();

            int crossoverPoint1 = (int) (Math.random() * len);
            int crossoverPoint2 = (int) (Math.random() * len);

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

    /**
     * Runs the genetic algorithm.
     * 
     * This method performs the main loop of the genetic algorithm, evolving the
     * population
     * over a specified number of generations. It selects parents, performs
     * crossover to create
     * offspring, updates the population with the new generation, and calculates the
     * fitness
     * of each chromosome. The best solution found during the evolution process is
     * stored
     * in the 'bestSolution' variable.
     * 
     * After the specified number of generations, the method displays the best
     * overall solution
     * found, including its binary string representation and fitness value.
     */
    public void run() {
        System.out.println("Starting GA run");
        int generationCount = 0;
        Chromosome bestSolution = null;

        while (generationCount < maxGenerations) {
            List<Chromosome> parents = selectParents();
            List<Chromosome> offspring = crossover(parents);
            population.setChromosomes(offspring); // Update population with new generation
            double maxFitness = -Double.MAX_VALUE;

            for (Chromosome c : population.getChromosomes()) {
                c.calculateFitness(); // Recalculate fitness for new generation
                if (c.getFitness() > maxFitness) {
                    maxFitness = c.getFitness();
                    bestSolution = c;
                }
            }

            System.out.println("Generation " + generationCount + ": Best Fitness = " + maxFitness);
            generationCount++;
        }

        // Display the best overall solution
        if (bestSolution != null) {
            System.out.println("Best solution found: ");
            System.out.println(
                    "Binary String: " + bestSolution.getBinaryString() + ", Fitness: " + bestSolution.getFitness());
        }
    }

}
