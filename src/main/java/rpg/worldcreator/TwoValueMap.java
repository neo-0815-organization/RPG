package rpg.worldcreator;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class TwoValueMap<K, V1, V2> {
	private final HashMap<K, Values> map = new HashMap<>();
	
	public void put(final K key, final V1 valueOne, final V2 valueTwo) {
		if(key == null) return;
		
		map.put(key, new Values(valueOne, valueTwo));
	}
	
	public V1 getFirst(final K key) {
		if(key == null) return null;
		
		return map.get(key).valueOne;
	}
	
	public V2 getSecond(final K key) {
		if(key == null) return null;
		
		return map.get(key).valueTwo;
	}
	
	public boolean containsKey(final K key) {
		return map.containsKey(key);
	}
	
	public K keyWithValueOne(final V1 valueOne) {
		final AtomicReference<K> returnKey = new AtomicReference<>();
		
		forEach(new TriConsumer<K, V1, V2>() {
			
			@Override
			public void accept(final K key, final V1 v1, final V2 v2) {
				if(valueOne.equals(v1)) returnKey.set(key);
			};
		});
		
		return returnKey.get();
	}
	
	public K keyWithValueTwo(final V2 valueTwo) {
		final AtomicReference<K> returnKey = new AtomicReference<>();
		
		forEach(new TriConsumer<K, V1, V2>() {
			
			@Override
			public void accept(final K key, final V1 v1, final V2 v2) {
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
	
	public void forEach(final TriConsumer<? super K, ? super V1, ? super V2> action) {
		if(action == null) throw new NullPointerException();
		
		map.forEach((k, v) -> {
			action.accept(k, v.valueOne, v.valueTwo);
		});
	}
	
	public TwoValueMap<K, V1, V2> copy() {
		final TwoValueMap<K, V1, V2> map = new TwoValueMap<>();
		
		for(final K key : this.map.keySet())
			map.map.put(key, this.map.get(key));
		
		return map;
	}
	
	private class Values {
		private final V1 valueOne;
		private final V2 valueTwo;
		
		public Values(final V1 valueOne, final V2 valueTwo) {
			this.valueOne = valueOne;
			this.valueTwo = valueTwo;
		}
	}
}
