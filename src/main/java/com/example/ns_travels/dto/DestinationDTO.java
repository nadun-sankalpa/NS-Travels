package com.example.ns_travels.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class DestinationDTO {
        private Long id;
        private String name;
        private String location;
        private String description;
        private String imageURL;
        private String category;

        public DestinationDTO() {
        }

        public DestinationDTO(Long id, String name, String location, String description, String imageURL, String category) {
                this.id = id;
                this.name = name;
                this.location = location;
                this.description = description;
                this.imageURL = imageURL;
                this.category = category;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getLocation() {
                return location;
        }

        public void setLocation(String location) {
                this.location = location;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getImageURL() {
                return imageURL;
        }

        public void setImageURL(String imageURL) {
                this.imageURL = imageURL;
        }

        public String getCategory() {
                return category;
        }

        public void setCategory(String category) {
                this.category = category;
        }

        @Override
        public String toString() {
                return "DestinationDTO{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", location='" + location + '\'' +
                        ", description='" + description + '\'' +
                        ", imageURL='" + imageURL + '\'' +
                        ", category='" + category + '\'' +
                        '}';
        }
}
