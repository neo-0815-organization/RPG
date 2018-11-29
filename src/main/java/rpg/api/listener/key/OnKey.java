package rpg.api.listener.key;

@FunctionalInterface
public interface OnKey {

	public void onKey(KeyState state);
}
