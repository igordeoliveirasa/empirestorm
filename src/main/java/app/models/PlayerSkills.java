package app.models;

@javax.persistence.Entity
public class PlayerSkills extends Entity {

    private double walkVelocity;

    public void setWalkVelocity(double walkVelocity) {
            this.walkVelocity = walkVelocity;
    }

    public double getWalkVelocity() {
            return walkVelocity;
    }

    public static class Builder {

        public Builder() {
            this.item = new PlayerSkills();
        }
        private PlayerSkills item;

        public Builder withWalkVelocity(final double walkVelocity) {
            this.item.walkVelocity = walkVelocity;
            return this;
        }

        public PlayerSkills build() {
            return this.item;
        }
    }

}
