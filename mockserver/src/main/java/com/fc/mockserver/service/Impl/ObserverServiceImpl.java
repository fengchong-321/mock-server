package com.fc.mockserver.service.Impl;

import com.fc.mockserver.model.MockContext;
import com.fc.mockserver.observer.*;
import com.fc.mockserver.service.ObserverService;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * service业务层
 */
@Service
public class ObserverServiceImpl implements ObserverService {
    private List<MockObserver> observers;

    ObserverServiceImpl() {
        this.observers = Lists.newArrayList(
                new ParamterResolveObserver(),
                new LoadMockConfigObserver(),
                new SelectStrategyObserver(),
                new MockDataParseObserver(),
                new DynamicStrategyObserver(),
                new CallbackObserver(),
                new PenetrateObserver(),
                new ThinkTimeObserver());
    }

    @Override
    public void execute(MockContext context) {
        for (MockObserver observer : observers) {
            observer.update(context);
        }
    }
}
