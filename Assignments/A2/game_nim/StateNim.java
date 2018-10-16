public class StateNim extends State {
	
	int coins_pile;
	
    	
    public StateNim(StateNim state) {
			
			this.coins_pile = state.coins_pile;			
        player = state.player;
    }
}
