package com.goodinfluenceshop.enums;

public enum Category {
    FOOD("식음료", new String[]{
            "KOREAN", "CHINESE", "JAPANESE", "WESTERN", "GENERAL",
            "FAST_FOOD", "COFFEE_SHOP", "BAKERY", "ENTERTAINMENT_BAR",
            "DANRAN_BAR", "BEER_HALL"
    }),
    EDUCATION("교육", new String[]{
            "WORKSHOP", "VOCATIONAL_ACADEMY", "STUDY_CAFE", "SPORTS"
    }),
    SERVICE("생활(서비스)", new String[]{
            "OPTICS", "BEAUTY", "FLOWERS", "ACCOMMODATION_TRAVEL",
            "MACHINERY", "MEDICAL", "STUDIO"
    }),
    OTHER("기타", new String[]{
            "SUPERMARKET", "BUTCHERY", "FOOD_SUPPLIES", "CONVENIENCE_STORE"
    });

    private final String description;
    private final String[] subCategories;

    Category(String description, String[] subCategories) {
        this.description = description;
        this.subCategories = subCategories;
    }

    public String getDescription() {
        return description;
    }

    public String[] getSubCategories() {
        return subCategories;
    }

    public enum FoodSubCategory {
        KOREAN("한식"),
        CHINESE("중식"),
        JAPANESE("일식"),
        WESTERN("양식"),
        GENERAL("일반음식"),
        FAST_FOOD("패스트푸드"),
        COFFEE_SHOP("커피전문점"),
        BAKERY("제과점"),
        ENTERTAINMENT_BAR("유흥주점"),
        DANRAN_BAR("단란주점"),
        BEER_HALL("맥주홀");

        private final String description;

        FoodSubCategory(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum EducationSubCategory {
        WORKSHOP("체험(공방)"),
        VOCATIONAL_ACADEMY("직업전문학원"),
        STUDY_CAFE("스터디카페"),
        SPORTS("운동");

        private final String description;

        EducationSubCategory(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum ServiceSubCategory {
        OPTICS("안경"),
        BEAUTY("미용"),
        FLOWERS("꽃"),
        ACCOMMODATION_TRAVEL("숙박/여행"),
        MACHINERY("기계"),
        MEDICAL("의료"),
        STUDIO("스튜디오");

        private final String description;

        ServiceSubCategory(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum OtherSubCategory {
        SUPERMARKET("슈퍼마켓"),
        BUTCHERY("정육점"),
        FOOD_SUPPLIES("식품잡화"),
        CONVENIENCE_STORE("편의점");

        private final String description;

        OtherSubCategory(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
