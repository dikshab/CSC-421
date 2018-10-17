import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class GameTicTacToe extends Game {
	

    int pile = 13;    
	int losing_state = 1; // player forced to pick up the last coin

    int WinningScore = 10;
    int LosingScore = -10;
    int NeutralScore = 0;    
    
    public GameNim() {
    	currentState = new StateNim();
    }
    
    public boolean isWinState(State state)
    {
        StateNim nstate = (StateNim) state;
        //player who did the last move
        int previous_player = (state.player==0 ? 1 : 0);  
        char mark = marks[previous_player];
        
		if(losing_state != 1){
            	return true;
            }
        return false;
    }
	
	public Set<State> getSuccessors(State state)
    {
		if(isWinState(state))
			return null;
		
		Set<State> successors = new HashSet<State>();
        StateNim nstate = (StateNim) state;
        
        StateNim successor_state;
        
                successor_state = new StateNim(nstate);
				
                successor_state.coins_pile = (coins_pile-1) || (coins_pile-2) || (coins_pile-3);// we are allowed to take 1, 2 or 3 coins
                successor_state.player = (state.player==0 ? 1 : 0);   
                successors.add(successor_state);   
        return successors;
    }	
	
    public bool isValid(State state){
		if(state.coins_pile < 0)
			return false;
		return true;
	}
	
	
    public double eval(State state) 
    {   
    	if(isWinState(state)) {
    		//player who made last move
    		int previous_player = (state.player==0 ? 1 : 0);
    	
	    	if (previous_player==0) //computer wins
	            return WinningScore;
	    	else //human wins
	            return LosingScore;
    	}

        return NeutralScore;
    }
    
    
    public static void main(String[] args) throws Exception {
        
        Game game = new GameNim(); 
        Search search = new Search(game);
        int depth;
        
        //needed to get human's move
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
        	
        	StateNim 	nextState = null;
        	
            switch ( game.currentState.player ) {
              case 1: //Human
                  
            	  //get human's move
                  System.out.print("Enter your *valid* move> ");
                  int pos = Integer.parseInt( in.readLine() );
            	  
                  nextState = new StateNim((StateNim)game.currentState);
                  nextState.player = 1;
                  nextState.  = (coins_pile-1) || (coins_pile-2) || (coins_pile-3);
                  System.out.println("Human: \n" + nextState);
                  break;
                     
              case 0: //Computer
            	  
            	  nextState = (StateNim)search.bestSuccessorState(depth);
            	  nextState.player = 0;
            	  System.out.println("Computer: \n" + nextState);
                  break;
            }
                        
            game.currentState = nextState;
            //change player
            game.currentState.player = (game.currentState.player==0 ? 1 : 0);
            
            //Who wins?
            if ( game.isWinState(game.currentState) ) {
            
            	if (game.currentState.player == 1) //i.e. last move was by the computer
            		System.out.println("Computer wins!");
            	else
            		System.out.println("You win!");
            	
            	break;
            }
            
            if ( game.isStuckState(game.currentState) ) { 
            	System.out.println("Cat's game!");
            	break;
            }
        }
    }
}