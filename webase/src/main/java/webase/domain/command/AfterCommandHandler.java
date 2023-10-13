package webase.domain.command;


import com.bin.webase.domain.unitwork.Runner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 不在工作单元内
 */
public class AfterCommandHandler {
    private static final ThreadLocal<List<Runner>> runners = new ThreadLocal<>();
    private static final ExecutorService executorService = Executors.newFixedThreadPool(4);

    public void add(Runner runner) {
        if (runner == null) {
            return;
        }
        List<Runner> finishes = runners.get();
        if (finishes == null) {
            finishes = new ArrayList<>();
            finishes.add(runner);
            runners.set(finishes);
        } else {
            finishes.add(runner);
        }
    }

    void execute() {
        executorService.execute(() -> {
            List<Runner> finishes = runners.get();
            if (finishes != null) {
                try {
                    for (int i = 0; i < finishes.size(); i++) {
                        Runner finish = finishes.get(i);
                        finish.run();
                    }
                } catch (Exception ignored) {
                }
            }
        });
        runners.remove();

    }
}
