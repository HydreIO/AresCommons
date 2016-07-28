package fr.aresrpg.commons.domain;

/**
 * Represent an object with must be opened and closed
 * @author Duarte David <deltaduartedavid @ gmail.com>
 */
public interface Resource {
	/**
     * Open this resource to use it
     * @throws Exception if the open fail or an AlreadyOpenedException
     */
    void open() throws Exception;

	/**
	 * Check if this resource is opened
     * @return true if this resource is opened
     */
    boolean isOpen();

	/**
     * Close this resource to free it
     * @throws Exception if the close fail or if this is not opened with a NotOpenedException
     */
    void close() throws Exception;


	/**
	 * An exception related to a resource
	 * @author Duarte David <deltaduartedavid @ gmail.com>
	 */
	class ResourceException extends Exception{
		private Resource resource;

		public ResourceException(String message, Resource resource) {
			super(message);
			this.resource = resource;
		}

		public ResourceException(String message, Throwable cause, Resource resource) {
			super(message, cause);
			this.resource = resource;
		}

		/**
		 * Get the resource invoked in this exception
		 * @return the resource invoked in this exception
		 */
		public Resource getResource() {
			return resource;
		}
	}

	/**
	 * Thrown when a resource is not opened
	 * @author Duarte David <deltaduartedavid @ gmail.com>
	 */
    class NotOpenedException extends ResourceException{

	    public NotOpenedException(Resource resource) {
		    super("The resource " + Value.toString(resource) + " is not opened", resource);
	    }
    }

	/**
	 * Thrown when a resource is already opened
	 * @author Duarte David <deltaduartedavid @ gmail.com>
	 */
	class AlreadyOpenedException extends ResourceException{
		public AlreadyOpenedException(Resource resource) {
			super("The resource " + Value.toString(resource) + " is already opened" , resource);
		}
	}
}
