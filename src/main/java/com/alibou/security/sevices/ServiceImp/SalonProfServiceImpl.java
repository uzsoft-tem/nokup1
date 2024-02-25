package com.alibou.security.sevices.ServiceImp;

import com.alibou.security.dto.ApiResponse;
import com.alibou.security.dto.SalonProfDto;
import com.alibou.security.model.Locations;
import com.alibou.security.model.Master;
import com.alibou.security.model.SalonImage;
import com.alibou.security.model.SalonsProf;
import com.alibou.security.repository.*;
import com.alibou.security.sevices.SalonProfService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalonProfServiceImpl implements SalonProfService {

    private final SalonProfRepository salonProfRepository;

    private final LocationsRepository locationsRepository;

    private final SalonImageRepository salonImageRepository;

    private final SalonGalleryRepository salonGalleryRepository;

    private final MasterRepository masterRepository;

    private final MasterImagesRepository masterImagesRepository;

    private final SalonStorageService salonStorageService;

    private final FadeBackRepository fadeBackRepository;

    @Override
    public ApiResponse createSalonProf(SalonProfDto dto) throws IOException {
        boolean b = salonProfRepository.existsByBrandName(dto.getBrandName());
        if (b) return new ApiResponse("Already Added Salon",false);
        SalonsProf salonsProf=new SalonsProf();
        salonsProf.setUsersId(dto.getUsersId());
        salonsProf.setBrandName(dto.getBrandName());
        salonsProf.setAddress(dto.getAddress());
        salonsProf.setRating(dto.getRating());
        salonsProf.setRatingNUmber(dto.getRatingNUmber());
        salonsProf.setPhoneNumber(dto.getPhoneNumber());
        salonsProf.setAboutSalon(dto.getAboutSalon());
        salonsProf.setTimeWork(dto.getTimeWork());
        salonsProf.setViewCount(dto.getViewCount());

        SalonImage salonImage=new SalonImage();
        salonImage.setOriginalName(dto.getProfImage().getName());
        salonImage.setContentType(dto.getProfImage().getContentType());
        salonImage.setImageByte(dto.getProfImage().getBytes());

        salonImageRepository.save(salonImage);
        salonsProf.setProfImage(salonImage);

        Locations locations=new Locations();
        locations.setLatitude(dto.getLatitude());
        locations.setLongitude(dto.getLongitude());
        locationsRepository.save(locations);

        salonsProf.setLocations(locations);
        salonProfRepository.save(salonsProf);
        return new ApiResponse("Successfully added Salon",true);
    }

    @Override
    public ApiResponse updateSalonProf(Long id,SalonProfDto dto) throws IOException {
        Optional<SalonsProf> byId = salonProfRepository.findById(id);
        boolean b = salonProfRepository.existsByIdNotAndBrandName(id, dto.getBrandName());
        if(byId.isEmpty()) return new ApiResponse("Salon Not Found",false);
        if (b) return new ApiResponse("Already register brandName",false);
        SalonsProf salonsProf=byId.get();
        salonsProf.setUsersId(dto.getUsersId());
        salonsProf.setBrandName(dto.getBrandName());
        salonsProf.setAddress(dto.getAddress());
        salonsProf.setRating(dto.getRating());
        salonsProf.setRatingNUmber(dto.getRatingNUmber());
        salonsProf.setPhoneNumber(dto.getPhoneNumber());
        salonsProf.setAboutSalon(dto.getAboutSalon());
        salonsProf.setTimeWork(dto.getTimeWork());
        salonsProf.setViewCount(dto.getViewCount());

//        salonsProf.setProfImage(salonStorageService.uploadSalonImageToFileSystem(dto.getProfImage()));
        SalonImage salonImage=byId.get().getProfImage();
        salonImage.setOriginalName(dto.getProfImage().getName());
        salonImage.setContentType(dto.getProfImage().getContentType());
        salonImage.setImageByte(dto.getProfImage().getBytes());

        salonImageRepository.save(salonImage);
        salonsProf.setProfImage(salonImage);

        Locations locations=new Locations();
        locations.setLatitude(dto.getLatitude());
        locations.setLongitude(dto.getLongitude());
        locationsRepository.save(locations);

        salonsProf.setLocations(locations);
        salonProfRepository.save(salonsProf);
        return new ApiResponse("Successfully update Salon",true);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { Exception.class })
    @Override
    public ApiResponse deleteSalonProf(Long id) {
        Optional<SalonsProf> byId = salonProfRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("Salon Not Found",false);
        salonGalleryRepository.deleteBySalonId(id);
        fadeBackRepository.deleteBySalonId(id);
        for (Master master : masterRepository.findBySalonId(id)) {
            masterRepository.deleteById(master.getId());
            masterImagesRepository.deleteById(master.getImage().getId());
        }
        salonProfRepository.deleteById(id);
        return new ApiResponse("SalonProf deleted",true);
    }

    @Override
    public ApiResponse getSalonProf(Long userId, Long id) {
        List<SalonsProf> byUsersIdAndId = salonProfRepository.findByUsersIdAndId(userId, id);
        return new ApiResponse("salon ",true,byUsersIdAndId);
    }

    @Override
    public ApiResponse getAllUserSalonProf(Long userId) {
        List<SalonsProf> all = salonProfRepository.findByUsersId(userId);
        return new ApiResponse("salonlar",true,all);
    }

    @Override
    public ApiResponse getAllSalonProf() {
        List<SalonsProf> all = salonProfRepository.findAll();
        return new ApiResponse("salonlar",true,all);
    }
}
