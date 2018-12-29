package rpg.api.entity;

/**
 * The class LocalController used to control an {@link Entity} locally.
 */
public class LocalController implements Controller {
	private final Entity entity;
	
	public LocalController(final Entity entity) {
		super();
		this.entity = entity;
	}
	
	@Override
	public Entity getEntity() {
		return entity;
	}
}
