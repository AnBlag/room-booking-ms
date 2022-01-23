package ru.roombooking.admin.service.notification;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.roombooking.admin.exception.*;
import ru.roombooking.admin.feign.DepartmentFeignClient;
import ru.roombooking.admin.feign.EmployeeViewFeignClient;
import ru.roombooking.admin.model.Department;
import ru.roombooking.admin.model.EmployeeView;
import ru.roombooking.admin.model.Profile;
import ru.roombooking.admin.model.dto.EmployeeDTO;
import ru.roombooking.admin.model.dto.EmployeeEditRequest;
import ru.roombooking.admin.model.dto.EmployeeRequest;
import ru.roombooking.admin.service.EmployeeAndProfileService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeNotificationService {

    private final EmployeeAndProfileService employeeAndProfileService;
    private final DepartmentFeignClient departmentFeignClient;
    private final EmployeeViewFeignClient employeeViewFeignClient;

    public void updateUsers(EmployeeRequest employeeRequest) {

        try {
            employeeViewFeignClient.batchUpdateProfileAndEmployee(getProfileViewListFromParams(String.valueOf(employeeRequest.getId()),
                    employeeRequest.getName(),
                    employeeRequest.getSurname(),
                    employeeRequest.getMiddleName(),
                    employeeRequest.getPhone(),
                    employeeRequest.getEmail(),
                    employeeRequest.getBanned()));
        } catch (FeignException e) {
            throw new UpdateUsersException();
        }
    }

    public List<EmployeeView> getByParamPage(String search) {
        List<EmployeeView> list;
        try {
            if (search != null) {
                //list = profileViewSearchCriteriaRepository.search(searchByURLParams.getParamsFromSearch(search));
                list = employeeViewFeignClient.getEmployeeViewListByURLParams(search);
            }
            else {
                list = employeeViewFeignClient.findAll();
            }
            return list;
        } catch (FeignException e) {
            throw new EmployeeViewBadRequestException();
        }
    }

    public List<EmployeeView> findByParamPage(@RequestBody EmployeeView employeeView) {
        //return profileViewSearchCriteriaRepository.search(getParamsFromProfileView(employeeView));
        try {
            return employeeViewFeignClient.getEmployeeViewListByEmployeeViewParams(employeeView);
        } catch (FeignException e) {
            throw new EmployeeViewBadRequestException();
        }
    }


    public EmployeeEditRequest editEmployee(String id) {
        EmployeeDTO employeeDTO = employeeAndProfileService.findEmployeeByProfileId(Long.parseLong(id));
        Profile profile = employeeAndProfileService.findProfileById(Long.parseLong(id));
        List<Department> departmentList;
        try {
            departmentList = departmentFeignClient.findAll();
        } catch (FeignException e) {
            throw new DepartmentBadRequestException();
        }
        return new EmployeeEditRequest(employeeDTO, profile, departmentList);
    }

    public void saveEmployee(String id,
                               EmployeeDTO employeeDTO,
                               Profile profile) {
        try {
            saveEmployeeFromPageData(Long.parseLong(id), employeeDTO, profile);
        } catch (Exception e) {
            throw new SaveUsersException();
        }
    }

    public void deleteEmployee(String id) {
        try {
            employeeAndProfileService.deleteByProfileId(Long.parseLong(id));
        } catch (Exception e) {
            throw new UsersDeleteException();
        }
    }

    private List<EmployeeView> getProfileViewListFromParams(String id,
                                                            String name,
                                                            String surname,
                                                            String middleName,
                                                            String phone,
                                                            String email,
                                                            String banned) {
        String[] idMas = id.split(",");
        String[] nameMas = name.split(",");
        String[] surnameMas = surname.split(",");
        String[] middleNameMas = middleName.split(",");
        String[] phoneMas = phone.split(",");
        String[] emailMas = email.split(",");
        String[] bannedMas = banned.split(",");

        List<EmployeeView> employeeViewList = new ArrayList<>();
        for (int i=0; i<idMas.length; i++) {
            employeeViewList.add(
                    EmployeeView.builder()
                            .id(Long.parseLong(idMas[i]))
                            .name(nameMas[i])
                            .surname(surnameMas[i])
                            .middleName(middleNameMas[i])
                            .phone(phoneMas[i])
                            .email(emailMas[i])
                            .banned(Boolean.parseBoolean(bannedMas[i]))
                            .build()
            );
        }
        return employeeViewList;
    }

    private void saveEmployeeFromPageData (Long id, EmployeeDTO employeeDTO, Profile profile) {
        EmployeeDTO tempEmployeeDTO = employeeAndProfileService.findEmployeeByProfileId(id);
        Profile tempProfile = employeeAndProfileService.findProfileById(id);
        profile.setId(id);
        profile.setPassword(tempProfile.getPassword());
        employeeDTO.setIsActive(tempEmployeeDTO.getIsActive());
        employeeDTO.setId(tempEmployeeDTO.getId());
        employeeDTO.setProfileId(id);
        employeeAndProfileService.update(employeeDTO, profile);
    }

}
