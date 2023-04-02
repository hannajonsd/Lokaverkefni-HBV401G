package vidmot;

public enum View {

        HEIMASIDA("hotelSida-view.fxml"),

        HOTEL("hotel-view.fxml"),

        INNSKRANING("user-view.fxml"),

        BOKUN("booking-view.fxml");



        private String filename;

        public String getFilename() {
            return filename;
        }

        View(String filename) { this.filename = filename;
        }

}
