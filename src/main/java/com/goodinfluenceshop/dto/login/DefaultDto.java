package com.goodinfluenceshop.dto.login;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class DefaultDto {
    @Builder
    @Getter
    @Setter
    public static class FileResDto{
        private String url;
    }

    @SuperBuilder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BaseDto {
        String empty;
        public BaseDto afterBuild(BaseDto param) {
            BeanUtils.copyProperties(param, this);
            return this;
        }
    }

    @SuperBuilder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateReqDto extends BaseDto {
        private String id;
        private String deleted;
        private String process;
    }

    @SuperBuilder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeleteReqDto extends BaseDto {
        private String id;
    }
    @SuperBuilder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeleteServDto extends DeleteReqDto {
        private boolean isAdmin;
        private String reqTbuserId;
    }

    @SuperBuilder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeletesReqDto extends BaseDto {
        private List<String> ids;
    }
    @SuperBuilder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeletesServDto extends DeletesReqDto {
        private String reqTbuserId;
        private boolean isAdmin;
    }

    @SuperBuilder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetailReqDto extends BaseDto {
        private String id;
    }

    @SuperBuilder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetailServDto extends DetailReqDto{
        private boolean isAdmin;
        private String reqTbuserId;
    }

    @Getter
    @Setter
    public static class DetailResDto{
        private String id;
        private String deleted;
        private String process;
        private String createdAt;
        private String modifiedAt;
    }
}
