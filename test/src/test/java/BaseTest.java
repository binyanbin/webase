import com.bin.api.operate.domain.cache.WebSessionDo;
import com.bin.webase.core.web.ThreadWebContextHolder;
import com.bin.webase.core.web.WebContext;


public class BaseTest {


    public void createWebContext(WebSessionDo webSession) {
        WebContext webContext = new WebContext();
        if (webSession != null) {
            webContext.setToken(webSession.getModel());
        }
        webContext.setVersionId("10001");
        ThreadWebContextHolder.setContext(webContext);
    }


}
