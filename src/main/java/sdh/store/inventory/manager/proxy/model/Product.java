package sdh.store.inventory.manager.proxy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("product")
    @Expose
    private ProductDetails productDetails;

    @Getter
    @Setter
    public static class ProductDetails {

        @SerializedName("_id")
        @Expose
        private String id;

        @SerializedName("product_name")
        @Expose
        private String productName;

        @SerializedName("brands")
        @Expose
        private String brands;

        @SerializedName("ingredients_text")
        @Expose
        private String ingredientsText;

        @SerializedName("quantity")
        @Expose
        private String quantity;

        @SerializedName("nutriments")
        @Expose
        private Nutriments nutriments;

        @SerializedName("ecoscore_grade")
        @Expose
        private String ecoscoreGrade;

        @SerializedName("nutriscore_grade")
        @Expose
        private String nutriscoreGrade;

        @SerializedName("categories")
        @Expose
        private String categories;

        @SerializedName("allergens")
        @Expose
        private String allergens;

        @SerializedName("traces")
        @Expose
        private String traces;

        @SerializedName("images")
        @Expose
        private Images images;

    }

    @Getter
    @Setter
    public static class Nutriments {

        @SerializedName("energy-kcal_100g")
        @Expose
        private Double energyKcal100g;

        @SerializedName("proteins_100g")
        @Expose
        private Double proteins100g;

        @SerializedName("fat_100g")
        @Expose
        private Double fat100g;

        @SerializedName("carbohydrates_100g")
        @Expose
        private Double carbohydrates100g;

        @SerializedName("sugars_100g")
        @Expose
        private Double sugars100g;

        @SerializedName("salt_100g")
        @Expose
        private Double salt100g;

    }

    @Getter
    @Setter
    public static class Images {

        @SerializedName("front")
        @Expose
        private Image front;

        @SerializedName("ingredients")
        @Expose
        private Image ingredients;

        @SerializedName("nutrition")
        @Expose
        private Image nutrition;

    }

    @Getter
    @Setter
    public static class Image {

        @SerializedName("display")
        @Expose
        private String display;

        @SerializedName("small")
        @Expose
        private String small;

        @SerializedName("thumb")
        @Expose
        private String thumb;

    }


}
