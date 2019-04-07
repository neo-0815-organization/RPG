package rpg.api.gfx;

/**
 * Interface representing varibales that change a path.
 * 
 * @author Tim Ludwig
 */
public interface PathModifier {
	/**
	 * @return the {@code pathModifier} corresponding to this {@link PathModifier}
	 */
	public default String getPathModifier() {
		return "";
	}
	
	/**
	 * @return the {@code prePath} corresponding to this {@link PathModifier}
	 */
	public default String getPrePath() {
		return "/";
	}
	
	/**
	 * @return the {@code postPath} corresponding to this {@link PathModifier}
	 */
	public default String getPostPath() {
		return "";
	}
	
	/**
	 * @return the {@code path} corresponding to this {@link PathModifier} ({@code prePath + pathModifier + postPath})
	 */
	public default String getPath() {
		return getPrePath() + getPathModifier() + getPostPath();
	}
}
