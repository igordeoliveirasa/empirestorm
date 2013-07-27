package app.models;

@javax.persistence.Entity
public class PlaceType extends Entity {

	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

    public static class Builder {

        public Builder() {
            this.item = new PlaceType();
        }
        private PlaceType item;

        public Builder withName(final String name) {
            this.item.name = name;
            return this;
        }

        public PlaceType build() {
            return this.item;
        }
    }

}
