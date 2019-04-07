package rpg.api.eventhandling;

public interface EventTrigger {
	public void triggerEvent(EventType eventType, Object... objects);
}
