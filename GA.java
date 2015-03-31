public class GA
{
	int G = 0;
	char [] chromosome = new char[32];
	char [] offspring = new char[32];
	
	int populationSize;
	int maxGenerations;
	float crossoverProbability;
	float mutationRate;
	int tournamentSize;
	float generationGap;
	
	public GA(int popSize, int maxGen, float crossover, float mutation, int tournament, float genGap)
	{
	}
	
	//Selection Strategies
	public char [] randomSelection()
	{
		return null;
	}
	
	public char [] tournament()
	{
		return null;
	}
	
	public char [] proportional()
	{
		return null;
	}
	public char [] rankBased()
	{
		return null;
	}
	

	//Crossover Strategy
	public char [] OnePointCrossover()
	{
		return null;
	}
	
	//Mutation
	public char[] applyMutation(char [] chrom)
	{
		return null;
	}
	
	
	
	
	public char[] createAndInitRandomPopulation()
	{
		return null;
	}
	
	
	
	public char [] determineNextPopulation(boolean elitism, char [] pg1, char [] o)
	{
		return null;
	}
	
	public int incrementGenerationCounter(int g)
	{
		return ++g;
	}
	
	public void reportBestIndividual(char [] chrom)
	{
		
	}
	
	public int calculateFitness(char [] chrom)
	{
		return 0;
	}
	
}

/*
1. Initialize the generation counter G = 0
2. Create and initialize a random population P0
, 
containing N chromosomes
3. While NOT (stopping conditions) do
a) Calculate the fitness of each chromosome Ci in PG, f(Ci)
for i = 1, …, N
b) Create a new population O to contain the offspring and 
populate it by repeatedly selecting parents from PG and 
applying crossover
c) Apply mutation at some probability to the chromosomes 
in O, the offspring.
d) Determine the next population PG+1 from PG and O 
(applying elitism if used)
e) Increment the generation counter, G = G + 1
f) Report the best individual (for keeping track of progress).
4. end While
*/