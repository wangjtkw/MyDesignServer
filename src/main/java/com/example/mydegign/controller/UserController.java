package com.example.mydegign.controller;

import com.example.mydegign.common.ErrorCode;
import com.example.mydegign.common.MyResponseBody;
import com.example.mydegign.common.UploadUtils;
import com.example.mydegign.common.state.EmployerRecordState;
import com.example.mydegign.common.state.UserRecordState;
import com.example.mydegign.entity.*;
import com.example.mydegign.entity.requestbean.EmployerRequestBean;
import com.example.mydegign.entity.requestbean.UserInfoRequestBean;
import com.example.mydegign.entity.responsebean.PositionResponseBean;
import com.example.mydegign.entity.responsebean.UsersInfoResponseBean;
import com.example.mydegign.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsersAccountService usersAccountService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private EducationExperiencesService educationExperiencesService;

    @Autowired
    private CurriculumVitaeService curriculumVitaeService;

    @Autowired
    private EmployerPositionService employerPositionService;

    @Autowired
    private PositionRequirementService positionRequirementService;

    @Autowired
    private EmployerCompanyInfoService employerCompanyInfoService;

    @Autowired
    private EmployerAccountService employerAccountService;

    @Autowired
    private RecordsService recordsService;

    @RequestMapping("/register")
    @ResponseBody
    public Object register(EmployerRequestBean requestBean) {
        if (requestBean.getEmployerAccountAccount() == null
                || "".equals(requestBean.getEmployerAccountAccount())
                || requestBean.getEmployerAccountPassword() == null
                || "".equals(requestBean.getEmployerAccountPassword())) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "参数错误");
        }
        UsersAccount account = usersAccountService.selectByAccount(requestBean.getEmployerAccountAccount());
        if (account != null) {
            return new MyResponseBody(ErrorCode.DUPLICATE_ACCOUNT_NUMBER_CODE, ErrorCode.DUPLICATE_ACCOUNT_NUMBER_DESCRIBE);
        }
        account = new UsersAccount(
                requestBean.getEmployerAccountAccount(),
                requestBean.getEmployerAccountPassword());
        usersAccountService.insert(account);
        return new MyResponseBody(200, "OK");
    }

    @RequestMapping("/login")
    @ResponseBody
    public Object login(EmployerRequestBean requestBean) {
        if (requestBean.getEmployerAccountAccount() == null
                || "".equals(requestBean.getEmployerAccountAccount())
                || requestBean.getEmployerAccountPassword() == null
                || "".equals(requestBean.getEmployerAccountPassword())) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "参数错误");
        }
        UsersAccount account = usersAccountService.selectByAccount(requestBean.getEmployerAccountAccount());
        if (account == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "账号或密码错误");
        }
        if (!requestBean.getEmployerAccountPassword().equals(account.getUsersAccountPassword())) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "账号或密码错误");
        }
        return new MyResponseBody(200, "OK", account);
    }

    @RequestMapping("/insert/userInfo")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object insertUserInfo(@RequestParam MultipartFile headImg, UserInfoRequestBean userInfoRequestBean) {
        if (headImg.isEmpty()) {
            return new MyResponseBody(ErrorCode.PICTURE_ERROR_CODE, ErrorCode.PICTURE_ERROR_DESCRIBE + "头像图片不能为空");
        }
        String headImgSrc = UploadUtils.uploadImg(headImg);
        if (headImgSrc == null) {
            return new MyResponseBody(ErrorCode.PICTURE_ERROR_CODE, ErrorCode.PICTURE_ERROR_DESCRIBE + "图片上传错误");
        }
        if (userInfoRequestBean == null
                || userInfoRequestBean.getUsersAccountId() == null
                || userInfoRequestBean.getEducationExperiencesEducation() == null
                || userInfoRequestBean.getUsersName() == null
                || userInfoRequestBean.getEducationExperiencesEnterDate() == null
                || userInfoRequestBean.getEducationExperiencesMajor() == null
                || userInfoRequestBean.getEducationExperiencesSchool() == null
                || userInfoRequestBean.getUsersBirthday() == null
                || userInfoRequestBean.getUsersPhoneNum() == null
                || userInfoRequestBean.getUsersRole() == null
                || userInfoRequestBean.getUsersSex() == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "参数错误");
        }
        int accountId = Integer.parseInt(userInfoRequestBean.getUsersAccountId());
        UsersAccount account = usersAccountService.selectById(accountId);
        if (account == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "当前编号不存在！");
        }
        EducationExperiences educationExperiences = new EducationExperiences(
                userInfoRequestBean.getEducationExperiencesEducation(),
                userInfoRequestBean.getEducationExperiencesSchool(),
                userInfoRequestBean.getEducationExperiencesEnterDate(),
                userInfoRequestBean.getEducationExperiencesMajor()
        );
        educationExperiencesService.insert(educationExperiences);

        Users users = new Users(
                educationExperiences.getEducationExperiencesId(),
                headImgSrc,
                userInfoRequestBean.getUsersName(),
                userInfoRequestBean.getUsersSex(),
                userInfoRequestBean.getUsersBirthday(),
                userInfoRequestBean.getUsersRole(),
                userInfoRequestBean.getUsersPhoneNum(),
                userInfoRequestBean.getUsersWechat(),
                userInfoRequestBean.getUsersQq()
        );
        usersService.insert(users);
        account.setUsersId(users.getUsersId());
        usersAccountService.updateByPrimaryKeySelective(account);
        return new MyResponseBody(200, "OK", account);
    }

    @RequestMapping("/get/userInfo")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object getUserInfo(int usersId) {
        Users users = usersService.selectById(usersId);
        if (users == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "当前编号不存在！");
        }
        EducationExperiences educationExperiences = educationExperiencesService.selectById(users.getEducationExperiencesId());
        return new MyResponseBody(200, "OK", new UsersInfoResponseBean(users, educationExperiences));
    }

    @RequestMapping("/update/userInfo/headImg")
    @ResponseBody
    public Object updateUserInfoHeadImg(@RequestParam MultipartFile headImg, String usersIdStr) {
        if (headImg.isEmpty()) {
            return new MyResponseBody(ErrorCode.PICTURE_ERROR_CODE, ErrorCode.PICTURE_ERROR_DESCRIBE + "头像图片不能为空");
        }
        String headImgSrc = UploadUtils.uploadImg(headImg);
        if (headImgSrc == null) {
            return new MyResponseBody(ErrorCode.PICTURE_ERROR_CODE, ErrorCode.PICTURE_ERROR_DESCRIBE + "图片上传错误");
        }
        int userId = Integer.parseInt(usersIdStr);
        Users users = usersService.selectById(userId);
        if (users == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "当前编号不存在！");
        }
        users.setUsersImg(headImgSrc);
        usersService.updateByPrimaryKeySelective(users);
        return new MyResponseBody(200, "OK");
    }

    @RequestMapping("/update/userInfo")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object updatePersonInfo(UserInfoRequestBean requestBean) {
        if (requestBean == null
                || requestBean.getUsersAccountId() == null
                || requestBean.getUsersAccountId().isEmpty()) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "参数错误");
        }
        int accountId = Integer.parseInt(requestBean.getUsersAccountId());
        UsersAccount account = usersAccountService.selectById(accountId);
        if (account == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "当前编号不存在！");
        }
        Users users = usersService.selectById(account.getUsersId());
        if (users == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "当前编号不存在！");
        }
        users.setUsersName(requestBean.getUsersName());
        users.setUsersSex(requestBean.getUsersSex());
        users.setUsersBirthday(requestBean.getUsersBirthday());
        users.setUsersRole(requestBean.getUsersRole());
        users.setUsersPhoneNum(requestBean.getUsersPhoneNum());
        users.setUsersWechat(requestBean.getUsersWechat());
        users.setUsersQq(requestBean.getUsersQq());
        usersService.updateByPrimaryKeySelective(users);
        EducationExperiences educationExperiences = educationExperiencesService.selectById(users.getEducationExperiencesId());
        if (educationExperiences == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "当前编号不存在！");
        }
        educationExperiences.setEducationExperiencesEducation(requestBean.getEducationExperiencesEducation());
        educationExperiences.setEducationExperiencesSchool(requestBean.getEducationExperiencesSchool());
        educationExperiences.setEducationExperiencesEnterDate(requestBean.getEducationExperiencesEnterDate());
        educationExperiences.setEducationExperiencesMajor(requestBean.getEducationExperiencesMajor());
        educationExperiencesService.updateByPrimaryKeySelective(educationExperiences);
        return new MyResponseBody(200, "OK");
    }

    @RequestMapping("/update/description")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object updateDescription(String description, int usersId) {
        if (description == null
                || description.isEmpty()) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "参数错误");
        }
        Users users = usersService.selectById(usersId);
        if (users == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "当前编号不存在！");
        }
        users.setUsersSelfDescription(description);
        usersService.updateByPrimaryKeySelective(users);
        return new MyResponseBody(200, "OK");
    }

    @RequestMapping("/insert/email")
    @ResponseBody
    public Object updateEmail(int userId, String email) {
        if (email == null
                || email.isEmpty()) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "参数错误");
        }
        Users users = usersService.selectById(userId);
        if (users == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "当前编号不存在！");
        }
        CurriculumVitae curriculumVitae = new CurriculumVitae(email);
        curriculumVitaeService.insert(curriculumVitae);
        users.setCurriculumVitaeId(curriculumVitae.getCurriculumVitaeId());
        usersService.updateByPrimaryKeySelective(users);
        return new MyResponseBody(200, "OK", curriculumVitae);
    }

    @RequestMapping("/get/jobList")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object getJobList(String type) {
        List<EmployerPosition> positionList = employerPositionService.selectAllByType(type);
        System.out.println(positionList.toString());
        ArrayList<PositionResponseBean> result = new ArrayList<>();
        for (EmployerPosition position : positionList) {
            PositionRequirement positionRequirement = null;
            if (position.getPositionRequirementId() != null) {
                positionRequirement = positionRequirementService.selectById(position.getPositionRequirementId());
            }
            EmployerAccount account = employerAccountService.selectById(position.getEmployerAccountId());
            System.out.println(account.toString());
            EmployerCompanyInfo companyInfo = employerCompanyInfoService.selectById(account.getEmployerCompanyInfo());
            System.out.println(companyInfo);
            PositionResponseBean responseBean = new PositionResponseBean(position, positionRequirement, companyInfo);
            if (companyInfo.getEmployerCompanyInfoAuditState().equals("审批通过")) {
                result.add(responseBean);
            }

        }
        return new MyResponseBody(200, "OK", result);
    }

    @RequestMapping("/get/jobDetail")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object getJobDetail(int positionId) {
        EmployerPosition position = employerPositionService.selectById(positionId);
        if (position == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "当前编号不存在！");
        }
        PositionRequirement positionRequirement = null;
        if (position.getPositionRequirementId() != null) {
            positionRequirement = positionRequirementService.selectById(position.getPositionRequirementId());
        }
        EmployerAccount account = employerAccountService.selectById(position.getEmployerAccountId());
        System.out.println(account.toString());
        EmployerCompanyInfo companyInfo = employerCompanyInfoService.selectById(account.getEmployerCompanyInfo());
        System.out.println(companyInfo);
        PositionResponseBean responseBean = new PositionResponseBean(position, positionRequirement, companyInfo);
        return new MyResponseBody(200, "OK", responseBean);

    }

    /**
     * userState：已报名 已录取 已结束
     * employerState：
     *
     * @param userAccountId
     * @param jobId
     * @param employerAccountId
     * @return
     */
    @RequestMapping("/signUp")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object signUp(int userAccountId, int jobId, int employerAccountId) {
        UsersAccount usersAccount = usersAccountService.selectById(userAccountId);
        EmployerPosition position = employerPositionService.selectById(jobId);
        EmployerAccount employerAccount = employerAccountService.selectById(employerAccountId);
        if (usersAccount == null
                || position == null
                || employerAccount == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "当前编号不存在！");
        }
        Records records = recordsService.selectByAllID(employerAccountId, jobId, userAccountId);
        if (records != null) {
            return new MyResponseBody(ErrorCode.DUPLICATE_ACCOUNT_NUMBER_CODE, ErrorCode.DUPLICATE_ACCOUNT_NUMBER_DESCRIBE);
        }
        records = new Records(
                employerAccountId,
                jobId,
                userAccountId,
                UserRecordState.SIGN_UP,
                EmployerRecordState.APPROVAL_PENDING
        );
        recordsService.insert(records);
        return new MyResponseBody(200, "OK", records);
    }


    @RequestMapping("/get/all/records")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object getAllRecords(int userAccountId) {
        UsersAccount account = usersAccountService.selectById(userAccountId);
        if (account == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "当前编号不存在！");
        }
        List<Records> recordsList = recordsService.selectAllByUserAccountId(userAccountId);
        return new MyResponseBody(200, "OK", recordsList);
    }


}
