import com.bin.api.Application;
import com.bin.api.dao.mybatis.model.Campus;
import com.bin.api.operate.domain.cache.WebSessionDo;
import com.bin.api.operate.domain.db.CampusDo;
import com.bin.webase.core.context.WeContext;
import com.bin.webase.core.web.ThreadWebContextHolder;
import com.bin.webase.core.web.WebContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTest {


    public void createWebContext(WebSessionDo webSession) {
        WebContext webContext = new WebContext();
        if (webSession != null) {
            webContext.setToken(webSession.getModel());
        }
        webContext.setVersionId("10001");
        ThreadWebContextHolder.setContext(webContext);
    }

    @Test
    public void test() {
        CampusDo campusDo = new CampusDo(10020L);
        WeContext.getUnitWork().remove(campusDo);
        WeContext.getUnitWork().commit();
        List<Campus> result = CampusDo.REPOSITORY.listCacheByBranchId(10045L, Campus.class);
        ;
    }


}
