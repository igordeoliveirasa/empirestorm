package app.models;

@javax.persistence.Entity
public class PlayerSkills extends Entity {

    private Double walkVelocity;

    public void setWalkVelocity(Double walkVelocity) {
            this.walkVelocity = walkVelocity;
    }

    public Double getWalkVelocity() {
            return walkVelocity;
    }

    public static class Builder {

        public Builder() {
            this.item = new PlayerSkills();
        }
        private PlayerSkills item;

        public Builder withWalkVelocity(final Double walkVelocity) {
            this.item.walkVelocity = walkVelocity;
            return this;
        }

        public PlayerSkills build() {
            return this.item;
        }
    }

}
