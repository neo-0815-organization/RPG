package rpg.worldcreator;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class TwoValueMap<K, V1, V2> {
	private final HashMap<K, Values> map = new HashMap<>();
	
	public void put(K key, V1 valueOne, V2 valueTwo) {
		map.put(key, new Values(valueOne, valueTwo));
	}
	
	public V1 getFirst(K key) {
		return map.get(key).valueOne;
	}
	
	public V2 getSecond(K key) {
		return map.get(key).valueTwo;
	}
	
	public boolean containsKey(K key) {
		return map.containsKey(key);
	}
	
	public K keyWithValueOne(V1 valueOne) {
		AtomicReference<K> returnKey = new AtomicReference<>();
		
		forEach(new TriConsumer<K, V1, V2>() {
			
			public void accept(K key, V1 v1, V2 v2) {
				if(valueOne.equals(v1)) returnKey.set(key);
			};
		});
		
		return returnKey.get();
	}
	
	public K keyWithValueTwo(V2 valueTwo) {
		AtomicReference<K> returnKey = new AtomicReference<>();
		
		forEach(new TriConsumer<K, V1, V2>() {
			
			public void accept(K key, V1 v1, V2 v2) {
				if(valueTwo.equals(v2)) returnKey.set(key);
			};
		});
		
		return returnKey.get();
	}
	
	public int size() {
		return map.size();
	}
	
	public void clear() {
		map.clear();
	}
	
	public void forEach(TriConsumer<? super K, ? super V1, ? super V2> action) {
		if(action == null) throw new NullPointerException();
		
		map.forEach((k, v) -> {
			action.accept(k, v.valueOne, v.valueTwo);
		});
	}
	
	private class Values {
		private V1 valueOne;
		private V2 valueTwo;
		
		public Values(V1 valueOne, V2 valueTwo) {
			this.valueOne = valueOne;
			this.valueTwo = valueTwo;
		}
	}
}
