
public class MyArrayList {
	private Object[] myStore;
	private int actSize;
	
	public MyArrayList() {
		myStore = new Object[10];
	}
	
	public Object get(long index) {
		if(index < actSize) {
			return myStore[(int)index];
		}else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}
	
	public void set(long i, Object o) {
		if(i < actSize) {
			 myStore[(int)i] = o;
		}else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}
	public void add(Object obj) {
		if(myStore.length-actSize < 5) {
			increasSize();
		}
		myStore[actSize++] = obj;
	}
	
	
	
	public Object remove(int index) {
		if(index < actSize) {
			Object obj = myStore[index];
			myStore[index] =null;
			int tmp = index;
			while (tmp < actSize) {
				myStore[tmp]= myStore[tmp+1];
				myStore[tmp+1] = null;
				tmp++;
			}
			actSize--;
			return obj;
		}else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}
	
	public int size() {
		return actSize;
	}
	private void increasSize() {
		Object tempArray[] = new Object[myStore.length*2];
		 for(int i =0 ; i<myStore.length;i++) {
			 tempArray[i] =myStore[i];
		 }
		 myStore = tempArray;
		 
	}
}
