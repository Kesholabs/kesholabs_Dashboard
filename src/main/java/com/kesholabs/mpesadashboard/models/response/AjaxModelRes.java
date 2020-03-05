package com.kesholabs.mpesadashboard.models.response;

import com.kesholabs.mpesadashboard.entity.Wavu.WavuUsersEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AjaxModelRes {
    Page<WavuUsersEntity> data;
}
