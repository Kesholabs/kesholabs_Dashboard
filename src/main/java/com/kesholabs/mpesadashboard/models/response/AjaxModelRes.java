package com.kesholabs.mpesadashboard.models.response;

import com.kesholabs.mpesadashboard.entity.WavuUsersEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AjaxModelRes {
    Page<WavuUsersEntity> data;
}
