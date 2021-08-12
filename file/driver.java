
public class driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IntelligentSIDC testTree = new IntelligentSIDC();
		
		testTree.add(10, " Eric");
		
		testTree.add(12, "Pascale");
		testTree.add(5, "Gertrude ");
		
		testTree.add(3, "Jean-luis");
		testTree.add(8, "Nami");
		testTree.add(7, "Tachou");
		testTree.add(9, "bouboule");
		testTree.add(6, "Misty");
		
		
	
	/*	long[] sequences = testTree.allKeys();
		for (int i = 0; i < sequences.length; i++) {
			System.out.println(sequences[i]);
		} */
		
		//System.out.println(testTree.getValue(testTree.prevKey(6)));
		
		//System.out.println(testTree.rangeKey(9,5));
		
		//System.out.println("after remove" );
		/*HashTable<Integer , String> table = new HashTable<>();
		
		table.add(10, "samuel Lambert");
		table.add(53, "brunaut Lambert");
		table.add(96, "marc Lambert");
		 */
	/*
		System.out.println(table.get(10));
		System.out.println(table.remove(53));
		System.out.println(table.get(96));
		System.out.println("End");		*/
	}

}
