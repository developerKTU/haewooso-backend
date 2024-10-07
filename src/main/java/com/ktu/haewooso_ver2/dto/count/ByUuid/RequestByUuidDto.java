package com.ktu.haewooso_ver2.dto.count.ByUuid;

import com.google.firebase.database.annotations.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestByUuidDto {

    @NotNull
    private String uuid;

}
