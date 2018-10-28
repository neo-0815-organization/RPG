package rpg.api.entity;

/**
 * The class LocalController used to control an {@link Entity} locally.
 */
public class LocalController implements Controller {
	private Entity entity;
	
	@Override
	public Entity getEntity() {
		return entity;
	}
}
