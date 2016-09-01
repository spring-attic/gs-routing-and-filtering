package hello.filters.pre;

import com.netflix.zuul.context.RequestContext;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SimpleFilterTest {

    private SimpleFilter filter;

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Before
    public void setup() {
        this.filter = new SimpleFilter();
    }

    @Test
    public void testFilterType() {
        assertThat(filter.filterType()).isEqualTo("pre");
    }

    @Test
    public void testFilterOrder() {
        assertThat(filter.filterOrder()).isEqualTo(1);
    }

    @Test
    public void testShouldFilter() {
        assertThat(filter.shouldFilter()).isTrue();
    }

    @Test
    public void testRun() {
        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getMethod()).thenReturn("GET");
        when(req.getRequestURL()).thenReturn(new StringBuffer("http://foo"));
        RequestContext context = mock(RequestContext.class);
        when(context.getRequest()).thenReturn(req);
        RequestContext.testSetCurrentContext(context);
        filter.run();
        this.outputCapture.expect(Matchers.containsString("GET request to http://foo"));
    }
}
