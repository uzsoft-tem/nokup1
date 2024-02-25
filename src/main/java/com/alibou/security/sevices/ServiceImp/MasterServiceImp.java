package com.alibou.security.sevices.ServiceImp;


import com.alibou.security.dto.ApiResponse;
import com.alibou.security.dto.MasterDto;
import com.alibou.security.model.Master;
import com.alibou.security.model.MasterImages;
import com.alibou.security.repository.MasterImagesRepository;
import com.alibou.security.repository.MasterRepository;
import com.alibou.security.sevices.MasterService;
import com.alibou.security.template.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MasterServiceImp implements MasterService {

    @Autowired
    private MasterRepository masterRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private MasterImagesRepository masterImagesRepository;

    @Override
    public ApiResponse addMaster(MasterDto dto) throws IOException {
        boolean b = masterRepository.existsBySalonIdAndName(dto.getSalonId(), dto.getName());
        System.out.println(b +"");
        if(b) return new ApiResponse("Already add master",false);
        Master master=new Master();
        master.setSalonId(dto.getSalonId());
        master.setName(dto.getName());
        master.setPosition(Position.BARBER);
//        master.setImage(storageService.uploadMaterImageToFileSystem(dto.getImage()));

        MasterImages masterImages=new MasterImages();
        masterImages.setOriginalName(dto.getImage().getName());
        masterImages.setContentType(dto.getImage().getContentType());
        masterImages.setImageByte(dto.getImage().getBytes());
        masterImagesRepository.save(masterImages);

        master.setImage(masterImages);

        masterRepository.save(master);
        return new ApiResponse("add Master",true,master);
    }

    @Override
    public ApiResponse updateMaster(Long id, MasterDto dto) throws IOException {
        Optional<Master> byId = masterRepository.findById(id);
        boolean b = masterRepository.existsByIdNotAndName(id, dto.getName());
        if (byId.isEmpty()) return new ApiResponse("Master Not Found",false);
        if (b) return new ApiResponse("Already name register",true);
        Master master=byId.get();
        master.setSalonId(dto.getSalonId());
        master.setName(dto.getName());
        master.setPosition(Position.BARBER);
//        master.setImage(storageService.uploadMaterImageToFileSystem(dto.getImage()));
        masterRepository.save(master);
        return new ApiResponse("update Master",true,master);
    }

    @Override
    public ApiResponse getAllMaster(Long salonId) {
        List<Master> allBySalonId = masterRepository.findBySalonId(salonId);
        System.out.println(allBySalonId.size());
        if (allBySalonId.isEmpty()) return new ApiResponse("Not Found Masters",false);
        return new ApiResponse(salonId+" salon masters",true,allBySalonId);
    }

    @Override
    public ApiResponse getIdMaster(Long id) {
        Optional<Master> byId = masterRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("Not Found Master",false);
        return new ApiResponse(id+" master",true,byId);
    }

    @Override
    public ApiResponse deleteMaster(Long id) {
        Optional<Master> byId = masterRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("Not Found Master",false);
        masterRepository.deleteById(id);
        masterImagesRepository.deleteById(byId.get().getImage().getId());
        return new ApiResponse("Master deleted",true);
    }
}
