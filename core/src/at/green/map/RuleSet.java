package at.green.map;

public class RuleSet {
	public float chanceToStartAsWall;
	public int numberIterations;
	public int maxActive;
	public int maxInactive;
	
	
	public RuleSet(float startChance, int maxActive, int maxInactive) {
	    this.chanceToStartAsWall = startChance;
	    this.maxActive = maxActive;
	    this.maxInactive = maxInactive;
	}
}
