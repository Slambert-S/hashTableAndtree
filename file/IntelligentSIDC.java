
public class IntelligentSIDC {

	private int size =0;
	private int threshold = 3;
	BinaryTree  databaseBinary= null;
	HashTable  dataTable = null;
	
	public void SetSIDCThreshold(int size) {
		threshold = size;
		// call restructuring 
	}
	
	public IntelligentSIDC() {
		 if(size < threshold ) {
			 databaseBinary = new BinaryTree();
		 }
		 
		 if(size <threshold) {
			 dataTable = new HashTable();
			 
		 }
		 
		 
	}
	
	// Add an element to the good data structure
	public void add(long key, String value) {
		Student element= new Student(key, value);
		
		if(databaseBinary != null && (size+1) <threshold ) {
			databaseBinary.addEllement(element); 
		}
		else if(databaseBinary!= null &&(size+1) >= threshold) {
			// to implement
			dataTable = new HashTable();
			long[] sequence;
			sequence = this.allKeys();
			for (int i = 0; i < sequence.length; i++) {
				dataTable.add(sequence[i],databaseBinary.getValue(sequence[i]));
				databaseBinary.removeNode(sequence[i]);
			}
			this.databaseBinary= null;
		}
		
		if(dataTable != null && (size+1) >= threshold ) {
			dataTable.add(key,value); 
		}
		
		
		
		this.changeSize();
		
	}
	
	
	// remove the value of the corresponding data structure 
	public void remove(long key) {
		// removing from me binary tree one.
		if(size < threshold ) {
			databaseBinary.removeNode(key);
		}
		
		
		this.changeSize();
	}
	
	public String getValue( long key) {
		if(size < threshold ) {
			String value = databaseBinary.getValue(key);
			if(value != null) return value;
			else System.out.println("their is no corrsponding key in the database");
			 
		};
		
		return "temp";
	}
	
	public long nextKey(long key) {
		long nextKey = 0;
		if(size <threshold ) {
			nextKey = databaseBinary.nextKey(key);
		}
		
		if(nextKey == -1) {
			System.out.println("Their is no valid nextKey");
		}
	
		
		return nextKey;
	}
	
	
	public long prevKey(long key) {
		
		long nextKey =-1;
		
		if(size <threshold ) {
			nextKey = databaseBinary.prevKey(key);
		}
		
		//checking if theirs exist a valid previous key
		if(nextKey == -1 ) {
			System.out.println("Their is no valid prevKey");
		}
		return nextKey; 
	}
	
	
	public long rangeKey(long key1,long key2) {
		long nextKey = -1;
		 
		if(size <threshold ) {
			nextKey = databaseBinary.rangeKey(key1, key2);
		}
		
		return nextKey;
		
	}
	

	public long[] allKeys() {
		long[] sequences =new long[1];
		 
		if(size <threshold ) {
			sequences = databaseBinary.allKeys(databaseBinary.getSize());
		}
		
		return sequences;
		
	}
	
	public long generate() {
		long number = 0;
		do {
		for (int i = 0; i < 8; i++) {
			number = number+ this.getRandomNumberInRange(0, 9);
			number = number*10;
		}
		
		}while(this.getValue(number) !=null);
			return number;
	}
	
	private void changeSize() {
		if(databaseBinary != null) this.size=databaseBinary.getSize();  
		if(databaseBinary == null && dataTable!= null ) this.size=dataTable.getSize();
	}
	
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		return (int)(Math.random() * ((max - min) + 1)) + min;
	}
	
	

}
