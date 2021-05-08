package com.example.mydegign.controller;

import com.example.mydegign.common.*;
import com.example.mydegign.common.state.AuditState;
import com.example.mydegign.common.state.EmployerPositionState;
import com.example.mydegign.common.state.EmployerRecordState;
import com.example.mydegign.common.state.UserRecordState;
import com.example.mydegign.entity.*;
import com.example.mydegign.entity.requestbean.EmployerCompanyRequestBean;
import com.example.mydegign.entity.requestbean.EmployerPositionRequestBean;
import com.example.mydegign.entity.requestbean.EmployerRequestBean;
import com.example.mydegign.entity.responsebean.EmployerRecordResponse;
import com.example.mydegign.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/employer")
@Controller
public class EmployerController {

    @Autowired
    private EmployerAccountService employerAccountService;

    @Autowired
    private EmployerPersonalInfoService employerPersonalInfoService;

    @Autowired
    private EmployerCompanyInfoService employerCompanyInfoService;

    @Autowired
    private EmployerPositionService employerPositionService;

    @Autowired
    private PositionRequirementService positionRequirementService;

    @Autowired
    private RecordsService recordsService;

    @Autowired
    private UsersAccountService usersAccountService;

    @Autowired
    private UsersService usersService;

    @RequestMapping("/register")
    @ResponseBody
    public Object register(EmployerRequestBean requestBean) {
        if (requestBean.getEmployerAccountAccount() == null
                || "".equals(requestBean.getEmployerAccountAccount())
                || requestBean.getEmployerAccountPassword() == null
                || "".equals(requestBean.getEmployerAccountPassword())) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "参数错误");
        }
        EmployerAccount account = employerAccountService.selectByAccount(requestBean.getEmployerAccountAccount());
        if (account != null) {
            return new MyResponseBody(ErrorCode.DUPLICATE_ACCOUNT_NUMBER_CODE, ErrorCode.DUPLICATE_ACCOUNT_NUMBER_DESCRIBE);
        }
        account = new EmployerAccount(requestBean.getEmployerAccountAccount(), requestBean.getEmployerAccountPassword());
        employerAccountService.insert(account);
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
        EmployerAccount account = employerAccountService.selectByAccount(requestBean.getEmployerAccountAccount());
        if (account == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "账号或密码错误");
        }
        if (!requestBean.getEmployerAccountPassword().equals(account.getEmployerAccountPassword())) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "账号或密码错误");
        }
        return new MyResponseBody(200, "OK", account);
    }

    @RequestMapping("/insert/personalInfo")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object insertPersonalInfo(@RequestParam MultipartFile headImg,
                                     MultipartFile frontImg,
                                     MultipartFile backImg,
                                     String name, String idNum,
                                     String accountId) {
        if (headImg.isEmpty()) {
            return new MyResponseBody(ErrorCode.PICTURE_ERROR_CODE, ErrorCode.PICTURE_ERROR_DESCRIBE + "头像图片不能为空");
        }
        if (frontImg.isEmpty()) {
            return new MyResponseBody(ErrorCode.PICTURE_ERROR_CODE, ErrorCode.PICTURE_ERROR_DESCRIBE + "身份证正面不能为空");
        }
        if (backImg.isEmpty()) {
            return new MyResponseBody(ErrorCode.PICTURE_ERROR_CODE, ErrorCode.PICTURE_ERROR_DESCRIBE + "身份证背面不能为空");
        }
        String headImgSrc = UploadUtils.uploadImg(headImg);
        String frontImgSrc = UploadUtils.uploadImg(frontImg);
        String backImgSrc = UploadUtils.uploadImg(backImg);
        if (headImgSrc == null
                || frontImgSrc == null
                || backImgSrc == null) {
            return new MyResponseBody(ErrorCode.PICTURE_ERROR_CODE, ErrorCode.PICTURE_ERROR_DESCRIBE + "图片上传错误");
        }
        if (name == null
                || name.isEmpty()
                || idNum == null
                || idNum.isEmpty()
                || accountId == null
                || accountId.isEmpty()
        ) {
            return new MyResponseBody(ErrorCode.PICTURE_ERROR_CODE, ErrorCode.PICTURE_ERROR_DESCRIBE + "请求参数错误");
        }
        int mAccountId = Integer.parseInt(accountId);
        EmployerAccount account = employerAccountService.selectById(mAccountId);
        if (account == null) {
            return new MyResponseBody(ErrorCode.PICTURE_ERROR_CODE, ErrorCode.PICTURE_ERROR_DESCRIBE + "请求参数错误");
        }

        EmployerPersonalInfo personalInfo = new EmployerPersonalInfo(
                headImgSrc, name, idNum, frontImgSrc, backImgSrc
        );
        employerPersonalInfoService.insert(personalInfo);
        account.setEmployerPersonalInfoId(personalInfo.getEmployerPersonalInfoId());
        employerAccountService.updateSelective(account);
        return new MyResponseBody(200, "OK", account);
    }

    @RequestMapping("/get/personalInfo")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object getPersonalInfo(int personalInfoId) {
        EmployerPersonalInfo personalInfo = employerPersonalInfoService.selectById(personalInfoId);
        if (personalInfo == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "当前编号不存在");
        }
        return new MyResponseBody(200, "OK", personalInfo);
    }

    @RequestMapping("/update/personalInfo/headImg")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object updatePersonalHeadImg(@RequestParam MultipartFile headImg, String personalId) {
        if (headImg.isEmpty()) {
            return new MyResponseBody(ErrorCode.PICTURE_ERROR_CODE, ErrorCode.PICTURE_ERROR_DESCRIBE + "头像图片不能为空");
        }
        String headImgSrc = UploadUtils.uploadImg(headImg);
        if (headImgSrc == null
                || headImgSrc.isEmpty()) {
            return new MyResponseBody(ErrorCode.PICTURE_ERROR_CODE, ErrorCode.PICTURE_ERROR_DESCRIBE + "图片上传错误");
        }
        int mPersonalId = Integer.parseInt(personalId);
        EmployerPersonalInfo personalInfo = employerPersonalInfoService.selectById(mPersonalId);
        if (personalInfo == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "当前编号不存在");
        }
        personalInfo.setEmployerPersonalInfoHead(headImgSrc);
        employerPersonalInfoService.update(personalInfo);
        return new MyResponseBody(200, "OK", "");
    }

    @RequestMapping("/update/personalInfo/frontImg")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object updatePersonalFrontImg(@RequestParam MultipartFile frontImg, String personalId) {
        if (frontImg.isEmpty()) {
            return new MyResponseBody(ErrorCode.PICTURE_ERROR_CODE, ErrorCode.PICTURE_ERROR_DESCRIBE + "头像图片不能为空");
        }
        String frontImgSrc = UploadUtils.uploadImg(frontImg);
        if (frontImgSrc == null
                || frontImgSrc.isEmpty()) {
            return new MyResponseBody(ErrorCode.PICTURE_ERROR_CODE, ErrorCode.PICTURE_ERROR_DESCRIBE + "图片上传错误");
        }
        int mPersonalId = Integer.parseInt(personalId);
        EmployerPersonalInfo personalInfo = employerPersonalInfoService.selectById(mPersonalId);
        if (personalInfo == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "当前编号不存在");
        }
        personalInfo.setEmployerPersonalInfoIdCardFront(frontImgSrc);
        employerPersonalInfoService.update(personalInfo);
        return new MyResponseBody(200, "OK", "");
    }

    @RequestMapping("/update/personalInfo/backImg")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object updatePersonalBackImg(@RequestParam MultipartFile backImg, String personalId) {
        if (backImg.isEmpty()) {
            return new MyResponseBody(ErrorCode.PICTURE_ERROR_CODE, ErrorCode.PICTURE_ERROR_DESCRIBE + "头像图片不能为空");
        }
        String backImgSrc = UploadUtils.uploadImg(backImg);
        if (backImgSrc == null
                || backImgSrc.isEmpty()) {
            return new MyResponseBody(ErrorCode.PICTURE_ERROR_CODE, ErrorCode.PICTURE_ERROR_DESCRIBE + "图片上传错误");
        }
        int mPersonalId = Integer.parseInt(personalId);
        EmployerPersonalInfo personalInfo = employerPersonalInfoService.selectById(mPersonalId);
        if (personalInfo == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "当前编号不存在");
        }
        personalInfo.setEmployerPersonalInfoIdCardBack(backImgSrc);
        employerPersonalInfoService.update(personalInfo);
        return new MyResponseBody(200, "OK", "");
    }


    @RequestMapping("/update/personalInfo")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object updatePersonalInfo(String name, String idNum, int personalId) {
        EmployerPersonalInfo personalInfo = employerPersonalInfoService.selectById(personalId);
        if (personalInfo == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "当前编号不存在");
        }
        personalInfo.setEmployerPersonalInfoName(name);
        personalInfo.setEmployerPersonalInfoIdCardNum(idNum);
        employerPersonalInfoService.update(personalInfo);
        return new MyResponseBody(200, "OK", personalInfo);
    }

    @RequestMapping("/insert/companyInfo")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object insertCompanyInfo(EmployerCompanyRequestBean requestBean) {
        if (requestBean == null
                || requestBean.getAccountId() == null
                || requestBean.getEmployerCompanyInfoName() == null
                || requestBean.getEmployerCompanyInfoBusinessScope() == null
                || requestBean.getEmployerCompanyInfoCompanyType() == null
                || requestBean.getEmployerCompanyInfoBusinessState() == null
                || requestBean.getEmployerCompanyInfoFoundTime() == null
                || requestBean.getEmployerCompanyInfoOrganizationCode() == null
                || requestBean.getEmployerCompanyInfoRegisterAddress() == null
                || requestBean.getEmployerCompanyInfoUniformSocialCreditCode() == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "请求参数错误");
        }
        EmployerCompanyInfo companyInfo = new EmployerCompanyInfo(
                requestBean.getEmployerCompanyInfoName(),
                requestBean.getEmployerCompanyInfoCompanyType(),
                requestBean.getEmployerCompanyInfoBusinessState(),
                requestBean.getEmployerCompanyInfoFoundTime(),
                requestBean.getEmployerCompanyInfoRegisterAddress(),
                requestBean.getEmployerCompanyInfoUniformSocialCreditCode(),
                requestBean.getEmployerCompanyInfoOrganizationCode(),
                requestBean.getEmployerCompanyInfoBusinessScope(),
                AuditState.APPROVING);
        employerCompanyInfoService.insert(companyInfo);
        EmployerAccount account = employerAccountService.selectById(requestBean.getAccountId());
        account.setEmployerCompanyInfo(companyInfo.getEmployerCompanyInfoId());
        employerAccountService.updateSelective(account);
        return new MyResponseBody(200, "OK", account);
    }

    @RequestMapping("/get/companyInfo")
    @ResponseBody
    public Object getCompanyInfo(int companyId) {
        EmployerCompanyInfo companyInfo = employerCompanyInfoService.selectById(companyId);
        if (companyInfo == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "当前编号不存在");
        }
        return new MyResponseBody(200, "OK", companyInfo);
    }

    @RequestMapping("/update/companyInfo")
    @ResponseBody
    public Object updateCompanyInfo(EmployerCompanyInfo companyInfo) {
        if (companyInfo.getEmployerCompanyInfoId() == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "当前编号不存在");
        }
        System.out.println("companyInfo" + companyInfo);
        companyInfo.setEmployerCompanyInfoAuditState("待审批");
        employerCompanyInfoService.updateByPrimaryKeySelective(companyInfo);
        return new MyResponseBody(200, "OK", companyInfo);
    }

    @RequestMapping("/insert/positionInfo")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object insertPositionInfo(@RequestParam MultipartFile positionImg, EmployerPositionRequestBean positionRequestBean) {
        if (positionImg.isEmpty()) {
            return new MyResponseBody(ErrorCode.PICTURE_ERROR_CODE, ErrorCode.PICTURE_ERROR_DESCRIBE + "头像图片不能为空");
        }
        String positionImgSrc = UploadUtils.uploadImg(positionImg);
        if (positionImgSrc == null
                || positionImgSrc.isEmpty()) {
            return new MyResponseBody(ErrorCode.PICTURE_ERROR_CODE, ErrorCode.PICTURE_ERROR_DESCRIBE + "图片上传错误");
        }

        if (positionRequestBean == null
                || positionRequestBean.getEmployerPositionCity() == null
                || positionRequestBean.getEmployerPositionCity().isEmpty()
                || positionRequestBean.getEmployerPositionConnectInfo() == null
                || positionRequestBean.getEmployerPositionConnectInfo().isEmpty()
                || positionRequestBean.getEmployerPositionContent() == null
                || positionRequestBean.getEmployerPositionContent().isEmpty()
                || positionRequestBean.getEmployerPositionDate() == null
                || positionRequestBean.getEmployerPositionDate().isEmpty()
                || positionRequestBean.getEmployerPositionConnectType() == null
                || positionRequestBean.getEmployerPositionConnectType().isEmpty()
                || positionRequestBean.getEmployerPositionIndustry() == null
                || positionRequestBean.getEmployerPositionIndustry().isEmpty()
                || positionRequestBean.getEmployerPositionPersonNum() == null
                || positionRequestBean.getEmployerPositionPersonNum().isEmpty()
                || positionRequestBean.getEmployerPositionPlace() == null
                || positionRequestBean.getEmployerPositionPlace().isEmpty()
                || positionRequestBean.getEmployerPositionSalary() == null
                || positionRequestBean.getEmployerPositionSalary().isEmpty()
                || positionRequestBean.getEmployerPositionSettlement() == null
                || positionRequestBean.getEmployerPositionSettlement().isEmpty()
                || positionRequestBean.getEmployerPositionTitle() == null
                || positionRequestBean.getEmployerPositionTitle().isEmpty()
                || positionRequestBean.getEmployerPositionWelfare() == null
                || positionRequestBean.getEmployerPositionWelfare().isEmpty()
                || positionRequestBean.getEmployerPositionPersonRequirements() == null
                || positionRequestBean.getEmployerPositionPersonRequirements().isEmpty()) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "请求参数错误");
        }
        Integer requirementId = null;
        if (positionRequestBean.getAddPositionRequirement()) {
            if (positionRequestBean.getPositionRequirementAge() == null
                    && positionRequestBean.getPositionRequirementSex() == null
                    && positionRequestBean.getPositionRequirementHeight() == null
                    && positionRequestBean.getPositionRequirementEducation() == null) {
                return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "请求参数错误");
            }
            PositionRequirement requirement = new PositionRequirement(
                    positionRequestBean.getPositionRequirementAge(),
                    positionRequestBean.getPositionRequirementSex(),
                    positionRequestBean.getPositionRequirementHeight(),
                    positionRequestBean.getPositionRequirementEducation());
            positionRequirementService.insert(requirement);
            requirementId = requirement.getPositionRequirementId();
        }

        EmployerAccount account = employerAccountService.selectById(positionRequestBean.getEmployerAccountId());
        if (account == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "当前编号不存在");
        }
        EmployerPosition position = new EmployerPosition(
                requirementId,
                positionRequestBean.getEmployerAccountId(),
                positionRequestBean.getEmployerPositionTitle(),
                positionRequestBean.getEmployerPositionContent(),
                positionRequestBean.getEmployerPositionPersonNum(),
                positionImgSrc,
                positionRequestBean.getEmployerPositionSalary(),
                positionRequestBean.getEmployerPositionSettlement(),
                positionRequestBean.getEmployerPositionWelfare(),
                positionRequestBean.getEmployerPositionPlace(),
                positionRequestBean.getEmployerPositionDate(),
                positionRequestBean.getEmployerPositionConnectType(),
                positionRequestBean.getEmployerPositionConnectInfo(),
                positionRequestBean.getEmployerPositionPersonRequirements(),
                positionRequestBean.getEmployerPositionIndustry(),
                positionRequestBean.getEmployerPositionCity(),
                EmployerPositionState.SIGN_UP
        );
        employerPositionService.insertPosition(position);
        return new MyResponseBody(200, "OK", position);
    }

    @RequestMapping("/get/positionList")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object getPositionList(int accountId, String status) {
        System.out.println("accountId:" + accountId + " status:" + status);
        if (status == null
                || status.isEmpty()
                || (!EmployerPositionState.RECRUIT.equals(status)
                && !EmployerPositionState.OFFLINE.equals(status)
                && !EmployerPositionState.SIGN_UP.equals(status))) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "当前编号不存在");
        }
        List<EmployerPosition> result = employerPositionService.selectAllByAccountState(accountId, status);

        return new MyResponseBody(200, "OK", result);
    }

    @RequestMapping("/get/all/records")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object getRecord(int employerAccountId) {
        EmployerAccount account = employerAccountService.selectById(employerAccountId);
        if (account == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "当前编号不存在");
        }
        List<Records> recordsList = recordsService.selectAllByEmployerAccountId(employerAccountId);
        return new MyResponseBody(200, "OK", recordsList);
    }

    @RequestMapping("/get/recordsList/type")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object getRecordsByType(int employerAccountId, String type) {
        EmployerAccount account = employerAccountService.selectById(employerAccountId);
        if (account == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "当前编号不存在");
        }
        if (type == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "请求参数错误");
        }
        List<Records> recordsList = recordsService.selectAllByEmployerAccountIdType(employerAccountId, type);
        ArrayList<EmployerRecordResponse> resultList = new ArrayList<>();
        for (Records record : recordsList) {
            UsersAccount usersAccount = usersAccountService.selectById(record.getUsersAccountId());
            Users users = usersService.selectById(usersAccount.getUsersId());
            EmployerPosition position = employerPositionService.selectById(record.getEmployerPositionId());
            EmployerRecordResponse response = new EmployerRecordResponse(users, record, position);
            resultList.add(response);
        }
        return new MyResponseBody(200, "OK", resultList);
    }

    @RequestMapping("/set/giveUp")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object setGiveUp(int recordId) {
        Records records = recordsService.selectById(recordId);
        if (records == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "当前编号不存在");
        }
        records.setRecordStateEmployer(EmployerRecordState.REJECTED);
        records.setRecordStateUser(UserRecordState.OVER);
        recordsService.updateByPrimaryKeySelective(records);
        return new MyResponseBody(200, "OK");
    }

    @RequestMapping("/set/employ")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object setEmploy(int recordId) {
        Records records = recordsService.selectById(recordId);
        if (records == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "当前编号不存在");
        }
        records.setRecordStateEmployer(EmployerRecordState.SETTLING);
        records.setRecordStateUser(UserRecordState.ADMISSION);
        recordsService.updateByPrimaryKeySelective(records);
        return new MyResponseBody(200, "OK");
    }

    @RequestMapping("/set/settlement")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object setEmployment(int recordId) {
        Records records = recordsService.selectById(recordId);
        if (records == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "当前编号不存在");
        }
        records.setRecordStateEmployer(EmployerRecordState.FINISH);
        records.setRecordStateUser(UserRecordState.FINISH);
        recordsService.updateByPrimaryKeySelective(records);
        return new MyResponseBody(200, "OK");
    }

    @RequestMapping("/get/user/info")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object getUserInfo(int userAccountId) {
        UsersAccount account = usersAccountService.selectById(userAccountId);
        if (account == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "当前编号不存在");
        }
        Users users = usersService.selectById(account.getUsersId());
        if (users == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, ErrorCode.PARAMETER_ERROR_DESCRIBE + "当前编号不存在");
        }
        return new MyResponseBody(200, "OK", users);
    }


}
