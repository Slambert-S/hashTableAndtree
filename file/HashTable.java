
 
public class HashTable<K,V> {

	private class HashNode<K, V> {
		K key;
		V value;
		
		HashNode<K,V> next;
		
		public HashNode(K key , V value) {
			this.key=key;
			this.value= value;
		}
		
		public String toString() {
			return (" key:"+key+" Value: "+value);
		}
		
	}
	
	private MyArrayList  bucketArray ;
	private int numBucket;
	private int size;
	
	public HashTable() {
		bucketArray = new MyArrayList();
		numBucket = 10;
		size = 0;
		
	for(int i = 0 ; i<numBucket;i++) {
		bucketArray.add(null);
	}
		
	}
	
	public int size() {return size;}
	public boolean isEmpty() { return size()== 0;}
	
	
	
	public long hashing(long key) {
		long q = 43;
		long p = 23;
		return (p-(key%q));
	}
	
	public long getBucketIndex(K key) {
		long value = (long)key;
		long hashCode = hashing(value);
		long index = hashCode % numBucket;
		return index;
		
	}
	
	public V remove (K key) {
		long bucketIndex = getBucketIndex(key);
		int i = 0;
		HashNode<K, V> head = (HashTable<K, V>.HashNode<K, V>) bucketArray.get(bucketIndex);
		
		//searching for the key in the chain
		HashNode<K,V> prev = null;
		while(head != null) {
			if(head.key.equals(key))
				break;
			
			prev = head;
			head = head.next;
		}
		
		//If the key was not found 
		if(head == null)
			return null;
		
		size--;
		
		if(prev != null)
			prev.next = head.next;
		else
			bucketArray.set(bucketIndex, head.next);
		
		
		
		return head.value;
			
	}
	
	public V get (K key) {
		long bucketIndex = getBucketIndex(key);
		int i = 0;
		HashNode<K, V> head = (HashTable<K, V>.HashNode<K, V>) bucketArray.get(bucketIndex);
		
		//searching for the key in the chain
		while(head != null) {
			if(head.key.equals(key))
				return head.value;
			
			head = head.next;
		}
		
		// if noting was found
		return null;
			
	}
	
	public void add(K key , V value) {
		long bucketIndex = getBucketIndex(key);
		
		HashNode<K, V> head = (HashTable<K, V>.HashNode<K, V>) bucketArray.get(bucketIndex);
		
		while (head != null) {
			if(head.key.equals(key)) {
				head.value = value;
				return;
			}
			head = head.next;
		}
		
		size++;
		head =(HashTable<K, V>.HashNode<K, V>) bucketArray.get(bucketIndex);
		HashNode<K,V> newNode = new HashNode<K,V> (key,value);
		newNode.next = head;
		bucketArray.set(bucketIndex, newNode);
	
		
		if((1.0*size)/numBucket >= 0.7) {
			MyArrayList temp = bucketArray;
			bucketArray = new MyArrayList();
			numBucket = 2 * numBucket;
			size = 0;
			for(int j = 0; j < numBucket; j++) {
				bucketArray.add(null);
			}
			
			for(int k = 0; k<numBucket/2 ; k++) {
				head = (HashTable<K, V>.HashNode<K, V>) temp.get(k);
				while(head != null) {
					add(head.key, head.value);
					head = head.next;
				}
			}
		}
	}
	
	public int getSize() {
		return size;
	}
	   
	
	
}
