package p1;

//Class for defining each action including destination name and number and path cost

public class Action_p1 {
	int nextcityNO;
	String nextCity;
	int ActionCost;
	public Action_p1(String nextCity, int ActionCost, int nextcityNO) {
		this.nextcityNO = nextcityNO;
		this.ActionCost = ActionCost;
		this.nextCity = nextCity;
	}
}
