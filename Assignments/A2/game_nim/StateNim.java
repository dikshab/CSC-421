public class StateNim extends State {
	
	int coins_pile = 13;

    public StateNim() {}

    public StateNim(StateNim state) {
        this.coins_pile = state.coins_pile;
        player = state.player;
    }

    public String toString() {
        return "" + this.coins_pile;
    }
}
