import java.util.ArrayList;
import java.util.List;

public class GeneticAlgorithm {
    private Population population;
    private int populationSize = 4;
    private int maxGenerations = 100; // Example stopping condition

    public GeneticAlgorithm() {
        this.population = new Population(populationSize);
        this.population.initializePopulation(5); // Assuming 5 bits for binary representation
    }

    private double calculateTotalFitness() {
        double total = 0;
        for (Chromosome chromosome : population.getChromosomes()) {
            total += chromosome.getFitness();
        }
        return total;
    }

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
