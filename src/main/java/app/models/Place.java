package app.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;

@javax.persistence.Entity
public class Place extends Entity {
    
        @Column(unique = true)
	private String name;
	private int x;
	private int y;
        
        @ManyToOne(cascade = CascadeType.ALL, optional = true)
        private PlaceType type;
        

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

    

    public PlaceType getType() {
        return type;
    }

    public void setType(PlaceType type) {
        this.type = type;
    }

    public static class Builder {

        public Builder() {
            this.item = new Place();
        }
        private Place item;

        public Builder withId(final Long id) {
            this.item.setId(id);
            return this;
        }

        public Builder withName(final String name) {
            this.item.name = name;
            return this;
        }

        public Builder withX(final int x) {
            this.item.x = x;
            return this;
        }

        public Builder withY(final int y) {
            this.item.y = y;
            return this;
        }

        public Builder withType(final PlaceType type) {
            this.item.type = type;
            return this;
        }

        public Place build() {
            return this.item;
        }
    }
    
    

}
