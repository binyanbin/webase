import com.bin.api.Application;
import com.bin.api.controller.param.*;
import com.bin.api.controller.query.settings.CampusListQuery;
import com.bin.api.operate.base.LoginOp;
import com.bin.api.operate.domain.cache.WebSessionDo;
import com.bin.api.operate.setting.*;
import com.bin.webase.core.context.WeContext;
import com.bin.webase.core.operate.Result;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BiSettingTest extends BaseTest {
    private String openId = "ostH8wecUl03t3MXYSK07LPRF5yo";

    @Before
    public void before() throws Exception {
        createWebContext(null);
        UserLoginParam param = new UserLoginParam();
        param.setOpenId(openId);
        Result result = WeContext.getBean(LoginOp.class).execute(param);
        Assert.assertTrue(result.isSuccess());

        createWebContext(result.getData(WebSessionDo.class));
    }


    @Test
    public void operateCourse() {

        CourseParam courseParam = new CourseParam();
        courseParam.setName("中国舞教练专业系统班");
        courseParam.setIntroduce("教授目的:通过对“跳转翻”技术技巧的专攻练习，学员掌握每个技术技巧的开范要领和发力方式，从而让学员学会怎样做和训练好的关系。同时加强软开度能力素质技巧方面的训练，让学员提高综合性的舞蹈能力训教练水平\n" +
                "技术技巧训练大纲\n" +
                "软度能力类:肩倒立，胸倒立，侧肩倒立，侧肩翻，过肩翻蹬摆腿，曲肘倒立，曲肘前桥，倒立，前桥，后桥，撑手后软翻，侧手翻，侧空翻，跪地起脚背，圈绞柱，云里等跳类:小跳 (一二五位) ，中跳 (一二五位)，劈腿跳，吸撩腿大跳，凌空跃大跳，大射燕跳，元宝跳，倒踢紫金冠，双飞燕等\n" +
                "转类: 单一留头甩头，原地转，上步转，平转，四位转，跳转，端腿转，挥鞭转\n" +
                "旁腿转，搬腿转等翻类:踏步翻身，点翻身，吸翻身，串翻身，蹦了等(技术技巧根据学生基础和能力因材施教，逐步推进学习进度");
        courseParam.setPeriod("教学课时: 60课时，线下课时教学+线上打卡作业辅导");
        courseParam.setNeedToKnow("教授对象:技术技巧处于古典舞训练的高级阶段，培训对象应具备期、二期舞蹈能力水平，为力求在古典舞技术技巧方面能有新的突破和提高的舞蹈爱好者、舞蹈行业者，一二期段舞蹈学员渐进班。");
        courseParam.setClassTypeId(10001L);
        Result result = WeContext.getBean(AddCourseOp.class).execute(courseParam);
        Assert.assertTrue(result.isSuccess());

        Long courseId = result.getData(Long.class);
        courseParam.setId(courseId);
        courseParam.setName("中国舞教练专业系统班1");
        Assert.assertTrue(WeContext.getBean(UpdateCourseOp.class).execute(courseParam).isSuccess());

        IdListParam idListParam = new IdListParam(Lists.newArrayList(courseId));
        Assert.assertTrue(WeContext.getBean(DisableCourseOp.class).execute(idListParam).isSuccess());

        Assert.assertTrue(WeContext.getBean(DeleteCourseOp.class).execute(idListParam).isSuccess());
    }

    @Test
    public void operateCampus() {
        CampusParam courseParam = new CampusParam();
        courseParam.setName("开福校区");
        courseParam.setAddress("开福区政府旁边");

        Result result = WeContext.getBean(AddCampusOp.class).execute(courseParam);
        Assert.assertTrue(result.isSuccess());

        Long courseId = result.getData(Long.class);

        courseParam.setName("河西校区");
        courseParam.setId(courseId);
        Assert.assertTrue(WeContext.getBean(UpdateCampusOp.class).execute(courseParam).isSuccess());

        IdListParam idListParam = new IdListParam(Lists.newArrayList(courseId));
        Assert.assertTrue(WeContext.getBean(DisableCampusOp.class).execute(idListParam).isSuccess());

        Assert.assertTrue(WeContext.getBean(DeleteCampusOp.class).execute(idListParam).isSuccess());
        Assert.assertTrue(new CampusListQuery().getCode() == 0);
    }

    @Test
    public void operateClassType() {
        ClassTypeParam classTypeParam = new ClassTypeParam();
        classTypeParam.setName("教练班");
        classTypeParam.setDescription("教练专用");

        Result result = WeContext.getBean(AddClassTypeOp.class).execute(classTypeParam);
        Assert.assertTrue(result.isSuccess());

        Long courseId = result.getData(Long.class);

        classTypeParam.setId(courseId);
        classTypeParam.setName("河西校区");
        Assert.assertTrue(WeContext.getBean(UpdateClassTypeOp.class).execute(classTypeParam).isSuccess());

        IdListParam idListParam = new IdListParam(Lists.newArrayList(courseId));
        Assert.assertTrue(WeContext.getBean(DisableCampusOp.class).execute(idListParam).isSuccess());

        Assert.assertTrue(WeContext.getBean(DeleteClassTypeOp.class).execute(idListParam).isSuccess());
    }
}
