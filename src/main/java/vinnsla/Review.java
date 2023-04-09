package vinnsla;

public class Review {
    private String comment;
    private int stars;

    public Review(String comment, int stars) {
        this.comment = comment;
        this.stars = stars;
    }

    // Getters and Setters
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
