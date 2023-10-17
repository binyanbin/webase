import com.bin.api.Application;
import com.bin.api.controller.param.*;
import com.bin.api.operate.domain.cache.ActiveBranch;
import com.bin.api.operate.domain.cache.ActiveEmployee;
import com.bin.api.operate.domain.cache.WebSessionDo;
import com.bin.api.operate.base.*;
import com.bin.api.operate.guest.BindGuestOp;
import com.bin.api.operate.guest.ScanBranchOp;

import com.bin.webase.core.context.WeContext;
import com.bin.webase.core.operate.Result;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserTest extends BaseTest {
    private String openId = "ostH8wecUl03t3MXYSK07LPRF5yo";

    @Before
    public void before() {
        createWebContext(null);
    }

    @Test
    public void createdBranch() {
        BranchParam branchParam = new BranchParam();
        branchParam.setAddress("address");
        branchParam.setName("branchName");
        branchParam.setIntroduce("introduce");
        Result result = WeContext.getBean(CreatedBranchOp.class).execute(branchParam);
        Assert.assertTrue(result.isSuccess());
        ActiveBranch activeBranch = result.getData(ActiveBranch.class);

        ActiveBranchParam activeBranchParam = new ActiveBranchParam();
        activeBranchParam.setBranchId(activeBranch.getBranchId());
        activeBranchParam.setActiveCode(activeBranch.getCode());
        activeBranchParam.setAdminOpenId(openId);
        activeBranchParam.setPhone("13755144076");
        activeBranchParam.setName("严彬");
        Assert.assertTrue(WeContext.getBean(ActiveBranchOp.class).execute(activeBranchParam).isSuccess());
    }

    @Test
    public void guestLogin() {
        ScanBranchParam scanBranchParam = new ScanBranchParam();
        scanBranchParam.setBranchId(10032L);
        scanBranchParam.setOpenId(openId);
        Result result = WeContext.getBean(ScanBranchOp.class).execute(scanBranchParam);
        Assert.assertTrue(result.isSuccess());
        createWebContext(result.getData(WebSessionDo.class));

        BindGuestParam param = new BindGuestParam();
        param.setName("严彬");
        param.setPhone("13755144076");
        result = WeContext.getBean(BindGuestOp.class).execute(param);
        Assert.assertTrue(result.isSuccess());

        result = WeContext.getBean(GuestSwitchBranchOp.class).execute(new IdParam(10031L));
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void employeeOperate() {
        loginSuccess();
        String type = "add";
        if (type.equals("add")) {
            EmployeeParam param = new EmployeeParam();
            param.setName("系统1");
            param.setAdmin(true);
            Result result = WeContext.getBean(AddEmployeeOp.class).execute(param);
            Assert.assertTrue(result.isSuccess());
            ActiveEmployee activeEmployee = result.getData(ActiveEmployee.class);

            ActiveEmployeeParam activeEmployeeParam = new ActiveEmployeeParam();
            activeEmployeeParam.setActiveCode(activeEmployee.getCode());
            activeEmployeeParam.setOpenId("ostH8weLAw5QYAYqW5p2jDE1tMR0");
            activeEmployeeParam.setPhone("137111111111");
            activeEmployeeParam.setId(activeEmployee.getId());

            Assert.assertTrue(WeContext.getBean(ActiveEmployeeOp.class).execute(activeEmployeeParam).isSuccess());
        } else if (type.equals("delete")) {
            Assert.assertTrue(WeContext.getBean(DeleteEmployeeOp.class).execute(new IdParam(10047L)).isSuccess());
        }

    }


    @Test
    public void loginSuccess() {
        UserLoginParam param = new UserLoginParam();
        param.setOpenId(openId);
        Result result = WeContext.getBean(LoginOp.class).execute(param);
        Assert.assertTrue(result.isSuccess());
        createWebContext(result.getData(WebSessionDo.class));
    }

    @Test
    public void loginFail() {
        UserLoginParam param = new UserLoginParam();
        param.setOpenId(openId + "0");
        Assert.assertTrue(WeContext.getBean(LoginOp.class).execute(param).isSuccess());
    }


}
