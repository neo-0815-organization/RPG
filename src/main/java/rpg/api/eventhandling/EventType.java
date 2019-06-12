package rpg.api.eventhandling;

import rpg.api.eventhandling.events.Event;

/**
 * The enum EventType, used to distinguish between different types of
 * {@link Event}s.
 */
public enum EventType {
	GENERAL_EVENT,
	TEST_EVENT,
	COLLISION_EVENT,
	CURRENT_MAP_EVENT,
	SPEAK_EVENT,
	MOVE_EVENT,
	
	ALL_EVENTS;
}
