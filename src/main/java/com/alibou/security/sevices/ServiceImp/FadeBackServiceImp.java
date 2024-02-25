package com.alibou.security.sevices.ServiceImp;


import com.alibou.security.dto.ApiResponse;
import com.alibou.security.dto.FadeBackDto;
import com.alibou.security.model.FadeBack;
import com.alibou.security.repository.FadeBackRepository;
import com.alibou.security.sevices.FadeBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FadeBackServiceImp implements FadeBackService {

    @Autowired
    private FadeBackRepository fadeBackRepository;

    @Override
    public ApiResponse createFabeBack(FadeBackDto dto) {
        FadeBack fadeBack=new FadeBack();
        fadeBack.setSalonId(dto.getSalonId());
        fadeBack.setText(dto.getText());
        fadeBack.setRating(dto.getRating());
        fadeBack.setLikes(dto.getLikes());
        fadeBackRepository.save(fadeBack);
        return new ApiResponse("create Fade back",true,fadeBack);
    }

    @Override
    public ApiResponse updateFadeBack(Long id, FadeBackDto dto) {
        return null;
    }

    @Override
    public ApiResponse getAllFadeBack(Long salonId) {
        List<FadeBack> allBySalonId = fadeBackRepository.findAllBySalonId(salonId);
        return new ApiResponse("All fadeBack",true,allBySalonId);
    }

    @Override
    public ApiResponse deleteFadeBack(Long id) {
        return null;
    }
}
