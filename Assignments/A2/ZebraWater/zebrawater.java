public class zebrawater extends CSP {
	
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
		
		if(X.equals("englishman") && Y.equals("red") && !x.equals(y))
			return false;
		if(X.equals("spaniard") && Y.equals("dog") && !x.equals(y))
			return false;
		if(X.equals("coffee") && Y.equals("green") && !x.equals(y))
			return false;
		if(X.equals("ukranian") && Y.equals("tea") && !x.equals(y))
			return false;
		if(X.equals("green") && Y.equals("ivory") && !x.equals(y)) //green is on right to ivory
			return false;
		if(X.equals("old-gold") && Y.equals("snails	") && !x.equals(y))
			return false;
		if(X.equals("kools") && Y.equals("yellow") && !x.equals(y))
			return false;
		if(X.equals("milk") && Y.equals(new Integer(3))) //middle house will be 3
			return false;
		if(X.equals("norwegian") && Y.equals(new Integer(1))) //first house on the left
			return false;
		if(X.equals("chesterfield") && Y.equals("fox") && (Math.abs((Integer)x - (Integer)y == 1)))
			return false;
		if(X.equals("kools") && Y.equals("horse") && (Math.abs((Integer)x - (Integer)y == 1)))
			return false;
		if(X.equals("lucky-strike") && Y.equals("orange-juice"))
			return false;
		if(X.equals("japanese") && Y.equals("parliament"))
			return false;
		if(X.equals("norwegian") && Y.equals("blue") && (Math.abs((Integer)x - (Integer)y == 1)))
			return false;

		//not equal constraint
		if(color_var.contains(X) && color_var.contains(Y) && !X.equals(Y) &&!x.equals(y)) 
			return false;
		if(drinks_var.contains(X) && drinks_var.contains(Y) && !X.equals(Y) &&!x.equals(y)) 
			return false;
		if(nation_var.contains(X) && nation_var.contains(Y) && !X.equals(Y) &&!x.equals(y)) 
			return false;
		if(pet_var.contains(X) && pet_var.contains(Y) && !X.equals(Y) &&!x.equals(y)) 
			return false;
		if(cigar_var.contains(X) && cigar_var.contains(Y) && !X.equals(Y) &&!x.equals(y)) 
			return false;
		return true;
	}
		
	public static void main(String[] args) throws Exception {
		zebrawater csp = new zebrawater();
		int[] dom = {1, 2, 3, 4, 5};
		for(Object X: color_var)
			csp.addDomain(X, dom);
		for(Object X: drinks_var)
			csp.addDomain(X, dom);
		for(Object X: nation_var)
			csp.addDomain(X, dom);
		for(Object X: pet_var)
			csp.addDomain(X, dom);
		for(Object X: cigar_var)
			csp.addDomain(X, dom);
		
		
		csp.addBidirectionalArc("englishman", "red");
		csp.addBidirectionalArc("spaniard", "dog");
		csp.addBidirectionalArc("coffee", "green");
		csp.addBidirectionalArc("ukranian", "tea");
		csp.addBidirectionalArc("green", "ivory");
		csp.addBidirectionalArc("old-gold", "snail");
		csp.addBidirectionalArc("kools", "yellow");
		
		for(Object X: color_var)
			for(Object Y: color_var)
				csp.addBidirectionalArc(X,Y);
		for(Object X: drinks_var)
			for(Object Y: drinks_var)
				csp.addBidirectionalArc(X,Y);
		for(Object X: nation_var)
			for(Object Y: nation_var)
				csp.addBidirectionalArc(X,Y);
		for(Object X: pet_var)
			for(Object Y: pet_var)
				csp.addBidirectionalArc(X,Y);
		for(Object X: cigar_var)
			for(Object Y: cigar_var)
				csp.addBidirectionalArc(X,Y);
			
		Search search = new Search(csp);
		System.out.println(search.BacktrackingSearch());
	}
}