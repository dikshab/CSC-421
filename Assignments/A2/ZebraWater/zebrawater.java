public class CSPGraphColoring extends CSP {
	
	static Set<Object> color_var = new HashSet<Object>(
		Arrays.asList(new String[]{"red", "green", "yellow", "blue", "ivory"}));
	
	static Set<Object> drinks_var = new HashSet<Object>(
		Arrays.asList(new String[]{"coffee", "orange-juice", "tea", "water", "milk"}));
	static Set<Object> nation_var = new HashSet<Object>(
		Arrays.asList(new String[]{"englishman", "japanese", "norwegian", "ukranian", "spaniard"}));
	static Set<Object> pet_var = new HashSet<Object>(
		Arrays.asList(new String[]{"dog", "fox", "horse", "zebra", "snail"}));
	static Set<Object> cigar_var = new HashSet<Object>(
		Arrays.asList(new String[]{"old-gold", "kools", "chesterfield", "lucky-strike", "parliament"}));
		
	
	
		
	public boolean isGood(Object X, Object Y, Object x, Object y) {
		//if X is not even mentioned in by the constraints, just return true
		//as nothing can be violated
		if(!C.containsKey(X))
			return true;
		
		//check to see if there is an arc between X and Y
		//if there isn't an arc, then no constraint, i.e. it is good
		if(!C.get(X).contains(Y))
			return true;
		
		//not equal constraint
		if(!x.equals(y)) 
			return true;
		
		return false;
	}
		
	public static void main(String[] args) throws Exception {
		CSPGraphColoring csp = new CSPGraphColoring();
		
		String[] vars = {"WA", "NT", "Q", "NSW", "V", "SA", "T"}; 
		String[] colors = {"r", "g", "b"};
		String[][] pairs = {{"WA","NT"}, {"NT","Q"}, {"Q","NSW"}, {"NSW","V"}, 
							{"SA", "WA"}, {"SA", "NT"}, {"SA", "Q"}, 
							{"SA", "NSW"}, {"SA", "V"}};
		
		for(Object X : vars) 
			csp.addDomain(X, colors);
		
		for(Object[] p : pairs)
			csp.addBidirectionalArc(p[0], p[1]);
		
		Search search = new Search(csp);
		System.out.println(search.BacktrackingSearch());
	}
}