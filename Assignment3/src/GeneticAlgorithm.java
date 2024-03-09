public class GeneticAlgorithm {
    private Population population;
    private int populationSize = 4;

    public GeneticAlgorithm() {
        this.population = new Population(populationSize);
        this.population.initializePopulation();
    }

    // Methods for running the GA, selection, crossover, etc., will go here
}
